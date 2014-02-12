using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Orders
{
    public partial class FindGoodsForm : Form
    {
        private OrderItem item;
        private OrderList list;
        private bool firstTime = true;
        private string pictureBaseUrl;

        public FindGoodsForm(OrderItem orderItem,OrderList orderList)
        {
            MainForm.goodsFull = MainForm.dbProc.executeGet(MainForm.fullGoods);
            InitializeComponent();
            item = orderItem;
            list = orderList;
            loadFilters();
            DataTable picUrl = MainForm.dbProc.executeGet("SELECT value FROM settings WHERE name = 'picture_base_url'");
            if (picUrl.Rows.Count > 0)
            {
                pictureBaseUrl = (string)picUrl.Rows[0]["value"];
                ButtViewGoodPicture.Visible = true;
            }
            else
            {
                ButtViewGoodPicture.Visible = false;
            }
            applyStaticFilters();
            orderItem.clearText();
        }

        private void loadFilters()
        {
            DataTable brands = MainForm.dbProc.executeGet("SELECT distinct company FROM goods WHERE company IS NOT NULL ORDER BY company");
            DataTable weights = MainForm.dbProc.executeGet("SELECT distinct weight FROM goods WHERE weight IS NOT NULL ORDER BY weight");
            DataTable animals = MainForm.dbProc.executeGet("SELECT distinct animal FROM goods WHERE animal IS NOT NULL ORDER BY animal");
            DataTable food_types = MainForm.dbProc.executeGet("SELECT distinct food_type FROM goods WHERE food_type IS NOT NULL ORDER BY food_type");
            DataTable food_type_ages = MainForm.dbProc.executeGet("SELECT distinct food_type_age FROM goods WHERE food_type_age IS NOT NULL ORDER BY food_type_age");
            DataTable food_type_categorys = MainForm.dbProc.executeGet("SELECT distinct food_type_category FROM goods WHERE food_type_category IS NOT NULL ORDER BY food_type_category");


            CheckListBrand.DataSource = brands;
            CheckListBrand.DisplayMember = "company";
            CheckListBrand.ValueMember = "company";

            CheckListAge.DataSource = food_type_ages;
            CheckListAge.DisplayMember = "food_type_age";
            CheckListAge.ValueMember = "food_type_age";

            CheckListAnimal.DataSource = animals;
            CheckListAnimal.DisplayMember = "animal";
            CheckListAnimal.ValueMember = "animal";

            CheckListFoodType.DataSource = food_types;
            CheckListFoodType.DisplayMember = "food_type";
            CheckListFoodType.ValueMember = "food_type";

            CheckListUseType.DataSource = food_type_categorys;
            CheckListUseType.DisplayMember = "food_type_category";
            CheckListUseType.ValueMember = "food_type_category";

            CheckListWeight.DataSource = weights;
            CheckListWeight.DisplayMember = "weight";
            CheckListWeight.ValueMember = "weight";
        }
        
        private void applyStaticFilters()
        {
            ComboNameForOrder.DataSource = MainForm.goodsFull;
            ComboNameForOrder.DisplayMember = "name_for_order";
            ComboNameForOrder.ValueMember = "id";
            ComboNameForOrder.AutoCompleteSource = AutoCompleteSource.ListItems;
            ComboNameForOrder.AutoCompleteMode = AutoCompleteMode.SuggestAppend;

            ComboNameRus.DataSource = MainForm.goodsRus;
            ComboNameRus.DisplayMember = "name_rus";
            ComboNameRus.ValueMember = "id";
            ComboNameRus.AutoCompleteSource = AutoCompleteSource.ListItems;
            ComboNameRus.AutoCompleteMode = AutoCompleteMode.SuggestAppend;

            ComboNameForOrder.Text = "";
            ComboNameRus.Text = "";
            ComboNameForOrder.SelectedIndex = -1;
            ComboNameRus.SelectedItem = -1;
            StringBuilder filter = new StringBuilder();
            for (int i = 0; i < CheckListBrand.CheckedIndices.Count; i++)
            {
                if (i == 0)
                {
                    filter.Append(" company IN (");
                }
                filter.Append("'");
                filter.Append(((DataRowView)CheckListBrand.CheckedItems[i]).Row["company"]);
                filter.Append("' ");
                if (i != CheckListBrand.CheckedIndices.Count - 1)
                {
                    filter.Append(",");
                }
                if (i == CheckListBrand.CheckedIndices.Count - 1)
                {
                    filter.Append(")");
                }
            }
            for (int i = 0; i < CheckListAge.CheckedIndices.Count; i++)
            {
                if (i == 0)
                {
                    if (filter.Length > 0)
                    {
                        filter.Append(" AND ");
                    }
                    filter.Append(" food_type_age IN (");
                }
                filter.Append("'");
                filter.Append(((DataRowView)CheckListAge.CheckedItems[i]).Row["food_type_age"]);
                filter.Append("' ");
                if (i != CheckListAge.CheckedIndices.Count - 1)
                {
                    filter.Append(",");
                }
                if (i == CheckListAge.CheckedIndices.Count - 1)
                {
                    filter.Append(")");
                }
            }
            for (int i = 0; i < CheckListAnimal.CheckedIndices.Count; i++)
            {
                if (i == 0)
                {
                    if (filter.Length > 0)
                    {
                        filter.Append(" AND ");
                    }
                    filter.Append(" animal IN (");
                }
                filter.Append("'");
                filter.Append(((DataRowView)CheckListAnimal.CheckedItems[i]).Row["animal"]);
                filter.Append("' ");
                if (i != CheckListAnimal.CheckedIndices.Count - 1)
                {
                    filter.Append(",");
                }
                if (i == CheckListAnimal.CheckedIndices.Count - 1)
                {
                    filter.Append(")");
                }
            }
            for (int i = 0; i < CheckListFoodType.CheckedIndices.Count; i++)
            {
                if (i == 0)
                {
                    if (filter.Length > 0)
                    {
                        filter.Append(" AND ");
                    }
                    filter.Append(" food_type IN (");
                }
                filter.Append("'");
                filter.Append(((DataRowView)CheckListFoodType.CheckedItems[i]).Row["food_type"]);
                filter.Append("' ");
                if (i != CheckListFoodType.CheckedIndices.Count - 1)
                {
                    filter.Append(",");
                }
                if (i == CheckListFoodType.CheckedIndices.Count - 1)
                {
                    filter.Append(")");
                }
            }
            for (int i = 0; i < CheckListUseType.CheckedIndices.Count; i++)
            {
                if (i == 0)
                {
                    if (filter.Length > 0)
                    {
                        filter.Append(" AND ");
                    }
                    filter.Append(" food_type_category IN (");
                }
                filter.Append("'");
                filter.Append(((DataRowView)CheckListUseType.CheckedItems[i]).Row["food_type_category"]);
                filter.Append("' ");
                if (i != CheckListUseType.CheckedIndices.Count - 1)
                {
                    filter.Append(",");
                }
                if (i == CheckListUseType.CheckedIndices.Count - 1)
                {
                    filter.Append(")");
                }
            }
            for (int i = 0; i < CheckListWeight.CheckedIndices.Count; i++)
            {
                if (i == 0)
                {
                    if (filter.Length > 0)
                    {
                        filter.Append(" AND ");
                    }
                    filter.Append(" weight IN (");
                }
                filter.Append("'");
                filter.Append(((DataRowView)CheckListWeight.CheckedItems[i]).Row["weight"]);
                filter.Append("' ");
                if (i != CheckListWeight.CheckedIndices.Count - 1)
                {
                    filter.Append(",");
                }
                if (i == CheckListWeight.CheckedIndices.Count - 1)
                {
                    filter.Append(")");
                }
            }
            string totalFilter = filter.ToString();
            if (TxtArticul.Text.Replace(" ","") != "")
            {
                totalFilter = "articul = '"+TxtArticul.Text+"'";
            }
            ((DataTable)ComboNameForOrder.DataSource).DefaultView.RowFilter = totalFilter;
            ((DataTable)ComboNameRus.DataSource).DefaultView.RowFilter = totalFilter;
        }
        private void button1_Click(object sender, EventArgs e)
        {
            if (currentGoodID == 0)
            {
                MessageBox.Show("Вы не выбрали товар или нет товара подходящего под все условия фильтра");
                return;
            }
            DataTable t = MainForm.dbProc.executeGet("SELECT price,name_for_order_full FROM goods WHERE id=" + currentGoodID.ToString());
            item.loadItem(currentGoodID, 1, (string)t.Rows[0]["name_for_order_full"], (int)t.Rows[0]["price"]);
            Close();
        }

        private void CheckListBrand_MouseUp(object sender, MouseEventArgs e)
        {
            applyStaticFilters();
        }
        private int currentGoodID = 0;
        private string currentPictureUrl = "";
        private string currentNameForOrder = "";

        private void ComboNameForOrder_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                int rusIndex = -1;
                currentGoodID = ((int)ComboNameForOrder.SelectedValue);
                currentPictureUrl = ((DataRowView)ComboNameForOrder.SelectedItem).Row["photo_url"].ToString();
                currentNameForOrder = ((DataRowView)ComboNameForOrder.SelectedItem).Row["name_for_order"].ToString();
                if (currentPictureUrl == "")
                {
                    ButtViewGoodPicture.Enabled = false;
                }
                else
                {
                    ButtViewGoodPicture.Enabled = true;
                }
                for (int i = 0; i < ComboNameRus.Items.Count; i++)
                {
                    if ((int)((DataRowView)ComboNameRus.Items[i]).Row["id"] == currentGoodID)
                    {
                        rusIndex = i;
                        break;
                    }
                }
                if (rusIndex != -1)
                {
                    ComboNameRus.SelectedIndex = rusIndex;
                }
                else
                {
                    ComboNameRus.SelectedIndex = -1;
                    ComboNameRus.Text = "";
                }
                Text = currentGoodID.ToString();
            }
            catch (Exception ex)
            {
                //MessageBox.Show("!!");
            }
        }

        private void ComboNameRus_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                if (ComboNameRus.SelectedIndex != -1)
                {
                    int forOrderIndex = -1;
                    currentGoodID = ((int)ComboNameRus.SelectedValue);
                    for (int i = 0; i < ComboNameForOrder.Items.Count; i++)
                    {
                        if ((int)((DataRowView)ComboNameForOrder.Items[i]).Row["id"] == currentGoodID)
                        {
                            forOrderIndex = i;
                            //ComboNameForOrder.SelectedIndex = i;
                            break;
                        }
                    }
                    if (forOrderIndex != -1)
                    {
                        ComboNameForOrder.SelectedIndex = forOrderIndex;
                    }
                    else
                    {
                        ComboNameForOrder.Text = "";
                    }
                }
            }
            catch (Exception ex)
            {

            }
        }

        private void FindGoodsForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            if (currentGoodID == 0)
            {
                list.removeItem(item);
            }
        }

        private void FindGoodsForm_Load(object sender, EventArgs e)
        {
            ComboNameForOrder.Text = "";
            ComboNameRus.Text = "";
            ComboNameForOrder.SelectedIndex = -1;
            ComboNameRus.SelectedItem = -1;
            currentGoodID = 0;
        }

        private void ButtViewGoodPicture_Click(object sender, EventArgs e)
        {
            PictureViewForm f = new PictureViewForm(pictureBaseUrl + "/" + currentPictureUrl, currentNameForOrder);
            f.Visible = true;
            f.Activate();
        }

        private void TxtArticul_TextChanged(object sender, EventArgs e)
        {
            applyStaticFilters();
            if (ComboNameForOrder.Items.Count > 0)
            {
                ComboNameForOrder.SelectedItem = ComboNameForOrder.Items[0];
                currentGoodID = (int)ComboNameForOrder.SelectedValue;
                Text = currentGoodID.ToString();
            }
        }
    }
}
