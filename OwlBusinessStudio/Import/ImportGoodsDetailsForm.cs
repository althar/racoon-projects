using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Import
{
    public partial class ImportGoodsDetailsForm : Form
    {
        private List<Object[]> tab = null;

        public ImportGoodsDetailsForm(List<Object[]> table)
        {
            InitializeComponent();
            tab = table;
           
        }
        private void ImportGoodsDetailsForm_Resize(object sender, EventArgs e)
        {
            dataGridView1.Width = Width - 40;
            dataGridView1.Height = Height - 80;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            for (int i = 0; i < 39; i++)
            {
                dataGridView1.Columns.Add(i.ToString(), i.ToString());
            }
            for (int i = 0; i < tab.Count; i++)
            {
                dataGridView1.Rows.Add(tab[i]);
            }
        }
    }
}
