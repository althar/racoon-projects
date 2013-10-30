using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Content
{
    public partial class ContentForm : Form
    {
        private int selectedID = 0;
        public ContentForm()
        {
            InitializeComponent();
            DataTable t = MainForm.dbProc.executeGet("SELECT id,name FROM content");
            ListContent.DataSource = t;
            ListContent.DisplayMember = "name";
            ListContent.ValueMember = "id";
        }

        private void ButtSaveHtml_Click(object sender, EventArgs e)
        {
            string cont = HtmlEditor.getHTML().Replace("'","`");
            MainForm.dbProc.executeNonQuery("UPDATE content SET value = '"+cont+"' WHERE id="+selectedID);
        }

        private void ListContent_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ListContent.SelectedValue is int)
            {
                DataTable tt = MainForm.dbProc.executeGet("SELECT value FROM content WHERE id="+ListContent.SelectedValue.ToString());
                selectedID = (int)ListContent.SelectedValue;
                string cont = tt.Rows[0]["value"].ToString();
                HtmlEditor.setHTML(cont.Replace("`","'"));
                txtHTML.Text = "";
            }
        }

        private void txtHTML_TextChanged(object sender, EventArgs e)
        {
            string html = txtHTML.Text;
            HtmlEditor.setHTML(html);
        }


        private void ButtEditHtml_Click(object sender, EventArgs e)
        {
            string html = HtmlEditor.getHTML();
            txtHTML.Text = html;
        }
    }
}
