using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Users
{
    public partial class AddEditUserForm : Form
    {
        private UsersForm owner;
        private bool isEditing = false;
        private long id = 0;
        public AddEditUserForm(UsersForm parent,int userID)
        {
            owner = parent;
            owner.SetEnabled(false);
            isEditing = userID != 0;
            id = userID;
            InitializeComponent();

            DataTable roles = MainForm.dbProc.get("roles");
            ComboUserRole.DataSource = roles;
            ComboUserRole.DisplayMember = "name";
            ComboUserRole.ValueMember = "id";
            

            DataTable metros = MainForm.dbProc.get("metros");
            CheckListMetros.DataSource = metros;
            CheckListMetros.DisplayMember = "name";
            CheckListMetros.ValueMember = "id";
            if (isEditing)
            {
                DataTable t = MainForm.dbProc.get("users", "id=" + userID.ToString());
                DataTable driverMetros = MainForm.dbProc.get("driver_metros", "driver_id=" + userID.ToString());
                DataTable driverParams = MainForm.dbProc.get("driver_parameters", "user_id=" + userID.ToString());
                if (t.Rows.Count > 0)
                {
                    TxtEMail.Text = (string)t.Rows[0]["email"];
                    TxtLastName.Text = (string)t.Rows[0]["last_name"];
                    TxtLogin.Text = (string)t.Rows[0]["login"];
                    TxtName.Text = (string)t.Rows[0]["first_name"];
                    TxtPassword.Text = (string)t.Rows[0]["password"];
                    TxtPhone.Text = (string)t.Rows[0]["phone"];
                    TxtPhone2.Text = (string)t.Rows[0]["phone_2"];
                    TxtPhone3.Text = (string)t.Rows[0]["phone_3"];
                    TxtSecondName.Text = (string)t.Rows[0]["second_name"];
                }
                else
                {
                    MessageBox.Show("В базе не удалось найти пользователя. Вы можете создать нового.");
                }
                for (int i = 0; i < driverMetros.Rows.Count; i++)
                {
                    for (int j = 0; j < CheckListMetros.Items.Count; j++)
                    {
                        object item = CheckListMetros.Items[j];
                        int metro_id = (int)((DataRowView)item).Row["id"];
                        if ((int)driverMetros.Rows[i]["metro_id"] == metro_id)
                        {
                            CheckListMetros.SetItemChecked(j, true);
                        }
                    }
                }
                for (int i = 0; i < ComboUserRole.Items.Count; i++)
                {
                    if ((int)((DataRowView)ComboUserRole.Items[i]).Row["id"] == (int)t.Rows[0]["role_id"])
                    {
                        ComboUserRole.SelectedIndex = i;
                        break;
                    }
                }
                if (driverParams.Rows.Count > 0)
                {
                    DateTime fromTime = (DateTime)driverParams.Rows[0]["works_from"];
                    if (fromTime.Hour == 12)
                    {
                        RadioFrom12.Checked = true;
                    }
                }
            }
        }

        private void AddEditUserForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            owner.SetEnabled(true);
        }
        private int currentRole = 0;
        private void ComboUserRole_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                currentRole = (int)ComboUserRole.SelectedValue;
                //if ((int)ComboUserRole.SelectedValue == 3)// Driver
                //{
                //    CheckListMetros.Visible = true;
                //    RadioFrom9.Visible = true;
                //    RadioFrom12.Visible = true;
                //    Width = 529;
                //    MinimumSize = new Size(529,Height);
                //    MaximumSize = new Size(529, Height);
                //}
                //else
                //{
                //    CheckListMetros.Visible = false;
                //    RadioFrom9.Visible = false;
                //    RadioFrom12.Visible = false;
                //    Width = 286;
                //    MinimumSize = new Size(286, Height);
                //    MaximumSize = new Size(286, Height);
                //}
            }
            catch
            {

            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (TxtEMail.Text.Trim().Length < 1)
            {
                MessageBox.Show("Поля: " + Environment.NewLine
                    + Environment.NewLine + "Имя"
                    + Environment.NewLine + "Фамилия"
                    + Environment.NewLine + "Отчество"
                    + Environment.NewLine + "Телефон"
                    + Environment.NewLine + "eMail"
                    + Environment.NewLine + "Логин"
                    + Environment.NewLine + "Пароль"
                    + Environment.NewLine + Environment.NewLine + "Являются обязательными для заполнения.");
                TxtEMail.Focus();
                return;
            }
            if (TxtLastName.Text.Trim().Length < 1)
            {
                MessageBox.Show("Поля: " + Environment.NewLine
                    + Environment.NewLine + "Имя"
                    + Environment.NewLine + "Фамилия"
                    + Environment.NewLine + "Отчество"
                    + Environment.NewLine + "Телефон"
                    + Environment.NewLine + "eMail"
                    + Environment.NewLine + "Логин"
                    + Environment.NewLine + "Пароль"
                    + Environment.NewLine + Environment.NewLine + "Являются обязательными для заполнения.");
                TxtLastName.Focus();
                return;
            }
            if (TxtLogin.Text.Trim().Length < 1)
            {
                MessageBox.Show("Поля: " + Environment.NewLine
                    + Environment.NewLine + "Имя"
                    + Environment.NewLine + "Фамилия"
                    + Environment.NewLine + "Отчество"
                    + Environment.NewLine + "Телефон"
                    + Environment.NewLine + "eMail"
                    + Environment.NewLine + "Логин"
                    + Environment.NewLine + "Пароль"
                    + Environment.NewLine + Environment.NewLine + "Являются обязательными для заполнения.");
                TxtLogin.Focus();
                return;
            }
            if (TxtName.Text.Trim().Length < 1)
            {
                MessageBox.Show("Поля: " + Environment.NewLine
                    + Environment.NewLine + "Имя"
                    + Environment.NewLine + "Фамилия"
                    + Environment.NewLine + "Отчество"
                    + Environment.NewLine + "Телефон"
                    + Environment.NewLine + "eMail"
                    + Environment.NewLine + "Логин"
                    + Environment.NewLine + "Пароль"
                    + Environment.NewLine + Environment.NewLine + "Являются обязательными для заполнения.");
                TxtName.Focus();
                return;
            }
            if (TxtPassword.Text.Trim().Length < 1)
            {
                MessageBox.Show("Поля: " + Environment.NewLine
                    + Environment.NewLine + "Имя"
                    + Environment.NewLine + "Фамилия"
                    + Environment.NewLine + "Отчество"
                    + Environment.NewLine + "Телефон"
                    + Environment.NewLine + "eMail"
                    + Environment.NewLine + "Логин"
                    + Environment.NewLine + "Пароль"
                    + Environment.NewLine + Environment.NewLine + "Являются обязательными для заполнения.");
                TxtPassword.Focus();
                return;
            }
            if (TxtPhone.Text.Trim().Length < 1)
            {
                MessageBox.Show("Поля: " + Environment.NewLine
                    + Environment.NewLine + "Имя"
                    + Environment.NewLine + "Фамилия"
                    + Environment.NewLine + "Отчество"
                    + Environment.NewLine + "Телефон"
                    + Environment.NewLine + "eMail"
                    + Environment.NewLine + "Логин"
                    + Environment.NewLine + "Пароль"
                    + Environment.NewLine + Environment.NewLine + "Являются обязательными для заполнения.");
                TxtPhone.Focus();
                return;
            }
            if (TxtSecondName.Text.Trim().Length < 1)
            {
                MessageBox.Show("Поля: " + Environment.NewLine
                    + Environment.NewLine + "Имя"
                    + Environment.NewLine + "Фамилия"
                    + Environment.NewLine + "Отчество"
                    + Environment.NewLine + "Телефон"
                    + Environment.NewLine + "eMail"
                    + Environment.NewLine + "Логин"
                    + Environment.NewLine + "Пароль"
                    + Environment.NewLine + Environment.NewLine + "Являются обязательными для заполнения.");
                TxtSecondName.Focus();
                return;
            }
            Hashtable parameters = new Hashtable();
            parameters.Add("email", TxtEMail.Text);
            parameters.Add("last_name", TxtLastName.Text);
            parameters.Add("login", TxtLogin.Text);
            parameters.Add("first_name", TxtName.Text);
            parameters.Add("password", TxtPassword.Text);
            parameters.Add("phone", TxtPhone.Text);
            parameters.Add("phone_2", TxtPhone2.Text);
            parameters.Add("phone_3", TxtPhone3.Text);
            parameters.Add("second_name", TxtSecondName.Text);
            parameters.Add("role_id", currentRole);

            if (isEditing)
            {
                //MainForm.dbProc.delete("driver_metros","driver_id="+id.ToString()); // Delete metros for user
                MainForm.dbProc.delete("driver_parameters", "user_id=" + id.ToString()); // Delete parameters for driver
                MainForm.dbProc.update("users", parameters, "id=" + id.ToString());
            }
            else
            {
                id = MainForm.dbProc.insert("users", parameters);
            }
            if (currentRole == 3)
            {
                //StringBuilder builder = new StringBuilder();
                //for (int i = 0; i < CheckListMetros.CheckedItems.Count; i++)
                //{
                //    object item = CheckListMetros.CheckedItems[i];
                //    int metro_id = (int)((DataRowView)item).Row["id"];
                //    builder.Append("INSERT INTO driver_metros (driver_id,metro_id) VALUES (");
                //    builder.Append(id);
                //    builder.Append(",");
                //    builder.Append(metro_id);
                //    builder.Append(");");
                //}
                //string sqlStatement = builder.ToString();
                //MainForm.dbProc.executeNonQuery(sqlStatement);
                Hashtable pers = new Hashtable();
                pers.Add("user_id",id);
                if (RadioFrom9.Checked)
                {
                    pers.Add("works_from", new DateTime(2000, 1, 1, 9, 0, 0));
                }
                else
                {
                    pers.Add("works_from", new DateTime(2000, 1, 1, 12, 0, 0));
                }
                MainForm.dbProc.insert("driver_parameters", pers);
            }
            owner.LoadUsers();
            Close();
        }
    }
}
