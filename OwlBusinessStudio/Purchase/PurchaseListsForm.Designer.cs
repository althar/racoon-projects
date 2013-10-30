namespace OwlBusinessStudio.Purchase
{
    partial class PurchaseListsForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(PurchaseListsForm));
            this.ListSuppliers = new System.Windows.Forms.ListBox();
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripButton1 = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtAdd = new System.Windows.Forms.ToolStripButton();
            this.ButtDelete = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator4 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator5 = new System.Windows.Forms.ToolStripSeparator();
            this.DataGridViewGoods = new System.Windows.Forms.DataGridView();
            this.PanelAddGood = new System.Windows.Forms.Panel();
            this.ButtCancel = new System.Windows.Forms.Button();
            this.ButtAddOK = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.TxtPrice = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.ComboSuppliers = new System.Windows.Forms.ComboBox();
            this.ComboGoods = new System.Windows.Forms.ComboBox();
            this.NumToPurchase = new System.Windows.Forms.NumericUpDown();
            this.CheckAllSupliers = new System.Windows.Forms.CheckBox();
            this.label5 = new System.Windows.Forms.Label();
            this.TxtTotal = new System.Windows.Forms.TextBox();
            this.TimePickerPurchaseTo = new System.Windows.Forms.DateTimePicker();
            this.toolStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewGoods)).BeginInit();
            this.PanelAddGood.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumToPurchase)).BeginInit();
            this.SuspendLayout();
            // 
            // ListSuppliers
            // 
            this.ListSuppliers.FormattingEnabled = true;
            this.ListSuppliers.Location = new System.Drawing.Point(12, 84);
            this.ListSuppliers.Name = "ListSuppliers";
            this.ListSuppliers.Size = new System.Drawing.Size(216, 290);
            this.ListSuppliers.TabIndex = 0;
            this.ListSuppliers.SelectedIndexChanged += new System.EventHandler(this.ListSuppliers_SelectedIndexChanged);
            // 
            // toolStrip1
            // 
            this.toolStrip1.ImageScalingSize = new System.Drawing.Size(32, 32);
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator1,
            this.toolStripButton1,
            this.toolStripSeparator2,
            this.toolStripSeparator3,
            this.ButtAdd,
            this.ButtDelete,
            this.toolStripSeparator4,
            this.toolStripSeparator5});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(1097, 39);
            this.toolStrip1.TabIndex = 1;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripButton1
            // 
            this.toolStripButton1.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton1.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton1.Image")));
            this.toolStripButton1.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton1.Name = "toolStripButton1";
            this.toolStripButton1.Size = new System.Drawing.Size(36, 36);
            this.toolStripButton1.Text = "Сохранить листы";
            this.toolStripButton1.Click += new System.EventHandler(this.toolStripButton1_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 39);
            // 
            // ButtAdd
            // 
            this.ButtAdd.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtAdd.Image = ((System.Drawing.Image)(resources.GetObject("ButtAdd.Image")));
            this.ButtAdd.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtAdd.Name = "ButtAdd";
            this.ButtAdd.Size = new System.Drawing.Size(36, 36);
            this.ButtAdd.Text = "Добавить";
            this.ButtAdd.Click += new System.EventHandler(this.ButtAdd_Click);
            // 
            // ButtDelete
            // 
            this.ButtDelete.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtDelete.Enabled = false;
            this.ButtDelete.Image = ((System.Drawing.Image)(resources.GetObject("ButtDelete.Image")));
            this.ButtDelete.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtDelete.Name = "ButtDelete";
            this.ButtDelete.Size = new System.Drawing.Size(36, 36);
            this.ButtDelete.Text = "Удалить";
            this.ButtDelete.Click += new System.EventHandler(this.ButtDelete_Click);
            // 
            // toolStripSeparator4
            // 
            this.toolStripSeparator4.Name = "toolStripSeparator4";
            this.toolStripSeparator4.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator5
            // 
            this.toolStripSeparator5.Name = "toolStripSeparator5";
            this.toolStripSeparator5.Size = new System.Drawing.Size(6, 39);
            // 
            // DataGridViewGoods
            // 
            this.DataGridViewGoods.AllowUserToAddRows = false;
            this.DataGridViewGoods.AllowUserToDeleteRows = false;
            this.DataGridViewGoods.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewGoods.Location = new System.Drawing.Point(234, 42);
            this.DataGridViewGoods.Name = "DataGridViewGoods";
            this.DataGridViewGoods.ReadOnly = true;
            this.DataGridViewGoods.Size = new System.Drawing.Size(851, 302);
            this.DataGridViewGoods.TabIndex = 4;
            this.DataGridViewGoods.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewGoods_CellStateChanged);
            // 
            // PanelAddGood
            // 
            this.PanelAddGood.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelAddGood.Controls.Add(this.ButtCancel);
            this.PanelAddGood.Controls.Add(this.ButtAddOK);
            this.PanelAddGood.Controls.Add(this.label4);
            this.PanelAddGood.Controls.Add(this.label3);
            this.PanelAddGood.Controls.Add(this.label2);
            this.PanelAddGood.Controls.Add(this.TxtPrice);
            this.PanelAddGood.Controls.Add(this.label1);
            this.PanelAddGood.Controls.Add(this.ComboSuppliers);
            this.PanelAddGood.Controls.Add(this.ComboGoods);
            this.PanelAddGood.Controls.Add(this.NumToPurchase);
            this.PanelAddGood.Location = new System.Drawing.Point(253, 80);
            this.PanelAddGood.Name = "PanelAddGood";
            this.PanelAddGood.Size = new System.Drawing.Size(671, 133);
            this.PanelAddGood.TabIndex = 5;
            this.PanelAddGood.Visible = false;
            // 
            // ButtCancel
            // 
            this.ButtCancel.Location = new System.Drawing.Point(529, 99);
            this.ButtCancel.Name = "ButtCancel";
            this.ButtCancel.Size = new System.Drawing.Size(126, 23);
            this.ButtCancel.TabIndex = 9;
            this.ButtCancel.Text = "Отмена";
            this.ButtCancel.UseVisualStyleBackColor = true;
            this.ButtCancel.Click += new System.EventHandler(this.ButtCancel_Click);
            // 
            // ButtAddOK
            // 
            this.ButtAddOK.Location = new System.Drawing.Point(16, 99);
            this.ButtAddOK.Name = "ButtAddOK";
            this.ButtAddOK.Size = new System.Drawing.Size(507, 23);
            this.ButtAddOK.TabIndex = 8;
            this.ButtAddOK.Text = "Добавить";
            this.ButtAddOK.UseVisualStyleBackColor = true;
            this.ButtAddOK.Click += new System.EventHandler(this.ButtAddOK_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(13, 23);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(38, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Товар";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 49);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(41, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Кол-во";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(13, 76);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(33, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Цена";
            // 
            // TxtPrice
            // 
            this.TxtPrice.Location = new System.Drawing.Point(63, 73);
            this.TxtPrice.Name = "TxtPrice";
            this.TxtPrice.Size = new System.Drawing.Size(592, 20);
            this.TxtPrice.TabIndex = 4;
            this.TxtPrice.Text = "0";
            this.TxtPrice.TextChanged += new System.EventHandler(this.TxtPrice_TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(179, 49);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(65, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Поставщик";
            // 
            // ComboSuppliers
            // 
            this.ComboSuppliers.FormattingEnabled = true;
            this.ComboSuppliers.Location = new System.Drawing.Point(250, 46);
            this.ComboSuppliers.Name = "ComboSuppliers";
            this.ComboSuppliers.Size = new System.Drawing.Size(405, 21);
            this.ComboSuppliers.TabIndex = 2;
            // 
            // ComboGoods
            // 
            this.ComboGoods.FormattingEnabled = true;
            this.ComboGoods.Location = new System.Drawing.Point(63, 20);
            this.ComboGoods.Name = "ComboGoods";
            this.ComboGoods.Size = new System.Drawing.Size(592, 21);
            this.ComboGoods.TabIndex = 1;
            this.ComboGoods.SelectedIndexChanged += new System.EventHandler(this.ComboGoods_SelectedIndexChanged);
            // 
            // NumToPurchase
            // 
            this.NumToPurchase.Location = new System.Drawing.Point(63, 47);
            this.NumToPurchase.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.NumToPurchase.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.NumToPurchase.Name = "NumToPurchase";
            this.NumToPurchase.Size = new System.Drawing.Size(110, 20);
            this.NumToPurchase.TabIndex = 0;
            this.NumToPurchase.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.NumToPurchase.ValueChanged += new System.EventHandler(this.NumToPurchase_ValueChanged);
            // 
            // CheckAllSupliers
            // 
            this.CheckAllSupliers.AutoSize = true;
            this.CheckAllSupliers.Location = new System.Drawing.Point(25, 61);
            this.CheckAllSupliers.Name = "CheckAllSupliers";
            this.CheckAllSupliers.Size = new System.Drawing.Size(110, 17);
            this.CheckAllSupliers.TabIndex = 6;
            this.CheckAllSupliers.Text = "Все поставщики";
            this.CheckAllSupliers.UseVisualStyleBackColor = true;
            this.CheckAllSupliers.CheckedChanged += new System.EventHandler(this.CheckAllSupliers_CheckedChanged);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.label5.Location = new System.Drawing.Point(886, 353);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(93, 13);
            this.label5.TabIndex = 7;
            this.label5.Text = "Общая сумма:";
            // 
            // TxtTotal
            // 
            this.TxtTotal.Location = new System.Drawing.Point(985, 350);
            this.TxtTotal.Name = "TxtTotal";
            this.TxtTotal.ReadOnly = true;
            this.TxtTotal.Size = new System.Drawing.Size(100, 20);
            this.TxtTotal.TabIndex = 8;
            // 
            // TimePickerPurchaseTo
            // 
            this.TimePickerPurchaseTo.Location = new System.Drawing.Point(149, 9);
            this.TimePickerPurchaseTo.Name = "TimePickerPurchaseTo";
            this.TimePickerPurchaseTo.Size = new System.Drawing.Size(200, 20);
            this.TimePickerPurchaseTo.TabIndex = 9;
            // 
            // PurchaseListsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1097, 386);
            this.Controls.Add(this.TimePickerPurchaseTo);
            this.Controls.Add(this.TxtTotal);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.CheckAllSupliers);
            this.Controls.Add(this.PanelAddGood);
            this.Controls.Add(this.DataGridViewGoods);
            this.Controls.Add(this.toolStrip1);
            this.Controls.Add(this.ListSuppliers);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "PurchaseListsForm";
            this.Text = "Закупочный лист";
            this.Resize += new System.EventHandler(this.PurchaseListsForm_Resize);
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewGoods)).EndInit();
            this.PanelAddGood.ResumeLayout(false);
            this.PanelAddGood.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumToPurchase)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox ListSuppliers;
        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.DataGridView DataGridViewGoods;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripButton toolStripButton1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripButton ButtAdd;
        private System.Windows.Forms.ToolStripButton ButtDelete;
        private System.Windows.Forms.Panel PanelAddGood;
        private System.Windows.Forms.NumericUpDown NumToPurchase;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox ComboSuppliers;
        private System.Windows.Forms.ComboBox ComboGoods;
        private System.Windows.Forms.Button ButtAddOK;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox TxtPrice;
        private System.Windows.Forms.Button ButtCancel;
        private System.Windows.Forms.CheckBox CheckAllSupliers;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator4;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator5;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox TxtTotal;
        private System.Windows.Forms.DateTimePicker TimePickerPurchaseTo;
    }
}