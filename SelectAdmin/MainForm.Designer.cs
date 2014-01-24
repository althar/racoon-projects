namespace LowByAdmin
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.TabControlMain = new System.Windows.Forms.TabControl();
            this.TabGoods = new System.Windows.Forms.TabPage();
            this.DataGridGoods = new System.Windows.Forms.DataGridView();
            this.ToolStripGoods = new System.Windows.Forms.ToolStrip();
            this.ButtAddGood = new System.Windows.Forms.ToolStripButton();
            this.ButtEditGood = new System.Windows.Forms.ToolStripButton();
            this.ButtDeleteGood = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator4 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtRefreshGoods = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator6 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator9 = new System.Windows.Forms.ToolStripSeparator();
            this.TabUsers = new System.Windows.Forms.TabPage();
            this.PanelPartners = new System.Windows.Forms.Panel();
            this.ButtClosePartnersPanel = new System.Windows.Forms.Button();
            this.label12 = new System.Windows.Forms.Label();
            this.ButtStartImport = new System.Windows.Forms.Button();
            this.ComboPartners = new System.Windows.Forms.ComboBox();
            this.ToolStripUsers = new System.Windows.Forms.ToolStrip();
            this.ButtAddUser = new System.Windows.Forms.ToolStripButton();
            this.ButtEditUser = new System.Windows.Forms.ToolStripButton();
            this.ButtDeleteUser = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtMakePayment = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator7 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtLoadUsers = new System.Windows.Forms.ToolStripButton();
            this.toolStripLabel9 = new System.Windows.Forms.ToolStripLabel();
            this.TxtUserLogin = new System.Windows.Forms.ToolStripTextBox();
            this.DataGridUsers = new System.Windows.Forms.DataGridView();
            this.TabOrders = new System.Windows.Forms.TabPage();
            this.TimePickerOrdersTo = new System.Windows.Forms.DateTimePicker();
            this.TimePickerOrdersFrom = new System.Windows.Forms.DateTimePicker();
            this.toolStrip2 = new System.Windows.Forms.ToolStrip();
            this.ButtEditOrder = new System.Windows.Forms.ToolStripButton();
            this.ComboOrderStatuses = new System.Windows.Forms.ToolStripComboBox();
            this.toolStripLabel5 = new System.Windows.Forms.ToolStripLabel();
            this.toolStripLabel7 = new System.Windows.Forms.ToolStripLabel();
            this.toolStripLabel6 = new System.Windows.Forms.ToolStripLabel();
            this.toolStripLabel8 = new System.Windows.Forms.ToolStripLabel();
            this.ButtShowOrders = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator8 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtSell12 = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.ComboOrderStatusChange = new System.Windows.Forms.ToolStripComboBox();
            this.DataGridOrders = new System.Windows.Forms.DataGridView();
            this.StatusStripMain = new System.Windows.Forms.StatusStrip();
            this.LabelStatus = new System.Windows.Forms.ToolStripStatusLabel();
            this.toolStripStatusLabel1 = new System.Windows.Forms.ToolStripStatusLabel();
            this.LabelStatusDescription = new System.Windows.Forms.ToolStripStatusLabel();
            this.LabelProcessDescription = new System.Windows.Forms.ToolStripStatusLabel();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.ButtMenuGoods = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtMenuImportGoods = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtMenuUpdatePrices = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtMenuHideGoods = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtArticles = new System.Windows.Forms.ToolStripMenuItem();
            this.DialogOpenImportGoods = new System.Windows.Forms.OpenFileDialog();
            this.TabControlMain.SuspendLayout();
            this.TabGoods.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridGoods)).BeginInit();
            this.ToolStripGoods.SuspendLayout();
            this.TabUsers.SuspendLayout();
            this.PanelPartners.SuspendLayout();
            this.ToolStripUsers.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridUsers)).BeginInit();
            this.TabOrders.SuspendLayout();
            this.toolStrip2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridOrders)).BeginInit();
            this.StatusStripMain.SuspendLayout();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // TabControlMain
            // 
            this.TabControlMain.Controls.Add(this.TabGoods);
            this.TabControlMain.Controls.Add(this.TabUsers);
            this.TabControlMain.Controls.Add(this.TabOrders);
            this.TabControlMain.Location = new System.Drawing.Point(2, 35);
            this.TabControlMain.Name = "TabControlMain";
            this.TabControlMain.SelectedIndex = 0;
            this.TabControlMain.Size = new System.Drawing.Size(902, 454);
            this.TabControlMain.TabIndex = 0;
            this.TabControlMain.SelectedIndexChanged += new System.EventHandler(this.TabControlMain_SelectedIndexChanged);
            // 
            // TabGoods
            // 
            this.TabGoods.Controls.Add(this.DataGridGoods);
            this.TabGoods.Controls.Add(this.ToolStripGoods);
            this.TabGoods.Location = new System.Drawing.Point(4, 22);
            this.TabGoods.Name = "TabGoods";
            this.TabGoods.Padding = new System.Windows.Forms.Padding(3);
            this.TabGoods.Size = new System.Drawing.Size(894, 428);
            this.TabGoods.TabIndex = 1;
            this.TabGoods.Text = "Товары";
            this.TabGoods.UseVisualStyleBackColor = true;
            // 
            // DataGridGoods
            // 
            this.DataGridGoods.AllowUserToAddRows = false;
            this.DataGridGoods.AllowUserToDeleteRows = false;
            this.DataGridGoods.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridGoods.Location = new System.Drawing.Point(6, 31);
            this.DataGridGoods.Name = "DataGridGoods";
            this.DataGridGoods.ReadOnly = true;
            this.DataGridGoods.Size = new System.Drawing.Size(882, 374);
            this.DataGridGoods.TabIndex = 1;
            this.DataGridGoods.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DataGridGoods_CellClick);
            // 
            // ToolStripGoods
            // 
            this.ToolStripGoods.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtAddGood,
            this.ButtEditGood,
            this.ButtDeleteGood,
            this.toolStripSeparator4,
            this.toolStripSeparator3,
            this.ButtRefreshGoods,
            this.toolStripSeparator6,
            this.toolStripSeparator9});
            this.ToolStripGoods.Location = new System.Drawing.Point(3, 3);
            this.ToolStripGoods.Name = "ToolStripGoods";
            this.ToolStripGoods.Size = new System.Drawing.Size(888, 25);
            this.ToolStripGoods.TabIndex = 0;
            this.ToolStripGoods.Text = "toolStrip1";
            // 
            // ButtAddGood
            // 
            this.ButtAddGood.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtAddGood.Image = ((System.Drawing.Image)(resources.GetObject("ButtAddGood.Image")));
            this.ButtAddGood.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtAddGood.Name = "ButtAddGood";
            this.ButtAddGood.Size = new System.Drawing.Size(23, 22);
            this.ButtAddGood.Text = "Добавить товар";
            this.ButtAddGood.Visible = false;
            // 
            // ButtEditGood
            // 
            this.ButtEditGood.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtEditGood.Enabled = false;
            this.ButtEditGood.Image = ((System.Drawing.Image)(resources.GetObject("ButtEditGood.Image")));
            this.ButtEditGood.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtEditGood.Name = "ButtEditGood";
            this.ButtEditGood.Size = new System.Drawing.Size(23, 22);
            this.ButtEditGood.Text = "Изменить товар";
            this.ButtEditGood.Click += new System.EventHandler(this.ButtEditGood_Click);
            // 
            // ButtDeleteGood
            // 
            this.ButtDeleteGood.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtDeleteGood.Enabled = false;
            this.ButtDeleteGood.Image = ((System.Drawing.Image)(resources.GetObject("ButtDeleteGood.Image")));
            this.ButtDeleteGood.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtDeleteGood.Name = "ButtDeleteGood";
            this.ButtDeleteGood.Size = new System.Drawing.Size(23, 22);
            this.ButtDeleteGood.Text = "Удалить товар";
            this.ButtDeleteGood.Visible = false;
            // 
            // toolStripSeparator4
            // 
            this.toolStripSeparator4.Name = "toolStripSeparator4";
            this.toolStripSeparator4.Size = new System.Drawing.Size(6, 25);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 25);
            // 
            // ButtRefreshGoods
            // 
            this.ButtRefreshGoods.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtRefreshGoods.Image = ((System.Drawing.Image)(resources.GetObject("ButtRefreshGoods.Image")));
            this.ButtRefreshGoods.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtRefreshGoods.Name = "ButtRefreshGoods";
            this.ButtRefreshGoods.Size = new System.Drawing.Size(23, 22);
            this.ButtRefreshGoods.Text = "Обновить";
            this.ButtRefreshGoods.Click += new System.EventHandler(this.ButtRefreshGoods_Click);
            // 
            // toolStripSeparator6
            // 
            this.toolStripSeparator6.Name = "toolStripSeparator6";
            this.toolStripSeparator6.Size = new System.Drawing.Size(6, 25);
            // 
            // toolStripSeparator9
            // 
            this.toolStripSeparator9.Name = "toolStripSeparator9";
            this.toolStripSeparator9.Size = new System.Drawing.Size(6, 25);
            // 
            // TabUsers
            // 
            this.TabUsers.Controls.Add(this.PanelPartners);
            this.TabUsers.Controls.Add(this.ToolStripUsers);
            this.TabUsers.Controls.Add(this.DataGridUsers);
            this.TabUsers.Location = new System.Drawing.Point(4, 22);
            this.TabUsers.Name = "TabUsers";
            this.TabUsers.Padding = new System.Windows.Forms.Padding(3);
            this.TabUsers.Size = new System.Drawing.Size(894, 428);
            this.TabUsers.TabIndex = 0;
            this.TabUsers.Text = "Пользователи";
            this.TabUsers.UseVisualStyleBackColor = true;
            // 
            // PanelPartners
            // 
            this.PanelPartners.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelPartners.Controls.Add(this.ButtClosePartnersPanel);
            this.PanelPartners.Controls.Add(this.label12);
            this.PanelPartners.Controls.Add(this.ButtStartImport);
            this.PanelPartners.Controls.Add(this.ComboPartners);
            this.PanelPartners.Location = new System.Drawing.Point(375, 21);
            this.PanelPartners.Name = "PanelPartners";
            this.PanelPartners.Size = new System.Drawing.Size(305, 89);
            this.PanelPartners.TabIndex = 2;
            this.PanelPartners.Visible = false;
            // 
            // ButtClosePartnersPanel
            // 
            this.ButtClosePartnersPanel.Location = new System.Drawing.Point(273, 3);
            this.ButtClosePartnersPanel.Name = "ButtClosePartnersPanel";
            this.ButtClosePartnersPanel.Size = new System.Drawing.Size(27, 23);
            this.ButtClosePartnersPanel.TabIndex = 3;
            this.ButtClosePartnersPanel.Text = "X";
            this.ButtClosePartnersPanel.UseVisualStyleBackColor = true;
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(3, 14);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(187, 13);
            this.label12.TabIndex = 2;
            this.label12.Text = "Выберите или введите поставщика";
            // 
            // ButtStartImport
            // 
            this.ButtStartImport.Location = new System.Drawing.Point(3, 57);
            this.ButtStartImport.Name = "ButtStartImport";
            this.ButtStartImport.Size = new System.Drawing.Size(297, 23);
            this.ButtStartImport.TabIndex = 1;
            this.ButtStartImport.Text = "Начать импорт";
            this.ButtStartImport.UseVisualStyleBackColor = true;
            // 
            // ComboPartners
            // 
            this.ComboPartners.FormattingEnabled = true;
            this.ComboPartners.Location = new System.Drawing.Point(3, 30);
            this.ComboPartners.Name = "ComboPartners";
            this.ComboPartners.Size = new System.Drawing.Size(297, 21);
            this.ComboPartners.TabIndex = 0;
            // 
            // ToolStripUsers
            // 
            this.ToolStripUsers.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtAddUser,
            this.ButtEditUser,
            this.ButtDeleteUser,
            this.toolStripSeparator1,
            this.ButtMakePayment,
            this.toolStripSeparator7,
            this.ButtLoadUsers,
            this.toolStripLabel9,
            this.TxtUserLogin});
            this.ToolStripUsers.Location = new System.Drawing.Point(3, 3);
            this.ToolStripUsers.Name = "ToolStripUsers";
            this.ToolStripUsers.Size = new System.Drawing.Size(888, 25);
            this.ToolStripUsers.TabIndex = 1;
            this.ToolStripUsers.Text = "toolStrip1";
            // 
            // ButtAddUser
            // 
            this.ButtAddUser.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtAddUser.Image = ((System.Drawing.Image)(resources.GetObject("ButtAddUser.Image")));
            this.ButtAddUser.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtAddUser.Name = "ButtAddUser";
            this.ButtAddUser.Size = new System.Drawing.Size(23, 22);
            this.ButtAddUser.Text = "Добавить пользователя";
            // 
            // ButtEditUser
            // 
            this.ButtEditUser.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtEditUser.Enabled = false;
            this.ButtEditUser.Image = ((System.Drawing.Image)(resources.GetObject("ButtEditUser.Image")));
            this.ButtEditUser.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtEditUser.Name = "ButtEditUser";
            this.ButtEditUser.Size = new System.Drawing.Size(23, 22);
            this.ButtEditUser.Text = "Редактировать пользователя";
            // 
            // ButtDeleteUser
            // 
            this.ButtDeleteUser.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtDeleteUser.Enabled = false;
            this.ButtDeleteUser.Image = ((System.Drawing.Image)(resources.GetObject("ButtDeleteUser.Image")));
            this.ButtDeleteUser.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtDeleteUser.Name = "ButtDeleteUser";
            this.ButtDeleteUser.Size = new System.Drawing.Size(23, 22);
            this.ButtDeleteUser.Text = "Удалить пользователя";
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
            // 
            // ButtMakePayment
            // 
            this.ButtMakePayment.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtMakePayment.Image = ((System.Drawing.Image)(resources.GetObject("ButtMakePayment.Image")));
            this.ButtMakePayment.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtMakePayment.Name = "ButtMakePayment";
            this.ButtMakePayment.Size = new System.Drawing.Size(23, 22);
            this.ButtMakePayment.Text = "Сделать платеж";
            // 
            // toolStripSeparator7
            // 
            this.toolStripSeparator7.Name = "toolStripSeparator7";
            this.toolStripSeparator7.Size = new System.Drawing.Size(6, 25);
            // 
            // ButtLoadUsers
            // 
            this.ButtLoadUsers.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtLoadUsers.Name = "ButtLoadUsers";
            this.ButtLoadUsers.Size = new System.Drawing.Size(23, 22);
            this.ButtLoadUsers.Text = "Загрузить пользователей на сайт";
            this.ButtLoadUsers.Visible = false;
            // 
            // toolStripLabel9
            // 
            this.toolStripLabel9.Name = "toolStripLabel9";
            this.toolStripLabel9.Size = new System.Drawing.Size(41, 22);
            this.toolStripLabel9.Text = "Логин";
            // 
            // TxtUserLogin
            // 
            this.TxtUserLogin.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.TxtUserLogin.Name = "TxtUserLogin";
            this.TxtUserLogin.Size = new System.Drawing.Size(100, 25);
            // 
            // DataGridUsers
            // 
            this.DataGridUsers.AllowUserToAddRows = false;
            this.DataGridUsers.AllowUserToDeleteRows = false;
            this.DataGridUsers.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridUsers.Location = new System.Drawing.Point(6, 31);
            this.DataGridUsers.Name = "DataGridUsers";
            this.DataGridUsers.ReadOnly = true;
            this.DataGridUsers.Size = new System.Drawing.Size(882, 374);
            this.DataGridUsers.TabIndex = 0;
            // 
            // TabOrders
            // 
            this.TabOrders.Controls.Add(this.TimePickerOrdersTo);
            this.TabOrders.Controls.Add(this.TimePickerOrdersFrom);
            this.TabOrders.Controls.Add(this.toolStrip2);
            this.TabOrders.Controls.Add(this.DataGridOrders);
            this.TabOrders.Location = new System.Drawing.Point(4, 22);
            this.TabOrders.Name = "TabOrders";
            this.TabOrders.Size = new System.Drawing.Size(894, 428);
            this.TabOrders.TabIndex = 3;
            this.TabOrders.Text = "Заказы";
            this.TabOrders.UseVisualStyleBackColor = true;
            // 
            // TimePickerOrdersTo
            // 
            this.TimePickerOrdersTo.Location = new System.Drawing.Point(374, 3);
            this.TimePickerOrdersTo.Name = "TimePickerOrdersTo";
            this.TimePickerOrdersTo.Size = new System.Drawing.Size(157, 20);
            this.TimePickerOrdersTo.TabIndex = 5;
            // 
            // TimePickerOrdersFrom
            // 
            this.TimePickerOrdersFrom.Location = new System.Drawing.Point(177, 3);
            this.TimePickerOrdersFrom.Name = "TimePickerOrdersFrom";
            this.TimePickerOrdersFrom.Size = new System.Drawing.Size(157, 20);
            this.TimePickerOrdersFrom.TabIndex = 4;
            // 
            // toolStrip2
            // 
            this.toolStrip2.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtEditOrder,
            this.ComboOrderStatuses,
            this.toolStripLabel5,
            this.toolStripLabel7,
            this.toolStripLabel6,
            this.toolStripLabel8,
            this.ButtShowOrders,
            this.toolStripSeparator8,
            this.ButtSell12,
            this.toolStripSeparator2,
            this.ComboOrderStatusChange});
            this.toolStrip2.Location = new System.Drawing.Point(0, 0);
            this.toolStrip2.Name = "toolStrip2";
            this.toolStrip2.Size = new System.Drawing.Size(894, 25);
            this.toolStrip2.TabIndex = 2;
            this.toolStrip2.Text = "toolStrip2";
            // 
            // ButtEditOrder
            // 
            this.ButtEditOrder.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtEditOrder.Enabled = false;
            this.ButtEditOrder.Image = ((System.Drawing.Image)(resources.GetObject("ButtEditOrder.Image")));
            this.ButtEditOrder.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtEditOrder.Name = "ButtEditOrder";
            this.ButtEditOrder.Size = new System.Drawing.Size(23, 22);
            this.ButtEditOrder.Text = "Редактировать заказ";
            // 
            // ComboOrderStatuses
            // 
            this.ComboOrderStatuses.Items.AddRange(new object[] {
            "Новый",
            "Подтвержденный",
            "Выполненный",
            "Удаленный"});
            this.ComboOrderStatuses.Name = "ComboOrderStatuses";
            this.ComboOrderStatuses.Size = new System.Drawing.Size(121, 25);
            this.ComboOrderStatuses.Text = "Новый";
            this.ComboOrderStatuses.SelectedIndexChanged += new System.EventHandler(this.ComboOrderStatuses_SelectedIndexChanged);
            // 
            // toolStripLabel5
            // 
            this.toolStripLabel5.Name = "toolStripLabel5";
            this.toolStripLabel5.Size = new System.Drawing.Size(18, 22);
            this.toolStripLabel5.Text = "С:";
            // 
            // toolStripLabel7
            // 
            this.toolStripLabel7.Name = "toolStripLabel7";
            this.toolStripLabel7.Size = new System.Drawing.Size(175, 22);
            this.toolStripLabel7.Text = "                                                        ";
            // 
            // toolStripLabel6
            // 
            this.toolStripLabel6.Name = "toolStripLabel6";
            this.toolStripLabel6.Size = new System.Drawing.Size(26, 22);
            this.toolStripLabel6.Text = "По:";
            // 
            // toolStripLabel8
            // 
            this.toolStripLabel8.Name = "toolStripLabel8";
            this.toolStripLabel8.Size = new System.Drawing.Size(178, 22);
            this.toolStripLabel8.Text = "                                                         ";
            // 
            // ButtShowOrders
            // 
            this.ButtShowOrders.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtShowOrders.Image = ((System.Drawing.Image)(resources.GetObject("ButtShowOrders.Image")));
            this.ButtShowOrders.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtShowOrders.Name = "ButtShowOrders";
            this.ButtShowOrders.Size = new System.Drawing.Size(23, 22);
            this.ButtShowOrders.Text = "Выбрать";
            this.ButtShowOrders.Click += new System.EventHandler(this.ButtShowOrders_Click);
            // 
            // toolStripSeparator8
            // 
            this.toolStripSeparator8.Name = "toolStripSeparator8";
            this.toolStripSeparator8.Size = new System.Drawing.Size(6, 25);
            // 
            // ButtSell12
            // 
            this.ButtSell12.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.ButtSell12.Enabled = false;
            this.ButtSell12.Image = ((System.Drawing.Image)(resources.GetObject("ButtSell12.Image")));
            this.ButtSell12.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.ButtSell12.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtSell12.Name = "ButtSell12";
            this.ButtSell12.Size = new System.Drawing.Size(54, 22);
            this.ButtSell12.Text = "Торг-12";
            this.ButtSell12.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.ButtSell12.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ButtSell12.Click += new System.EventHandler(this.ButtSell12_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
            // 
            // ComboOrderStatusChange
            // 
            this.ComboOrderStatusChange.Enabled = false;
            this.ComboOrderStatusChange.Items.AddRange(new object[] {
            "Новый",
            "Подтвержденный",
            "Выполненный",
            "Удаленный"});
            this.ComboOrderStatusChange.Name = "ComboOrderStatusChange";
            this.ComboOrderStatusChange.Size = new System.Drawing.Size(121, 25);
            this.ComboOrderStatusChange.SelectedIndexChanged += new System.EventHandler(this.ComboOrderStatusChange_SelectedIndexChanged);
            // 
            // DataGridOrders
            // 
            this.DataGridOrders.AllowUserToAddRows = false;
            this.DataGridOrders.AllowUserToDeleteRows = false;
            this.DataGridOrders.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridOrders.Location = new System.Drawing.Point(6, 31);
            this.DataGridOrders.Name = "DataGridOrders";
            this.DataGridOrders.ReadOnly = true;
            this.DataGridOrders.Size = new System.Drawing.Size(882, 374);
            this.DataGridOrders.TabIndex = 1;
            this.DataGridOrders.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.DataGridOrders_CellMouseClick);
            this.DataGridOrders.CellMouseDoubleClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.DataGridOrders_CellMouseDoubleClick);
            // 
            // StatusStripMain
            // 
            this.StatusStripMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.LabelStatus,
            this.toolStripStatusLabel1,
            this.LabelStatusDescription,
            this.LabelProcessDescription});
            this.StatusStripMain.Location = new System.Drawing.Point(0, 492);
            this.StatusStripMain.Name = "StatusStripMain";
            this.StatusStripMain.Size = new System.Drawing.Size(904, 22);
            this.StatusStripMain.TabIndex = 1;
            this.StatusStripMain.Text = "statusStrip1";
            // 
            // LabelStatus
            // 
            this.LabelStatus.Name = "LabelStatus";
            this.LabelStatus.Size = new System.Drawing.Size(67, 17);
            this.LabelStatus.Text = "Обработка";
            // 
            // toolStripStatusLabel1
            // 
            this.toolStripStatusLabel1.Name = "toolStripStatusLabel1";
            this.toolStripStatusLabel1.Size = new System.Drawing.Size(16, 17);
            this.toolStripStatusLabel1.Text = " : ";
            // 
            // LabelStatusDescription
            // 
            this.LabelStatusDescription.Name = "LabelStatusDescription";
            this.LabelStatusDescription.Size = new System.Drawing.Size(0, 17);
            // 
            // LabelProcessDescription
            // 
            this.LabelProcessDescription.Name = "LabelProcessDescription";
            this.LabelProcessDescription.Size = new System.Drawing.Size(0, 17);
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtMenuGoods,
            this.ButtArticles});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(904, 24);
            this.menuStrip1.TabIndex = 2;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // ButtMenuGoods
            // 
            this.ButtMenuGoods.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtMenuImportGoods,
            this.ButtMenuUpdatePrices,
            this.ButtMenuHideGoods});
            this.ButtMenuGoods.Name = "ButtMenuGoods";
            this.ButtMenuGoods.Size = new System.Drawing.Size(61, 20);
            this.ButtMenuGoods.Text = "Товары";
            // 
            // ButtMenuImportGoods
            // 
            this.ButtMenuImportGoods.Name = "ButtMenuImportGoods";
            this.ButtMenuImportGoods.Size = new System.Drawing.Size(250, 22);
            this.ButtMenuImportGoods.Text = "Импорт ассортимента";
            this.ButtMenuImportGoods.Click += new System.EventHandler(this.ButtMenuImportGoods_Click);
            // 
            // ButtMenuUpdatePrices
            // 
            this.ButtMenuUpdatePrices.Name = "ButtMenuUpdatePrices";
            this.ButtMenuUpdatePrices.Size = new System.Drawing.Size(250, 22);
            this.ButtMenuUpdatePrices.Text = "Обновление цен ассортимента";
            this.ButtMenuUpdatePrices.Click += new System.EventHandler(this.ButtMenuUpdatePrices_Click);
            // 
            // ButtMenuHideGoods
            // 
            this.ButtMenuHideGoods.Name = "ButtMenuHideGoods";
            this.ButtMenuHideGoods.Size = new System.Drawing.Size(250, 22);
            this.ButtMenuHideGoods.Text = "Убрать товары из ассортимента";
            this.ButtMenuHideGoods.Click += new System.EventHandler(this.ButtMenuHideGoods_Click);
            // 
            // ButtArticles
            // 
            this.ButtArticles.Name = "ButtArticles";
            this.ButtArticles.Size = new System.Drawing.Size(56, 20);
            this.ButtArticles.Text = "Статьи";
            this.ButtArticles.Click += new System.EventHandler(this.ButtArticles_Click);
            // 
            // DialogOpenImportGoods
            // 
            this.DialogOpenImportGoods.Filter = "Excel|*.csv";
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(904, 514);
            this.Controls.Add(this.StatusStripMain);
            this.Controls.Add(this.menuStrip1);
            this.Controls.Add(this.TabControlMain);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "MainForm";
            this.Text = "Select Admin";
            this.TabControlMain.ResumeLayout(false);
            this.TabGoods.ResumeLayout(false);
            this.TabGoods.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridGoods)).EndInit();
            this.ToolStripGoods.ResumeLayout(false);
            this.ToolStripGoods.PerformLayout();
            this.TabUsers.ResumeLayout(false);
            this.TabUsers.PerformLayout();
            this.PanelPartners.ResumeLayout(false);
            this.PanelPartners.PerformLayout();
            this.ToolStripUsers.ResumeLayout(false);
            this.ToolStripUsers.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridUsers)).EndInit();
            this.TabOrders.ResumeLayout(false);
            this.TabOrders.PerformLayout();
            this.toolStrip2.ResumeLayout(false);
            this.toolStrip2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridOrders)).EndInit();
            this.StatusStripMain.ResumeLayout(false);
            this.StatusStripMain.PerformLayout();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TabControl TabControlMain;
        private System.Windows.Forms.TabPage TabUsers;
        private System.Windows.Forms.TabPage TabGoods;
        private System.Windows.Forms.StatusStrip StatusStripMain;
        private System.Windows.Forms.ToolStrip ToolStripUsers;
        private System.Windows.Forms.ToolStripButton ButtEditUser;
        private System.Windows.Forms.ToolStripButton ButtDeleteUser;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripButton ButtMakePayment;
        private System.Windows.Forms.DataGridView DataGridUsers;
        private System.Windows.Forms.ToolStripButton ButtAddUser;
        private System.Windows.Forms.TabPage TabOrders;
        private System.Windows.Forms.ToolStrip ToolStripGoods;
        private System.Windows.Forms.ToolStripButton ButtAddGood;
        private System.Windows.Forms.ToolStripButton ButtEditGood;
        private System.Windows.Forms.DataGridView DataGridGoods;
        private System.Windows.Forms.ToolStripButton ButtDeleteGood;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripButton ButtRefreshGoods;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator4;
        private System.Windows.Forms.DataGridView DataGridOrders;
        private System.Windows.Forms.ToolStrip toolStrip2;
        private System.Windows.Forms.ToolStripButton ButtEditOrder;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator6;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator7;
        private System.Windows.Forms.ToolStripButton ButtLoadUsers;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator8;
        private System.Windows.Forms.ToolStripButton ButtSell12;
        private System.Windows.Forms.DateTimePicker TimePickerOrdersTo;
        private System.Windows.Forms.DateTimePicker TimePickerOrdersFrom;
        private System.Windows.Forms.ToolStripLabel toolStripLabel5;
        private System.Windows.Forms.ToolStripLabel toolStripLabel7;
        private System.Windows.Forms.ToolStripLabel toolStripLabel6;
        private System.Windows.Forms.ToolStripLabel toolStripLabel8;
        private System.Windows.Forms.ToolStripButton ButtShowOrders;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator9;
        private System.Windows.Forms.ToolStripTextBox TxtUserLogin;
        private System.Windows.Forms.ToolStripLabel toolStripLabel9;
        private System.Windows.Forms.ToolStripMenuItem ButtMenuGoods;
        private System.Windows.Forms.ToolStripMenuItem ButtMenuImportGoods;
        private System.Windows.Forms.Panel PanelPartners;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Button ButtStartImport;
        private System.Windows.Forms.ComboBox ComboPartners;
        private System.Windows.Forms.Button ButtClosePartnersPanel;
        private System.Windows.Forms.OpenFileDialog DialogOpenImportGoods;
        private System.Windows.Forms.ToolStripStatusLabel LabelStatus;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel1;
        private System.Windows.Forms.ToolStripStatusLabel LabelStatusDescription;
        private System.Windows.Forms.ToolStripStatusLabel LabelProcessDescription;
        private System.Windows.Forms.ToolStripMenuItem ButtArticles;
        private System.Windows.Forms.ToolStripComboBox ComboOrderStatuses;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripComboBox ComboOrderStatusChange;
        private System.Windows.Forms.ToolStripMenuItem ButtMenuUpdatePrices;
        private System.Windows.Forms.ToolStripMenuItem ButtMenuHideGoods;

    }
}