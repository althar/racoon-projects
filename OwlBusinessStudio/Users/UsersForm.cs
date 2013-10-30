using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FTwoFramework.DB;

namespace OwlBusinessStudio.Users
{
    public partial class UsersForm : Form
    {
        private MainForm owner;
        private int currentUserID = 0;
        public UsersForm(MainForm parent)
        {
            owner = parent;
            owner.SetEnabled(false);
            InitializeComponent();
            LoadUsers();
            FTwoFramework.DB.Configurator.translateToRussian(DataGridUsers);
        }
        public void LoadUsers()
        {
            if (MenuButtDrivers.Checked)
            {

                DataTable users = MainForm.dbProc.executeGet("SELECT u.id,u.first_name,u.second_name,u.last_name,u.phone,u.phone_2,u.phone_3,u.email,u.password,r.name FROM users u, roles r WHERE u.role_id=r.id AND u.role_id=3");
                DataGridUsers.DataSource = users;
            }
            else
            {
                DataTable users = MainForm.dbProc.executeGet("SELECT u.id,u.first_name,u.second_name,u.last_name,u.phone,u.phone_2,u.phone_3,u.email,u.password,r.name FROM users u, roles r WHERE u.role_id=r.id AND u.role_id!=3");
                DataGridUsers.DataSource = users;
            }
        }
        public void SetEnabled(bool enabled)
        {
            Enabled = enabled;
        }
        private void UsersForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            owner.SetEnabled(true);
        }

        private void AddUserButton_Click(object sender, EventArgs e)
        {
            AddEditUserForm form = new AddEditUserForm(this, 0);
            form.Visible = true;
            form.Activate();
        }

        private void DataGridUsers_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            try
            {
                if (DataGridUsers.SelectedCells != null & DataGridUsers.SelectedCells.Count == 1)
                {
                    currentUserID = (int)DataGridUsers.SelectedCells[0].OwningRow.Cells["id"].Value;
                    ButtEditUser.Enabled = true;
                    ButtDeleteUser.Enabled = true;
                }
                else
                {
                    ButtEditUser.Enabled = false;
                    ButtDeleteUser.Enabled = false;
                }
            }
            catch
            {
                ButtEditUser.Enabled = false;
                ButtDeleteUser.Enabled = false;
            }
        }

        private void ButtDeleteUser_Click(object sender, EventArgs e)
        {
            DialogResult res = MessageBox.Show("Вы действительно хотите удалить пользователя '"
                +DataGridUsers.SelectedCells[0].OwningRow.Cells["first_name"].Value
                +" "
                + DataGridUsers.SelectedCells[0].OwningRow.Cells["second_name"].Value
                +"'", "Удаление пользователя", MessageBoxButtons.YesNo);
            if (res == System.Windows.Forms.DialogResult.Yes)
            {
                MainForm.dbProc.delete("users", currentUserID);
                MainForm.dbProc.delete("driver_metros", "driver_id=" + currentUserID.ToString());
                MainForm.dbProc.delete("driver_parameters", "user_id=" + currentUserID.ToString());
                LoadUsers();
            }
        }

        private void ButtEditUser_Click(object sender, EventArgs e)
        {
            Users.AddEditUserForm form = new AddEditUserForm(this, currentUserID);
            form.Visible = true;
            form.Activate();
        }
        private void MenuButtOperators_CheckedChanged(object sender, EventArgs e)
        {
            if (MenuButtOperators.Checked)
            {
                MenuButtDrivers.Checked = false;
            }
            LoadUsers();
        }

        private void MenuButtDrivers_CheckedChanged(object sender, EventArgs e)
        {
            if (MenuButtDrivers.Checked)
            {
                MenuButtOperators.Checked = false;
            }
            LoadUsers();
        }

        private void UsersForm_Resize(object sender, EventArgs e)
        {
            DataGridUsers.Width = Width - 40;
            DataGridUsers.Height = Height - 105;
        }
    }
}
