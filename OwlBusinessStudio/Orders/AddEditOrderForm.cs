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

namespace OwlBusinessStudio.Orders
{
    public partial class AddEditOrderForm : Form
    {
        private MainForm owner;
        private int orderID;
        private long clientID = 0;
        public bool isEditing;
        private OrderList orderTable;
        private int currentDiscount = 0;
        private bool needChangeStreet = false;
        private string daStreet = "";
        private bool loaded = false;

        public AddEditOrderForm(MainForm parent, int order_id)
        {
            InitializeComponent();
            owner = parent;
            isEditing = order_id != 0;
            //TxtPhone1.Enabled = !isEditing;
            orderID = order_id;
            ComboStreet.MouseWheel += new MouseEventHandler(ComboStreet_MouseWheel);
        }
        private void ComboStreet_MouseWheel(object sender, MouseEventArgs e)
        {
            ((HandledMouseEventArgs)e).Handled = true;
        }
        private void loadStaticData()
        {
            #region Static data
            DataTable metros = MainForm.dbProc.get("metros");
            ComboMetros.DataSource = metros;
            ComboMetros.DisplayMember = "name";
            ComboMetros.ValueMember = "id";
            ComboMetros.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            ComboMetros.AutoCompleteSource = AutoCompleteSource.ListItems;

            DataTable deliverWays = MainForm.dbProc.executeGet("SELECT DISTINCT ON (distance) distance,pickup,id FROM delivery_costs GROUP BY distance, pickup,id");
            ComboDelivery.DataSource = deliverWays;
            ComboDelivery.DisplayMember = "distance";
            ComboDelivery.ValueMember = "pickup";
            ComboDelivery.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            ComboDelivery.AutoCompleteSource = AutoCompleteSource.ListItems;

            #endregion
        }
        private void loadGoods(int delPrice)
        {
            
            DataTable goods = MainForm.dbProc.get("order_goods", "order_id=" + orderID.ToString());
            orderTable.needCalc = false;
            for (int i = 0; i < goods.Rows.Count; i++)
            {
                orderTable.AddItem((int)goods.Rows[i]["good_id"], (int)goods.Rows[i]["count"], (string)goods.Rows[i]["good_name"], (int)goods.Rows[i]["price"],true,true,delPrice);
            }
            orderTable.needCalc = true;
            orderTable.calc_total(true, delPrice, true);
        }
        private DataTable order;
        private void loadOrder()
        {
            order = MainForm.dbProc.get("orders", "id=" + orderID.ToString());
            if (order.Rows.Count == 0)
            {
                MessageBox.Show("Заказ более не сущестыует.", "Ошибка");
                return;
            }
            clientID = (int)order.Rows[0]["client_id"];
            bool deliver = (bool)order.Rows[0]["deliver"];
            //CheckDeliver.Checked = (bool)order.Rows[0]["deliver"];
            TxtName.Text = (string)order.Rows[0]["client_name"];
            TxtPhone1.Text = (string)order.Rows[0]["phone_1"];
            TxtPhone2.Text = (string)order.Rows[0]["phone_2"];
            TxtPhone3.Text = (string)order.Rows[0]["phone_3"];
            TimePickerDate.Value = (DateTime)order.Rows[0]["deliver_date"];
            TimePickerFrom1.Value = (DateTime)order.Rows[0]["deliver_time_from_1"];
            TimePickerTo1.Value = (DateTime)order.Rows[0]["deliver_time_to_1"];
            if (!(order.Rows[0]["deliver_time_from_2"] is DBNull))
            {
                CheckSecondDeliverTime.Checked = true;
                TimePickerFrom2.Value = (DateTime)order.Rows[0]["deliver_time_from_2"];
                TimePickerTo2.Value = (DateTime)order.Rows[0]["deliver_time_to_2"];
            }
            TxtDiscount.Text = order.Rows[0]["discount_percent"].ToString();
            orderTable.setDiscount((int)order.Rows[0]["discount_percent"],true);
            delPrice = (int)order.Rows[0]["deliver_price"];
            TxtDescription.Text = order.Rows[0]["description"].ToString();
            //ComboDelivery.Text = order.Rows[0]["deliver_distance"].ToString();
            DataTable dels = (DataTable)ComboDelivery.DataSource;
            string del_dist = order.Rows[0]["deliver_distance"].ToString();
            for (int i = 0; i < dels.Rows.Count; i++)
            {
                if (del_dist == dels.Rows[i]["distance"].ToString())
                {
                    ComboDelivery.SelectedIndex = i;
                    break;
                }
            }
            //orderTable.setDeliverPrice((int)order.Rows[0]["deliver_price"]);
            orderTable.setDeliverDistance(ComboDelivery.Text,true,true);
            orderTable.setClientId((int)clientID);
        }

        private void loadClient()
        {
            DataTable client = MainForm.dbProc.get("clients", "id=" + clientID.ToString());
            DataTable totalSumm = MainForm.dbProc.executeGet("SELECT sum(goods_price) AS total_summ FROM orders WHERE client_id=" + clientID + " AND status=3");
            long totalSummValue = 0;
            int discountForClient = 0;
            if (totalSumm.Rows.Count > 0 && !(totalSumm.Rows[0]["total_summ"] is DBNull))
            {
                totalSummValue = (long)totalSumm.Rows[0]["total_summ"];
                DataTable discounts = MainForm.dbProc.executeGet("SELECT * FROM discounts WHERE cumulative=TRUE AND order_price<" + totalSummValue + " ORDER BY discount_percent ASC LIMIT 1");
                if (discounts.Rows.Count > 0)
                {
                    discountForClient = (int)discounts.Rows[0]["discount_percent"];
                }
            }

            if (clientID != 0)// Existant client
            {
                TxtName.Text = (string)client.Rows[0]["name"];
                TxtPhone1.Text = (string)client.Rows[0]["phone_1"];
                if (!(client.Rows[0]["phone_2"] is DBNull))
                {
                    TxtPhone2.Text = (string)client.Rows[0]["phone_2"];
                }
                if (!(client.Rows[0]["phone_3"] is DBNull))
                {
                    TxtPhone3.Text = (string)client.Rows[0]["phone_3"];
                }
                if (!(client.Rows[0]["street"] is DBNull))
                {
                    ComboStreet.Text = (string)client.Rows[0]["street"];
                }
                if (!(client.Rows[0]["city"] is DBNull))
                {
                    TxtCIty.Text = (string)client.Rows[0]["city"];
                }
                if (!isEditing)
                {
                    orderTable.setDiscount(discountForClient,false);
                    TxtDiscount.Text = discountForClient.ToString();
                }

                for (int i = 0; i < ComboMetros.Items.Count; i++)
                {
                    if (client.Rows[0]["metro_id"] is DBNull)
                    {
                        break;
                    }
                    if ((int)((DataRowView)ComboMetros.Items[i]).Row["id"] == (int)client.Rows[0]["metro_id"])
                    {
                        ComboMetros.SelectedIndex = i;
                        break;
                    }

                }
                // Distance...
                try
                {
                    for (int i = 0; i < ComboDelivery.Items.Count; i++)
                    {
                        if (!(client.Rows[0]["distance"] is DBNull))
                        {
                            if ((string)((DataRowView)ComboDelivery.Items[i]).Row["distance"] == (string)client.Rows[0]["distance"])
                            {
                                ComboDelivery.SelectedIndex = i;
                                break;
                            }
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.ToString());
                }
                TxtHouse.Text = (string)client.Rows[0]["house"];
                TxtPorch.Text = (string)client.Rows[0]["porch"];
                TxtFloor.Text = (string)client.Rows[0]["floor"];
                TxtDomophone.Text = (string)client.Rows[0]["domophone"];
                TxtRoom.Text = (string)client.Rows[0]["room"];
            }
            else// New client
            {
                ComboStreet.SelectedIndex = -1;
                TxtCIty.Text = "";
                if (!isEditing)
                {
                    TxtDiscount.Text = "0";
                }

                //ComboStreetType.Text = "";
                ComboMetros.SelectedIndex = -1;

                TxtHouse.Text = "";
                TxtPorch.Text = "";
                TxtFloor.Text = "";
                TxtDomophone.Text = "";
                TxtRoom.Text = "";
            }
        }
        private void clearClient()
        {
            TxtName.Text = "";
            TxtPhone2.Text = "";
            TxtPhone3.Text = "";
            TxtDiscount.Text = "";
            ComboStreet.Text = "";
            TxtDiscount.Text = "";
            ComboMetros.SelectedIndex = 0;
            TxtHouse.Text = "";
            TxtPorch.Text = "";
            TxtFloor.Text = "";
            TxtDomophone.Text = "";
            TxtRoom.Text = "";
            //orderTable.setDiscount(0);
            TxtDiscount.Text = "0";
        }

        private void AddEditOrderForm_Load(object sender, EventArgs e)
        {
            OrderList orderList = new OrderList(orderID);
            GroupBoxGoods.Controls.Add(orderList);
            orderList.Top = 15;
            orderList.Left = 10;
            orderTable = orderList;
            orderTable.onlistItemAdded += new OrderList.listItemAddedHandler(orderTable_onlistItemAdded);
            orderTable.onListSumChanged += new OrderList.sumListChangedHandler(orderTable_onListSumChanged);
            loadStaticData();
        }

        void orderTable_onListSumChanged()
        {
            TxtDiscount.Text = orderTable.getDiscount().ToString();
        }
        private int delPrice = 0;
        private void loadClientAddress()
        {
            DataTable client = MainForm.dbProc.get("clients", "id=" + clientID.ToString());
            DataTable totalSumm = MainForm.dbProc.executeGet("SELECT sum(goods_price) AS total_summ FROM orders WHERE client_id=" + clientID + " AND status=3");
            long totalSummValue = 0;
            int discountForClient = 0;
            if (totalSumm.Rows.Count > 0 && !(totalSumm.Rows[0]["total_summ"] is DBNull))
            {
                totalSummValue = (long)totalSumm.Rows[0]["total_summ"];
                DataTable discounts = MainForm.dbProc.executeGet("SELECT * FROM discounts WHERE order_price<" + totalSummValue + " AND cumulative=TRUE ORDER BY discount_percent DESC LIMIT 1");
                if (discounts.Rows.Count > 0)
                {
                    discountForClient = (int)discounts.Rows[0]["discount_percent"];
                }
            }

            if (clientID != 0)// Existant client
            {
                TxtName.Text = (string)client.Rows[0]["name"];
                TxtPhone1.Text = (string)order.Rows[0]["phone_1"];
                if (!(order.Rows[0]["phone_2"] is DBNull))
                {
                    TxtPhone2.Text = (string)order.Rows[0]["phone_2"];
                }
                if (!(order.Rows[0]["phone_3"] is DBNull))
                {
                    TxtPhone3.Text = (string)order.Rows[0]["phone_3"];
                }
                if (!(order.Rows[0]["street"] is DBNull))
                {
                    ComboStreet.Text = (string)order.Rows[0]["street"];
                }
                if (!(order.Rows[0]["city"] is DBNull))
                {
                    TxtCIty.Text = (string)order.Rows[0]["city"];
                }
                if (!isEditing)
                {
                    orderTable.setDiscount(discountForClient,false);
                    TxtDiscount.Text = discountForClient.ToString();
                }
                if (!(order.Rows[0]["house"] is DBNull))
                {
                    TxtHouse.Text = (string)order.Rows[0]["house"];
                }
                if (!(order.Rows[0]["porch"] is DBNull))
                {
                    TxtPorch.Text = (string)order.Rows[0]["porch"];
                }
                if (!(order.Rows[0]["floor"] is DBNull))
                {
                    TxtFloor.Text = (string)order.Rows[0]["floor"];
                }
                if (!(order.Rows[0]["domophone"] is DBNull))
                {
                    TxtDomophone.Text = (string)order.Rows[0]["domophone"];
                }
                if (!(order.Rows[0]["room"] is DBNull))
                {
                    TxtRoom.Text = (string)order.Rows[0]["room"];
                }
                
                for (int i = 0; i < ComboMetros.Items.Count; i++)
                {
                    if (order.Rows[0]["metro_id"] is DBNull)
                    {
                        break;
                    }
                    if ((int)((DataRowView)ComboMetros.Items[i]).Row["id"] == (int)order.Rows[0]["metro_id"])
                    {
                        ComboMetros.SelectedIndex = i;
                        break;
                    }
                }
                // Distance...
                try
                {
                    for (int i = 0; i < ComboDelivery.Items.Count; i++)
                    {
                        if (!(order.Rows[0]["deliver_distance"] is DBNull))
                        {
                            if ((string)((DataRowView)ComboDelivery.Items[i]).Row["distance"] == (string)order.Rows[0]["deliver_distance"])
                            {
                                ComboDelivery.SelectedIndex = i;
                                break;
                            }
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.ToString());
                }
                
            }
            else// New client
            {
                ComboStreet.SelectedIndex = -1;
                TxtCIty.Text = "";
                if (!isEditing)
                {
                    TxtDiscount.Text = "0";
                }

                //ComboStreetType.Text = "";
                ComboMetros.SelectedIndex = -1;

                TxtHouse.Text = "";
                TxtPorch.Text = "";
                TxtFloor.Text = "";
                TxtDomophone.Text = "";
                TxtRoom.Text = "";
            }
        }
        private void AddEditOrderForm_Shown(object sender, EventArgs e)
        {
            if (isEditing)// Edit order
            {
                loadOrder();
                loadClientAddress();
                loadGoods(delPrice);
            }
            else
            {
                clientID = 0;
                clearClient();
            }

            this.ComboDelivery.SelectedValueChanged += new System.EventHandler(this.TxtDeliver_SelectedValueChanged);
            this.TxtDiscount.TextChanged += new System.EventHandler(this.TxtDiscount_TextChanged);
            orderTable.canRecalculate = true;
            if (!isEditing)
            {
                ComboMetros.Text = "";
                ComboMetros.SelectedIndex = -1;
                TimePickerDate.Value = MainForm.dbProc.getServerTime().AddDays(1);
            }
            else
            {
                orderTable_onlistItemAdded();
            }
            loaded = true;
        }

        private void orderTable_onlistItemAdded()
        {
            int he = orderTable.Height;
            GroupBoxGoods.Height = he + 20;
        }
        private void AddEditOrderForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            owner.SetEnabled(true);
        }
        private void TxtDiscount_TextChanged(object sender, EventArgs e)
        {
            try
            {
                if (TxtDiscount.Text == "")
                {
                    TxtDiscount.Text = "0";
                }
                int disc = Int32.Parse(TxtDiscount.Text);
                if (disc > 99 || disc < 0)
                {
                    TxtDiscount.Text = currentDiscount.ToString();
                    return;
                }
                currentDiscount = disc;
            }
            catch
            {
                TxtDiscount.Text = currentDiscount.ToString();
            }
            orderTable.setDiscount(currentDiscount,true);
        }
        private void CheckDeliver_CheckedChanged(object sender, EventArgs e)
        {
            //ComboDelivery.Enabled = CheckDeliver.Checked;
            //if (!ComboDelivery.Enabled)
            //{
            //    orderTable.setDeliverPriceID(0);
            //    LabelDopDiscount.Text = "3";
            //    LabelDopDiscount.Visible = true;
            //    LabelPlus.Visible = true;

            //    TxtCIty.Enabled = false;
            //    TxtCIty.Text = "";
            //    ComboStreet.Enabled = false;
            //    ComboStreet.SelectedIndex = -1;
            //    ComboStreet.Text = "";
            //    ComboMetros.Enabled = false;
            //    ComboMetros.SelectedIndex = -1;
            //    TxtHouse.Enabled = false;
            //    TxtHouse.Text = "";
            //    TxtPorch.Enabled = false;
            //    TxtPorch.Text = "";
            //    TxtFloor.Enabled = false;
            //    TxtFloor.Text = "";
            //    TxtRoom.Enabled = false;
            //    TxtRoom.Text = "";
            //    TxtDomophone.Enabled = false;
            //    TxtDomophone.Text = "";
            //    //clientID = 0;
            //    LabelDeliver.Text = "Самовывоз";
            //}
            //else
            //{
            //    ComboDelivery.SelectedIndex = 0;
            //    TxtDeliver_SelectedValueChanged(null, null);
            //    LabelDopDiscount.Text = "0";
            //    LabelDopDiscount.Visible = false;
            //    LabelPlus.Visible = false;

            //    TxtCIty.Enabled = true;
            //    ComboStreet.Enabled = true;
            //    ComboMetros.Enabled = true;
            //    TxtHouse.Enabled = true;
            //    TxtPorch.Enabled = true;
            //    TxtFloor.Enabled = true;
            //    TxtRoom.Enabled = true;
            //    TxtDomophone.Enabled = true;
            //    LabelDeliver.Text = "Доставка";
            //}
        }
        private bool checkFilling()
        {
            if (TxtName.Text.Trim().Length < 2)
            {
                MessageBox.Show("Имя должно быть заполнено и содержать не менее 2х символов.");
                return false;
            }
            if (TxtPhone1.Text.Trim().Length != 14)
            {
                MessageBox.Show("Телефонный номер 1 должен содержать не менее 10 чисел.");
                return false;
            }
            if (TxtPhone2.Text.Replace(" ", "").Length != 4 && TxtPhone2.Text.Replace(" ", "").Length != 14)
            {
                MessageBox.Show("Телефонный номер 2 должен содержать не менее 10 чисел или быть пустым.");
                return false;
            }
            if (TxtPhone3.Text.Replace(" ", "").Length != 4 && TxtPhone3.Text.Replace(" ", "").Length != 14)
            {
                MessageBox.Show("Телефонный номер 3 должен содержать не менее 10 чисел или быть пустым.");
                return false;
            }
            if (orderTable.Items.Count <= 0)
            {
                MessageBox.Show("Заказ должен содержать хотябы одно наименование товара.");
                return false;
            }
            if (TxtDomophone.Enabled && ComboMetros.SelectedValue == null&&(ComboDelivery.SelectedValue is bool&&!(bool)ComboDelivery.SelectedValue))
            {
                MessageBox.Show("Укажите метро.");
                return false;
            }
            if (TimePickerDate.Value.Year < MainForm.dbProc.getServerTime().Year || (TimePickerDate.Value.DayOfYear < MainForm.dbProc.getServerTime().DayOfYear && TimePickerDate.Value.Year <= MainForm.dbProc.getServerTime().Year))
            {
                if (MainForm.currentUser.Role != Roles.Admin)
                {
                    MessageBox.Show("Нельзя указывать уже прошедшую дату.");
                    return false;
                }
            }
            return true;
        }
        private void changeCompletedOrder(bool plus)
        {
            DataTable ordersToAccept = MainForm.dbProc.executeGet("SELECT * FROM orders_with_details WHERE status_id=3 AND id=" + orderID.ToString());
            List<string> UpdateQuantities = new List<string>();
            for (int i = 0; i < ordersToAccept.Rows.Count; i++)
            {
                int count = (int)ordersToAccept.Rows[i]["count"];
                int good_id = (int)ordersToAccept.Rows[i]["good_id"];
                int order_id = (int)ordersToAccept.Rows[i]["id"];

                string command = "UPDATE goods SET quantity=quantity";
                if (plus)
                {
                    command += "+";
                }
                else
                {
                    command += "-";
                }
                command += "("
                + count.ToString()
                + ") WHERE id="
                + good_id.ToString()
                + "; ";
                MainForm.dbProc.executeNonQuery(command);
            }
        }
        private void ButtSaveOrder_Click(object sender, EventArgs e)
        {
            try
            {
                if (!checkFilling())
                {
                    return;
                }
                #region client
                int current_metro_id = 0;
                if (TxtDomophone.Enabled)
                {
                    current_metro_id = (int)ComboMetros.SelectedValue;
                }
                Hashtable clientParams = new Hashtable();
                clientParams.Add("name", TxtName.Text);
                
                clientParams.Add("phone_1", TxtPhone1.Text);
                if (TxtPhone2.Text.Replace(" ", "").Length == 14)
                {
                    clientParams.Add("phone_2", TxtPhone2.Text);
                }
                else
                {
                    clientParams.Add("phone_2", "");
                }
                if (TxtPhone3.Text.Replace(" ", "").Length == 14)
                {
                    clientParams.Add("phone_3", TxtPhone3.Text);
                }
                else
                {
                    clientParams.Add("phone_3", "");
                }
                clientParams.Add("distance", ComboDelivery.Text);
                if (TxtDomophone.Enabled||true)
                {
                    clientParams.Add("street", Configurator.fromUpper(ComboStreet.Text.TrimStart(new char[1] { ' ' }).TrimEnd(new char[1] { ' ' })));
                    clientParams.Add("street_type", "");
                    clientParams.Add("house", TxtHouse.Text);
                    clientParams.Add("porch", TxtPorch.Text);
                    clientParams.Add("floor", TxtFloor.Text);
                    clientParams.Add("room", TxtRoom.Text);
                    clientParams.Add("domophone", TxtDomophone.Text);
                    if (ComboMetros.SelectedValue != null)
                    {
                        clientParams.Add("metro_id", ((int)ComboMetros.SelectedValue));
                    }
                    else
                    {
                        clientParams.Add("metro_id", 0);
                    }
                    clientParams.Add("city", Configurator.fromUpper(TxtCIty.Text));

                    if (clientID == 0)// New client
                    {
                        clientID = MainForm.dbProc.insert("clients", clientParams);
                    }
                    else// Edit client
                    {
                        MainForm.dbProc.update("clients", clientParams, "id=" + clientID.ToString());
                    }
                }
                                
                #endregion

                if (isEditing)// Edit order
                {
                    #region order
                    Hashtable orderParams = new Hashtable();

                    DataTable userTab = MainForm.dbProc.executeGet("SELECT user_id FROM orders WHERE id=" + orderID);
                    
                    //if (userTab == null || userTab.Rows.Count == 1)
                    //{
                    //    orderParams.Add("user_id", MainForm.currentUser.ID);    
                    //}
                    
                    orderParams.Add("client_id", clientID);
                    if (ComboDelivery.SelectedValue != null)
                    {
                        orderParams.Add("deliver", !(bool)ComboDelivery.SelectedValue);
                        if ((bool)ComboDelivery.SelectedValue)
                        {
                            orderParams.Add("street", ComboDelivery.Text);
                            orderParams.Add("street_type", "");
                            orderParams.Add("house", "");
                            orderParams.Add("porch", "");
                            orderParams.Add("floor", "");
                            orderParams.Add("room", "");
                        }
                        else
                        {
                            orderParams.Add("street", Configurator.fromUpper(ComboStreet.Text));
                            orderParams.Add("street_type", "");
                            orderParams.Add("house", TxtHouse.Text);
                            orderParams.Add("porch", TxtPorch.Text);
                            orderParams.Add("floor", TxtFloor.Text);
                            orderParams.Add("room", TxtRoom.Text);
                        }
                    }
                    orderParams.Add("deliver_date", TimePickerDate.Value);
                    orderParams.Add("deliver_time_from_1", TimePickerFrom1.Value);
                    if (CheckSecondDeliverTime.Checked)
                    {
                        orderParams.Add("deliver_time_from_2", TimePickerFrom2.Value);
                        orderParams.Add("deliver_time_to_2", TimePickerTo2.Value);
                    }
                    else
                    {
                        orderParams.Add("deliver_time_from_2", null);
                        orderParams.Add("deliver_time_to_2", null);
                    }
                    orderParams.Add("deliver_time_to_1", TimePickerTo1.Value);
                    orderParams.Add("modifier_user_id", MainForm.currentUser.ID);
                    orderParams.Add("deliver_price", orderTable.getDeliverPrice());
                    orderParams.Add("goods_price", orderTable.getGoodsPrice());
                    orderParams.Add("phone_1", TxtPhone1.Text);
                    if (TxtPhone2.Text.Replace(" ", "").Length == 14)
                    {
                        orderParams.Add("phone_2", TxtPhone2.Text);
                    }
                    else
                    {
                        orderParams.Add("phone_2", "");
                    }
                    if (TxtPhone3.Text.Replace(" ", "").Length == 14)
                    {
                        orderParams.Add("phone_3", TxtPhone3.Text);
                    }
                    else
                    {
                        orderParams.Add("phone_3", "");
                    }
                    
                    orderParams.Add("modified", MainForm.dbProc.getServerTime());
                    orderParams.Add("modifier_name", MainForm.currentUser.SecondName+" "+MainForm.currentUser.FirstName);
                    orderParams.Add("domophone", TxtDomophone.Text);
                    orderParams.Add("discount_percent", Int32.Parse(TxtDiscount.Text));
                    orderParams.Add("metro_id", current_metro_id);
                    orderParams.Add("city", Configurator.fromUpper(TxtCIty.Text));
                    orderParams.Add("deliver_distance", ComboDelivery.Text);
                    orderParams.Add("description", TxtDescription.Text);
                    orderParams.Add("client_name", TxtName.Text);

                    DataTable driver = MainForm.dbProc.executeGet("SELECT driver_id FROM driver_metros WHERE metro_id=" + current_metro_id.ToString());
                    if (driver.Rows.Count > 0 && !(bool)ComboDelivery.SelectedValue)
                    {
                        orderParams.Add("driver_id", driver.Rows[0]["driver_id"]);
                    }
                    else
                    {
                        orderParams.Add("driver_id", 0);
                    }
                    
                    // Here we change store goods...
                    changeCompletedOrder(true);

                    MainForm.dbProc.update("orders", orderParams, "id=" + orderID.ToString());
                    Hashtable historyParams = new Hashtable();
                    historyParams.Add("order_id", orderID);
                    historyParams.Add("action", "Редактирование");
                    //historyParams.Add("user_id", MainForm.currentUser.ID);
                    //MainForm.dbProc.insert("history", historyParams);
                    MainForm.dbProc.delete("order_goods", "order_id=" + orderID.ToString());
                    for (int i = 0; i < orderTable.Items.Count; i++)// Goods for order
                    {
                        Hashtable orderGoodParams = new Hashtable();
                        OrderItem item = orderTable.Items[i];
                        orderGoodParams.Add("order_id", orderID);
                        orderGoodParams.Add("good_id", item.getGoodID());
                        orderGoodParams.Add("price", item.getPrice());
                        orderGoodParams.Add("count", item.getQuantity());
                        orderGoodParams.Add("good_name", item.ComboGoods.Text);
                        MainForm.dbProc.insert("order_goods", orderGoodParams);
                    }
                    changeCompletedOrder(false);
                    #endregion
                }
                else// New order
                {
                    #region order
                    Hashtable orderParams = new Hashtable();
                    orderParams.Add("user_id", MainForm.currentUser.ID);
                    orderParams.Add("client_id", clientID);
                    if (ComboDelivery.SelectedValue != null)
                    {
                        orderParams.Add("deliver", !(bool)ComboDelivery.SelectedValue);
                        if ((bool)ComboDelivery.SelectedValue)
                        {
                            orderParams.Add("street", ComboDelivery.Text);
                            orderParams.Add("street_type", "");
                            orderParams.Add("house", "");
                            orderParams.Add("porch", "");
                            orderParams.Add("floor", "");
                            orderParams.Add("room", "");
                            orderParams.Add("domophone", "");
                            orderParams.Add("metro_id", 0);
                        }
                        else
                        {
                            orderParams.Add("street", Configurator.fromUpper(ComboStreet.Text));
                            orderParams.Add("street_type", "");
                            orderParams.Add("house", TxtHouse.Text);
                            orderParams.Add("porch", TxtPorch.Text);
                            orderParams.Add("floor", TxtFloor.Text);
                            orderParams.Add("room", TxtRoom.Text);
                            orderParams.Add("domophone", TxtDomophone.Text);
                            orderParams.Add("metro_id", current_metro_id);
                        }
                    }
                    orderParams.Add("deliver_date", TimePickerDate.Value);
                    orderParams.Add("deliver_time_from_1", TimePickerFrom1.Value);
                    if (CheckSecondDeliverTime.Checked)
                    {
                        orderParams.Add("deliver_time_from_2", TimePickerFrom2.Value);
                        orderParams.Add("deliver_time_to_2", TimePickerTo2.Value);
                    }
                    else
                    {
                        orderParams.Add("deliver_time_from_2", null);
                        orderParams.Add("deliver_time_to_2", null);
                    }
                    orderParams.Add("deliver_time_to_1", TimePickerTo1.Value);
                    orderParams.Add("modifier_name", MainForm.currentUser.SecondName + " " + MainForm.currentUser.FirstName);
                    orderParams.Add("modifier_user_id", MainForm.currentUser.ID);
                    orderParams.Add("deliver_price", orderTable.getDeliverPrice());
                    orderParams.Add("goods_price", orderTable.getGoodsPrice());
                    orderParams.Add("phone_1", TxtPhone1.Text);
                    if (TxtPhone2.Text.Replace(" ", "").Length == 14)
                    {
                        orderParams.Add("phone_2", TxtPhone2.Text);
                    }
                    else
                    {
                        orderParams.Add("phone_2", "");
                    }
                    if (TxtPhone3.Text.Replace(" ", "").Length == 14)
                    {
                        orderParams.Add("phone_3", TxtPhone3.Text);
                    }
                    else
                    {
                        orderParams.Add("phone_3", "");
                    }
                    orderParams.Add("discount_percent", Double.Parse(TxtDiscount.Text));
                    
                    orderParams.Add("city", Configurator.fromUpper(TxtCIty.Text));
                    orderParams.Add("deliver_distance", ComboDelivery.Text);
                    orderParams.Add("description", TxtDescription.Text);
                    orderParams.Add("client_name", TxtName.Text);

                    DataTable driver = MainForm.dbProc.executeGet("SELECT driver_id FROM driver_metros WHERE metro_id=" + current_metro_id.ToString());
                    if (driver.Rows.Count > 0 && !(bool)ComboDelivery.SelectedValue)
                    {
                        orderParams.Add("driver_id", driver.Rows[0]["driver_id"]);
                    }
                    else
                    {
                        orderParams.Add("driver_id", 0);
                    }
                    long order_id = MainForm.dbProc.insert("orders", orderParams);
                    Hashtable historyParams = new Hashtable();
                    historyParams.Add("user_id",MainForm.currentUser.ID);
                    historyParams.Add("order_id",order_id);
                    historyParams.Add("action","Создание");
                    MainForm.dbProc.insert("history", historyParams);

                    for (int i = 0; i < orderTable.Items.Count; i++)
                    {
                        Hashtable orderGoodParams = new Hashtable();
                        OrderItem item = orderTable.Items[i];
                        orderGoodParams.Add("order_id", order_id);
                        orderGoodParams.Add("good_id", item.getGoodID());
                        orderGoodParams.Add("price", item.getPrice());
                        orderGoodParams.Add("count", item.getQuantity());
                        orderGoodParams.Add("good_name", item.ComboGoods.Text);
                        MainForm.dbProc.insert("order_goods", orderGoodParams);
                    }
                    #endregion
                }

                Close();
                owner.TabControlMain_SelectedIndexChanged(null, null);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }
        private void TxtDeliver_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                orderTable.setDeliverDistance(ComboDelivery.Text,false,false);
                if(ComboDelivery.SelectedValue is bool)
                {
                    bool pickup = (bool)ComboDelivery.SelectedValue;
                    disableAddressInput(pickup);
                }
            }
            catch (Exception ex)
            {

            }
        }
        private void disableAddressInput(bool disable)
        {
            TxtCIty.Enabled = !disable;
            ComboStreet.Enabled = !disable;
            ComboMetros.Enabled = !disable;
            TxtHouse.Enabled = !disable;
            TxtFloor.Enabled = !disable;
            TxtPorch.Enabled = !disable;
            TxtRoom.Enabled = !disable;
            TxtDomophone.Enabled = !disable;
        }
        private void CheckSecondDeliverTime_CheckedChanged(object sender, EventArgs e)
        {
            label13.Visible = CheckSecondDeliverTime.Checked;
            label14.Visible = CheckSecondDeliverTime.Checked;
            label15.Visible = CheckSecondDeliverTime.Checked;
            TimePickerFrom2.Visible = CheckSecondDeliverTime.Checked;
            TimePickerTo2.Visible = CheckSecondDeliverTime.Checked;
        }
        private void GroupBoxGoods_Resize(object sender, EventArgs e)
        {
            TxtDescription.Top = GroupBoxGoods.Height + 230;
            label21.Top = GroupBoxGoods.Height + 230;
            ButtSaveOrder.Top = GroupBoxGoods.Height + 308;
            Height = GroupBoxGoods.Height + 381;
        }
        private void TxtPhone1_TextChanged(object sender, EventArgs e)
        {
            if (TxtPhone1.Text.Length == 14 && loaded)
            {
                // Search client, load it...
                DataTable clients_by_phone = MainForm.dbProc.executeGet("SELECT id FROM clients WHERE phone_1='" + TxtPhone1.Text + "'");
                if (clients_by_phone.Rows.Count == 0)
                {
                    MessageBox.Show("Новый клиент");
                }
                else
                {
                    clientID = (int)clients_by_phone.Rows[0]["id"];
                    orderTable.setClientId((int)clientID);
                    loadClient();
                }
            }
        }
        private bool needSearchForStreet = true;
        private void ComboStreet_TextChanged(object sender, EventArgs e)
        {
            if (ComboStreet.Text.Length == 3 && needSearchForStreet)
            {
                // Find streets...
                string text = ComboStreet.Text;
                DataTable streets = MainForm.dbProc.executeGet("SELECT * FROM streets WHERE name LIKE '" + text + "%'");
                ComboStreet.DataSource = streets;
                ComboStreet.DisplayMember = "name";
                ComboStreet.ValueMember = "id";
                ComboStreet.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
                ComboStreet.AutoCompleteSource = AutoCompleteSource.ListItems;
                needSearchForStreet = false;
                ComboStreet.Text = text;
                ComboStreet.Select(ComboStreet.Text.Length, 0);
                needSearchForStreet = true;
            }
        }
        private void NumDeliveryPrice_ValueChanged(object sender, EventArgs e)
        {
            //orderTable.setDeliverPrice((int)NumDeliveryPrice.Value);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            TxtDeliver_SelectedValueChanged(null, null);
        }
    }
}
