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
    public partial class DeliverTypesForm : Form
    {
        public DeliverTypesForm()
        {
            InitializeComponent();
        }

        private void DeliverTypesForm_Load(object sender, EventArgs e)
        {
            DataGridDeliverTypes.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.AllCells;
            DataGridDeliverTypes.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            DataTable t = MainForm.dbProc.get("deliver_types");
            DataGridDeliverTypes.DataSource = t;
            Configurator.translateToRussian(DataGridDeliverTypes);
        }
        private int current_id = 0;
        private void DataGridIncomeScheme_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            object id_obj = Configurator.getValueFromDataGrid(DataGridDeliverTypes, "id");
            if (id_obj != null)
            {
                ButtEdit.Enabled = true;
                ButtDelete.Enabled = true;
                current_id = (int)id_obj;
                NumPrice.Value = (int)Configurator.getValueFromDataGrid(DataGridDeliverTypes, "price");
                TxtName.Text = (string)Configurator.getValueFromDataGrid(DataGridDeliverTypes, "name");
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
            pars.Add("price", NumPrice.Value);
            pars.Add("name", TxtName.Text);
            if (!is_new)
            {
                MainForm.dbProc.update("deliver_types", pars, "id=" + current_id.ToString());
            }
            else
            {
                MainForm.dbProc.insert("deliver_types", pars);
            }
            PanelEdit.Visible = false;
            DeliverTypesForm_Load(null, null);
        }

        private void ButtDelete_Click(object sender, EventArgs e)
        {
            MainForm.dbProc.delete("deliver_types", current_id);
            DeliverTypesForm_Load(null, null);
        }
        private bool is_new = false;
        private void ButtAdd_Click(object sender, EventArgs e)
        {
            NumPrice.Value = 100;
            TxtName.Text = "";
            is_new = true;
            PanelEdit.Visible = true;
        }
    }
}

