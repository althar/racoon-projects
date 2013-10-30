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
    public partial class AddEditGoodForm : Form
    {
        private int current_good_id = 0;
        private bool is_edit = false;
        public AddEditGoodForm(int good_id)
        {
            current_good_id = good_id;
            is_edit = current_good_id != 0;
            InitializeComponent();
            loadStaticData();
            if (is_edit)
            {
                loadGood();
                Text = "Редактировать товар";
            }
        }
        private void loadStaticData()
        {

            ComboCompany.DataSource = MainForm.brands;
            ComboCompany.DisplayMember = "company";
            ComboCompany.ValueMember = "company";

            ComboAge.DataSource = MainForm.food_type_ages;
            ComboAge.DisplayMember = "food_type_age";
            ComboAge.ValueMember = "food_type_age";

            ComboAnimal.DataSource = MainForm.animals;
            ComboAnimal.DisplayMember = "animal";
            ComboAnimal.ValueMember = "animal";

            ComboFoodType.DataSource = MainForm.food_types;
            ComboFoodType.DisplayMember = "food_type";
            ComboFoodType.ValueMember = "food_type";

            ComboFoodCategory.DataSource = MainForm.food_type_categorys;
            ComboFoodCategory.DisplayMember = "food_type_category";
            ComboFoodCategory.ValueMember = "food_type_category";
        }
        private void loadGood()
        {
            DataTable good = MainForm.dbProc.executeGet("SELECT * FROM goods WHERE id="+current_good_id.ToString());
            if(good.Rows.Count>0)
            {
                TxtArticul.Text = good.Rows[0]["articul"].ToString();
                TxtBasicPrice.Text = good.Rows[0]["price_basic"].ToString();
                TxtNameForOrder.Text = good.Rows[0]["name_for_order"].ToString();
                TxtNameRus.Text = good.Rows[0]["name_rus"].ToString();
                TxtPrice.Text = good.Rows[0]["price"].ToString();
                TxtPurchaseDiscount.Text = good.Rows[0]["price_discount_percent"].ToString();
                TxtSupplier.Text = good.Rows[0]["supplier"].ToString();
                TxtWeight.Text = good.Rows[0]["weight"].ToString();
                ComboAge.Text = good.Rows[0]["food_type_age"].ToString();
                ComboAnimal.Text = good.Rows[0]["animal"].ToString();
                ComboCompany.Text = good.Rows[0]["company"].ToString();
                ComboFoodType.Text = good.Rows[0]["food_type"].ToString();
                txtDescription.Text = good.Rows[0]["description"].ToString();
                txtShortDescription.Text = good.Rows[0]["description_short"].ToString();
                ComboFoodCategory.Text = good.Rows[0]["food_type_category"].ToString();
            }
        }
        private bool checkFilling()
        {
            for (int i = 0; i < Controls.Count; i++)
            {
                if (Controls[i] is TextBox)
                {
                    if (((TextBox)Controls[i]).Text.Trim() == "")
                    {
                        MessageBox.Show("Все поля должны быть заполнены", "Ошибка заполнения");
                        return false;
                    }
                }
                if (Controls[i] is ComboBox)
                {
                    if (((ComboBox)Controls[i]).Text.Trim() == "")
                    {
                        MessageBox.Show("Все поля должны быть заполнены", "Ошибка заполнения");
                        return false;
                    }
                }
            }
            DataTable tab = null;
            if (is_edit)
            {
                tab = MainForm.dbProc.get("goods", "id!=" + current_good_id.ToString() + " AND articul='" + TxtArticul.Text.Replace(" ", "")+"'");
            }
            if (!is_edit)
            {
                tab = MainForm.dbProc.get("goods", " articul='" + TxtArticul.Text.Replace(" ", "")+"'");
            }
            if (tab.Rows.Count > 0)
            {
                MessageBox.Show("Товар с таким артикулом уже существует");
                return false;
            }
            return true;
        }
        private void ButtOK_Click(object sender, EventArgs e)
        {
            try
            {
                if (!checkFilling())
                {
                    return;
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
                parameters.Add("description", txtDescription.Text);
                parameters.Add("description_short", txtShortDescription.Text);
                parameters.Add("food_type_category", ComboFoodCategory.Text);
                parameters.Add("name_for_order_full", TxtNameForOrder.Text+" "+TxtWeight.Text.Replace(",",".")+" kg.");
                if (is_edit)
                {
                    MainForm.dbProc.update("goods", parameters,"id="+current_good_id.ToString());
                }
                else
                {
                    MainForm.dbProc.insert("goods", parameters);
                }
                
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString(), "Ошибка.");
            }
            Close();
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
