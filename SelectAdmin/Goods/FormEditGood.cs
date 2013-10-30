using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace LowByAdmin.Goods
{
    public partial class FormEditGood : Form
    {
        private long goodId;
        public FormEditGood(long goodId)
        {
            try
            {
                InitializeComponent();
                this.goodId = goodId;

                DataTable goodTab = MainForm.dbProc.get("good", "id=" + goodId);
                DataTable goodPics = MainForm.dbProc.get("good_image", "is_main = FALSE AND good_id=" + goodId);
                DataTable goodMainPic = MainForm.dbProc.get("good_image", "is_main = TRUE AND good_id=" + goodId);
                DataTable goodCategories = MainForm.dbProc.executeGet("SELECT DISTINCT category AS category FROM good");
                DataRow good = goodTab.Rows[0];
                TxtArticle.Text = (String)good["article"];
                TxtBrand.Text = (String)good["brand"];
                TxtName.Text = (String)good["name"];
                if (!(good["description"] is DBNull))
                {
                    TxtDescription.Text = (String)good["description"];
                }
                if (!(good["short_description"] is DBNull))
                {
                    TxtShortDescription.Text = (String)good["short_description"];
                }
                if (!(good["seo_description"] is DBNull))
                {
                    TxtSeoDescription.Text = (String)good["seo_description"];
                }
                TxtPurchasePrice.Text = good["purchase_price"].ToString();
                TxtSellPrice.Text = good["sell_price"].ToString();
                ComboCategory.DataSource = goodCategories;
                ComboCategory.DisplayMember = "category";
                ComboCategory.Text = (String)good["category"];
                NumAvailable.Value = Decimal.Parse(good["available"].ToString());
                if(goodPics.Rows.Count>0)
                {
                    Pic1.Load(goodPics.Rows[0]["url"].ToString());
                    Pic1.Tag = goodPics.Rows[0]["url"].ToString();
                }
                if (goodPics.Rows.Count > 1)
                {
                    Pic2.Load(goodPics.Rows[1]["url"].ToString());
                    Pic2.Tag = goodPics.Rows[1]["url"].ToString();
                }
                if (goodPics.Rows.Count > 2)
                {
                    Pic3.Load(goodPics.Rows[2]["url"].ToString());
                    Pic3.Tag = goodPics.Rows[2]["url"].ToString();
                }
                if (goodMainPic.Rows.Count > 0)
                {
                    PicMain.Load(goodMainPic.Rows[0]["url"].ToString());
                    PicMain.Tag = goodMainPic.Rows[0]["url"].ToString();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Hashtable good = new Hashtable();
            good.Add("article", TxtArticle.Text);
            good.Add("brand", TxtBrand.Text);
            good.Add("name", TxtName.Text);
            good.Add("description", TxtDescription.Text);
            good.Add("short_description", TxtShortDescription.Text);
            good.Add("seo_description", TxtSeoDescription.Text);
            good.Add("category", ComboCategory.Text);
            good.Add("purchase_price", Double.Parse(TxtPurchasePrice.Text));
            good.Add("available", Int32.Parse(NumAvailable.Value.ToString()));
            good.Add("sell_price", Double.Parse(TxtSellPrice.Text));
            MainForm.dbProc.update("good", good, "id=" + goodId);

            MainForm.dbProc.delete("good_image", "good_id=" + goodId);
            
            if (PicMain.Tag != null)
            {
                Hashtable image = new Hashtable();
                image.Add("is_main", true);
                image.Add("good_id", goodId);
                image.Add("url", PicMain.Tag.ToString());
                string size = PicMain.Image.Width.ToString() + "x" + PicMain.Image.Height.ToString();
                image.Add("size", size);
                MainForm.dbProc.insert("good_image", image);
            }
            if (Pic1.Tag != null)
            {
                Hashtable image = new Hashtable();
                image.Add("is_main", false);
                image.Add("good_id", goodId);
                image.Add("url", Pic1.Tag.ToString());
                string size = Pic1.Image.Width.ToString() + "x" + Pic1.Image.Height.ToString();
                image.Add("size", size);
                MainForm.dbProc.insert("good_image", image);
            }
            if (Pic2.Tag != null)
            {
                Hashtable image = new Hashtable();
                image.Add("is_main", false);
                image.Add("good_id", goodId);
                image.Add("url", Pic2.Tag.ToString());
                string size = Pic2.Image.Width.ToString() + "x" + Pic2.Image.Height.ToString();
                image.Add("size", size);
                MainForm.dbProc.insert("good_image", image);
            }
            if (Pic3.Tag != null)
            {
                Hashtable image = new Hashtable();
                image.Add("is_main", false);
                image.Add("good_id", goodId);
                image.Add("url", Pic3.Tag.ToString());
                string size = Pic3.Image.Width.ToString() + "x" + Pic3.Image.Height.ToString();
                image.Add("size", size);
                MainForm.dbProc.insert("good_image", image);
            }
            Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                PictureBox box = (PictureBox)PanelPicUrl.Tag;
                box.Tag = TxtPicUrl.Text;
                box.Load(TxtPicUrl.Text);
                PanelPicUrl.Visible = false;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Не удалось загрузить страницу...");
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            PanelPicUrl.Visible = false;
        }

        private void PicMain_Click(object sender, EventArgs e)
        {
            PanelPicUrl.Tag = sender;
            TxtPicUrl.Text = "";
            if (((PictureBox)sender).Tag != null)
            {
                TxtPicUrl.Text = ((PictureBox)sender).Tag.ToString();
            }
            PanelPicUrl.Visible = true;
            TxtPicUrl.Focus();
            TxtPicUrl.SelectAll();
        }
    }
}
