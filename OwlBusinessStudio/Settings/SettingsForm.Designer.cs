namespace OwlBusinessStudio.Settings
{
    partial class SettingsForm
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
            this.DataGridSettings = new System.Windows.Forms.DataGridView();
            this.ButtChange = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridSettings)).BeginInit();
            this.SuspendLayout();
            // 
            // DataGridSettings
            // 
            this.DataGridSettings.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridSettings.Location = new System.Drawing.Point(12, 12);
            this.DataGridSettings.Name = "DataGridSettings";
            this.DataGridSettings.Size = new System.Drawing.Size(992, 406);
            this.DataGridSettings.TabIndex = 0;
            this.DataGridSettings.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridSettings_CellStateChanged);
            // 
            // ButtChange
            // 
            this.ButtChange.Location = new System.Drawing.Point(12, 424);
            this.ButtChange.Name = "ButtChange";
            this.ButtChange.Size = new System.Drawing.Size(992, 32);
            this.ButtChange.TabIndex = 1;
            this.ButtChange.Text = "Изменить";
            this.ButtChange.UseVisualStyleBackColor = true;
            this.ButtChange.Click += new System.EventHandler(this.ButtChange_Click);
            // 
            // SettingsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1016, 459);
            this.Controls.Add(this.ButtChange);
            this.Controls.Add(this.DataGridSettings);
            this.Name = "SettingsForm";
            this.Text = "SettingsForm";
            ((System.ComponentModel.ISupportInitialize)(this.DataGridSettings)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView DataGridSettings;
        private System.Windows.Forms.Button ButtChange;
    }
}