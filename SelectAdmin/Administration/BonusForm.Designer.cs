namespace LowByAdmin.Administration
{
    partial class BonusForm
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
            this.DataGridIncomeScheme = new System.Windows.Forms.DataGridView();
            this.ButtEdit = new System.Windows.Forms.Button();
            this.ButtDelete = new System.Windows.Forms.Button();
            this.PanelEdit = new System.Windows.Forms.Panel();
            this.label3 = new System.Windows.Forms.Label();
            this.ButtSave = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.NumBonus = new System.Windows.Forms.NumericUpDown();
            this.NumMinClicks = new System.Windows.Forms.NumericUpDown();
            this.ButtAdd = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridIncomeScheme)).BeginInit();
            this.PanelEdit.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumBonus)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumMinClicks)).BeginInit();
            this.SuspendLayout();
            // 
            // DataGridIncomeScheme
            // 
            this.DataGridIncomeScheme.AllowUserToAddRows = false;
            this.DataGridIncomeScheme.AllowUserToDeleteRows = false;
            this.DataGridIncomeScheme.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridIncomeScheme.Location = new System.Drawing.Point(12, 12);
            this.DataGridIncomeScheme.Name = "DataGridIncomeScheme";
            this.DataGridIncomeScheme.ReadOnly = true;
            this.DataGridIncomeScheme.Size = new System.Drawing.Size(423, 276);
            this.DataGridIncomeScheme.TabIndex = 0;
            this.DataGridIncomeScheme.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridIncomeScheme_CellStateChanged);
            // 
            // ButtEdit
            // 
            this.ButtEdit.Location = new System.Drawing.Point(12, 294);
            this.ButtEdit.Name = "ButtEdit";
            this.ButtEdit.Size = new System.Drawing.Size(180, 23);
            this.ButtEdit.TabIndex = 1;
            this.ButtEdit.Text = "Редактировать";
            this.ButtEdit.UseVisualStyleBackColor = true;
            this.ButtEdit.Click += new System.EventHandler(this.ButtEdit_Click);
            // 
            // ButtDelete
            // 
            this.ButtDelete.Location = new System.Drawing.Point(292, 294);
            this.ButtDelete.Name = "ButtDelete";
            this.ButtDelete.Size = new System.Drawing.Size(143, 23);
            this.ButtDelete.TabIndex = 2;
            this.ButtDelete.Text = "Удалить";
            this.ButtDelete.UseVisualStyleBackColor = true;
            this.ButtDelete.Click += new System.EventHandler(this.ButtDelete_Click);
            // 
            // PanelEdit
            // 
            this.PanelEdit.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelEdit.Controls.Add(this.label3);
            this.PanelEdit.Controls.Add(this.ButtSave);
            this.PanelEdit.Controls.Add(this.label2);
            this.PanelEdit.Controls.Add(this.label1);
            this.PanelEdit.Controls.Add(this.NumBonus);
            this.PanelEdit.Controls.Add(this.NumMinClicks);
            this.PanelEdit.Location = new System.Drawing.Point(31, 86);
            this.PanelEdit.Name = "PanelEdit";
            this.PanelEdit.Size = new System.Drawing.Size(385, 63);
            this.PanelEdit.TabIndex = 3;
            this.PanelEdit.Visible = false;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(363, 11);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(15, 13);
            this.label3.TabIndex = 4;
            this.label3.Text = "%";
            // 
            // ButtSave
            // 
            this.ButtSave.Location = new System.Drawing.Point(6, 35);
            this.ButtSave.Name = "ButtSave";
            this.ButtSave.Size = new System.Drawing.Size(372, 23);
            this.ButtSave.TabIndex = 4;
            this.ButtSave.Text = "ОК";
            this.ButtSave.UseVisualStyleBackColor = true;
            this.ButtSave.Click += new System.EventHandler(this.ButtSave_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(220, 11);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(37, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Бонус";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(3, 11);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(102, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Мин. число кликов";
            // 
            // NumBonus
            // 
            this.NumBonus.Location = new System.Drawing.Point(260, 9);
            this.NumBonus.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.NumBonus.Name = "NumBonus";
            this.NumBonus.Size = new System.Drawing.Size(100, 20);
            this.NumBonus.TabIndex = 1;
            this.NumBonus.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // NumMinClicks
            // 
            this.NumMinClicks.Location = new System.Drawing.Point(109, 9);
            this.NumMinClicks.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.NumMinClicks.Name = "NumMinClicks";
            this.NumMinClicks.Size = new System.Drawing.Size(81, 20);
            this.NumMinClicks.TabIndex = 0;
            // 
            // ButtAdd
            // 
            this.ButtAdd.Location = new System.Drawing.Point(198, 294);
            this.ButtAdd.Name = "ButtAdd";
            this.ButtAdd.Size = new System.Drawing.Size(91, 23);
            this.ButtAdd.TabIndex = 4;
            this.ButtAdd.Text = "Добавить";
            this.ButtAdd.UseVisualStyleBackColor = true;
            this.ButtAdd.Click += new System.EventHandler(this.ButtAdd_Click);
            // 
            // BonusForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(447, 329);
            this.Controls.Add(this.ButtAdd);
            this.Controls.Add(this.PanelEdit);
            this.Controls.Add(this.ButtDelete);
            this.Controls.Add(this.ButtEdit);
            this.Controls.Add(this.DataGridIncomeScheme);
            this.MaximumSize = new System.Drawing.Size(463, 367);
            this.MinimumSize = new System.Drawing.Size(463, 367);
            this.Name = "BonusForm";
            this.Text = "Таблица бонусов";
            this.Load += new System.EventHandler(this.BonusForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.DataGridIncomeScheme)).EndInit();
            this.PanelEdit.ResumeLayout(false);
            this.PanelEdit.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumBonus)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.NumMinClicks)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView DataGridIncomeScheme;
        private System.Windows.Forms.Button ButtEdit;
        private System.Windows.Forms.Button ButtDelete;
        private System.Windows.Forms.Panel PanelEdit;
        private System.Windows.Forms.Button ButtSave;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.NumericUpDown NumBonus;
        private System.Windows.Forms.NumericUpDown NumMinClicks;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button ButtAdd;
    }
}