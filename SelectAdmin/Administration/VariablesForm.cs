using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FTwoFramework;
using FTwoFramework.DB;

namespace LowByAdmin.Administration
{
    public partial class VariablesForm : Form
    {
        public VariablesForm()
        {
            InitializeComponent();
        }

        private void VariablesForm_Load(object sender, EventArgs e)
        {
            DataTable t = MainForm.dbProc.get("settings");
            DataGridViewVariables.DataSource = t;
            Configurator.translateToRussian(DataGridViewVariables);
            DataGridViewVariables.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.AllCells;
            DataGridViewVariables.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
        }

        private void DataGridViewVariables_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            object id_obj = Configurator.getValueFromDataGrid(DataGridViewVariables, "id");
            object name_obj = Configurator.getValueFromDataGrid(DataGridViewVariables, "name");
            object value_obj = Configurator.getValueFromDataGrid(DataGridViewVariables, "value");

            if (id_obj != null && id_obj is int)
            {
                ButtEdit.Enabled = true;
                LabelVarName.Text = (string)name_obj;
                TxtVarValue.Text = (string)value_obj;
                PanelChangeVar.Tag = id_obj;
            }
            else
            {
                ButtEdit.Enabled = false;
            }
        }

        private void ButtEdit_Click(object sender, EventArgs e)
        {
            PanelChangeVar.Visible = true;
        }

        private void ButtOK_Click(object sender, EventArgs e)
        {
            int id = (int)PanelChangeVar.Tag;
            Hashtable pars = new Hashtable();
            pars.Add("id", id);
            pars.Add("name", LabelVarName.Text);
            pars.Add("value", TxtVarValue.Text);
            MainForm.dbProc.update("settings", pars, "id=" + ((int)PanelChangeVar.Tag).ToString());
            PanelChangeVar.Visible = false;
            VariablesForm_Load(null, null);
        }
    }
}
