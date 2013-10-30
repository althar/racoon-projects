using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FTwoFramework.DB;

namespace OwlBusinessStudio.Authorization
{
    public partial class DBAuthorizationForm : Form
    {
        private MainForm owner;
        private bool ok = false;

        public DBAuthorizationForm(MainForm parent)
        {
            InitializeComponent();
            owner = parent;
            owner.SetEnabled(false);
            try
            {
                string[] parrs = Configurator.unpackFromFile(Environment.CurrentDirectory + "/configurations/dbconfig.conf");
                TxtHost.Text = parrs[0];
                TxtName.Text = parrs[1];
                TxtLogin.Text = parrs[2];
                TxtPassword.Text = parrs[3];
            }
            catch (Exception ex)
            {

            }
        }

        private void ButtKO_Click(object sender, EventArgs e)
        {
            try
            {
                MainForm.dbProc = new DBProcessor(TxtHost.Text, TxtName.Text, TxtLogin.Text, TxtPassword.Text);
                ok = true;
                string[] parss = new string[4];
                parss[0] = TxtHost.Text;
                parss[1] = TxtName.Text;
                parss[2] = TxtLogin.Text;
                parss[3] = TxtPassword.Text;
                Configurator.packToFile(parss, Environment.CurrentDirectory + "/configurations/dbconfig.conf");
                LoginForm form = new LoginForm(owner);
                form.Visible = true;
                form.Activate();
                Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Ошибка авторизации в БД: "+ex.ToString(), "Ошибка");
            }
        }

        private void DBConfigForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            
        }

        private void DBConfigForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            if (ok)
            {
                owner.SetEnabled(true);
            }
            else
            {
                owner.Finish();
            }
        }
    }
}
