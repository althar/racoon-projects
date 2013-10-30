namespace OwlBusinessStudio.Orders
{
    partial class OrderItem
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(OrderItem));
            this.TxtSum = new System.Windows.Forms.TextBox();
            this.NumPrice = new System.Windows.Forms.NumericUpDown();
            this.NumQuantity = new System.Windows.Forms.NumericUpDown();
            this.ComboGoods = new System.Windows.Forms.ComboBox();
            this.PicWarning = new System.Windows.Forms.PictureBox();
            this.PicFind = new System.Windows.Forms.PictureBox();
            this.LabelWarning = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.NumPrice)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumQuantity)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.PicWarning)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.PicFind)).BeginInit();
            this.SuspendLayout();
            // 
            // TxtSum
            // 
            this.TxtSum.BackColor = System.Drawing.SystemColors.ButtonHighlight;
            this.TxtSum.Location = new System.Drawing.Point(729, 4);
            this.TxtSum.Name = "TxtSum";
            this.TxtSum.ReadOnly = true;
            this.TxtSum.Size = new System.Drawing.Size(73, 20);
            this.TxtSum.TabIndex = 12;
            // 
            // NumPrice
            // 
            this.NumPrice.Location = new System.Drawing.Point(660, 4);
            this.NumPrice.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumPrice.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.NumPrice.Name = "NumPrice";
            this.NumPrice.Size = new System.Drawing.Size(66, 20);
            this.NumPrice.TabIndex = 11;
            this.NumPrice.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.NumPrice.ValueChanged += new System.EventHandler(this.NumQuantity_ValueChanged);
            // 
            // NumQuantity
            // 
            this.NumQuantity.Location = new System.Drawing.Point(603, 4);
            this.NumQuantity.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.NumQuantity.Minimum = new decimal(new int[] {
            100000,
            0,
            0,
            -2147483648});
            this.NumQuantity.Name = "NumQuantity";
            this.NumQuantity.Size = new System.Drawing.Size(54, 20);
            this.NumQuantity.TabIndex = 10;
            this.NumQuantity.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.NumQuantity.ValueChanged += new System.EventHandler(this.NumQuantity_ValueChanged);
            // 
            // ComboGoods
            // 
            this.ComboGoods.FormattingEnabled = true;
            this.ComboGoods.Location = new System.Drawing.Point(32, 3);
            this.ComboGoods.Name = "ComboGoods";
            this.ComboGoods.Size = new System.Drawing.Size(565, 21);
            this.ComboGoods.TabIndex = 9;
            this.ComboGoods.SelectedIndexChanged += new System.EventHandler(this.ComboGoods_SelectedIndexChanged);
            this.ComboGoods.KeyDown += new System.Windows.Forms.KeyEventHandler(this.ComboGoods_KeyDown);
            this.ComboGoods.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.ComboGoods_KeyPress);
            // 
            // PicWarning
            // 
            this.PicWarning.Cursor = System.Windows.Forms.Cursors.Help;
            this.PicWarning.Image = ((System.Drawing.Image)(resources.GetObject("PicWarning.Image")));
            this.PicWarning.InitialImage = ((System.Drawing.Image)(resources.GetObject("PicWarning.InitialImage")));
            this.PicWarning.Location = new System.Drawing.Point(824, 2);
            this.PicWarning.Name = "PicWarning";
            this.PicWarning.Size = new System.Drawing.Size(24, 24);
            this.PicWarning.TabIndex = 13;
            this.PicWarning.TabStop = false;
            this.PicWarning.Visible = false;
            this.PicWarning.Click += new System.EventHandler(this.PicWarning_Click);
            // 
            // PicFind
            // 
            this.PicFind.Cursor = System.Windows.Forms.Cursors.Hand;
            this.PicFind.Image = ((System.Drawing.Image)(resources.GetObject("PicFind.Image")));
            this.PicFind.InitialImage = ((System.Drawing.Image)(resources.GetObject("PicFind.InitialImage")));
            this.PicFind.Location = new System.Drawing.Point(3, 2);
            this.PicFind.Name = "PicFind";
            this.PicFind.Size = new System.Drawing.Size(24, 24);
            this.PicFind.TabIndex = 14;
            this.PicFind.TabStop = false;
            this.PicFind.Click += new System.EventHandler(this.PicFind_Click);
            // 
            // LabelWarning
            // 
            this.LabelWarning.AutoSize = true;
            this.LabelWarning.ForeColor = System.Drawing.Color.OrangeRed;
            this.LabelWarning.Location = new System.Drawing.Point(808, 7);
            this.LabelWarning.Name = "LabelWarning";
            this.LabelWarning.Size = new System.Drawing.Size(67, 13);
            this.LabelWarning.TabIndex = 15;
            this.LabelWarning.Text = "на складе 0";
            // 
            // OrderItem
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.LabelWarning);
            this.Controls.Add(this.PicFind);
            this.Controls.Add(this.PicWarning);
            this.Controls.Add(this.TxtSum);
            this.Controls.Add(this.NumPrice);
            this.Controls.Add(this.NumQuantity);
            this.Controls.Add(this.ComboGoods);
            this.Name = "OrderItem";
            this.Size = new System.Drawing.Size(892, 27);
            ((System.ComponentModel.ISupportInitialize)(this.NumPrice)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumQuantity)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.PicWarning)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.PicFind)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox TxtSum;
        private System.Windows.Forms.NumericUpDown NumPrice;
        private System.Windows.Forms.NumericUpDown NumQuantity;
        private System.Windows.Forms.PictureBox PicWarning;
        private System.Windows.Forms.PictureBox PicFind;
        private System.Windows.Forms.Label LabelWarning;
        public System.Windows.Forms.ComboBox ComboGoods;
    }
}
