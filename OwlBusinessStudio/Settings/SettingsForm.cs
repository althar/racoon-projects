using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using OwlBusinessStudio;
using FTwoFramework.Helpers;
using FTwoFramework.DB;

namespace OwlBusinessStudio.Settings
{
    public partial class SettingsForm : Form
    {
        public SettingsForm()
        {
            InitializeComponent();
            load();
        }

        private void ButtChange_Click(object sender, EventArgs e)
        {
            string name = Configurator.getValueFromDataGrid(DataGridSettings, "name").ToString();
            string val = Configurator.getValueFromDataGrid(DataGridSettings, "value").ToString();
            SettingsEditForm f = new SettingsEditForm(name, val,"");
            f.Activate();
            f.Visible = true;
            f.FormClosed += new FormClosedEventHandler(f_FormClosed);
        }
        private void load()
        {
            DataGridSettings.DataSource = MainForm.dbProc.get("settings");
            Configurator.translateToRussian(DataGridSettings);
            DataGridSettings.DefaultCellStyle.WrapMode = DataGridViewTriState.True;
            DataGridSettings.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            DataGridSettings.Columns[0].Width = 30;
            DataGridSettings.Columns[1].Width = 200;
            DataGridSettings.Columns[2].Width = 500;
        }
        void f_FormClosed(object sender, FormClosedEventArgs e)
        {
            load();
        }

        private void DataGridSettings_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            try
            {
                string name = Configurator.getValueFromDataGrid(DataGridSettings, "name").ToString();
                string val = Configurator.getValueFromDataGrid(DataGridSettings, "value").ToString();
                ButtChange.Enabled = true;
            }
            catch (Exception ex)
            {
                ButtChange.Enabled = false;
            }
        }
    }
}
