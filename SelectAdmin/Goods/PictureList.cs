using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace LowByAdmin.Goods
{
    public partial class PictureList : UserControl
    {
        List<PictureBox> pics = new List<PictureBox>();
        List<Button> changeButts = new List<Button>();
        List<CheckBox> mainCheckBoxes = new List<CheckBox>();
        int picSize;
        int buttW;
        int buttH;

        public PictureList()
        {
            InitializeComponent();
            //this.picSize = 20;
            //this.buttW = picSize - 12;
            //this.buttH = 20;
            //Height = picSize + buttH + 2;
        }

        public void addPicture(string url)
        {
            PictureBox pic = new PictureBox();
            pic.Load(url);
            Button change = new Button();
            change.Text = "заменить";
            CheckBox box = new CheckBox();
            Controls.Add(pic);
            pics.Add(pic);
            Controls.Add(change);
            changeButts.Add(change);
            Controls.Add(box);
            mainCheckBoxes.Add(box);
            pic.Left = (picSize + 2) * (pics.Count-1);
            pic.Top = 0;
            pic.Width = picSize;
            pic.Height = picSize;
            change.Width = buttW;
            change.Height = buttH;
            change.Top = picSize + 2;
            change.Left = (picSize + 2) * (pics.Count - 1);
            box.Top = change.Top;
            box.Left = change.Left + change.Width + 2;
            Width = (picSize + 2) * pics.Count;
        }
        

        private void PictureList_Resize(object sender, EventArgs e)
        {

        }
    }
}
