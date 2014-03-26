using System;
using System.IO;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FTwoFramework.DB;

namespace OwlBusinessStudio.Purchase
{
    public partial class PurchaseEditForm : Form
    {
        private MainForm Owner;
        private int Status;
        private List<string> ListName, Supplier;
        private bool NewGoodLoaded = false;
        private DateTime date;
        public PurchaseEditForm(List<string> list_name, List<string> supplier, MainForm parent,int status,DateTime date)
        {
            InitializeComponent();
            ListName = list_name;
            this.date = date;
            Supplier = supplier;
            Text = "Закупки";
            Status = status;
            loadTable();
            Owner = parent;
            
            if (status == 2)
            {
                ButtAccept.Enabled = false;
                ButtDecline.Enabled = false;
                //ButtEdit.Visible = false;
            }
        }
        private void loadTable()
        {

            string command = "(SELECT DISTINCT pl.supplier,gs.articul,gs.real_article,pl.good_name,pl.good_count,pl.good_purchase_price,(pl.good_count*pl.good_purchase_price) as total_price,pl.list_name,pl.id,pl.good_id,pl.description,pl.accept_time FROM purchase_lists pl, goods gs WHERE gs.id=pl.good_id AND pl.status=" + Status + " AND"
            + " pl.list_name='" + ListName[0] + "'"
            + " AND "
            + " pl.supplier='" + Supplier[0] + "'"
            + " ORDER BY pl.list_name, pl.supplier,pl.id)";
            
            for (int i = 1; i < ListName.Count; i++)
            {
                command += " UNION ALL ";
                command += "(SELECT DISTINCT pl.supplier,gs.articul,gs.real_article,pl.good_name,pl.good_count,pl.good_purchase_price,(pl.good_count*pl.good_purchase_price) as total_price,pl.list_name,pl.id,pl.good_id,pl.description,pl.accept_time FROM purchase_lists pl, goods gs WHERE gs.id=pl.good_id AND pl.status=" + Status + " AND"
                + " pl.list_name='" + ListName[i] + "'"
                + " AND "
                + " pl.supplier='" + Supplier[i] + "'"
                + " ORDER BY pl.list_name, pl.supplier,pl.id)";
            }
            DataTable goods = MainForm.dbProc.executeGet(command);
            DataGridViewPurchase.DataSource = goods;
            formatTable();
            Configurator.translateToRussian(DataGridViewPurchase);
            int total = 0;
            for (int i = 0; i < DataGridViewPurchase.RowCount; i++)
            {
                total += (int)((double)DataGridViewPurchase.Rows[i].Cells[7].Value);
                DataGridViewCheckBoxCell cell = (DataGridViewCheckBoxCell)DataGridViewPurchase.Rows[i].Cells[0];
                cell.Value = true;
            }
            LabelTotal.Text = total.ToString();
        }

        private void ButtAccept_Click(object sender, EventArgs e)
        {
            this.ControlBox = false;
            List<string> commands = new List<string>();
            LabelPurchaseProgress.Text = "Обработка закупок...";
            ButtProcess.Visible = true;
            for (int i = 0; i < DataGridViewPurchase.RowCount; i++)
            {
                if (DataGridViewPurchase.Rows[i].Cells[0].Value != null && (bool)DataGridViewPurchase.Rows[i].Cells[0].Value == true)
                {
                    int list_id = (int)DataGridViewPurchase.Rows[i].Cells["id"].Value;
                    int good_count = (int)DataGridViewPurchase.Rows[i].Cells["good_count"].Value;
                    int good_id = (int)DataGridViewPurchase.Rows[i].Cells["good_id"].Value;
                    string c1 = " UPDATE purchase_lists SET status=2,accept_time=now() WHERE id=" + list_id.ToString() + "; "; 
                    string c2 = " UPDATE goods SET quantity = quantity + " + good_count.ToString() + " WHERE id=" + good_id.ToString() + "; ";
                    DataTable before = MainForm.dbProc.executeGet("SELECT quantity FROM goods WHERE id=" + good_id.ToString());
                    MainForm.dbProc.executeNonQuery(c1);
                    MainForm.dbProc.executeNonQuery(c2);
                    Application.DoEvents();
                    DataTable after = MainForm.dbProc.executeGet("SELECT quantity FROM goods WHERE id=" + good_id.ToString());
                    if (before.Rows.Count == 1)
                    {
                        int q_before = (int)before.Rows[0]["quantity"];
                        int q_after = (int)after.Rows[0]["quantity"];
                        MainForm.dbProc.executeNonQuery("INSERT INTO goods_history (action,good_id,quantity_before,quantity_after,description,good_action_count) VALUES('purchase_approve',"+good_id+","+q_before+","+q_after+",'"+ListName+"; "+Supplier+"',"+good_count+");");
                    }
                    Application.DoEvents();
                }
            }
            ButtProcess.Visible = false;
            LabelPurchaseProgress.Text = "Готово";
            this.ControlBox = true;
            loadTable();
        }
        private void formatTable()
        {
            if (!DataGridViewPurchase.Columns.Contains("checked"))
            {
                DataGridViewCheckBoxColumn col = new DataGridViewCheckBoxColumn();
                
                col.FalseValue = false;
                col.IndeterminateValue = false;
                col.TrueValue = true;
                col.HeaderText = ".";
                col.Name = "checked";
                DataGridViewPurchase.Columns.Insert(0, col);
            }
            for (int i = 0; i < DataGridViewPurchase.RowCount; i++)
            {
                DataGridViewCheckBoxCell cell = (DataGridViewCheckBoxCell)DataGridViewPurchase.Rows[i].Cells[0];
                cell.Value = true;
            }
            DataGridViewPurchase.Columns[0].Width = 50;
            
            DataGridViewPurchase.Columns[1].Width = 300;
            DataGridViewPurchase.Columns[2].Width = 40;
            DataGridViewPurchase.Columns[3].Width = 40;
            DataGridViewPurchase.Columns[4].Width = 620;
            DataGridViewPurchase.Columns[5].Width = 50;
            DataGridViewPurchase.Columns[6].Width = 70;
            DataGridViewPurchase.Columns[7].Width = 70;
            DataGridViewPurchase.Columns[8].Visible = false;
            DataGridViewPurchase.Columns[9].Visible = false;
            DataGridViewPurchase.Columns[10].Visible = false;
        }

        private void DataGridViewPurchase_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                if (e.ColumnIndex == 0)
                {
                    if (((DataGridView)sender).Rows[e.RowIndex].Cells[e.ColumnIndex].Value == null || (bool)((DataGridView)sender).Rows[e.RowIndex].Cells[e.ColumnIndex].Value == false)
                    {
                        ((DataGridView)sender).Rows[e.RowIndex].Cells[e.ColumnIndex].Value = true;
                    }
                    else
                    {
                        ((DataGridView)sender).Rows[e.RowIndex].Cells[e.ColumnIndex].Value = false;
                    }
                }
            }
            catch(Exception ex)
            {

            }
        }

        private bool purchaseSelect = false;
        private void DataGridViewPurchase_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            
        }

        private void DataGridViewPurchase_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            try
            {
                if (DataGridViewPurchase.SelectedCells != null & DataGridViewPurchase.SelectedCells.Count == 1)
                {
                    PanelEditPurchase.Tag = DataGridViewPurchase.SelectedCells[0].OwningRow;
                    ButtEdit.Enabled = true;
                }
                else
                {
                    ButtEdit.Enabled = false;
                }
            }
            catch
            {
                ButtEdit.Enabled = false;
            }
        }

        private void ButtEdit_Click(object sender, EventArgs e)
        {
            if (PanelEditPurchase.Tag != null)
            {
                DataGridViewRow row = (DataGridViewRow)PanelEditPurchase.Tag;
                NumPurchaseEditCount.Value = (int)row.Cells["good_count"].Value;
                TxtPurchaseEditPrice.Text = row.Cells["good_purchase_price"].Value.ToString();
                TxtPurchaseEditTotal.Text = row.Cells["total_price"].Value.ToString();
                TxtDescription.Text = row.Cells["description"].Value.ToString();
            }
            else
            {
                PanelEditPurchase.Visible = false;
            }
            PanelEditPurchase.Visible = true;
        }

        private void ButtOKPurchaseEdit_Click(object sender, EventArgs e)
        {
            DataGridViewRow row = (DataGridViewRow)PanelEditPurchase.Tag;
            string command = "UPDATE purchase_lists SET good_count = " + row.Cells["good_count"].Value.ToString()
                + ", good_purchase_price = " + row.Cells["good_purchase_price"].Value.ToString().Replace(",", ".")
                + ", description = '" + TxtDescription.Text.ToString().Replace("'", "`")+"'"
                + " WHERE id=" + row.Cells["id"].Value.ToString() + ";";
            MainForm.dbProc.executeNonQuery(command);
            PanelEditPurchase.Visible = false;
            loadTable();
        }

        private void ButtCancelPurchaseEdit_Click(object sender, EventArgs e)
        {
            PanelEditPurchase.Visible = false;
        }
        string currPurchasePrice = "0";
        private void NumPurchaseEditCount_ValueChanged(object sender, EventArgs e)
        {
            try
            {
                Double.Parse(TxtPurchaseEditPrice.Text);
                currPurchasePrice = TxtPurchaseEditPrice.Text;
            }
            catch
            {
                TxtPurchaseEditPrice.Text = currPurchasePrice;
            }
            if (PanelEditPurchase.Visible && PanelEditPurchase.Tag != null)
            {
                DataGridViewRow row = (DataGridViewRow)PanelEditPurchase.Tag;
                row.Cells["good_count"].Value = (int)NumPurchaseEditCount.Value;
                row.Cells["good_purchase_price"].Value = Double.Parse(TxtPurchaseEditPrice.Text);
                row.Cells["total_price"].Value = (int)row.Cells["good_count"].Value * (double)row.Cells["good_purchase_price"].Value;
                TxtPurchaseEditTotal.Text = row.Cells["total_price"].Value.ToString();
            }
        }

        private void ButtDecline_Click(object sender, EventArgs e)
        {
            string command = "";
            for (int i = 0; i < DataGridViewPurchase.RowCount; i++)
            {
                if (DataGridViewPurchase.Rows[i].Cells[0].Value != null && (bool)DataGridViewPurchase.Rows[i].Cells[0].Value == true)
                {
                    int list_id = (int)DataGridViewPurchase.Rows[i].Cells["id"].Value;
                    command += " DELETE FROM purchase_lists WHERE id=" + list_id.ToString() + "; ";
                    //DataGridViewPurchase.Rows.RemoveAt(i);
                }
            }
            MainForm.dbProc.executeNonQuery(command);
            loadTable();
            Owner.ComboIsForOrders_SelectedIndexChanged(null, null);
        }
        
        private void PurchaseEditForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (this.ControlBox == false)
            {
                e.Cancel = true;
            }
            Owner.ComboIsForOrders_SelectedIndexChanged(null, null);
        }

        private void PurchaseEditForm_Load(object sender, EventArgs e)
        {
            for (int i = 0; i < DataGridViewPurchase.RowCount; i++)
            {
                DataGridViewCheckBoxCell cell = (DataGridViewCheckBoxCell)DataGridViewPurchase.Rows[i].Cells[0];
                cell.Value = true;
            }
        }

        private void PurchaseEditForm_Resize(object sender, EventArgs e)
        {
            DataGridViewPurchase.Width = Width - 40;
            DataGridViewPurchase.Height = Height - 84;
        }

        private void ButtAddGood_Click(object sender, EventArgs e)
        {
            PanelAddGood.Visible = true;
            if (!NewGoodLoaded)
            {
                DataTable goods = MainForm.dbProc.get("goods", "supplier='"+Supplier[0]+"' ORDER BY name_for_order_full", "id,name_for_order_full,supplier");
                ComboGoods.DataSource = goods;
                ComboGoods.DisplayMember = "name_for_order_full";
                ComboGoods.ValueMember = "id";
                ComboGoods.AutoCompleteSource = AutoCompleteSource.ListItems;
                ComboGoods.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
                NewGoodLoaded = true;
                ComboLists.DataSource = ListName; 
            }
        }

        private void ButtCloseAddGoodPanel_Click(object sender, EventArgs e)
        {
            PanelAddGood.Visible = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (ComboGoods.SelectedValue is int)
            {
                int good_id = (int)ComboGoods.SelectedValue;
                int count = (int)NumNewGoodCount.Value;
                DataTable goods = MainForm.dbProc.get("goods", "id="+good_id.ToString(), "*");
                DataRow good = goods.Rows[0];
                string list_name = ComboLists.Text;

                Hashtable pars = new Hashtable();
                pars.Add("supplier",good["supplier"]);
                pars.Add("good_name",good["name_for_order_full"]);
                pars.Add("good_count",count);
                pars.Add("good_purchase_price",good["price_discount"]);
                pars.Add("good_id",good_id);
                pars.Add("is_for_orders", CheckForOrders.Checked);
                pars.Add("list_name",list_name);
                pars.Add("date", date);
                pars.Add("date_to", date);
                MainForm.dbProc.insert("purchase_lists", pars);
            }
            PanelAddGood.Visible = false;
            loadTable();
        }

        private void DataGridViewPurchase_ColumnHeaderMouseDoubleClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                for (int i = 0; i < ((DataGridView)sender).Rows.Count; i++)
                {
                    ((DataGridView)sender).Rows[i].Cells[e.ColumnIndex].Value = purchaseSelect;
                }
                purchaseSelect = !purchaseSelect;
            }
        }

        private void ComboGoods_SelectedValueChanged(object sender, EventArgs e)
        {
            if (ComboGoods.SelectedValue is int)
            {
                DataRowView item = (DataRowView)ComboGoods.SelectedItem;
                string supplier = item.Row["supplier"].ToString();
                TxtSupplier.Text = supplier;
            }
        }

        private void toolStripButton1_Click(object sender, EventArgs e)
        {
            if(OpenPurchaseImportDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                ButtProcess.Visible = true;
                DataTable t = ExcelProcessor.getTable(OpenPurchaseImportDialog.FileName, "Database");
                string err = "";
                int err_count = 0;
                for (int i = 0; i < t.Rows.Count; i++)
                {
                    try
                    {
                        DataRow r = t.Rows[i];
                        string articul = r[0].ToString();
                        int count = 0;
                        Int32.TryParse(r[1].ToString(), out count);

                        DataRow good = MainForm.dbProc.executeGetRow("SELECT * FROM goods WHERE articul='"+articul+"'");
                        Application.DoEvents();
                        if (good != null)
                        {
                            string list_name = ComboLists.Text;
                            double purchase_price = 0;
                            int good_id = 0;
                            if (!Double.TryParse(good["price_discount"].ToString(), out purchase_price))
                            {
                                throw new Exception();
                            }
                            if (!Int32.TryParse(good["id"].ToString(), out good_id))
                            {
                                throw new Exception();
                            }
                            Hashtable pars = new Hashtable();
                            pars.Add("supplier", good["supplier"]);
                            pars.Add("good_name", good["name_for_order_full"]);
                            pars.Add("good_count", count);
                            pars.Add("good_purchase_price", purchase_price);
                            pars.Add("good_id", good_id);
                            pars.Add("is_for_orders", CheckForOrders.Checked);
                            pars.Add("list_name", list_name);
                            pars.Add("date", date);
                            pars.Add("date_to", date);
                            MainForm.dbProc.insert("purchase_lists", pars);
                            Application.DoEvents();
                        }
                        else
                        {
                            err += i + Environment.NewLine;
                            err_count++;
                        }
                    }
                    catch (Exception ex)
                    {
                        //err += i + Environment.NewLine;
                        //err_count++;
                    }
                    ProgressLabel.Text = "Обработано: " + (i+1).ToString() + " из " + t.Rows.Count+". Ошибок: "+err_count;
                }
                loadTable();
                if (err_count != 0)
                {
                    MessageBox.Show(err, "Не удалось импортировать строки с индексами:");
                }
                ProgressLabel.Text +=". Импортирование завершено";
                ButtProcess.Visible = false;
            }
        }
    }
}
