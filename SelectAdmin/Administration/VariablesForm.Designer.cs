namespace LowByAdmin.Administration
{
    partial class VariablesForm
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
            this.DataGridViewVariables = new System.Windows.Forms.DataGridView();
            this.ButtEdit = new System.Windows.Forms.Button();
            this.PanelChangeVar = new System.Windows.Forms.Panel();
            this.ButtOK = new System.Windows.Forms.Button();
            this.LabelVarName = new System.Windows.Forms.Label();
            this.TxtVarValue = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewVariables)).BeginInit();
            this.PanelChangeVar.SuspendLayout();
            this.SuspendLayout();
            // 
            // DataGridViewVariables
            // 
            this.DataGridViewVariables.AllowUserToAddRows = false;
            this.DataGridViewVariables.AllowUserToDeleteRows = false;
            this.DataGridViewVariables.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewVariables.Location = new System.Drawing.Point(12, 12);
            this.DataGridViewVariables.Name = "DataGridViewVariables";
            this.DataGridViewVariables.ReadOnly = true;
            this.DataGridViewVariables.Size = new System.Drawing.Size(467, 324);
            this.DataGridViewVariables.TabIndex = 0;
            this.DataGridViewVariables.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewVariables_CellStateChanged);
            // 
            // ButtEdit
            // 
            this.ButtEdit.Location = new System.Drawing.Point(12, 342);
            this.ButtEdit.Name = "ButtEdit";
            this.ButtEdit.Size = new System.Drawing.Size(467, 23);
            this.ButtEdit.TabIndex = 1;
            this.ButtEdit.Text = "Редактировать";
            this.ButtEdit.UseVisualStyleBackColor = true;
            this.ButtEdit.Click += new System.EventHandler(this.ButtEdit_Click);
            // 
            // PanelChangeVar
            // 
            this.PanelChangeVar.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelChangeVar.Controls.Add(this.TxtVarValue);
            this.PanelChangeVar.Controls.Add(this.LabelVarName);
            this.PanelChangeVar.Controls.Add(this.ButtOK);
            this.PanelChangeVar.Location = new System.Drawing.Point(22, 99);
            this.PanelChangeVar.Name = "PanelChangeVar";
            this.PanelChangeVar.Size = new System.Drawing.Size(445, 80);
            this.PanelChangeVar.TabIndex = 2;
            this.PanelChangeVar.Visible = false;
            // 
            // ButtOK
            // 
            this.ButtOK.Location = new System.Drawing.Point(3, 52);
            this.ButtOK.Name = "ButtOK";
            this.ButtOK.Size = new System.Drawing.Size(437, 23);
            this.ButtOK.TabIndex = 0;
            this.ButtOK.Text = "ОК";
            this.ButtOK.UseVisualStyleBackColor = true;
            this.ButtOK.Click += new System.EventHandler(this.ButtOK_Click);
            // 
            // LabelVarName
            // 
            this.LabelVarName.AutoSize = true;
            this.LabelVarName.Location = new System.Drawing.Point(3, 8);
            this.LabelVarName.Name = "LabelVarName";
            this.LabelVarName.Size = new System.Drawing.Size(35, 13);
            this.LabelVarName.TabIndex = 1;
            this.LabelVarName.Text = "label1";
            // 
            // TxtVarValue
            // 
            this.TxtVarValue.Location = new System.Drawing.Point(6, 26);
            this.TxtVarValue.Name = "TxtVarValue";
            this.TxtVarValue.Size = new System.Drawing.Size(434, 20);
            this.TxtVarValue.TabIndex = 2;
            // 
            // VariablesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(491, 372);
            this.Controls.Add(this.PanelChangeVar);
            this.Controls.Add(this.ButtEdit);
            this.Controls.Add(this.DataGridViewVariables);
            this.MaximumSize = new System.Drawing.Size(507, 410);
            this.MinimumSize = new System.Drawing.Size(507, 410);
            this.Name = "VariablesForm";
            this.Text = "Переменные сервиса";
            this.Load += new System.EventHandler(this.VariablesForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewVariables)).EndInit();
            this.PanelChangeVar.ResumeLayout(false);
            this.PanelChangeVar.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView DataGridViewVariables;
        private System.Windows.Forms.Button ButtEdit;
        private System.Windows.Forms.Panel PanelChangeVar;
        private System.Windows.Forms.TextBox TxtVarValue;
        private System.Windows.Forms.Label LabelVarName;
        private System.Windows.Forms.Button ButtOK;
    }
}