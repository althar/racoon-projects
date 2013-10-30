namespace OwlBusinessStudio.Settings
{
    partial class SettingsEditForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SettingsEditForm));
            this.TxtSettingValue = new System.Windows.Forms.TextBox();
            this.LabelSettingName = new System.Windows.Forms.Label();
            this.ButtOK = new System.Windows.Forms.Button();
            this.LabelSuffix = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // TxtSettingValue
            // 
            this.TxtSettingValue.Location = new System.Drawing.Point(121, 12);
            this.TxtSettingValue.Multiline = true;
            this.TxtSettingValue.Name = "TxtSettingValue";
            this.TxtSettingValue.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.TxtSettingValue.Size = new System.Drawing.Size(328, 59);
            this.TxtSettingValue.TabIndex = 0;
            this.TxtSettingValue.TextChanged += new System.EventHandler(this.TxtSettingValue_TextChanged);
            // 
            // LabelSettingName
            // 
            this.LabelSettingName.AutoSize = true;
            this.LabelSettingName.Location = new System.Drawing.Point(12, 15);
            this.LabelSettingName.Name = "LabelSettingName";
            this.LabelSettingName.Size = new System.Drawing.Size(0, 13);
            this.LabelSettingName.TabIndex = 1;
            // 
            // ButtOK
            // 
            this.ButtOK.Location = new System.Drawing.Point(12, 77);
            this.ButtOK.Name = "ButtOK";
            this.ButtOK.Size = new System.Drawing.Size(437, 23);
            this.ButtOK.TabIndex = 2;
            this.ButtOK.Text = "Сохранить";
            this.ButtOK.UseVisualStyleBackColor = true;
            this.ButtOK.Click += new System.EventHandler(this.ButtOK_Click);
            // 
            // LabelSuffix
            // 
            this.LabelSuffix.AutoSize = true;
            this.LabelSuffix.Location = new System.Drawing.Point(455, 15);
            this.LabelSuffix.Name = "LabelSuffix";
            this.LabelSuffix.Size = new System.Drawing.Size(0, 13);
            this.LabelSuffix.TabIndex = 3;
            // 
            // SettingsEditForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(484, 102);
            this.Controls.Add(this.LabelSuffix);
            this.Controls.Add(this.ButtOK);
            this.Controls.Add(this.LabelSettingName);
            this.Controls.Add(this.TxtSettingValue);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximumSize = new System.Drawing.Size(500, 140);
            this.MinimumSize = new System.Drawing.Size(500, 140);
            this.Name = "SettingsEditForm";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox TxtSettingValue;
        private System.Windows.Forms.Label LabelSettingName;
        private System.Windows.Forms.Button ButtOK;
        private System.Windows.Forms.Label LabelSuffix;
    }
}