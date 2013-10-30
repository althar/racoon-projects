using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FTwoFramework.DB;
using System.Collections;

namespace LowByAdmin.Administration
{
    public partial class BonusForm : Form
    {
        public BonusForm()
        {
            InitializeComponent();
        }

        private void BonusForm_Load(object sender, EventArgs e)
        {
            DataGridIncomeScheme.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.AllCells;
            DataGridIncomeScheme.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            DataTable t = MainForm.dbProc.get("income_schema");
            DataGridIncomeScheme.DataSource = t;
            Configurator.translateToRussian(DataGridIncomeScheme);
        }
        private int current_id = 0;
        private void DataGridIncomeScheme_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            object id_obj = Configurator.getValueFromDataGrid(DataGridIncomeScheme, "id");
            if (id_obj != null)
            {
                ButtEdit.Enabled = true;
                ButtDelete.Enabled = true;
                current_id = (int)id_obj;
                NumBonus.Value = (int)Configurator.getValueFromDataGrid(DataGridIncomeScheme, "profit_percent");
                NumMinClicks.Value = (int)Configurator.getValueFromDataGrid(DataGridIncomeScheme, "minimum_clients_clicks");
            }
            else
            {
                ButtEdit.Enabled = false;
                ButtDelete.Enabled = false;
                current_id = 0;
            }
        }

        private void ButtEdit_Click(object sender, EventArgs e)
        {
            is_new = false;
            PanelEdit.Visible = true;
        }

        private void ButtSave_Click(object sender, EventArgs e)
        {
            Hashtable pars = new Hashtable();
            pars.Add("profit_percent", NumBonus.Value);
            pars.Add("minimum_clients_clicks", NumMinClicks.Value);
            if (!is_new)
            {
                MainForm.dbProc.update("income_schema", pars, "id=" + current_id.ToString());
            }
            else
            {
                MainForm.dbProc.insert("income_schema", pars);
            }
            PanelEdit.Visible = false;
            BonusForm_Load(null, null);
        }

        private void ButtDelete_Click(object sender, EventArgs e)
        {
            MainForm.dbProc.delete("income_schema", current_id);
            BonusForm_Load(null, null);
        }
        private bool is_new = false;
        private void ButtAdd_Click(object sender, EventArgs e)
        {
            NumBonus.Value = 10;
            NumMinClicks.Value = 1000;
            is_new = true;
            PanelEdit.Visible = true;
        }
    }
}

