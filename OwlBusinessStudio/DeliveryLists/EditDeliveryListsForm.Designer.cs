namespace OwlBusinessStudio.DeliveryLists
{
    partial class EditDeliveryListsForm
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(EditDeliveryListsForm));
            this.ListDrivers = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.DataGridViewDeliveryLists = new System.Windows.Forms.DataGridView();
            this.ContextMenuDrivers = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.ButtDrivers = new System.Windows.Forms.ToolStripMenuItem();
            this.PickUp = new System.Windows.Forms.PictureBox();
            this.PicDown = new System.Windows.Forms.PictureBox();
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.toolStripStatusLabel1 = new System.Windows.Forms.ToolStripStatusLabel();
            this.toolStripStatusLabel2 = new System.Windows.Forms.ToolStripStatusLabel();
            this.toolStripStatusLabel3 = new System.Windows.Forms.ToolStripStatusLabel();
            this.toolStripStatusLabel4 = new System.Windows.Forms.ToolStripStatusLabel();
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.ButtSave = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtRefresh = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewDeliveryLists)).BeginInit();
            this.ContextMenuDrivers.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.PickUp)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.PicDown)).BeginInit();
            this.statusStrip1.SuspendLayout();
            this.toolStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // ListDrivers
            // 
            this.ListDrivers.FormattingEnabled = true;
            this.ListDrivers.Location = new System.Drawing.Point(12, 87);
            this.ListDrivers.Name = "ListDrivers";
            this.ListDrivers.Size = new System.Drawing.Size(207, 290);
            this.ListDrivers.TabIndex = 0;
            this.ListDrivers.SelectedIndexChanged += new System.EventHandler(this.ListDrivers_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 61);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(55, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Водители";
            // 
            // DataGridViewDeliveryLists
            // 
            this.DataGridViewDeliveryLists.AllowUserToAddRows = false;
            this.DataGridViewDeliveryLists.AllowUserToDeleteRows = false;
            this.DataGridViewDeliveryLists.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewDeliveryLists.ContextMenuStrip = this.ContextMenuDrivers;
            this.DataGridViewDeliveryLists.Location = new System.Drawing.Point(235, 61);
            this.DataGridViewDeliveryLists.MultiSelect = false;
            this.DataGridViewDeliveryLists.Name = "DataGridViewDeliveryLists";
            this.DataGridViewDeliveryLists.ReadOnly = true;
            this.DataGridViewDeliveryLists.Size = new System.Drawing.Size(694, 316);
            this.DataGridViewDeliveryLists.TabIndex = 2;
            this.DataGridViewDeliveryLists.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewDeliveryLists_CellStateChanged);
            this.DataGridViewDeliveryLists.ColumnAdded += new System.Windows.Forms.DataGridViewColumnEventHandler(this.DataGridViewDeliveryLists_ColumnAdded);
            this.DataGridViewDeliveryLists.RowStateChanged += new System.Windows.Forms.DataGridViewRowStateChangedEventHandler(this.DataGridViewDeliveryLists_RowStateChanged);
            // 
            // ContextMenuDrivers
            // 
            this.ContextMenuDrivers.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtDrivers});
            this.ContextMenuDrivers.Name = "ContextMenuDrivers";
            this.ContextMenuDrivers.Size = new System.Drawing.Size(183, 26);
            // 
            // ButtDrivers
            // 
            this.ButtDrivers.Name = "ButtDrivers";
            this.ButtDrivers.Size = new System.Drawing.Size(182, 22);
            this.ButtDrivers.Text = "Передать водителю";
            // 
            // PickUp
            // 
            this.PickUp.Cursor = System.Windows.Forms.Cursors.Hand;
            this.PickUp.Image = ((System.Drawing.Image)(resources.GetObject("PickUp.Image")));
            this.PickUp.InitialImage = ((System.Drawing.Image)(resources.GetObject("PickUp.InitialImage")));
            this.PickUp.Location = new System.Drawing.Point(935, 112);
            this.PickUp.Name = "PickUp";
            this.PickUp.Size = new System.Drawing.Size(59, 70);
            this.PickUp.TabIndex = 3;
            this.PickUp.TabStop = false;
            this.PickUp.Click += new System.EventHandler(this.PickUp_Click);
            // 
            // PicDown
            // 
            this.PicDown.Cursor = System.Windows.Forms.Cursors.Hand;
            this.PicDown.Image = ((System.Drawing.Image)(resources.GetObject("PicDown.Image")));
            this.PicDown.Location = new System.Drawing.Point(935, 270);
            this.PicDown.Name = "PicDown";
            this.PicDown.Size = new System.Drawing.Size(59, 65);
            this.PicDown.TabIndex = 4;
            this.PicDown.TabStop = false;
            this.PicDown.Click += new System.EventHandler(this.PicDown_Click);
            // 
            // statusStrip1
            // 
            this.statusStrip1.AutoSize = false;
            this.statusStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.statusStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripStatusLabel1,
            this.toolStripStatusLabel2,
            this.toolStripStatusLabel3,
            this.toolStripStatusLabel4});
            this.statusStrip1.Location = new System.Drawing.Point(0, 424);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.Size = new System.Drawing.Size(1019, 24);
            this.statusStrip1.TabIndex = 6;
            this.statusStrip1.Text = "statusStrip1";
            // 
            // toolStripStatusLabel1
            // 
            this.toolStripStatusLabel1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(209)))), ((int)(((byte)(240)))), ((int)(((byte)(252)))));
            this.toolStripStatusLabel1.BorderSides = ((System.Windows.Forms.ToolStripStatusLabelBorderSides)((((System.Windows.Forms.ToolStripStatusLabelBorderSides.Left | System.Windows.Forms.ToolStripStatusLabelBorderSides.Top)
                        | System.Windows.Forms.ToolStripStatusLabelBorderSides.Right)
                        | System.Windows.Forms.ToolStripStatusLabelBorderSides.Bottom)));
            this.toolStripStatusLabel1.Name = "toolStripStatusLabel1";
            this.toolStripStatusLabel1.Size = new System.Drawing.Size(38, 19);
            this.toolStripStatusLabel1.Text = "         ";
            // 
            // toolStripStatusLabel2
            // 
            this.toolStripStatusLabel2.Name = "toolStripStatusLabel2";
            this.toolStripStatusLabel2.Size = new System.Drawing.Size(151, 19);
            this.toolStripStatusLabel2.Text = " - Распределенные заказы";
            // 
            // toolStripStatusLabel3
            // 
            this.toolStripStatusLabel3.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(252)))), ((int)(((byte)(248)))), ((int)(((byte)(209)))));
            this.toolStripStatusLabel3.BorderSides = ((System.Windows.Forms.ToolStripStatusLabelBorderSides)((((System.Windows.Forms.ToolStripStatusLabelBorderSides.Left | System.Windows.Forms.ToolStripStatusLabelBorderSides.Top)
                        | System.Windows.Forms.ToolStripStatusLabelBorderSides.Right)
                        | System.Windows.Forms.ToolStripStatusLabelBorderSides.Bottom)));
            this.toolStripStatusLabel3.Name = "toolStripStatusLabel3";
            this.toolStripStatusLabel3.Size = new System.Drawing.Size(41, 19);
            this.toolStripStatusLabel3.Text = "          ";
            // 
            // toolStripStatusLabel4
            // 
            this.toolStripStatusLabel4.Name = "toolStripStatusLabel4";
            this.toolStripStatusLabel4.Size = new System.Drawing.Size(95, 19);
            this.toolStripStatusLabel4.Text = " - Новые заказы";
            // 
            // toolStrip1
            // 
            this.toolStrip1.ImageScalingSize = new System.Drawing.Size(32, 32);
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtSave,
            this.toolStripSeparator1,
            this.toolStripSeparator2,
            this.ButtRefresh,
            this.toolStripSeparator3});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(1019, 39);
            this.toolStrip1.TabIndex = 7;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // ButtSave
            // 
            this.ButtSave.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtSave.Image = ((System.Drawing.Image)(resources.GetObject("ButtSave.Image")));
            this.ButtSave.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtSave.Name = "ButtSave";
            this.ButtSave.Size = new System.Drawing.Size(36, 36);
            this.ButtSave.Text = "Сохранить";
            this.ButtSave.Click += new System.EventHandler(this.ButtSave_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 39);
            // 
            // ButtRefresh
            // 
            this.ButtRefresh.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtRefresh.Image = ((System.Drawing.Image)(resources.GetObject("ButtRefresh.Image")));
            this.ButtRefresh.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtRefresh.Name = "ButtRefresh";
            this.ButtRefresh.Size = new System.Drawing.Size(36, 36);
            this.ButtRefresh.Text = "Обновить";
            this.ButtRefresh.Click += new System.EventHandler(this.ButtRefresh_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 39);
            // 
            // EditDeliveryListsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1019, 448);
            this.Controls.Add(this.toolStrip1);
            this.Controls.Add(this.statusStrip1);
            this.Controls.Add(this.PicDown);
            this.Controls.Add(this.PickUp);
            this.Controls.Add(this.DataGridViewDeliveryLists);
            this.Controls.Add(this.ListDrivers);
            this.Controls.Add(this.label1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "EditDeliveryListsForm";
            this.Text = "Составление листов развоза";
            this.Activated += new System.EventHandler(this.EditDeliveryListsForm_Activated);
            this.Resize += new System.EventHandler(this.EditDeliveryListsForm_Resize);
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewDeliveryLists)).EndInit();
            this.ContextMenuDrivers.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.PickUp)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.PicDown)).EndInit();
            this.statusStrip1.ResumeLayout(false);
            this.statusStrip1.PerformLayout();
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox ListDrivers;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView DataGridViewDeliveryLists;
        private System.Windows.Forms.PictureBox PickUp;
        private System.Windows.Forms.PictureBox PicDown;
        private System.Windows.Forms.ContextMenuStrip ContextMenuDrivers;
        private System.Windows.Forms.ToolStripMenuItem ButtDrivers;
        private System.Windows.Forms.StatusStrip statusStrip1;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel1;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel2;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel3;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel4;
        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.ToolStripButton ButtSave;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripButton ButtRefresh;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
    }
}