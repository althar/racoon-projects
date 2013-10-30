namespace OwlBusinessStudio.Settings
{
    partial class DiscountsForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DiscountsForm));
            this.DataGridTab = new System.Windows.Forms.DataGridView();
            this.NumPrice = new System.Windows.Forms.NumericUpDown();
            this.NumDiscount = new System.Windows.Forms.NumericUpDown();
            this.ButtSave = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.ButtClear = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridTab)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumPrice)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumDiscount)).BeginInit();
            this.SuspendLayout();
            // 
            // DataGridTab
            // 
            this.DataGridTab.AllowUserToAddRows = false;
            this.DataGridTab.AllowUserToDeleteRows = false;
            this.DataGridTab.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridTab.Location = new System.Drawing.Point(12, 88);
            this.DataGridTab.Name = "DataGridTab";
            this.DataGridTab.ReadOnly = true;
            this.DataGridTab.Size = new System.Drawing.Size(299, 186);
            this.DataGridTab.TabIndex = 0;
            // 
            // NumPrice
            // 
            this.NumPrice.Location = new System.Drawing.Point(95, 12);
            this.NumPrice.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumPrice.Minimum = new decimal(new int[] {
            100,
            0,
            0,
            0});
            this.NumPrice.Name = "NumPrice";
            this.NumPrice.Size = new System.Drawing.Size(177, 20);
            this.NumPrice.TabIndex = 1;
            this.NumPrice.Value = new decimal(new int[] {
            1000,
            0,
            0,
            0});
            // 
            // NumDiscount
            // 
            this.NumDiscount.Location = new System.Drawing.Point(95, 38);
            this.NumDiscount.Name = "NumDiscount";
            this.NumDiscount.Size = new System.Drawing.Size(177, 20);
            this.NumDiscount.TabIndex = 2;
            this.NumDiscount.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // ButtSave
            // 
            this.ButtSave.Location = new System.Drawing.Point(117, 64);
            this.ButtSave.Name = "ButtSave";
            this.ButtSave.Size = new System.Drawing.Size(194, 23);
            this.ButtSave.TabIndex = 3;
            this.ButtSave.Text = "Сохранить";
            this.ButtSave.UseVisualStyleBackColor = true;
            this.ButtSave.Click += new System.EventHandler(this.ButtSave_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 14);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(72, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Цена заказа";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 40);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(44, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Скидка";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(278, 14);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(27, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "руб.";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(278, 40);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(15, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "%";
            // 
            // ButtClear
            // 
            this.ButtClear.Location = new System.Drawing.Point(12, 64);
            this.ButtClear.Name = "ButtClear";
            this.ButtClear.Size = new System.Drawing.Size(99, 23);
            this.ButtClear.TabIndex = 8;
            this.ButtClear.Text = "Очистить";
            this.ButtClear.UseVisualStyleBackColor = true;
            this.ButtClear.Click += new System.EventHandler(this.ButtClear_Click);
            // 
            // DiscountsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(320, 286);
            this.Controls.Add(this.ButtClear);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.ButtSave);
            this.Controls.Add(this.NumDiscount);
            this.Controls.Add(this.NumPrice);
            this.Controls.Add(this.DataGridTab);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "DiscountsForm";
            this.Text = "Скидки";
            ((System.ComponentModel.ISupportInitialize)(this.DataGridTab)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumPrice)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumDiscount)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView DataGridTab;
        private System.Windows.Forms.NumericUpDown NumPrice;
        private System.Windows.Forms.NumericUpDown NumDiscount;
        private System.Windows.Forms.Button ButtSave;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button ButtClear;
    }
}