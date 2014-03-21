using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Orders
{
    public partial class OrderList : UserControl
    {
        public delegate void sumListChangedHandler();
        public event sumListChangedHandler onListSumChanged;
        public delegate void listItemAddedHandler();
        public event listItemAddedHandler onlistItemAdded;

        private bool deliverPriceSet = false;
        public List<OrderItem> Items = new List<OrderItem>();
        public List<PictureBox> DeletePics = new List<PictureBox>();
        public int Discount = 0;
        //public int DeliverPrice = 0;
        public string distance = "";
        private int orderID = 0;
        private int clientID = 0;
        public bool isPickup = false;
        public DataTable goods;

        public void loadGoods()
        {
            try
            {
                //goods = MainForm.dbProc.executeGet("(SELECT id,name_for_order AS name_for_order,quantity,minimum,price FROM goods WHERE weight is null UNION ALL "
                //    + "SELECT id,name_for_order||' '||weight||' кг.' AS name_for_order,quantity,minimum,price FROM goods WHERE weight is not null) ORDER BY id");
                //ComboGoods.DataSource = goods;
                //ComboGoods.DisplayMember = "name_for_order";
                //ComboGoods.ValueMember = "id";
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }
        public OrderList(int order_id)
        {
            InitializeComponent();
            loadGoods();
            orderID = order_id;
        }
        public int getDiscount()
        {
            return Discount;
        }
        private void OrderList_Load(object sender, EventArgs e)
        {

        }
        public void setDiscount(int discount,bool useDelPrice)
        {
            Discount = discount;
            firstItem_onSumChanged(true, 0, useDelPrice);
        }
        public void setDeliverDistance(string distance,bool useDelPrice,bool useDiscount)
        {
            this.distance = distance;
            firstItem_onSumChanged(useDiscount,0,useDelPrice);
        }
        public void setClientId(int id)
        {
            clientID = id;
        }
        public void calc_total(bool useGivenDiscount,Int32 delPrice,bool useDelPrice)
        {
            if (needCalc)
            {
                int goodsPrice = 0;
                int totalDiscount = 0;
                int deliveryPrice = 0;
                int priceWithDiscount = 0;

                // 1 - Calculate goods price
                for (int i = 0; i < Items.Count; i++)
                {
                    goodsPrice += (int)(Items[i].getSum());
                }
                // 2 - Calculate delivery price
                DataTable deliveryPriceTab = MainForm.dbProc.executeGet("SELECT * FROM get_delivery_price('" + distance + "',CAST(" + goodsPrice + " AS DOUBLE PRECISION))");
                deliveryPrice = (int)((double)deliveryPriceTab.Rows[0]["delivery_price"]);
                if (useDelPrice)
                {
                    deliveryPrice = delPrice;
                }
                isPickup = (bool)deliveryPriceTab.Rows[0]["is_pickup"];
                int pickupDiscount = 0;
                if (!(deliveryPriceTab.Rows[0]["discount"] is DBNull))
                {
                    pickupDiscount += (int)deliveryPriceTab.Rows[0]["discount"];
                }

                // 3 - Calculate total discount and price with discount
                DataTable discountTab = MainForm.dbProc.executeGet("SELECT * FROM get_discount(CAST(" + goodsPrice + " AS DOUBLE PRECISION)," + clientID + "," + pickupDiscount + ")");
                if (!(discountTab.Rows[0]["total_discount"] is DBNull))
                {
                    totalDiscount = (int)((double)discountTab.Rows[0]["total_discount"]);
                }
                if (useGivenDiscount)
                {
                    totalDiscount = Discount;
                }
                priceWithDiscount = goodsPrice - (int)(goodsPrice * ((totalDiscount) / 100.0));

                // 4 - Fill controll with data
                TxtPrice.Text = goodsPrice.ToString();
                TxtDeliveryPrice.Text = deliveryPrice.ToString();
                TxtTotalPrice.Text = (deliveryPrice + priceWithDiscount).ToString();
                Discount = totalDiscount;
                if (onListSumChanged != null)
                {
                    onListSumChanged();
                }
            }
        }
        void firstItem_onSumChanged(bool useGivenDiscount,Int32 delPrice,bool useDelPrice)
        {
            calc_total(useGivenDiscount, delPrice,useDelPrice);
            
        }
        public bool canRecalculate = false;
        #region UI
        public bool needCalc = true;
        public void AddItem(int good_id, int quantity, string goodName, int goodPrice,bool useDelPrice,bool useGivenDiscount,int delPrice)
        {
            // Add control...
            OrderItem item = new OrderItem(this, orderID);
            //item.loadItem(good_id, quantity,goodName,goodPrice);
            item.Top = Items.Count * 27 + 15;
            item.Left = 0;
            Items.Add(item);
            Controls.Add(item);
            Height = Items.Count * 27 + 65;
            item.onSumChanged += new OrderItem.sumChangedHandler(firstItem_onSumChanged);

            PictureBox delBox = new PictureBox();
            delBox.Cursor = System.Windows.Forms.Cursors.Hand;
            delBox.Size = new Size(24, 24);
            delBox.Top = (Items.Count - 1) * 27 + 15;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(OrderList));
            delBox.Image = ((System.Drawing.Image)(resources.GetObject("PicDelete.Image")));
            delBox.Left = 895;
            delBox.Tag = item;
            Controls.Add(delBox);
            DeletePics.Add(delBox);
            delBox.Click += new EventHandler(delBox_Click);
            ButtAddGood.Top = 15 + 27 * Items.Count;

            label3.Top = ButtAddGood.Top + 7;
            label4.Top = ButtAddGood.Top + 7;
            LabelItogo.Top = ButtAddGood.Top + 7;
            TxtPrice.Top = ButtAddGood.Top + 25;
            TxtDeliveryPrice.Top = ButtAddGood.Top + 25;
            TxtTotalPrice.Top = ButtAddGood.Top + 25;

            item.loadItem(good_id, quantity, goodName, goodPrice);
            calc_total(useGivenDiscount, delPrice, useDelPrice);
            item.Tag = delBox;
        }
        public OrderItem AddItem()
        {
            // Add control...
            OrderItem item = new OrderItem(this, orderID);
            item.Top = Items.Count * 27 + 15;
            item.Left = 0;
            Items.Add(item);
            Controls.Add(item);
            Height = Items.Count * 27 + 65;
            item.onSumChanged += new OrderItem.sumChangedHandler(firstItem_onSumChanged);

            PictureBox delBox = new PictureBox();
            delBox.Cursor = System.Windows.Forms.Cursors.Hand;
            delBox.Size = new Size(24, 24);
            delBox.Top = (Items.Count - 1) * 27 + 15;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(OrderList));
            delBox.Image = ((System.Drawing.Image)(resources.GetObject("PicDelete.Image")));
            delBox.Left = 895;
            delBox.Tag = item;
            Controls.Add(delBox);
            DeletePics.Add(delBox);
            delBox.Click += new EventHandler(delBox_Click);
            ButtAddGood.Top = 15 + 27 * Items.Count;

            label3.Top = ButtAddGood.Top + 7;
            label4.Top = ButtAddGood.Top + 7;
            LabelItogo.Top = ButtAddGood.Top + 7;
            TxtPrice.Top = ButtAddGood.Top + 25;
            TxtDeliveryPrice.Top = ButtAddGood.Top + 25;
            TxtTotalPrice.Top = ButtAddGood.Top + 25;
            item.loadItem();
            calc_total(false,0,false);
            item.Tag = delBox;
            return item;
        }
        public int getDeliverPrice()
        {
            return (Int32.Parse(TxtDeliveryPrice.Text));
        }
        public int getGoodsPrice()
        {
            return (Int32.Parse(TxtPrice.Text));
        }
        private void ButtAddGood_Click(object sender, EventArgs e)
        {
            OrderItem item = AddItem();
            //calc_total(false, 0, false);
            Items[Items.Count - 1].PicFind_Click(null, null);
            if (onlistItemAdded != null)
            {
                onlistItemAdded();
            }
        }
        private void rebuildeList()
        {
            for (int i = 0; i < Items.Count; i++)
            {
                Items[i].Top = i * 27 + 15;
                DeletePics[i].Top = i * 27 + 15;
            }

            ButtAddGood.Top = 15 + 27 * Items.Count;

            label3.Top = ButtAddGood.Top + 7;
            label4.Top = ButtAddGood.Top + 7;
            LabelItogo.Top = ButtAddGood.Top + 7;
            TxtPrice.Top = ButtAddGood.Top + 25;
            TxtDeliveryPrice.Top = ButtAddGood.Top + 25;
            TxtTotalPrice.Top = ButtAddGood.Top + 25;
            Height = Items.Count * 27 + 65;
            calc_total(false, 0, false);
        }
        void delBox_Click(object sender, EventArgs e)
        {
            OrderItem itemToDel = ((OrderItem)((PictureBox)sender).Tag);
            Controls.Remove(itemToDel);
            Items.Remove(itemToDel);
            Controls.Remove((PictureBox)sender);
            DeletePics.Remove((PictureBox)sender);
            rebuildeList();
            if (onlistItemAdded != null)
            {
                onlistItemAdded();
            }
        }
        public void removeItem(OrderItem item)
        {
            Controls.Remove(item);
            Items.Remove(item);
            Controls.Remove((PictureBox)item.Tag);
            DeletePics.Remove((PictureBox)item.Tag);
            rebuildeList();
            if (onlistItemAdded != null)
            {
                onlistItemAdded();
            }
        }

        #endregion
        int currentDelPrice = 0;
        private void TxtDeliveryPrice_TextChanged(object sender, EventArgs e)
        {
            try
            {
                if (TxtDeliveryPrice.Text == "")
                {
                    TxtDeliveryPrice.Text = "0";
                }
                int disc = Int32.Parse(TxtDeliveryPrice.Text);
                if (disc > 10000 || disc < 0)
                {
                    TxtDeliveryPrice.Text = currentDelPrice.ToString();
                    return;
                }
                currentDelPrice = disc;
                calc_total(true, currentDelPrice, true);
            }
            catch
            {
                TxtDeliveryPrice.Text = currentDelPrice.ToString();
                calc_total(true, currentDelPrice, true);
            }
        }
    }
}
