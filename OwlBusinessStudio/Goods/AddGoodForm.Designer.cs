namespace OwlBusinessStudio.Goods
{
    partial class AddGoodForm
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
            this.TxtArticul = new System.Windows.Forms.TextBox();
            this.TxtWeight = new System.Windows.Forms.TextBox();
            this.TxtNameForOrder = new System.Windows.Forms.TextBox();
            this.TxtPrice = new System.Windows.Forms.TextBox();
            this.TxtPurchaseDiscount = new System.Windows.Forms.TextBox();
            this.TxtBasicPrice = new System.Windows.Forms.TextBox();
            this.TxtNameRus = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.TxtSupplier = new System.Windows.Forms.TextBox();
            this.ComboAnimal = new System.Windows.Forms.ComboBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label12 = new System.Windows.Forms.Label();
            this.ComboAge = new System.Windows.Forms.ComboBox();
            this.label13 = new System.Windows.Forms.Label();
            this.ComboFoodType = new System.Windows.Forms.ComboBox();
            this.label14 = new System.Windows.Forms.Label();
            this.ComboFoodCategory = new System.Windows.Forms.ComboBox();
            this.ButtOK = new System.Windows.Forms.Button();
            this.label9 = new System.Windows.Forms.Label();
            this.ComboCompany = new System.Windows.Forms.ComboBox();
            this.SuspendLayout();
            // 
            // TxtArticul
            // 
            this.TxtArticul.Location = new System.Drawing.Point(124, 22);
            this.TxtArticul.Name = "TxtArticul";
            this.TxtArticul.Size = new System.Drawing.Size(433, 20);
            this.TxtArticul.TabIndex = 0;
            // 
            // TxtWeight
            // 
            this.TxtWeight.Location = new System.Drawing.Point(124, 74);
            this.TxtWeight.Name = "TxtWeight";
            this.TxtWeight.Size = new System.Drawing.Size(433, 20);
            this.TxtWeight.TabIndex = 1;
            this.TxtWeight.TextChanged += new System.EventHandler(this.TxtWeight_TextChanged);
            // 
            // TxtNameForOrder
            // 
            this.TxtNameForOrder.Location = new System.Drawing.Point(124, 48);
            this.TxtNameForOrder.Name = "TxtNameForOrder";
            this.TxtNameForOrder.Size = new System.Drawing.Size(433, 20);
            this.TxtNameForOrder.TabIndex = 2;
            // 
            // TxtPrice
            // 
            this.TxtPrice.Location = new System.Drawing.Point(124, 177);
            this.TxtPrice.Name = "TxtPrice";
            this.TxtPrice.Size = new System.Drawing.Size(433, 20);
            this.TxtPrice.TabIndex = 3;
            this.TxtPrice.TextChanged += new System.EventHandler(this.TxtPrice_TextChanged);
            // 
            // TxtPurchaseDiscount
            // 
            this.TxtPurchaseDiscount.Location = new System.Drawing.Point(124, 151);
            this.TxtPurchaseDiscount.Name = "TxtPurchaseDiscount";
            this.TxtPurchaseDiscount.Size = new System.Drawing.Size(433, 20);
            this.TxtPurchaseDiscount.TabIndex = 4;
            this.TxtPurchaseDiscount.TextChanged += new System.EventHandler(this.TxtPurchaseDiscount_TextChanged);
            // 
            // TxtBasicPrice
            // 
            this.TxtBasicPrice.Location = new System.Drawing.Point(124, 125);
            this.TxtBasicPrice.Name = "TxtBasicPrice";
            this.TxtBasicPrice.Size = new System.Drawing.Size(433, 20);
            this.TxtBasicPrice.TabIndex = 5;
            this.TxtBasicPrice.TextChanged += new System.EventHandler(this.TxtBasicPrice_TextChanged);
            // 
            // TxtNameRus
            // 
            this.TxtNameRus.Location = new System.Drawing.Point(124, 100);
            this.TxtNameRus.Name = "TxtNameRus";
            this.TxtNameRus.Size = new System.Drawing.Size(433, 20);
            this.TxtNameRus.TabIndex = 6;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 25);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(48, 13);
            this.label1.TabIndex = 8;
            this.label1.Text = "Артикул";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 51);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(89, 13);
            this.label2.TabIndex = 9;
            this.label2.Text = "Имя для заказа";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 77);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(40, 13);
            this.label3.TabIndex = 10;
            this.label3.Text = "Масса";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 180);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(33, 13);
            this.label5.TabIndex = 12;
            this.label5.Text = "Цена";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(12, 103);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(100, 13);
            this.label6.TabIndex = 13;
            this.label6.Text = "Русское название";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(12, 154);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(105, 13);
            this.label7.TabIndex = 14;
            this.label7.Text = "Закупочная скидка";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(12, 128);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(93, 13);
            this.label8.TabIndex = 15;
            this.label8.Text = "Закупочная цена";
            // 
            // TxtSupplier
            // 
            this.TxtSupplier.Location = new System.Drawing.Point(124, 203);
            this.TxtSupplier.Name = "TxtSupplier";
            this.TxtSupplier.Size = new System.Drawing.Size(433, 20);
            this.TxtSupplier.TabIndex = 17;
            // 
            // ComboAnimal
            // 
            this.ComboAnimal.FormattingEnabled = true;
            this.ComboAnimal.Location = new System.Drawing.Point(124, 256);
            this.ComboAnimal.Name = "ComboAnimal";
            this.ComboAnimal.Size = new System.Drawing.Size(433, 21);
            this.ComboAnimal.TabIndex = 18;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(12, 206);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(65, 13);
            this.label4.TabIndex = 19;
            this.label4.Text = "Поставщик";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(12, 259);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(59, 13);
            this.label10.TabIndex = 20;
            this.label10.Text = "Животное";
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(12, 286);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(49, 13);
            this.label12.TabIndex = 23;
            this.label12.Text = "Возраст";
            // 
            // ComboAge
            // 
            this.ComboAge.FormattingEnabled = true;
            this.ComboAge.Location = new System.Drawing.Point(124, 283);
            this.ComboAge.Name = "ComboAge";
            this.ComboAge.Size = new System.Drawing.Size(433, 21);
            this.ComboAge.TabIndex = 22;
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(12, 313);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(61, 13);
            this.label13.TabIndex = 25;
            this.label13.Text = "Тип корма";
            // 
            // ComboFoodType
            // 
            this.ComboFoodType.FormattingEnabled = true;
            this.ComboFoodType.Location = new System.Drawing.Point(124, 310);
            this.ComboFoodType.Name = "ComboFoodType";
            this.ComboFoodType.Size = new System.Drawing.Size(433, 21);
            this.ComboFoodType.TabIndex = 24;
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Location = new System.Drawing.Point(12, 340);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(91, 13);
            this.label14.TabIndex = 27;
            this.label14.Text = "Тип применения";
            // 
            // ComboFoodCategory
            // 
            this.ComboFoodCategory.FormattingEnabled = true;
            this.ComboFoodCategory.Location = new System.Drawing.Point(124, 337);
            this.ComboFoodCategory.Name = "ComboFoodCategory";
            this.ComboFoodCategory.Size = new System.Drawing.Size(433, 21);
            this.ComboFoodCategory.TabIndex = 26;
            // 
            // ButtOK
            // 
            this.ButtOK.Location = new System.Drawing.Point(15, 368);
            this.ButtOK.Name = "ButtOK";
            this.ButtOK.Size = new System.Drawing.Size(542, 23);
            this.ButtOK.TabIndex = 28;
            this.ButtOK.Text = "Добавить";
            this.ButtOK.UseVisualStyleBackColor = true;
            this.ButtOK.Click += new System.EventHandler(this.ButtOK_Click);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(12, 232);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(58, 13);
            this.label9.TabIndex = 30;
            this.label9.Text = "Компания";
            // 
            // ComboCompany
            // 
            this.ComboCompany.FormattingEnabled = true;
            this.ComboCompany.Location = new System.Drawing.Point(124, 229);
            this.ComboCompany.Name = "ComboCompany";
            this.ComboCompany.Size = new System.Drawing.Size(433, 21);
            this.ComboCompany.TabIndex = 31;
            // 
            // AddGoodForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(569, 403);
            this.Controls.Add(this.ComboCompany);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.ButtOK);
            this.Controls.Add(this.label14);
            this.Controls.Add(this.ComboFoodCategory);
            this.Controls.Add(this.label13);
            this.Controls.Add(this.ComboFoodType);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.ComboAge);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.ComboAnimal);
            this.Controls.Add(this.TxtSupplier);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.TxtNameRus);
            this.Controls.Add(this.TxtBasicPrice);
            this.Controls.Add(this.TxtPurchaseDiscount);
            this.Controls.Add(this.TxtPrice);
            this.Controls.Add(this.TxtNameForOrder);
            this.Controls.Add(this.TxtWeight);
            this.Controls.Add(this.TxtArticul);
            this.Name = "AddGoodForm";
            this.Text = "Добавить товар";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox TxtArticul;
        private System.Windows.Forms.TextBox TxtWeight;
        private System.Windows.Forms.TextBox TxtNameForOrder;
        private System.Windows.Forms.TextBox TxtPrice;
        private System.Windows.Forms.TextBox TxtPurchaseDiscount;
        private System.Windows.Forms.TextBox TxtBasicPrice;
        private System.Windows.Forms.TextBox TxtNameRus;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox TxtSupplier;
        private System.Windows.Forms.ComboBox ComboAnimal;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.ComboBox ComboAge;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.ComboBox ComboFoodType;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.ComboBox ComboFoodCategory;
        private System.Windows.Forms.Button ButtOK;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.ComboBox ComboCompany;
    }
}