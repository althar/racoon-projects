using System;
using System.IO;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FTwoFramework;
using FTwoFramework.DB;

namespace LowByAdmin
{
    public partial class AuthorizationForm : Form
    {
        public AuthorizationForm()
        {
            InitializeComponent();
            load();
        }

        private void ButtOpen_Click(object sender, EventArgs e)
        {
            try
            {
                string[] pars = new string[4];
                pars[0] = TxtHost.Text;
                pars[1] = TxtDBName.Text;
                pars[2] = TxtLogin.Text;
                pars[3] = TxtPassword.Text;
                Configurator.packToFile(pars, Environment.CurrentDirectory + "/configurations/dbconfig.conf");
                MainForm.dbProc = new DBProcessor(pars[0], pars[1], pars[2], pars[3]);
                MainForm.dbProc.connect();
                MainForm.self.setEnabled(true);
                Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString(),"Ошибка соединения с базой данных");
            }
        }
        private void CheckDirectory()
        {
            DirectoryInfo dir = new DirectoryInfo(Environment.CurrentDirectory + "/configurations/");
            if (!dir.Exists)
            {
                dir.Create();
            }
            FileInfo file = new FileInfo(Environment.CurrentDirectory + "/configurations/dbconfig.conf");
            if (!file.Exists)
            {
                file.Create().Close();
            }
        }
        private void load()
        {
            try
            {
                CheckDirectory();
                string[] parrs = Configurator.unpackFromFile(Environment.CurrentDirectory + "/configurations/dbconfig.conf");
                TxtHost.Text = parrs[0];
                TxtDBName.Text = parrs[1];
                TxtLogin.Text = parrs[2];
                TxtPassword.Text = parrs[3];
            }
            catch (Exception ex)
            { 
            }
        }
    }
}
