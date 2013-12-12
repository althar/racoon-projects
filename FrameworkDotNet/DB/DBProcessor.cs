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
            last_q = command;
            comm.ExecuteNonQuery();
        }
        public DataTable executeGet(string query)
        {
            connect();
            NpgsqlCommand comm = new NpgsqlCommand(query, connection);
            last_q = query;
            NpgsqlDataAdapter adapter = new NpgsqlDataAdapter(comm);
            DataTable t = new DataTable();
            adapter.Fill(t);
            return t;
        }
        public DataTable get(string tableName)
        {
            connect();
            string command = "SELECT * FROM "+tableName+" ORDER BY id";
            NpgsqlCommand comm = new NpgsqlCommand(command, connection);
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
        public bool importGoodsItem(DataRow item)
        {
            return importGoodsItem(item, 0);
        }
        public bool importGoodsItem(DataRow item,int category_id)
        {
            try
            {
                object articul = (object)item[0];
                object real_article = (object)item[5];
                if (articul is DBNull)
                {
                    return false;
                }
                if (real_article is DBNull)
                {
                    real_article = "";
                }
                else
                {
                    real_article = real_article.ToString();
                }
                if ((double)articul == 1245)
                {
                    string g = "";
                    g = "DD";
                }
                object web_page_url = (object)item[19];
                object name_eng = (object)item[21];
                object name_rus = (object)item[22];
                object name_for_shop = (object)item[20];
                object name_for_order = (object)item[1];
                object weight = (object)item[2];
                object minimum = (object)item[3];
                if (!(minimum is DBNull) && !(minimum == null))
                {
                    minimum = Convert.ToInt32(minimum);
                }
                else
                {
                    minimum = 0;
                }
                object quantity = (object)item[4];
                if (!(quantity is DBNull) && !(quantity == null))
                {
                    quantity = Convert.ToInt32(quantity);
                }
                else
                {
                    quantity = 0;
                }
                object weight_product = (object)item[24];
                if (!(weight_product is DBNull) && weight_product != null)
                {
                    weight_product = Double.Parse(weight_product.ToString());
                }
                object weight_for_site = (object)item[27].ToString();
                if (weight_product is DBNull)
                {
                    weight_product = 0.0;
                }
                object name_for_order_full = "";
                if (!(weight is DBNull) && !(weight == null))
                {
                    name_for_order_full = name_for_order + " " + weight.ToString().Replace(",", ".") + " kg.";
                }
                else
                {
                    name_for_order_full = name_for_order;
                }

                object price_basic = (object)item[6];
                if (!(price_basic is DBNull) && price_basic != null)
                {
                    price_basic = Double.Parse(price_basic.ToString());
                }
                object price = (object)item[10];
                object overprice_percent = (object)item[9];
                object price_discount = (object)item[8];
                object price_discount_percent = (object)item[7];
                
                object description = (object)item[29];
                object description_short = (object)item[30];
                object photo_url = (object)item[31];
                object keywords_meta = (object)item[32];
                object description_meta = (object)item[33];
                object page_header = (object)item[34];
                object sort = (object)item[35];
                if (!(sort is DBNull))
                {
                    sort = Int32.Parse(sort.ToString());
                }
                bool is_hidden = true;
                if (item[36] is DBNull)
                {
                    is_hidden = false;
                }
                object price_kg = (object)item[37];
                if (!(price_kg is DBNull) && price_kg != null)
                {
                    price_kg = Double.Parse(price_kg.ToString());
                }
                object profit = (object)item[38];
                if (!(profit is DBNull) && profit != null)
                {
                    profit = Double.Parse(profit.ToString());
                }
                object supplier = (object)item[12];
                object company = (object)item[13];
                object animal = (object)item[14];
                object food_type = (object)item[15];
                object food_type_age = (object)item[17];
                object food_type_category = (object)item[16];

                if (price is DBNull)
                {
                    return false;
                }
                if (supplier is DBNull || supplier == null)
                {
                    supplier = "";
                }
                Hashtable parameters = new Hashtable();
                parameters.Add("articul", articul);
                parameters.Add("real_article", real_article);
                parameters.Add("sort", sort);
                parameters.Add("minimum", minimum);
                if ((int)quantity > 0)
                {
                    parameters.Add("quantity", quantity);
                }
                parameters.Add("web_page_url", web_page_url);
                parameters.Add("name_rus", name_rus);
                parameters.Add("name_eng", name_eng);
                parameters.Add("name_for_shop", name_for_shop);
                parameters.Add("name_for_order", name_for_order);
                parameters.Add("name_for_order_full", name_for_order_full);
                parameters.Add("weight", weight);
                //if (!(weight is double) && !(weight is DBNull))
                //{
                //    string g = weight.ToString();
                //    weight = Double.Parse(g);
                //}
                parameters.Add("weight_product", weight_product);
                parameters.Add("price", price);
                parameters.Add("overprice_percent", overprice_percent);
                parameters.Add("price_discount", price_discount);
                parameters.Add("price_discount_percent", price_discount_percent);
                parameters.Add("price_basic", price_basic);
                parameters.Add("description", description);
                parameters.Add("description_short", description_short);
                parameters.Add("photo_url", photo_url);
                parameters.Add("keywords_meta", keywords_meta);
                parameters.Add("description_meta", description_meta);
                parameters.Add("page_header", page_header);
                parameters.Add("is_hidden", is_hidden);
                parameters.Add("price_kg", price_kg);
                parameters.Add("profit", profit);
                parameters.Add("supplier", supplier);
                parameters.Add("company", company);
                parameters.Add("animal", animal);
                parameters.Add("food_type", food_type);
                parameters.Add("food_type_age", food_type_age);
                parameters.Add("food_type_category", food_type_category);
                parameters.Add("modified", getServerTime());
                parameters.Add("category_id", category_id);
                parameters.Add("weight_for_site", weight_for_site);
                
                DataTable tab = null;
                tab = get("goods", "articul='" + articul + "'", "id");
                //if (weight is DBNull)
                //{
                //    tab = get("goods", "name_for_order='" + name_for_order + "'", "id");
                //}
                //else
                //{
                //    tab = get("goods", "name_for_order='" + name_for_order + "' AND weight = '" + weight.ToString().Replace(",", ".")+"'", "id");
                //}
                if (tab.Rows.Count <= 0)
                {
                    //return false;
                    insert("goods", parameters);
                }
                else
                {
                    update("goods", parameters, "articul='" + articul + "'");
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
        private string objectToString(object ob)
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

