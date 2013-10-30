using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OwlBusinessStudio.Goods
{
    public class GoodCategory
    {
        public int ID, Parent_id,Level;
        public string Name;
        public GoodCategory(int id,int parent_id,int level,string name)
        {
            ID = id;
            Parent_id = parent_id;
            Level = level;
            Name = name;
        }
    }
}
