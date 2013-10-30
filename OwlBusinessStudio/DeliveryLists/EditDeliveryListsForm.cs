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

namespace OwlBusinessStudio.DeliveryLists
{
    public partial class EditDeliveryListsForm : Form
    {
        private Hashtable DriverLists = new Hashtable();
        private Hashtable DriverListsNew = new Hashtable();
        private Hashtable DriverListsResult = new Hashtable();
        private DataTable Drivers = null;
        private DataTable CurrentList = null;
        private int currentRowID = 0;
        private MainForm parent;
        private DateTime CurrentListsDate;

        public EditDeliveryListsForm(MainForm owner,DateTime t)
        {
            InitializeComponent();
            parent = owner;
            CurrentListsDate = t;
            loadAll();
        }
        public void loadAll()
        {
            loadDrivers();
            loadCurrentDeliveryLists();
            loadNewDeliveryLists();
            createResultList();
            reloadDrivers();
            ListDrivers_SelectedIndexChanged(null, null);
            checkCurrentRow();
        }
        public void loadDrivers()
        {
            Drivers = MainForm.dbProc.executeGet("SELECT *, CASE first_name WHEN '' THEN second_name ELSE (substring(first_name, 1, 1) || '. ')|| second_name"
            + " END AS fio, CASE first_name WHEN '' THEN second_name ELSE (substring(first_name, 1, 1) || '. ')|| second_name"
            + " END AS fio2 FROM users WHERE role_id=3 ORDER BY fio");
            ListDrivers.DataSource = Drivers;
            ListDrivers.DisplayMember = "fio";
            ListDrivers.ValueMember = "id";
            ButtDrivers.DropDownItems.Clear();
            for(int i=0; i<Drivers.Rows.Count; i++)
            {
                ToolStripMenuItem drItem = new ToolStripMenuItem(Drivers.Rows[i]["fio"].ToString());
                drItem.Tag = Drivers.Rows[i]["id"];
                drItem.Click += new EventHandler(drItem_Click); 
                ButtDrivers.DropDownItems.Add(drItem);
            }
            
        }
        public void reloadDrivers()
        {
            for (int i = 0; i < ListDrivers.Items.Count; i++)
            {
                int count = ((DataTable)DriverListsResult[((DataRowView)ListDrivers.Items[i]).Row["id"]]).Rows.Count;
                ((DataRowView)ListDrivers.Items[i]).Row["fio"] = ((string)((DataRowView)ListDrivers.Items[i]).Row["fio2"])+" ("+count.ToString()+")";
            }
        }
        private void checkCurrentRow()
        {
            if (DataGridViewDeliveryLists.RowCount > 0 && DataGridViewDeliveryLists.SelectedCells.Count<=0)
            {
                currentRowID = (int)DataGridViewDeliveryLists[0, 0].Value;
                DataGridViewDeliveryLists[0, 0].Selected = true;
                Text = currentRowID.ToString();
                return;
            }
            if (DataGridViewDeliveryLists.SelectedCells.Count > 0)
            {
                currentRowID = (int)DataGridViewDeliveryLists[0, DataGridViewDeliveryLists.SelectedCells[0].RowIndex].Value;
            }
            else
            {
                currentRowID = 0;
            }
        }
        void drItem_Click(object sender, EventArgs e)
        {
            int newDriverID = (int)((ToolStripMenuItem)sender).Tag;
            int rowIndex = getRowIndex();
            if (rowIndex >= 0)
            {
                DataTable newDriversTable = (DataTable)DriverListsResult[newDriverID];
                DataRow orderToMove = newDriversTable.NewRow();
                orderToMove.ItemArray = CurrentList.Rows[rowIndex].ItemArray;
                CurrentList.Rows.RemoveAt(rowIndex);
                newDriversTable.Rows.Add(orderToMove);
                checkCurrentRow();
            }
            Text = currentRowID.ToString();
            reloadDrivers();
        }
        public void loadCurrentDeliveryLists()
        {
            DriverLists = new Hashtable();
            for(int i=0; i<Drivers.Rows.Count; i++)
            {
                DataTable drTable = MainForm.dbProc.executeGet("SELECT DISTINCT owd.id,owd.address,owd.deliver_price,owd.total_price,owd.deliver_time,false AS is_new,priority FROM delivery_lists dl,orders_with_details owd WHERE owd.id=dl.order_id AND owd.status_id=1 AND owd.deliver_date=date(" + MainForm.dbProc.getDateTimeString(CurrentListsDate) + ") AND dl.driver_id=" + Drivers.Rows[i]["id"] + " GROUP BY owd.id,owd.address,owd.deliver_price,owd.total_price,deliver_time,dl.priority ORDER BY dl.priority");
                DriverLists.Add(Drivers.Rows[i]["id"], drTable);
            }
        }
        public void loadNewDeliveryLists()
        {
            DriverListsNew = new Hashtable();
            for (int i = 0; i < Drivers.Rows.Count; i++)
            {
                DataTable drTable = null;
                if (((int)Drivers.Rows[i]["id"] == 0))
                {
                    string command = "SELECT DISTINCT owd.id,owd.address,owd.deliver_price,owd.total_price,owd.deliver_time,true AS is_new,0 AS priority"
                    + " FROM orders_with_details owd,orders ord WHERE ord.id = owd.id AND owd.status_id=1 AND owd.deliver_date=date(" + MainForm.dbProc.getDateTimeString(CurrentListsDate) + ") AND owd.id NOT IN (SELECT order_id FROM delivery_lists) AND owd.driver_id=" + Drivers.Rows[i]["id"] + " GROUP BY owd.id,owd.address,owd.deliver_price,owd.total_price,deliver_time,priority ORDER BY priority";
                    drTable = MainForm.dbProc.executeGet(command);
                }
                else
                {
                    string command = "SELECT DISTINCT owd.id,owd.address,owd.deliver_price,owd.total_price,owd.deliver_time,true AS is_new,dm.priority AS priority"
                    + " FROM orders_with_details owd,orders ord,driver_metros dm WHERE ord.metro_id=dm.metro_id AND ord.id = owd.id AND owd.status_id=1 AND owd.deliver_date=date(" + MainForm.dbProc.getDateTimeString(CurrentListsDate) + ") AND owd.id NOT IN (SELECT order_id FROM delivery_lists) AND owd.driver_id=" + Drivers.Rows[i]["id"] + " GROUP BY owd.id,owd.address,owd.deliver_price,owd.total_price,deliver_time,priority ORDER BY priority";
                    drTable = MainForm.dbProc.executeGet(command);
                }
                
                DriverListsNew.Add(Drivers.Rows[i]["id"], drTable);
            }
        }
        public void createResultList()
        {
            DriverListsResult = new Hashtable();
            for (int i = 0; i < Drivers.Rows.Count; i++)
            {
                DataTable resForDriver = (DataTable)DriverLists[Drivers.Rows[i]["id"]];
                DataTable toAdd = (DataTable)DriverListsNew[Drivers.Rows[i]["id"]];
                for(int j=0; j<toAdd.Rows.Count; j++)
                {
                    resForDriver.Rows.Add(toAdd.Rows[j].ItemArray);
                }
                DriverListsResult.Add(Drivers.Rows[i]["id"], resForDriver);
            }
        }
        private void formatTable()
        {
            if (DataGridViewDeliveryLists.Columns.Count > 0)
            {
                DataGridViewDeliveryLists.Columns[5].Visible = false;
            }
            Color colBlue = Color.FromArgb(209, 240, 252);
            Color colGreen = Color.FromArgb(255, 255, 255);
            for (int i = 0; i < DataGridViewDeliveryLists.RowCount; i++)
            {
                if (((bool)DataGridViewDeliveryLists[5, i].Value) == true)
                {
                    DataGridViewDeliveryLists.Rows[i].DefaultCellStyle.BackColor = colGreen;
                }
                else
                {
                    DataGridViewDeliveryLists.Rows[i].DefaultCellStyle.BackColor = colBlue;
                }
            }
            if (DataGridViewDeliveryLists.ColumnCount > 0)
            {
                DataGridViewDeliveryLists.Columns[6].Visible = false;
                DataGridViewDeliveryLists.Columns[1].Width = 300;
            }
            Configurator.translateToRussian(DataGridViewDeliveryLists);
        }
        public void showListOfDriver(int driver_id)
        {
            CurrentList = (DataTable)DriverListsResult[driver_id];
            DataGridViewDeliveryLists.DataSource = CurrentList;
            checkCurrentRow();
            formatTable();
        }

        private void ListDrivers_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ListDrivers.SelectedValue is int)
            {
                currentRowID = 0;
                showListOfDriver((int)ListDrivers.SelectedValue);
            }
            formatTable();
        }

        private void DataGridViewDeliveryLists_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            if (e.StateChanged == DataGridViewElementStates.Selected && DataGridViewDeliveryLists.SelectedCells.Count == 1)
            {
                currentRowID = (int)e.Cell.OwningRow.Cells["id"].Value;
            }
            else
            {
                currentRowID = 0;
            }
            Text = currentRowID.ToString();
        }

        private void DataGridViewDeliveryLists_RowStateChanged(object sender, DataGridViewRowStateChangedEventArgs e)
        {
            if (e.StateChanged == DataGridViewElementStates.Selected)
            {
                currentRowID = (int)e.Row.Cells["id"].Value;
            }
            Text = currentRowID.ToString();
        }

        private int getRowIndex()
        {
            if (currentRowID != 0)
            {
                for (int i = 0; i < CurrentList.Rows.Count; i++)
                {
                    if ((int)CurrentList.Rows[i]["id"] == currentRowID)
                    {
                        return i;
                    }
                }
            }
            return -1;
        }
        private void PickUp_Click(object sender, EventArgs e)
        {
            int index = getRowIndex();
            moveUp(index);
        }
        private void PicDown_Click(object sender, EventArgs e)
        {
            int index = getRowIndex();
            moveDown(index);
        }
        private void moveDown(int rowIndex)
        {
            if (rowIndex >= 0 && rowIndex < CurrentList.Rows.Count - 1)
            {
                DataRow toDown = CurrentList.NewRow();
                toDown.ItemArray = CurrentList.Rows[rowIndex].ItemArray;
                DataRow toSwitch = CurrentList.NewRow();
                toSwitch.ItemArray = CurrentList.Rows[rowIndex+1].ItemArray;


                CurrentList.Rows.RemoveAt(rowIndex);
                CurrentList.Rows.InsertAt(toSwitch, rowIndex);
                CurrentList.Rows.RemoveAt(rowIndex+1);
                CurrentList.Rows.InsertAt(toDown, rowIndex+1);

                DataGridViewDeliveryLists.CurrentCell = DataGridViewDeliveryLists[1, rowIndex+1];
            }
            formatTable();
        }
        private void moveUp(int rowIndex)
        {
            if (rowIndex > 0 && rowIndex <= CurrentList.Rows.Count - 1)
            {
                DataRow toUp = CurrentList.NewRow();
                toUp.ItemArray = CurrentList.Rows[rowIndex].ItemArray;
                DataRow toSwitch = CurrentList.NewRow();
                toSwitch.ItemArray = CurrentList.Rows[rowIndex - 1].ItemArray;


                CurrentList.Rows.RemoveAt(rowIndex);
                CurrentList.Rows.InsertAt(toSwitch, rowIndex);
                CurrentList.Rows.RemoveAt(rowIndex - 1);
                CurrentList.Rows.InsertAt(toUp, rowIndex - 1);

                DataGridViewDeliveryLists.CurrentCell = DataGridViewDeliveryLists[1, rowIndex - 1];
            }
            formatTable();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            formatTable();
        }

        private void EditDeliveryListsForm_Activated(object sender, EventArgs e)
        {
            formatTable();
        }

        private void ButtRefresh_Click(object sender, EventArgs e)
        {
            loadAll();
        }

        private void ButtSave_Click(object sender, EventArgs e)
        {
            IDictionaryEnumerator iterator = DriverListsResult.GetEnumerator();
            iterator.Reset();
            StringBuilder builder = new StringBuilder("");
            while (iterator.MoveNext())
            {
                int driverID = (int)iterator.Key;
                DataTable driversList = (DataTable)iterator.Value;
                for (int i = 0; i < driversList.Rows.Count; i++)
                {
                    DataRow r = driversList.Rows[i];
                    builder.Append("DELETE FROM delivery_lists WHERE order_id=" + r["id"].ToString() + "; ");
                    builder.Append("INSERT INTO delivery_lists (order_id,driver_id,priority) VALUES (");
                    builder.Append(r["id"].ToString());
                    builder.Append(",");
                    builder.Append(driverID.ToString());
                    builder.Append(",");
                    builder.Append(i.ToString());
                    builder.Append("); ");
                }
            }
            MainForm.dbProc.executeNonQuery(builder.ToString());
        }

        private void EditDeliveryListsForm_Resize(object sender, EventArgs e)
        {
            DataGridViewDeliveryLists.Width = Width - 341;
            DataGridViewDeliveryLists.Height = Height - 170;
            PickUp.Left = Width - 100;
            PicDown.Left = Width - 100;
        }

        private void DataGridViewDeliveryLists_ColumnAdded(object sender, DataGridViewColumnEventArgs e)
        {
            DataGridViewDeliveryLists.Columns[e.Column.Index].SortMode = DataGridViewColumnSortMode.NotSortable;
        }
    }
}
