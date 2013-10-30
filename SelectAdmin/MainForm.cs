using System;
using System.IO;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using FTwoFramework.DB;
using FTwoFramework.Excel;
using FTwoFramework.Helpers;

namespace LowByAdmin
{
    public partial class MainForm : Form
    {
        public static FTwoFramework.DB.DBProcessor dbProc;
        public static MainForm self;

        private int current_user_id = 0;
        private List<String> importSuccess = new List<string>();
        private List<String> importFailure = new List<string>();
        private List<String> importNewCount = new List<string>();
        private List<String> importUpdatedCount = new List<string>();

        public MainForm()
        {
            InitializeComponent();
            self = this;
            setEnabled(false);
            AuthorizationForm af = new AuthorizationForm();
            af.Activate();
            af.Visible = true;
            af.TopMost = true;
        }
        public void setEnabled(bool enabled)
        {
            Enabled = enabled;
        }
        
        private void TabControlMain_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (TabControlMain.SelectedTab == TabUsers)// Users
            {
                loadUsers();
            }
            if (TabControlMain.SelectedTab == TabGoods)// Goods
            {
                loadGoods();
            }
            if (TabControlMain.SelectedTab == TabOrders)// orders
            {
                loadOrders();
            }
        }
        public void loadUsers()
        {
            DataGridUsers.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.AllCells;
            DataGridUsers.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            DataTable users = dbProc.executeGet("SELECT * FROM user_list");
            DataGridUsers.DataSource = users;
            Configurator.translateToRussian(DataGridUsers);
        }
        public void loadGoods()
        {
            DataGridGoods.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.AllCells;
            DataGridGoods.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            DataGridGoods.DataSource = dbProc.executeGet("SELECT id,article,brand,name,category,purchase_price,sell_price,supplier,available FROM good");
            Configurator.translateToRussian(DataGridGoods);
        }
        public void loadOrders()
        {
            DataGridOrders.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.AllCells;
            DataGridOrders.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            string query = "SELECT * FROM order_detail";
            DataGridOrders.DataSource = dbProc.executeGet(query);
            (DataGridOrders.DataSource as DataTable).DefaultView.RowFilter = string.Format("status='{0}'",ComboOrderStatuses.Text);
            Configurator.translateToRussian(DataGridOrders);
        }

        string importMessage;
        bool clearAval = false;
        private bool updatePrice(string record)
        {
            
            string[] cells = record.Split(new string[1] { ";" }, StringSplitOptions.None);
            if (cells.Length >= 3)
            {
                string brand = cells[0].Trim();
                string article = cells[1].Trim();
                string sell_price = cells[3].ToLower().Replace("руб.", "").Replace("рублей", "").Trim();
                string name = cells[2];
                string purchase_price = "";
                if (cells.Length > 4 && cells[4].ToString() != "")
                {
                    purchase_price = cells[4].ToLower().Replace("руб.", "").Replace("рублей", "").Trim();
                }
                
                double sell_price_double = 0;
                Double.TryParse(sell_price, out sell_price_double);

                double purchase_price_double = 0;
                Double.TryParse(purchase_price, out purchase_price_double);
                DataTable t = dbProc.get("good", "lower(brand)=lower('" + brand + "') AND article='" + article + "'");

                Hashtable table = new Hashtable();
                if (cells.Length > 4 && cells[4].ToString()!="")
                {
                    table.Add("purchase_price", purchase_price_double);
                }
                table.Add("sell_price", sell_price_double);
                table.Add("name", name);
                table.Add("available", 1);
                try
                {
                    // Update
                    if (t != null && t.Rows.Count > 0)
                    {
                        if (!clearAval)
                        {
                            dbProc.update("good", "available=0", "lower(brand)=lower('" + brand + "')");
                            clearAval = true;
                        }
                        dbProc.update("good", table, "lower(brand)=lower('" + brand + "') AND article='" + article + "'");
                        importUpdatedCount.Add(record);
                        importSuccess.Add(record);
                        return true;
                    }
                    // Insert
                    else
                    {
                        importFailure.Add(record);
                        return false;
                    }
                }
                catch (Exception ex)
                {
                    importFailure.Add(record);
                    return false;
                }
            }
            importFailure.Add(record);
            return false;                
        }
        private bool removeRecord(string record)
        {

            string[] cells = record.Split(new string[1] { ";" }, StringSplitOptions.None);
            if (cells.Length >= 2)
            {
                string brand = cells[0].Trim();
                string article = cells[1].Trim();
                
                DataTable t = dbProc.get("good", "lower(brand)=lower('" + brand + "') AND article='" + article + "'");
                Hashtable table = new Hashtable();
                try
                {
                    // Update
                    if (t != null && t.Rows.Count > 0)
                    {
                        table.Add("available", 0);
                        dbProc.update("good", table, "lower(brand)=lower('" + brand + "') AND article='" + article + "'");
                        importUpdatedCount.Add(record);
                        importSuccess.Add(record);
                        return true;
                    }
                    // Insert
                    else
                    {
                        importFailure.Add(record);
                        return false;
                    }
                }
                catch (Exception ex)
                {
                    importFailure.Add(record);
                    return false;
                }
            }
            importFailure.Add(record);
            return false;
        }
        private bool importGood(string record)
        {
            string[] cells = record.Split(new string[1] { ";" }, StringSplitOptions.None);
            if (cells.Length >= 9)
            {
                string brand = cells[0];
                string article = cells[1];
                string name = cells[2];
                string sell_price = cells[3].ToLower().Replace("руб.", "").Replace("рублей", "").Trim();
                string purchase_price = cells[4].ToLower().Replace("руб.", "").Replace("рублей", "").Trim();
                string supplier = cells[5];
                string available = cells[6];
                string category1 = cells[7];
                string category2 = cells[8];
                string equipment = "";
                string collection = "";
                string parent = "";
                if (cells.Length > 9)
                {
                    equipment = cells[9];
                }
                if (cells.Length > 10)
                {
                    collection = cells[10];
                }
                if (cells.Length > 11)
                {
                    parent = cells[11];
                }
                double sell_price_double = 0;
                Double.TryParse(sell_price, out sell_price_double);

                double purchase_price_double = 0;
                Double.TryParse(purchase_price, out purchase_price_double);

                int available_int = 0;
                Int32.TryParse(available, out available_int);
                string category = "";
                if (category1.Length > 1)
                {
                    category += category1.Substring(0, 1).ToUpper() + category1.Substring(1).ToLower();
                }
                if (category2.Length > 1)
                {
                    category += "|" + category2.Substring(0, 1).ToUpper() + category2.Substring(1).ToLower();
                }
                DataTable t = dbProc.get("good", "brand='" + brand + "' AND article='" + article + "'");

                Hashtable table = new Hashtable();
                table.Add("purchase_price", purchase_price_double);
                table.Add("sell_price", sell_price_double);
                table.Add("available", available_int);
                table.Add("equipment", equipment);
                table.Add("collection", collection);
                //table.Add("parent_id", description);

                try
                {
                    // Update
                    if (t != null && t.Rows.Count > 0)
                    {
                        dbProc.update("good", table, "brand='" + brand + "' AND article='" + article + "'");
                        importUpdatedCount.Add(record);
                    }
                    // Insert
                    else
                    {
                        if (article.ToLower() != "артикул" && article.Length > 0 && brand.Length > 0)
                        {
                            table.Add("store_id", 1);
                            table.Add("brand", brand);
                            table.Add("name", name);
                            table.Add("article", article);
                            table.Add("supplier", supplier);
                            table.Add("category", category);
                            dbProc.insert("good", table);
                            importNewCount.Add(record);
                        }
                    }
                    importSuccess.Add(record);
                    return true;
                }
                catch (Exception ex)
                {
                    importFailure.Add(record);
                    return false;
                }
            }
            importFailure.Add(record);
            return false;
        }
        private void ButtMenuImportGoods_Click(object sender, EventArgs e)
        {
            try
            {
                importNewCount = new List<string>();
                importUpdatedCount = new List<string>();
                importSuccess = new List<string>();
                importFailure = new List<string>();
                MessageBox.Show("Обновить категории?", "Категории", MessageBoxButtons.YesNo);
                DialogResult res = DialogOpenImportGoods.ShowDialog();

                if (res==System.Windows.Forms.DialogResult.OK)
                {
                    string fileName = DialogOpenImportGoods.FileName;
                    StreamReader reader = new StreamReader(fileName, Encoding.Default);
                    string record;
                    while ((record = reader.ReadLine()) != null)
                    {
                        importGood(record);
                        LabelProcessDescription.Text = "Импорт товара: (" + importSuccess.Count + " успешно / " + importFailure.Count + " не удалось импортировать)";
                        Application.DoEvents();
                    }
                    reader.Close();
                    normalizeCategories();
                    MessageBox.Show("Успешно импортированы: " + importSuccess.Count
                        +Environment.NewLine
                        + "Не удалось импортировать: " + importFailure.Count
                        + Environment.NewLine
                        + Environment.NewLine
                        + "Новых товаров: " + importNewCount.Count
                        + Environment.NewLine
                        + "Обновлено товаров: " + importUpdatedCount.Count
                        + Environment.NewLine
                        +importMessage, "Результат импорта");
                }
                
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }
        private void normalizeCategories()
        {
            int success = 0, failure = 0;
            
            DataTable categories = dbProc.executeGet("SELECT DISTINCT (regexp_split_to_array(category, E'\\\\|'))[1] AS name FROM good");
            foreach (DataRow cat in categories.Rows)
            {
                string catName = cat["name"].ToString();
                if(!dbProc.exists("SELECT * FROM category WHERE name='"+catName+"'"))
                {
                    Hashtable tab = new Hashtable();
                    tab.Add("name",catName);
                    tab.Add("full_name",catName);
                    dbProc.insert("category", tab);
                }
                DataRow catRow = dbProc.getRow("SELECT * FROM category WHERE name='"+catName+"'");
                // Update all goods with this category
                dbProc.update("good", "category_id="+catRow["id"], "category='"+catRow["full_name"]+"'");

                // Get all subcategories...
                DataTable subcategories = dbProc.executeGet("SELECT DISTINCT (regexp_split_to_array(category, E'\\\\|'))[2] AS name FROM good WHERE category LIKE '"+catName+"|%'");
                foreach (DataRow subcat in subcategories.Rows)
                {
                    string subcatName = subcat["name"].ToString();
                    if (!dbProc.exists("SELECT * FROM category WHERE name='" + subcatName + "'"))
                    {
                        Hashtable tab = new Hashtable();
                        tab.Add("name", subcatName);
                        tab.Add("full_name", catName+"|"+subcatName);
                        tab.Add("parent_id", catRow["id"]);
                        dbProc.insert("category", tab);
                    }
                    DataRow subcatRow = dbProc.getRow("SELECT * FROM category WHERE full_name='" + catName + "|" + subcatName + "'");
                    // Update all goods with this category
                    dbProc.update("good", "category_id=" + subcatRow["id"], "category='" + subcatRow["full_name"] + "'");

                    success++;
                    LabelProcessDescription.Text = "Импорт категорий: (" + success + " успешно / " + failure + " не удалось импортировать)";
                    Application.DoEvents();
                }
            }
        }

        private long currentGoodId = 0;

        private void ButtEditGood_Click(object sender, EventArgs e)
        {
            Goods.FormEditGood f = new Goods.FormEditGood(currentGoodId);
            f.Visible = true;
            f.Activate();
        }

        private void DataGridGoods_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                currentGoodId = (long)DataGridGoods.Rows[e.RowIndex].Cells[0].Value;
                ButtEditGood.Enabled = currentGoodId != 0;
                Text = currentGoodId.ToString();
            }
            catch (Exception ex)
            {
                ButtEditGood.Enabled = false;
            }
        }

        private void ButtRefreshGoods_Click(object sender, EventArgs e)
        {
            loadGoods();
        }

        private void ButtArticles_Click(object sender, EventArgs e)
        {
            Administration.ArticlesForm f = new Administration.ArticlesForm();
            f.Activate();
            f.Visible = true;
        }

        private void ButtShowOrders_Click(object sender, EventArgs e)
        {
            loadOrders();
        }

        private void ComboOrderStatuses_SelectedIndexChanged(object sender, EventArgs e)
        {
            (DataGridOrders.DataSource as DataTable).DefaultView.RowFilter = string.Format("status='{0}'", ComboOrderStatuses.Text);
        }

        private void ButtSell12_Click(object sender, EventArgs e)
        {

        }

        private void DataGridOrders_CellMouseDoubleClick(object sender, DataGridViewCellMouseEventArgs e)
        {

        }
        private long currentOrderId = 0;
        private void DataGridOrders_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            currentOrderId = 0;            
            if (long.TryParse(DataGridOrders.Rows[e.RowIndex].Cells["id"].Value.ToString(), out currentOrderId))
            {
                ComboOrderStatusChange.Enabled = true;
                ComboOrderStatusChange.Text = DataGridOrders.Rows[e.RowIndex].Cells["status"].Value.ToString();
            }
            else
            {
                ComboOrderStatusChange.Enabled = false;
                ComboOrderStatusChange.Text = "";
            }
        }

        private void ComboOrderStatusChange_SelectedIndexChanged(object sender, EventArgs e)
        {
            dbProc.update("order_list", "status='" + ComboOrderStatusChange.Text + "'", "id=" + currentOrderId);
            loadOrders();
        }

        private void ButtMenuUpdatePrices_Click(object sender, EventArgs e)
        {
            try
            {
                clearAval = false;
                importNewCount = new List<string>();
                importUpdatedCount = new List<string>();
                importSuccess = new List<string>();
                importFailure = new List<string>();
                DialogResult res = DialogOpenImportGoods.ShowDialog();

                if (res == System.Windows.Forms.DialogResult.OK)
                {
                    string fileName = DialogOpenImportGoods.FileName;
                    StreamReader reader = new StreamReader(fileName, Encoding.Default);
                    string record;
                    while ((record = reader.ReadLine()) != null)
                    {
                        updatePrice(record);
                        LabelProcessDescription.Text = "Обновление цен: (" + importSuccess.Count + " успешно / " + importFailure.Count + " не удалось обновить)";
                        Application.DoEvents();
                    }
                    reader.Close();
                    normalizeCategories();
                    MessageBox.Show("Успешно обновлено: " + importSuccess.Count
                        + Environment.NewLine
                        + "Не удалось обновить: " + importFailure.Count
                        + Environment.NewLine);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void ButtMenuHideGoods_Click(object sender, EventArgs e)
        {
            try
            {
                clearAval = false;
                importNewCount = new List<string>();
                importUpdatedCount = new List<string>();
                importSuccess = new List<string>();
                importFailure = new List<string>();
                DialogResult res = DialogOpenImportGoods.ShowDialog();

                if (res == System.Windows.Forms.DialogResult.OK)
                {
                    string fileName = DialogOpenImportGoods.FileName;
                    StreamReader reader = new StreamReader(fileName, Encoding.Default);
                    string record;
                    while ((record = reader.ReadLine()) != null)
                    {
                        removeRecord(record);
                        LabelProcessDescription.Text = "Удаление из ассортимента: (" + importSuccess.Count + " успешно / " + importFailure.Count + " не удалось удалить)";
                        Application.DoEvents();
                    }
                    reader.Close();
                    normalizeCategories();
                    MessageBox.Show("Успешно удалено: " + importSuccess.Count
                        + Environment.NewLine
                        + "Не удалось удалить: " + importFailure.Count
                        + Environment.NewLine);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }
    }
}
