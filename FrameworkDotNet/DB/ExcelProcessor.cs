using System;
using System.Data;
using System.Data.OleDb;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LinqToExcel.Domain;
using LinqToExcel.Extensions;
using LinqToExcel.Query;
using LinqToExcel;

namespace FTwoFramework.DB
{
    public class ExcelProcessor
    {
        public static DataTable getTable(string excelFilePath, string excelPageName)
        {
            bool isXLSX = excelFilePath.Contains(".xlsx");
            string connectionString = "Provider=Microsoft.Jet.OLEDB.4.0; data source=" + excelFilePath + "; Extended Properties=Excel 8.0;";
            string connectionStringXLSX = "Provider=Microsoft.ACE.OLEDB.12.0; Data Source=" + excelFilePath + "; Extended Properties=Excel 12.0 Xml;";

            OleDbConnection con = null;
            string selectString = "SELECT * FROM ["+excelPageName+"$]";
            if (isXLSX)
            {
                con = new OleDbConnection(connectionStringXLSX);
            }
            else
            {
                con = new OleDbConnection(connectionString);
            }
            OleDbCommand cmd = new OleDbCommand(selectString, con);
            con.Open();
            OleDbDataAdapter adapter = new OleDbDataAdapter(cmd);
            DataTable t = new DataTable();
            adapter.Fill(t);
            con.Close();
            return t;
        }
        public static DataTable getTableLink(string path)
        {
            ExcelQueryFactory factory = new ExcelQueryFactory(path);

            ExcelQueryable<Row> sheet =  factory.Worksheet(0);

            return new DataTable();
        }
    }
}
