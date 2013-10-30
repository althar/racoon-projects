using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using OwlBusinessStudio;
using FTwoFramework.DB;

namespace OwlBusinessStudio.Authorization
{
    public partial class LoginForm : Form
    {
        private DataTable t;
        private MainForm owner;
        public LoginForm(MainForm parent)
        {
            InitializeComponent();
            //t = MainForm.dbProc.getTable("users");
            t = MainForm.dbProc.get("users","role_id=1 OR role_id=2 OR role_id=4");
            ComboUser.DataSource = t;
            //ComboUser.ValueMember = "role_id";
            //ComboUser.DisplayMember = "login";
            ComboUser.ValueMember = "id";
            ComboUser.DisplayMember = "login";
            owner = parent;
            owner.SetEnabled(false);
            ComboUser_SelectedIndexChanged(null, null);
        }

        private void ButtLogin_Click(object sender, EventArgs e)
        {
            if (ComboUser.SelectedItem == null)
            {
                MessageBox.Show("Выберите пользователя");
                return;
            }
            if (TxtPassword.Text.Trim() == "")
            {
                MessageBox.Show("Введте пароль");
                return;
            }
            foreach (DataRow r in t.Rows)
            {
                if ((string)r["login"] == ComboUser.Text && (string)r["password"] == TxtPassword.Text)
                {
                    owner.Enabled = true;
                    MainForm.currentUser = new User((int)r["id"], (string)r["first_name"], (string)r["second_name"], (string)r["last_name"], (string)r["email"], (string)r["phone"], (Roles)(int)r["role_id"]);
                    owner.SetUser();
                    Close();
                    if (MainForm.currentUser.Role == Roles.Admin)
                    {
                        owner.AcceptAllOrders();
                    }
                    owner.loadStaticGoods();
                    owner.TabControlMain_SelectedIndexChanged(null, null);
                    MainForm.dbProc.executeNonQuery("INSERT INTO login_history (user_id) VALUES (" + MainForm.currentUser.ID.ToString() + ")");
                    return;
                }
            }
            MessageBox.Show("Неверный логин и/или пароль.");
        }

        private void LoginForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (MainForm.currentUser == null)
            {
                owner.Finish();
            }
            else
            {
                owner.SetEnabled(true);
            }
        }

        private void TxtPassword_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Return)
            {
                ButtLogin_Click(null, null);
            }
        }

        private void ComboUser_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}
