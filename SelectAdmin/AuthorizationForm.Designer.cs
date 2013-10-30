namespace LowByAdmin
{
    partial class AuthorizationForm
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
            this.TxtHost = new System.Windows.Forms.TextBox();
            this.TxtDBName = new System.Windows.Forms.TextBox();
            this.TxtPassword = new System.Windows.Forms.TextBox();
            this.TxtLogin = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.ButtOpen = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // TxtHost
            // 
            this.TxtHost.Location = new System.Drawing.Point(63, 12);
            this.TxtHost.Name = "TxtHost";
            this.TxtHost.Size = new System.Drawing.Size(124, 20);
            this.TxtHost.TabIndex = 0;
            // 
            // TxtDBName
            // 
            this.TxtDBName.Location = new System.Drawing.Point(63, 38);
            this.TxtDBName.Name = "TxtDBName";
            this.TxtDBName.Size = new System.Drawing.Size(124, 20);
            this.TxtDBName.TabIndex = 1;
            // 
            // TxtPassword
            // 
            this.TxtPassword.Location = new System.Drawing.Point(63, 93);
            this.TxtPassword.Name = "TxtPassword";
            this.TxtPassword.PasswordChar = '*';
            this.TxtPassword.Size = new System.Drawing.Size(124, 20);
            this.TxtPassword.TabIndex = 3;
            // 
            // TxtLogin
            // 
            this.TxtLogin.Location = new System.Drawing.Point(63, 67);
            this.TxtLogin.Name = "TxtLogin";
            this.TxtLogin.Size = new System.Drawing.Size(124, 20);
            this.TxtLogin.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(31, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Хост";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(6, 41);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(48, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Имя БД";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 70);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(38, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Логин";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(6, 96);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(45, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Пароль";
            // 
            // ButtOpen
            // 
            this.ButtOpen.Location = new System.Drawing.Point(9, 119);
            this.ButtOpen.Name = "ButtOpen";
            this.ButtOpen.Size = new System.Drawing.Size(178, 23);
            this.ButtOpen.TabIndex = 8;
            this.ButtOpen.Text = "Войти";
            this.ButtOpen.UseVisualStyleBackColor = true;
            this.ButtOpen.Click += new System.EventHandler(this.ButtOpen_Click);
            // 
            // AuthorizationForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(196, 144);
            this.Controls.Add(this.ButtOpen);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.TxtPassword);
            this.Controls.Add(this.TxtLogin);
            this.Controls.Add(this.TxtDBName);
            this.Controls.Add(this.TxtHost);
            this.MaximumSize = new System.Drawing.Size(212, 182);
            this.MinimumSize = new System.Drawing.Size(212, 182);
            this.Name = "AuthorizationForm";
            this.Text = "База данных";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox TxtHost;
        private System.Windows.Forms.TextBox TxtDBName;
        private System.Windows.Forms.TextBox TxtPassword;
        private System.Windows.Forms.TextBox TxtLogin;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button ButtOpen;
    }
}

