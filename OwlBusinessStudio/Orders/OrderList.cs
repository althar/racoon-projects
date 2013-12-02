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
        public string distance = "";
        private int orderID = 0;

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
        public void setDeliverDistance(string distance)
        {
            this.distance = distance;
            firstItem_onSumChanged();
        }
        public void setDeliverPrice(int price)
        {
            deliverPriceSet = true;
            DeliverPrice = price;
            calc_total();
        }
        
        public void calc_total()
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

            // 3 - Calculate total discount and price with discount
            priceWithDiscount = goodsPrice - (int)(goodsPrice * ((totalDiscount) / 100.0));

            // 4 - Fill controll with data
            TxtPrice.Text = goodsPrice.ToString();
            TxtDeliveryPrice.Text = deliveryPrice.ToString();
            TxtTotalPrice.Text = (deliveryPrice + priceWithDiscount).ToString();
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

        #region UI

        public void AddItem(int good_id, int quantity, string goodName, int goodPrice)
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
            calc_total();
            item.Tag = delBox;
        }
        public void AddItem()
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

        #endregion
    }
}
