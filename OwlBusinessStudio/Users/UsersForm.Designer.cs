namespace OwlBusinessStudio.Users
{
    partial class UsersForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(UsersForm));
            this.ToolMenu = new System.Windows.Forms.ToolStrip();
            this.ButtAddUsser = new System.Windows.Forms.ToolStripButton();
            this.ButtEditUser = new System.Windows.Forms.ToolStripButton();
            this.ButtDeleteUser = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.MenuButtDrivers = new System.Windows.Forms.ToolStripButton();
            this.MenuButtOperators = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator4 = new System.Windows.Forms.ToolStripSeparator();
            this.DataGridUsers = new System.Windows.Forms.DataGridView();
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.ToolMenu.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridUsers)).BeginInit();
            this.SuspendLayout();
            // 
            // ToolMenu
            // 
            this.ToolMenu.ImageScalingSize = new System.Drawing.Size(32, 32);
            this.ToolMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtAddUsser,
            this.ButtEditUser,
            this.ButtDeleteUser,
            this.toolStripSeparator1,
            this.toolStripSeparator2,
            this.MenuButtDrivers,
            this.MenuButtOperators,
            this.toolStripSeparator3,
            this.toolStripSeparator4});
            this.ToolMenu.Location = new System.Drawing.Point(0, 0);
            this.ToolMenu.Name = "ToolMenu";
            this.ToolMenu.Size = new System.Drawing.Size(934, 39);
            this.ToolMenu.TabIndex = 0;
            this.ToolMenu.Text = "toolStrip1";
            // 
            // ButtAddUsser
            // 
            this.ButtAddUsser.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtAddUsser.Image = ((System.Drawing.Image)(resources.GetObject("ButtAddUsser.Image")));
            this.ButtAddUsser.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtAddUsser.Name = "ButtAddUsser";
            this.ButtAddUsser.Size = new System.Drawing.Size(36, 36);
            this.ButtAddUsser.Text = "Добавить пользователя";
            this.ButtAddUsser.Click += new System.EventHandler(this.AddUserButton_Click);
            // 
            // ButtEditUser
            // 
            this.ButtEditUser.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtEditUser.Enabled = false;
            this.ButtEditUser.Image = ((System.Drawing.Image)(resources.GetObject("ButtEditUser.Image")));
            this.ButtEditUser.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtEditUser.Name = "ButtEditUser";
            this.ButtEditUser.Size = new System.Drawing.Size(36, 36);
            this.ButtEditUser.Text = "Редактировать пользователя";
            this.ButtEditUser.Click += new System.EventHandler(this.ButtEditUser_Click);
            // 
            // ButtDeleteUser
            // 
            this.ButtDeleteUser.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtDeleteUser.Enabled = false;
            this.ButtDeleteUser.Image = ((System.Drawing.Image)(resources.GetObject("ButtDeleteUser.Image")));
            this.ButtDeleteUser.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtDeleteUser.Name = "ButtDeleteUser";
            this.ButtDeleteUser.Size = new System.Drawing.Size(36, 36);
            this.ButtDeleteUser.Text = "Удалить пользователя";
            this.ButtDeleteUser.Click += new System.EventHandler(this.ButtDeleteUser_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 39);
            // 
            // MenuButtDrivers
            // 
            this.MenuButtDrivers.CheckOnClick = true;
            this.MenuButtDrivers.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.MenuButtDrivers.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtDrivers.Image")));
            this.MenuButtDrivers.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.MenuButtDrivers.Name = "MenuButtDrivers";
            this.MenuButtDrivers.Size = new System.Drawing.Size(36, 36);
            this.MenuButtDrivers.Text = "Водители";
            this.MenuButtDrivers.CheckedChanged += new System.EventHandler(this.MenuButtDrivers_CheckedChanged);
            // 
            // MenuButtOperators
            // 
            this.MenuButtOperators.Checked = true;
            this.MenuButtOperators.CheckOnClick = true;
            this.MenuButtOperators.CheckState = System.Windows.Forms.CheckState.Checked;
            this.MenuButtOperators.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.MenuButtOperators.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtOperators.Image")));
            this.MenuButtOperators.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.MenuButtOperators.Name = "MenuButtOperators";
            this.MenuButtOperators.Size = new System.Drawing.Size(36, 36);
            this.MenuButtOperators.Text = "Работники офиса";
            this.MenuButtOperators.CheckedChanged += new System.EventHandler(this.MenuButtOperators_CheckedChanged);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator4
            // 
            this.toolStripSeparator4.Name = "toolStripSeparator4";
            this.toolStripSeparator4.Size = new System.Drawing.Size(6, 39);
            // 
            // DataGridUsers
            // 
            this.DataGridUsers.AllowUserToAddRows = false;
            this.DataGridUsers.AllowUserToDeleteRows = false;
            this.DataGridUsers.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridUsers.Location = new System.Drawing.Point(12, 42);
            this.DataGridUsers.Name = "DataGridUsers";
            this.DataGridUsers.ReadOnly = true;
            this.DataGridUsers.Size = new System.Drawing.Size(910, 395);
            this.DataGridUsers.TabIndex = 1;
            this.DataGridUsers.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridUsers_CellStateChanged);
            // 
            // statusStrip1
            // 
            this.statusStrip1.Location = new System.Drawing.Point(0, 440);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.Size = new System.Drawing.Size(934, 22);
            this.statusStrip1.TabIndex = 2;
            this.statusStrip1.Text = "statusStrip1";
            // 
            // UsersForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(934, 462);
            this.Controls.Add(this.statusStrip1);
            this.Controls.Add(this.DataGridUsers);
            this.Controls.Add(this.ToolMenu);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "UsersForm";
            this.Text = "Пользователи";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.UsersForm_FormClosing);
            this.Resize += new System.EventHandler(this.UsersForm_Resize);
            this.ToolMenu.ResumeLayout(false);
            this.ToolMenu.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridUsers)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ToolStrip ToolMenu;
        private System.Windows.Forms.ToolStripButton ButtAddUsser;
        private System.Windows.Forms.ToolStripButton ButtEditUser;
        private System.Windows.Forms.ToolStripButton ButtDeleteUser;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.DataGridView DataGridUsers;
        private System.Windows.Forms.StatusStrip statusStrip1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripButton MenuButtDrivers;
        private System.Windows.Forms.ToolStripButton MenuButtOperators;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator4;
    }
}