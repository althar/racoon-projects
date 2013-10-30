using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Orders
{
    public partial class PictureViewForm : Form
    {
        private string pictureURL;

        public PictureViewForm(string picURL,string name)
        {
            InitializeComponent();
            pictureURL = picURL;
            Text = name;
        }

        private void PictureViewForm_Load(object sender, EventArgs e)
        {
            PicMain.ImageLocation = pictureURL;
        }

        private void PicMain_Resize(object sender, EventArgs e)
        {
            Height = PicMain.Height + 38;
            Width = PicMain.Width + 16;
            this.MaximumSize = new Size(Width,Height);
            this.MinimumSize = new Size(Width, Height);
        }
    }
}
