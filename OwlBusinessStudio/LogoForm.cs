using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio
{
    public partial class LogoForm : Form
    {
        public LogoForm()
        {
            InitializeComponent();
        }
        public void setVersion(string version)
        {
            LabelVersion.Text = version;
        }
        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            System.Diagnostics.Process.Start(linkLabel1.Text);
        }
    }
}
