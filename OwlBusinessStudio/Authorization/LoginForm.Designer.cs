namespace OwlBusinessStudio.Authorization
{
    partial class LoginForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(LoginForm));
            this.ComboUser = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.TxtPassword = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.ButtLogin = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // ComboUser
            // 
            this.ComboUser.FormattingEnabled = true;
            this.ComboUser.Location = new System.Drawing.Point(98, 12);
            this.ComboUser.Name = "ComboUser";
            this.ComboUser.Size = new System.Drawing.Size(401, 21);
            this.ComboUser.TabIndex = 0;
            this.ComboUser.SelectedIndexChanged += new System.EventHandler(this.ComboUser_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(80, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Пользователь";
            // 
            // TxtPassword
            // 
            this.TxtPassword.Location = new System.Drawing.Point(98, 39);
            this.TxtPassword.Name = "TxtPassword";
            this.TxtPassword.PasswordChar = '*';
            this.TxtPassword.Size = new System.Drawing.Size(401, 20);
            this.TxtPassword.TabIndex = 2;
            this.TxtPassword.KeyDown += new System.Windows.Forms.KeyEventHandler(this.TxtPassword_KeyDown);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 42);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(45, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Пароль";
            // 
            // ButtLogin
            // 
            this.ButtLogin.Location = new System.Drawing.Point(15, 65);
            this.ButtLogin.Name = "ButtLogin";
            this.ButtLogin.Size = new System.Drawing.Size(484, 23);
            this.ButtLogin.TabIndex = 4;
            this.ButtLogin.Text = "Вход";
            this.ButtLogin.UseVisualStyleBackColor = true;
            this.ButtLogin.Click += new System.EventHandler(this.ButtLogin_Click);
            // 
            // LoginForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(502, 94);
            this.Controls.Add(this.ButtLogin);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.TxtPassword);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.ComboUser);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximumSize = new System.Drawing.Size(518, 132);
            this.MinimumSize = new System.Drawing.Size(518, 132);
            this.Name = "LoginForm";
            this.Text = "Авторизация";
            this.TopMost = true;
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.LoginForm_FormClosing);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox ComboUser;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox TxtPassword;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button ButtLogin;
    }
}