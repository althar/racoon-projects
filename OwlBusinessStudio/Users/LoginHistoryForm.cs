using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Users
{
    public partial class LoginHistoryForm : Form
    {
        public LoginHistoryForm()
        {
            InitializeComponent();
        }

        private void LoginHistoryForm_Load(object sender, EventArgs e)
        {
            ComboUser.DataSource = MainForm.dbProc.executeGet("SELECT id, second_name||' '||first_name||' '||last_name AS fio FROM users WHERE role_id!=3");
            ComboUser.DisplayMember = "fio";
            ComboUser.ValueMember = "id";
            ComboUser.SelectedIndex = 0;
        }

        private void ComboUser_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ComboUser.SelectedValue is int)
            {
                int user_id = (int)ComboUser.SelectedValue;
                DataGridHistory.DataSource = MainForm.dbProc.executeGet("SELECT time, CASE came_in WHEN true THEN 'Вход' ELSE 'Выход' END AS action FROM login_history WHERE user_id="+user_id.ToString()+" ORDER BY time");
                FTwoFramework.DB.Configurator.translateToRussian(DataGridHistory);
            }
        }

    }
}
