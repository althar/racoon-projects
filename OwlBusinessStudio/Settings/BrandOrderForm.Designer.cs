namespace OwlBusinessStudio.Settings
{
    partial class BrandOrderForm
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
            this.ListAllBrands = new System.Windows.Forms.ListBox();
            this.ListOrderedBrands = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.ButtAdd = new System.Windows.Forms.Button();
            this.ButtDelete = new System.Windows.Forms.Button();
            this.ButtSave = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // ListAllBrands
            // 
            this.ListAllBrands.FormattingEnabled = true;
            this.ListAllBrands.Location = new System.Drawing.Point(12, 25);
            this.ListAllBrands.Name = "ListAllBrands";
            this.ListAllBrands.Size = new System.Drawing.Size(175, 420);
            this.ListAllBrands.TabIndex = 0;
            // 
            // ListOrderedBrands
            // 
            this.ListOrderedBrands.FormattingEnabled = true;
            this.ListOrderedBrands.Location = new System.Drawing.Point(312, 25);
            this.ListOrderedBrands.Name = "ListOrderedBrands";
            this.ListOrderedBrands.Size = new System.Drawing.Size(196, 394);
            this.ListOrderedBrands.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(67, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Все бренды";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(309, 9);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(114, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Упорядоченный лист";
            // 
            // ButtAdd
            // 
            this.ButtAdd.Location = new System.Drawing.Point(202, 103);
            this.ButtAdd.Name = "ButtAdd";
            this.ButtAdd.Size = new System.Drawing.Size(94, 23);
            this.ButtAdd.TabIndex = 4;
            this.ButtAdd.Text = "Добавить ->";
            this.ButtAdd.UseVisualStyleBackColor = true;
            this.ButtAdd.Click += new System.EventHandler(this.ButtAdd_Click);
            // 
            // ButtDelete
            // 
            this.ButtDelete.Location = new System.Drawing.Point(312, 422);
            this.ButtDelete.Name = "ButtDelete";
            this.ButtDelete.Size = new System.Drawing.Size(196, 23);
            this.ButtDelete.TabIndex = 5;
            this.ButtDelete.Text = "Удалить из списка";
            this.ButtDelete.UseVisualStyleBackColor = true;
            this.ButtDelete.Click += new System.EventHandler(this.ButtDelete_Click);
            // 
            // ButtSave
            // 
            this.ButtSave.Location = new System.Drawing.Point(12, 451);
            this.ButtSave.Name = "ButtSave";
            this.ButtSave.Size = new System.Drawing.Size(496, 39);
            this.ButtSave.TabIndex = 6;
            this.ButtSave.Text = "Сохранить порядок брендов";
            this.ButtSave.UseVisualStyleBackColor = true;
            this.ButtSave.Click += new System.EventHandler(this.ButtSave_Click);
            // 
            // BrandOrderForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(520, 502);
            this.Controls.Add(this.ButtSave);
            this.Controls.Add(this.ButtDelete);
            this.Controls.Add(this.ButtAdd);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.ListOrderedBrands);
            this.Controls.Add(this.ListAllBrands);
            this.Name = "BrandOrderForm";
            this.Text = "BrandOrderForm";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox ListAllBrands;
        private System.Windows.Forms.ListBox ListOrderedBrands;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button ButtAdd;
        private System.Windows.Forms.Button ButtDelete;
        private System.Windows.Forms.Button ButtSave;
    }
}