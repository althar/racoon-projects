namespace LowByAdmin.Administration
{
    partial class ArticlesForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.TxtTitle = new System.Windows.Forms.TextBox();
            this.TxtKeyword = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.ComboPage = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.ComboLocation = new System.Windows.Forms.ComboBox();
            this.label4 = new System.Windows.Forms.Label();
            this.HtmlEditor = new HTMLWYSIWYG.htmlwysiwyg();
            this.ButtSave = new System.Windows.Forms.Button();
            this.TxtHtml = new System.Windows.Forms.TextBox();
            this.ListArticles = new System.Windows.Forms.ListBox();
            this.label5 = new System.Windows.Forms.Label();
            this.ButtAddArticle = new System.Windows.Forms.Button();
            this.ButtRemoveArticle = new System.Windows.Forms.Button();
            this.ButtToHtml = new System.Windows.Forms.Button();
            this.label6 = new System.Windows.Forms.Label();
            this.TxtLink = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // TxtTitle
            // 
            this.TxtTitle.Location = new System.Drawing.Point(108, 12);
            this.TxtTitle.Name = "TxtTitle";
            this.TxtTitle.Size = new System.Drawing.Size(289, 20);
            this.TxtTitle.TabIndex = 0;
            // 
            // TxtKeyword
            // 
            this.TxtKeyword.Location = new System.Drawing.Point(108, 38);
            this.TxtKeyword.Name = "TxtKeyword";
            this.TxtKeyword.Size = new System.Drawing.Size(289, 20);
            this.TxtKeyword.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(61, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Загаловок";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 41);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(90, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Ключевое слово";
            // 
            // ComboPage
            // 
            this.ComboPage.FormattingEnabled = true;
            this.ComboPage.Items.AddRange(new object[] {
            "Главная",
            "Каталог"});
            this.ComboPage.Location = new System.Drawing.Point(108, 64);
            this.ComboPage.Name = "ComboPage";
            this.ComboPage.Size = new System.Drawing.Size(289, 21);
            this.ComboPage.TabIndex = 4;
            this.ComboPage.Text = "Главная";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 67);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(55, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Страница";
            // 
            // ComboLocation
            // 
            this.ComboLocation.FormattingEnabled = true;
            this.ComboLocation.Items.AddRange(new object[] {
            "footer",
            "left"});
            this.ComboLocation.Location = new System.Drawing.Point(108, 91);
            this.ComboLocation.Name = "ComboLocation";
            this.ComboLocation.Size = new System.Drawing.Size(289, 21);
            this.ComboLocation.TabIndex = 6;
            this.ComboLocation.Text = "footer";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(12, 94);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(82, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Расположение";
            // 
            // HtmlEditor
            // 
            this.HtmlEditor.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.HtmlEditor.Location = new System.Drawing.Point(403, 12);
            this.HtmlEditor.Name = "HtmlEditor";
            this.HtmlEditor.ShowAlignCenterButton = true;
            this.HtmlEditor.ShowAlignLeftButton = true;
            this.HtmlEditor.ShowAlignRightButton = true;
            this.HtmlEditor.ShowBackColorButton = true;
            this.HtmlEditor.ShowBolButton = true;
            this.HtmlEditor.ShowBulletButton = true;
            this.HtmlEditor.ShowCopyButton = true;
            this.HtmlEditor.ShowCutButton = true;
            this.HtmlEditor.ShowFontFamilyButton = true;
            this.HtmlEditor.ShowFontSizeButton = true;
            this.HtmlEditor.ShowIndentButton = true;
            this.HtmlEditor.ShowItalicButton = true;
            this.HtmlEditor.ShowJustifyButton = true;
            this.HtmlEditor.ShowLinkButton = true;
            this.HtmlEditor.ShowNewButton = true;
            this.HtmlEditor.ShowOrderedListButton = true;
            this.HtmlEditor.ShowOutdentButton = true;
            this.HtmlEditor.ShowPasteButton = true;
            this.HtmlEditor.ShowPrintButton = true;
            this.HtmlEditor.ShowTxtBGButton = true;
            this.HtmlEditor.ShowTxtColorButton = true;
            this.HtmlEditor.ShowUnderlineButton = true;
            this.HtmlEditor.ShowUnlinkButton = true;
            this.HtmlEditor.Size = new System.Drawing.Size(727, 245);
            this.HtmlEditor.TabIndex = 8;
            // 
            // ButtSave
            // 
            this.ButtSave.Location = new System.Drawing.Point(108, 493);
            this.ButtSave.Name = "ButtSave";
            this.ButtSave.Size = new System.Drawing.Size(1022, 30);
            this.ButtSave.TabIndex = 9;
            this.ButtSave.Text = "Сохранить";
            this.ButtSave.UseVisualStyleBackColor = true;
            this.ButtSave.Click += new System.EventHandler(this.ButtSave_Click);
            // 
            // TxtHtml
            // 
            this.TxtHtml.Location = new System.Drawing.Point(403, 285);
            this.TxtHtml.Multiline = true;
            this.TxtHtml.Name = "TxtHtml";
            this.TxtHtml.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.TxtHtml.Size = new System.Drawing.Size(727, 202);
            this.TxtHtml.TabIndex = 10;
            this.TxtHtml.TextChanged += new System.EventHandler(this.TxtHtml_TextChanged);
            // 
            // ListArticles
            // 
            this.ListArticles.FormattingEnabled = true;
            this.ListArticles.Location = new System.Drawing.Point(108, 171);
            this.ListArticles.Name = "ListArticles";
            this.ListArticles.Size = new System.Drawing.Size(289, 290);
            this.ListArticles.TabIndex = 12;
            this.ListArticles.SelectedIndexChanged += new System.EventHandler(this.ListArticles_SelectedIndexChanged);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 171);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(42, 13);
            this.label5.TabIndex = 13;
            this.label5.Text = "Статьи";
            // 
            // ButtAddArticle
            // 
            this.ButtAddArticle.Location = new System.Drawing.Point(108, 142);
            this.ButtAddArticle.Name = "ButtAddArticle";
            this.ButtAddArticle.Size = new System.Drawing.Size(289, 23);
            this.ButtAddArticle.TabIndex = 14;
            this.ButtAddArticle.Text = "Создать";
            this.ButtAddArticle.UseVisualStyleBackColor = true;
            this.ButtAddArticle.Click += new System.EventHandler(this.ButtAddArticle_Click);
            // 
            // ButtRemoveArticle
            // 
            this.ButtRemoveArticle.Location = new System.Drawing.Point(108, 464);
            this.ButtRemoveArticle.Name = "ButtRemoveArticle";
            this.ButtRemoveArticle.Size = new System.Drawing.Size(289, 23);
            this.ButtRemoveArticle.TabIndex = 15;
            this.ButtRemoveArticle.Text = "Удалить";
            this.ButtRemoveArticle.UseVisualStyleBackColor = true;
            this.ButtRemoveArticle.Click += new System.EventHandler(this.ButtRemoveArticle_Click);
            // 
            // ButtToHtml
            // 
            this.ButtToHtml.Location = new System.Drawing.Point(403, 259);
            this.ButtToHtml.Name = "ButtToHtml";
            this.ButtToHtml.Size = new System.Drawing.Size(727, 26);
            this.ButtToHtml.TabIndex = 16;
            this.ButtToHtml.Text = "HTML";
            this.ButtToHtml.UseVisualStyleBackColor = true;
            this.ButtToHtml.Click += new System.EventHandler(this.ButtToHtml_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(12, 119);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(46, 13);
            this.label6.TabIndex = 17;
            this.label6.Text = "Ссылка";
            // 
            // TxtLink
            // 
            this.TxtLink.Location = new System.Drawing.Point(108, 116);
            this.TxtLink.Name = "TxtLink";
            this.TxtLink.Size = new System.Drawing.Size(289, 20);
            this.TxtLink.TabIndex = 18;
            // 
            // ArticlesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1142, 535);
            this.Controls.Add(this.TxtLink);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.ButtToHtml);
            this.Controls.Add(this.ButtRemoveArticle);
            this.Controls.Add(this.ButtAddArticle);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.ListArticles);
            this.Controls.Add(this.TxtHtml);
            this.Controls.Add(this.ButtSave);
            this.Controls.Add(this.HtmlEditor);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.ComboLocation);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.ComboPage);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.TxtKeyword);
            this.Controls.Add(this.TxtTitle);
            this.Name = "ArticlesForm";
            this.Text = "ArticlesForm";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox TxtTitle;
        private System.Windows.Forms.TextBox TxtKeyword;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ComboBox ComboPage;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ComboBox ComboLocation;
        private System.Windows.Forms.Label label4;
        private HTMLWYSIWYG.htmlwysiwyg HtmlEditor;
        private System.Windows.Forms.Button ButtSave;
        private System.Windows.Forms.TextBox TxtHtml;
        private System.Windows.Forms.ListBox ListArticles;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button ButtAddArticle;
        private System.Windows.Forms.Button ButtRemoveArticle;
        private System.Windows.Forms.Button ButtToHtml;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox TxtLink;
    }
}