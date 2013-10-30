using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using FTwoFramework.DB;
using System.Windows.Forms;

namespace OwlBusinessStudio.Purchase
{
    public partial class PurchaseListsForm : Form
    {
        private bool forOrders = false;
        private MainForm parent;
        public PurchaseListsForm(bool for_orders,MainForm owner)
        {
            InitializeComponent();
            parent = owner;
            forOrders = for_orders;
            if (forOrders)
            {
                Text = "Закупки под заказы";
            }
            else
            {
                Text = "Закупки на склад";
            }
            // Load suppliers
            DataTable suppliers = MainForm.dbProc.executeGet("SELECT DISTINCT supplier FROM goods WHERE supplier is not null AND supplier !=''");
            ListSuppliers.DataSource = suppliers;
            ListSuppliers.DisplayMember = "supplier";
            ListSuppliers.ValueMember = "supplier";
            ComboSuppliers.DataSource = suppliers;
            ComboSuppliers.DisplayMember = "supplier";
            ComboSuppliers.ValueMember = "supplier";
            ComboSuppliers.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            ComboSuppliers.AutoCompleteSource = AutoCompleteSource.ListItems;
            DataTable goods = null;
            if (forOrders)
            {
                goods = MainForm.dbProc.executeGet("SELECT * FROM goods_for_orders_purchase WHERE to_purchase_count>0");
            }
            else
            {
                goods = MainForm.dbProc.executeGet("SELECT * FROM goods_for_store_purchase WHERE to_purchase_count>0");
            }
            DataGridViewGoods.DataSource = goods;
            DataTable allGoods = MainForm.dbProc.executeGet("SELECT * FROM goods_list");
            ComboGoods.DataSource = allGoods;
            ComboGoods.DisplayMember = "name_for_order";
            ComboGoods.ValueMember = "id";
            ComboGoods.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            ComboGoods.AutoCompleteSource = AutoCompleteSource.ListItems;
            if (ComboGoods.Items.Count > 1)
            {
                ComboGoods.SelectedIndex = 1;
            }
            CheckAllSupliers.Checked = true;
            calculate();
            formatTable();
        }
        private void ListSuppliers_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                if (ListSuppliers.SelectedValue is string&&!CheckAllSupliers.Checked)
                {
                    String currentSupplier = (string)ListSuppliers.SelectedValue;
                    ((DataTable)DataGridViewGoods.DataSource).DefaultView.RowFilter = "supplier = '" + currentSupplier + "'";
                }
                calculate();
            }
            catch (Exception ex)
            {

            }
        }
        DataGridViewRow currentRow = null;
        private void DataGridViewGoods_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            if (e.StateChanged == DataGridViewElementStates.Selected)
            {
                currentRow = (DataGridViewRow)e.Cell.OwningRow;
                ButtDelete.Enabled = true;
            }
            else
            {
                ButtDelete.Enabled = false;
            }
        }

        private void ButtDelete_Click(object sender, EventArgs e)
        {
            try
            {
                DataGridViewGoods.Rows.RemoveAt(currentRow.Index);
                ButtDelete.Enabled = false;
                calculate();
            }
            catch (Exception ex)
            {
            }
        }

        private void ButtEdit_Click(object sender, EventArgs e)
        {

        }

        private void ButtAddOK_Click(object sender, EventArgs e)
        {
            if (ComboGoods.SelectedValue == null || !(ComboGoods.SelectedValue is int))
            {
                MessageBox.Show("Выберите товар из списка.","Ошибка заполнения");
            }
            if (DataGridViewGoods.DataSource != null)
            {
                object[] vals = new object[7];
                vals[0] = (int)NumToPurchase.Value;//to_purchase_count
                vals[1] = Double.Parse(TxtPrice.Text);
                vals[2] = Double.Parse(TxtPrice.Text) * (int)NumToPurchase.Value;
                vals[4] = ComboGoods.Text;
                vals[5] = (int)ComboGoods.SelectedValue;
                vals[6] = ComboSuppliers.Text;
                DataTable source = ((DataTable)DataGridViewGoods.DataSource);
                source.Rows.Add(vals);
                DataGridViewGoods.DataSource = source;
                PanelAddGood.Visible = false;
                calculate();
            }
        }
        private void calculate()
        {
            double total = 0;
            for (int i = 0; i < DataGridViewGoods.RowCount; i++)
            {
                double price = 0;
                if (DataGridViewGoods.Rows[i].Cells["total_purchase_price"].Value != null && !(DataGridViewGoods.Rows[i].Cells["total_purchase_price"].Value is DBNull))
                {
                    price = (double)DataGridViewGoods.Rows[i].Cells["total_purchase_price"].Value;
                }
                total += price;
            }
            TxtTotal.Text = (/*(int)*/total).ToString();
        }
        private void ButtAdd_Click(object sender, EventArgs e)
        {
            PanelAddGood.Visible = true;
        }
        private string currPrice = "0";
        private void TxtPrice_TextChanged(object sender, EventArgs e)
        {
            try
            {
                Double.Parse(TxtPrice.Text);
                currPrice = TxtPrice.Text;
            }
            catch
            {
                TxtPrice.Text = currPrice;
            }
        }

        private void toolStripButton1_Click(object sender, EventArgs e)
        {
            DataTable t = MainForm.dbProc.executeGet("SELECT nextval('purchase_list_number_seq') AS num");
            long num = (long)t.Rows[0]["num"];
            if (forOrders)
            {
                string purchase_list_name = "Закупка под заказы от " + MainForm.dbProc.getServerTime().ToShortDateString() + " " + MainForm.dbProc.getServerTime().ToShortTimeString() + " #" + num.ToString();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < DataGridViewGoods.RowCount; i++)
                {
                    builder.Append("INSERT INTO purchase_lists (is_for_orders,supplier,good_name,good_count,good_purchase_price,good_id,list_name,status,date_to,article)");
                    builder.Append(" VALUES (");
                    builder.Append("true,'");
                    builder.Append(((string)DataGridViewGoods.Rows[i].Cells["supplier"].Value).Replace("'","`"));
                    builder.Append("','");
                    builder.Append(((string)DataGridViewGoods.Rows[i].Cells["name_for_order"].Value).Replace("'", "`"));
                    builder.Append("',");
                    builder.Append(DataGridViewGoods.Rows[i].Cells["to_purchase_count"].Value.ToString().Replace(",", "."));
                    builder.Append(",");
                    builder.Append(DataGridViewGoods.Rows[i].Cells["purchase_price"].Value.ToString().Replace(",", "."));
                    builder.Append(",");
                    builder.Append(DataGridViewGoods.Rows[i].Cells["good_id"].Value);
                    builder.Append(",'");
                    builder.Append(purchase_list_name.Replace("'", "`"));
                    builder.Append("',");
                    builder.Append(1);
                    builder.Append(",");
                    builder.Append(MainForm.dbProc.getDateTimeString(TimePickerPurchaseTo.Value));
                    builder.Append(",'");
                    string art = "";
                    if (!(DataGridViewGoods.Rows[i].Cells["articul"].Value is DBNull))
                    {
                        art = ((string)DataGridViewGoods.Rows[i].Cells["articul"].Value).Replace("'", "`");
                    }
                    builder.Append(art);
                    builder.Append("'); ");
                }
                MainForm.dbProc.executeNonQuery(builder.ToString());
                MessageBox.Show("Закупочный лист под заказы сформирован.", "Результат");
                Close();
            }
            else
            {
                string purchase_list_name = "Закупка для склада от " + MainForm.dbProc.getServerTime().ToShortDateString() + " " + MainForm.dbProc.getServerTime().ToShortTimeString() + " #" + num.ToString();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < DataGridViewGoods.RowCount; i++)
                {
                    builder.Append("INSERT INTO purchase_lists (is_for_orders,supplier,good_name,good_count,good_purchase_price,good_id,list_name,status,date_to,article)");
                    builder.Append(" VALUES (");
                    builder.Append("false,'");
                    builder.Append(((string)DataGridViewGoods.Rows[i].Cells["supplier"].Value).Replace("'", "`"));
                    builder.Append("','");
                    builder.Append(((string)DataGridViewGoods.Rows[i].Cells["name_for_order"].Value).Replace("'", "`"));
                    builder.Append("',");
                    builder.Append(DataGridViewGoods.Rows[i].Cells["to_purchase_count"].Value.ToString().Replace(",", "."));
                    builder.Append(",");
                    double gpp = 0;
                    if (DataGridViewGoods.Rows[i].Cells["purchase_price"].Value != null && !(DataGridViewGoods.Rows[i].Cells["purchase_price"].Value is DBNull))
                    {
                        gpp = (double)DataGridViewGoods.Rows[i].Cells["purchase_price"].Value;
                    }
                    builder.Append(gpp.ToString().Replace(",", "."));
                    builder.Append(",");
                    builder.Append(DataGridViewGoods.Rows[i].Cells["good_id"].Value);
                    builder.Append(",'");
                    builder.Append(purchase_list_name.Replace("'", "`"));
                    builder.Append("',");
                    builder.Append(1);
                    builder.Append(",");
                    builder.Append(MainForm.dbProc.getDateTimeString(TimePickerPurchaseTo.Value));
                    builder.Append(",'");
                    string art = "";
                    if (!(DataGridViewGoods.Rows[i].Cells["articul"].Value is DBNull))
                    {
                        art = ((string)DataGridViewGoods.Rows[i].Cells["articul"].Value).Replace("'", "`");
                    }
                    builder.Append(art);
                    builder.Append("'); ");
                }
                MainForm.dbProc.executeNonQuery(builder.ToString());
                MessageBox.Show("Лакупочный лист для склада сформирован.", "Результат");
                Close();
            }
            parent.loadPurchaseTable(false);
        }

        private void ButtCancel_Click(object sender, EventArgs e)
        {
            PanelAddGood.Visible = false;
        }

        private void CheckAllSupliers_CheckedChanged(object sender, EventArgs e)
        {
            if (CheckAllSupliers.Checked)
            {
                ((DataTable)DataGridViewGoods.DataSource).DefaultView.RowFilter = "";
            }
            calculate();
        }

        private void ComboGoods_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ComboGoods.SelectedValue != null && ComboGoods.SelectedValue is int)
            {
                int good_id = (int)ComboGoods.SelectedValue;
                DataTable good = MainForm.dbProc.executeGet("SELECT price_discount, supplier FROM goods_list WHERE id=" + good_id);
                if (good.Rows.Count > 0)
                {
                    ComboSuppliers.Text = (string)good.Rows[0]["supplier"];
                    TxtPrice.Tag = (double)good.Rows[0]["price_discount"];
                    TxtPrice.Text = ((double)TxtPrice.Tag * (int)NumToPurchase.Value).ToString();
                }
            }
        }
        private void formatTable()
        {
            Configurator.translateToRussian(DataGridViewGoods);
            DataGridViewGoods.Columns[0].Width = 80;
            DataGridViewGoods.Columns[1].Width = 80;
            DataGridViewGoods.Columns[2].Width = 80;
            DataGridViewGoods.Columns[3].Width = 60;
            DataGridViewGoods.Columns[4].Width = 360;
            DataGridViewGoods.Columns[5].Width = 60;
            DataGridViewGoods.Columns[5].Visible = false;
            DataGridViewGoods.Columns[6].Width = 260;
        }
        private void NumToPurchase_ValueChanged(object sender, EventArgs e)
        {
            if (TxtPrice.Tag != null && TxtPrice.Tag is double)
            {
                TxtPrice.Text = ((double)TxtPrice.Tag * (int)NumToPurchase.Value).ToString();
            }
        }

        private void PurchaseListsForm_Resize(object sender, EventArgs e)
        {
            DataGridViewGoods.Width = Width - 262;
            DataGridViewGoods.Height = Height - 122;
            label5.Top = Height - 71;
            label5.Left = Width - 227;
            TxtTotal.Top = Height - 74;
            TxtTotal.Left = Width - 128;
        }

    }
}

