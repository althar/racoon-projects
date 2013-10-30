using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Goods
{
    public partial class AddGoodForm : Form
    {
        public AddGoodForm()
        {
            InitializeComponent();
            DataTable brands = MainForm.dbProc.executeGet("SELECT distinct company FROM goods WHERE company IS NOT NULL ORDER BY company");
            DataTable animals = MainForm.dbProc.executeGet("SELECT distinct animal FROM goods WHERE animal IS NOT NULL ORDER BY animal");
            DataTable food_types = MainForm.dbProc.executeGet("SELECT distinct food_type FROM goods WHERE food_type IS NOT NULL ORDER BY food_type");
            DataTable food_type_ages = MainForm.dbProc.executeGet("SELECT distinct food_type_age FROM goods WHERE food_type_age IS NOT NULL ORDER BY food_type_age");
            DataTable food_type_categorys = MainForm.dbProc.executeGet("SELECT distinct food_type_category FROM goods WHERE food_type_category IS NOT NULL ORDER BY food_type_category");


            ComboCompany.DataSource = brands;
            ComboCompany.DisplayMember = "company";
            ComboCompany.ValueMember = "company";

            ComboAge.DataSource = food_type_ages;
            ComboAge.DisplayMember = "food_type_age";
            ComboAge.ValueMember = "food_type_age";

            ComboAnimal.DataSource = animals;
            ComboAnimal.DisplayMember = "animal";
            ComboAnimal.ValueMember = "animal";

            ComboFoodType.DataSource = food_types;
            ComboFoodType.DisplayMember = "food_type";
            ComboFoodType.ValueMember = "food_type";

            ComboFoodCategory.DataSource = food_type_categorys;
            ComboFoodCategory.DisplayMember = "food_type_category";
            ComboFoodCategory.ValueMember = "food_type_category";
        }

        private void ButtOK_Click(object sender, EventArgs e)
        {
            try
            {
                for (int i = 0; i < Controls.Count; i++)
                {
                    if (Controls[i] is TextBox)
                    {
                        if (((TextBox)Controls[i]).Text.Trim() == "")
                        {
                            MessageBox.Show("Все поля должны быть заполнены", "Ошибка заполнения");
                            return;
                        }
                    }
                    if (Controls[i] is ComboBox)
                    {
                        if (((ComboBox)Controls[i]).Text.Trim() == "")
                        {
                            MessageBox.Show("Все поля должны быть заполнены", "Ошибка заполнения");
                            return;
                        }
                    }
                }
                DataTable tab = null;
                if (TxtWeight.Text.Trim() == "")
                {
                    tab = MainForm.dbProc.get("goods", "name_for_order='" + TxtNameForOrder.Text + "'", "id");
                }
                else
                {
                    tab = MainForm.dbProc.get("goods", "name_for_order='" + TxtNameForOrder.Text + "' AND weight = " + TxtWeight.Text.Replace(",", "."), "id");
                }
                if (tab.Rows.Count > 0)
                {
                    MessageBox.Show("Товар с таким именем и массой уже существует.", "Ошибка.");
                }
                Hashtable parameters = new Hashtable();
                parameters.Add("articul", TxtArticul.Text);
                parameters.Add("price_basic", Double.Parse(TxtBasicPrice.Text));
                parameters.Add("name_for_order", TxtNameForOrder.Text);
                parameters.Add("name_rus", TxtNameRus.Text);
                parameters.Add("price", Int32.Parse(TxtPrice.Text));
                parameters.Add("price_discount_percent", (double)(Int32.Parse(TxtPurchaseDiscount.Text)/100.0));
                parameters.Add("supplier", TxtSupplier.Text);
                parameters.Add("weight", Double.Parse(TxtWeight.Text));
                parameters.Add("food_type_age", ComboAge.Text);
                parameters.Add("animal", ComboAnimal.Text);
                parameters.Add("company", ComboCompany.Text);
                parameters.Add("food_type", ComboFoodType.Text);
                parameters.Add("food_type_category", ComboFoodCategory.Text);
                parameters.Add("name_for_order_full", TxtNameForOrder.Text+" "+TxtWeight.Text.Replace(",",".")+" kg.");
                MainForm.dbProc.insert("goods", parameters);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString(), "Ошибка.");
            }
        }
        double weight = 0;
        double basic_price = 0;
        double dummy = 0;
        int dummyInt = 0;
        private void TxtWeight_TextChanged(object sender, EventArgs e)
        {
            if (!Double.TryParse(TxtWeight.Text, out dummy))
            {
                TxtWeight.Text = weight.ToString();
                return;
            }
            weight = Double.Parse(TxtWeight.Text);
        }

        private void TxtBasicPrice_TextChanged(object sender, EventArgs e)
        {
            if (!Double.TryParse(TxtBasicPrice.Text, out dummy))
            {
                TxtBasicPrice.Text = basic_price.ToString();
                return;
            }
            basic_price = Double.Parse(TxtBasicPrice.Text);
        }
        int discount_persent = 0;
        private void TxtPurchaseDiscount_TextChanged(object sender, EventArgs e)
        {
            if (!Int32.TryParse(TxtPurchaseDiscount.Text, out dummyInt))
            {
                TxtPurchaseDiscount.Text = discount_persent.ToString();
                return;
            }
            discount_persent = Int32.Parse(TxtPurchaseDiscount.Text);
        }
        int price = 0;
        private void TxtPrice_TextChanged(object sender, EventArgs e)
        {
            if (!Int32.TryParse(TxtPrice.Text, out dummyInt))
            {
                TxtPrice.Text = price.ToString();
                return;
            }
            price = Int32.Parse(TxtPrice.Text);
        }
    }
}
