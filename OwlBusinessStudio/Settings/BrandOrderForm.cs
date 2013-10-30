using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Settings
{
    public partial class BrandOrderForm : Form
    {
        public BrandOrderForm()
        {
            InitializeComponent();
            DataTable t = MainForm.dbProc.executeGet("SELECT DISTINCT(company) AS brand FROM goods WHERE company IS NOT NULL");
            for (int i = 0; i < t.Rows.Count; i++)
            {
                ListAllBrands.Items.Add(t.Rows[i]["brand"].ToString());
            }
        }

        private void ButtAdd_Click(object sender, EventArgs e)
        {
            if (ListAllBrands.SelectedItem != null)
            {
                String brand = ListAllBrands.SelectedItem.ToString();
                ListOrderedBrands.Items.Add(brand);
                ListAllBrands.Items.Remove(brand);
            }
        }

        private void ButtDelete_Click(object sender, EventArgs e)
        {
            if (ListOrderedBrands.SelectedItem != null)
            {
                ListAllBrands.Items.Add(ListOrderedBrands.SelectedItem.ToString());
                ListOrderedBrands.Items.Remove(ListOrderedBrands.SelectedItem);
            }
        }

        private void ButtSave_Click(object sender, EventArgs e)
        {
            
            if(ListOrderedBrands.Items.Count==0)
            {
                Close();
                return;
            }

            string brands = ListOrderedBrands.Items[0].ToString();
            for (int i = 1; i < ListOrderedBrands.Items.Count; i++)
            {
                brands += "," + ListOrderedBrands.Items[i].ToString();
            }
            MainForm.dbProc.executeNonQuery("INSERT INTO settings (name,value) VALUES ('brands_order','" + brands + "')");
            Close();
        }
    }
}
