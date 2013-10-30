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
        public int DeliverPrice = 0;
        private int orderID = 0;
        private int deliveryDistanceID = 0;
        private int lowCost = 0;
        private int highCost = 0;
        private int separator = 0;

        public OrderList(int order_id)
        {
            InitializeComponent();
            orderID = order_id;
        }

        private void OrderList_Load(object sender, EventArgs e)
        {

        }
        public void setDiscount(int discount)
        {
            Discount = discount;
            firstItem_onSumChanged();
        }
        public void setDeliverPriceID(int id)
        {
            deliveryDistanceID = id;
            if (deliveryDistanceID != 0)
            {
                DataTable t = MainForm.dbProc.get("delivery_costs", "id=" + deliveryDistanceID.ToString());
                lowCost = (int)t.Rows[0]["low_price_delivery_cost"];
                highCost = (int)t.Rows[0]["high_price_delivery_cost"];
                separator = (int)t.Rows[0]["high_low_price_separator"];
            }
            else
            {
                lowCost = 0;
                highCost = 0;
                separator = 0;
            }
            firstItem_onSumChanged();
        }
        public void setDeliverPrice(int price)
        {
            deliverPriceSet = true;
            DeliverPrice = price;
            calc_total();
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
            calc_total();
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
        public void calc_total()
        {
            int goodsPrice = 0;
            for (int i = 0; i < Items.Count; i++)
            {
                goodsPrice += (int)(Items[i].getSum());
            }
            TxtPrice.Text = goodsPrice.ToString();
            int plusDisc = 0;
            if (deliveryDistanceID == 0)
            {
                plusDisc = 3;
            }
            int priceWithDiscount = goodsPrice - (int)(goodsPrice * ((Discount + plusDisc) / 100.0));
            TxtDeliveryPrice.Text = DeliverPrice.ToString();
            TxtTotalPrice.Text = (DeliverPrice + priceWithDiscount).ToString();
        }
        void firstItem_onSumChanged()
        {
            int goodsPrice = 0;
            for (int i = 0; i < Items.Count; i++)
            {
                goodsPrice += (int)(Items[i].getSum());
            }
            TxtPrice.Text = goodsPrice.ToString();
            
            
            int plusDisc = 0;
            if (deliveryDistanceID == 0)
            {
                plusDisc = 3;
            }
            int priceWithDiscount = goodsPrice-(int)(goodsPrice*((Discount+plusDisc)/100.0));
            

            // ---------------------------------- Deliver price using discount... 
            if (!deliverPriceSet)
            {
                if (priceWithDiscount >= separator)
                {
                    TxtDeliveryPrice.Text = highCost.ToString();
                    DeliverPrice = highCost;
                }
                else
                {
                    TxtDeliveryPrice.Text = lowCost.ToString();
                    DeliverPrice = lowCost;
                }
            }
            TxtTotalPrice.Text = (DeliverPrice + priceWithDiscount).ToString();


            if (onListSumChanged != null)
            {
                onListSumChanged();
            }
        }
        public void AddItem(int good_id, int quantity, string goodName, int goodPrice)
        {
            // Add control...
            OrderItem item = new OrderItem(this,orderID);
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
            calc_total();
            item.Tag = delBox;
        }
        public void AddItem()
        {
            // Add control...
            OrderItem item = new OrderItem(this,orderID);
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
            calc_total();
            item.Tag = delBox;
        }
        public int getGoodsPrice()
        {
            return (Int32.Parse(TxtPrice.Text));
        }
        private void ButtAddGood_Click(object sender, EventArgs e)
        {
            AddItem();
            calc_total();
            Items[Items.Count - 1].PicFind_Click(null, null);
            if (onlistItemAdded!= null)
            {
                onlistItemAdded();
            }
        }
        string deliver_price_text = "0";

        bool changedByHands = false;
        private void TxtDeliveryPrice_KeyPress(object sender, KeyPressEventArgs e)
        {
            changedByHands = true;
        }

        private void TxtDeliveryPrice_TextChanged(object sender, EventArgs e)
        {
            if (changedByHands)
            {
                try
                {
                    if (TxtDeliveryPrice.Text == "")
                    {
                        TxtDeliveryPrice.Text = "0";
                    }
                    DeliverPrice = Int32.Parse(TxtDeliveryPrice.Text);
                    deliver_price_text = DeliverPrice.ToString();
                    lowCost = DeliverPrice;
                    highCost = DeliverPrice;
                    calc_total();
                }
                catch (Exception ex)
                {
                    TxtDeliveryPrice.Text = deliver_price_text;
                }
                changedByHands = false;
            }
        }
    }
}
