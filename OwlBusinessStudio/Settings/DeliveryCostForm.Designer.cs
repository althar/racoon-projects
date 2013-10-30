namespace OwlBusinessStudio.Settings
{
    partial class DeliveryCostForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DeliveryCostForm));
            this.NumCostSeparator = new System.Windows.Forms.NumericUpDown();
            this.TxtDistance = new System.Windows.Forms.TextBox();
            this.NumCheapOrderDeliveryCost = new System.Windows.Forms.NumericUpDown();
            this.NumExpensiveOrderDeliveryCost = new System.Windows.Forms.NumericUpDown();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.DataGridTable = new System.Windows.Forms.DataGridView();
            this.ButtClear = new System.Windows.Forms.Button();
            this.ButtSave = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.NumCostSeparator)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumCheapOrderDeliveryCost)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumExpensiveOrderDeliveryCost)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridTable)).BeginInit();
            this.SuspendLayout();
            // 
            // NumCostSeparator
            // 
            this.NumCostSeparator.Location = new System.Drawing.Point(210, 37);
            this.NumCostSeparator.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumCostSeparator.Minimum = new decimal(new int[] {
            10,
            0,
            0,
            0});
            this.NumCostSeparator.Name = "NumCostSeparator";
            this.NumCostSeparator.Size = new System.Drawing.Size(317, 20);
            this.NumCostSeparator.TabIndex = 0;
            this.NumCostSeparator.Value = new decimal(new int[] {
            3000,
            0,
            0,
            0});
            // 
            // TxtDistance
            // 
            this.TxtDistance.Location = new System.Drawing.Point(210, 11);
            this.TxtDistance.Name = "TxtDistance";
            this.TxtDistance.Size = new System.Drawing.Size(317, 20);
            this.TxtDistance.TabIndex = 1;
            // 
            // NumCheapOrderDeliveryCost
            // 
            this.NumCheapOrderDeliveryCost.Location = new System.Drawing.Point(210, 63);
            this.NumCheapOrderDeliveryCost.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumCheapOrderDeliveryCost.Name = "NumCheapOrderDeliveryCost";
            this.NumCheapOrderDeliveryCost.Size = new System.Drawing.Size(317, 20);
            this.NumCheapOrderDeliveryCost.TabIndex = 2;
            this.NumCheapOrderDeliveryCost.Value = new decimal(new int[] {
            300,
            0,
            0,
            0});
            // 
            // NumExpensiveOrderDeliveryCost
            // 
            this.NumExpensiveOrderDeliveryCost.Location = new System.Drawing.Point(210, 89);
            this.NumExpensiveOrderDeliveryCost.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumExpensiveOrderDeliveryCost.Name = "NumExpensiveOrderDeliveryCost";
            this.NumExpensiveOrderDeliveryCost.Size = new System.Drawing.Size(317, 20);
            this.NumExpensiveOrderDeliveryCost.TabIndex = 3;
            this.NumExpensiveOrderDeliveryCost.Value = new decimal(new int[] {
            200,
            0,
            0,
            0});
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(9, 14);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(117, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Расстояние доставки";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(9, 91);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(190, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Стоимость свыше переходной цены";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(9, 65);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(188, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Стоимость менее переходной цены";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(9, 39);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(134, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Переходная цена заказа";
            // 
            // DataGridTable
            // 
            this.DataGridTable.AllowUserToAddRows = false;
            this.DataGridTable.AllowUserToDeleteRows = false;
            this.DataGridTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridTable.Location = new System.Drawing.Point(12, 149);
            this.DataGridTable.Name = "DataGridTable";
            this.DataGridTable.ReadOnly = true;
            this.DataGridTable.Size = new System.Drawing.Size(515, 150);
            this.DataGridTable.TabIndex = 8;
            // 
            // ButtClear
            // 
            this.ButtClear.Location = new System.Drawing.Point(12, 120);
            this.ButtClear.Name = "ButtClear";
            this.ButtClear.Size = new System.Drawing.Size(131, 23);
            this.ButtClear.TabIndex = 9;
            this.ButtClear.Text = "Удалить все";
            this.ButtClear.UseVisualStyleBackColor = true;
            this.ButtClear.Click += new System.EventHandler(this.ButtClear_Click);
            // 
            // ButtSave
            // 
            this.ButtSave.Location = new System.Drawing.Point(149, 120);
            this.ButtSave.Name = "ButtSave";
            this.ButtSave.Size = new System.Drawing.Size(376, 23);
            this.ButtSave.TabIndex = 10;
            this.ButtSave.Text = "Добавить";
            this.ButtSave.UseVisualStyleBackColor = true;
            this.ButtSave.Click += new System.EventHandler(this.ButtSave_Click);
            // 
            // DeliveryCostForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(539, 310);
            this.Controls.Add(this.ButtSave);
            this.Controls.Add(this.ButtClear);
            this.Controls.Add(this.DataGridTable);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.NumExpensiveOrderDeliveryCost);
            this.Controls.Add(this.NumCheapOrderDeliveryCost);
            this.Controls.Add(this.TxtDistance);
            this.Controls.Add(this.NumCostSeparator);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "DeliveryCostForm";
            this.Text = "Таблица стоимости доставки";
            ((System.ComponentModel.ISupportInitialize)(this.NumCostSeparator)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumCheapOrderDeliveryCost)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumExpensiveOrderDeliveryCost)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridTable)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.NumericUpDown NumCostSeparator;
        private System.Windows.Forms.TextBox TxtDistance;
        private System.Windows.Forms.NumericUpDown NumCheapOrderDeliveryCost;
        private System.Windows.Forms.NumericUpDown NumExpensiveOrderDeliveryCost;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.DataGridView DataGridTable;
        private System.Windows.Forms.Button ButtClear;
        private System.Windows.Forms.Button ButtSave;
    }
}