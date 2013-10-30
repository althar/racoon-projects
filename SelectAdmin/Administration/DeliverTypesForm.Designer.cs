namespace LowByAdmin.Administration
{
    partial class DeliverTypesForm
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
            this.DataGridDeliverTypes = new System.Windows.Forms.DataGridView();
            this.ButtEdit = new System.Windows.Forms.Button();
            this.ButtDelete = new System.Windows.Forms.Button();
            this.PanelEdit = new System.Windows.Forms.Panel();
            this.TxtName = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.ButtSave = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.NumPrice = new System.Windows.Forms.NumericUpDown();
            this.ButtAdd = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridDeliverTypes)).BeginInit();
            this.PanelEdit.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumPrice)).BeginInit();
            this.SuspendLayout();
            // 
            // DataGridDeliverTypes
            // 
            this.DataGridDeliverTypes.AllowUserToAddRows = false;
            this.DataGridDeliverTypes.AllowUserToDeleteRows = false;
            this.DataGridDeliverTypes.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridDeliverTypes.Location = new System.Drawing.Point(12, 12);
            this.DataGridDeliverTypes.Name = "DataGridDeliverTypes";
            this.DataGridDeliverTypes.ReadOnly = true;
            this.DataGridDeliverTypes.Size = new System.Drawing.Size(423, 276);
            this.DataGridDeliverTypes.TabIndex = 0;
            this.DataGridDeliverTypes.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridIncomeScheme_CellStateChanged);
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
            this.PanelEdit.Controls.Add(this.TxtName);
            this.PanelEdit.Controls.Add(this.label3);
            this.PanelEdit.Controls.Add(this.ButtSave);
            this.PanelEdit.Controls.Add(this.label2);
            this.PanelEdit.Controls.Add(this.label1);
            this.PanelEdit.Controls.Add(this.NumPrice);
            this.PanelEdit.Location = new System.Drawing.Point(31, 86);
            this.PanelEdit.Name = "PanelEdit";
            this.PanelEdit.Size = new System.Drawing.Size(385, 63);
            this.PanelEdit.TabIndex = 3;
            this.PanelEdit.Visible = false;
            // 
            // TxtName
            // 
            this.TxtName.Location = new System.Drawing.Point(85, 8);
            this.TxtName.Name = "TxtName";
            this.TxtName.Size = new System.Drawing.Size(154, 20);
            this.TxtName.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(359, 12);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(24, 13);
            this.label3.TabIndex = 4;
            this.label3.Text = "руб";
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
            this.label2.Location = new System.Drawing.Point(245, 11);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(33, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Цена";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(3, 11);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(76, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Тип доставки";
            // 
            // NumPrice
            // 
            this.NumPrice.Location = new System.Drawing.Point(284, 9);
            this.NumPrice.Maximum = new decimal(new int[] {
            10000,
            0,
            0,
            0});
            this.NumPrice.Name = "NumPrice";
            this.NumPrice.Size = new System.Drawing.Size(71, 20);
            this.NumPrice.TabIndex = 1;
            this.NumPrice.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
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
            // DeliverTypesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(447, 329);
            this.Controls.Add(this.ButtAdd);
            this.Controls.Add(this.PanelEdit);
            this.Controls.Add(this.ButtDelete);
            this.Controls.Add(this.ButtEdit);
            this.Controls.Add(this.DataGridDeliverTypes);
            this.MaximumSize = new System.Drawing.Size(463, 367);
            this.MinimumSize = new System.Drawing.Size(463, 367);
            this.Name = "DeliverTypesForm";
            this.Text = "Таблица доставок";
            this.Load += new System.EventHandler(this.DeliverTypesForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.DataGridDeliverTypes)).EndInit();
            this.PanelEdit.ResumeLayout(false);
            this.PanelEdit.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumPrice)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView DataGridDeliverTypes;
        private System.Windows.Forms.Button ButtEdit;
        private System.Windows.Forms.Button ButtDelete;
        private System.Windows.Forms.Panel PanelEdit;
        private System.Windows.Forms.Button ButtSave;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.NumericUpDown NumPrice;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button ButtAdd;
        private System.Windows.Forms.TextBox TxtName;
    }
}