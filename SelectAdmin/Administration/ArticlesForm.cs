using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace LowByAdmin.Administration
{
    public partial class ArticlesForm : Form
    {
        public ArticlesForm()
        {
            InitializeComponent();
            loadArticles();
        }
        private void loadArticles()
        {
            DataTable articales = MainForm.dbProc.get("seo_article",null,"*,title||'('||keyword||')' AS caption");
            ListArticles.DataSource = articales;
            ListArticles.DisplayMember = "caption";
            ListArticles.ValueMember = "id";
        }
        private void ButtAddArticle_Click(object sender, EventArgs e)
        {
            Hashtable tab = new Hashtable();
            tab.Add("title", TxtTitle.Text);
            tab.Add("text", "");
            tab.Add("page", ComboPage.Text);
            tab.Add("keyword", TxtKeyword.Text);
            tab.Add("location", ComboLocation.Text);
            tab.Add("link", TxtLink.Text);
            MainForm.dbProc.insert("seo_article", tab);
            loadArticles();
        }
        private void synchronyze(bool fromHtml)
        {
            if (fromHtml)
            {
                string html = HtmlEditor.getHTML();
                TxtHtml.Text = html;
            }
            else
            {
                string html = TxtHtml.Text;
                HtmlEditor.setHTML(html);
            }
        }
        private long currentId = 0;
        private void ListArticles_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (!(ListArticles.SelectedValue is long))
            {
                return;
            }
            currentId = (long)ListArticles.SelectedValue;
            DataTable article = MainForm.dbProc.get("seo_article", "id=" + currentId, "*");
            if (article.Rows.Count == 1)
            {
                DataRow r = article.Rows[0];
                string text = r["text"].ToString();
                HtmlEditor.setHTML(text);
                TxtTitle.Text = r["title"].ToString();
                ComboPage.Text = r["page"].ToString();
                TxtKeyword.Text = r["keyword"].ToString();
                ComboLocation.Text = r["location"].ToString();
                TxtLink.Text = r["link"].ToString();
                synchronyze(true);
            }
        }
        private void TxtHtml_TextChanged(object sender, EventArgs e)
        {
            synchronyze(false);
        }

        private void ButtToHtml_Click(object sender, EventArgs e)
        {
            synchronyze(true);
        }
        //Мы предоставлем широкий выбор смесителей для ванны и кухни от ведущих мировых производителей. В нашем магазине представлены такие бренды как <A href="http://http://www.zorginox.ru/">Zorg</A>, <A href="http://www.wasserkraft.ru/">WasserKRAFT</A>. И многих других. В нашем интернет магазине можно так же заказать сантехнику, которая не представлена в каталоге. Мы постараемся заказть ее наприямую у поставщика специально для вас.
        private void ButtSave_Click(object sender, EventArgs e)
        {
            string text = HtmlEditor.getHTML();
            Hashtable tab = new Hashtable();
            tab.Add("text", text);
            tab.Add("title", TxtTitle.Text);
            tab.Add("page", ComboPage.Text);
            tab.Add("keyword", TxtKeyword.Text);
            tab.Add("location", ComboLocation.Text);
            tab.Add("link", TxtLink.Text);
            MainForm.dbProc.update("seo_article", tab, "id=" + currentId);
        }

        private void ButtRemoveArticle_Click(object sender, EventArgs e)
        {
            MainForm.dbProc.delete("seo_article", currentId);
            loadArticles();
        }
    }
}
