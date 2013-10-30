namespace OwlBusinessStudio.Users
{
    partial class LoginHistoryForm
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
            this.ComboUser = new System.Windows.Forms.ComboBox();
            this.DataGridHistory = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridHistory)).BeginInit();
            this.SuspendLayout();
            // 
            // ComboUser
            // 
            this.ComboUser.FormattingEnabled = true;
            this.ComboUser.Location = new System.Drawing.Point(98, 12);
            this.ComboUser.Name = "ComboUser";
            this.ComboUser.Size = new System.Drawing.Size(235, 21);
            this.ComboUser.TabIndex = 0;
            this.ComboUser.SelectedIndexChanged += new System.EventHandler(this.ComboUser_SelectedIndexChanged);
            // 
            // DataGridHistory
            // 
            this.DataGridHistory.AllowUserToAddRows = false;
            this.DataGridHistory.AllowUserToDeleteRows = false;
            this.DataGridHistory.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridHistory.Location = new System.Drawing.Point(12, 39);
            this.DataGridHistory.Name = "DataGridHistory";
            this.DataGridHistory.ReadOnly = true;
            this.DataGridHistory.Size = new System.Drawing.Size(320, 296);
            this.DataGridHistory.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(80, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Пользователь";
            // 
            // LoginHistoryForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(344, 347);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.DataGridHistory);
            this.Controls.Add(this.ComboUser);
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(360, 385);
            this.MinimumSize = new System.Drawing.Size(360, 385);
            this.Name = "LoginHistoryForm";
            this.Text = "История пользователей";
            this.Load += new System.EventHandler(this.LoginHistoryForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.DataGridHistory)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox ComboUser;
        private System.Windows.Forms.DataGridView DataGridHistory;
        private System.Windows.Forms.Label label1;
    }
}