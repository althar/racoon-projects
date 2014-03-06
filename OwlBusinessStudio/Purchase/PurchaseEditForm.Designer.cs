namespace OwlBusinessStudio.Purchase
{
    partial class PurchaseEditForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(PurchaseEditForm));
            this.DataGridViewPurchase = new System.Windows.Forms.DataGridView();
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.ButtAccept = new System.Windows.Forms.ToolStripButton();
            this.ButtDecline = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtEdit = new System.Windows.Forms.ToolStripButton();
            this.ButtAddGood = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtProcess = new System.Windows.Forms.ToolStripButton();
            this.PanelEditPurchase = new System.Windows.Forms.Panel();
            this.label1 = new System.Windows.Forms.Label();
            this.TxtDescription = new System.Windows.Forms.TextBox();
            this.TxtPurchaseEditTotal = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.ButtOKPurchaseEdit = new System.Windows.Forms.Button();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.TxtPurchaseEditPrice = new System.Windows.Forms.TextBox();
            this.NumPurchaseEditCount = new System.Windows.Forms.NumericUpDown();
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.LabelPurchaseProgress = new System.Windows.Forms.ToolStripStatusLabel();
            this.PanelAddGood = new System.Windows.Forms.Panel();
            this.button2 = new System.Windows.Forms.Button();
            this.TxtSupplier = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.CheckForOrders = new System.Windows.Forms.CheckBox();
            this.label9 = new System.Windows.Forms.Label();
            this.ComboLists = new System.Windows.Forms.ComboBox();
            this.ButtCloseAddGoodPanel = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.ComboGoods = new System.Windows.Forms.ComboBox();
            this.button1 = new System.Windows.Forms.Button();
            this.label8 = new System.Windows.Forms.Label();
            this.NumNewGoodCount = new System.Windows.Forms.NumericUpDown();
            this.label3 = new System.Windows.Forms.Label();
            this.LabelTotal = new System.Windows.Forms.Label();
            this.OpenPurchaseImportDialog = new System.Windows.Forms.OpenFileDialog();
            this.ProgressLabel = new System.Windows.Forms.ToolStripLabel();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewPurchase)).BeginInit();
            this.toolStrip1.SuspendLayout();
            this.PanelEditPurchase.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumPurchaseEditCount)).BeginInit();
            this.statusStrip1.SuspendLayout();
            this.PanelAddGood.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumNewGoodCount)).BeginInit();
            this.SuspendLayout();
            // 
            // DataGridViewPurchase
            // 
            this.DataGridViewPurchase.AllowUserToAddRows = false;
            this.DataGridViewPurchase.AllowUserToDeleteRows = false;
            this.DataGridViewPurchase.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewPurchase.Location = new System.Drawing.Point(12, 34);
            this.DataGridViewPurchase.Name = "DataGridViewPurchase";
            this.DataGridViewPurchase.ReadOnly = true;
            this.DataGridViewPurchase.Size = new System.Drawing.Size(1204, 357);
            this.DataGridViewPurchase.TabIndex = 0;
            this.DataGridViewPurchase.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DataGridViewPurchase_CellClick);
            this.DataGridViewPurchase.CellDoubleClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DataGridViewPurchase_CellDoubleClick);
            this.DataGridViewPurchase.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewPurchase_CellStateChanged);
            this.DataGridViewPurchase.ColumnHeaderMouseDoubleClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.DataGridViewPurchase_ColumnHeaderMouseDoubleClick);
            // 
            // toolStrip1
            // 
            this.toolStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtAccept,
            this.ButtDecline,
            this.toolStripSeparator1,
            this.ButtEdit,
            this.ButtAddGood,
            this.toolStripSeparator2,
            this.toolStripSeparator3,
            this.ButtProcess,
            this.ProgressLabel});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(1228, 31);
            this.toolStrip1.TabIndex = 1;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // ButtAccept
            // 
            this.ButtAccept.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtAccept.Image = ((System.Drawing.Image)(resources.GetObject("ButtAccept.Image")));
            this.ButtAccept.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtAccept.Name = "ButtAccept";
            this.ButtAccept.Size = new System.Drawing.Size(28, 28);
            this.ButtAccept.Text = "Утвердить";
            this.ButtAccept.Click += new System.EventHandler(this.ButtAccept_Click);
            // 
            // ButtDecline
            // 
            this.ButtDecline.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtDecline.Image = ((System.Drawing.Image)(resources.GetObject("ButtDecline.Image")));
            this.ButtDecline.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtDecline.Name = "ButtDecline";
            this.ButtDecline.Size = new System.Drawing.Size(28, 28);
            this.ButtDecline.Text = "Отменить";
            this.ButtDecline.Click += new System.EventHandler(this.ButtDecline_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 31);
            // 
            // ButtEdit
            // 
            this.ButtEdit.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtEdit.Enabled = false;
            this.ButtEdit.Image = ((System.Drawing.Image)(resources.GetObject("ButtEdit.Image")));
            this.ButtEdit.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtEdit.Name = "ButtEdit";
            this.ButtEdit.Size = new System.Drawing.Size(28, 28);
            this.ButtEdit.Text = "Изменить";
            this.ButtEdit.Click += new System.EventHandler(this.ButtEdit_Click);
            // 
            // ButtAddGood
            // 
            this.ButtAddGood.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtAddGood.Image = ((System.Drawing.Image)(resources.GetObject("ButtAddGood.Image")));
            this.ButtAddGood.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtAddGood.Name = "ButtAddGood";
            this.ButtAddGood.Size = new System.Drawing.Size(28, 28);
            this.ButtAddGood.Text = "Добавить товар";
            this.ButtAddGood.Click += new System.EventHandler(this.ButtAddGood_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 31);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 31);
            // 
            // ButtProcess
            // 
            this.ButtProcess.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtProcess.Image = ((System.Drawing.Image)(resources.GetObject("ButtProcess.Image")));
            this.ButtProcess.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtProcess.Name = "ButtProcess";
            this.ButtProcess.Size = new System.Drawing.Size(28, 28);
            this.ButtProcess.Text = "Процесс";
            this.ButtProcess.Visible = false;
            // 
            // PanelEditPurchase
            // 
            this.PanelEditPurchase.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelEditPurchase.Controls.Add(this.label1);
            this.PanelEditPurchase.Controls.Add(this.TxtDescription);
            this.PanelEditPurchase.Controls.Add(this.TxtPurchaseEditTotal);
            this.PanelEditPurchase.Controls.Add(this.label6);
            this.PanelEditPurchase.Controls.Add(this.ButtOKPurchaseEdit);
            this.PanelEditPurchase.Controls.Add(this.label5);
            this.PanelEditPurchase.Controls.Add(this.label4);
            this.PanelEditPurchase.Controls.Add(this.TxtPurchaseEditPrice);
            this.PanelEditPurchase.Controls.Add(this.NumPurchaseEditCount);
            this.PanelEditPurchase.Location = new System.Drawing.Point(189, 165);
            this.PanelEditPurchase.Name = "PanelEditPurchase";
            this.PanelEditPurchase.Size = new System.Drawing.Size(500, 138);
            this.PanelEditPurchase.TabIndex = 4;
            this.PanelEditPurchase.Visible = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(3, 44);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(57, 13);
            this.label1.TabIndex = 9;
            this.label1.Text = "Описание";
            // 
            // TxtDescription
            // 
            this.TxtDescription.Location = new System.Drawing.Point(60, 41);
            this.TxtDescription.Multiline = true;
            this.TxtDescription.Name = "TxtDescription";
            this.TxtDescription.Size = new System.Drawing.Size(430, 63);
            this.TxtDescription.TabIndex = 8;
            // 
            // TxtPurchaseEditTotal
            // 
            this.TxtPurchaseEditTotal.Location = new System.Drawing.Point(387, 15);
            this.TxtPurchaseEditTotal.Name = "TxtPurchaseEditTotal";
            this.TxtPurchaseEditTotal.ReadOnly = true;
            this.TxtPurchaseEditTotal.Size = new System.Drawing.Size(103, 20);
            this.TxtPurchaseEditTotal.TabIndex = 6;
            this.TxtPurchaseEditTotal.Text = "0";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(319, 18);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(62, 13);
            this.label6.TabIndex = 7;
            this.label6.Text = "Стоимость";
            // 
            // ButtOKPurchaseEdit
            // 
            this.ButtOKPurchaseEdit.Location = new System.Drawing.Point(3, 110);
            this.ButtOKPurchaseEdit.Name = "ButtOKPurchaseEdit";
            this.ButtOKPurchaseEdit.Size = new System.Drawing.Size(487, 23);
            this.ButtOKPurchaseEdit.TabIndex = 4;
            this.ButtOKPurchaseEdit.Text = "OK";
            this.ButtOKPurchaseEdit.UseVisualStyleBackColor = true;
            this.ButtOKPurchaseEdit.Click += new System.EventHandler(this.ButtOKPurchaseEdit_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(168, 18);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(33, 13);
            this.label5.TabIndex = 3;
            this.label5.Text = "Цена";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(3, 18);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(41, 13);
            this.label4.TabIndex = 2;
            this.label4.Text = "Кол-во";
            // 
            // TxtPurchaseEditPrice
            // 
            this.TxtPurchaseEditPrice.Location = new System.Drawing.Point(207, 15);
            this.TxtPurchaseEditPrice.Name = "TxtPurchaseEditPrice";
            this.TxtPurchaseEditPrice.Size = new System.Drawing.Size(106, 20);
            this.TxtPurchaseEditPrice.TabIndex = 1;
            this.TxtPurchaseEditPrice.Text = "0";
            this.TxtPurchaseEditPrice.TextChanged += new System.EventHandler(this.NumPurchaseEditCount_ValueChanged);
            // 
            // NumPurchaseEditCount
            // 
            this.NumPurchaseEditCount.Location = new System.Drawing.Point(60, 15);
            this.NumPurchaseEditCount.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.NumPurchaseEditCount.Name = "NumPurchaseEditCount";
            this.NumPurchaseEditCount.Size = new System.Drawing.Size(89, 20);
            this.NumPurchaseEditCount.TabIndex = 0;
            this.NumPurchaseEditCount.ValueChanged += new System.EventHandler(this.NumPurchaseEditCount_ValueChanged);
            // 
            // statusStrip1
            // 
            this.statusStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.LabelPurchaseProgress});
            this.statusStrip1.Location = new System.Drawing.Point(0, 394);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.Size = new System.Drawing.Size(1228, 22);
            this.statusStrip1.TabIndex = 5;
            this.statusStrip1.Text = "statusStrip1";
            // 
            // LabelPurchaseProgress
            // 
            this.LabelPurchaseProgress.Name = "LabelPurchaseProgress";
            this.LabelPurchaseProgress.Size = new System.Drawing.Size(0, 17);
            // 
            // PanelAddGood
            // 
            this.PanelAddGood.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelAddGood.Controls.Add(this.button2);
            this.PanelAddGood.Controls.Add(this.TxtSupplier);
            this.PanelAddGood.Controls.Add(this.label7);
            this.PanelAddGood.Controls.Add(this.CheckForOrders);
            this.PanelAddGood.Controls.Add(this.label9);
            this.PanelAddGood.Controls.Add(this.ComboLists);
            this.PanelAddGood.Controls.Add(this.ButtCloseAddGoodPanel);
            this.PanelAddGood.Controls.Add(this.label2);
            this.PanelAddGood.Controls.Add(this.ComboGoods);
            this.PanelAddGood.Controls.Add(this.button1);
            this.PanelAddGood.Controls.Add(this.label8);
            this.PanelAddGood.Controls.Add(this.NumNewGoodCount);
            this.PanelAddGood.Location = new System.Drawing.Point(291, 71);
            this.PanelAddGood.Name = "PanelAddGood";
            this.PanelAddGood.Size = new System.Drawing.Size(661, 130);
            this.PanelAddGood.TabIndex = 10;
            this.PanelAddGood.Visible = false;
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(498, 4);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(158, 46);
            this.button2.TabIndex = 16;
            this.button2.Text = "Импортировать из EXCEL";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.toolStripButton1_Click);
            // 
            // TxtSupplier
            // 
            this.TxtSupplier.Location = new System.Drawing.Point(69, 53);
            this.TxtSupplier.Name = "TxtSupplier";
            this.TxtSupplier.ReadOnly = true;
            this.TxtSupplier.Size = new System.Drawing.Size(423, 20);
            this.TxtSupplier.TabIndex = 15;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(5, 56);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(65, 13);
            this.label7.TabIndex = 14;
            this.label7.Text = "Поставщик";
            // 
            // CheckForOrders
            // 
            this.CheckForOrders.AutoSize = true;
            this.CheckForOrders.Location = new System.Drawing.Point(166, 77);
            this.CheckForOrders.Name = "CheckForOrders";
            this.CheckForOrders.Size = new System.Drawing.Size(87, 17);
            this.CheckForOrders.TabIndex = 13;
            this.CheckForOrders.Text = "Под заказы";
            this.CheckForOrders.UseVisualStyleBackColor = true;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(6, 32);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(32, 13);
            this.label9.TabIndex = 12;
            this.label9.Text = "Лист";
            // 
            // ComboLists
            // 
            this.ComboLists.FormattingEnabled = true;
            this.ComboLists.Location = new System.Drawing.Point(69, 29);
            this.ComboLists.Name = "ComboLists";
            this.ComboLists.Size = new System.Drawing.Size(423, 21);
            this.ComboLists.TabIndex = 11;
            // 
            // ButtCloseAddGoodPanel
            // 
            this.ButtCloseAddGoodPanel.Location = new System.Drawing.Point(403, 102);
            this.ButtCloseAddGoodPanel.Name = "ButtCloseAddGoodPanel";
            this.ButtCloseAddGoodPanel.Size = new System.Drawing.Size(89, 23);
            this.ButtCloseAddGoodPanel.TabIndex = 10;
            this.ButtCloseAddGoodPanel.Text = "Отмена";
            this.ButtCloseAddGoodPanel.UseVisualStyleBackColor = true;
            this.ButtCloseAddGoodPanel.Click += new System.EventHandler(this.ButtCloseAddGoodPanel_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 7);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(38, 13);
            this.label2.TabIndex = 9;
            this.label2.Text = "Товар";
            // 
            // ComboGoods
            // 
            this.ComboGoods.FormattingEnabled = true;
            this.ComboGoods.Location = new System.Drawing.Point(69, 4);
            this.ComboGoods.Name = "ComboGoods";
            this.ComboGoods.Size = new System.Drawing.Size(423, 21);
            this.ComboGoods.TabIndex = 8;
            this.ComboGoods.SelectedValueChanged += new System.EventHandler(this.ComboGoods_SelectedValueChanged);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(5, 102);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(392, 23);
            this.button1.TabIndex = 4;
            this.button1.Text = "Добавить";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(4, 79);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(41, 13);
            this.label8.TabIndex = 2;
            this.label8.Text = "Кол-во";
            // 
            // NumNewGoodCount
            // 
            this.NumNewGoodCount.Location = new System.Drawing.Point(69, 76);
            this.NumNewGoodCount.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.NumNewGoodCount.Name = "NumNewGoodCount";
            this.NumNewGoodCount.Size = new System.Drawing.Size(82, 20);
            this.NumNewGoodCount.TabIndex = 0;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.label3.Location = new System.Drawing.Point(1026, 9);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(75, 13);
            this.label3.TabIndex = 11;
            this.label3.Text = "Стоимость:";
            // 
            // LabelTotal
            // 
            this.LabelTotal.AutoSize = true;
            this.LabelTotal.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.LabelTotal.Location = new System.Drawing.Point(1103, 9);
            this.LabelTotal.Name = "LabelTotal";
            this.LabelTotal.Size = new System.Drawing.Size(14, 13);
            this.LabelTotal.TabIndex = 12;
            this.LabelTotal.Text = "0";
            // 
            // OpenPurchaseImportDialog
            // 
            this.OpenPurchaseImportDialog.Filter = "Excel|*.xls;*.xlsx";
            // 
            // ProgressLabel
            // 
            this.ProgressLabel.Name = "ProgressLabel";
            this.ProgressLabel.Size = new System.Drawing.Size(0, 28);
            // 
            // PurchaseEditForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1228, 416);
            this.Controls.Add(this.LabelTotal);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.PanelAddGood);
            this.Controls.Add(this.statusStrip1);
            this.Controls.Add(this.PanelEditPurchase);
            this.Controls.Add(this.toolStrip1);
            this.Controls.Add(this.DataGridViewPurchase);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "PurchaseEditForm";
            this.Text = "Подтверждение закупки";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.PurchaseEditForm_FormClosing);
            this.Load += new System.EventHandler(this.PurchaseEditForm_Load);
            this.Resize += new System.EventHandler(this.PurchaseEditForm_Resize);
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewPurchase)).EndInit();
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.PanelEditPurchase.ResumeLayout(false);
            this.PanelEditPurchase.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumPurchaseEditCount)).EndInit();
            this.statusStrip1.ResumeLayout(false);
            this.statusStrip1.PerformLayout();
            this.PanelAddGood.ResumeLayout(false);
            this.PanelAddGood.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumNewGoodCount)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView DataGridViewPurchase;
        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.ToolStripButton ButtAccept;
        private System.Windows.Forms.ToolStripButton ButtDecline;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripButton ButtEdit;
        private System.Windows.Forms.Panel PanelEditPurchase;
        private System.Windows.Forms.TextBox TxtPurchaseEditTotal;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button ButtOKPurchaseEdit;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox TxtPurchaseEditPrice;
        private System.Windows.Forms.NumericUpDown NumPurchaseEditCount;
        private System.Windows.Forms.StatusStrip statusStrip1;
        private System.Windows.Forms.ToolStripStatusLabel LabelPurchaseProgress;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripButton ButtProcess;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox TxtDescription;
        private System.Windows.Forms.Panel PanelAddGood;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ComboBox ComboGoods;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.NumericUpDown NumNewGoodCount;
        private System.Windows.Forms.ToolStripButton ButtAddGood;
        private System.Windows.Forms.Button ButtCloseAddGoodPanel;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.ComboBox ComboLists;
        private System.Windows.Forms.CheckBox CheckForOrders;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label LabelTotal;
        private System.Windows.Forms.TextBox TxtSupplier;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.OpenFileDialog OpenPurchaseImportDialog;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.ToolStripLabel ProgressLabel;
    }
}