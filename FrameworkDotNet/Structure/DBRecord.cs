using System;
using System.ComponentModel;
using System.Data;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

namespace OwlBusinessStudioLib.Structure
{
    public class DBRecord : Dictionary<string,object>
    {
        private object ID;
        private Dictionary<string, Object> Fields;
        private string EntityName;

        public Dictionary<string, Object> getFields()
        {
            return Fields;
        }
        public string getEntity()
        {
            return EntityName;
        }
        public object getID()
        {
            return ID;
        }
        public DBRecord(string entity, Dictionary<string, Object> fields)
        {
            EntityName = entity;
            ID = (int)fields["id"];
            Fields = new Dictionary<string, object>();
        }
    }
}
