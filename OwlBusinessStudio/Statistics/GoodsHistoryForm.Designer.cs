namespace OwlBusinessStudio.Statistics
{
    partial class GoodsHistoryForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(GoodsHistoryForm));
            this.CheckPurchase = new System.Windows.Forms.CheckBox();
            this.CheckAcceptGoods = new System.Windows.Forms.CheckBox();
            this.DataGridGoodsHistory = new System.Windows.Forms.DataGridView();
            this.TimePickerFrom = new System.Windows.Forms.DateTimePicker();
            this.TimePickerTo = new System.Windows.Forms.DateTimePicker();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.ButtOK = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridGoodsHistory)).BeginInit();
            this.SuspendLayout();
            // 
            // CheckPurchase
            // 
            this.CheckPurchase.AutoSize = true;
            this.CheckPurchase.Location = new System.Drawing.Point(12, 12);
            this.CheckPurchase.Name = "CheckPurchase";
            this.CheckPurchase.Size = new System.Drawing.Size(68, 17);
            this.CheckPurchase.TabIndex = 0;
            this.CheckPurchase.Text = "Закупки";
            this.CheckPurchase.UseVisualStyleBackColor = true;
            // 
            // CheckAcceptGoods
            // 
            this.CheckAcceptGoods.AutoSize = true;
            this.CheckAcceptGoods.Location = new System.Drawing.Point(12, 35);
            this.CheckAcceptGoods.Name = "CheckAcceptGoods";
            this.CheckAcceptGoods.Size = new System.Drawing.Size(187, 17);
            this.CheckAcceptGoods.TabIndex = 1;
            this.CheckAcceptGoods.Text = "Списание остатков в конце дня";
            this.CheckAcceptGoods.UseVisualStyleBackColor = true;
            // 
            // DataGridGoodsHistory
            // 
            this.DataGridGoodsHistory.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridGoodsHistory.Location = new System.Drawing.Point(12, 58);
            this.DataGridGoodsHistory.Name = "DataGridGoodsHistory";
            this.DataGridGoodsHistory.Size = new System.Drawing.Size(880, 420);
            this.DataGridGoodsHistory.TabIndex = 2;
            // 
            // TimePickerFrom
            // 
            this.TimePickerFrom.Location = new System.Drawing.Point(283, 7);
            this.TimePickerFrom.Name = "TimePickerFrom";
            this.TimePickerFrom.Size = new System.Drawing.Size(200, 20);
            this.TimePickerFrom.TabIndex = 3;
            // 
            // TimePickerTo
            // 
            this.TimePickerTo.Location = new System.Drawing.Point(283, 32);
            this.TimePickerTo.Name = "TimePickerTo";
            this.TimePickerTo.Size = new System.Drawing.Size(200, 20);
            this.TimePickerTo.TabIndex = 4;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(262, 11);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(14, 13);
            this.label1.TabIndex = 5;
            this.label1.Text = "С";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(261, 36);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(21, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "По";
            // 
            // ButtOK
            // 
            this.ButtOK.Location = new System.Drawing.Point(489, 6);
            this.ButtOK.Name = "ButtOK";
            this.ButtOK.Size = new System.Drawing.Size(401, 46);
            this.ButtOK.TabIndex = 7;
            this.ButtOK.Text = "Выбрать";
            this.ButtOK.UseVisualStyleBackColor = true;
            this.ButtOK.Click += new System.EventHandler(this.ButtOK_Click);
            // 
            // GoodsHistoryForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(902, 482);
            this.Controls.Add(this.ButtOK);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.TimePickerTo);
            this.Controls.Add(this.TimePickerFrom);
            this.Controls.Add(this.DataGridGoodsHistory);
            this.Controls.Add(this.CheckAcceptGoods);
            this.Controls.Add(this.CheckPurchase);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "GoodsHistoryForm";
            this.Text = "История товаров";
            this.Resize += new System.EventHandler(this.GoodsHistoryForm_Resize);
            ((System.ComponentModel.ISupportInitialize)(this.DataGridGoodsHistory)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.CheckBox CheckPurchase;
        private System.Windows.Forms.CheckBox CheckAcceptGoods;
        private System.Windows.Forms.DataGridView DataGridGoodsHistory;
        private System.Windows.Forms.DateTimePicker TimePickerFrom;
        private System.Windows.Forms.DateTimePicker TimePickerTo;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button ButtOK;
    }
}