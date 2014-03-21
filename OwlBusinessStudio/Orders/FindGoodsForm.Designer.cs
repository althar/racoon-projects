namespace OwlBusinessStudio.Orders
{
    partial class FindGoodsForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FindGoodsForm));
            this.button1 = new System.Windows.Forms.Button();
            this.ComboNameForOrder = new System.Windows.Forms.ComboBox();
            this.ComboNameRus = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.CheckListBrand = new System.Windows.Forms.CheckedListBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.ButtApplyFilters = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.TxtArticul = new System.Windows.Forms.TextBox();
            this.ButtViewGoodPicture = new System.Windows.Forms.Button();
            this.CheckListAge = new System.Windows.Forms.CheckedListBox();
            this.CheckListUseType = new System.Windows.Forms.CheckedListBox();
            this.CheckListFoodType = new System.Windows.Forms.CheckedListBox();
            this.CheckListAnimal = new System.Windows.Forms.CheckedListBox();
            this.CheckListWeight = new System.Windows.Forms.CheckedListBox();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.TxtDescription = new System.Windows.Forms.TextBox();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(5, 359);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(849, 23);
            this.button1.TabIndex = 0;
            this.button1.Text = "Выбрать";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // ComboNameForOrder
            // 
            this.ComboNameForOrder.FormattingEnabled = true;
            this.ComboNameForOrder.Location = new System.Drawing.Point(95, 239);
            this.ComboNameForOrder.Name = "ComboNameForOrder";
            this.ComboNameForOrder.Size = new System.Drawing.Size(723, 21);
            this.ComboNameForOrder.TabIndex = 1;
            this.ComboNameForOrder.SelectedIndexChanged += new System.EventHandler(this.ComboNameForOrder_SelectedIndexChanged);
            this.ComboNameForOrder.TextChanged += new System.EventHandler(this.ComboNameForOrder_TextChanged);
            this.ComboNameForOrder.KeyDown += new System.Windows.Forms.KeyEventHandler(this.ComboNameForOrder_KeyDown);
            // 
            // ComboNameRus
            // 
            this.ComboNameRus.FormattingEnabled = true;
            this.ComboNameRus.Location = new System.Drawing.Point(95, 266);
            this.ComboNameRus.Name = "ComboNameRus";
            this.ComboNameRus.Size = new System.Drawing.Size(723, 21);
            this.ComboNameRus.TabIndex = 2;
            this.ComboNameRus.Visible = false;
            this.ComboNameRus.SelectedIndexChanged += new System.EventHandler(this.ComboNameRus_SelectedIndexChanged);
            this.ComboNameRus.TextChanged += new System.EventHandler(this.ComboNameRus_TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 242);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(83, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Наименование";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(7, 269);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(57, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Описание";
            // 
            // CheckListBrand
            // 
            this.CheckListBrand.CheckOnClick = true;
            this.CheckListBrand.FormattingEnabled = true;
            this.CheckListBrand.Location = new System.Drawing.Point(10, 37);
            this.CheckListBrand.Name = "CheckListBrand";
            this.CheckListBrand.Size = new System.Drawing.Size(267, 169);
            this.CheckListBrand.TabIndex = 7;
            this.CheckListBrand.MouseUp += new System.Windows.Forms.MouseEventHandler(this.CheckListBrand_MouseUp);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.TxtDescription);
            this.groupBox1.Controls.Add(this.ButtApplyFilters);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.TxtArticul);
            this.groupBox1.Controls.Add(this.ButtViewGoodPicture);
            this.groupBox1.Controls.Add(this.CheckListAge);
            this.groupBox1.Controls.Add(this.CheckListUseType);
            this.groupBox1.Controls.Add(this.CheckListFoodType);
            this.groupBox1.Controls.Add(this.CheckListAnimal);
            this.groupBox1.Controls.Add(this.CheckListWeight);
            this.groupBox1.Controls.Add(this.label9);
            this.groupBox1.Controls.Add(this.label8);
            this.groupBox1.Controls.Add(this.label7);
            this.groupBox1.Controls.Add(this.label6);
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.CheckListBrand);
            this.groupBox1.Controls.Add(this.ComboNameForOrder);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.ComboNameRus);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Location = new System.Drawing.Point(5, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(849, 341);
            this.groupBox1.TabIndex = 8;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Поиск";
            // 
            // ButtApplyFilters
            // 
            this.ButtApplyFilters.Location = new System.Drawing.Point(283, 210);
            this.ButtApplyFilters.Name = "ButtApplyFilters";
            this.ButtApplyFilters.Size = new System.Drawing.Size(560, 23);
            this.ButtApplyFilters.TabIndex = 22;
            this.ButtApplyFilters.Text = "Применить фильтры";
            this.ButtApplyFilters.UseVisualStyleBackColor = true;
            this.ButtApplyFilters.Click += new System.EventHandler(this.ButtApplyFilters_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(7, 216);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(48, 13);
            this.label3.TabIndex = 21;
            this.label3.Text = "Артикул";
            // 
            // TxtArticul
            // 
            this.TxtArticul.Location = new System.Drawing.Point(95, 213);
            this.TxtArticul.Name = "TxtArticul";
            this.TxtArticul.Size = new System.Drawing.Size(100, 20);
            this.TxtArticul.TabIndex = 20;
            this.TxtArticul.TextChanged += new System.EventHandler(this.TxtArticul_TextChanged);
            this.TxtArticul.KeyDown += new System.Windows.Forms.KeyEventHandler(this.TxtArticul_KeyDown);
            // 
            // ButtViewGoodPicture
            // 
            this.ButtViewGoodPicture.Enabled = false;
            this.ButtViewGoodPicture.Image = ((System.Drawing.Image)(resources.GetObject("ButtViewGoodPicture.Image")));
            this.ButtViewGoodPicture.Location = new System.Drawing.Point(820, 238);
            this.ButtViewGoodPicture.Name = "ButtViewGoodPicture";
            this.ButtViewGoodPicture.Size = new System.Drawing.Size(25, 23);
            this.ButtViewGoodPicture.TabIndex = 19;
            this.ButtViewGoodPicture.UseVisualStyleBackColor = true;
            this.ButtViewGoodPicture.Click += new System.EventHandler(this.ButtViewGoodPicture_Click);
            // 
            // CheckListAge
            // 
            this.CheckListAge.CheckOnClick = true;
            this.CheckListAge.FormattingEnabled = true;
            this.CheckListAge.Location = new System.Drawing.Point(283, 127);
            this.CheckListAge.Name = "CheckListAge";
            this.CheckListAge.Size = new System.Drawing.Size(192, 79);
            this.CheckListAge.TabIndex = 18;
            this.CheckListAge.MouseUp += new System.Windows.Forms.MouseEventHandler(this.CheckListBrand_MouseUp);
            // 
            // CheckListUseType
            // 
            this.CheckListUseType.CheckOnClick = true;
            this.CheckListUseType.FormattingEnabled = true;
            this.CheckListUseType.Location = new System.Drawing.Point(486, 142);
            this.CheckListUseType.Name = "CheckListUseType";
            this.CheckListUseType.Size = new System.Drawing.Size(171, 64);
            this.CheckListUseType.TabIndex = 17;
            this.CheckListUseType.MouseUp += new System.Windows.Forms.MouseEventHandler(this.CheckListBrand_MouseUp);
            // 
            // CheckListFoodType
            // 
            this.CheckListFoodType.CheckOnClick = true;
            this.CheckListFoodType.FormattingEnabled = true;
            this.CheckListFoodType.Location = new System.Drawing.Point(486, 37);
            this.CheckListFoodType.Name = "CheckListFoodType";
            this.CheckListFoodType.Size = new System.Drawing.Size(171, 79);
            this.CheckListFoodType.TabIndex = 16;
            this.CheckListFoodType.MouseUp += new System.Windows.Forms.MouseEventHandler(this.CheckListBrand_MouseUp);
            // 
            // CheckListAnimal
            // 
            this.CheckListAnimal.CheckOnClick = true;
            this.CheckListAnimal.FormattingEnabled = true;
            this.CheckListAnimal.Location = new System.Drawing.Point(283, 37);
            this.CheckListAnimal.Name = "CheckListAnimal";
            this.CheckListAnimal.Size = new System.Drawing.Size(195, 64);
            this.CheckListAnimal.TabIndex = 15;
            this.CheckListAnimal.MouseUp += new System.Windows.Forms.MouseEventHandler(this.CheckListBrand_MouseUp);
            // 
            // CheckListWeight
            // 
            this.CheckListWeight.CheckOnClick = true;
            this.CheckListWeight.FormattingEnabled = true;
            this.CheckListWeight.Location = new System.Drawing.Point(664, 37);
            this.CheckListWeight.Name = "CheckListWeight";
            this.CheckListWeight.Size = new System.Drawing.Size(179, 169);
            this.CheckListWeight.TabIndex = 14;
            this.CheckListWeight.MouseUp += new System.Windows.Forms.MouseEventHandler(this.CheckListBrand_MouseUp);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(483, 126);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(91, 13);
            this.label9.TabIndex = 13;
            this.label9.Text = "Тип применения";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(661, 21);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(46, 13);
            this.label8.TabIndex = 12;
            this.label8.Text = "Вес (кг)";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(283, 125);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(59, 13);
            this.label7.TabIndex = 11;
            this.label7.Text = "Животное";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(483, 21);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(61, 13);
            this.label6.TabIndex = 10;
            this.label6.Text = "Тип корма";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(283, 111);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(49, 13);
            this.label5.TabIndex = 9;
            this.label5.Text = "Возраст";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(7, 21);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(38, 13);
            this.label4.TabIndex = 8;
            this.label4.Text = "Бренд";
            // 
            // TxtDescription
            // 
            this.TxtDescription.Location = new System.Drawing.Point(95, 266);
            this.TxtDescription.Multiline = true;
            this.TxtDescription.Name = "TxtDescription";
            this.TxtDescription.Size = new System.Drawing.Size(723, 68);
            this.TxtDescription.TabIndex = 23;
            // 
            // FindGoodsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(860, 383);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.button1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MinimumSize = new System.Drawing.Size(876, 357);
            this.Name = "FindGoodsForm";
            this.Text = "Поиск товара";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.FindGoodsForm_FormClosed);
            this.Load += new System.EventHandler(this.FindGoodsForm_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ComboBox ComboNameForOrder;
        private System.Windows.Forms.ComboBox ComboNameRus;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.CheckedListBox CheckListBrand;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.CheckedListBox CheckListAge;
        private System.Windows.Forms.CheckedListBox CheckListUseType;
        private System.Windows.Forms.CheckedListBox CheckListFoodType;
        private System.Windows.Forms.CheckedListBox CheckListAnimal;
        private System.Windows.Forms.CheckedListBox CheckListWeight;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button ButtViewGoodPicture;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox TxtArticul;
        private System.Windows.Forms.Button ButtApplyFilters;
        private System.Windows.Forms.TextBox TxtDescription;
    }
}