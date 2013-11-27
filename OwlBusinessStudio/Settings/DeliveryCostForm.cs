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
            DataTable tab = MainForm.dbProc.executeGet("SELECT id,distance,\"from\",\"to\",price,pickup,discount FROM delivery_costs ORDER BY distance");
            DataGridTable.DataSource = tab;
            Configurator.translateToRussian(DataGridTable);
            DataTable distances = MainForm.dbProc.executeGet("SELECT DISTINCT distance FROM delivery_costs");
            ComboDistance.DataSource = distances;
            ComboDistance.DisplayMember = "distance";
            ComboDistance.ValueMember = "distance";
        }

        private void ButtSave_Click(object sender, EventArgs e)
        {
            if (ComboDistance.Text.Trim().Length < 2)
            {
                MessageBox.Show("Введите нормальное расстояние доставки");
            }
            Hashtable pars = new Hashtable();
            pars.Add("distance", ComboDistance.Text);
            pars.Add("\"from\"", (int)NumOrderFrom.Value);
            pars.Add("\"to\"", (int)NumOrderTo.Value);
            pars.Add("price", (int)NumDeliveryPrice.Value);
            pars.Add("discount", (int)NumDiscount.Value);
            pars.Add("pickup", checkboxPickup.Checked);
            MainForm.dbProc.insert("delivery_costs", pars);

            loadTable();
        }

        private void ButtDelete_Click(object sender, EventArgs e)
        {
            Object id = Configurator.getValueFromDataGrid(DataGridTable, "id");
            if (id != null)
            {
                MainForm.dbProc.delete("delivery_costs", (int)id);
                loadTable();
            }
        }
    }
}
