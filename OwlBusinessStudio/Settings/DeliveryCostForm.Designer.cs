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
            this.NumOrderFrom = new System.Windows.Forms.NumericUpDown();
            this.NumOrderTo = new System.Windows.Forms.NumericUpDown();
            this.NumDeliveryPrice = new System.Windows.Forms.NumericUpDown();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.DataGridTable = new System.Windows.Forms.DataGridView();
            this.ButtClear = new System.Windows.Forms.Button();
            this.ButtSave = new System.Windows.Forms.Button();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.checkboxPickup = new System.Windows.Forms.CheckBox();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.NumDiscount = new System.Windows.Forms.NumericUpDown();
            this.ComboDistance = new System.Windows.Forms.ComboBox();
            this.ButtDelete = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.NumOrderFrom)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumOrderTo)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumDeliveryPrice)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridTable)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumDiscount)).BeginInit();
            this.SuspendLayout();
            // 
            // NumOrderFrom
            // 
            this.NumOrderFrom.Location = new System.Drawing.Point(136, 37);
            this.NumOrderFrom.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumOrderFrom.Name = "NumOrderFrom";
            this.NumOrderFrom.Size = new System.Drawing.Size(275, 20);
            this.NumOrderFrom.TabIndex = 0;
            // 
            // NumOrderTo
            // 
            this.NumOrderTo.Location = new System.Drawing.Point(136, 63);
            this.NumOrderTo.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumOrderTo.Name = "NumOrderTo";
            this.NumOrderTo.Size = new System.Drawing.Size(275, 20);
            this.NumOrderTo.TabIndex = 2;
            this.NumOrderTo.Value = new decimal(new int[] {
            1000,
            0,
            0,
            0});
            // 
            // NumDeliveryPrice
            // 
            this.NumDeliveryPrice.Location = new System.Drawing.Point(136, 89);
            this.NumDeliveryPrice.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumDeliveryPrice.Name = "NumDeliveryPrice";
            this.NumDeliveryPrice.Size = new System.Drawing.Size(275, 20);
            this.NumDeliveryPrice.TabIndex = 3;
            this.NumDeliveryPrice.Value = new decimal(new int[] {
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
            this.label2.Size = new System.Drawing.Size(83, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Цена доставки";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(9, 65);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Заказ до";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(9, 39);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(52, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Заказ от";
            // 
            // DataGridTable
            // 
            this.DataGridTable.AllowUserToAddRows = false;
            this.DataGridTable.AllowUserToDeleteRows = false;
            this.DataGridTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridTable.Location = new System.Drawing.Point(12, 201);
            this.DataGridTable.Name = "DataGridTable";
            this.DataGridTable.ReadOnly = true;
            this.DataGridTable.Size = new System.Drawing.Size(630, 235);
            this.DataGridTable.TabIndex = 8;
            // 
            // ButtClear
            // 
            this.ButtClear.Location = new System.Drawing.Point(12, 172);
            this.ButtClear.Name = "ButtClear";
            this.ButtClear.Size = new System.Drawing.Size(114, 23);
            this.ButtClear.TabIndex = 9;
            this.ButtClear.Text = "Удалить все";
            this.ButtClear.UseVisualStyleBackColor = true;
            this.ButtClear.Click += new System.EventHandler(this.ButtClear_Click);
            // 
            // ButtSave
            // 
            this.ButtSave.Location = new System.Drawing.Point(236, 172);
            this.ButtSave.Name = "ButtSave";
            this.ButtSave.Size = new System.Drawing.Size(406, 23);
            this.ButtSave.TabIndex = 10;
            this.ButtSave.Text = "Добавить";
            this.ButtSave.UseVisualStyleBackColor = true;
            this.ButtSave.Click += new System.EventHandler(this.ButtSave_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(421, 39);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(42, 13);
            this.label5.TabIndex = 11;
            this.label5.Text = "рублей";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(421, 65);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(42, 13);
            this.label6.TabIndex = 12;
            this.label6.Text = "рублей";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(421, 91);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(42, 13);
            this.label7.TabIndex = 13;
            this.label7.Text = "рублей";
            // 
            // checkboxPickup
            // 
            this.checkboxPickup.AutoSize = true;
            this.checkboxPickup.Location = new System.Drawing.Point(12, 143);
            this.checkboxPickup.Name = "checkboxPickup";
            this.checkboxPickup.Size = new System.Drawing.Size(85, 17);
            this.checkboxPickup.TabIndex = 14;
            this.checkboxPickup.Text = "Самовывоз";
            this.checkboxPickup.UseVisualStyleBackColor = true;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(421, 117);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(77, 13);
            this.label8.TabIndex = 17;
            this.label8.Text = "процентов (%)";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(9, 117);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(44, 13);
            this.label9.TabIndex = 16;
            this.label9.Text = "Скидка";
            // 
            // NumDiscount
            // 
            this.NumDiscount.Location = new System.Drawing.Point(136, 115);
            this.NumDiscount.Name = "NumDiscount";
            this.NumDiscount.Size = new System.Drawing.Size(275, 20);
            this.NumDiscount.TabIndex = 15;
            // 
            // ComboDistance
            // 
            this.ComboDistance.FormattingEnabled = true;
            this.ComboDistance.Location = new System.Drawing.Point(136, 11);
            this.ComboDistance.Name = "ComboDistance";
            this.ComboDistance.Size = new System.Drawing.Size(275, 21);
            this.ComboDistance.TabIndex = 18;
            // 
            // ButtDelete
            // 
            this.ButtDelete.Location = new System.Drawing.Point(136, 172);
            this.ButtDelete.Name = "ButtDelete";
            this.ButtDelete.Size = new System.Drawing.Size(94, 23);
            this.ButtDelete.TabIndex = 19;
            this.ButtDelete.Text = "Удалить";
            this.ButtDelete.UseVisualStyleBackColor = true;
            this.ButtDelete.Click += new System.EventHandler(this.ButtDelete_Click);
            // 
            // DeliveryCostForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(654, 448);
            this.Controls.Add(this.ButtDelete);
            this.Controls.Add(this.ComboDistance);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.NumDiscount);
            this.Controls.Add(this.checkboxPickup);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.ButtSave);
            this.Controls.Add(this.ButtClear);
            this.Controls.Add(this.DataGridTable);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.NumDeliveryPrice);
            this.Controls.Add(this.NumOrderTo);
            this.Controls.Add(this.NumOrderFrom);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "DeliveryCostForm";
            this.Text = "Таблица стоимости доставки";
            ((System.ComponentModel.ISupportInitialize)(this.NumOrderFrom)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumOrderTo)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumDeliveryPrice)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridTable)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumDiscount)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.NumericUpDown NumOrderFrom;
        private System.Windows.Forms.NumericUpDown NumOrderTo;
        private System.Windows.Forms.NumericUpDown NumDeliveryPrice;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.DataGridView DataGridTable;
        private System.Windows.Forms.Button ButtClear;
        private System.Windows.Forms.Button ButtSave;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.CheckBox checkboxPickup;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.NumericUpDown NumDiscount;
        private System.Windows.Forms.ComboBox ComboDistance;
        private System.Windows.Forms.Button ButtDelete;
    }
}