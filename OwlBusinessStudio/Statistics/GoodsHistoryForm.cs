using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Statistics
{
    public partial class GoodsHistoryForm : Form
    {
        public GoodsHistoryForm()
        {
            InitializeComponent();
        }

        private void GoodsHistoryForm_Resize(object sender, EventArgs e)
        {
            DataGridGoodsHistory.Width = Width - 38;
            DataGridGoodsHistory.Height = Height - 100;
        }

        private void ButtOK_Click(object sender, EventArgs e)
        {
            string query = "SELECT * FROM goods_history WHERE ";
            query += "date(action_time)>=date(" + MainForm.dbProc.getDateTimeString(TimePickerFrom.Value) + ") ";
            query += "AND ";
            query += "date(action_time)<=date(" + MainForm.dbProc.getDateTimeString(TimePickerTo.Value) + ") ";
            if (CheckAcceptGoods.Checked || CheckPurchase.Checked)
            {
                query += " AND ";
            }
            if (CheckAcceptGoods.Checked)
            {
                query += " (action = 'orders_accept'";
                if (CheckPurchase.Checked)
                {
                    query += " OR ";
                }
            }
            if (CheckPurchase.Checked)
            {
                query += " action = 'purchase_approve'";
            }
            if (CheckAcceptGoods.Checked)
            {
                query += ");";
            }
            DataTable t = MainForm.dbProc.executeGet(query);
            DataGridGoodsHistory.DataSource = t;
        }
    }
}
