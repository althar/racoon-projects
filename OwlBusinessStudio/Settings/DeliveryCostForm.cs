using System;
using System.IO;
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
    public partial class DeliveryCostForm : Form
    {
        public DeliveryCostForm()
        {
            InitializeComponent();
            loadTable();
        }

        private void ButtClear_Click(object sender, EventArgs e)
        {
            MainForm.dbProc.delete("delivery_costs", "1=1");
            loadTable();
        }
        private void loadTable()
        {
            DataTable tab = MainForm.dbProc.executeGet("SELECT distance,high_low_price_separator,low_price_delivery_cost,high_price_delivery_cost FROM delivery_costs ORDER BY low_price_delivery_cost");
            DataGridTable.DataSource = tab;
            Configurator.translateToRussian(DataGridTable);
        }

        private void ButtSave_Click(object sender, EventArgs e)
        {
            if (TxtDistance.Text.Trim().Length < 2)
            {
                MessageBox.Show("Введите нормальное расстояние доставки");
            }
            MainForm.dbProc.delete("delivery_costs", "distance='" + TxtDistance.Text + "'");
            Hashtable pars = new Hashtable();
            pars.Add("distance", TxtDistance.Text);
            pars.Add("high_low_price_separator", (int)NumCostSeparator.Value);
            pars.Add("low_price_delivery_cost", (int)NumCheapOrderDeliveryCost.Value);
            pars.Add("high_price_delivery_cost", (int)NumExpensiveOrderDeliveryCost.Value);
            MainForm.dbProc.insert("delivery_costs", pars);

            loadTable();
        }
    }
}
