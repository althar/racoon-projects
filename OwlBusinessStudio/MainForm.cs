using System;
using System.Threading;
using System.IO;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Data.OleDb;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using OwlBusinessStudio.Authorization;
using FTwoFramework.DB;
using OwlBusinessStudio.Goods;
using FTwoFramework.Excel;
using System.Web;
using System.Net;
using System.Xml;
using System.Runtime.InteropServices;

namespace OwlBusinessStudio
{
    public partial class MainForm : Form
    {
        public static DBProcessor dbProc;
        public static User currentUser;
        public static DataTable goodsRus;
        public static DataTable goodsFull;
        public static DataTable brands;
        public static DataTable animals;
        public static DataTable food_types;
        public static DataTable food_type_ages;
        public static DataTable food_type_categorys;

        private int LastTabIndex = -1;
        private int current_page = 1;

        private List<int> currentGoodIDs = new List<int>();
        private int currentOrderID = 0;
        private int currentOrderStatus = 0;

        public static string rusGoods = "SELECT id,articul,name_rus AS name_rus,company,food_type_age,animal,food_type,food_type_category,weight"
            + " FROM goods WHERE name_rus IS NOT NULL AND enabled AND name_rus !='' ORDER BY name_rus";
        public static string fullGoods = "SELECT g.id,g.articul,g.name_for_order||' '||CASE COALESCE(g.weight,'') WHEN '' THEN '' ELSE g.weight END || '      \t('|| quantity - COALESCE(ords.ordered,0)"
    + "||' на складе)' AS name_for_order,g.company,g.food_type_age,g.animal,g.food_type,g.food_type_category,g.weight,g.photo_url,ords.ordered FROM "
    + "goods g LEFT JOIN (SELECT good_id,sum(count) AS ordered FROM orders_with_details WHERE status_id=1 GROUP BY good_id) ords ON g.id = ords.good_id "
    + "WHERE g.articul IS NOT NULL AND g.enabled ORDER BY g.name_for_order";

        public MainForm()
        {
            InitializeComponent();
            createDirectories();
        }

        public void loadStaticGoods()
        {
            goodsRus = dbProc.executeGet(rusGoods);
            goodsFull = dbProc.executeGet(fullGoods);
            brands = MainForm.dbProc.executeGet("SELECT distinct company FROM goods WHERE company IS NOT NULL ORDER BY company");
            animals = MainForm.dbProc.executeGet("SELECT distinct animal FROM goods WHERE animal IS NOT NULL ORDER BY animal");
            food_types = MainForm.dbProc.executeGet("SELECT distinct food_type FROM goods WHERE food_type IS NOT NULL ORDER BY food_type");
            food_type_ages = MainForm.dbProc.executeGet("SELECT distinct food_type_age FROM goods WHERE food_type_age IS NOT NULL ORDER BY food_type_age");
            food_type_categorys = MainForm.dbProc.executeGet("SELECT distinct food_type_category FROM goods WHERE food_type_category IS NOT NULL ORDER BY food_type_category");

        }
        public void MainForm_Load(object sender, EventArgs e)
        {
            try
            {
                DirectoryInfo dirConf = new DirectoryInfo(Environment.CurrentDirectory + "/configurations/");
                if (!dirConf.Exists)
                {
                    dirConf.Create();
                }
                dbProc = new DBProcessor(Environment.CurrentDirectory + "/configurations/dbconfig.conf");
                LoginForm form = new LoginForm(this);
                Enabled = false;
                form.Activate();
                form.Visible = true;
            }
            catch
            {
                //MessageBox.Show(ex.ToString());
                DBAuthorizationForm f = new DBAuthorizationForm(this);
                f.Activate();
                f.Visible = true;
            }
            TabControlMain_SelectedIndexChanged(null, null);
        }
        private TabPage tabPurchase = null;
        
        public void SetUser()
        {
            // Admin
            ToolStripMenuAdministration.Visible = true;
            ToolStripMenuStatistics.Visible = true;
            tabPurchase = TabPurchase;
            if (!TabControlMain.Contains(tabPurchase))
            {
                TabControlMain.TabPages.Insert(2, tabPurchase);
            }
            ButtGenerateReceipt.Visible = true;
            ButtGenerateReceipt.Enabled = true;
            ButtGenerateDeliverListsFile.Visible = true;
            ButtEditDeliveryLists.Visible = true;
            ButtPurchaseForOrdersForm.Visible = true;
            ButtPurchaseForStoreForm.Visible = true;
            contextMenuGoods.Enabled = true;

            if (currentUser.Role == Roles.Operator||currentUser.Role == Roles.Driver)
            {
                MenuButtUsers.Visible = false;
                MenuButtImportGoods.Visible = false;
                TabControlMain.TabPages.Remove(tabPurchase);
                ToolStripMenuAdministration.Visible = false;
                ToolStripMenuStatistics.Visible = false;
                //ButtGenerateReceipt.Visible = false;
                //ButtGenerateDeliverListsFile.Visible = false;
                ButtEditDeliveryLists.Visible = false;
                ButtPurchaseForOrdersForm.Visible = false;
                ButtPurchaseForStoreForm.Visible = false;
                contextMenuGoods.Enabled = false;
            }
            if (currentUser.Role == Roles.Partner)
            {
                MenuButtUsers.Visible = false;
                MenuButtImportGoods.Visible = false;
                ToolStripMenuAdministration.Visible = false;
                ToolStripMenuStatistics.Visible = false;
                ButtAddGood.Visible = false;
                ButtEditGood.Visible = false;
                ButtDeleteGood.Visible = false;
                ButtPurchaseForOrdersForm.Visible = false;
                ButtPurchaseForStoreForm.Visible = false;
                TabControlMain.TabPages.Remove(TabDeliveryLists);
                TabControlMain.TabPages.Remove(TabGoods);
                TabControlMain.TabPages.Remove(TabPurchase);
            }
        }
        public void SetEnabled(bool enabled)
        {
            Enabled = enabled;
        }
        public void AcceptAllOrders()
        {
            //calculateGoods(true);
        }
        //public void AcceptAllOrders()
        //{
        //    string processing = dbProc.getSetting("processing");
        //    if (processing == "false")
        //    {
        //        dbProc.update("settings", "value='true'", "name='processing'");
        //        ButtProcess.Visible = true;

        //        // 1) New orders with delivery...
        //        DataTable ordersToAccept = dbProc.executeGet("SELECT * FROM orders_with_details WHERE deliver_date<date(now()) AND status_id=1 AND deliver=true");
        //        Application.DoEvents();
        //        for (int i = 0; i < ordersToAccept.Rows.Count; i++)
        //        {
        //            LabelProcessName.Text = "Пересчет запасов по выполненным заказам (" + i + " из " + ordersToAccept.Rows.Count + ")";
        //            int count = (int)ordersToAccept.Rows[i]["count"];
        //            int good_id = (int)ordersToAccept.Rows[i]["good_id"];
        //            int order_id = (int)ordersToAccept.Rows[i]["id"];
        //            string c = "";
        //            if (count > 0)
        //            {
        //                c = "UPDATE goods SET quantity=quantity-" + count.ToString() + " WHERE id=" + good_id.ToString() + "; ";
        //            }
        //            if (count < 0)
        //            {
        //                c = "UPDATE goods SET quantity=quantity+" + Math.Abs(count).ToString() + " WHERE id=" + good_id.ToString() + "; ";
        //            }
        //            DataTable before = MainForm.dbProc.executeGet("SELECT quantity FROM goods WHERE id=" + good_id.ToString());
        //            dbProc.executeNonQuery(c);
        //            DataTable after = MainForm.dbProc.executeGet("SELECT quantity FROM goods WHERE id=" + good_id.ToString());
        //            Application.DoEvents();
        //            if (before.Rows.Count == 1)
        //            {
        //                int q_before = (int)before.Rows[0]["quantity"];
        //                int q_after = (int)after.Rows[0]["quantity"];
        //                MainForm.dbProc.executeNonQuery("INSERT INTO goods_history (action,good_id,quantity_before,quantity_after,description,good_action_count) VALUES('orders_accept'," + good_id + "," + q_before + "," + q_after + ",'Order:" + order_id + "'," + count + ");");
        //            }
        //            Application.DoEvents();
        //        }

        //        // 2) Move self gets to next day...
        //        DataTable ordersToMoveDistinct = dbProc.executeGet("SELECT DISTINCT id FROM orders_with_details WHERE deliver_date<date(now()) AND status_id=1 AND deliver=false");
        //        Application.DoEvents();
        //        for (int i = 0; i < ordersToMoveDistinct.Rows.Count; i++)// Selfget - move order one day forward...
        //        {
        //            LabelProcessName.Text = "Перенос самовывозов на следующий день (" + i + " из " + ordersToMoveDistinct.Rows.Count + ")";
        //            int order_id = (int)ordersToMoveDistinct.Rows[i]["id"];
        //            string c = "UPDATE orders SET deliver_date = deliver_date+INTERVAL '1 day' WHERE id=" + order_id.ToString() + ";";
        //            dbProc.executeNonQuery(c);
        //            Application.DoEvents();
        //        }

        //        // 3) Change deliver statuses...
        //        DataTable ordersToAcceptDistinct = dbProc.executeGet("SELECT DISTINCT id FROM orders_with_details WHERE deliver_date<date(now()) AND status_id=1 AND deliver=true");
        //        Application.DoEvents();
        //        for (int i = 0; i < ordersToAcceptDistinct.Rows.Count; i++)// Delivers - just change statuses...
        //        {
        //            LabelProcessName.Text = "Перенос заказов в \"выполненные\" (" + i + " из " + ordersToAcceptDistinct.Rows.Count + ")";
        //            int order_id = (int)ordersToAcceptDistinct.Rows[i]["id"];
        //            string c = "UPDATE orders SET status=3 WHERE id=" + order_id.ToString() + ";";
        //            dbProc.executeNonQuery(c);
        //            Application.DoEvents();
        //        }
        //        LabelProcessName.Text = "";
        //        ButtProcess.Visible = false;
        //        dbProc.update("settings", "value='false'", "name='processing'");
        //    }
        //}

        public void Finish()
        {
            //TODO: save statement...
            if (dbProc != null)
            {
                dbProc.disconnect();
            }
            Close();
        }

        private void MenuButtImportGoods_Click(object sender, EventArgs e)
        {
            string full = ((ToolStripMenuItem)sender).Tag.ToString();
            DialogResult res = OpenFileGoods.ShowDialog();
            if (res == System.Windows.Forms.DialogResult.OK)
            {
                DataTable t = ExcelProcessor.getTable(OpenFileGoods.FileName, "Database");
                int failedCount = 0;
                int successCount = 0;
                LabelProcessName.Text = "Инициализация импорта";
                Application.DoEvents();
                if (full == "true")
                {
                    dbProc.executeNonQuery("UPDATE goods SET is_hidden=TRUE, enabled=FALSE");
                }
                List<Object[]> fail_table = new List<object[]>();
                string[] fields = dbProc.extractFields(t.Rows[0]);
                for (int i = 1; i < t.Rows.Count; i++)
                {
                    Application.DoEvents();
                    bool success = dbProc.importGoodsItem(t.Rows[i],fields);
                    //bool success = dbProc.importGoodsItem(t.Rows[i]);
                    LabelProcessName.Text = "Импорт позиций ("+i.ToString()+" из " + t.Rows.Count.ToString() + ")  Не удалось импортировать: "+failedCount;
                    if (!success)
                    {
                        
                        failedCount++;
                        fail_table.Add(t.Rows[i].ItemArray);
                    }
                    else
                    {
                        successCount++;
                    }
                    Application.DoEvents();
                }
                //ProgressBarMain.Value = ProgressBarMain.Maximum;
                DialogResult result = MessageBox.Show("Всего записей для импорта: "+(successCount+failedCount)
                    +Environment.NewLine
                    + "Успешно импортировано: " + successCount
                    +Environment.NewLine
                    +"Не удалось импортировать: "+failedCount
                    +Environment.NewLine+Environment.NewLine
                    +"Просмотреть не импортированные записи?", "Импорт ассортимента", MessageBoxButtons.YesNo);
                //ProgressBarMain.Value = 0;
                if (result == System.Windows.Forms.DialogResult.Yes)
                {
                    Import.ImportGoodsDetailsForm form = new Import.ImportGoodsDetailsForm(fail_table);
                    form.Visible = true;
                    form.Activate();
                }
                LabelProcessName.Text = "";
                ButtProcess.Visible = false;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            StreamReader reader = new StreamReader("C://metros.txt",Encoding.Default);
            string line = reader.ReadLine();
            while (line != null)
            {
                Hashtable tab = new Hashtable();
                tab.Add("name", line);
                dbProc.insert("metros", tab);
                line = reader.ReadLine();
            }
        }

        private void MenuButtUsers_Click(object sender, EventArgs e)
        {
            Users.UsersForm form = new Users.UsersForm(this);
            form.Visible = true;
            form.Activate();
        }

        private void MenuButtExit_Click(object sender, EventArgs e)
        {
            Finish();
        }

        private void MenuButtMetros_Click(object sender, EventArgs e)
        {
            StreamReader reader = new StreamReader("C://metros.txt", Encoding.Default);
            dbProc.delete("metros","");
            string line = reader.ReadLine();
            while (line != null)
            {
                Hashtable tab = new Hashtable();
                tab.Add("name", line+" (до 16:00)");
                dbProc.insert("metros", tab);

                Hashtable tab1 = new Hashtable();
                tab1.Add("name", line + " (после 16:00)");
                dbProc.insert("metros", tab1);

                line = reader.ReadLine();
            }
        }

        private void MenuButtAuthorization_Click(object sender, EventArgs e)
        {
            LoginForm form = new LoginForm(this);
            Enabled = false;
            form.Activate();
            form.Visible = true;
        }

        public void TabControlMain_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (LastTabIndex != TabControlMain.SelectedIndex)
            {
                if (TabControlMain.SelectedTab == TabPurchase)// Orders
                {
                    loadOrders(false);
                }
                if (TabControlMain.SelectedTab == TabGoods)// Goods
                {
                    loadGoods();
                }
                if (TabControlMain.SelectedTab == TabPurchase)// Purchase
                {
                    loadPurchaseTable(false);
                }
                if (TabControlMain.SelectedTab == TabDeliveryLists)// DeliverLists
                {
                    loadDrivers(true);
                    loadDeliverLists();
                }
                LastTabIndex = TabControlMain.SelectedIndex;
            }
        }
        private void loadGoods()
        {
            loadGoods("", "");
        }
        private void loadGoods(string name, string value)
        {
            DataTable goods = null;
            if (name != null && name.Replace(" ", "") != "" && value != null && value.Replace(" ", "") != "")
            {
    //            "SELECT g.id,g.articul,g.name_for_order||' '||CASE COALESCE(g.weight,'') WHEN '' THEN '' ELSE g.weight END || '      \t('|| quantity - COALESCE(ords.ordered,0)"
    //+ "||' на складе)' AS name_for_order,g.company,g.food_type_age,g.animal,g.food_type,g.food_type_category,g.weight,g.photo_url,ords.ordered FROM "
    //+ "goods g LEFT JOIN (SELECT good_id,sum(count) AS ordered FROM orders_with_details WHERE status_id=1 GROUP BY good_id) ords ON g.id = ords.good_id "
    //+ "WHERE g.articul IS NOT NULL ORDER BY g.name_for_order"
                goods = dbProc.executeGet("SELECT articul, name_for_order_full,price_basic,price_discount,price,description_short,supplier,company,minimum,quantity,(quantity - COALESCE(ords.ordered,0)) AS on_warehouse,id FROM goods g LEFT JOIN (SELECT good_id,sum(count) AS ordered FROM orders_with_details WHERE status_id=1 GROUP BY good_id) ords ON g.id = ords.good_id "
    + "WHERE g.articul IS NOT NULL AND "+ name + "='" + value + "' ORDER BY g.id");
                //" + name + "='" + value + "' ORDER BY id"
            }
            else
            {
                goods = dbProc.executeGet("SELECT articul, name_for_order_full,price_basic,price_discount,price,description_short,supplier,company,minimum,quantity,(quantity - COALESCE(ords.ordered,0)) AS on_warehouse,id FROM goods g LEFT JOIN (SELECT good_id,sum(count) AS ordered FROM orders_with_details WHERE status_id=1 AND date(deliver_date)=date(now()) GROUP BY good_id) ords ON g.id = ords.good_id "
    + "WHERE g.articul IS NOT NULL ORDER BY g.id");
            }
            DataGridViewGoods.DataSource = goods;
            Configurator.translateToRussian(DataGridViewGoods);
            formatGoods(DataGridViewGoods);
        }
        private void formatGoods(DataGridView view)
        {
            view.Columns[0].Width = 60;
            view.Columns[0].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;

            view.Columns[1].Width = 280;
            view.Columns[1].DefaultCellStyle.WrapMode = DataGridViewTriState.True;
            view.Columns[1].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;

            view.Columns[2].Width = 70;
            view.Columns[2].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleRight;
            view.Columns[2].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;

            view.Columns[3].Width = 70;
            view.Columns[3].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleRight;
            view.Columns[3].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;

            view.Columns[4].Width = 65;
            view.Columns[4].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleRight;
            view.Columns[4].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;

            view.Columns[5].Width = 380;
            view.Columns[5].DefaultCellStyle.WrapMode = DataGridViewTriState.True;
            view.Columns[5].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;

            view.Columns[6].Width = 220;
            view.Columns[6].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;

            view.Columns[7].Width = 120;
            view.Columns[7].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;

            view.Columns[8].Width = 70;
            view.Columns[8].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleRight;
            view.Columns[8].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;

            view.Columns[9].Width = 70;
            view.Columns[9].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleRight;
            view.Columns[9].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;

            view.Columns[10].Width = 70;
            view.Columns[10].DefaultCellStyle.Alignment = DataGridViewContentAlignment.MiddleRight;
            view.Columns[10].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;
            //view.Columns[10].Visible = false;
            view.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            Padding newPadding = new Padding(10, 0, 10, 0);
            view.Columns[5].DefaultCellStyle.Padding = newPadding;
            view.CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal;
        }
        private void loadOrders(bool change_page)
        {
            int limit = Int32.Parse(ComboPageSize.Text);
            int total_rows = 0;
            int total_pages = 0;
            if (MainForm.currentUser.Role == Roles.Partner)
            {
                OrderFilter += " AND user_id=" + MainForm.currentUser.ID.ToString();
            }
            DataTable goods = null;
            if (!change_page)
            {
                current_page = 1;
            }
            if (RadioOrdersToday.Checked)
            {
                label2.Enabled = false;
                label3.Enabled = false;
                DatePickerOrdersFrom.Enabled = false;
                DatePickerOrdersTo.Enabled = false;
                ButtSelectOrders.Enabled = false;
                
                string query_count = "SELECT owd.id FROM orders_with_details owd LEFT OUTER JOIN (SELECT substring(u.first_name, 1, 1) || '. ' || u.second_name AS driver,dll.order_id AS order_id FROM delivery_lists dll,users u WHERE dll.driver_id=u.id) as dll ON dll.order_id=owd.id WHERE owd.deliver_date=date(now()) AND owd.status_id=" + (currentOrderStatusIndex + 1).ToString() + " " + OrderFilter + " ORDER BY owd.deliver_date DESC,owd.created";
                DataTable for_count = dbProc.executeGet(query_count);
                total_rows = for_count.Rows.Count;
                if (total_rows < limit)
                {
                    limit = total_rows;
                }
                if (limit == 0)
                {
                    limit = 1;
                }
                total_pages = (int)Math.Ceiling((double)total_rows / limit);
                if (total_pages == 0)
                {
                    total_pages = 1;
                }
                LabelTotalPages.Text = total_pages.ToString();
                if (current_page > total_pages)
                {
                    current_page = total_pages;
                }
                if (current_page == 0)
                {
                    current_page = 1;
                }
                LabelTotalPages.Text = total_pages.ToString();
                LabelCurrentPage.Text = current_page.ToString();
                string query = "SELECT owd.id,owd.deliver_date,owd.articul,owd.good_name,owd.count,owd.good_price,owd.deliver_price,owd.total_price,owd.client_name,owd.phones,owd.address,owd.deliver_time,COALESCE(dll.driver,owd.driver) AS driver,owd.modifier_name,owd.modified,owd.status_id,owd.user_name,owd.created FROM orders_with_details owd LEFT OUTER JOIN (SELECT substring(u.first_name, 1, 1) || '. ' || u.second_name AS driver,dll.order_id AS order_id FROM delivery_lists dll,users u WHERE dll.driver_id=u.id) as dll ON dll.order_id=owd.id WHERE owd.deliver_date=date(now()) AND owd.status_id=" + (currentOrderStatusIndex + 1).ToString() + " " + OrderFilter + " ORDER BY owd.deliver_date DESC,owd.created LIMIT " + limit.ToString() + " OFFSET " + (current_page - 1) * limit;
                goods = dbProc.executeGet(query);

                DataGridViewOrders.DataSource = goods;
            }
            if (RadioOrdersWeek.Checked)
            {
                label2.Enabled = false;
                label3.Enabled = false;
                DatePickerOrdersFrom.Enabled = false;
                DatePickerOrdersTo.Enabled = false;
                ButtSelectOrders.Enabled = false;

                string query_count = "SELECT owd.id FROM orders_with_details owd LEFT OUTER JOIN (SELECT substring(u.first_name, 1, 1) || '. ' || u.second_name AS driver,dll.order_id AS order_id FROM delivery_lists dll,users u WHERE dll.driver_id=u.id) as dll ON dll.order_id=owd.id WHERE owd.deliver_date>=date(now()) AND owd.status_id=" + (currentOrderStatusIndex + 1).ToString() + " " + OrderFilter + " ORDER BY owd.deliver_date DESC,owd.created";
                DataTable for_count = dbProc.executeGet(query_count);
                total_rows = for_count.Rows.Count;
                if (total_rows < limit)
                {
                    limit = total_rows;
                }
                if (limit == 0)
                {
                    limit = 1;
                }
                total_pages = (int)Math.Ceiling((double)total_rows / limit);
                if (total_pages == 0)
                {
                    total_pages = 1;
                }
                LabelTotalPages.Text = total_pages.ToString();
                if (current_page > total_pages)
                {
                    current_page = total_pages;
                }
                if (current_page == 0)
                {
                    current_page = 1;
                }
                LabelTotalPages.Text = total_pages.ToString();
                LabelCurrentPage.Text = current_page.ToString();

                string query = "SELECT owd.id,owd.deliver_date,owd.articul,owd.good_name,owd.count,owd.good_price,owd.deliver_price,owd.total_price,owd.client_name,owd.phones,owd.address,owd.deliver_time,COALESCE(dll.driver,owd.driver) AS driver,owd.created,owd.modifier_name,owd.modified,owd.status_id,owd.user_name FROM orders_with_details owd LEFT OUTER JOIN (SELECT substring(u.first_name, 1, 1) || '. ' || u.second_name AS driver,dll.order_id AS order_id FROM delivery_lists dll,users u WHERE dll.driver_id=u.id) as dll ON dll.order_id=owd.id WHERE owd.deliver_date>=date(now()) AND owd.status_id=" + (currentOrderStatusIndex + 1).ToString() + " " + OrderFilter + " ORDER BY owd.deliver_date DESC,owd.created LIMIT " + limit.ToString() + " OFFSET " + (current_page - 1) * limit;
                goods = dbProc.executeGet(query);

                DataGridViewOrders.DataSource = goods;
            }
            if (RadioOrdersAll.Checked)
            {
                label2.Enabled = true;
                label3.Enabled = true;
                DatePickerOrdersFrom.Enabled = true;
                DatePickerOrdersTo.Enabled = true;
                ButtSelectOrders.Enabled = true;

                string query_count = "SELECT owd.id FROM orders_with_details owd LEFT OUTER JOIN (SELECT substring(u.first_name, 1, 1) || '. ' || u.second_name AS driver,dll.order_id AS order_id FROM delivery_lists dll,users u WHERE dll.driver_id=u.id) as dll ON dll.order_id=owd.id WHERE "
                            + " deliver_date>=date(" + dbProc.getDateTimeString(DatePickerOrdersFrom.Value) + ")"
                            + " AND "
                            + " deliver_date<=date(" + dbProc.getDateTimeString(DatePickerOrdersTo.Value) + ")"
                            + " AND status_id=" + (currentOrderStatusIndex + 1).ToString() + " " + OrderFilter + " ORDER BY deliver_date DESC,created";
                DataTable for_count = dbProc.executeGet(query_count);
                total_rows = for_count.Rows.Count;
                if (total_rows < limit)
                {
                    limit = total_rows;
                }
                if (limit == 0)
                {
                    limit = 1;
                }
                total_pages = (int)Math.Ceiling((double)total_rows / limit);
                if (total_pages == 0)
                {
                    total_pages = 1;
                }
                LabelTotalPages.Text = total_pages.ToString();
                if (current_page > total_pages)
                {
                    current_page = total_pages;
                }
                if (current_page == 0)
                {
                    current_page = 1;
                }
                LabelTotalPages.Text = total_pages.ToString();
                LabelCurrentPage.Text = current_page.ToString();
                string command = "SELECT owd.id,owd.deliver_date,owd.articul,owd.good_name,owd.count,owd.good_price,owd.deliver_price,owd.total_price,owd.client_name,owd.phones,owd.address,owd.deliver_time,COALESCE(dll.driver,owd.driver) AS driver,owd.created,owd.modifier_name,owd.modified,owd.status_id,owd.user_name FROM orders_with_details owd LEFT OUTER JOIN (SELECT substring(u.first_name, 1, 1) || '. ' || u.second_name AS driver,dll.order_id AS order_id FROM delivery_lists dll,users u WHERE dll.driver_id=u.id) as dll ON dll.order_id=owd.id WHERE ";
                command += " deliver_date>=date(" + dbProc.getDateTimeString(DatePickerOrdersFrom.Value) + ")"
                            + " AND "
                            + " deliver_date<=date(" + dbProc.getDateTimeString(DatePickerOrdersTo.Value) + ")";
                command += " AND status_id=" + (currentOrderStatusIndex + 1).ToString() + " " + OrderFilter + " ORDER BY deliver_date DESC,created  LIMIT " + limit.ToString()
                        + " OFFSET " + (current_page-1)*limit;
                
                goods = dbProc.executeGet(command);
                
                DataGridViewOrders.DataSource = goods;
            }
            if (goods != null)
            {
                Configurator.translateToRussian(DataGridViewOrders);
                formatOrders(DataGridViewOrders);
            }
        }
        private void loadDrivers(bool need_set_date)
        {
            string date = "date(" +dbProc.getDateTimeString(TimePickerDeliverListDate.Value)+")";
            string command = "SELECT u.*," +
            "CASE u.first_name WHEN '' THEN  u.second_name ELSE (substring(u.first_name, 1, 1) || '. ')|| u.second_name END AS fio,"+
            "CASE u.first_name WHEN '' THEN  u.second_name ELSE (substring(u.first_name, 1, 1) || '. ')|| u.second_name END|| '('||"+
            "(SELECT count(DISTINCT owd.id) FROM delivery_lists dl,orders_with_details owd,users dr WHERE owd.driver_id=dr.id AND owd.deliver_date=" + date + " AND owd.id=dl.order_id AND dl.driver_id=u.id)||')' AS fio_count FROM users u WHERE u.role_id=3 ORDER BY fio";

            //
            DataTable Drivers = MainForm.dbProc.executeGet(command);
            ListDrivers.DataSource = Drivers;
            ListDrivers.DisplayMember = "fio_count";
            ListDrivers.ValueMember = "id";
            if (need_set_date)
            {
                TimePickerDeliverListDate.Value = MainForm.dbProc.getServerTime();
                TimePickerDeliverListsFormDate.Value = MainForm.dbProc.getServerTime();
            }
            calculateTotalForDay();

        }
        private void calculateTotalForDay()
        {
            double total = 0;
            for (int i=0; i<ListDrivers.Items.Count; i++)
            {
                try
                {

                    int driver_id = (int)((DataRowView)ListDrivers.Items[i]).Row["id"];
                    string command = "SELECT DISTINCT owd.id, total_price, dl.id AS dl_id, dl.sum FROM delivery_lists dl,orders_with_details owd,users dr WHERE owd.driver_id=dr.id AND owd.deliver_date=date(" + dbProc.getDateTimeString(TimePickerDeliverListDate.Value) + ") AND owd.id=dl.order_id AND dl.driver_id=" + driver_id.ToString() + " GROUP BY owd.total_price,owd.id,dl.id,dl.sum";

                    DataTable drTable = MainForm.dbProc.executeGet(command);
                    double local_total = 0.0;
                    for (int j = 0; j < drTable.Rows.Count; j++)
                    {
                        double sumForOrder = 0.0;
                        if(Double.TryParse(drTable.Rows[j]["total_price"].ToString(),out sumForOrder))
                        {
                            local_total += sumForOrder;
                        }
                        if(Double.TryParse(drTable.Rows[j]["sum"].ToString(),out sumForOrder))
                        {
                            local_total = sumForOrder;
                            break;
                        }
                    }
                    total += local_total;
                }
                catch (Exception ex)
                {

                }
            }
            TxtDayTotal.Text = total.ToString();
        }
        private int current_delivery_list_id = 0;
        public void loadDeliverLists()
        {
            if (ListDrivers.SelectedValue is int)
            {
                int driver_id = (int)ListDrivers.SelectedValue;
                //"SELECT id,deliver_date,good_name,count,good_price,deliver_price,total_price,client_name,phones,address,deliver_time,driver,created FROM orders_with_details WHERE deliver_date=date(now()) ORDER BY id,deliver_date,created"
                                  //"SELECT owd.id,owd.deliver_date,owd.articul,owd.good_name,owd.count,owd.good_price,owd.deliver_price,owd.total_price,owd.client_name,owd.phones,owd.address,owd.deliver_time,COALESCE(dll.driver,owd.driver) AS driver,owd.created,owd.modifier_name,owd.modified,owd.status_id FROM orders_with_details owd LEFT OUTER JOIN (SELECT substring(u.first_name, 1, 1) || '. ' || u.second_name AS driver,dll.order_id AS order_id FROM delivery_lists dll,users u WHERE dll.driver_id=u.id) as dll ON dll.order_id=owd.id WHERE ";
                string command = "SELECT owd.id,owd.deliver_date,owd.articul,owd.good_name,owd.count,owd.good_price,owd.deliver_price,owd.total_price,owd.client_name,owd.phones,owd.address,owd.deliver_time,owd.driver,owd.created,owd.modifier_name,owd.modified,owd.status_id, dl.sum, dl.description,dl.comment, dl.id AS delivery_list_id FROM delivery_lists dl,orders_with_details owd,users dr WHERE owd.driver_id=dr.id AND owd.deliver_date=date(" + dbProc.getDateTimeString(TimePickerDeliverListDate.Value) + ") AND owd.id=dl.order_id AND dl.driver_id=" + driver_id.ToString() + " ORDER BY dl.priority,owd.id";
                
                DataTable drTable = MainForm.dbProc.executeGet(command);
                DataGridViewDeliveryLists.DataSource = drTable;
                int total = 0;

                if(drTable.Rows.Count > 0)
                {
                    current_delivery_list_id = (int)drTable.Rows[0]["delivery_list_id"];
                    TxtTotalDescription.Text = drTable.Rows[0]["description"].ToString();
                    TxtCalculatorComment.Text = drTable.Rows[0]["comment"].ToString();
                }
                else
                {
                    current_delivery_list_id = 0;
                    TxtTotalDescription.Text = "";
                    TxtCalculatorComment.Text = "";
                }
                Text = current_delivery_list_id.ToString();
                int curr_order_id = 0;
                for (int i = 0; i < DataGridViewDeliveryLists.RowCount; i++)
                {
                    if (curr_order_id != (int)(DataGridViewDeliveryLists.Rows[i].Cells[0].Value))
                    {
                        curr_order_id = (int)(DataGridViewDeliveryLists.Rows[i].Cells[0].Value);
                        int price = (int)(DataGridViewDeliveryLists.Rows[i].Cells[7].Value);
                        total += price;
                    }
                }
                TxtTotalForDriver.Text = total.ToString();
                if (drTable.Rows.Count > 0 && drTable.Rows[0]["sum"].ToString() != "")
                {
                    TxtResultForDriver.Text = drTable.Rows[0]["sum"].ToString();
                }
                else
                {
                    TxtResultForDriver.Text = total.ToString();
                }
                if (TxtTotalDescription.Text == "")
                {
                    TxtTotalDescription.Text = total.ToString();
                }
                Configurator.translateToRussian(DataGridViewDeliveryLists);
                formatOrders(DataGridViewDeliveryLists);
                DataGridViewDeliveryLists.Columns["created"].Visible = false;
                DataGridViewDeliveryLists.Columns["sum"].Visible = false;
                DataGridViewDeliveryLists.Columns["description"].Visible = false;
                
                //formatDeliverLists();
            }
        }
        public void loadPurchaseTable(Boolean byDateTo)
        {
            DataTable goods = null;
            string timeCondition = "";
            if (RadioPurchaseForPeriod.Checked)
            {
                string from = dbProc.getDateTimeString(TimePickerPurchaseFrom.Value);
                string to = dbProc.getDateTimeString(TimePickerPurchaseTo.Value);
                if (byDateTo)
                {
                    timeCondition = " AND \"date_to\"<=date(" + to + ") AND \"date_to\">=date(" + from + ")";
                }
                else
                {
                    timeCondition = " AND \"date\"<=date(" + to + ") AND \"date\">=date(" + from + ")";
                }
                
            }
                        
            if (ComboIsForOrders.SelectedIndex == 0)//For orders
            {
                //goods = dbProc.executeGet("SELECT DISTINCT pl.supplier,pl.list_name,round(sum(pl.good_count*pl.good_purchase_price)) AS total_price,(sum(g.weight_product*pl.good_count)||' '||'кг.') AS total_weight FROM purchase_lists pl,goods g WHERE pl.good_id=g.id " + timeCondition + " AND pl.status="+Purchased_Status+" and pl.is_for_orders=true GROUP BY pl.supplier,pl.list_name ORDER BY pl.list_name, pl.supplier");
                goods = dbProc.executeGet("SELECT DISTINCT pl.supplier,pl.list_name,sum(pl.good_count*pl.good_purchase_price) AS total_price,(sum(g.weight_product*pl.good_count)||' '||'кг.') AS total_weight,date_to FROM purchase_lists pl,goods g WHERE pl.good_id=g.id " + timeCondition + " AND pl.status=" + Purchased_Status + " and pl.is_for_orders=true GROUP BY pl.supplier,pl.list_name,date_to ORDER BY pl.list_name, pl.supplier");
            }
            if (ComboIsForOrders.SelectedIndex == 1)
            {
                goods = dbProc.executeGet("SELECT DISTINCT pl.supplier,pl.list_name,sum(pl.good_count*pl.good_purchase_price) AS total_price,(sum(g.weight_product*pl.good_count)||' '||'кг.') AS total_weight,date_to FROM purchase_lists pl,goods g WHERE pl.good_id=g.id " + timeCondition + " AND pl.status=" + Purchased_Status + " and pl.is_for_orders=false GROUP BY pl.supplier,pl.list_name,date_to ORDER BY pl.list_name, pl.supplier");
            }
            if (ComboIsForOrders.SelectedIndex == 2)
            {
                goods = dbProc.executeGet("SELECT DISTINCT pl.supplier,pl.list_name,sum(pl.good_count*pl.good_purchase_price) AS total_price,(sum(g.weight_product*pl.good_count)||' '||'кг.') AS total_weight,date_to FROM purchase_lists pl,goods g WHERE pl.good_id=g.id " + timeCondition + " AND pl.status=" + Purchased_Status + " GROUP BY pl.supplier,pl.list_name,date_to ORDER BY pl.list_name, pl.supplier");
            }
            DataGridViewPurchase.DataSource = goods;
            Configurator.translateToRussian(DataGridViewPurchase);
            formatPurchaseTable();
        }
        private void formatPurchaseTable()
        {
            if (!DataGridViewPurchase.Columns.Contains("checked"))
            {
                DataGridViewCheckBoxColumn col = new DataGridViewCheckBoxColumn();
                col.FalseValue = false;
                col.IndeterminateValue = false;
                col.TrueValue = true;
                col.HeaderText = ".";
                col.Name = "checked";
                DataGridViewPurchase.Columns.Insert(0, col);
            }
            string currSupp = "";
            Color colBlue = Color.FromArgb(209, 240, 252);
            Color colGreen = Color.FromArgb(255, 255, 255);
            Color currCol = colGreen;
            for (int i = 0; i < DataGridViewPurchase.RowCount; i++)
            {
                if (currSupp != (string)DataGridViewPurchase.Rows[i].Cells["supplier"].Value)
                {
                    currSupp = (string)DataGridViewPurchase.Rows[i].Cells["supplier"].Value;
                    if (currCol == colGreen)
                    {
                        currCol = colBlue;
                    }
                    else
                    {
                        currCol = colGreen;
                    }
                }
                DataGridViewPurchase.Rows[i].DefaultCellStyle.BackColor = currCol;
            }
            DataGridViewPurchase.CellBorderStyle = DataGridViewCellBorderStyle.None;
            DataGridViewPurchase.Columns[0].Visible = false;
            DataGridViewPurchase.Columns[0].Width = 50;
            DataGridViewPurchase.Columns[1].Width = 330;
            DataGridViewPurchase.Columns[2].Width = 270;
            DataGridViewPurchase.Columns[3].Width = 100;
            DataGridViewCheckBoxColumn checkColumn = new DataGridViewCheckBoxColumn(false);
            DataGridViewPurchase.Columns.Insert(0, checkColumn);
        }
        private void MenuButtAddOrder_Click(object sender, EventArgs e)
        {
            Orders.AddEditOrderForm form = new Orders.AddEditOrderForm(this, 0);
            form.Visible = true;
            form.Activate();
        }
        private void DataGridViewOrders_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            try
            {
                if (DataGridViewOrders.SelectedCells != null & DataGridViewOrders.SelectedCells.Count == 1)
                {
                    currentOrderID = (int)DataGridViewOrders.SelectedCells[0].OwningRow.Cells["id"].Value;
                    currentOrderStatus = (int)DataGridViewOrders.SelectedCells[0].OwningRow.Cells["status_id"].Value;
                    if (currentOrderStatus == 1 || currentOrderStatus == 2 || true)// Patch...
                    {
                        ButtChangeOrderStatus.Enabled = true;
                    }
                    MenuButtDeleteOrder.Enabled = true;
                    MenuButtEditOrder.Enabled = true;
                }
                else
                {
                    MenuButtDeleteOrder.Enabled = false;
                    MenuButtEditOrder.Enabled = false;
                    ButtChangeOrderStatus.Enabled = false;
                }
            }
            catch
            {
                MenuButtDeleteOrder.Enabled = false;
                MenuButtEditOrder.Enabled = false;
                ButtChangeOrderStatus.Enabled = false;
            }
        }

        private void RadioOrdersToday_CheckedChanged(object sender, EventArgs e)
        {
            loadOrders(false);
        }

        private void MenuButtEditOrder_Click(object sender, EventArgs e)
        {
            if (currentOrderStatus == 3)// Finished
            {
                if (currentUser.Role == Roles.Operator)
                {
                    MessageBox.Show("У вас нет права редактировать выполненные заказы");
                    return;
                }
            }
            Orders.AddEditOrderForm form = new Orders.AddEditOrderForm(this, currentOrderID);
            form.Visible = true;
            form.Activate();
        }

        private void MainForm_Resize(object sender, EventArgs e)
        {
            TabControlMain.Width = Width - 16;
            TabControlMain.Height = Height - 128;
            DataGridViewOrders.Width = TabControlMain.Width - 177;
            DataGridViewOrders.Height = TabControlMain.Height - 72;
            DataGridViewGoods.Width = TabControlMain.Width - 25;
            DataGridViewGoods.Height = TabControlMain.Height - 127;
            DataGridViewPurchase.Height = DataGridViewGoods.Height - 22;
            DataGridViewPurchase.Width = DataGridViewGoods.Width - 150;
            DataGridViewDeliveryLists.Width = TabControlMain.Width - 180;
            DataGridViewDeliveryLists.Height = TabControlMain.Height - 70;
        }

        private void toolStripMenuItem2_Click(object sender, EventArgs e)
        {
            Settings.DeliveryCostForm form = new Settings.DeliveryCostForm();
            form.Visible = true;
            form.Activate();
        }

        private void MenuButtDiscounts_Click(object sender, EventArgs e)
        {
            Settings.DiscountsForm form = new Settings.DiscountsForm();
            form.Visible = true;
            form.Activate();
        }

        private void toolStripMenuItem2_Click_1(object sender, EventArgs e)
        {
            Settings.DriverMetrosForm form = new Settings.DriverMetrosForm();
            form.Visible = true;
            form.Activate();
        }

        private void toolStripMenuItem6_Click(object sender, EventArgs e)
        {
            PanelSetMinimum.Visible = true;
            DataGridViewGoods.Enabled = false;
        }

        private void ButtOkMinimumClick(object sender, EventArgs e)
        {
            ProgressBarMain.Maximum = currentGoodIDs.Count;
            for (int i = 0; i < currentGoodIDs.Count; i++)
            {
                Hashtable par = new Hashtable();
                par.Add("minimum", (int)NumGoodMinimum.Value);
                dbProc.update("goods", par, "id=" + currentGoodIDs[i]);
                ProgressBarMain.Value = i;
                Application.DoEvents();
            }
            ProgressBarMain.Value = 0;
            PanelSetMinimum.Visible = false;
            DataGridViewGoods.Enabled = true;
            //loadGoods();
        }

        private void contextMenuGoods_Opening(object sender, CancelEventArgs e)
        {
            currentGoodIDs = new List<int>();
            if (DataGridViewGoods.SelectedCells.Count > 0)
            {
                for (int i = 0; i < DataGridViewGoods.SelectedCells.Count; i++)
                {
                    NumGoodMinimum.Value = ((int)DataGridViewGoods.SelectedCells[i].OwningRow.Cells["minimum"].Value);
                    NumGoodQuantity.Value = ((int)DataGridViewGoods.SelectedCells[i].OwningRow.Cells["quantity"].Value);
                    currentGoodIDs.Add((int)DataGridViewGoods.SelectedCells[i].OwningRow.Cells["id"].Value);
                }
                ButtSetMinimum.Enabled = true;
                ButtSetQuantity.Enabled = true;
            }
            else
            {
                ButtSetMinimum.Enabled = false;
                ButtSetQuantity.Enabled = false;
            }
        }

        private void ButtNullMinimum_Click(object sender, EventArgs e)
        {
            PanelSetQuantity.Visible = true;
            DataGridViewGoods.Enabled = false;
        }

        private void ButtRefreshGoods_Click(object sender, EventArgs e)
        {
            DataTable goods = dbProc.executeGet("SELECT * FROM goods ORDER BY id");
            DataGridViewGoods.DataSource = goods;
            Configurator.translateToRussian(DataGridViewGoods);
        }

        private void ButtSelectOrders_Click(object sender, EventArgs e)
        {
            loadOrders(false);
        }
        private void formatOrders(DataGridView view)
        {
            int currOrdID = 0;
            Color colBlue = Color.FromArgb(233,246,247);
            Color colGreen = Color.FromArgb(255,255,255);
            Color currCol = colGreen;
            for(int i=0; i<view.RowCount; i++)
            {
                bool needHide = false;
                if (currOrdID != (int)view.Rows[i].Cells["id"].Value)
                {
                    currOrdID = (int)view.Rows[i].Cells["id"].Value;
                    view.Rows[i].Height = 45;
                    if (currCol == colGreen)
                    {
                        currCol = colBlue;
                    }
                    else
                    {
                        currCol = colGreen;
                    }
                }
                else
                {
                    needHide = true;
                    view.Rows[i].Height = 20;
                    view.Rows[i].Cells["id"].Style.ForeColor = view.Rows[i].Cells["id"].Style.BackColor;
                    view.Rows[i].Cells["id"].Style.SelectionForeColor = view.Rows[i].Cells["id"].Style.SelectionBackColor;
                    view.Rows[i].Cells["deliver_date"].Value = DBNull.Value;
                    view.Rows[i].Cells["phones"].Value = "";
                    view.Rows[i].Cells["client_name"].Value = "";
                    view.Rows[i].Cells["modified"].Value = "";
                    view.Rows[i].Cells["modifier_name"].Value = "";
                    view.Rows[i].Cells["address"].Value = "";
                    view.Rows[i].Cells["deliver_time"].Value = "";
                    view.Rows[i].Cells["deliver_price"].Value = DBNull.Value;
                    view.Rows[i].Cells["total_price"].Value = DBNull.Value;
                    view.Rows[i].Cells[11].Value = DBNull.Value;
                    view.Rows[i].Cells[12].Value = DBNull.Value;
                }
                //view.Rows[i].DefaultCellStyle.BackColor = currCol;
                if (needHide)
                {
                    view.Rows[i].Cells["id"].Style.ForeColor = view.Rows[i].DefaultCellStyle.BackColor;
                    view.Rows[i].Cells["id"].Style.SelectionForeColor = view.Rows[i].DefaultCellStyle.SelectionBackColor;
                }
            }
            if (view.ColumnCount > 0)
            {
                view.Columns[0].Width = 45;
                view.Columns[1].Width = 70;
                view.Columns[2].Width = 40;
                view.Columns[3].Width = 240;
                view.Columns[4].Width = 45;
                view.Columns[5].Width = 52;
                view.Columns[6].Width = 57;
                view.Columns[7].Width = 65;
                view.Columns[8].Width = 65;
                view.Columns[9].Width = 80;
                view.Columns[10].Width = 220;
                view.Columns[11].Width = 70;

                view.Columns[12].Width = 90;
                //view.Columns[13].Visible = false;
                view.Columns[16].Visible = false;
                Padding newPadding = new Padding(0, 0, 0, 0);

                view.Columns[0].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[1].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[2].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
                view.Columns[3].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[4].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
                view.Columns[5].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
                view.Columns[6].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
                view.Columns[7].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopRight;
                view.Columns[8].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopCenter;
                view.Columns[9].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[10].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[11].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[12].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[13].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[14].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
                view.Columns[15].DefaultCellStyle.Alignment = System.Windows.Forms.DataGridViewContentAlignment.TopLeft;
            }
            view.Columns[8].DefaultCellStyle.WrapMode = DataGridViewTriState.True;
            view.Columns[11].DefaultCellStyle.WrapMode = DataGridViewTriState.True;
            view.Columns[3].DefaultCellStyle.WrapMode = DataGridViewTriState.True;
            view.CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal;
            view.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
        }
        private void formatDeliverLists()
        {
            int currOrdID = 0;
            Color colBlue = Color.FromArgb(209, 240, 252);
            Color colGreen = Color.FromArgb(252, 248, 209);
            Color currCol = colGreen;
            for (int i = 0; i < DataGridViewDeliveryLists.RowCount; i++)
            {
                if (currOrdID != (int)DataGridViewDeliveryLists.Rows[i].Cells["id"].Value)
                {
                    currOrdID = (int)DataGridViewDeliveryLists.Rows[i].Cells["id"].Value;
                    if (currCol == colGreen)
                    {
                        currCol = colBlue;
                    }
                    else
                    {
                        currCol = colGreen;
                    }
                }
                DataGridViewDeliveryLists.Rows[i].DefaultCellStyle.BackColor = currCol;
            }
            if (DataGridViewDeliveryLists.ColumnCount > 0)
            {
                DataGridViewDeliveryLists.Columns[0].Width = 40;
                DataGridViewDeliveryLists.Columns[1].Width = 280;
                DataGridViewDeliveryLists.Columns[2].Width = 80;
                DataGridViewDeliveryLists.Columns[3].Width = 80;
                DataGridViewDeliveryLists.Columns[4].Width = 80;
                DataGridViewDeliveryLists.Columns[5].Width = 80;
                DataGridViewDeliveryLists.Columns[6].Width = 120;
            }
            Configurator.translateToRussian(DataGridViewDeliveryLists);
        }
        private void toolStripMenuItem8_Click(object sender, EventArgs e)
        {
            Settings.SettingsEditForm form = new Settings.SettingsEditForm("purchase_email", "","");
            form.Activate();
            form.Visible = true;
        }

        private void toolStripMenuItem9_Click(object sender, EventArgs e)
        {
            Settings.SettingsEditForm form = new Settings.SettingsEditForm("purchase_discount", "","%");
            form.Activate();
            form.Visible = true;
        }

        private void ButtPurchaseForm_Click(object sender, EventArgs e)
        {
            Purchase.PurchaseListsForm form = new Purchase.PurchaseListsForm(true,this);
            form.Activate();
            form.Visible = true;

        }

        private void ButtAddGood_Click(object sender, EventArgs e)
        {
            Goods.AddEditGoodForm form = new Goods.AddEditGoodForm(0);
            form.Visible = true;
            form.Activate();
        }

        private void ButtPurchaseForStoreForm_Click(object sender, EventArgs e)
        {
            Purchase.PurchaseListsForm form = new Purchase.PurchaseListsForm(false,this);
            form.Activate();
            form.Visible = true;
        }

        public void ComboIsForOrders_SelectedIndexChanged(object sender, EventArgs e)
        {
            loadPurchaseTable(false);
        }

        private void DataGridViewPurchase_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                if (((DataGridView)sender).Rows[e.RowIndex].Cells[e.ColumnIndex].Value == null || (bool)((DataGridView)sender).Rows[e.RowIndex].Cells[e.ColumnIndex].Value == false)
                {
                    ((DataGridView)sender).Rows[e.RowIndex].Cells[e.ColumnIndex].Value = true;
                }
                else
                {
                    ((DataGridView)sender).Rows[e.RowIndex].Cells[e.ColumnIndex].Value = false;
                }
            }
        }

        private void DataGridViewPurchase_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            try
            {
                if (DataGridViewPurchase.SelectedCells != null & DataGridViewPurchase.SelectedCells.Count == 1)
                {
                    ButtChoosePurchaseList.Tag = DataGridViewPurchase.SelectedCells[0].OwningRow;
                    ButtChoosePurchaseList.Enabled = true;
                }
                else
                {
                    ButtChoosePurchaseList.Enabled = false;
                }
            }
            catch
            {
                ButtChoosePurchaseList.Enabled = false;
            }
        }
        //private string currPurchasePrice = "0";
        private void ButtChoosePurchaseList_Click(object sender, EventArgs e)
        {
            try
            {
                int checked_count = 0;
                List<DataGridViewRow> checkedRows = new List<DataGridViewRow>();
                List<string> list_names = new List<string>();
                List<string> suppliers = new List<string>();
                DateTime date = DateTime.Now;
                int status = 1;
                for (int i = 0; i < DataGridViewPurchase.RowCount; i++)
                {
                    if (DataGridViewPurchase.Rows[i].Cells[0].Value != null && (bool)DataGridViewPurchase.Rows[i].Cells[0].Value == true)
                    {
                        checked_count++;
                        DataGridViewRow row = (DataGridViewRow)DataGridViewPurchase.Rows[i];
                        list_names.Add((string)row.Cells["list_name"].Value);
                        suppliers.Add((string)row.Cells["supplier"].Value);
                        status = Int32.Parse(Purchased_Status);
                    }
                }
                if (checked_count == 0)// Single...
                {
                    if (ButtChoosePurchaseList.Tag != null)
                    {
                        DataGridViewRow row = (DataGridViewRow)ButtChoosePurchaseList.Tag;
                        list_names.Add((string)row.Cells["list_name"].Value);
                        suppliers.Add((string)row.Cells["supplier"].Value);
                        status = Int32.Parse(Purchased_Status);
                        for (int i = 0; i < row.Cells.Count; i++)
                        {
                            if (row.Cells[i].Value is DateTime)
                            {
                                date = (DateTime)row.Cells[i].Value;
                                break;
                            }
                        }
                    }
                }
                Purchase.PurchaseEditForm f = new Purchase.PurchaseEditForm(list_names, suppliers, this, status,date);
                f.Visible = true;
                f.Activate();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void RadioPurchaseForPeriod_CheckedChanged(object sender, EventArgs e)
        {
            ButtPurchaseSelect.Enabled = RadioPurchaseForPeriod.Checked;
            ButtPurchaseSelectByDateTo.Enabled = RadioPurchaseForPeriod.Checked;
            TimePickerPurchaseFrom.Enabled = RadioPurchaseForPeriod.Checked;
            TimePickerPurchaseTo.Enabled = RadioPurchaseForPeriod.Checked;
            loadPurchaseTable(false);
        }

        private void ButtPurchaseSelect_Click(object sender, EventArgs e)
        {
            loadPurchaseTable(false);
        }

        private void ButtEditDeliveryLists_Click(object sender, EventArgs e)
        {
            PanelOpenDeliverListForm.Visible = true;
        }

        private void ListDrivers_SelectedIndexChanged(object sender, EventArgs e)
        {
            loadDeliverLists();
        }
        private void ButtGenerateDeliverListsFile_Click(object sender, EventArgs e)
        {
            try
            {
                //StreamWriter writer = new StreamWriter("C:/temp/tempOwlList.txt");

                FTwoExcelProcessor excel = new FTwoExcelProcessor();
                excel.InitApplication(true);
                excel.OnExcelClose += new FTwoExcelProcessor.ExcelCloseHandler(excel_OnExcelClose);
                excel.changeOrientation(Microsoft.Office.Interop.Excel.XlPageOrientation.xlLandscape);
                ButtGenerateDeliverListsFile.Enabled = false;
                Color blue = Color.FromArgb(209, 240, 252);
                Color yellow = Color.FromArgb(252, 248, 209);
                Color currentColor = blue;
                DataTable drivers = dbProc.executeGet("SELECT *, substring(first_name from 1 for 1)||'. '||second_name AS fio FROM users WHERE role_id=3 ORDER BY fio");
                DataTable goodsQuantity = dbProc.executeGet("SELECT id,quantity FROM goods");
                Hashtable goodsHashtable = new Hashtable();
                for (int i = 0; i < goodsQuantity.Rows.Count; i++)
                {
                    goodsHashtable.Add(goodsQuantity.Rows[i]["id"], goodsQuantity.Rows[i]["quantity"]);
                }
                int currentRow = 1;
                string info = "";
                string orders = "";
                for (int i = 0; i < drivers.Rows.Count; i++)// For each driver
                {
                    int driversStartRow = currentRow;
                    int driver_id = (int)drivers.Rows[i]["id"];
                    string fio = (string)drivers.Rows[i]["fio"];

                    string sqlCommand = "SELECT owd.good_id,owd.description,owd,articul,owd.weight_product as weight_product,owd.weight as weight,owd.deliver_date,owd.id,owd.phones,owd.good_name,owd.count,owd.address,owd.deliver_time,owd.total_price,owd.goods_price,owd.good_price,owd.deliver_price,owd.driver AS fio,owd.client_name FROM delivery_lists dl,orders_with_details owd WHERE owd.deliver_date=date(" + MainForm.dbProc.getDateTimeString(TimePickerDeliverListDate.Value) + ") AND owd.id=dl.order_id AND dl.driver_id=" + driver_id.ToString() + " AND owd.status_id=1 ORDER BY dl.priority, owd.id";
                    //SELECT owd.id,owd.deliver_date,owd.good_name,owd.count,owd.good_price,owd.deliver_price,owd.total_price,owd.client_name,owd.phones,owd.address,owd.deliver_time,owd.driver,owd.created                                                            FROM delivery_lists dl,orders_with_details owd,users dr WHERE owd.driver_id=dr.id AND owd.deliver_date=date("+dbProc.getDateTimeString(TimePickerDeliverListDate.Value)+") AND owd.id=dl.order_id AND owd.status_id=1 AND dl.driver_id=" + driver_id.ToString()+" ORDER BY dl.priority"
                    
                    DataTable lists = dbProc.executeGet(sqlCommand);

                    double weight_total = 0;
                    //excel.mergeCells(1, currentRow, 4, currentRow);
                    excel.makeBorder(1, currentRow, 4, currentRow, Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin,false);
                    excel.makeBorder(2, 1, 2, 1, Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin, true);
                    excel.makeBorder(5, 1, 7, 1, Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin, true);
                    excel.makeFont(1, currentRow, 2, currentRow, true, false, false, 10);
                    excel.renameSheet(fio+" id_"+driver_id.ToString());
                    int lastOrderRow = currentRow + 1;
                    int currentOrderID = 0;
                    int currentOrderIndex = 0;
                    for (int j = 0; j < lists.Rows.Count; j++)// Each order item for driver
                    {
                        currentRow++;
                        string good_name = (string)lists.Rows[j]["good_name"];

                        int orderID = (int)lists.Rows[j]["id"];
                        //if (currentOrderID == 0)
                        //{
                            currentOrderID = orderID;
                        //}
                        // Good count...
                        int good_id = (int)lists.Rows[j]["good_id"];
                        int goodLeft = (int)goodsHashtable[good_id];
                        int count = (int)lists.Rows[j]["count"];
                        string countStr = count.ToString();
                        if (count > goodLeft)
                        {
                            countStr = " "+goodLeft.ToString() + "/" + count.ToString();
                            if (goodLeft < 0)
                            {
                                countStr = " 0/" + count.ToString();
                            }
                        }
                        goodLeft -= count;
                        goodsHashtable.Remove(good_id);
                        goodsHashtable.Add(good_id, goodLeft);

                        double item_total_weight = 0;
                        if (!(lists.Rows[j]["weight_product"] is DBNull))
                        {
                            item_total_weight = Math.Round((double)lists.Rows[j]["weight_product"] * count, 2);
                        }
                        weight_total += item_total_weight;
                        int good_price = (int)lists.Rows[j]["good_price"];
                        string client_name = (string)lists.Rows[j]["client_name"];
                        string weight_product = "";
                        if (!(lists.Rows[j]["weight_product"] is DBNull))
                        {
                            weight_product = lists.Rows[j]["weight_product"].ToString();
                        }
                        string weight = "";
                        if (!(lists.Rows[j]["weight"] is DBNull))
                        {
                            weight = (string)lists.Rows[j]["weight"];
                        }
                        string phones = (string)lists.Rows[j]["phones"];
                        phones = phones.Replace(" ", "").Replace("\r\n","\n");
                        string articul = (string)lists.Rows[j]["articul"];
                        string description = "";
                        if(!(lists.Rows[j]["description"] is DBNull))
                        {
                            description = lists.Rows[j]["description"].ToString();
                        }
                        string address = (string)lists.Rows[j]["address"];
                        string deliver_time = ((string)lists.Rows[j]["deliver_time"]).Replace(":00","");
                        int deliver_price = (int)lists.Rows[j]["deliver_price"];
                        int total_price = (int)lists.Rows[j]["total_price"];

                        excel.text(currentRow, 1, articul);
                        excel.text(currentRow, 2, good_name);
                        if (weight == null || weight == "")
                        {
                            excel.text(currentRow, 3, weight_product);
                        }
                        else
                        {
                            excel.text(currentRow, 3, weight);
                        }
                        excel.text(currentRow, 4, countStr);
                        excel.text(currentRow, 5, good_price.ToString());
                        excel.text(currentRow, 6, deliver_price.ToString());
                        excel.text(currentRow, 7, total_price.ToString());
                        excel.text(currentRow, 8, phones);
                        excel.text(currentRow, 9, address + " " + description+" №"+currentOrderID.ToString());
                        excel.text(currentRow, 10, deliver_time);

                        if (j + 1 == lists.Rows.Count || (int)lists.Rows[j + 1]["id"] != orderID)// Last item in this order
                        {
                            info += lastOrderRow.ToString() + " - " + currentRow + "; ";
                            currentOrderIndex++;
                            if (currentColor == blue)
                            {
                                currentColor = yellow;
                            }
                            else
                            {
                                currentColor = blue;
                            }
                            
                            excel.mergeCells(6, lastOrderRow, 6, currentRow);
                            excel.mergeCells(7, lastOrderRow, 7, currentRow);
                            excel.mergeCells(8, lastOrderRow, 8, currentRow);
                            excel.mergeCells(9, lastOrderRow, 9, currentRow);
                            excel.mergeCells(10, lastOrderRow, 10, currentRow);
                            double hh = 12.75;
                            if (currentRow - lastOrderRow == 0)
                            {
                                hh = 38.25;
                            }
                            if (currentRow - lastOrderRow == 1)
                            {
                                hh = 19.125;
                            }
                            for (int k = lastOrderRow; k < currentRow + 1; k++)
                            {
                                excel.setHeight(k, 1, hh);
                            }
                            lastOrderRow = currentRow + 1;
                        }
                    }
                    excel.text(1, 2, fio + "_" + currentOrderIndex + "шт_" + weight_total.ToString() + "кг");

                    excel.makeFormula(1, 5, "=G1-F1");
                    excel.makeFormula(1, 6, "=SUM(F2:F" + currentRow + ")");
                    excel.makeFormula(1, 7, "=SUM(G2:G" + currentRow + ")");
                    excel.makeBorder(1, driversStartRow + 1, 10, currentRow, Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin,true);
                    excel.makeFont(1, 2, 10, currentRow + 1, false, false, false, 10);
                    excel.makeFont(1, 1, 10, currentRow + 1, "Arial");

                    excel.setWidth(1, 1, 5.00);
                    excel.setWidth(1, 2, 32.43);
                    excel.setWidth(1, 3, 6.14);
                    excel.setWidth(1, 4, 5.14);
                    excel.setWidth(1, 5, 5.14);
                    excel.setWidth(1, 6, 5.14);
                    excel.setWidth(1, 7, 5.29);
                    excel.setWidth(1, 8, 12.71);
                    excel.setWidth(1, 9, 36.50);
                    excel.setWidth(1, 10, 11);
                    
                    excel.setWrapText(1, true);
                    excel.setWrapText(9, true);
                    excel.makeFont(7, 2, 7, currentRow, true, false, false, 10);
                    excel.makeFont(5, 1, 7, 1, true, false, false, 10);

                    excel.setTextAlignment(1, 5, 1, 7, ExcelTextHorizontalAlignment.Center, ExcelTextVerticalAlignment.AlignCenter);
                    excel.setTextAlignment(2, 1, currentRow, 1, ExcelTextHorizontalAlignment.Center, ExcelTextVerticalAlignment.AlignCenter);
                    excel.setTextAlignment(2, 2, currentRow, 2, ExcelTextHorizontalAlignment.AlignLeft, ExcelTextVerticalAlignment.AlignCenter);//Names
                    excel.setTextAlignment(2, 3, currentRow, 8, ExcelTextHorizontalAlignment.Center, ExcelTextVerticalAlignment.AlignCenter);
                    excel.setTextAlignment(2, 9, currentRow, 9, ExcelTextHorizontalAlignment.AlignLeft, ExcelTextVerticalAlignment.AlignTop);
                    excel.setTextAlignment(2, 10, currentRow, 10, ExcelTextHorizontalAlignment.Center, ExcelTextVerticalAlignment.AlignCenter);
                    if (i + 1 < drivers.Rows.Count)
                    {
                        excel.moveToNextSheet();
                        excel.changeOrientation(Microsoft.Office.Interop.Excel.XlPageOrientation.xlLandscape);
                    }
                    currentRow = 1;
                }

                DirectoryInfo dir = new DirectoryInfo(roadListsDirectory + "/" + MainForm.dbProc.getServerTime().Year.ToString().Replace(".", "_") + "/");
                if (!dir.Exists)
                {
                    dir.Create();
                }
                //string month = MainForm.dbProc.getServerTime().ToString("MMMM");
                string fileName = dir.FullName + "Маршрут_" + TimePickerDeliverListDate.Value.Month.ToString("MMMM") + "_" + TimePickerDeliverListDate.Value.Day.ToString().Replace(".", "_");
                excel.Save(fileName);
                //writer.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        void excel_OnExcelClose(Microsoft.Office.Interop.Excel.Workbook Wb, Microsoft.Office.Interop.Excel.Window Wn
            ,Microsoft.Office.Interop.Excel.Application app
            ,Microsoft.Office.Interop.Excel.Workbook book
            ,Microsoft.Office.Interop.Excel.Sheets sheets
            ,FTwoExcelProcessor proc)
        {
            try
            {
                ButtGenerateDeliverListsFile.Enabled = true;
            }
            catch (Exception ex)
            {

            }
        }
        private string currentDir = Environment.CurrentDirectory;
        private string roadListsDirectory = Environment.CurrentDirectory + "/Маршруты и Чеки/";
        private void createDirectories()
        {
            DirectoryInfo dir = new DirectoryInfo(roadListsDirectory);
            if (!dir.Exists)
            {
                dir.Create();
            }
        }
        private void ButtGenerateReceipt_Click(object sender, EventArgs e)
        {
            try
            {
                DataTable drivers = dbProc.executeGet("SELECT *, substring(first_name from 1 for 1)||'. '||second_name AS fio FROM users WHERE role_id=3 ORDER BY fio");
                DataTable orders = null;
                for (int i = 0; i < drivers.Rows.Count; i++)// For each driver
                {
                    int driver_id = (int)drivers.Rows[i]["id"];
                    string sqlCommand = "SELECT owd.articul,owd.id,owd.phone_1,owd.phones,owd.good_name,owd.deliver_date,owd.count,owd.address,owd.deliver_time,owd.total_price,owd.goods_price,owd.good_price,owd.deliver_price,owd.client_name,owd.discount_percent FROM orders_with_details owd, delivery_lists dl WHERE owd.deliver_date=date(" + MainForm.dbProc.getDateTimeString(TimePickerDeliverListDate.Value) + ") AND owd.id=dl.order_id AND dl.driver_id=" + driver_id + " ORDER BY dl.priority,owd.id";
                    DataTable forDriver = dbProc.executeGet(sqlCommand);

                    if (orders != null)
                    {
                        for (int ll = 0; ll < forDriver.Rows.Count; ll++)
                        {
                            orders.Rows.Add(forDriver.Rows[ll].ItemArray);
                        }
                    }
                    else
                    {
                        orders = forDriver;
                    }
                }

                string seller_requisites = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_requisites'").Rows[0][0];
                string seller_phone = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_phone'").Rows[0][0];
                string seller_fio = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_fio'").Rows[0][0];
                FTwoExcelProcessor excel = new FTwoExcelProcessor();
                excel.InitApplication(true);
                excel.OnExcelClose += new FTwoExcelProcessor.ExcelCloseHandler(proc_OnExcelClose);
                ButtGenerateReceipts.Enabled = false;
                long receiptNumber = (long)dbProc.executeGet("SELECT nextval('receipt_seq')").Rows[0][0];
                int orderID = 0;
                int currentOrderItemIndex = 0;
                int orderIndex = 1;
                for (int i = 0; i < orders.Rows.Count; i++)
                {
                    string deliver_date = ((DateTime)orders.Rows[i]["deliver_date"]).ToShortDateString();
                    string receipt_number = (receiptNumber + orderIndex).ToString() + " от " + deliver_date;
                    string good_name = (string)orders.Rows[i]["good_name"];
                    string count = orders.Rows[i]["count"].ToString();
                    string discount_percent = orders.Rows[i]["discount_percent"].ToString();
                    string good_price = orders.Rows[i]["good_price"].ToString();
                    string goods_price = orders.Rows[i]["goods_price"].ToString();
                    string good_item_price = ((int)orders.Rows[i]["good_price"] * (int)orders.Rows[i]["count"]).ToString();
                    string total_price = orders.Rows[i]["total_price"].ToString();
                    string articul = orders.Rows[i]["articul"].ToString();
                    string deliver_price = orders.Rows[i]["deliver_price"].ToString();
                    string buyer = orders.Rows[i]["client_name"].ToString() + ", тел."
                        + orders.Rows[i]["phone_1"].ToString()
                        + ", " + orders.Rows[i]["address"].ToString();

                    if (orderID == 0)
                    {
                        orderID = (int)orders.Rows[i]["id"];
                    }
                    currentOrderItemIndex++;

                    excel.text(5, 1, "№");
                    excel.text(5, 2, "Арт");
                    excel.text(5, 3, "Наименование");
                    excel.text(5, 4, "Кол-во");
                    excel.text(5, 5, "Цена");
                    excel.text(5, 6, "Сумма");

                    excel.text(5 + currentOrderItemIndex, 1, currentOrderItemIndex.ToString());
                    excel.text(5 + currentOrderItemIndex, 2, articul);
                    excel.text(5 + currentOrderItemIndex, 3, good_name);
                    excel.text(5 + currentOrderItemIndex, 4, count);
                    excel.text(5 + currentOrderItemIndex, 5, good_price);
                    excel.text(5 + currentOrderItemIndex, 6, good_item_price);
                    if (orders.Rows.Count - 1 == i || (int)orders.Rows[i + 1]["id"] != orderID)// End or next order
                    {
                        // Need to create header and footer
                        // Then turn current sheet
                        excel.mergeCells(2, 1, 3, 1);
                        excel.mergeCells(2, 2, 6, 2);
                        excel.mergeCells(2, 3, 3, 3);
                        excel.mergeCells(2, 4, 6, 4);
                        excel.mergeCells(3, currentOrderItemIndex + 6, 5, currentOrderItemIndex + 6);
                        excel.mergeCells(3, currentOrderItemIndex + 7, 5, currentOrderItemIndex + 7);

                        excel.makeFont(1, 2, 1, 4, true, false, false, 12);
                        excel.makeFont(2, 1, 2, 1, true, false, false, 12);
                        excel.makeFont(2, 5, 6, 5, true, false, false, 12);

                        excel.text(6 + currentOrderItemIndex, 3, "Скидка");
                        excel.text(6 + currentOrderItemIndex, 6, discount_percent + "%");
                        excel.text(7 + currentOrderItemIndex, 3, "Услуги по доставке заказа");
                        excel.text(7 + currentOrderItemIndex, 6, deliver_price);
                        
                        
                        excel.text(1, 2, "Товарный чек № " + receipt_number);
                        
                        
                        excel.text(2, 1, "Продавец: ");
                        excel.text(2, 2, seller_requisites);
                        excel.text(3, 2, "тел. " + seller_phone);
                        excel.text(4, 1, "Покупатель: ");
                        excel.text(4, 2, "№"+orderID.ToString()+", "+buyer);

                        excel.text(currentOrderItemIndex + 8, 2, "Всего отпущено товаров и оказано услуг на сумму: ");
                        excel.text(currentOrderItemIndex + 8, 6, total_price + " руб.");
                        

                        excel.makeFont(1, currentOrderItemIndex + 8, 6, currentOrderItemIndex + 8, true, false, false, 12);

                        excel.text(currentOrderItemIndex + 10, 2, "Отпуск товара разрешил: ");
                        excel.text(currentOrderItemIndex + 10, 5, "");
                        excel.text(currentOrderItemIndex + 10, 6, seller_fio);
                        excel.mergeCells(2, currentOrderItemIndex + 10, 3, currentOrderItemIndex + 10);


                        excel.makeBorder(1, 5, 6, currentOrderItemIndex + 7, Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin, true);
                        excel.setImage(130 + currentOrderItemIndex*15,145, 100,90, Environment.CurrentDirectory + "/Resources/signature.jpg");
                        excel.setHeight(4, 2, 30);
                        excel.setTextAlignment(4, 2, 4, 2, ExcelTextHorizontalAlignment.AlignLeft, ExcelTextVerticalAlignment.AlignJustify);
                        excel.setTextAlignment(5, 1, 20, 2, ExcelTextHorizontalAlignment.CenterAcrossSelection, ExcelTextVerticalAlignment.AlignCenter);
                        excel.setWidth(1, 1, 13);
                        excel.setWidth(1, 2, 4);
                        excel.setWidth(1, 3, 32);
                        excel.setWidth(1, 4, 8);
                        excel.setWidth(1, 5, 10.7);
                        excel.setWidth(1, 6, 11.7);
                        currentOrderItemIndex = 0;
                        if (orders.Rows.Count > i + 1)
                        {
                            orderID = (int)orders.Rows[i + 1]["id"];
                        }

                        orderIndex++;
                        excel.renameSheet("№" + receipt_number);
                        if (orders.Rows.Count - 1 != i)
                        {
                            excel.moveToNextSheet();
                        }
                    }
                }
                DirectoryInfo dir = new DirectoryInfo(roadListsDirectory + "/" + MainForm.dbProc.getServerTime().Year.ToString().Replace(".", "_") + "/");
                if (!dir.Exists)
                {
                    dir.Create();
                }
                string month = MainForm.dbProc.getServerTime().ToString("MMMM");
                string fileName = dir.FullName + "Чеки_" + month + "_" + MainForm.dbProc.getServerTime().Day.ToString().Replace(".", "_");
                excel.Save(fileName);
                //writer.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        void proc_OnExcelClose(Microsoft.Office.Interop.Excel.Workbook Wb, Microsoft.Office.Interop.Excel.Window Wn, Microsoft.Office.Interop.Excel.Application app, Microsoft.Office.Interop.Excel.Workbook book, Microsoft.Office.Interop.Excel.Sheets sheets,FTwoExcelProcessor proc)
        {
            ButtGenerateReceipts.Enabled = true;
        }

        private void MenuButtDeleteOrder_Click(object sender, EventArgs e)
        {
            DialogResult res = MessageBox.Show("Вы действительно хотите удалить заказ?", "Удаление заказа", MessageBoxButtons.YesNo);
            if (res == System.Windows.Forms.DialogResult.Yes)
            {
                DataTable ordr = dbProc.executeGet("SELECT status FROM orders WHERE id=" + currentOrderID.ToString());
                if ((int)ordr.Rows[0]["status"] == 3)
                {
                    MessageBox.Show("Нельзя удалять выполненные заказы");
                    return;
                }
                dbProc.executeNonQuery("UPDATE orders SET status=4,modifier_user_id=" + currentUser.ID + ",modifier_name='" + currentUser.getFullName() + "' WHERE id=" + currentOrderID.ToString());
                Hashtable historyParams = new Hashtable();
                historyParams.Add("user_id", MainForm.currentUser.ID);
                historyParams.Add("order_id", currentOrderID);
                historyParams.Add("modifier_user_id", MainForm.currentUser.ID);
                historyParams.Add("modifier_name", MainForm.currentUser.getFullName());
                historyParams.Add("action", "Удаление");
                MainForm.dbProc.insert("history", historyParams);
                dbProc.executeNonQuery("DELETE FROM delivery_lists WHERE order_id=" + currentOrderID.ToString());
                
                loadOrders(false);
                loadDrivers(false);
            }
        }

        private void DataGridViewOrders_RowStateChanged(object sender, DataGridViewRowStateChangedEventArgs e)
        {
            try
            {
                if (e.StateChanged == DataGridViewElementStates.Selected)
                {
                    currentOrderID = (int)DataGridViewOrders.SelectedRows[0].Cells["id"].Value;
                    int currentStatus = (int)DataGridViewOrders.SelectedCells[0].OwningRow.Cells["status_id"].Value;
                    if (currentStatus==1 || currentStatus==2)
                    {
                        ButtChangeOrderStatus.Enabled = true;
                    }
                    MenuButtDeleteOrder.Enabled = true;
                    MenuButtEditOrder.Enabled = true;
                }
                else
                {
                    MenuButtDeleteOrder.Enabled = false;
                    MenuButtEditOrder.Enabled = false;
                    ButtChangeOrderStatus.Enabled = false;
                }
            }
            catch
            {
                MenuButtDeleteOrder.Enabled = false;
                MenuButtEditOrder.Enabled = false;
                ButtChangeOrderStatus.Enabled = false;
            }
        }

        private void TimePickerDeliverListDate_ValueChanged(object sender, EventArgs e)
        {
            loadDrivers(false);
            loadDeliverLists();
        }

        private void ButtOpenDeliverListsForm_Click(object sender, EventArgs e)
        {
            DeliveryLists.EditDeliveryListsForm f = new DeliveryLists.EditDeliveryListsForm(this, TimePickerDeliverListsFormDate.Value);
            f.Visible = true;
            f.Activate();
            PanelOpenDeliverListForm.Visible = false;
        }

        private void ButtCancelDeliverListsForm_Click(object sender, EventArgs e)
        {
            PanelOpenDeliverListForm.Visible = false;
        }
        private void ButtOrderStatusNew_Click(object sender, EventArgs e)
        {
            if (currentOrderID > 0)
            {
                DataTable ordr = dbProc.executeGet("SELECT status FROM orders WHERE id=" + currentOrderID.ToString());
                if ((int)ordr.Rows[0]["status"] == 3)
                {
                    MessageBox.Show("Выполненным заказам нельзя менять статус. "
                    +"Для внесения изменения в остатки нужно отредактировать выполненный заказ.");
                    return;
                }
                dbProc.executeNonQuery("UPDATE orders SET status=1,modifier_user_id="+currentUser.ID+",modifier_name='"+currentUser.getFullName()+"' WHERE id=" + currentOrderID.ToString());
                //TabControlMain_SelectedIndexChanged(null, null);
            }
        }
        private void ButtOrderStatusDelay_Click(object sender, EventArgs e)
        {
            if (currentOrderID > 0)
            {
                DataTable ordr = dbProc.executeGet("SELECT status FROM orders WHERE id=" + currentOrderID.ToString());
                if ((int)ordr.Rows[0]["status"] == 3)
                {
                    MessageBox.Show("Выполненным заказам нельзя менять статус. "
                    + "Для внесения изменения в остатки нужно отредактировать выполненный заказ.");
                    return;
                }
                dbProc.executeNonQuery("UPDATE orders SET status=2,modifier_user_id=" + currentUser.ID + ",modifier_name='" + currentUser.getFullName() + "' WHERE id=" + currentOrderID.ToString());
                dbProc.executeNonQuery("DELETE FROM delivery_lists WHERE order_id=" + currentOrderID.ToString());
                //TabControlMain_SelectedIndexChanged(null, null);
            }
        }
        private void ButtOrderStatusFinished_Click(object sender, EventArgs e)
        {
            if (currentOrderID > 0)
            {
                DataTable ordr = dbProc.executeGet("SELECT status FROM orders WHERE id="+currentOrderID.ToString());
                if ((int)ordr.Rows[0]["status"] != 1)
                {
                    MessageBox.Show("Только новые заказы можно помечать как 'Выполненные'");
                    return;
                }
                if ((int)ordr.Rows[0]["status"] == 3)
                {
                    return;
                }
                LabelProgress.Text = "Обновление остатков на складе.";
                ButtProcess.Visible = true;
                DataTable ordersToAccept = dbProc.executeGet("SELECT * FROM orders_with_details WHERE status_id=1 AND id="+currentOrderID.ToString());
                List<string> UpdateQuantities = new List<string>();
                for (int i = 0; i < ordersToAccept.Rows.Count; i++)
                {
                    int count = (int)ordersToAccept.Rows[i]["count"];
                    int good_id = (int)ordersToAccept.Rows[i]["good_id"];
                    int order_id = (int)ordersToAccept.Rows[i]["id"];

                    string command = "UPDATE goods SET quantity=quantity-("
                        + count.ToString()
                        + ") WHERE id="
                        + good_id.ToString()
                        + "; ";
                    DataTable before = MainForm.dbProc.executeGet("SELECT quantity FROM goods WHERE id=" + good_id.ToString());
                    dbProc.executeNonQuery(command);
                    DataTable after = MainForm.dbProc.executeGet("SELECT quantity FROM goods WHERE id=" + good_id.ToString());
                    Application.DoEvents();
                    if (before.Rows.Count == 1)
                    {
                        int q_before = (int)before.Rows[0]["quantity"];
                        int q_after = (int)after.Rows[0]["quantity"];
                        MainForm.dbProc.executeNonQuery("INSERT INTO goods_history (action,good_id,quantity_before,quantity_after,description,good_action_count) VALUES('orders_accept'," + good_id + "," + q_before + "," + q_after + ",'Order:" + order_id +" user: "+currentUser.ID.ToString()+ "'," + count + ");");
                    }
                }
                
                //TODO: change quantity
                dbProc.executeNonQuery("UPDATE orders SET status=3,modifier_user_id=" + currentUser.ID + ",modifier_name='" + currentUser.getFullName() + "' WHERE id=" + currentOrderID.ToString());
                //loadOrders(true);
                ButtProcess.Visible = false;
                LabelProgress.Text = "";
            }
        }
        int currentOrderStatusIndex = 0;
        private void ListOrderStatuses_SelectedIndexChanged(object sender, EventArgs e)
        {
            currentOrderStatusIndex = ListOrderStatuses.SelectedIndex;
            //TabControlMain_SelectedIndexChanged(null, null);
        }
        private void ButtClosePanelSetMinimum_Click(object sender, EventArgs e)
        {
            PanelSetMinimum.Visible = false;
            DataGridViewGoods.Enabled = true;
        }
        private void button1_Click_2(object sender, EventArgs e)
        {
            PanelSetQuantity.Visible = false;
            DataGridViewGoods.Enabled = true;
        }
        private void ButtOKQuantity_Click(object sender, EventArgs e)
        {
            ProgressBarMain.Maximum = currentGoodIDs.Count;
            for (int i = 0; i < currentGoodIDs.Count; i++)
            {
                Hashtable par = new Hashtable();
                par.Add("quantity", (int)NumGoodQuantity.Value);
                DataTable before = MainForm.dbProc.executeGet("SELECT quantity FROM goods WHERE id=" + currentGoodIDs[i].ToString());
                dbProc.update("goods", par, "id=" + currentGoodIDs[i]);
                DataTable after = MainForm.dbProc.executeGet("SELECT quantity FROM goods WHERE id=" + currentGoodIDs[i].ToString());
                Application.DoEvents();
                if (before.Rows.Count == 1)
                {
                    int q_before = (int)before.Rows[0]["quantity"];
                    int q_after = (int)after.Rows[0]["quantity"];
                    MainForm.dbProc.executeNonQuery("INSERT INTO goods_history (action,good_id,quantity_before,quantity_after,description,good_action_count) VALUES('manual_update'," + currentGoodIDs[i] + "," + q_before + "," + q_after + ",'User: " + currentUser.ID.ToString() + "'," + (q_after-q_before).ToString() + ");");
                }
                
                ProgressBarMain.Value = i;
                Application.DoEvents();
            }
            ProgressBarMain.Value = 0;
            PanelSetQuantity.Visible = false;
            DataGridViewGoods.Enabled = true;
            //loadGoods();
        }

        private void toolStripMenuItem7_Click(object sender, EventArgs e)
        {
            Statistics.StatisticsForm f = new Statistics.StatisticsForm();
            f.Visible = true;
            f.Activate();
        }

        private void MenuButtAbout_Click(object sender, EventArgs e)
        {
            LogoForm f = new LogoForm();
            f.setVersion("3.0");
            f.Activate();
            f.Visible = true;
        }

        private void ButtDBSettings_Click(object sender, EventArgs e)
        {
            DBAuthorizationForm f = new DBAuthorizationForm(this);
            SetEnabled(true);
            f.Activate();
            f.Visible = true;
        }

        private void TimerDisabler_Tick(object sender, EventArgs e)
        {
            LabelProgress.Text = "";
            TimerDisabler.Enabled = false;
        }

        private string Purchased_Status = "1";
        private void CheckAlreadyPurchased_CheckedChanged(object sender, EventArgs e)
        {
            if (CheckAlreadyPurchased.Checked)
            {
                Purchased_Status = "2";
                loadPurchaseTable(false);
                return;
            }
            Purchased_Status = "1";
            loadPurchaseTable(false);
        }

        private void AcceptOrderFilter()
        {
            ((DataTable)DataGridViewOrders.DataSource).DefaultView.RowFilter = OrderFilter;
        }
        private string OrderFilter = "";
        private void GenerateOrderFilter()
        {
            OrderFilter = "";
            if (ComboOrderFilterName.Text.ToLower() == "артикул")
            {
                OrderFilter = " articul LIKE '%" + TxtOrderFilterValue.Text + "%'";
            }
            if (ComboOrderFilterName.Text.ToLower() == "номер заказа")
            {
                int for_out = 0;
                if(Int32.TryParse(TxtOrderFilterValue.Text,out for_out))
                {
                    OrderFilter = " id = " + TxtOrderFilterValue.Text + "";
                }
            }
            if (ComboOrderFilterName.Text.ToLower() == "телефон")
            {
                OrderFilter = " phones LIKE '%" + TxtOrderFilterValue.Text + "%'";
            }
            if (ComboOrderFilterName.Text.ToLower() == "товар")
            {
                OrderFilter = " good_name LIKE '%" + TxtOrderFilterValue.Text + "%'";
            }
            if (ComboOrderFilterName.Text.ToLower() == "клиент")
            {
                OrderFilter = " client_name LIKE '%" + TxtOrderFilterValue.Text + "%'";
            }
            if (ComboOrderFilterName.Text.ToLower() == "водитель")
            {
                OrderFilter = " owd.driver LIKE '%" + TxtOrderFilterValue.Text + "%'";
            }
            if (ComboOrderFilterName.Text.ToLower() == "адрес")
            {
                OrderFilter = " address LIKE '%" + TxtOrderFilterValue.Text + "%'";
            }
            if (ComboOrderFilterSelfget.Text.ToLower() == "самовывоз")
            {
                if (OrderFilter != "")
                {
                    OrderFilter = OrderFilter + " AND ";
                }
                OrderFilter += " owd.driver LIKE '%амовывоз%'";
            }
            if (ComboOrderFilterSelfget.Text.ToLower() == "доставка")
            {
                if (OrderFilter != "")
                {
                    OrderFilter = OrderFilter + " AND ";
                }
                OrderFilter += " owd.driver NOT LIKE '%амовывоз%'";
            }
            if (OrderFilter != "")
            {
                OrderFilter = " AND " + OrderFilter;
            }
        }
        private void TxtOrderFilterValue_TextChanged(object sender, EventArgs e)
        {
            try
            {
                GenerateOrderFilter();
                //AcceptOrderFilter();
            }
            catch (Exception ex)
            {

            }
        }

        private void ButtRefresh_Click(object sender, EventArgs e)
        {
            loadOrders(true);
        }

        private void ButtGenerateReceipt_Click_1(object sender, EventArgs e)
        {
            try
            {
                int order_id = currentDeliveryListOrder;
                DataTable drivers = dbProc.executeGet("SELECT *, substring(first_name from 1 for 1)||'. '||second_name AS fio FROM users WHERE role_id=3 ORDER BY fio");
                DataTable orders = null;
                string sqlCommand = "SELECT owd.id,owd.phone_1,owd.phone_2,owd.phone_3,owd.phones,owd.good_name,owd.deliver_date,owd.count,owd.address,owd.deliver_time,owd.total_price,owd.goods_price,owd.good_price,owd.deliver_price,owd.client_name,owd.discount_percent FROM orders_with_details owd, delivery_lists dl WHERE owd.id=dl.order_id AND dl.order_id=" + order_id + " ORDER BY dl.priority";
                orders = dbProc.executeGet(sqlCommand);

                string seller_requisites = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_requisites'").Rows[0][0];
                string seller_phone = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_phone'").Rows[0][0];
                string seller_fio = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_fio'").Rows[0][0];
                FTwoExcelProcessor excel = new FTwoExcelProcessor();
                excel.InitApplication(true);
                excel.OnExcelClose += new FTwoExcelProcessor.ExcelCloseHandler(proc_OnExcelClose);
                ButtGenerateReceipt.Enabled = false;
                long receiptNumber = (long)dbProc.executeGet("SELECT nextval('receipt_seq')").Rows[0][0];
                int orderID = 0;
                int currentOrderItemIndex = 0;
                int orderIndex = 1;
                for (int i = 0; i < orders.Rows.Count; i++)
                {
                    string deliver_date = ((DateTime)orders.Rows[i]["deliver_date"]).ToShortDateString();
                    string receipt_number = (receiptNumber + orderIndex).ToString() + " от " + deliver_date;
                    string good_name = (string)orders.Rows[i]["good_name"];
                    string count = orders.Rows[i]["count"].ToString();
                    string discount_percent = orders.Rows[i]["discount_percent"].ToString();
                    string good_price = orders.Rows[i]["good_price"].ToString();
                    string goods_price = orders.Rows[i]["goods_price"].ToString();
                    string good_item_price = ((int)orders.Rows[i]["good_price"] * (int)orders.Rows[i]["count"]).ToString();
                    string total_price = orders.Rows[i]["total_price"].ToString();
                    string deliver_price = orders.Rows[i]["deliver_price"].ToString();
                    string buyer = orders.Rows[i]["client_name"].ToString() + ", тел."
                        + orders.Rows[i]["phone_1"].ToString()
                        +", "
                        + orders.Rows[i]["phone_2"].ToString()
                        +", "
                        + orders.Rows[i]["phone_3"].ToString()
                        + ", " + orders.Rows[i]["address"].ToString();

                    if (orderID == 0)
                    {
                        orderID = (int)orders.Rows[i]["id"];
                    }
                    currentOrderItemIndex++;

                    excel.text(5, 2, "№");
                    excel.text(5, 3, "Наименование");
                    excel.text(5, 4, "Кол-во");
                    excel.text(5, 5, "Цена");
                    excel.text(5, 6, "Сумма");

                    excel.text(5 + currentOrderItemIndex, 2, currentOrderItemIndex.ToString());
                    excel.text(5 + currentOrderItemIndex, 3, good_name);
                    excel.text(5 + currentOrderItemIndex, 4, count);
                    excel.text(5 + currentOrderItemIndex, 5, good_price);
                    excel.text(5 + currentOrderItemIndex, 6, good_item_price);
                    if (orders.Rows.Count - 1 == i || (int)orders.Rows[i + 1]["id"] != orderID)// End or next order
                    {
                        // Need to create header and footer
                        // Then turn current sheet
                        excel.mergeCells(2, 1, 3, 1);
                        excel.mergeCells(2, 2, 6, 2);
                        excel.mergeCells(2, 3, 3, 3);
                        excel.mergeCells(2, 4, 6, 4);
                        excel.mergeCells(3, currentOrderItemIndex + 6, 5, currentOrderItemIndex + 6);
                        excel.mergeCells(3, currentOrderItemIndex + 7, 5, currentOrderItemIndex + 7);

                        excel.makeFont(1, 2, 1, 4, true, false, false, 12);
                        excel.makeFont(2, 1, 2, 1, true, false, false, 12);
                        excel.makeFont(2, 5, 6, 5, true, false, false, 12);

                        excel.text(6 + currentOrderItemIndex, 3, "Скидка");
                        excel.text(6 + currentOrderItemIndex, 6, discount_percent + "%");
                        excel.text(7 + currentOrderItemIndex, 3, "Услуги по доставке заказа");
                        excel.text(7 + currentOrderItemIndex, 6, deliver_price);
                        

                        excel.text(1, 2, "Товарный чек № " + receipt_number);


                        excel.text(2, 1, "Продавец: ");
                        excel.text(2, 2, seller_requisites);
                        excel.text(3, 2, "тел. " + seller_phone);
                        excel.text(4, 1, "Покупатель: ");
                        excel.text(4, 2, buyer);
                        excel.setHeight(4,2,30);
                        excel.setTextAlignment(4, 2, 4, 2, ExcelTextHorizontalAlignment.AlignLeft, ExcelTextVerticalAlignment.AlignJustify);

                        excel.text(currentOrderItemIndex + 8, 2, "Всего отпущено товаров и оказано услуг на сумму: ");
                        excel.text(currentOrderItemIndex + 8, 6, total_price + " руб.");


                        excel.makeFont(2, currentOrderItemIndex + 8, 6, currentOrderItemIndex + 8, true, false, false, 12);
                        excel.text(currentOrderItemIndex + 10, 2, "Отпуск товара разрешил: ");
                        //Заказ получил, претензий по комплекту, упаковке и внешнему виду товаров не имею, правее слова "Подпись Клиента" и "Фамилия И.О.". Если влезет, то ниже две черты, над которыми клиент предположительно ставит подпись и ее расшифровку.
                        excel.text(currentOrderItemIndex + 11, 2, "Заказ получил, претензий по комплекту, упаковке и внешнему виду товаров не имею.");
                        excel.text(currentOrderItemIndex + 12, 2, "Подпись клиента ____ ФИО _______________");
                        excel.text(currentOrderItemIndex + 10, 6, seller_fio);
                        excel.text(currentOrderItemIndex + 10, 3, "Отпуск товара разрешил: ");
                        excel.mergeCells(2, currentOrderItemIndex + 10, 4, currentOrderItemIndex + 10);
                        excel.mergeCells(2, currentOrderItemIndex + 11, 6, currentOrderItemIndex + 11);
                        excel.setHeight(currentOrderItemIndex + 11, 2,30);
                        excel.setTextAlignment(currentOrderItemIndex + 11, 2, currentOrderItemIndex + 11, 2, ExcelTextHorizontalAlignment.AlignLeft, ExcelTextVerticalAlignment.AlignJustify);
                        excel.mergeCells(2, currentOrderItemIndex + 12, 6, currentOrderItemIndex + 12);


                        excel.makeBorder(2, 5, 6, currentOrderItemIndex + 7, Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin, true);
                        excel.setImage(130 + currentOrderItemIndex * 15, 145, 100, 90, Environment.CurrentDirectory + "/Resources/signature.jpg");
                        excel.setWidth(1, 1, 13);
                        excel.setWidth(1, 2, 4);
                        excel.setWidth(1, 3, 32);
                        excel.setWidth(1, 4, 8);
                        excel.setWidth(1, 5, 10.7);
                        excel.setWidth(1, 6, 11.7);
                        currentOrderItemIndex = 0;
                        if (orders.Rows.Count > i + 1)
                        {
                            orderID = (int)orders.Rows[i + 1]["id"];
                        }

                        orderIndex++;
                        excel.renameSheet("№" + receipt_number);
                        if (orders.Rows.Count - 1 != i)
                        {
                            excel.moveToNextSheet();
                        }
                    }
                }
                DirectoryInfo dir = new DirectoryInfo(roadListsDirectory + "/" + MainForm.dbProc.getServerTime().Year.ToString().Replace(".", "_") + "/");
                if (!dir.Exists)
                {
                    dir.Create();
                }
                string month = MainForm.dbProc.getServerTime().ToString("MMMM");
                string fileName = dir.FullName + "Чеки_" + month + "_" + MainForm.dbProc.getServerTime().Day.ToString().Replace(".", "_");
                excel.Save(fileName);
            }
            catch (Exception ex)
            {
            }
        }
        private int currentDeliveryListOrder = 0;
        private void DataGridViewDeliveryLists_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            try
            {
                if (DataGridViewDeliveryLists.SelectedCells != null & DataGridViewDeliveryLists.SelectedCells.Count == 1)
                {
                    currentDeliveryListOrder = (int)DataGridViewDeliveryLists.SelectedCells[0].OwningRow.Cells["id"].Value;
                    ButtGenerateReceipt.Enabled = true;
                }
                else
                {
                    ButtGenerateReceipt.Enabled = false;
                }
            }
            catch
            {
                ButtGenerateReceipt.Enabled = false;
            }
        }
        private int currentGoodID = 0;
        private void DataGridViewGoods_CellStateChanged(object sender, DataGridViewCellStateChangedEventArgs e)
        {
            try
            {
                if (e.StateChanged == DataGridViewElementStates.Selected)
                {
                    currentGoodID = (int)DataGridViewGoods.SelectedCells[0].OwningRow.Cells["id"].Value;
                    ButtEditGood.Enabled = true;
                    ButtDeleteGood.Enabled = true;
                }
                else
                {
                    ButtEditGood.Enabled = false;
                    ButtDeleteGood.Enabled = false;
                }
            }
            catch
            {
                ButtEditGood.Enabled = false;
            }
        }

        private void ButtEditGood_Click(object sender, EventArgs e)
        {
            Goods.AddEditGoodForm form = new Goods.AddEditGoodForm(currentGoodID);
            form.Visible = true;
            form.Activate();
        }

        private void ButtDeleteGood_Click(object sender, EventArgs e)
        {
            DialogResult res = MessageBox.Show("Удалить товар?", "Удаление товара", MessageBoxButtons.YesNo);
            if (res == System.Windows.Forms.DialogResult.Yes)
            {
                dbProc.delete("goods", currentGoodID);
                loadGoods();
            }
        }

        private void ButtGoodsHistory_Click(object sender, EventArgs e)
        {
            Statistics.GoodsHistoryForm f = new Statistics.GoodsHistoryForm();
            f.Activate();
            f.Visible = true;
        }
        private string getYandexXML()
        {
            try
            {
                DataTable categories = dbProc.get("good_categories");
                DataTable goods = dbProc.executeGet("SELECT id,web_page_url,price,category_id,photo_url,name_for_shop,description_short FROM goods WHERE is_hidden = FALSE");
                StringBuilder builder = new StringBuilder();
                builder.Append("<?xml version=\"1.0\" encoding=\"windows-1251\"?>");
                builder.Append(Environment.NewLine);
                builder.Append("<!DOCTYPE yml_catalog SYSTEM \"shops.dtd\">");
                builder.Append(Environment.NewLine);
                string time_str = DateTime.Now.ToString("yyyy-MM-dd hh:mm");
                builder.Append("<yml_catalog date=\"");
                builder.Append(time_str);
                builder.Append("\">");
                builder.Append(Environment.NewLine);
                builder.Append("<shop>");
                builder.Append(Environment.NewLine);
                builder.Append("<name>ZooMag</name>");
                builder.Append(Environment.NewLine);
                builder.Append("<company>ZooMag</company>");
                builder.Append(Environment.NewLine);
                builder.Append("<url>http://myzoomag.ru/</url>");
                builder.Append(Environment.NewLine);
                builder.Append("<currencies>");
                builder.Append(Environment.NewLine);
                builder.Append("<currency id=\"RUR\" rate=\"1\"/>");
                builder.Append(Environment.NewLine);
                builder.Append("<currency id=\"USD\" rate=\"CBRF\"/>");
                builder.Append(Environment.NewLine);
                builder.Append("<currency id=\"EUR\" rate=\"CBRF\"/>");
                builder.Append(Environment.NewLine);
                builder.Append("<currency id=\"UAH\" rate=\"CBRF\"/>");
                builder.Append(Environment.NewLine);
                builder.Append("</currencies>");
                builder.Append(Environment.NewLine);
                builder.Append("<categories>");
                builder.Append(Environment.NewLine);
                for (int i = 0; i < categories.Rows.Count; i++)
                {
                    //<category id="2919" parentId="2918">Для щенков (до 1 года)</category>
                    string id = categories.Rows[i]["id"].ToString();
                    string parent_id = categories.Rows[i]["parent_id"].ToString();
                    string name = categories.Rows[i]["name"].ToString();
                    builder.Append("<category id=\"");
                    builder.Append(id);
                    builder.Append("\"");
                    if (parent_id != "0")
                    {
                        builder.Append(" parentId=\"");
                        builder.Append(parent_id);
                        builder.Append("\"");
                    }
                    builder.Append(">");
                    builder.Append(name);
                    builder.Append("</category>");
                    builder.Append(Environment.NewLine);
                }
                builder.Append("</categories>");
                builder.Append(Environment.NewLine);

                builder.Append("<offers>");
                builder.Append(Environment.NewLine);
                string good_base_url = dbProc.getSetting("good_base_url");
                string picture_base_url = dbProc.getSetting("picture_base_url");

                for (int i = 0; i < goods.Rows.Count; i++)
                {

                    //<offer available="true" id="26149">
                    //    <url>http://myzoomag.ru/product/hills_science_plan_puppy_chicken_1kg_5224/?from=ya</url>
                    //    <price>300.00</price>
                    //    <currencyId>RUR</currencyId>
                    //    <categoryId>2919</categoryId>
                    //    <picture>http://myzoomag.ru/published/publicdata/WWWMYZOOMAGRU/attachments/SC/products_pictures/big/hills_puppy_chicken.jpg</picture>
                    //    <name>Hills Science Plan Puppy Chicken 1 kg 5224 / Сухой корм Хиллс для щенков с Курицей 1 кг</name>
                    //    <description>Сухой корм Хиллс для щенков с Курицей 1 кг</description>
                    //</offer>
                    string id = goods.Rows[i]["id"].ToString();
                    string url = good_base_url + goods.Rows[i]["web_page_url"].ToString() + "/?from=ya";
                    string price = goods.Rows[i]["price"].ToString();
                    string category_id = goods.Rows[i]["category_id"].ToString();
                    string picture = picture_base_url + goods.Rows[i]["photo_url"].ToString();
                    string name = goods.Rows[i]["name_for_shop"].ToString();
                    string description = goods.Rows[i]["description_short"].ToString();


                    builder.Append("<offer available=\"true\" id=\"");
                    builder.Append(id);
                    builder.Append("\">");
                    builder.Append(Environment.NewLine);
                    builder.Append("<url>");
                    builder.Append(url);
                    builder.Append("</url>");
                    builder.Append(Environment.NewLine);
                    builder.Append("<price>");
                    builder.Append(price);
                    builder.Append("</price>");
                    builder.Append(Environment.NewLine);
                    builder.Append("<currencyId>RUR</currencyId>");
                    builder.Append(Environment.NewLine);
                    builder.Append("<categoryId>");
                    builder.Append(category_id);
                    builder.Append("</categoryId>");
                    builder.Append(Environment.NewLine);
                    builder.Append("<picture>");
                    builder.Append(picture);
                    builder.Append("</picture>");
                    builder.Append(Environment.NewLine);
                    builder.Append("<name>");
                    builder.Append(name);
                    builder.Append("</name>");
                    builder.Append(Environment.NewLine);
                    builder.Append("<description>");
                    builder.Append(description);
                    builder.Append("</description>");
                    builder.Append(Environment.NewLine);
                    builder.Append("</offer>");
                    builder.Append(Environment.NewLine);
                }
                builder.Append("</offers></shop></yml_catalog>");
                builder.Append(Environment.NewLine);
                return builder.ToString().Replace("&", "&amp;");
            }
            catch (Exception ex)
            {
                return "";
            }
        }
        private void ButtGenerateYandexXML_Click(object sender, EventArgs e)
        {
            try
            {
                DialogResult res = DialogSaveYandexXML.ShowDialog();
                if (res == System.Windows.Forms.DialogResult.OK)
                {
                    StreamWriter writer = new StreamWriter(DialogSaveYandexXML.FileName,false,Encoding.GetEncoding("windows-1251"));
                    string xml = getYandexXML();
                    writer.Write(xml);
                    writer.Close();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString(), "Ошибка в генерации XML документа.");
            }
        }

        private void toolStripMenuItem5_Click(object sender, EventArgs e)
        {
            try
            {
                string xml = getYandexXML();
                // TODO: Send to server...
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString(), "Ошибка в генерации XML документа.");
            }
        }

        private void TxtOrderFilterValue_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Return)
            {
                loadOrders(true);
            }
        }

        private void ButtNextPage_Click(object sender, EventArgs e)
        {
            current_page++;
            loadOrders(true);
        }

        private void ButtPrevPage_Click(object sender, EventArgs e)
        {
            current_page--;
            loadOrders(true);
        }

        private void toolStripMenuItem7_Click_1(object sender, EventArgs e)
        {
            DataTable clients = dbProc.executeGet("SELECT id, phone_1 FROM clients ORDER BY phone_1");
            string current_client_phone = "";
            int current_client_id = 0;
            ProgressBarMain.Value = 0;
            ProgressBarMain.Maximum = clients.Rows.Count;
            for (int i = 0; i < clients.Rows.Count; i++)
            {
                if (current_client_phone != (string)clients.Rows[i]["phone_1"])
                {
                    current_client_id = (int)clients.Rows[i]["id"];
                    current_client_phone = (string)clients.Rows[i]["phone_1"];
                }
                else
                {
                    dbProc.executeNonQuery("UPDATE orders SET client_id=" + current_client_id + " WHERE client_id=" + (int)clients.Rows[i]["id"]);
                    dbProc.executeNonQuery("DELETE FROM clients WHERE id=" + (int)clients.Rows[i]["id"]);
                }
                ProgressBarMain.Value = i;
                LabelProcessName.Text = "Перебор клиентов (" + i + " из " + clients.Rows.Count + ")";
                Application.DoEvents();
            }
            MessageBox.Show("Было клиентов: " + clients.Rows.Count
                + Environment.NewLine
                + "Стало клиентов: " + dbProc.executeGet("SELECT id, phone_1 FROM clients ORDER BY phone_1").Rows.Count
                + Environment.NewLine);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            //OleDbConnection conn = new OleDbConnection();
            //String connectionString = @"Provider=Microsoft.JET.OLEDB.4.0; Data Source=C:\Users\AlThar\Documents\Visual Studio 2010\Projects\OwlBusinessStudio\OwlBusinessStudio\str.mdb";
            //conn = new OleDbConnection(connectionString);
            //conn.Open();
            //OleDbCommand comm = new OleDbCommand("SELECT * FROM Streets",conn);
            //OleDbDataAdapter adapter = new OleDbDataAdapter(comm);
            //DataTable t = new DataTable();
            //adapter.Fill(t);
            //ProgressBarMain.Maximum = t.Rows.Count;
            //ProgressBarMain.Value = 0;
            //for (int i = 0; i < t.Rows.Count; i++)
            //{
            //    string street = t.Rows[i]["INFSTREET"].ToString();
            //    string postfix = "";
            //    if (street.Contains("ул."))
            //    {
            //        postfix = "ул.";
            //    }
            //    if (street.Contains("пр."))
            //    {
            //        postfix = "пр.";
            //    }
            //    if (street.Contains("пл."))
            //    {
            //        postfix = "пл.";
            //    }
            //    if (street.Contains("пер."))
            //    {
            //        postfix = "пер.";
            //    }
            //    if (street.Contains("пос."))
            //    {
            //        postfix = "пос.";
            //    }
            //    if (street.Contains("бульв."))
            //    {
            //        postfix = "бульв.";
            //    }
            //    if (street.Contains("просп."))
            //    {
            //        postfix = "просп.";
            //    }
            //    if (street.Contains("туп."))
            //    {
            //        postfix = "туп.";
            //    }
            //    if (street.Contains("наб."))
            //    {
            //        postfix = "наб.";
            //    }
            //    dbProc.executeNonQuery("INSERT INTO streets (name,type) VALUES('" + street + "','" + postfix + "')");
            //    ProgressBarMain.Value++;
            //    LabelProcessName.Text = "Перебор улиц (" + i + " из " + t.Rows.Count + ")";
            //    Application.DoEvents();
            //}
        }

        private void ButtSiteContent_Click(object sender, EventArgs e)
        {
            Content.ContentForm f = new Content.ContentForm();
            f.Activate();
            f.Visible = true;
        }

        private void toolStripMenuItem10_Click(object sender, EventArgs e)
        {
            Settings.SettingsForm f = new Settings.SettingsForm();
            f.Activate();
            f.Visible = true;
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            MainForm.dbProc.executeNonQuery("INSERT INTO login_history (user_id,came_in) VALUES (" + MainForm.currentUser.ID.ToString() + ",false)");
        }

        private void ButtLoginHistory_Click(object sender, EventArgs e)
        {
            Users.LoginHistoryForm f = new Users.LoginHistoryForm();
            f.Activate();
            f.Visible = true;
        }

        private void DataGridViewDeliveryLists_RowStateChanged(object sender, DataGridViewRowStateChangedEventArgs e)
        {
            try
            {
                if (e.StateChanged == DataGridViewElementStates.Selected)
                {
                    currentOrderID = (int)DataGridViewDeliveryLists.SelectedRows[0].Cells["id"].Value;
                    MenuButtDeleteOrder.Enabled = true;
                    MenuButtEditOrder.Enabled = true;
                }
                else
                {
                    MenuButtDeleteOrder.Enabled = false;
                    MenuButtEditOrder.Enabled = false;
                }
            }
            catch
            {
                MenuButtDeleteOrder.Enabled = false;
                MenuButtEditOrder.Enabled = false;
            }
        }

        private void ButtSaveSummChange_Click(object sender, EventArgs e)
        {
            FTwoFramework.Helpers.StringCalculator calculator = new FTwoFramework.Helpers.StringCalculator(TxtTotalDescription.Text);
            try
            {
                double res = calculator.calculateSimple();
                TxtResultForDriver.Text = res.ToString();
                TxtResultForDriver.BackColor = Color.White;
            }
            catch (Exception ex)
            {
                TxtResultForDriver.BackColor = Color.Red;
            }
            
            

            Hashtable pars = new Hashtable();
            pars.Add("sum", TxtResultForDriver.Text);
            pars.Add("description", TxtTotalDescription.Text);
            pars.Add("comment", TxtCalculatorComment.Text);
            dbProc.update("delivery_lists", pars, "id=" + current_delivery_list_id);
        }

        private void ButtSetBrandOrder_Click(object sender, EventArgs e)
        {
            Settings.BrandOrderForm f = new Settings.BrandOrderForm();
            f.Activate();
            f.Visible = true;
        }

        private void RefreshGoodsButt_Click(object sender, EventArgs e)
        {
            loadGoods(ComboGoodFilterName.Text,GoodFilterValue.Text);
        }

        private void toolStripMenuItem11_Click(object sender, EventArgs e)
        {
            calculateGoods(true);
        }

        private void ButtPrintCheck_Click(object sender, EventArgs e)
        {
            try
            {
                int order_id = currentOrderID;
                DataTable drivers = dbProc.executeGet("SELECT *, substring(first_name from 1 for 1)||'. '||second_name AS fio FROM users WHERE role_id=3 ORDER BY fio");
                DataTable orders = null;
                string sqlCommand = "SELECT owd.id,owd.phone_1,owd.phone_2,owd.phone_3,owd.phones,owd.good_name,owd.deliver_date,owd.count,owd.address,owd.deliver_time,owd.total_price,owd.goods_price,owd.good_price,owd.deliver_price,owd.client_name,owd.discount_percent FROM orders_with_details owd WHERE owd.id=" + order_id;
                orders = dbProc.executeGet(sqlCommand);

                string seller_requisites = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_requisites'").Rows[0][0];
                string seller_phone = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_phone'").Rows[0][0];
                string seller_fio = (string)dbProc.executeGet("SELECT value FROM settings WHERE name='seller_fio'").Rows[0][0];
                FTwoExcelProcessor excel = new FTwoExcelProcessor();
                excel.InitApplication(true);
                excel.OnExcelClose += new FTwoExcelProcessor.ExcelCloseHandler(proc_OnExcelClose);
                ButtGenerateReceipt.Enabled = false;
                long receiptNumber = (long)dbProc.executeGet("SELECT nextval('receipt_seq')").Rows[0][0];
                int orderID = 0;
                int currentOrderItemIndex = 0;
                int orderIndex = 1;
                for (int i = 0; i < orders.Rows.Count; i++)
                {
                    string deliver_date = ((DateTime)orders.Rows[i]["deliver_date"]).ToShortDateString();
                    string receipt_number = (receiptNumber + orderIndex).ToString() + " от " + deliver_date;
                    string good_name = (string)orders.Rows[i]["good_name"];
                    string count = orders.Rows[i]["count"].ToString();
                    string discount_percent = orders.Rows[i]["discount_percent"].ToString();
                    string good_price = orders.Rows[i]["good_price"].ToString();
                    string goods_price = orders.Rows[i]["goods_price"].ToString();
                    string good_item_price = ((int)orders.Rows[i]["good_price"] * (int)orders.Rows[i]["count"]).ToString();
                    string total_price = orders.Rows[i]["total_price"].ToString();
                    string deliver_price = orders.Rows[i]["deliver_price"].ToString();
                    string buyer = orders.Rows[i]["client_name"].ToString() + ", тел."
                        + orders.Rows[i]["phone_1"].ToString()
                        + ", "
                        + orders.Rows[i]["phone_2"].ToString()
                        + ", "
                        + orders.Rows[i]["phone_3"].ToString()
                        + ", " + orders.Rows[i]["address"].ToString();

                    if (orderID == 0)
                    {
                        orderID = (int)orders.Rows[i]["id"];
                    }
                    currentOrderItemIndex++;

                    excel.text(5, 2, "№");
                    excel.text(5, 3, "Наименование");
                    excel.text(5, 4, "Кол-во");
                    excel.text(5, 5, "Цена");
                    excel.text(5, 6, "Сумма");

                    excel.text(5 + currentOrderItemIndex, 2, currentOrderItemIndex.ToString());
                    excel.text(5 + currentOrderItemIndex, 3, good_name);
                    excel.text(5 + currentOrderItemIndex, 4, count);
                    excel.text(5 + currentOrderItemIndex, 5, good_price);
                    excel.text(5 + currentOrderItemIndex, 6, good_item_price);
                    if (orders.Rows.Count - 1 == i || (int)orders.Rows[i + 1]["id"] != orderID)// End or next order
                    {
                        // Need to create header and footer
                        // Then turn current sheet
                        excel.mergeCells(2, 1, 3, 1);
                        excel.mergeCells(2, 2, 6, 2);
                        excel.mergeCells(2, 3, 3, 3);
                        excel.mergeCells(2, 4, 6, 4);
                        excel.mergeCells(3, currentOrderItemIndex + 6, 5, currentOrderItemIndex + 6);
                        excel.mergeCells(3, currentOrderItemIndex + 7, 5, currentOrderItemIndex + 7);

                        excel.makeFont(1, 2, 1, 4, true, false, false, 12);
                        excel.makeFont(2, 1, 2, 1, true, false, false, 12);
                        excel.makeFont(2, 5, 6, 5, true, false, false, 12);

                        excel.text(6 + currentOrderItemIndex, 3, "Скидка");
                        excel.text(6 + currentOrderItemIndex, 6, discount_percent + "%");
                        excel.text(7 + currentOrderItemIndex, 3, "Услуги по доставке заказа");
                        excel.text(7 + currentOrderItemIndex, 6, deliver_price);


                        excel.text(1, 2, "Товарный чек № " + receipt_number);


                        excel.text(2, 1, "Продавец: ");
                        excel.text(2, 2, seller_requisites);
                        excel.text(3, 2, "тел. " + seller_phone);
                        excel.text(4, 1, "Покупатель: ");
                        excel.text(4, 2, buyer);
                        excel.setHeight(4, 2, 30);
                        excel.setTextAlignment(4, 2, 4, 2, ExcelTextHorizontalAlignment.AlignLeft, ExcelTextVerticalAlignment.AlignJustify);

                        excel.text(currentOrderItemIndex + 8, 2, "Всего отпущено товаров и оказано услуг на сумму: ");
                        excel.text(currentOrderItemIndex + 8, 6, total_price + " руб.");


                        excel.makeFont(2, currentOrderItemIndex + 8, 6, currentOrderItemIndex + 8, true, false, false, 12);

                        excel.text(currentOrderItemIndex + 10, 2, "Отпуск товара разрешил: ");
                        excel.text(currentOrderItemIndex + 10, 5, "");
                        excel.text(currentOrderItemIndex + 10, 6, seller_fio);
                        excel.mergeCells(2, currentOrderItemIndex + 10, 3, currentOrderItemIndex + 10);


                        excel.makeBorder(2, 5, 6, currentOrderItemIndex + 7, Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin, true);
                        excel.setImage(130 + currentOrderItemIndex * 15, 145, 100, 90, Environment.CurrentDirectory + "/Resources/signature.jpg");
                        excel.setWidth(1, 1, 13);
                        excel.setWidth(1, 2, 4);
                        excel.setWidth(1, 3, 32);
                        excel.setWidth(1, 4, 8);
                        excel.setWidth(1, 5, 10.7);
                        excel.setWidth(1, 6, 11.7);
                        currentOrderItemIndex = 0;
                        if (orders.Rows.Count > i + 1)
                        {
                            orderID = (int)orders.Rows[i + 1]["id"];
                        }

                        orderIndex++;
                        excel.renameSheet("№" + receipt_number);
                        if (orders.Rows.Count - 1 != i)
                        {
                            excel.moveToNextSheet();
                        }
                    }
                }
                DirectoryInfo dir = new DirectoryInfo(roadListsDirectory + "/" + MainForm.dbProc.getServerTime().Year.ToString().Replace(".", "_") + "/");
                if (!dir.Exists)
                {
                    dir.Create();
                }
                string month = MainForm.dbProc.getServerTime().ToString("MMMM");
                string fileName = dir.FullName + "Чеки_" + month + "_" + MainForm.dbProc.getServerTime().Day.ToString().Replace(".", "_");
                excel.Save(fileName);
            }
            catch (Exception ex)
            {
            }
        }

        private void ButtPurchaseSelectByDateTo_Click(object sender, EventArgs e)
        {
            loadPurchaseTable(true);
        }

        private void ButtCancelPurchase_Click(object sender, EventArgs e)
        {
            PanelAddPurchase.Visible = false;
        }

        private void ButtAddPurchase_Click(object sender, EventArgs e)
        {
            PanelAddPurchase.Visible = true;
            ComboPurchaseSupplier.DataSource = dbProc.executeGet("SELECT DISTINCT supplier FROM goods WHERE supplier IS NOT NULL AND supplier!=''");
            ComboPurchaseSupplier.DisplayMember = "supplier";
            ComboPurchaseSupplier.ValueMember = "supplier";
            TxtPurchaseName.Text = "Ручная закупка от " + DateTime.Now.ToShortDateString();

        }

        private void ButtCreatePurchase_Click(object sender, EventArgs e)
        {
            List<string> listName = new List<string>();
            listName.Add(TxtPurchaseName.Text);
            List<string> listSupplier = new List<string>();
            listSupplier.Add(ComboPurchaseSupplier.SelectedValue.ToString());
            Purchase.PurchaseEditForm f = new Purchase.PurchaseEditForm(listName, listSupplier, this, 1, DatePickerPurchase.Value);
            f.Activate();
            f.Visible = true;
            PanelAddPurchase.Visible = false;
        }

        private void ButtAdv_Click(object sender, EventArgs e)
        {
            Settings.AdvForm form = new Settings.AdvForm();
            form.Activate();
            form.Visible = true;
        }

        private void ButtExportGoods_Click(object sender, EventArgs e)
        {
            try
            {
                FTwoExcelProcessor excel = new FTwoExcelProcessor();
                excel.InitApplication(true);
                excel.OnExcelClose += new FTwoExcelProcessor.ExcelCloseHandler(excel_OnExcelClose);
                excel.changeOrientation(Microsoft.Office.Interop.Excel.XlPageOrientation.xlLandscape);
                DataTable goods = (DataTable)DataGridViewGoods.DataSource;
                List<string> error_arts = new List<string>();
                for (int i = 0; i<goods.Columns.Count; i++)
                {
                    excel.text(1,i+1, goods.Columns[i].ColumnName);
                }

                for (int i = 0; i < goods.Rows.Count; i++)
                {
                    for (int j = 0; j<goods.Columns.Count; j++)
                    {
                        if (goods.Rows[i][0].ToString() == "246")
                        {
                            string ddd = "";
                        }
                        string value = goods.Rows[i][j].ToString();
                        if (goods.Rows[i][j] is double)
                        {
                            value = value.Replace(",", ".");
                        }
                        try
                        {
                            excel.text(i + 2, j + 1, value);
                        }
                        catch (Exception eex)
                        {
                            error_arts.Add(goods.Rows[i][0].ToString());
                        }
                    }
                }
                string all_errors = "Экспорт завершен. Не удалось экспортировать тоары с артикулами: "+Environment.NewLine;
                for(int i=0; i<error_arts.Count; i++)
                {
                    all_errors+=error_arts[i];
                    if(i/10.0==(int)i/10)
                    {
                        all_errors+=Environment.NewLine;
                    }
                }
                MessageBox.Show(all_errors);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }
        int calcCheckCount = 0;
        private void calculateGoods(bool start)
        {
            try
            {
                string url = "http://" + dbProc.getSetting("zoomag_api_url") + "/User?cmd=calculate_goods&password=zooadminpass&command=";
                if (start)
                {
                    url += "start";
                }
                else
                {
                    url += "check";
                }
                WebRequest webRequest = WebRequest.Create(url);
                WebResponse webResp = webRequest.GetResponse();
                byte[] buff = new byte[webResp.ContentLength];
                Stream stream = webResp.GetResponseStream();
                stream.Read(buff, 0, buff.Length);
                string xml = System.Text.Encoding.UTF8.GetString(buff);

                using (XmlReader reader = XmlReader.Create(new StringReader(xml)))
                {
                    string status = "";
                    string progress = "";
                    while (reader.Read())
                    {
                        XmlNodeType type;
                        type = reader.NodeType;
                        if (type == XmlNodeType.Element)
                        {
                            if (reader.Name == "status")
                            {
                                status = reader.ReadElementContentAsString();
                            }
                            if (reader.Name == "progress")
                            {
                                progress = reader.ReadElementContentAsString() + "%";
                            }
                        }
                    }

                    if (status == "Готово")
                    {
                        progress = "100%";
                        TimerChecker.Interval = 2000;
                        calcCheckCount++;
                        if (calcCheckCount > 3)
                        {
                            LabelProcessName.Text = "";
                            return;
                        }
                    }
                    else
                    {
                        TimerChecker.Interval = 500;
                        calcCheckCount = 0;
                    }
                    LabelProcessName.Text = status + " " + progress;
                }
            }
            catch(Exception ex)
            {
                TxtLog.Text+=ex.ToString();
            }
        }
        private void TimerChecker_Tick(object sender, EventArgs e)
        {
            calculateGoods(false);
        }
    }
}
