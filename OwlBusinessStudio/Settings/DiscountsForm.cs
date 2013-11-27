using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FTwoFramework.DB;

namespace OwlBusinessStudio.Settings
{
    public partial class DiscountsForm : Form
    {
        public DiscountsForm()
        {
            InitializeComponent();
            loadTab();
        }

        private void ButtSave_Click(object sender, EventArgs e)
        {
            Hashtable tab = new Hashtable();
            tab.Add("order_price", (int)NumPrice.Value);
            tab.Add("discount_percent", (int)NumDiscount.Value);
            tab.Add("cumulative", CheckCumulative.Checked);
            MainForm.dbProc.delete("discounts", "order_price="+NumPrice.Value.ToString());
            MainForm.dbProc.insert("discounts", tab);
            loadTab();
        }

        private void ButtClear_Click(object sender, EventArgs e)
        {
            MainForm.dbProc.delete("discounts", "1=1");
            loadTab();
        }
        private void loadTab()
        {
            DataGridTab.DataSource = MainForm.dbProc.executeGet("SELECT order_price,discount_percent,cumulative FROM discounts");
            Configurator.translateToRussian(DataGridTab);
        }
    }
}
