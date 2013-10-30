using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Settings
{
    public partial class SettingsEditForm : Form
    {
        private string settingName;
        private string settingSuffix;

        public SettingsEditForm(string name, string value,string suffix)
        {
            settingName = name;
            settingSuffix = suffix;
            InitializeComponent();
            if (settingName == "purchase_email")
            {
                LabelSettingName.Text = "Email для закупок";
            }
            if (settingName == "purchase_discount")
            {
                LabelSettingName.Text = "Закупочная скидка";
            }
            else
            {
                LabelSettingName.Text = name;
            }
            LabelSuffix.Text = suffix;
            LabelSettingName.Text = name;
            DataTable ta = MainForm.dbProc.executeGet("SELECT value FROM settings WHERE name='"+settingName+"'");
            if (ta.Rows.Count > 0)
            {
                TxtSettingValue.Text = ta.Rows[0]["value"].ToString();
            }
        }

        private void ButtOK_Click(object sender, EventArgs e)
        {
            Hashtable pars = new Hashtable();
            pars.Add("value",TxtSettingValue.Text);
            MainForm.dbProc.update("settings", pars, "name='" + settingName + "'");
            Close();
        }
        private string lastText = "";
        private void TxtSettingValue_TextChanged(object sender, EventArgs e)
        {
            if (settingName == "purchase_discount")
            {
                try
                {
                    int disc = Int32.Parse(TxtSettingValue.Text);
                    if (disc > 99)
                    {
                        throw new Exception();
                    }
                    lastText = TxtSettingValue.Text;
                }
                catch
                {
                    TxtSettingValue.Text = lastText;
                }
            }
        }
    }
}
