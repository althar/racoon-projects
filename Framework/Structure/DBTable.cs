using System;
using System.Data;
using System.ComponentModel;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

namespace OwlBusinessStudioLib.Structure
{
    public class DBTable : IListSource
    {
        private ArrayList DBRecords;
        private DataTable SourceTable;

        public IList GetList()
        {
            return DBRecords;
        }
        public bool ContainsListCollection
        {
            get { return false; }
        }
        public DBTable(DataTable table)
        {
            DBRecords = new ArrayList();
            for (int i = 0; i < table.Rows.Count; i++)
            {
                Dictionary<string, object> fields = new Dictionary<string, object>();
                for(int j=0; j<table.Columns.Count; j++)
                {
                    fields.Add(table.Columns[j].ColumnName, table.Rows[i][j]);
                }
                DBRecord rec = new DBRecord(table.TableName, fields);
                DBRecords.Add(rec);
            }
            SourceTable = table;
        }
        public DataTable getSourceTable()
        {
            return SourceTable;
        }
    }
}
