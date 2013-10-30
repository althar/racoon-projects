namespace OwlBusinessStudio.Orders
{
    partial class OrderList
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(OrderList));
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.ButtAddGood = new System.Windows.Forms.Button();
            this.LabelItogo = new System.Windows.Forms.Label();
            this.TxtPrice = new System.Windows.Forms.TextBox();
            this.PicDelete = new System.Windows.Forms.PictureBox();
            this.TxtDeliveryPrice = new System.Windows.Forms.TextBox();
            this.TxtTotalPrice = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.PicDelete)).BeginInit();
            this.SuspendLayout();
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(731, 22);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(57, 13);
            this.label4.TabIndex = 12;
            this.label4.Text = "Доставка";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(661, 22);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(33, 13);
            this.label3.TabIndex = 11;
            this.label3.Text = "Цена";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(603, 2);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(41, 13);
            this.label2.TabIndex = 10;
            this.label2.Text = "Кол-во";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(33, 3);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(38, 13);
            this.label1.TabIndex = 9;
            this.label1.Text = "Товар";
            // 
            // ButtAddGood
            // 
            this.ButtAddGood.Location = new System.Drawing.Point(0, 15);
            this.ButtAddGood.Name = "ButtAddGood";
            this.ButtAddGood.Size = new System.Drawing.Size(655, 47);
            this.ButtAddGood.TabIndex = 13;
            this.ButtAddGood.Text = "Добавить еще товар";
            this.ButtAddGood.UseVisualStyleBackColor = true;
            this.ButtAddGood.Click += new System.EventHandler(this.ButtAddGood_Click);
            // 
            // LabelItogo
            // 
            this.LabelItogo.AutoSize = true;
            this.LabelItogo.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.LabelItogo.Location = new System.Drawing.Point(801, 22);
            this.LabelItogo.Name = "LabelItogo";
            this.LabelItogo.Size = new System.Drawing.Size(42, 13);
            this.LabelItogo.TabIndex = 14;
            this.LabelItogo.Text = "Итого";
            // 
            // TxtPrice
            // 
            this.TxtPrice.BackColor = System.Drawing.Color.OldLace;
            this.TxtPrice.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.TxtPrice.Location = new System.Drawing.Point(661, 40);
            this.TxtPrice.Name = "TxtPrice";
            this.TxtPrice.ReadOnly = true;
            this.TxtPrice.Size = new System.Drawing.Size(64, 20);
            this.TxtPrice.TabIndex = 15;
            // 
            // PicDelete
            // 
            this.PicDelete.Cursor = System.Windows.Forms.Cursors.Hand;
            this.PicDelete.Image = ((System.Drawing.Image)(resources.GetObject("PicDelete.Image")));
            this.PicDelete.Location = new System.Drawing.Point(895, 14);
            this.PicDelete.Name = "PicDelete";
            this.PicDelete.Size = new System.Drawing.Size(24, 24);
            this.PicDelete.TabIndex = 16;
            this.PicDelete.TabStop = false;
            this.PicDelete.Visible = false;
            // 
            // TxtDeliveryPrice
            // 
            this.TxtDeliveryPrice.BackColor = System.Drawing.Color.OldLace;
            this.TxtDeliveryPrice.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.TxtDeliveryPrice.Location = new System.Drawing.Point(731, 40);
            this.TxtDeliveryPrice.Name = "TxtDeliveryPrice";
            this.TxtDeliveryPrice.Size = new System.Drawing.Size(64, 20);
            this.TxtDeliveryPrice.TabIndex = 17;
            this.TxtDeliveryPrice.Text = "0";
            this.TxtDeliveryPrice.TextChanged += new System.EventHandler(this.TxtDeliveryPrice_TextChanged);
            this.TxtDeliveryPrice.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.TxtDeliveryPrice_KeyPress);
            // 
            // TxtTotalPrice
            // 
            this.TxtTotalPrice.BackColor = System.Drawing.Color.OldLace;
            this.TxtTotalPrice.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.TxtTotalPrice.Location = new System.Drawing.Point(801, 40);
            this.TxtTotalPrice.Name = "TxtTotalPrice";
            this.TxtTotalPrice.ReadOnly = true;
            this.TxtTotalPrice.Size = new System.Drawing.Size(64, 20);
            this.TxtTotalPrice.TabIndex = 19;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(661, 2);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(33, 13);
            this.label5.TabIndex = 20;
            this.label5.Text = "Цена";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(731, 3);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(62, 13);
            this.label6.TabIndex = 21;
            this.label6.Text = "Стоимость";
            // 
            // OrderList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.TxtTotalPrice);
            this.Controls.Add(this.TxtDeliveryPrice);
            this.Controls.Add(this.PicDelete);
            this.Controls.Add(this.TxtPrice);
            this.Controls.Add(this.LabelItogo);
            this.Controls.Add(this.ButtAddGood);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "OrderList";
            this.Size = new System.Drawing.Size(924, 65);
            this.Load += new System.EventHandler(this.OrderList_Load);
            ((System.ComponentModel.ISupportInitialize)(this.PicDelete)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button ButtAddGood;
        private System.Windows.Forms.Label LabelItogo;
        private System.Windows.Forms.TextBox TxtPrice;
        private System.Windows.Forms.PictureBox PicDelete;
        private System.Windows.Forms.TextBox TxtDeliveryPrice;
        private System.Windows.Forms.TextBox TxtTotalPrice;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;

    }
}
