using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Settings
{
    public partial class DriverMetrosForm : Form
    {
        public DriverMetrosForm()
        {
            InitializeComponent();
            loadElseMetros();
        }

        private void DriverMetrosForm_Load(object sender, EventArgs e)
        {
            ComboDrivers.DataSource = MainForm.dbProc.executeGet("SELECT id,first_name||' '||second_name||' '||last_name AS name FROM users WHERE role_id=3");
            ComboDrivers.DisplayMember = "name";
            ComboDrivers.ValueMember = "id";
        }
        private void loadElseMetros()
        {
            DataTable metros = MainForm.dbProc.executeGet("SELECT * FROM metros WHERE id NOT IN (SELECT distinct metro_id FROM driver_metros) ORDER BY name");
            ListMetros.DataSource = metros;
            ListMetros.DisplayMember = "name";
            ListMetros.ValueMember = "id";
        }
        private void loadDriversMetros(int driver_id)
        {
            DataTable driverMetros = MainForm.dbProc.executeGet("SELECT dm.priority,m.name,dm.id FROM driver_metros dm, metros m WHERE dm.metro_id=m.id AND driver_id="+driver_id.ToString()+" ORDER BY priority");
            DataGridViewMetros.DataSource = driverMetros;
            DataGridViewMetros.Columns["id"].Visible = false;
            FTwoFramework.DB.Configurator.translateToRussian(DataGridViewMetros);
            DataGridViewMetros.AutoResizeColumns(DataGridViewAutoSizeColumnsMode.AllCells);
        }

        private void ComboDrivers_SelectedValueChanged(object sender, EventArgs e)
        {
            if (ComboDrivers.SelectedValue is int)
            {
                loadDriversMetros((int)ComboDrivers.SelectedValue);
            }
        }

        private void ButtAdd_Click(object sender, EventArgs e)
        {
            if (ListMetros.SelectedValue is int&&ComboDrivers.SelectedValue is int)
            {
                int metro_id = (int)ListMetros.SelectedValue;
                int driver_id = (int)ComboDrivers.SelectedValue;
                Hashtable pars = new Hashtable();
                pars.Add("metro_id",metro_id);
                pars.Add("driver_id",driver_id);
                pars.Add("priority",DataGridViewMetros.RowCount);
                MainForm.dbProc.insert("driver_metros", pars);
                loadElseMetros();
                loadDriversMetros(driver_id);
            }
        }
        int currentIndex = -1;
        private void DataGridViewMetros_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                currentIndex = e.RowIndex;
                Text = currentIndex.ToString();
            }
        }

        private void DataGridViewMetros_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Delete)
            {
                int driver_metro_id = (int)DataGridViewMetros.Rows[currentIndex].Cells["id"].Value;
                int priority = (int)DataGridViewMetros.Rows[currentIndex].Cells["priority"].Value;
                int driver_id = (int)ComboDrivers.SelectedValue;
                MainForm.dbProc.executeNonQuery("UPDATE driver_metros SET priority = priority-1 WHERE priority>" + priority.ToString() + " AND driver_id=" + driver_id.ToString());
                MainForm.dbProc.delete("driver_metros", driver_metro_id);
                loadElseMetros();
                loadDriversMetros(driver_id);
            }
        }

        private void DataGridViewMetros_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            if (e.StateChanged == DataGridViewElementStates.Selected)
            {
                if (e.Cell.RowIndex >= 0)
                {
                    currentIndex = e.Cell.RowIndex;
                    //Text = currentIndex.ToString();
                }
            }
        }
    }
}
