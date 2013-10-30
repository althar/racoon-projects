namespace OwlBusinessStudio.Settings
{
    partial class DriverMetrosForm
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
            this.ComboDrivers = new System.Windows.Forms.ComboBox();
            this.ListMetros = new System.Windows.Forms.ListBox();
            this.ButtAdd = new System.Windows.Forms.Button();
            this.DataGridViewMetros = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewMetros)).BeginInit();
            this.SuspendLayout();
            // 
            // ComboDrivers
            // 
            this.ComboDrivers.FormattingEnabled = true;
            this.ComboDrivers.Location = new System.Drawing.Point(12, 26);
            this.ComboDrivers.Name = "ComboDrivers";
            this.ComboDrivers.Size = new System.Drawing.Size(277, 21);
            this.ComboDrivers.TabIndex = 0;
            this.ComboDrivers.SelectedValueChanged += new System.EventHandler(this.ComboDrivers_SelectedValueChanged);
            // 
            // ListMetros
            // 
            this.ListMetros.FormattingEnabled = true;
            this.ListMetros.Location = new System.Drawing.Point(12, 53);
            this.ListMetros.Name = "ListMetros";
            this.ListMetros.Size = new System.Drawing.Size(277, 225);
            this.ListMetros.TabIndex = 1;
            // 
            // ButtAdd
            // 
            this.ButtAdd.Location = new System.Drawing.Point(12, 287);
            this.ButtAdd.Name = "ButtAdd";
            this.ButtAdd.Size = new System.Drawing.Size(277, 23);
            this.ButtAdd.TabIndex = 2;
            this.ButtAdd.Text = "Добавить";
            this.ButtAdd.UseVisualStyleBackColor = true;
            this.ButtAdd.Click += new System.EventHandler(this.ButtAdd_Click);
            // 
            // DataGridViewMetros
            // 
            this.DataGridViewMetros.AllowUserToAddRows = false;
            this.DataGridViewMetros.AllowUserToDeleteRows = false;
            this.DataGridViewMetros.AllowUserToOrderColumns = true;
            this.DataGridViewMetros.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewMetros.Location = new System.Drawing.Point(295, 53);
            this.DataGridViewMetros.Name = "DataGridViewMetros";
            this.DataGridViewMetros.Size = new System.Drawing.Size(370, 257);
            this.DataGridViewMetros.TabIndex = 3;
            this.DataGridViewMetros.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DataGridViewMetros_CellClick);
            this.DataGridViewMetros.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewMetros_CellStateChanged);
            this.DataGridViewMetros.KeyDown += new System.Windows.Forms.KeyEventHandler(this.DataGridViewMetros_KeyDown);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(304, 34);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(89, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Метро водителя";
            // 
            // DriverMetrosForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(677, 319);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.DataGridViewMetros);
            this.Controls.Add(this.ButtAdd);
            this.Controls.Add(this.ListMetros);
            this.Controls.Add(this.ComboDrivers);
            this.MaximumSize = new System.Drawing.Size(693, 357);
            this.MinimumSize = new System.Drawing.Size(693, 357);
            this.Name = "DriverMetrosForm";
            this.Text = "Водители - метро";
            this.Load += new System.EventHandler(this.DriverMetrosForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewMetros)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox ComboDrivers;
        private System.Windows.Forms.ListBox ListMetros;
        private System.Windows.Forms.Button ButtAdd;
        private System.Windows.Forms.DataGridView DataGridViewMetros;
        private System.Windows.Forms.Label label1;
    }
}