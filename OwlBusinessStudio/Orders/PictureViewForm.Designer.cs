namespace OwlBusinessStudio.Orders
{
    partial class PictureViewForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(PictureViewForm));
            this.PicMain = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.PicMain)).BeginInit();
            this.SuspendLayout();
            // 
            // PicMain
            // 
            this.PicMain.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PicMain.Location = new System.Drawing.Point(0, 0);
            this.PicMain.Name = "PicMain";
            this.PicMain.Size = new System.Drawing.Size(116, 101);
            this.PicMain.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize;
            this.PicMain.TabIndex = 0;
            this.PicMain.TabStop = false;
            this.PicMain.Resize += new System.EventHandler(this.PicMain_Resize);
            // 
            // PictureViewForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(116, 101);
            this.Controls.Add(this.PicMain);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "PictureViewForm";
            this.Load += new System.EventHandler(this.PictureViewForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.PicMain)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox PicMain;
    }
}