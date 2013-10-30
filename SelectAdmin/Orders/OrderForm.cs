using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace LowByAdmin.Orders
{
    public partial class OrderForm : Form
    {
        private long orderId;
        public OrderForm(long orderId)
        {
            InitializeComponent();
            this.orderId = orderId;
        }
        private void loadOrder()
        {

        }
    }
}
