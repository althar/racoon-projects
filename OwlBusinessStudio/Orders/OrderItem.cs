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
    public partial class OrderItem : UserControl
    {
        public delegate void sumChangedHandler(bool useGivenDiscount,int delPrice,bool useDelPrice);
        public event sumChangedHandler onSumChanged;
        public long currentGoodQuantity = 0;
        public long currentGoodMinimum = 0;
        public long currentGoodReserved = 0;
        private int goodID = 0;
        private int orderID = 0;
        private OrderList parent;

        public OrderItem(OrderList owner,int order_id)
        {
            InitializeComponent();
            orderID = order_id;
            loadGoods();
            parent = owner;
        }
        public void loadItem(int good_id,int quantity,string goodName,int goodPrice)
        {
            goodID = good_id;
            NumQuantity.Value = quantity;
            ComboGoods.Text = goodName;
            NumPrice.Value = goodPrice;
            DataTable goodInfo = MainForm.dbProc.executeGet("SELECT g.quantity,g.minimum FROM goods g WHERE g.id="
                + goodID.ToString());
            DataTable orderGoodInfo = MainForm.dbProc.executeGet("SELECT sum(og.count) AS reserved FROM orders ord,order_goods og WHERE og.order_id = ord.id "
            + " AND (ord.status=1) AND og.good_id=" + goodID.ToString() + " AND ord.id!=" + orderID);
            currentGoodQuantity = (int)goodInfo.Rows[0]["quantity"];
            currentGoodMinimum = (int)goodInfo.Rows[0]["minimum"];
            currentGoodReserved = 0;
            if (!(orderGoodInfo.Rows[0]["reserved"] is DBNull))
            {
                currentGoodReserved = (long)orderGoodInfo.Rows[0]["reserved"];
            }
            NumQuantity_ValueChanged(null, null);
        }
        public void loadItem()
        {
            if (ComboGoods.SelectedValue != null)
            {
                currentRow = ((DataRowView)ComboGoods.SelectedItem).Row;
                NumPrice.Value = (int)currentRow["price"];
                goodID = (int)currentRow["id"];
                DataTable goodInfo = MainForm.dbProc.executeGet("SELECT g.quantity,g.minimum FROM goods g WHERE g.id="
                + goodID.ToString());
                DataTable orderGoodInfo = MainForm.dbProc.executeGet("SELECT sum(og.count) AS reserved FROM orders ord,order_goods og WHERE og.order_id = ord.id "
                + " AND (ord.status=1 OR ord.status=2) AND og.good_id=" + goodID.ToString() + orderID);
                currentGoodQuantity = (int)goodInfo.Rows[0]["quantity"];
                currentGoodMinimum = (int)goodInfo.Rows[0]["minimum"];
                currentGoodReserved = 0;
                if (!(orderGoodInfo.Rows[0]["reserved"] is DBNull))
                {
                    currentGoodReserved = (long)orderGoodInfo.Rows[0]["reserved"];
                }
                NumQuantity_ValueChanged(null, null);
            }
        }
        public void loadGoods()
        {
            try
            {
                DataTable goods = MainForm.dbProc.executeGet("(SELECT id,name_for_order AS name_for_order,quantity,minimum,price FROM goods WHERE weight is null UNION ALL "
                    + "SELECT id,name_for_order||' '||weight||' кг.' AS name_for_order,quantity,minimum,price FROM goods WHERE weight is not null) ORDER BY id");
                ComboGoods.DataSource = goods;
                ComboGoods.DisplayMember = "name_for_order";
                ComboGoods.ValueMember = "id";
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }
        private DataRow currentRow = null;
        private void ComboGoods_SelectedIndexChanged(object sender, EventArgs e)
        {
            //MessageBox.Show("index changed");
            if (ComboGoods.SelectedValue != null&&goodID!=0)
            {
                currentRow = ((DataRowView)ComboGoods.SelectedItem).Row;
                NumPrice.Value = Convert.ToInt32(currentRow["price"]);
                goodID = (int)currentRow["id"];
                DataTable goodInfo = MainForm.dbProc.executeGet("SELECT g.quantity,g.minimum FROM goods g WHERE g.id="
                + goodID.ToString());
                DataTable orderGoodInfo = MainForm.dbProc.executeGet("SELECT sum(og.count) AS reserved FROM orders ord,order_goods og WHERE og.order_id = ord.id "
                + " AND (ord.status=1 OR ord.status=2) AND og.good_id=" + goodID.ToString());
                currentGoodQuantity = (int)goodInfo.Rows[0]["quantity"];
                currentGoodMinimum = (int)goodInfo.Rows[0]["minimum"];
                currentGoodReserved = 0;
                if (!(orderGoodInfo.Rows[0]["reserved"] is DBNull))
                {
                    currentGoodReserved = (long)orderGoodInfo.Rows[0]["reserved"];
                }
                NumQuantity_ValueChanged(null, null);
            }
        }
         
        private void NumQuantity_ValueChanged(object sender, EventArgs e)
        {
            try
            {
                long quantity_left = currentGoodQuantity - currentGoodReserved;
                if (quantity_left < NumQuantity.Value)
                {
                    LabelWarning.Text = "На складе " + (currentGoodQuantity - currentGoodReserved).ToString();
                    LabelWarning.Visible = true;
                    //PicWarning.Visible = true;
                }
                else
                {
                    LabelWarning.Visible = false;
                    //PicWarning.Visible = false;
                }
                TxtSum.Text = (NumPrice.Value * NumQuantity.Value).ToString();
                if (onSumChanged != null)
                {
                    onSumChanged(!parent.canRecalculate,0,!parent.canRecalculate);
                }
            }
            catch (Exception ex)
            {

            }
        }
        public void clearText()
        {
            ComboGoods.Text = "";
            ComboGoods.SelectedIndex = -1;
        }
        public int getQuantity()
        {
            return (int)NumQuantity.Value;
        }
        public int getPrice()
        {
            return (int)NumPrice.Value;
        }
        public int getSum()
        {
            return Int32.Parse(TxtSum.Text);
        }
        public int getGoodID()
        {
            return goodID;
            //return (int)currentRow["id"];
        }
        public string getGoodName()
        {
            return ComboGoods.Text;
        }

        private void PicWarning_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Текущее кол-во на складе: "+currentGoodQuantity+Environment.NewLine+
                "Зарезервировано под заказы: "+currentGoodReserved+Environment.NewLine+
                    "Должен остаться минимальный запас: "+currentGoodMinimum+Environment.NewLine);
        }

        public void PicFind_Click(object sender, EventArgs e)
        {
            FindGoodsForm form = new FindGoodsForm(this,parent);
            form.Visible = true;
            form.Activate();
        }

        private void ComboGoods_KeyDown(object sender, KeyEventArgs e)
        {
            e.Handled = true;
        }

        private void ComboGoods_KeyPress(object sender, KeyPressEventArgs e)
        {
            e.Handled = true;
        }
    }
}
