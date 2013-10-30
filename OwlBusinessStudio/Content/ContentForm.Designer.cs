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
    partial class ContentForm
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
            this.ListContent = new System.Windows.Forms.ListBox();
            this.ButtSaveHtml = new System.Windows.Forms.Button();
            this.HtmlEditor = new HTMLWYSIWYG.htmlwysiwyg();
            this.txtHTML = new System.Windows.Forms.TextBox();
            this.ButtEditHtml = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // ListContent
            // 
            this.ListContent.FormattingEnabled = true;
            this.ListContent.Location = new System.Drawing.Point(1, 1);
            this.ListContent.Name = "ListContent";
            this.ListContent.Size = new System.Drawing.Size(157, 485);
            this.ListContent.TabIndex = 0;
            this.ListContent.SelectedIndexChanged += new System.EventHandler(this.ListContent_SelectedIndexChanged);
            // 
            // ButtSaveHtml
            // 
            this.ButtSaveHtml.Location = new System.Drawing.Point(164, 457);
            this.ButtSaveHtml.Name = "ButtSaveHtml";
            this.ButtSaveHtml.Size = new System.Drawing.Size(696, 29);
            this.ButtSaveHtml.TabIndex = 2;
            this.ButtSaveHtml.Text = "Сохранить";
            this.ButtSaveHtml.UseVisualStyleBackColor = true;
            this.ButtSaveHtml.Click += new System.EventHandler(this.ButtSaveHtml_Click);
            // 
            // HtmlEditor
            // 
            this.HtmlEditor.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.HtmlEditor.Location = new System.Drawing.Point(164, 1);
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
            this.HtmlEditor.Size = new System.Drawing.Size(696, 224);
            this.HtmlEditor.TabIndex = 3;
            // 
            // txtHTML
            // 
            this.txtHTML.Location = new System.Drawing.Point(164, 257);
            this.txtHTML.Multiline = true;
            this.txtHTML.Name = "txtHTML";
            this.txtHTML.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtHTML.Size = new System.Drawing.Size(696, 194);
            this.txtHTML.TabIndex = 4;
            this.txtHTML.TextChanged += new System.EventHandler(this.txtHTML_TextChanged);
            // 
            // ButtEditHtml
            // 
            this.ButtEditHtml.Location = new System.Drawing.Point(164, 231);
            this.ButtEditHtml.Name = "ButtEditHtml";
            this.ButtEditHtml.Size = new System.Drawing.Size(696, 23);
            this.ButtEditHtml.TabIndex = 5;
            this.ButtEditHtml.Text = "Отредактировать HTML";
            this.ButtEditHtml.UseVisualStyleBackColor = true;
            this.ButtEditHtml.Click += new System.EventHandler(this.ButtEditHtml_Click);
            // 
            // ContentForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(864, 493);
            this.Controls.Add(this.ButtEditHtml);
            this.Controls.Add(this.txtHTML);
            this.Controls.Add(this.HtmlEditor);
            this.Controls.Add(this.ButtSaveHtml);
            this.Controls.Add(this.ListContent);
            this.MaximumSize = new System.Drawing.Size(880, 531);
            this.MinimumSize = new System.Drawing.Size(880, 531);
            this.Name = "ContentForm";
            this.Text = "Контент сайта";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox ListContent;
        private System.Windows.Forms.Button ButtSaveHtml;
        private HTMLWYSIWYG.htmlwysiwyg HtmlEditor;
        private TextBox txtHTML;
        private Button ButtEditHtml;
    }
}