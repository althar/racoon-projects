using System.IO;
using System;
using System.Collections.Generic;
using System.Collections;
using System.Data.SqlClient;
using System.Data.Sql;
using System.Data;
using System.Data.SqlTypes;
using System.Linq;
using System.Text;
using Npgsql;
using NpgsqlTypes;

namespace FTwoFramework.DB
{
    public class DBProcessor
    {
        #region Init
        private NpgsqlConnection connection;

        private string dbAddress = "85.159.44.59", dbName = "zoomag", dbUser = "althar", dbPassword = "bredok", dbPort = "5432";
        private NpgsqlCommand ping = new NpgsqlCommand("SELECT 1");

        public DBProcessor(string configFile)
        {
            try
            {
                string[] pars = Configurator.unpackFromFile(configFile);
                connect(pars[0], pars[1], pars[2], pars[3]);
                dbAddress = pars[0];
                dbName = pars[1];
                dbUser = pars[2];
                dbPassword = pars[3];
            }
            catch (Exception ex)
            {
                throw new Exception("Error while creating DBProcessor: " + ex.ToString());
            }
        }
        public DBProcessor(string host, string name, string login, string password)
        {
            try
            {
                connect(host, name, login, password);
                Configurator.packToFile(new string[4] { host, name, login, password }, Environment.CurrentDirectory + "/configurations/dbconfig.conf");
            }
            catch (Exception ex)
            {
                throw new Exception(ex.ToString());
            }
        }
        public bool isConnected
        {
            get
            {
                try
                {
                    ping.Connection = connection;
                    ping.ExecuteNonQuery();
                    return true;
                }
                catch
                {
                    return false;
                }
            }
        }
        public void connect(string host, string name, string user, string password)
        {
            dbAddress = host;
            dbName = name;
            dbUser = user;
            dbPassword = password;
            connect();
        }
        public void connect()
        {
            if (!isConnected)
            {
                connection = new NpgsqlConnection("Server=" + dbAddress
                    + "; Port=" + dbPort
                    + "; User Id=" + dbUser
                    + ";Password=" + dbPassword
                    + ";Database=" + dbName + ";");
                connection.Open();
            }
        }
        public void disconnect()
        {
            connection.Close();
        }
        #endregion

        public DateTime getServerTime()
        {
            DataTable timeT = executeGet("SELECT now() as current_time");
            return (DateTime)timeT.Rows[0][0];
        }
        public NpgsqlDataReader executeStoredProcedure(string procedure_name, List<NpgsqlParameter> parameters)
        {
            NpgsqlCommand comm = new NpgsqlCommand(procedure_name, connection);
            comm.CommandType = CommandType.StoredProcedure;
            comm.Parameters.AddRange(parameters.ToArray());
            return comm.ExecuteReader();
        }
        public void executeNonQuery(string query)
        {
            connect();
            string command = query;
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            comm.CommandTimeout = 300;
            last_q = command;
            comm.ExecuteNonQuery();
        }
        public DataTable executeGet(string query)
        {
            connect();
            NpgsqlCommand comm = new NpgsqlCommand(query, connection);
            comm.CommandTimeout = 2000;
            last_q = query;
            NpgsqlDataAdapter adapter = new NpgsqlDataAdapter(comm);
            DataTable t = new DataTable();
            adapter.Fill(t);
            return t;
        }
        public DataRow executeGetRow(string query)
        {
            DataTable t = executeGet(query);
            if (t.Rows.Count > 0)
            {
                return t.Rows[0];
            }
            return null;
        }
        public DataTable get(string tableName)
        {
            connect();
            string command = "SELECT * FROM "+tableName+" ORDER BY id";
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            comm.CommandTimeout = 2000;
            last_q = command;
            NpgsqlDataAdapter adapter = new NpgsqlDataAdapter(comm);
            DataTable t = new DataTable();
            adapter.Fill(t);
            t.TableName = tableName;
            return t;
        }
        public DataTable get(string tableName, string condition)
        {
            connect();
            string command = "SELECT * FROM " + tableName;
            if (condition != null & condition != "")
            {
                command += " WHERE " + condition;
            }
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            last_q = command;
            NpgsqlDataAdapter adapter = new NpgsqlDataAdapter(comm);
            DataTable t = new DataTable();
            adapter.Fill(t);
            t.TableName = tableName;
            return t;
        }
        public DataTable get(string tableName, string condition, string fields)
        {
            connect();
            string command = "SELECT "+fields+" FROM " + tableName;
            if (condition != null & condition != "")
            {
                command += " WHERE " + condition;
            }
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            last_q = command;
            NpgsqlDataAdapter adapter = new NpgsqlDataAdapter(comm);
            DataTable t = new DataTable();
            adapter.Fill(t);
            t.TableName = tableName;
            return t;
        }
        public long setSetting(string name, string value)
        {
            delete("settings", "name='" + name + "'");
            Hashtable tab = new Hashtable();
            tab.Add("name",name);
            tab.Add("value",value);
            return insert("settings",tab);
        }
        public long setContent(string name, string value)
        {
            delete("content", "name='" + name + "'");
            Hashtable tab = new Hashtable();
            tab.Add("name", name);
            tab.Add("value", value);
            return insert("content", tab);
        }
        public long insert(string tableName, Hashtable parameters)
        {
            connect();
            string command = "INSERT INTO " + tableName + " (";
            IDictionaryEnumerator enumer = parameters.GetEnumerator();
            List<object> keys = new List<object>();
            List<object> values = new List<object>();
            while (enumer.MoveNext())
            {
                keys.Add(enumer.Key);
                values.Add(enumer.Value);
            }
            for(int i=0; i<keys.Count; i++)
            {
                command+=keys[i].ToString();
                if(i<keys.Count-1)
                {
                    command+=",";
                }
            }
            command+=") VALUES (";
            for(int i=0; i<values.Count; i++)
            {
                command+=objectToString(values[i]);
                if(i<keys.Count-1)
                {
                    command+=",";
                }
            }
            if (parameters.ContainsKey("id"))
            {
                command += "); SELECT "+parameters["id"]+";";
            }
            else
            {
                command += "); SELECT currval('" + tableName + "_id_seq');";
            }
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            last_q = command;
            object iddd = comm.ExecuteScalar();
            if (iddd is int)
            {
                return (int)iddd;
            }
            return (long)iddd;
        }
        public void delete(string tableName, int rowID)
        {
            connect();
            string command = "DELETE FROM " + tableName+" WHERE id="+rowID.ToString();
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            last_q = command;
            comm.ExecuteNonQuery();
        }
        public void delete(string tableName, string condition)
        {
            connect();
            string command = "DELETE FROM " + tableName;
            if (condition != null && condition != "")
            {
                command += " WHERE " + condition;
            }
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            last_q = command;
            comm.ExecuteNonQuery();
        }
        public void update(string tableName, Hashtable parameters, string condition)
        {
            connect();
            string command = "UPDATE " + tableName+" SET ";
            IDictionaryEnumerator enumer = parameters.GetEnumerator();
            List<object> keys = new List<object>();
            List<object> values = new List<object>();
            while (enumer.MoveNext())
            {
                keys.Add(enumer.Key);
                values.Add(enumer.Value);
            }
            for (int i = 0; i < keys.Count; i++)
            {
                command += keys[i];
                command += "=";
                command += objectToString(values[i]);
                
                if (i < keys.Count - 1)
                {
                    command += ", ";
                }
            }
            if(condition!="")
            {
                command += " WHERE " + condition;
            }
            
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            last_q = command;
            comm.ExecuteNonQuery();
        }
        public void update(string tableName, string parameters, string condition)
        {
            connect();
            string command = "UPDATE " + tableName + " SET ";
            command += parameters;
            if (condition != "")
            {
                command += " WHERE " + condition;
            }

            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
            last_q = command;
            comm.ExecuteNonQuery();
        }
        public string getSetting(string name)
        {
            DataTable t = get("settings", "name='" + name + "'");
            if (t.Rows.Count > 0)
            {
                return t.Rows[0]["value"].ToString();
            }
            return "";
        }
        private object getValue(DataRow item, string[] field_names,string name)
        {
            int index = 0;
            for (int i = 0; i < field_names.Length; i++)
            {
                if (field_names[i] == name)
                {
                    index = i;
                    break;
                }
            }
            object o = null;
            if (item.ItemArray.Length > index)
            {
                o = item[index];
            }
            if (o is DBNull)
            {
                return null;
            }
            return o;
        }
        private string[] fields = null;
        private bool getBool(DataRow item, string[] field_names, string name)
        {
            object o = getValue(item, field_names, name);
            bool result = false;
            Boolean.TryParse(o.ToString(), out result);
            return result;
        }
        private int getInt(DataRow item, string[] field_names, string name)
        {
            object o = getValue(item, field_names, name);
            int result = 0;
            Int32.TryParse(o.ToString(), out result);
            return result;
        }
        private double getDouble(DataRow item, string[] field_names, string name)
        {
            object o = getValue(item, field_names, name);
            double result = 0.0;
            Double.TryParse(o.ToString(), out result);
            return result;
        }
        private string getString(DataRow item, string[] field_names, string name)
        {
            object o = getValue(item, field_names, name);
            return o.ToString();
        }
        public string[] extractFields(DataRow item)
        {
            string[] result = new string[item.ItemArray.Length];
            for(int i=0; i<item.ItemArray.Length; i++)
            {
                result[i] = item.ItemArray[i].ToString();
            }
            return result;
        }
        public bool importGoodsItem(DataRow item, string[] fields)
        {
            try
            {
                Hashtable pars = new Hashtable();
                pars.Add("articul", getString(item, fields, "articul"));
                pars.Add("name_rus", getString(item, fields, "name_rus"));
                pars.Add("name_eng", getString(item, fields, "name_eng"));
                pars.Add("name_for_shop", getString(item, fields, "name_for_shop"));
                pars.Add("name_for_order", getString(item, fields, "name_for_order"));
                pars.Add("weight", getString(item, fields, "weight"));
                pars.Add("weight_product", getDouble(item, fields, "weight_product"));
                pars.Add("overprice_percent", getDouble(item, fields, "overprice_percent"));
                pars.Add("price_discount", getDouble(item, fields, "price_discount"));
                pars.Add("price_discount_percent", getDouble(item, fields, "price_discount_percent"));
                pars.Add("price_basic", getDouble(item, fields, "price_basic"));
                pars.Add("description", getString(item, fields, "description"));
                pars.Add("description_short", getString(item, fields, "description_short"));
                pars.Add("photo_url", getString(item, fields, "photo_url"));
                pars.Add("is_hidden", getBool(item, fields, "is_hidden"));
                pars.Add("enabled", getBool(item, fields, "enabled"));
                pars.Add("price_kg", getDouble(item, fields, "price_kg"));
                pars.Add("profit", getDouble(item, fields, "profit"));
                pars.Add("supplier", getString(item, fields, "supplier"));
                pars.Add("company", getString(item, fields, "company"));
                pars.Add("animal", getString(item, fields, "animal"));
                pars.Add("food_type", getString(item, fields, "food_type"));
                pars.Add("food_type_age", getString(item, fields, "food_type_age"));
                pars.Add("food_type_category", getString(item, fields, "food_type_category"));
                if (getBool(item, fields, "change_quantity"))
                {
                    pars.Add("quantity", getInt(item, fields, "quantity"));
                }
                pars.Add("minimum", getInt(item, fields, "minimum"));
                pars.Add("price", getInt(item, fields, "price"));
                pars.Add("name_for_order_full", getString(item, fields, "name_for_order_full"));
                pars.Add("category_id", getInt(item, fields, "category_id"));
                pars.Add("sort", getInt(item, fields, "sort"));
                pars.Add("weight_for_site", getString(item, fields, "weight_for_site"));
                pars.Add("real_article", getString(item, fields, "real_articul"));

                DataTable tab = executeGet("SELECT articul FROM goods WHERE articul='"+getString(item,fields,"articul")+"'");
                if (tab != null && tab.Rows.Count > 0)
                {
                    update("goods", pars, "articul='" + tab.Rows[0]["articul"] + "'");
                }
                else
                {
                    insert("goods", pars);
                }
                return true;
            }
            catch (Exception ex)
            {
                string exxx = ex.ToString();
                return false;
            }
        }
        private static string last_q = "";
        public static string objectToString(object ob)
        {
            if (ob is DBNull||ob == null)
            {
                return "NULL";
            }
            if (ob is bool)
            {
                return ((bool)ob).ToString().ToUpper();
            }
            if (ob is double)
            {
                return ((double)ob).ToString().Replace(",",".");
            }
            if (ob is int)
            {
                return ((int)ob).ToString();
            }
            if (ob is decimal)
            {
                return ((decimal)ob).ToString();
            }
            if (ob is long)
            {
                return ((long)ob).ToString();
            }
            if (ob is string)
            {
                return "'" + ((string)ob).Replace("'","`") + "'";
            }
            if (ob is DateTime)
            {
                DateTime date = (DateTime)ob;
                return "'"+date.Year.ToString() + "-" + date.Month.ToString() + "-" + date.Day.ToString() + " " + date.Hour.ToString() + ":" + date.Minute + ":0'";
            }
            return "";
        }
        public string getDateTimeString(DateTime date)
        {
            return "'" + date.Year.ToString() + "-" + date.Month.ToString() + "-" + date.Day.ToString() + " " + date.Hour.ToString() + ":" + date.Minute + ":0'";
        }
        public bool checkExists(string expression, out DataTable t)
        {
            t = executeGet(expression);
            return t.Rows.Count > 0;
        }
    }
}

