namespace OwlBusinessStudio
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.StatusBarMain = new System.Windows.Forms.StatusStrip();
            this.ProgressBarMain = new System.Windows.Forms.ToolStripProgressBar();
            this.LabelProgress = new System.Windows.Forms.ToolStripStatusLabel();
            this.MainMenu = new System.Windows.Forms.MenuStrip();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtAbout = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtExit = new System.Windows.Forms.ToolStripMenuItem();
            this.ToolStripMenuStatistics = new System.Windows.Forms.ToolStripMenuItem();
            this.ToolStripMenuAdministration = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem4 = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtExportGoods = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtExportOrders = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem3 = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtImportGoods = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem12 = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtDiscounts = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem2 = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtDeliveryPrice = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtUsers = new System.Windows.Forms.ToolStripMenuItem();
            this.MenuButtMetros = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem6 = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem8 = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem9 = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem10 = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtSetBrandOrder = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtSiteContent = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtLoginHistory = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtDBSettings = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtGoodsHistory = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtGenerateYandexXML = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem5 = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem7 = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem11 = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtAdv = new System.Windows.Forms.ToolStripMenuItem();
            this.OpenFileGoods = new System.Windows.Forms.OpenFileDialog();
            this.TabControlMain = new System.Windows.Forms.TabControl();
            this.PageOrders = new System.Windows.Forms.TabPage();
            this.PanelExportOrders = new System.Windows.Forms.Panel();
            this.ButtExportOrdersGo = new System.Windows.Forms.Button();
            this.label20 = new System.Windows.Forms.Label();
            this.label19 = new System.Windows.Forms.Label();
            this.TimePickerDeliverOrderTo = new System.Windows.Forms.DateTimePicker();
            this.TimePickerDeliverOrderFrom = new System.Windows.Forms.DateTimePicker();
            this.TimePickerCreateOrderTo = new System.Windows.Forms.DateTimePicker();
            this.TimePickerCreateOrderFrom = new System.Windows.Forms.DateTimePicker();
            this.CheckDeliverOrderDate = new System.Windows.Forms.CheckBox();
            this.CheckCreateOrderDate = new System.Windows.Forms.CheckBox();
            this.label17 = new System.Windows.Forms.Label();
            this.ComboExportOrdersStatus = new System.Windows.Forms.ComboBox();
            this.toolStrip4 = new System.Windows.Forms.ToolStrip();
            this.toolStripLabel2 = new System.Windows.Forms.ToolStripLabel();
            this.ComboOrderFilterName = new System.Windows.Forms.ToolStripComboBox();
            this.toolStripLabel1 = new System.Windows.Forms.ToolStripLabel();
            this.TxtOrderFilterValue = new System.Windows.Forms.ToolStripTextBox();
            this.toolStripSeparator5 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator10 = new System.Windows.Forms.ToolStripSeparator();
            this.ComboOrderFilterSelfget = new System.Windows.Forms.ToolStripComboBox();
            this.toolStripSeparator11 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtRefresh = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator16 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator17 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtPrevPage = new System.Windows.Forms.ToolStripButton();
            this.LabelCurrentPage = new System.Windows.Forms.ToolStripLabel();
            this.toolStripLabel4 = new System.Windows.Forms.ToolStripLabel();
            this.LabelTotalPages = new System.Windows.Forms.ToolStripLabel();
            this.ButtNextPage = new System.Windows.Forms.ToolStripButton();
            this.toolStripLabel3 = new System.Windows.Forms.ToolStripLabel();
            this.ComboPageSize = new System.Windows.Forms.ToolStripComboBox();
            this.ButtPrintCheck = new System.Windows.Forms.ToolStripButton();
            this.ButtExportOrders = new System.Windows.Forms.ToolStripButton();
            this.ListOrderStatuses = new System.Windows.Forms.ListBox();
            this.ButtSelectOrders = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.DatePickerOrdersTo = new System.Windows.Forms.DateTimePicker();
            this.label2 = new System.Windows.Forms.Label();
            this.DatePickerOrdersFrom = new System.Windows.Forms.DateTimePicker();
            this.RadioOrdersAll = new System.Windows.Forms.RadioButton();
            this.RadioOrdersWeek = new System.Windows.Forms.RadioButton();
            this.RadioOrdersToday = new System.Windows.Forms.RadioButton();
            this.DataGridViewOrders = new System.Windows.Forms.DataGridView();
            this.TabGoods = new System.Windows.Forms.TabPage();
            this.toolStrip5 = new System.Windows.Forms.ToolStrip();
            this.ComboGoodFilterName = new System.Windows.Forms.ToolStripComboBox();
            this.toolStripLabel5 = new System.Windows.Forms.ToolStripLabel();
            this.GoodFilterValue = new System.Windows.Forms.ToolStripTextBox();
            this.RefreshGoodsButt = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator18 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtExportGoods = new System.Windows.Forms.ToolStripButton();
            this.PanelSetQuantity = new System.Windows.Forms.Panel();
            this.button1 = new System.Windows.Forms.Button();
            this.ButtOKQuantity = new System.Windows.Forms.Button();
            this.label12 = new System.Windows.Forms.Label();
            this.NumGoodQuantity = new System.Windows.Forms.NumericUpDown();
            this.PanelSetMinimum = new System.Windows.Forms.Panel();
            this.ButtClosePanelSetMinimum = new System.Windows.Forms.Button();
            this.ButtOKMinumum = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.NumGoodMinimum = new System.Windows.Forms.NumericUpDown();
            this.DataGridViewGoods = new System.Windows.Forms.DataGridView();
            this.contextMenuGoods = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.ButtSetMinimum = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtSetQuantity = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtRefreshGoods = new System.Windows.Forms.ToolStripMenuItem();
            this.TabPurchase = new System.Windows.Forms.TabPage();
            this.PanelAddPurchase = new System.Windows.Forms.Panel();
            this.ButtCancelPurchase = new System.Windows.Forms.Button();
            this.label16 = new System.Windows.Forms.Label();
            this.label15 = new System.Windows.Forms.Label();
            this.ComboPurchaseSupplier = new System.Windows.Forms.ComboBox();
            this.DatePickerPurchase = new System.Windows.Forms.DateTimePicker();
            this.TxtPurchaseName = new System.Windows.Forms.TextBox();
            this.label14 = new System.Windows.Forms.Label();
            this.ButtCreatePurchase = new System.Windows.Forms.Button();
            this.ButtPurchaseSelectByDateTo = new System.Windows.Forms.Button();
            this.CheckAlreadyPurchased = new System.Windows.Forms.CheckBox();
            this.ButtPurchaseSelect = new System.Windows.Forms.Button();
            this.label7 = new System.Windows.Forms.Label();
            this.TimePickerPurchaseTo = new System.Windows.Forms.DateTimePicker();
            this.label8 = new System.Windows.Forms.Label();
            this.TimePickerPurchaseFrom = new System.Windows.Forms.DateTimePicker();
            this.RadioPurchaseForPeriod = new System.Windows.Forms.RadioButton();
            this.RadioPurchaseToday = new System.Windows.Forms.RadioButton();
            this.toolStrip2 = new System.Windows.Forms.ToolStrip();
            this.toolStripButton3 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator7 = new System.Windows.Forms.ToolStripSeparator();
            this.ComboIsForOrders = new System.Windows.Forms.ToolStripComboBox();
            this.ButtAddPurchase = new System.Windows.Forms.ToolStripButton();
            this.ButtChoosePurchaseList = new System.Windows.Forms.ToolStripButton();
            this.DataGridViewPurchase = new System.Windows.Forms.DataGridView();
            this.TabDeliveryLists = new System.Windows.Forms.TabPage();
            this.TxtDayTotal = new System.Windows.Forms.TextBox();
            this.label13 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.TxtTotalForDriver = new System.Windows.Forms.TextBox();
            this.TxtCalculatorComment = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.ButtSaveSummChange = new System.Windows.Forms.Button();
            this.label5 = new System.Windows.Forms.Label();
            this.TxtTotalDescription = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.TxtResultForDriver = new System.Windows.Forms.TextBox();
            this.PanelOpenDeliverListForm = new System.Windows.Forms.Panel();
            this.ButtCancelDeliverListsForm = new System.Windows.Forms.Button();
            this.ButtOpenDeliverListsForm = new System.Windows.Forms.Button();
            this.label10 = new System.Windows.Forms.Label();
            this.TimePickerDeliverListsFormDate = new System.Windows.Forms.DateTimePicker();
            this.TimePickerDeliverListDate = new System.Windows.Forms.DateTimePicker();
            this.label9 = new System.Windows.Forms.Label();
            this.ListDrivers = new System.Windows.Forms.ListBox();
            this.toolStrip3 = new System.Windows.Forms.ToolStrip();
            this.toolStripSeparator8 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator9 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtEditDeliveryLists = new System.Windows.Forms.ToolStripButton();
            this.ButtGenerateDeliverListsFile = new System.Windows.Forms.ToolStripButton();
            this.ButtGenerateReceipts = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator12 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtGenerateReceipt = new System.Windows.Forms.ToolStripButton();
            this.DataGridViewDeliveryLists = new System.Windows.Forms.DataGridView();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.TxtLog = new System.Windows.Forms.TextBox();
            this.ImageListMain = new System.Windows.Forms.ImageList(this.components);
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.MenuButtAuthorization = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.MenuButtAddOrder = new System.Windows.Forms.ToolStripButton();
            this.MenuButtEditOrder = new System.Windows.Forms.ToolStripButton();
            this.MenuButtDeleteOrder = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator13 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator14 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtChangeOrderStatus = new System.Windows.Forms.ToolStripSplitButton();
            this.ButtOrderStatusNew = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtOrderStatusDelay = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtOrderStatusFinished = new System.Windows.Forms.ToolStripMenuItem();
            this.ButtAddGood = new System.Windows.Forms.ToolStripButton();
            this.ButtEditGood = new System.Windows.Forms.ToolStripButton();
            this.ButtDeleteGood = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator4 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtPurchaseForOrdersForm = new System.Windows.Forms.ToolStripButton();
            this.ButtPurchaseForStoreForm = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator6 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripSeparator15 = new System.Windows.Forms.ToolStripSeparator();
            this.ButtProcess = new System.Windows.Forms.ToolStripButton();
            this.LabelProcessName = new System.Windows.Forms.ToolStripLabel();
            this.button2 = new System.Windows.Forms.Button();
            this.TimerDisabler = new System.Windows.Forms.Timer(this.components);
            this.DialogSaveYandexXML = new System.Windows.Forms.SaveFileDialog();
            this.TimerChecker = new System.Windows.Forms.Timer(this.components);
            this.PanelConcatinatePhones = new System.Windows.Forms.Panel();
            this.TxtMainPhone = new System.Windows.Forms.MaskedTextBox();
            this.TxtPhone2 = new System.Windows.Forms.MaskedTextBox();
            this.TxtPhone3 = new System.Windows.Forms.MaskedTextBox();
            this.label18 = new System.Windows.Forms.Label();
            this.label21 = new System.Windows.Forms.Label();
            this.label22 = new System.Windows.Forms.Label();
            this.ButtCancatPhones = new System.Windows.Forms.Button();
            this.toolStripButton1 = new System.Windows.Forms.ToolStripButton();
            this.StatusBarMain.SuspendLayout();
            this.MainMenu.SuspendLayout();
            this.TabControlMain.SuspendLayout();
            this.PageOrders.SuspendLayout();
            this.PanelExportOrders.SuspendLayout();
            this.toolStrip4.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewOrders)).BeginInit();
            this.TabGoods.SuspendLayout();
            this.toolStrip5.SuspendLayout();
            this.PanelSetQuantity.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumGoodQuantity)).BeginInit();
            this.PanelSetMinimum.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumGoodMinimum)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewGoods)).BeginInit();
            this.contextMenuGoods.SuspendLayout();
            this.TabPurchase.SuspendLayout();
            this.PanelAddPurchase.SuspendLayout();
            this.toolStrip2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewPurchase)).BeginInit();
            this.TabDeliveryLists.SuspendLayout();
            this.PanelOpenDeliverListForm.SuspendLayout();
            this.toolStrip3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewDeliveryLists)).BeginInit();
            this.tabPage1.SuspendLayout();
            this.toolStrip1.SuspendLayout();
            this.PanelConcatinatePhones.SuspendLayout();
            this.SuspendLayout();
            // 
            // StatusBarMain
            // 
            this.StatusBarMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ProgressBarMain,
            this.LabelProgress});
            this.StatusBarMain.Location = new System.Drawing.Point(0, 626);
            this.StatusBarMain.Name = "StatusBarMain";
            this.StatusBarMain.Size = new System.Drawing.Size(984, 22);
            this.StatusBarMain.TabIndex = 0;
            this.StatusBarMain.Text = "statusStrip1";
            // 
            // ProgressBarMain
            // 
            this.ProgressBarMain.Name = "ProgressBarMain";
            this.ProgressBarMain.Size = new System.Drawing.Size(160, 16);
            // 
            // LabelProgress
            // 
            this.LabelProgress.Name = "LabelProgress";
            this.LabelProgress.Size = new System.Drawing.Size(0, 17);
            // 
            // MainMenu
            // 
            this.MainMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItem1,
            this.ToolStripMenuStatistics,
            this.ToolStripMenuAdministration});
            this.MainMenu.Location = new System.Drawing.Point(0, 0);
            this.MainMenu.Name = "MainMenu";
            this.MainMenu.Size = new System.Drawing.Size(984, 24);
            this.MainMenu.TabIndex = 2;
            this.MainMenu.Text = "menuStrip1";
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.MenuButtAbout,
            this.MenuButtExit});
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(91, 20);
            this.toolStripMenuItem1.Text = "Приложение";
            // 
            // MenuButtAbout
            // 
            this.MenuButtAbout.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtAbout.Image")));
            this.MenuButtAbout.Name = "MenuButtAbout";
            this.MenuButtAbout.Size = new System.Drawing.Size(149, 22);
            this.MenuButtAbout.Text = "О программе";
            this.MenuButtAbout.Click += new System.EventHandler(this.MenuButtAbout_Click);
            // 
            // MenuButtExit
            // 
            this.MenuButtExit.Name = "MenuButtExit";
            this.MenuButtExit.Size = new System.Drawing.Size(149, 22);
            this.MenuButtExit.Text = "Выход";
            this.MenuButtExit.Click += new System.EventHandler(this.MenuButtExit_Click);
            // 
            // ToolStripMenuStatistics
            // 
            this.ToolStripMenuStatistics.Name = "ToolStripMenuStatistics";
            this.ToolStripMenuStatistics.Size = new System.Drawing.Size(80, 20);
            this.ToolStripMenuStatistics.Text = "Статистика";
            this.ToolStripMenuStatistics.Click += new System.EventHandler(this.toolStripMenuItem7_Click);
            // 
            // ToolStripMenuAdministration
            // 
            this.ToolStripMenuAdministration.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItem4,
            this.toolStripMenuItem3,
            this.MenuButtDiscounts,
            this.toolStripMenuItem2,
            this.MenuButtDeliveryPrice,
            this.MenuButtUsers,
            this.MenuButtMetros,
            this.toolStripMenuItem6,
            this.ButtSiteContent,
            this.ButtLoginHistory,
            this.ButtDBSettings,
            this.ButtGoodsHistory,
            this.ButtGenerateYandexXML,
            this.toolStripMenuItem7,
            this.toolStripMenuItem11,
            this.ButtAdv});
            this.ToolStripMenuAdministration.Name = "ToolStripMenuAdministration";
            this.ToolStripMenuAdministration.Size = new System.Drawing.Size(134, 20);
            this.ToolStripMenuAdministration.Text = "Администрирование";
            // 
            // toolStripMenuItem4
            // 
            this.toolStripMenuItem4.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.MenuButtExportGoods,
            this.MenuButtExportOrders});
            this.toolStripMenuItem4.Image = ((System.Drawing.Image)(resources.GetObject("toolStripMenuItem4.Image")));
            this.toolStripMenuItem4.Name = "toolStripMenuItem4";
            this.toolStripMenuItem4.Size = new System.Drawing.Size(301, 22);
            this.toolStripMenuItem4.Text = "Экспорт";
            this.toolStripMenuItem4.Visible = false;
            // 
            // MenuButtExportGoods
            // 
            this.MenuButtExportGoods.Name = "MenuButtExportGoods";
            this.MenuButtExportGoods.Size = new System.Drawing.Size(147, 22);
            this.MenuButtExportGoods.Text = "Ассортимент";
            // 
            // MenuButtExportOrders
            // 
            this.MenuButtExportOrders.Name = "MenuButtExportOrders";
            this.MenuButtExportOrders.Size = new System.Drawing.Size(147, 22);
            this.MenuButtExportOrders.Text = "Заказы";
            // 
            // toolStripMenuItem3
            // 
            this.toolStripMenuItem3.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.MenuButtImportGoods,
            this.toolStripMenuItem12});
            this.toolStripMenuItem3.Image = ((System.Drawing.Image)(resources.GetObject("toolStripMenuItem3.Image")));
            this.toolStripMenuItem3.Name = "toolStripMenuItem3";
            this.toolStripMenuItem3.Size = new System.Drawing.Size(301, 22);
            this.toolStripMenuItem3.Text = "Импорт";
            // 
            // MenuButtImportGoods
            // 
            this.MenuButtImportGoods.Name = "MenuButtImportGoods";
            this.MenuButtImportGoods.Size = new System.Drawing.Size(178, 22);
            this.MenuButtImportGoods.Tag = "true";
            this.MenuButtImportGoods.Text = "Полная заливка";
            this.MenuButtImportGoods.Click += new System.EventHandler(this.MenuButtImportGoods_Click);
            // 
            // toolStripMenuItem12
            // 
            this.toolStripMenuItem12.Name = "toolStripMenuItem12";
            this.toolStripMenuItem12.Size = new System.Drawing.Size(178, 22);
            this.toolStripMenuItem12.Tag = "false";
            this.toolStripMenuItem12.Text = "Частичная заливка";
            this.toolStripMenuItem12.Click += new System.EventHandler(this.MenuButtImportGoods_Click);
            // 
            // MenuButtDiscounts
            // 
            this.MenuButtDiscounts.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtDiscounts.Image")));
            this.MenuButtDiscounts.Name = "MenuButtDiscounts";
            this.MenuButtDiscounts.Size = new System.Drawing.Size(301, 22);
            this.MenuButtDiscounts.Text = "Скидки";
            this.MenuButtDiscounts.Click += new System.EventHandler(this.MenuButtDiscounts_Click);
            // 
            // toolStripMenuItem2
            // 
            this.toolStripMenuItem2.Image = ((System.Drawing.Image)(resources.GetObject("toolStripMenuItem2.Image")));
            this.toolStripMenuItem2.Name = "toolStripMenuItem2";
            this.toolStripMenuItem2.Size = new System.Drawing.Size(301, 22);
            this.toolStripMenuItem2.Text = "Метро по водителям";
            this.toolStripMenuItem2.Click += new System.EventHandler(this.toolStripMenuItem2_Click_1);
            // 
            // MenuButtDeliveryPrice
            // 
            this.MenuButtDeliveryPrice.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtDeliveryPrice.Image")));
            this.MenuButtDeliveryPrice.Name = "MenuButtDeliveryPrice";
            this.MenuButtDeliveryPrice.Size = new System.Drawing.Size(301, 22);
            this.MenuButtDeliveryPrice.Text = "Цены доставки";
            this.MenuButtDeliveryPrice.Click += new System.EventHandler(this.toolStripMenuItem2_Click);
            // 
            // MenuButtUsers
            // 
            this.MenuButtUsers.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtUsers.Image")));
            this.MenuButtUsers.Name = "MenuButtUsers";
            this.MenuButtUsers.Size = new System.Drawing.Size(301, 22);
            this.MenuButtUsers.Text = "Пользователи";
            this.MenuButtUsers.Click += new System.EventHandler(this.MenuButtUsers_Click);
            // 
            // MenuButtMetros
            // 
            this.MenuButtMetros.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtMetros.Image")));
            this.MenuButtMetros.Name = "MenuButtMetros";
            this.MenuButtMetros.Size = new System.Drawing.Size(301, 22);
            this.MenuButtMetros.Text = "Метро";
            this.MenuButtMetros.Visible = false;
            this.MenuButtMetros.Click += new System.EventHandler(this.MenuButtMetros_Click);
            // 
            // toolStripMenuItem6
            // 
            this.toolStripMenuItem6.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItem8,
            this.toolStripMenuItem9,
            this.toolStripMenuItem10,
            this.ButtSetBrandOrder});
            this.toolStripMenuItem6.Image = ((System.Drawing.Image)(resources.GetObject("toolStripMenuItem6.Image")));
            this.toolStripMenuItem6.Name = "toolStripMenuItem6";
            this.toolStripMenuItem6.Size = new System.Drawing.Size(301, 22);
            this.toolStripMenuItem6.Text = "Настройки";
            // 
            // toolStripMenuItem8
            // 
            this.toolStripMenuItem8.Image = ((System.Drawing.Image)(resources.GetObject("toolStripMenuItem8.Image")));
            this.toolStripMenuItem8.Name = "toolStripMenuItem8";
            this.toolStripMenuItem8.Size = new System.Drawing.Size(208, 22);
            this.toolStripMenuItem8.Text = "EMail для закупок";
            this.toolStripMenuItem8.Click += new System.EventHandler(this.toolStripMenuItem8_Click);
            // 
            // toolStripMenuItem9
            // 
            this.toolStripMenuItem9.Image = ((System.Drawing.Image)(resources.GetObject("toolStripMenuItem9.Image")));
            this.toolStripMenuItem9.Name = "toolStripMenuItem9";
            this.toolStripMenuItem9.Size = new System.Drawing.Size(208, 22);
            this.toolStripMenuItem9.Text = "Закупочная скидка";
            this.toolStripMenuItem9.Click += new System.EventHandler(this.toolStripMenuItem9_Click);
            // 
            // toolStripMenuItem10
            // 
            this.toolStripMenuItem10.Name = "toolStripMenuItem10";
            this.toolStripMenuItem10.Size = new System.Drawing.Size(208, 22);
            this.toolStripMenuItem10.Text = "Настройки сервиса";
            this.toolStripMenuItem10.Click += new System.EventHandler(this.toolStripMenuItem10_Click);
            // 
            // ButtSetBrandOrder
            // 
            this.ButtSetBrandOrder.Name = "ButtSetBrandOrder";
            this.ButtSetBrandOrder.Size = new System.Drawing.Size(208, 22);
            this.ButtSetBrandOrder.Text = "Задать порядок брендов";
            this.ButtSetBrandOrder.Click += new System.EventHandler(this.ButtSetBrandOrder_Click);
            // 
            // ButtSiteContent
            // 
            this.ButtSiteContent.Name = "ButtSiteContent";
            this.ButtSiteContent.Size = new System.Drawing.Size(301, 22);
            this.ButtSiteContent.Text = "Контент сайтов";
            this.ButtSiteContent.Click += new System.EventHandler(this.ButtSiteContent_Click);
            // 
            // ButtLoginHistory
            // 
            this.ButtLoginHistory.Name = "ButtLoginHistory";
            this.ButtLoginHistory.Size = new System.Drawing.Size(301, 22);
            this.ButtLoginHistory.Text = "История заходов";
            this.ButtLoginHistory.Click += new System.EventHandler(this.ButtLoginHistory_Click);
            // 
            // ButtDBSettings
            // 
            this.ButtDBSettings.Image = ((System.Drawing.Image)(resources.GetObject("ButtDBSettings.Image")));
            this.ButtDBSettings.Name = "ButtDBSettings";
            this.ButtDBSettings.Size = new System.Drawing.Size(301, 22);
            this.ButtDBSettings.Text = "База данных";
            this.ButtDBSettings.Click += new System.EventHandler(this.ButtDBSettings_Click);
            // 
            // ButtGoodsHistory
            // 
            this.ButtGoodsHistory.Name = "ButtGoodsHistory";
            this.ButtGoodsHistory.Size = new System.Drawing.Size(301, 22);
            this.ButtGoodsHistory.Text = "История запасов";
            this.ButtGoodsHistory.Click += new System.EventHandler(this.ButtGoodsHistory_Click);
            // 
            // ButtGenerateYandexXML
            // 
            this.ButtGenerateYandexXML.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItem5});
            this.ButtGenerateYandexXML.Name = "ButtGenerateYandexXML";
            this.ButtGenerateYandexXML.Size = new System.Drawing.Size(301, 22);
            this.ButtGenerateYandexXML.Text = "Сформировать файл для Яндекс маркета";
            this.ButtGenerateYandexXML.Click += new System.EventHandler(this.ButtGenerateYandexXML_Click);
            // 
            // toolStripMenuItem5
            // 
            this.toolStripMenuItem5.Name = "toolStripMenuItem5";
            this.toolStripMenuItem5.Size = new System.Drawing.Size(171, 22);
            this.toolStripMenuItem5.Text = "Загрузить на сайт";
            this.toolStripMenuItem5.Click += new System.EventHandler(this.toolStripMenuItem5_Click);
            // 
            // toolStripMenuItem7
            // 
            this.toolStripMenuItem7.Name = "toolStripMenuItem7";
            this.toolStripMenuItem7.Size = new System.Drawing.Size(301, 22);
            this.toolStripMenuItem7.Text = "Преобразовать уникальных клиентов";
            this.toolStripMenuItem7.Click += new System.EventHandler(this.toolStripMenuItem7_Click_1);
            // 
            // toolStripMenuItem11
            // 
            this.toolStripMenuItem11.Name = "toolStripMenuItem11";
            this.toolStripMenuItem11.Size = new System.Drawing.Size(301, 22);
            this.toolStripMenuItem11.Text = "Начать пересчет товаров";
            this.toolStripMenuItem11.Click += new System.EventHandler(this.toolStripMenuItem11_Click);
            // 
            // ButtAdv
            // 
            this.ButtAdv.Name = "ButtAdv";
            this.ButtAdv.Size = new System.Drawing.Size(301, 22);
            this.ButtAdv.Text = "Рекламный блок";
            this.ButtAdv.Click += new System.EventHandler(this.ButtAdv_Click);
            // 
            // OpenFileGoods
            // 
            this.OpenFileGoods.Filter = "excel| *.xls;*.xlsx";
            // 
            // TabControlMain
            // 
            this.TabControlMain.Controls.Add(this.PageOrders);
            this.TabControlMain.Controls.Add(this.TabGoods);
            this.TabControlMain.Controls.Add(this.TabPurchase);
            this.TabControlMain.Controls.Add(this.TabDeliveryLists);
            this.TabControlMain.Controls.Add(this.tabPage1);
            this.TabControlMain.ImageList = this.ImageListMain;
            this.TabControlMain.Location = new System.Drawing.Point(0, 66);
            this.TabControlMain.Name = "TabControlMain";
            this.TabControlMain.SelectedIndex = 0;
            this.TabControlMain.Size = new System.Drawing.Size(984, 557);
            this.TabControlMain.TabIndex = 4;
            this.TabControlMain.SelectedIndexChanged += new System.EventHandler(this.TabControlMain_SelectedIndexChanged);
            // 
            // PageOrders
            // 
            this.PageOrders.Controls.Add(this.PanelConcatinatePhones);
            this.PageOrders.Controls.Add(this.PanelExportOrders);
            this.PageOrders.Controls.Add(this.toolStrip4);
            this.PageOrders.Controls.Add(this.ListOrderStatuses);
            this.PageOrders.Controls.Add(this.ButtSelectOrders);
            this.PageOrders.Controls.Add(this.label3);
            this.PageOrders.Controls.Add(this.DatePickerOrdersTo);
            this.PageOrders.Controls.Add(this.label2);
            this.PageOrders.Controls.Add(this.DatePickerOrdersFrom);
            this.PageOrders.Controls.Add(this.RadioOrdersAll);
            this.PageOrders.Controls.Add(this.RadioOrdersWeek);
            this.PageOrders.Controls.Add(this.RadioOrdersToday);
            this.PageOrders.Controls.Add(this.DataGridViewOrders);
            this.PageOrders.ImageIndex = 0;
            this.PageOrders.Location = new System.Drawing.Point(4, 31);
            this.PageOrders.Name = "PageOrders";
            this.PageOrders.Padding = new System.Windows.Forms.Padding(3);
            this.PageOrders.Size = new System.Drawing.Size(976, 522);
            this.PageOrders.TabIndex = 0;
            this.PageOrders.Text = "Заказы";
            this.PageOrders.UseVisualStyleBackColor = true;
            // 
            // PanelExportOrders
            // 
            this.PanelExportOrders.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelExportOrders.Controls.Add(this.ButtExportOrdersGo);
            this.PanelExportOrders.Controls.Add(this.label20);
            this.PanelExportOrders.Controls.Add(this.label19);
            this.PanelExportOrders.Controls.Add(this.TimePickerDeliverOrderTo);
            this.PanelExportOrders.Controls.Add(this.TimePickerDeliverOrderFrom);
            this.PanelExportOrders.Controls.Add(this.TimePickerCreateOrderTo);
            this.PanelExportOrders.Controls.Add(this.TimePickerCreateOrderFrom);
            this.PanelExportOrders.Controls.Add(this.CheckDeliverOrderDate);
            this.PanelExportOrders.Controls.Add(this.CheckCreateOrderDate);
            this.PanelExportOrders.Controls.Add(this.label17);
            this.PanelExportOrders.Controls.Add(this.ComboExportOrdersStatus);
            this.PanelExportOrders.Location = new System.Drawing.Point(433, 80);
            this.PanelExportOrders.Name = "PanelExportOrders";
            this.PanelExportOrders.Size = new System.Drawing.Size(419, 137);
            this.PanelExportOrders.TabIndex = 11;
            this.PanelExportOrders.Visible = false;
            // 
            // ButtExportOrdersGo
            // 
            this.ButtExportOrdersGo.Location = new System.Drawing.Point(3, 109);
            this.ButtExportOrdersGo.Name = "ButtExportOrdersGo";
            this.ButtExportOrdersGo.Size = new System.Drawing.Size(404, 23);
            this.ButtExportOrdersGo.TabIndex = 11;
            this.ButtExportOrdersGo.Text = "Экспортировать";
            this.ButtExportOrdersGo.UseVisualStyleBackColor = true;
            this.ButtExportOrdersGo.Click += new System.EventHandler(this.ButtExportOrdersGo_Click);
            // 
            // label20
            // 
            this.label20.AutoSize = true;
            this.label20.Location = new System.Drawing.Point(264, 76);
            this.label20.Name = "label20";
            this.label20.Size = new System.Drawing.Size(13, 13);
            this.label20.TabIndex = 10;
            this.label20.Text = "--";
            // 
            // label19
            // 
            this.label19.AutoSize = true;
            this.label19.Location = new System.Drawing.Point(264, 50);
            this.label19.Name = "label19";
            this.label19.Size = new System.Drawing.Size(13, 13);
            this.label19.TabIndex = 9;
            this.label19.Text = "--";
            // 
            // TimePickerDeliverOrderTo
            // 
            this.TimePickerDeliverOrderTo.Location = new System.Drawing.Point(277, 72);
            this.TimePickerDeliverOrderTo.Name = "TimePickerDeliverOrderTo";
            this.TimePickerDeliverOrderTo.Size = new System.Drawing.Size(130, 20);
            this.TimePickerDeliverOrderTo.TabIndex = 7;
            this.TimePickerDeliverOrderTo.Visible = false;
            // 
            // TimePickerDeliverOrderFrom
            // 
            this.TimePickerDeliverOrderFrom.Location = new System.Drawing.Point(130, 72);
            this.TimePickerDeliverOrderFrom.Name = "TimePickerDeliverOrderFrom";
            this.TimePickerDeliverOrderFrom.Size = new System.Drawing.Size(130, 20);
            this.TimePickerDeliverOrderFrom.TabIndex = 6;
            this.TimePickerDeliverOrderFrom.Visible = false;
            // 
            // TimePickerCreateOrderTo
            // 
            this.TimePickerCreateOrderTo.Location = new System.Drawing.Point(277, 46);
            this.TimePickerCreateOrderTo.Name = "TimePickerCreateOrderTo";
            this.TimePickerCreateOrderTo.Size = new System.Drawing.Size(130, 20);
            this.TimePickerCreateOrderTo.TabIndex = 5;
            this.TimePickerCreateOrderTo.Visible = false;
            // 
            // TimePickerCreateOrderFrom
            // 
            this.TimePickerCreateOrderFrom.Location = new System.Drawing.Point(130, 46);
            this.TimePickerCreateOrderFrom.Name = "TimePickerCreateOrderFrom";
            this.TimePickerCreateOrderFrom.Size = new System.Drawing.Size(130, 20);
            this.TimePickerCreateOrderFrom.TabIndex = 4;
            this.TimePickerCreateOrderFrom.Visible = false;
            // 
            // CheckDeliverOrderDate
            // 
            this.CheckDeliverOrderDate.AutoSize = true;
            this.CheckDeliverOrderDate.Location = new System.Drawing.Point(3, 72);
            this.CheckDeliverOrderDate.Name = "CheckDeliverOrderDate";
            this.CheckDeliverOrderDate.Size = new System.Drawing.Size(102, 17);
            this.CheckDeliverOrderDate.TabIndex = 3;
            this.CheckDeliverOrderDate.Text = "Дата доставки";
            this.CheckDeliverOrderDate.UseVisualStyleBackColor = true;
            this.CheckDeliverOrderDate.CheckedChanged += new System.EventHandler(this.CheckDeliverOrderDate_CheckedChanged);
            // 
            // CheckCreateOrderDate
            // 
            this.CheckCreateOrderDate.AutoSize = true;
            this.CheckCreateOrderDate.Location = new System.Drawing.Point(3, 49);
            this.CheckCreateOrderDate.Name = "CheckCreateOrderDate";
            this.CheckCreateOrderDate.Size = new System.Drawing.Size(119, 17);
            this.CheckCreateOrderDate.TabIndex = 2;
            this.CheckCreateOrderDate.Text = "Дата оформления";
            this.CheckCreateOrderDate.UseVisualStyleBackColor = true;
            this.CheckCreateOrderDate.CheckedChanged += new System.EventHandler(this.CheckCreateOrderDate_CheckedChanged);
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Location = new System.Drawing.Point(19, 16);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(41, 13);
            this.label17.TabIndex = 1;
            this.label17.Text = "Статус";
            // 
            // ComboExportOrdersStatus
            // 
            this.ComboExportOrdersStatus.FormattingEnabled = true;
            this.ComboExportOrdersStatus.Items.AddRange(new object[] {
            "Новый",
            "Выполненный",
            "Любой"});
            this.ComboExportOrdersStatus.Location = new System.Drawing.Point(130, 13);
            this.ComboExportOrdersStatus.Name = "ComboExportOrdersStatus";
            this.ComboExportOrdersStatus.Size = new System.Drawing.Size(277, 21);
            this.ComboExportOrdersStatus.TabIndex = 0;
            this.ComboExportOrdersStatus.Text = "Любой";
            // 
            // toolStrip4
            // 
            this.toolStrip4.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripLabel2,
            this.ComboOrderFilterName,
            this.toolStripLabel1,
            this.TxtOrderFilterValue,
            this.toolStripSeparator5,
            this.toolStripSeparator10,
            this.ComboOrderFilterSelfget,
            this.toolStripSeparator11,
            this.ButtRefresh,
            this.toolStripSeparator16,
            this.toolStripSeparator17,
            this.ButtPrevPage,
            this.LabelCurrentPage,
            this.toolStripLabel4,
            this.LabelTotalPages,
            this.ButtNextPage,
            this.toolStripLabel3,
            this.ComboPageSize,
            this.ButtPrintCheck,
            this.ButtExportOrders,
            this.toolStripButton1});
            this.toolStrip4.Location = new System.Drawing.Point(3, 3);
            this.toolStrip4.Name = "toolStrip4";
            this.toolStrip4.Size = new System.Drawing.Size(970, 25);
            this.toolStrip4.TabIndex = 10;
            this.toolStrip4.Text = "toolStrip4";
            // 
            // toolStripLabel2
            // 
            this.toolStripLabel2.Name = "toolStripLabel2";
            this.toolStripLabel2.Size = new System.Drawing.Size(45, 22);
            this.toolStripLabel2.Text = "Поиск:";
            // 
            // ComboOrderFilterName
            // 
            this.ComboOrderFilterName.Items.AddRange(new object[] {
            "Артикул",
            "Телефон",
            "Товар",
            "Клиент",
            "Водитель",
            "Адрес",
            "Номер заказа"});
            this.ComboOrderFilterName.Name = "ComboOrderFilterName";
            this.ComboOrderFilterName.Size = new System.Drawing.Size(121, 25);
            this.ComboOrderFilterName.Text = "Артикул";
            this.ComboOrderFilterName.TextChanged += new System.EventHandler(this.TxtOrderFilterValue_TextChanged);
            // 
            // toolStripLabel1
            // 
            this.toolStripLabel1.Name = "toolStripLabel1";
            this.toolStripLabel1.Size = new System.Drawing.Size(15, 22);
            this.toolStripLabel1.Text = "=";
            // 
            // TxtOrderFilterValue
            // 
            this.TxtOrderFilterValue.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.TxtOrderFilterValue.Name = "TxtOrderFilterValue";
            this.TxtOrderFilterValue.Size = new System.Drawing.Size(100, 25);
            this.TxtOrderFilterValue.KeyDown += new System.Windows.Forms.KeyEventHandler(this.TxtOrderFilterValue_KeyDown);
            this.TxtOrderFilterValue.TextChanged += new System.EventHandler(this.TxtOrderFilterValue_TextChanged);
            // 
            // toolStripSeparator5
            // 
            this.toolStripSeparator5.Name = "toolStripSeparator5";
            this.toolStripSeparator5.Size = new System.Drawing.Size(6, 25);
            // 
            // toolStripSeparator10
            // 
            this.toolStripSeparator10.Name = "toolStripSeparator10";
            this.toolStripSeparator10.Size = new System.Drawing.Size(6, 25);
            // 
            // ComboOrderFilterSelfget
            // 
            this.ComboOrderFilterSelfget.Items.AddRange(new object[] {
            "Все",
            "Самовывоз",
            "Доставка"});
            this.ComboOrderFilterSelfget.Name = "ComboOrderFilterSelfget";
            this.ComboOrderFilterSelfget.Size = new System.Drawing.Size(121, 25);
            this.ComboOrderFilterSelfget.Text = "Все";
            this.ComboOrderFilterSelfget.TextChanged += new System.EventHandler(this.TxtOrderFilterValue_TextChanged);
            // 
            // toolStripSeparator11
            // 
            this.toolStripSeparator11.Name = "toolStripSeparator11";
            this.toolStripSeparator11.Size = new System.Drawing.Size(6, 25);
            // 
            // ButtRefresh
            // 
            this.ButtRefresh.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtRefresh.Image = ((System.Drawing.Image)(resources.GetObject("ButtRefresh.Image")));
            this.ButtRefresh.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtRefresh.Name = "ButtRefresh";
            this.ButtRefresh.Size = new System.Drawing.Size(23, 22);
            this.ButtRefresh.Text = "Обновить";
            this.ButtRefresh.Click += new System.EventHandler(this.ButtRefresh_Click);
            // 
            // toolStripSeparator16
            // 
            this.toolStripSeparator16.Name = "toolStripSeparator16";
            this.toolStripSeparator16.Size = new System.Drawing.Size(6, 25);
            // 
            // toolStripSeparator17
            // 
            this.toolStripSeparator17.Name = "toolStripSeparator17";
            this.toolStripSeparator17.Size = new System.Drawing.Size(6, 25);
            // 
            // ButtPrevPage
            // 
            this.ButtPrevPage.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtPrevPage.Image = ((System.Drawing.Image)(resources.GetObject("ButtPrevPage.Image")));
            this.ButtPrevPage.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtPrevPage.Name = "ButtPrevPage";
            this.ButtPrevPage.Size = new System.Drawing.Size(23, 22);
            this.ButtPrevPage.Text = "toolStripButton1";
            this.ButtPrevPage.Visible = false;
            this.ButtPrevPage.Click += new System.EventHandler(this.ButtPrevPage_Click);
            // 
            // LabelCurrentPage
            // 
            this.LabelCurrentPage.Name = "LabelCurrentPage";
            this.LabelCurrentPage.Size = new System.Drawing.Size(13, 22);
            this.LabelCurrentPage.Text = "1";
            this.LabelCurrentPage.Visible = false;
            // 
            // toolStripLabel4
            // 
            this.toolStripLabel4.Name = "toolStripLabel4";
            this.toolStripLabel4.Size = new System.Drawing.Size(19, 22);
            this.toolStripLabel4.Text = "из";
            this.toolStripLabel4.Visible = false;
            // 
            // LabelTotalPages
            // 
            this.LabelTotalPages.Name = "LabelTotalPages";
            this.LabelTotalPages.Size = new System.Drawing.Size(13, 22);
            this.LabelTotalPages.Text = "1";
            this.LabelTotalPages.Visible = false;
            // 
            // ButtNextPage
            // 
            this.ButtNextPage.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtNextPage.Image = ((System.Drawing.Image)(resources.GetObject("ButtNextPage.Image")));
            this.ButtNextPage.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtNextPage.Name = "ButtNextPage";
            this.ButtNextPage.Size = new System.Drawing.Size(23, 22);
            this.ButtNextPage.Text = "toolStripButton2";
            this.ButtNextPage.Visible = false;
            this.ButtNextPage.Click += new System.EventHandler(this.ButtNextPage_Click);
            // 
            // toolStripLabel3
            // 
            this.toolStripLabel3.Name = "toolStripLabel3";
            this.toolStripLabel3.Size = new System.Drawing.Size(120, 22);
            this.toolStripLabel3.Text = "Заказов на странице";
            this.toolStripLabel3.Visible = false;
            // 
            // ComboPageSize
            // 
            this.ComboPageSize.Items.AddRange(new object[] {
            "100",
            "200",
            "500",
            "1000"});
            this.ComboPageSize.Name = "ComboPageSize";
            this.ComboPageSize.Size = new System.Drawing.Size(121, 25);
            this.ComboPageSize.Text = "100000";
            this.ComboPageSize.Visible = false;
            // 
            // ButtPrintCheck
            // 
            this.ButtPrintCheck.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtPrintCheck.Image = ((System.Drawing.Image)(resources.GetObject("ButtPrintCheck.Image")));
            this.ButtPrintCheck.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtPrintCheck.Name = "ButtPrintCheck";
            this.ButtPrintCheck.Size = new System.Drawing.Size(23, 22);
            this.ButtPrintCheck.Text = "Печатать чек";
            this.ButtPrintCheck.Click += new System.EventHandler(this.ButtPrintCheck_Click);
            // 
            // ButtExportOrders
            // 
            this.ButtExportOrders.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtExportOrders.Image = ((System.Drawing.Image)(resources.GetObject("ButtExportOrders.Image")));
            this.ButtExportOrders.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtExportOrders.Name = "ButtExportOrders";
            this.ButtExportOrders.Size = new System.Drawing.Size(23, 22);
            this.ButtExportOrders.Text = "Экспорт заказов";
            this.ButtExportOrders.Click += new System.EventHandler(this.ButtExportOrders_Click);
            // 
            // ListOrderStatuses
            // 
            this.ListOrderStatuses.FormattingEnabled = true;
            this.ListOrderStatuses.Items.AddRange(new object[] {
            "Новые",
            "Отложенные",
            "Выполненные",
            "Удаленные"});
            this.ListOrderStatuses.Location = new System.Drawing.Point(8, 35);
            this.ListOrderStatuses.Name = "ListOrderStatuses";
            this.ListOrderStatuses.Size = new System.Drawing.Size(142, 56);
            this.ListOrderStatuses.TabIndex = 9;
            this.ListOrderStatuses.SelectedIndexChanged += new System.EventHandler(this.ListOrderStatuses_SelectedIndexChanged);
            // 
            // ButtSelectOrders
            // 
            this.ButtSelectOrders.Location = new System.Drawing.Point(11, 227);
            this.ButtSelectOrders.Name = "ButtSelectOrders";
            this.ButtSelectOrders.Size = new System.Drawing.Size(142, 23);
            this.ButtSelectOrders.TabIndex = 8;
            this.ButtSelectOrders.Text = "Выбрать";
            this.ButtSelectOrders.UseVisualStyleBackColor = true;
            this.ButtSelectOrders.Click += new System.EventHandler(this.ButtSelectOrders_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(8, 204);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(21, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "По";
            // 
            // DatePickerOrdersTo
            // 
            this.DatePickerOrdersTo.Location = new System.Drawing.Point(35, 201);
            this.DatePickerOrdersTo.Name = "DatePickerOrdersTo";
            this.DatePickerOrdersTo.Size = new System.Drawing.Size(118, 20);
            this.DatePickerOrdersTo.TabIndex = 6;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(8, 178);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(14, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "С";
            // 
            // DatePickerOrdersFrom
            // 
            this.DatePickerOrdersFrom.Location = new System.Drawing.Point(35, 175);
            this.DatePickerOrdersFrom.Name = "DatePickerOrdersFrom";
            this.DatePickerOrdersFrom.Size = new System.Drawing.Size(118, 20);
            this.DatePickerOrdersFrom.TabIndex = 4;
            // 
            // RadioOrdersAll
            // 
            this.RadioOrdersAll.AutoSize = true;
            this.RadioOrdersAll.Location = new System.Drawing.Point(8, 143);
            this.RadioOrdersAll.Name = "RadioOrdersAll";
            this.RadioOrdersAll.Size = new System.Drawing.Size(77, 17);
            this.RadioOrdersAll.TabIndex = 3;
            this.RadioOrdersAll.Text = "За период";
            this.RadioOrdersAll.UseVisualStyleBackColor = true;
            // 
            // RadioOrdersWeek
            // 
            this.RadioOrdersWeek.AutoSize = true;
            this.RadioOrdersWeek.Location = new System.Drawing.Point(8, 120);
            this.RadioOrdersWeek.Name = "RadioOrdersWeek";
            this.RadioOrdersWeek.Size = new System.Drawing.Size(85, 17);
            this.RadioOrdersWeek.TabIndex = 2;
            this.RadioOrdersWeek.Text = "На будущее";
            this.RadioOrdersWeek.UseVisualStyleBackColor = true;
            // 
            // RadioOrdersToday
            // 
            this.RadioOrdersToday.AutoSize = true;
            this.RadioOrdersToday.Checked = true;
            this.RadioOrdersToday.Location = new System.Drawing.Point(8, 97);
            this.RadioOrdersToday.Name = "RadioOrdersToday";
            this.RadioOrdersToday.Size = new System.Drawing.Size(83, 17);
            this.RadioOrdersToday.TabIndex = 1;
            this.RadioOrdersToday.TabStop = true;
            this.RadioOrdersToday.Text = "На сегодня";
            this.RadioOrdersToday.UseVisualStyleBackColor = true;
            // 
            // DataGridViewOrders
            // 
            this.DataGridViewOrders.AllowUserToAddRows = false;
            this.DataGridViewOrders.AllowUserToDeleteRows = false;
            this.DataGridViewOrders.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewOrders.Location = new System.Drawing.Point(159, 35);
            this.DataGridViewOrders.Name = "DataGridViewOrders";
            this.DataGridViewOrders.ReadOnly = true;
            this.DataGridViewOrders.Size = new System.Drawing.Size(805, 259);
            this.DataGridViewOrders.TabIndex = 0;
            this.DataGridViewOrders.VirtualMode = true;
            this.DataGridViewOrders.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewOrders_CellStateChanged);
            this.DataGridViewOrders.RowStateChanged += new System.Windows.Forms.DataGridViewRowStateChangedEventHandler(this.DataGridViewOrders_RowStateChanged);
            // 
            // TabGoods
            // 
            this.TabGoods.Controls.Add(this.toolStrip5);
            this.TabGoods.Controls.Add(this.PanelSetQuantity);
            this.TabGoods.Controls.Add(this.PanelSetMinimum);
            this.TabGoods.Controls.Add(this.DataGridViewGoods);
            this.TabGoods.ImageIndex = 1;
            this.TabGoods.Location = new System.Drawing.Point(4, 31);
            this.TabGoods.Name = "TabGoods";
            this.TabGoods.Padding = new System.Windows.Forms.Padding(3);
            this.TabGoods.Size = new System.Drawing.Size(976, 522);
            this.TabGoods.TabIndex = 1;
            this.TabGoods.Text = "Товары";
            this.TabGoods.UseVisualStyleBackColor = true;
            // 
            // toolStrip5
            // 
            this.toolStrip5.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ComboGoodFilterName,
            this.toolStripLabel5,
            this.GoodFilterValue,
            this.RefreshGoodsButt,
            this.toolStripSeparator18,
            this.ButtExportGoods});
            this.toolStrip5.Location = new System.Drawing.Point(3, 3);
            this.toolStrip5.Name = "toolStrip5";
            this.toolStrip5.Size = new System.Drawing.Size(970, 25);
            this.toolStrip5.TabIndex = 4;
            this.toolStrip5.Text = "toolStrip5";
            // 
            // ComboGoodFilterName
            // 
            this.ComboGoodFilterName.Items.AddRange(new object[] {
            "articul"});
            this.ComboGoodFilterName.Name = "ComboGoodFilterName";
            this.ComboGoodFilterName.Size = new System.Drawing.Size(121, 25);
            this.ComboGoodFilterName.Text = "articul";
            // 
            // toolStripLabel5
            // 
            this.toolStripLabel5.Name = "toolStripLabel5";
            this.toolStripLabel5.Size = new System.Drawing.Size(15, 22);
            this.toolStripLabel5.Text = "=";
            // 
            // GoodFilterValue
            // 
            this.GoodFilterValue.Name = "GoodFilterValue";
            this.GoodFilterValue.Size = new System.Drawing.Size(100, 25);
            // 
            // RefreshGoodsButt
            // 
            this.RefreshGoodsButt.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.RefreshGoodsButt.Image = ((System.Drawing.Image)(resources.GetObject("RefreshGoodsButt.Image")));
            this.RefreshGoodsButt.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.RefreshGoodsButt.Name = "RefreshGoodsButt";
            this.RefreshGoodsButt.Size = new System.Drawing.Size(23, 22);
            this.RefreshGoodsButt.Text = "Обновить";
            this.RefreshGoodsButt.Click += new System.EventHandler(this.RefreshGoodsButt_Click);
            // 
            // toolStripSeparator18
            // 
            this.toolStripSeparator18.Name = "toolStripSeparator18";
            this.toolStripSeparator18.Size = new System.Drawing.Size(6, 25);
            // 
            // ButtExportGoods
            // 
            this.ButtExportGoods.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtExportGoods.Image = ((System.Drawing.Image)(resources.GetObject("ButtExportGoods.Image")));
            this.ButtExportGoods.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtExportGoods.Name = "ButtExportGoods";
            this.ButtExportGoods.Size = new System.Drawing.Size(23, 22);
            this.ButtExportGoods.Text = "Выгрузить товары в Excel";
            this.ButtExportGoods.Click += new System.EventHandler(this.ButtExportGoods_Click);
            // 
            // PanelSetQuantity
            // 
            this.PanelSetQuantity.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelSetQuantity.Controls.Add(this.button1);
            this.PanelSetQuantity.Controls.Add(this.ButtOKQuantity);
            this.PanelSetQuantity.Controls.Add(this.label12);
            this.PanelSetQuantity.Controls.Add(this.NumGoodQuantity);
            this.PanelSetQuantity.Location = new System.Drawing.Point(246, 40);
            this.PanelSetQuantity.Name = "PanelSetQuantity";
            this.PanelSetQuantity.Size = new System.Drawing.Size(180, 85);
            this.PanelSetQuantity.TabIndex = 3;
            this.PanelSetQuantity.Visible = false;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(120, 53);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(55, 21);
            this.button1.TabIndex = 5;
            this.button1.Text = "Отмена";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click_2);
            // 
            // ButtOKQuantity
            // 
            this.ButtOKQuantity.Location = new System.Drawing.Point(6, 53);
            this.ButtOKQuantity.Name = "ButtOKQuantity";
            this.ButtOKQuantity.Size = new System.Drawing.Size(108, 21);
            this.ButtOKQuantity.TabIndex = 2;
            this.ButtOKQuantity.Text = "Сохранить";
            this.ButtOKQuantity.UseVisualStyleBackColor = true;
            this.ButtOKQuantity.Click += new System.EventHandler(this.ButtOKQuantity_Click);
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(3, 11);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(120, 13);
            this.label12.TabIndex = 1;
            this.label12.Text = "Количество на складе";
            // 
            // NumGoodQuantity
            // 
            this.NumGoodQuantity.Location = new System.Drawing.Point(6, 27);
            this.NumGoodQuantity.Maximum = new decimal(new int[] {
            1410065407,
            2,
            0,
            0});
            this.NumGoodQuantity.Minimum = new decimal(new int[] {
            1410065407,
            2,
            0,
            -2147483648});
            this.NumGoodQuantity.Name = "NumGoodQuantity";
            this.NumGoodQuantity.Size = new System.Drawing.Size(169, 20);
            this.NumGoodQuantity.TabIndex = 0;
            // 
            // PanelSetMinimum
            // 
            this.PanelSetMinimum.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelSetMinimum.Controls.Add(this.ButtClosePanelSetMinimum);
            this.PanelSetMinimum.Controls.Add(this.ButtOKMinumum);
            this.PanelSetMinimum.Controls.Add(this.label1);
            this.PanelSetMinimum.Controls.Add(this.NumGoodMinimum);
            this.PanelSetMinimum.Location = new System.Drawing.Point(60, 28);
            this.PanelSetMinimum.Name = "PanelSetMinimum";
            this.PanelSetMinimum.Size = new System.Drawing.Size(180, 85);
            this.PanelSetMinimum.TabIndex = 1;
            this.PanelSetMinimum.Visible = false;
            // 
            // ButtClosePanelSetMinimum
            // 
            this.ButtClosePanelSetMinimum.Location = new System.Drawing.Point(120, 53);
            this.ButtClosePanelSetMinimum.Name = "ButtClosePanelSetMinimum";
            this.ButtClosePanelSetMinimum.Size = new System.Drawing.Size(55, 21);
            this.ButtClosePanelSetMinimum.TabIndex = 4;
            this.ButtClosePanelSetMinimum.Text = "Отмена";
            this.ButtClosePanelSetMinimum.UseVisualStyleBackColor = true;
            this.ButtClosePanelSetMinimum.Click += new System.EventHandler(this.ButtClosePanelSetMinimum_Click);
            // 
            // ButtOKMinumum
            // 
            this.ButtOKMinumum.Location = new System.Drawing.Point(6, 53);
            this.ButtOKMinumum.Name = "ButtOKMinumum";
            this.ButtOKMinumum.Size = new System.Drawing.Size(108, 21);
            this.ButtOKMinumum.TabIndex = 2;
            this.ButtOKMinumum.Text = "Сохранить";
            this.ButtOKMinumum.UseVisualStyleBackColor = true;
            this.ButtOKMinumum.Click += new System.EventHandler(this.ButtOkMinimumClick);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(3, 11);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(113, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Минимальный запаз";
            // 
            // NumGoodMinimum
            // 
            this.NumGoodMinimum.Location = new System.Drawing.Point(6, 27);
            this.NumGoodMinimum.Maximum = new decimal(new int[] {
            1410065407,
            2,
            0,
            0});
            this.NumGoodMinimum.Minimum = new decimal(new int[] {
            1410065407,
            2,
            0,
            -2147483648});
            this.NumGoodMinimum.Name = "NumGoodMinimum";
            this.NumGoodMinimum.Size = new System.Drawing.Size(169, 20);
            this.NumGoodMinimum.TabIndex = 0;
            // 
            // DataGridViewGoods
            // 
            this.DataGridViewGoods.AllowUserToAddRows = false;
            this.DataGridViewGoods.AllowUserToDeleteRows = false;
            this.DataGridViewGoods.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewGoods.ContextMenuStrip = this.contextMenuGoods;
            this.DataGridViewGoods.Location = new System.Drawing.Point(6, 31);
            this.DataGridViewGoods.Name = "DataGridViewGoods";
            this.DataGridViewGoods.ReadOnly = true;
            this.DataGridViewGoods.Size = new System.Drawing.Size(962, 285);
            this.DataGridViewGoods.TabIndex = 0;
            this.DataGridViewGoods.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewGoods_CellStateChanged);
            // 
            // contextMenuGoods
            // 
            this.contextMenuGoods.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtSetMinimum,
            this.ButtSetQuantity,
            this.ButtRefreshGoods});
            this.contextMenuGoods.Name = "contextMenuGoods";
            this.contextMenuGoods.Size = new System.Drawing.Size(228, 70);
            this.contextMenuGoods.Opening += new System.ComponentModel.CancelEventHandler(this.contextMenuGoods_Opening);
            // 
            // ButtSetMinimum
            // 
            this.ButtSetMinimum.Name = "ButtSetMinimum";
            this.ButtSetMinimum.Size = new System.Drawing.Size(227, 22);
            this.ButtSetMinimum.Text = "Задать минимальный запас";
            this.ButtSetMinimum.Click += new System.EventHandler(this.toolStripMenuItem6_Click);
            // 
            // ButtSetQuantity
            // 
            this.ButtSetQuantity.Name = "ButtSetQuantity";
            this.ButtSetQuantity.Size = new System.Drawing.Size(227, 22);
            this.ButtSetQuantity.Text = "Задать количество";
            this.ButtSetQuantity.Click += new System.EventHandler(this.ButtNullMinimum_Click);
            // 
            // ButtRefreshGoods
            // 
            this.ButtRefreshGoods.Name = "ButtRefreshGoods";
            this.ButtRefreshGoods.Size = new System.Drawing.Size(227, 22);
            this.ButtRefreshGoods.Text = "Обновить";
            this.ButtRefreshGoods.Click += new System.EventHandler(this.ButtRefreshGoods_Click);
            // 
            // TabPurchase
            // 
            this.TabPurchase.Controls.Add(this.PanelAddPurchase);
            this.TabPurchase.Controls.Add(this.ButtPurchaseSelectByDateTo);
            this.TabPurchase.Controls.Add(this.CheckAlreadyPurchased);
            this.TabPurchase.Controls.Add(this.ButtPurchaseSelect);
            this.TabPurchase.Controls.Add(this.label7);
            this.TabPurchase.Controls.Add(this.TimePickerPurchaseTo);
            this.TabPurchase.Controls.Add(this.label8);
            this.TabPurchase.Controls.Add(this.TimePickerPurchaseFrom);
            this.TabPurchase.Controls.Add(this.RadioPurchaseForPeriod);
            this.TabPurchase.Controls.Add(this.RadioPurchaseToday);
            this.TabPurchase.Controls.Add(this.toolStrip2);
            this.TabPurchase.Controls.Add(this.DataGridViewPurchase);
            this.TabPurchase.ImageIndex = 3;
            this.TabPurchase.Location = new System.Drawing.Point(4, 31);
            this.TabPurchase.Name = "TabPurchase";
            this.TabPurchase.Size = new System.Drawing.Size(976, 522);
            this.TabPurchase.TabIndex = 2;
            this.TabPurchase.Text = "Закупки";
            this.TabPurchase.UseVisualStyleBackColor = true;
            // 
            // PanelAddPurchase
            // 
            this.PanelAddPurchase.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelAddPurchase.Controls.Add(this.ButtCancelPurchase);
            this.PanelAddPurchase.Controls.Add(this.label16);
            this.PanelAddPurchase.Controls.Add(this.label15);
            this.PanelAddPurchase.Controls.Add(this.ComboPurchaseSupplier);
            this.PanelAddPurchase.Controls.Add(this.DatePickerPurchase);
            this.PanelAddPurchase.Controls.Add(this.TxtPurchaseName);
            this.PanelAddPurchase.Controls.Add(this.label14);
            this.PanelAddPurchase.Controls.Add(this.ButtCreatePurchase);
            this.PanelAddPurchase.Location = new System.Drawing.Point(218, 45);
            this.PanelAddPurchase.Name = "PanelAddPurchase";
            this.PanelAddPurchase.Size = new System.Drawing.Size(536, 137);
            this.PanelAddPurchase.TabIndex = 19;
            this.PanelAddPurchase.Visible = false;
            // 
            // ButtCancelPurchase
            // 
            this.ButtCancelPurchase.Location = new System.Drawing.Point(299, 109);
            this.ButtCancelPurchase.Name = "ButtCancelPurchase";
            this.ButtCancelPurchase.Size = new System.Drawing.Size(222, 23);
            this.ButtCancelPurchase.TabIndex = 7;
            this.ButtCancelPurchase.Text = "Отмена";
            this.ButtCancelPurchase.UseVisualStyleBackColor = true;
            this.ButtCancelPurchase.Click += new System.EventHandler(this.ButtCancelPurchase_Click);
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Location = new System.Drawing.Point(11, 74);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(33, 13);
            this.label16.TabIndex = 6;
            this.label16.Text = "Дата";
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Location = new System.Drawing.Point(11, 44);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(65, 13);
            this.label15.TabIndex = 5;
            this.label15.Text = "Поставщик";
            // 
            // ComboPurchaseSupplier
            // 
            this.ComboPurchaseSupplier.FormattingEnabled = true;
            this.ComboPurchaseSupplier.Location = new System.Drawing.Point(110, 41);
            this.ComboPurchaseSupplier.Name = "ComboPurchaseSupplier";
            this.ComboPurchaseSupplier.Size = new System.Drawing.Size(411, 21);
            this.ComboPurchaseSupplier.TabIndex = 4;
            // 
            // DatePickerPurchase
            // 
            this.DatePickerPurchase.Location = new System.Drawing.Point(110, 68);
            this.DatePickerPurchase.Name = "DatePickerPurchase";
            this.DatePickerPurchase.Size = new System.Drawing.Size(411, 20);
            this.DatePickerPurchase.TabIndex = 3;
            // 
            // TxtPurchaseName
            // 
            this.TxtPurchaseName.Location = new System.Drawing.Point(110, 13);
            this.TxtPurchaseName.Name = "TxtPurchaseName";
            this.TxtPurchaseName.Size = new System.Drawing.Size(411, 20);
            this.TxtPurchaseName.TabIndex = 2;
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Location = new System.Drawing.Point(11, 16);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(101, 13);
            this.label14.TabIndex = 1;
            this.label14.Text = "Название закупки";
            // 
            // ButtCreatePurchase
            // 
            this.ButtCreatePurchase.Location = new System.Drawing.Point(14, 109);
            this.ButtCreatePurchase.Name = "ButtCreatePurchase";
            this.ButtCreatePurchase.Size = new System.Drawing.Size(279, 23);
            this.ButtCreatePurchase.TabIndex = 0;
            this.ButtCreatePurchase.Text = "Создать";
            this.ButtCreatePurchase.UseVisualStyleBackColor = true;
            this.ButtCreatePurchase.Click += new System.EventHandler(this.ButtCreatePurchase_Click);
            // 
            // ButtPurchaseSelectByDateTo
            // 
            this.ButtPurchaseSelectByDateTo.Enabled = false;
            this.ButtPurchaseSelectByDateTo.Location = new System.Drawing.Point(11, 199);
            this.ButtPurchaseSelectByDateTo.Name = "ButtPurchaseSelectByDateTo";
            this.ButtPurchaseSelectByDateTo.Size = new System.Drawing.Size(142, 40);
            this.ButtPurchaseSelectByDateTo.TabIndex = 18;
            this.ButtPurchaseSelectByDateTo.Text = "Выбрать по дате прихода на склад";
            this.ButtPurchaseSelectByDateTo.UseVisualStyleBackColor = true;
            this.ButtPurchaseSelectByDateTo.Click += new System.EventHandler(this.ButtPurchaseSelectByDateTo_Click);
            // 
            // CheckAlreadyPurchased
            // 
            this.CheckAlreadyPurchased.AutoSize = true;
            this.CheckAlreadyPurchased.Location = new System.Drawing.Point(197, 5);
            this.CheckAlreadyPurchased.Name = "CheckAlreadyPurchased";
            this.CheckAlreadyPurchased.Size = new System.Drawing.Size(118, 17);
            this.CheckAlreadyPurchased.TabIndex = 17;
            this.CheckAlreadyPurchased.Text = "Уже закупленные";
            this.CheckAlreadyPurchased.UseVisualStyleBackColor = true;
            this.CheckAlreadyPurchased.CheckedChanged += new System.EventHandler(this.CheckAlreadyPurchased_CheckedChanged);
            // 
            // ButtPurchaseSelect
            // 
            this.ButtPurchaseSelect.Enabled = false;
            this.ButtPurchaseSelect.Location = new System.Drawing.Point(11, 155);
            this.ButtPurchaseSelect.Name = "ButtPurchaseSelect";
            this.ButtPurchaseSelect.Size = new System.Drawing.Size(142, 38);
            this.ButtPurchaseSelect.TabIndex = 16;
            this.ButtPurchaseSelect.Text = "Выбрать по дате формирования";
            this.ButtPurchaseSelect.UseVisualStyleBackColor = true;
            this.ButtPurchaseSelect.Click += new System.EventHandler(this.ButtPurchaseSelect_Click);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(8, 132);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(21, 13);
            this.label7.TabIndex = 15;
            this.label7.Text = "По";
            // 
            // TimePickerPurchaseTo
            // 
            this.TimePickerPurchaseTo.Enabled = false;
            this.TimePickerPurchaseTo.Location = new System.Drawing.Point(35, 129);
            this.TimePickerPurchaseTo.Name = "TimePickerPurchaseTo";
            this.TimePickerPurchaseTo.Size = new System.Drawing.Size(118, 20);
            this.TimePickerPurchaseTo.TabIndex = 14;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(8, 106);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(14, 13);
            this.label8.TabIndex = 13;
            this.label8.Text = "С";
            // 
            // TimePickerPurchaseFrom
            // 
            this.TimePickerPurchaseFrom.Enabled = false;
            this.TimePickerPurchaseFrom.Location = new System.Drawing.Point(35, 103);
            this.TimePickerPurchaseFrom.Name = "TimePickerPurchaseFrom";
            this.TimePickerPurchaseFrom.Size = new System.Drawing.Size(118, 20);
            this.TimePickerPurchaseFrom.TabIndex = 12;
            // 
            // RadioPurchaseForPeriod
            // 
            this.RadioPurchaseForPeriod.AutoSize = true;
            this.RadioPurchaseForPeriod.Location = new System.Drawing.Point(8, 71);
            this.RadioPurchaseForPeriod.Name = "RadioPurchaseForPeriod";
            this.RadioPurchaseForPeriod.Size = new System.Drawing.Size(77, 17);
            this.RadioPurchaseForPeriod.TabIndex = 11;
            this.RadioPurchaseForPeriod.Text = "За период";
            this.RadioPurchaseForPeriod.UseVisualStyleBackColor = true;
            this.RadioPurchaseForPeriod.CheckedChanged += new System.EventHandler(this.RadioPurchaseForPeriod_CheckedChanged);
            // 
            // RadioPurchaseToday
            // 
            this.RadioPurchaseToday.AutoSize = true;
            this.RadioPurchaseToday.Checked = true;
            this.RadioPurchaseToday.Location = new System.Drawing.Point(8, 48);
            this.RadioPurchaseToday.Name = "RadioPurchaseToday";
            this.RadioPurchaseToday.Size = new System.Drawing.Size(83, 17);
            this.RadioPurchaseToday.TabIndex = 9;
            this.RadioPurchaseToday.TabStop = true;
            this.RadioPurchaseToday.Text = "На сегодня";
            this.RadioPurchaseToday.UseVisualStyleBackColor = true;
            // 
            // toolStrip2
            // 
            this.toolStrip2.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripButton3,
            this.toolStripSeparator7,
            this.ComboIsForOrders,
            this.ButtAddPurchase,
            this.ButtChoosePurchaseList});
            this.toolStrip2.Location = new System.Drawing.Point(0, 0);
            this.toolStrip2.Name = "toolStrip2";
            this.toolStrip2.Size = new System.Drawing.Size(976, 25);
            this.toolStrip2.TabIndex = 2;
            this.toolStrip2.Text = "toolStrip2";
            // 
            // toolStripButton3
            // 
            this.toolStripButton3.Name = "toolStripButton3";
            this.toolStripButton3.Size = new System.Drawing.Size(6, 25);
            // 
            // toolStripSeparator7
            // 
            this.toolStripSeparator7.Name = "toolStripSeparator7";
            this.toolStripSeparator7.Size = new System.Drawing.Size(6, 25);
            // 
            // ComboIsForOrders
            // 
            this.ComboIsForOrders.Items.AddRange(new object[] {
            "Под заказы",
            "Для склада",
            "Все"});
            this.ComboIsForOrders.Name = "ComboIsForOrders";
            this.ComboIsForOrders.Size = new System.Drawing.Size(121, 25);
            this.ComboIsForOrders.Text = "Все";
            this.ComboIsForOrders.SelectedIndexChanged += new System.EventHandler(this.ComboIsForOrders_SelectedIndexChanged);
            // 
            // ButtAddPurchase
            // 
            this.ButtAddPurchase.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtAddPurchase.Image = global::OwlBusinessStudio.Properties.Resources.add_icon;
            this.ButtAddPurchase.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtAddPurchase.Name = "ButtAddPurchase";
            this.ButtAddPurchase.Size = new System.Drawing.Size(23, 22);
            this.ButtAddPurchase.Text = "Создать закупку";
            this.ButtAddPurchase.Click += new System.EventHandler(this.ButtAddPurchase_Click);
            // 
            // ButtChoosePurchaseList
            // 
            this.ButtChoosePurchaseList.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtChoosePurchaseList.Image = ((System.Drawing.Image)(resources.GetObject("ButtChoosePurchaseList.Image")));
            this.ButtChoosePurchaseList.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtChoosePurchaseList.Name = "ButtChoosePurchaseList";
            this.ButtChoosePurchaseList.Size = new System.Drawing.Size(23, 22);
            this.ButtChoosePurchaseList.Text = "Выбрать";
            this.ButtChoosePurchaseList.Click += new System.EventHandler(this.ButtChoosePurchaseList_Click);
            // 
            // DataGridViewPurchase
            // 
            this.DataGridViewPurchase.AllowUserToAddRows = false;
            this.DataGridViewPurchase.AllowUserToDeleteRows = false;
            this.DataGridViewPurchase.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewPurchase.Location = new System.Drawing.Point(160, 28);
            this.DataGridViewPurchase.Name = "DataGridViewPurchase";
            this.DataGridViewPurchase.ReadOnly = true;
            this.DataGridViewPurchase.Size = new System.Drawing.Size(808, 263);
            this.DataGridViewPurchase.TabIndex = 1;
            this.DataGridViewPurchase.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DataGridViewPurchase_CellClick);
            this.DataGridViewPurchase.CellDoubleClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DataGridViewPurchase_CellClick);
            this.DataGridViewPurchase.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewPurchase_CellStateChanged);
            // 
            // TabDeliveryLists
            // 
            this.TabDeliveryLists.Controls.Add(this.TxtDayTotal);
            this.TabDeliveryLists.Controls.Add(this.label13);
            this.TabDeliveryLists.Controls.Add(this.label11);
            this.TabDeliveryLists.Controls.Add(this.TxtTotalForDriver);
            this.TabDeliveryLists.Controls.Add(this.TxtCalculatorComment);
            this.TabDeliveryLists.Controls.Add(this.label6);
            this.TabDeliveryLists.Controls.Add(this.ButtSaveSummChange);
            this.TabDeliveryLists.Controls.Add(this.label5);
            this.TabDeliveryLists.Controls.Add(this.TxtTotalDescription);
            this.TabDeliveryLists.Controls.Add(this.label4);
            this.TabDeliveryLists.Controls.Add(this.TxtResultForDriver);
            this.TabDeliveryLists.Controls.Add(this.PanelOpenDeliverListForm);
            this.TabDeliveryLists.Controls.Add(this.TimePickerDeliverListDate);
            this.TabDeliveryLists.Controls.Add(this.label9);
            this.TabDeliveryLists.Controls.Add(this.ListDrivers);
            this.TabDeliveryLists.Controls.Add(this.toolStrip3);
            this.TabDeliveryLists.Controls.Add(this.DataGridViewDeliveryLists);
            this.TabDeliveryLists.ImageKey = "Lorry.ico";
            this.TabDeliveryLists.Location = new System.Drawing.Point(4, 31);
            this.TabDeliveryLists.Name = "TabDeliveryLists";
            this.TabDeliveryLists.Size = new System.Drawing.Size(976, 522);
            this.TabDeliveryLists.TabIndex = 3;
            this.TabDeliveryLists.Text = "Список развоза";
            this.TabDeliveryLists.UseVisualStyleBackColor = true;
            // 
            // TxtDayTotal
            // 
            this.TxtDayTotal.Location = new System.Drawing.Point(63, 244);
            this.TxtDayTotal.Name = "TxtDayTotal";
            this.TxtDayTotal.ReadOnly = true;
            this.TxtDayTotal.Size = new System.Drawing.Size(95, 20);
            this.TxtDayTotal.TabIndex = 16;
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(8, 244);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(47, 13);
            this.label13.TabIndex = 15;
            this.label13.Text = "За день";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(8, 272);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(37, 13);
            this.label11.TabIndex = 14;
            this.label11.Text = "Всего";
            // 
            // TxtTotalForDriver
            // 
            this.TxtTotalForDriver.Location = new System.Drawing.Point(63, 269);
            this.TxtTotalForDriver.Name = "TxtTotalForDriver";
            this.TxtTotalForDriver.ReadOnly = true;
            this.TxtTotalForDriver.Size = new System.Drawing.Size(95, 20);
            this.TxtTotalForDriver.TabIndex = 13;
            // 
            // TxtCalculatorComment
            // 
            this.TxtCalculatorComment.Location = new System.Drawing.Point(8, 421);
            this.TxtCalculatorComment.Multiline = true;
            this.TxtCalculatorComment.Name = "TxtCalculatorComment";
            this.TxtCalculatorComment.Size = new System.Drawing.Size(150, 67);
            this.TxtCalculatorComment.TabIndex = 12;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(5, 405);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(63, 13);
            this.label6.TabIndex = 11;
            this.label6.Text = "Пояснение";
            // 
            // ButtSaveSummChange
            // 
            this.ButtSaveSummChange.Location = new System.Drawing.Point(8, 494);
            this.ButtSaveSummChange.Name = "ButtSaveSummChange";
            this.ButtSaveSummChange.Size = new System.Drawing.Size(147, 25);
            this.ButtSaveSummChange.TabIndex = 10;
            this.ButtSaveSummChange.Text = "Рассчитать и сохранить";
            this.ButtSaveSummChange.UseVisualStyleBackColor = true;
            this.ButtSaveSummChange.Click += new System.EventHandler(this.ButtSaveSummChange_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(5, 318);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(63, 13);
            this.label5.TabIndex = 9;
            this.label5.Text = "Пояснение";
            // 
            // TxtTotalDescription
            // 
            this.TxtTotalDescription.Location = new System.Drawing.Point(8, 333);
            this.TxtTotalDescription.Multiline = true;
            this.TxtTotalDescription.Name = "TxtTotalDescription";
            this.TxtTotalDescription.Size = new System.Drawing.Size(150, 69);
            this.TxtTotalDescription.TabIndex = 8;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(8, 298);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(37, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Итого";
            // 
            // TxtResultForDriver
            // 
            this.TxtResultForDriver.Location = new System.Drawing.Point(63, 295);
            this.TxtResultForDriver.Name = "TxtResultForDriver";
            this.TxtResultForDriver.ReadOnly = true;
            this.TxtResultForDriver.Size = new System.Drawing.Size(95, 20);
            this.TxtResultForDriver.TabIndex = 6;
            // 
            // PanelOpenDeliverListForm
            // 
            this.PanelOpenDeliverListForm.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelOpenDeliverListForm.Controls.Add(this.ButtCancelDeliverListsForm);
            this.PanelOpenDeliverListForm.Controls.Add(this.ButtOpenDeliverListsForm);
            this.PanelOpenDeliverListForm.Controls.Add(this.label10);
            this.PanelOpenDeliverListForm.Controls.Add(this.TimePickerDeliverListsFormDate);
            this.PanelOpenDeliverListForm.Location = new System.Drawing.Point(180, 44);
            this.PanelOpenDeliverListForm.Name = "PanelOpenDeliverListForm";
            this.PanelOpenDeliverListForm.Size = new System.Drawing.Size(235, 88);
            this.PanelOpenDeliverListForm.TabIndex = 5;
            this.PanelOpenDeliverListForm.Visible = false;
            // 
            // ButtCancelDeliverListsForm
            // 
            this.ButtCancelDeliverListsForm.Location = new System.Drawing.Point(150, 48);
            this.ButtCancelDeliverListsForm.Name = "ButtCancelDeliverListsForm";
            this.ButtCancelDeliverListsForm.Size = new System.Drawing.Size(66, 23);
            this.ButtCancelDeliverListsForm.TabIndex = 8;
            this.ButtCancelDeliverListsForm.Text = "Выход";
            this.ButtCancelDeliverListsForm.UseVisualStyleBackColor = true;
            this.ButtCancelDeliverListsForm.Click += new System.EventHandler(this.ButtCancelDeliverListsForm_Click);
            // 
            // ButtOpenDeliverListsForm
            // 
            this.ButtOpenDeliverListsForm.Location = new System.Drawing.Point(15, 48);
            this.ButtOpenDeliverListsForm.Name = "ButtOpenDeliverListsForm";
            this.ButtOpenDeliverListsForm.Size = new System.Drawing.Size(129, 23);
            this.ButtOpenDeliverListsForm.TabIndex = 7;
            this.ButtOpenDeliverListsForm.Text = "Сформировать";
            this.ButtOpenDeliverListsForm.UseVisualStyleBackColor = true;
            this.ButtOpenDeliverListsForm.Click += new System.EventHandler(this.ButtOpenDeliverListsForm_Click);
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(54, 6);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(102, 13);
            this.label10.TabIndex = 6;
            this.label10.Text = "Маршруты на день";
            // 
            // TimePickerDeliverListsFormDate
            // 
            this.TimePickerDeliverListsFormDate.Location = new System.Drawing.Point(15, 22);
            this.TimePickerDeliverListsFormDate.Name = "TimePickerDeliverListsFormDate";
            this.TimePickerDeliverListsFormDate.Size = new System.Drawing.Size(201, 20);
            this.TimePickerDeliverListsFormDate.TabIndex = 5;
            this.TimePickerDeliverListsFormDate.Value = new System.DateTime(2011, 8, 4, 0, 0, 0, 0);
            // 
            // TimePickerDeliverListDate
            // 
            this.TimePickerDeliverListDate.Location = new System.Drawing.Point(8, 44);
            this.TimePickerDeliverListDate.Name = "TimePickerDeliverListDate";
            this.TimePickerDeliverListDate.Size = new System.Drawing.Size(150, 20);
            this.TimePickerDeliverListDate.TabIndex = 4;
            this.TimePickerDeliverListDate.Value = new System.DateTime(2011, 8, 4, 0, 0, 0, 0);
            this.TimePickerDeliverListDate.ValueChanged += new System.EventHandler(this.TimePickerDeliverListDate_ValueChanged);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(8, 66);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(55, 13);
            this.label9.TabIndex = 3;
            this.label9.Text = "Водители";
            // 
            // ListDrivers
            // 
            this.ListDrivers.FormattingEnabled = true;
            this.ListDrivers.Location = new System.Drawing.Point(8, 81);
            this.ListDrivers.Name = "ListDrivers";
            this.ListDrivers.Size = new System.Drawing.Size(150, 160);
            this.ListDrivers.TabIndex = 2;
            this.ListDrivers.SelectedIndexChanged += new System.EventHandler(this.ListDrivers_SelectedIndexChanged);
            // 
            // toolStrip3
            // 
            this.toolStrip3.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.toolStrip3.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripSeparator8,
            this.toolStripSeparator9,
            this.ButtEditDeliveryLists,
            this.ButtGenerateDeliverListsFile,
            this.ButtGenerateReceipts,
            this.toolStripSeparator12,
            this.ButtGenerateReceipt});
            this.toolStrip3.Location = new System.Drawing.Point(0, 0);
            this.toolStrip3.Name = "toolStrip3";
            this.toolStrip3.Size = new System.Drawing.Size(976, 31);
            this.toolStrip3.TabIndex = 1;
            this.toolStrip3.Text = "toolStrip3";
            // 
            // toolStripSeparator8
            // 
            this.toolStripSeparator8.Name = "toolStripSeparator8";
            this.toolStripSeparator8.Size = new System.Drawing.Size(6, 31);
            // 
            // toolStripSeparator9
            // 
            this.toolStripSeparator9.Name = "toolStripSeparator9";
            this.toolStripSeparator9.Size = new System.Drawing.Size(6, 31);
            // 
            // ButtEditDeliveryLists
            // 
            this.ButtEditDeliveryLists.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtEditDeliveryLists.Image = ((System.Drawing.Image)(resources.GetObject("ButtEditDeliveryLists.Image")));
            this.ButtEditDeliveryLists.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtEditDeliveryLists.Name = "ButtEditDeliveryLists";
            this.ButtEditDeliveryLists.Size = new System.Drawing.Size(28, 28);
            this.ButtEditDeliveryLists.Text = "Редактировать листы";
            this.ButtEditDeliveryLists.Click += new System.EventHandler(this.ButtEditDeliveryLists_Click);
            // 
            // ButtGenerateDeliverListsFile
            // 
            this.ButtGenerateDeliverListsFile.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtGenerateDeliverListsFile.Image = ((System.Drawing.Image)(resources.GetObject("ButtGenerateDeliverListsFile.Image")));
            this.ButtGenerateDeliverListsFile.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtGenerateDeliverListsFile.Name = "ButtGenerateDeliverListsFile";
            this.ButtGenerateDeliverListsFile.Size = new System.Drawing.Size(28, 28);
            this.ButtGenerateDeliverListsFile.Text = "Создать файл листа развоза";
            this.ButtGenerateDeliverListsFile.Click += new System.EventHandler(this.ButtGenerateDeliverListsFile_Click);
            // 
            // ButtGenerateReceipts
            // 
            this.ButtGenerateReceipts.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtGenerateReceipts.Image = ((System.Drawing.Image)(resources.GetObject("ButtGenerateReceipts.Image")));
            this.ButtGenerateReceipts.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtGenerateReceipts.Name = "ButtGenerateReceipts";
            this.ButtGenerateReceipts.Size = new System.Drawing.Size(28, 28);
            this.ButtGenerateReceipts.Text = "Сгенерировать товарные чеки";
            this.ButtGenerateReceipts.Click += new System.EventHandler(this.ButtGenerateReceipt_Click);
            // 
            // toolStripSeparator12
            // 
            this.toolStripSeparator12.Name = "toolStripSeparator12";
            this.toolStripSeparator12.Size = new System.Drawing.Size(6, 31);
            // 
            // ButtGenerateReceipt
            // 
            this.ButtGenerateReceipt.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtGenerateReceipt.Enabled = false;
            this.ButtGenerateReceipt.Image = ((System.Drawing.Image)(resources.GetObject("ButtGenerateReceipt.Image")));
            this.ButtGenerateReceipt.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtGenerateReceipt.Name = "ButtGenerateReceipt";
            this.ButtGenerateReceipt.Size = new System.Drawing.Size(28, 28);
            this.ButtGenerateReceipt.Text = "Сформировать чек";
            this.ButtGenerateReceipt.Click += new System.EventHandler(this.ButtGenerateReceipt_Click_1);
            // 
            // DataGridViewDeliveryLists
            // 
            this.DataGridViewDeliveryLists.AllowUserToAddRows = false;
            this.DataGridViewDeliveryLists.AllowUserToDeleteRows = false;
            this.DataGridViewDeliveryLists.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridViewDeliveryLists.Location = new System.Drawing.Point(164, 32);
            this.DataGridViewDeliveryLists.Name = "DataGridViewDeliveryLists";
            this.DataGridViewDeliveryLists.ReadOnly = true;
            this.DataGridViewDeliveryLists.Size = new System.Drawing.Size(804, 264);
            this.DataGridViewDeliveryLists.TabIndex = 0;
            this.DataGridViewDeliveryLists.CellStateChanged += new System.Windows.Forms.DataGridViewCellStateChangedEventHandler(this.DataGridViewDeliveryLists_CellStateChanged);
            this.DataGridViewDeliveryLists.RowStateChanged += new System.Windows.Forms.DataGridViewRowStateChangedEventHandler(this.DataGridViewDeliveryLists_RowStateChanged);
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.TxtLog);
            this.tabPage1.Location = new System.Drawing.Point(4, 31);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Size = new System.Drawing.Size(976, 522);
            this.tabPage1.TabIndex = 4;
            this.tabPage1.Text = "Отладка";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // TxtLog
            // 
            this.TxtLog.Location = new System.Drawing.Point(8, 3);
            this.TxtLog.Multiline = true;
            this.TxtLog.Name = "TxtLog";
            this.TxtLog.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.TxtLog.Size = new System.Drawing.Size(960, 305);
            this.TxtLog.TabIndex = 0;
            // 
            // ImageListMain
            // 
            this.ImageListMain.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("ImageListMain.ImageStream")));
            this.ImageListMain.TransparentColor = System.Drawing.Color.Transparent;
            this.ImageListMain.Images.SetKeyName(0, "Task-List-icon (1).png");
            this.ImageListMain.Images.SetKeyName(1, "box-icon (3).png");
            this.ImageListMain.Images.SetKeyName(2, "Lorry.ico");
            this.ImageListMain.Images.SetKeyName(3, "shopping-bag-icon.png");
            // 
            // toolStrip1
            // 
            this.toolStrip1.ImageScalingSize = new System.Drawing.Size(32, 32);
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.MenuButtAuthorization,
            this.toolStripSeparator1,
            this.toolStripSeparator2,
            this.MenuButtAddOrder,
            this.MenuButtEditOrder,
            this.MenuButtDeleteOrder,
            this.toolStripSeparator13,
            this.toolStripSeparator14,
            this.ButtChangeOrderStatus,
            this.ButtAddGood,
            this.ButtEditGood,
            this.ButtDeleteGood,
            this.toolStripSeparator3,
            this.toolStripSeparator4,
            this.ButtPurchaseForOrdersForm,
            this.ButtPurchaseForStoreForm,
            this.toolStripSeparator6,
            this.toolStripSeparator15,
            this.ButtProcess,
            this.LabelProcessName});
            this.toolStrip1.Location = new System.Drawing.Point(0, 24);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(984, 39);
            this.toolStrip1.TabIndex = 5;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // MenuButtAuthorization
            // 
            this.MenuButtAuthorization.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.MenuButtAuthorization.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtAuthorization.Image")));
            this.MenuButtAuthorization.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.MenuButtAuthorization.Name = "MenuButtAuthorization";
            this.MenuButtAuthorization.Size = new System.Drawing.Size(36, 36);
            this.MenuButtAuthorization.Text = "Войти от имени...";
            this.MenuButtAuthorization.Click += new System.EventHandler(this.MenuButtAuthorization_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 39);
            // 
            // MenuButtAddOrder
            // 
            this.MenuButtAddOrder.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.MenuButtAddOrder.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtAddOrder.Image")));
            this.MenuButtAddOrder.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.MenuButtAddOrder.Name = "MenuButtAddOrder";
            this.MenuButtAddOrder.Size = new System.Drawing.Size(36, 36);
            this.MenuButtAddOrder.Text = "Новый заказ";
            this.MenuButtAddOrder.Click += new System.EventHandler(this.MenuButtAddOrder_Click);
            // 
            // MenuButtEditOrder
            // 
            this.MenuButtEditOrder.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.MenuButtEditOrder.Enabled = false;
            this.MenuButtEditOrder.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtEditOrder.Image")));
            this.MenuButtEditOrder.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.MenuButtEditOrder.Name = "MenuButtEditOrder";
            this.MenuButtEditOrder.Size = new System.Drawing.Size(36, 36);
            this.MenuButtEditOrder.Text = "Редактировать заказ";
            this.MenuButtEditOrder.Click += new System.EventHandler(this.MenuButtEditOrder_Click);
            // 
            // MenuButtDeleteOrder
            // 
            this.MenuButtDeleteOrder.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.MenuButtDeleteOrder.Enabled = false;
            this.MenuButtDeleteOrder.Image = ((System.Drawing.Image)(resources.GetObject("MenuButtDeleteOrder.Image")));
            this.MenuButtDeleteOrder.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.MenuButtDeleteOrder.Name = "MenuButtDeleteOrder";
            this.MenuButtDeleteOrder.Size = new System.Drawing.Size(36, 36);
            this.MenuButtDeleteOrder.Text = "Удалить заказ";
            this.MenuButtDeleteOrder.Click += new System.EventHandler(this.MenuButtDeleteOrder_Click);
            // 
            // toolStripSeparator13
            // 
            this.toolStripSeparator13.Name = "toolStripSeparator13";
            this.toolStripSeparator13.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator14
            // 
            this.toolStripSeparator14.Name = "toolStripSeparator14";
            this.toolStripSeparator14.Size = new System.Drawing.Size(6, 39);
            // 
            // ButtChangeOrderStatus
            // 
            this.ButtChangeOrderStatus.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtChangeOrderStatus.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ButtOrderStatusNew,
            this.ButtOrderStatusDelay,
            this.ButtOrderStatusFinished});
            this.ButtChangeOrderStatus.Enabled = false;
            this.ButtChangeOrderStatus.Image = ((System.Drawing.Image)(resources.GetObject("ButtChangeOrderStatus.Image")));
            this.ButtChangeOrderStatus.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtChangeOrderStatus.Name = "ButtChangeOrderStatus";
            this.ButtChangeOrderStatus.Size = new System.Drawing.Size(48, 36);
            this.ButtChangeOrderStatus.Text = "Сменить статус";
            // 
            // ButtOrderStatusNew
            // 
            this.ButtOrderStatusNew.Name = "ButtOrderStatusNew";
            this.ButtOrderStatusNew.Size = new System.Drawing.Size(131, 22);
            this.ButtOrderStatusNew.Text = "Новый";
            this.ButtOrderStatusNew.Click += new System.EventHandler(this.ButtOrderStatusNew_Click);
            // 
            // ButtOrderStatusDelay
            // 
            this.ButtOrderStatusDelay.Name = "ButtOrderStatusDelay";
            this.ButtOrderStatusDelay.Size = new System.Drawing.Size(131, 22);
            this.ButtOrderStatusDelay.Text = "Отложен";
            this.ButtOrderStatusDelay.Click += new System.EventHandler(this.ButtOrderStatusDelay_Click);
            // 
            // ButtOrderStatusFinished
            // 
            this.ButtOrderStatusFinished.Name = "ButtOrderStatusFinished";
            this.ButtOrderStatusFinished.Size = new System.Drawing.Size(131, 22);
            this.ButtOrderStatusFinished.Text = "Выполнен";
            this.ButtOrderStatusFinished.Click += new System.EventHandler(this.ButtOrderStatusFinished_Click);
            // 
            // ButtAddGood
            // 
            this.ButtAddGood.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtAddGood.Image = ((System.Drawing.Image)(resources.GetObject("ButtAddGood.Image")));
            this.ButtAddGood.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtAddGood.Name = "ButtAddGood";
            this.ButtAddGood.Size = new System.Drawing.Size(36, 36);
            this.ButtAddGood.Text = "Добавить товар";
            this.ButtAddGood.Click += new System.EventHandler(this.ButtAddGood_Click);
            // 
            // ButtEditGood
            // 
            this.ButtEditGood.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtEditGood.Enabled = false;
            this.ButtEditGood.Image = ((System.Drawing.Image)(resources.GetObject("ButtEditGood.Image")));
            this.ButtEditGood.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtEditGood.Name = "ButtEditGood";
            this.ButtEditGood.Size = new System.Drawing.Size(36, 36);
            this.ButtEditGood.Text = "Редактировать товар";
            this.ButtEditGood.Click += new System.EventHandler(this.ButtEditGood_Click);
            // 
            // ButtDeleteGood
            // 
            this.ButtDeleteGood.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtDeleteGood.Enabled = false;
            this.ButtDeleteGood.Image = ((System.Drawing.Image)(resources.GetObject("ButtDeleteGood.Image")));
            this.ButtDeleteGood.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtDeleteGood.Name = "ButtDeleteGood";
            this.ButtDeleteGood.Size = new System.Drawing.Size(36, 36);
            this.ButtDeleteGood.Text = "Удалить товар";
            this.ButtDeleteGood.Visible = false;
            this.ButtDeleteGood.Click += new System.EventHandler(this.ButtDeleteGood_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator4
            // 
            this.toolStripSeparator4.Name = "toolStripSeparator4";
            this.toolStripSeparator4.Size = new System.Drawing.Size(6, 39);
            // 
            // ButtPurchaseForOrdersForm
            // 
            this.ButtPurchaseForOrdersForm.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtPurchaseForOrdersForm.Image = ((System.Drawing.Image)(resources.GetObject("ButtPurchaseForOrdersForm.Image")));
            this.ButtPurchaseForOrdersForm.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtPurchaseForOrdersForm.Name = "ButtPurchaseForOrdersForm";
            this.ButtPurchaseForOrdersForm.Size = new System.Drawing.Size(36, 36);
            this.ButtPurchaseForOrdersForm.Text = "Закупки под заказы";
            this.ButtPurchaseForOrdersForm.Click += new System.EventHandler(this.ButtPurchaseForm_Click);
            // 
            // ButtPurchaseForStoreForm
            // 
            this.ButtPurchaseForStoreForm.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtPurchaseForStoreForm.Image = ((System.Drawing.Image)(resources.GetObject("ButtPurchaseForStoreForm.Image")));
            this.ButtPurchaseForStoreForm.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtPurchaseForStoreForm.Name = "ButtPurchaseForStoreForm";
            this.ButtPurchaseForStoreForm.Size = new System.Drawing.Size(36, 36);
            this.ButtPurchaseForStoreForm.Click += new System.EventHandler(this.ButtPurchaseForStoreForm_Click);
            // 
            // toolStripSeparator6
            // 
            this.toolStripSeparator6.Name = "toolStripSeparator6";
            this.toolStripSeparator6.Size = new System.Drawing.Size(6, 39);
            // 
            // toolStripSeparator15
            // 
            this.toolStripSeparator15.Name = "toolStripSeparator15";
            this.toolStripSeparator15.Size = new System.Drawing.Size(6, 39);
            // 
            // ButtProcess
            // 
            this.ButtProcess.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.ButtProcess.Image = ((System.Drawing.Image)(resources.GetObject("ButtProcess.Image")));
            this.ButtProcess.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ButtProcess.Name = "ButtProcess";
            this.ButtProcess.Size = new System.Drawing.Size(36, 36);
            this.ButtProcess.Text = "Процесс";
            this.ButtProcess.Visible = false;
            // 
            // LabelProcessName
            // 
            this.LabelProcessName.Name = "LabelProcessName";
            this.LabelProcessName.Size = new System.Drawing.Size(0, 36);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(441, 1);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 6;
            this.button2.Text = "button2";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Visible = false;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // TimerDisabler
            // 
            this.TimerDisabler.Interval = 2000;
            this.TimerDisabler.Tick += new System.EventHandler(this.TimerDisabler_Tick);
            // 
            // DialogSaveYandexXML
            // 
            this.DialogSaveYandexXML.Filter = "Яндекс маркет XML|*.yml";
            // 
            // TimerChecker
            // 
            this.TimerChecker.Enabled = true;
            this.TimerChecker.Interval = 1000;
            this.TimerChecker.Tick += new System.EventHandler(this.TimerChecker_Tick);
            // 
            // PanelConcatinatePhones
            // 
            this.PanelConcatinatePhones.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PanelConcatinatePhones.Controls.Add(this.ButtCancatPhones);
            this.PanelConcatinatePhones.Controls.Add(this.label22);
            this.PanelConcatinatePhones.Controls.Add(this.label21);
            this.PanelConcatinatePhones.Controls.Add(this.label18);
            this.PanelConcatinatePhones.Controls.Add(this.TxtPhone3);
            this.PanelConcatinatePhones.Controls.Add(this.TxtPhone2);
            this.PanelConcatinatePhones.Controls.Add(this.TxtMainPhone);
            this.PanelConcatinatePhones.Location = new System.Drawing.Point(180, 80);
            this.PanelConcatinatePhones.Name = "PanelConcatinatePhones";
            this.PanelConcatinatePhones.Size = new System.Drawing.Size(216, 137);
            this.PanelConcatinatePhones.TabIndex = 12;
            this.PanelConcatinatePhones.Visible = false;
            // 
            // TxtMainPhone
            // 
            this.TxtMainPhone.Location = new System.Drawing.Point(106, 16);
            this.TxtMainPhone.Mask = "(000)000-00-00";
            this.TxtMainPhone.Name = "TxtMainPhone";
            this.TxtMainPhone.Size = new System.Drawing.Size(104, 20);
            this.TxtMainPhone.TabIndex = 0;
            // 
            // TxtPhone2
            // 
            this.TxtPhone2.Location = new System.Drawing.Point(106, 46);
            this.TxtPhone2.Mask = "(000)000-00-00";
            this.TxtPhone2.Name = "TxtPhone2";
            this.TxtPhone2.Size = new System.Drawing.Size(104, 20);
            this.TxtPhone2.TabIndex = 1;
            // 
            // TxtPhone3
            // 
            this.TxtPhone3.Location = new System.Drawing.Point(106, 73);
            this.TxtPhone3.Mask = "(000)000-00-00";
            this.TxtPhone3.Name = "TxtPhone3";
            this.TxtPhone3.Size = new System.Drawing.Size(104, 20);
            this.TxtPhone3.TabIndex = 2;
            // 
            // label18
            // 
            this.label18.AutoSize = true;
            this.label18.Location = new System.Drawing.Point(2, 19);
            this.label18.Name = "label18";
            this.label18.Size = new System.Drawing.Size(103, 13);
            this.label18.TabIndex = 3;
            this.label18.Text = "Основной телефон";
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Location = new System.Drawing.Point(3, 49);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(35, 13);
            this.label21.TabIndex = 4;
            this.label21.Text = "Тел 2";
            // 
            // label22
            // 
            this.label22.AutoSize = true;
            this.label22.Location = new System.Drawing.Point(2, 76);
            this.label22.Name = "label22";
            this.label22.Size = new System.Drawing.Size(35, 13);
            this.label22.TabIndex = 5;
            this.label22.Text = "Тел 3";
            // 
            // ButtCancatPhones
            // 
            this.ButtCancatPhones.Location = new System.Drawing.Point(3, 109);
            this.ButtCancatPhones.Name = "ButtCancatPhones";
            this.ButtCancatPhones.Size = new System.Drawing.Size(207, 23);
            this.ButtCancatPhones.TabIndex = 6;
            this.ButtCancatPhones.Text = "Слияние номеров";
            this.ButtCancatPhones.UseVisualStyleBackColor = true;
            this.ButtCancatPhones.Click += new System.EventHandler(this.ButtCancatPhones_Click);
            // 
            // toolStripButton1
            // 
            this.toolStripButton1.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton1.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton1.Image")));
            this.toolStripButton1.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton1.Name = "toolStripButton1";
            this.toolStripButton1.Size = new System.Drawing.Size(23, 22);
            this.toolStripButton1.Text = "Слияние номеров";
            this.toolStripButton1.Click += new System.EventHandler(this.toolStripButton1_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(984, 648);
            this.Controls.Add(this.toolStrip1);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.TabControlMain);
            this.Controls.Add(this.StatusBarMain);
            this.Controls.Add(this.MainMenu);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MainMenuStrip = this.MainMenu;
            this.Name = "MainForm";
            this.Text = "\"Филин\" - бизнес студия";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainForm_FormClosing);
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.Resize += new System.EventHandler(this.MainForm_Resize);
            this.StatusBarMain.ResumeLayout(false);
            this.StatusBarMain.PerformLayout();
            this.MainMenu.ResumeLayout(false);
            this.MainMenu.PerformLayout();
            this.TabControlMain.ResumeLayout(false);
            this.PageOrders.ResumeLayout(false);
            this.PageOrders.PerformLayout();
            this.PanelExportOrders.ResumeLayout(false);
            this.PanelExportOrders.PerformLayout();
            this.toolStrip4.ResumeLayout(false);
            this.toolStrip4.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewOrders)).EndInit();
            this.TabGoods.ResumeLayout(false);
            this.TabGoods.PerformLayout();
            this.toolStrip5.ResumeLayout(false);
            this.toolStrip5.PerformLayout();
            this.PanelSetQuantity.ResumeLayout(false);
            this.PanelSetQuantity.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumGoodQuantity)).EndInit();
            this.PanelSetMinimum.ResumeLayout(false);
            this.PanelSetMinimum.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.NumGoodMinimum)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewGoods)).EndInit();
            this.contextMenuGoods.ResumeLayout(false);
            this.TabPurchase.ResumeLayout(false);
            this.TabPurchase.PerformLayout();
            this.PanelAddPurchase.ResumeLayout(false);
            this.PanelAddPurchase.PerformLayout();
            this.toolStrip2.ResumeLayout(false);
            this.toolStrip2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewPurchase)).EndInit();
            this.TabDeliveryLists.ResumeLayout(false);
            this.TabDeliveryLists.PerformLayout();
            this.PanelOpenDeliverListForm.ResumeLayout(false);
            this.PanelOpenDeliverListForm.PerformLayout();
            this.toolStrip3.ResumeLayout(false);
            this.toolStrip3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridViewDeliveryLists)).EndInit();
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.PanelConcatinatePhones.ResumeLayout(false);
            this.PanelConcatinatePhones.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.StatusStrip StatusBarMain;
        private System.Windows.Forms.MenuStrip MainMenu;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem MenuButtAbout;
        private System.Windows.Forms.ToolStripMenuItem MenuButtExit;
        private System.Windows.Forms.OpenFileDialog OpenFileGoods;
        private System.Windows.Forms.ToolStripProgressBar ProgressBarMain;
        private System.Windows.Forms.ToolStripMenuItem ToolStripMenuAdministration;
        private System.Windows.Forms.ToolStripMenuItem MenuButtUsers;
        private System.Windows.Forms.ToolStripMenuItem MenuButtMetros;
        private System.Windows.Forms.ToolStripMenuItem ToolStripMenuStatistics;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem4;
        private System.Windows.Forms.ToolStripMenuItem MenuButtExportGoods;
        private System.Windows.Forms.ToolStripMenuItem MenuButtExportOrders;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem3;
        private System.Windows.Forms.ToolStripMenuItem MenuButtImportGoods;
        private System.Windows.Forms.TabControl TabControlMain;
        private System.Windows.Forms.TabPage TabGoods;
        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.ToolStripButton MenuButtAuthorization;
        private System.Windows.Forms.ToolStripButton MenuButtAddOrder;
        private System.Windows.Forms.TabPage PageOrders;
        public System.Windows.Forms.ImageList ImageListMain;
        private System.Windows.Forms.DataGridView DataGridViewGoods;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripButton MenuButtDeleteOrder;
        private System.Windows.Forms.DataGridView DataGridViewOrders;
        private System.Windows.Forms.ToolStripButton MenuButtEditOrder;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator4;
        private System.Windows.Forms.RadioButton RadioOrdersAll;
        private System.Windows.Forms.RadioButton RadioOrdersWeek;
        private System.Windows.Forms.RadioButton RadioOrdersToday;
        private System.Windows.Forms.ToolStripMenuItem MenuButtDeliveryPrice;
        private System.Windows.Forms.ToolStripMenuItem MenuButtDiscounts;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem2;
        private System.Windows.Forms.Panel PanelSetMinimum;
        private System.Windows.Forms.Button ButtOKMinumum;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.NumericUpDown NumGoodMinimum;
        private System.Windows.Forms.ContextMenuStrip contextMenuGoods;
        private System.Windows.Forms.ToolStripMenuItem ButtSetMinimum;
        private System.Windows.Forms.ToolStripMenuItem ButtSetQuantity;
        private System.Windows.Forms.ToolStripMenuItem ButtRefreshGoods;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.DateTimePicker DatePickerOrdersTo;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.DateTimePicker DatePickerOrdersFrom;
        private System.Windows.Forms.Button ButtSelectOrders;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem6;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem8;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem9;
        private System.Windows.Forms.ToolStripButton ButtPurchaseForOrdersForm;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator6;
        private System.Windows.Forms.ToolStripButton ButtAddGood;
        private System.Windows.Forms.ToolStripButton ButtPurchaseForStoreForm;
        private System.Windows.Forms.TabPage TabPurchase;
        private System.Windows.Forms.ToolStrip toolStrip2;
        private System.Windows.Forms.ToolStripSeparator toolStripButton3;
        private System.Windows.Forms.DataGridView DataGridViewPurchase;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator7;
        private System.Windows.Forms.ToolStripComboBox ComboIsForOrders;
        private System.Windows.Forms.ToolStripButton ButtChoosePurchaseList;
        private System.Windows.Forms.Button ButtPurchaseSelect;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.DateTimePicker TimePickerPurchaseTo;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.DateTimePicker TimePickerPurchaseFrom;
        private System.Windows.Forms.RadioButton RadioPurchaseForPeriod;
        private System.Windows.Forms.RadioButton RadioPurchaseToday;
        private System.Windows.Forms.TabPage TabDeliveryLists;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.ListBox ListDrivers;
        private System.Windows.Forms.ToolStrip toolStrip3;
        private System.Windows.Forms.ToolStripButton ButtEditDeliveryLists;
        private System.Windows.Forms.DataGridView DataGridViewDeliveryLists;
        private System.Windows.Forms.ToolStripButton ButtGenerateDeliverListsFile;
        private System.Windows.Forms.ToolStripButton ButtGenerateReceipts;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator8;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator9;
        private System.Windows.Forms.DateTimePicker TimePickerDeliverListDate;
        private System.Windows.Forms.Panel PanelOpenDeliverListForm;
        private System.Windows.Forms.Button ButtCancelDeliverListsForm;
        private System.Windows.Forms.Button ButtOpenDeliverListsForm;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.DateTimePicker TimePickerDeliverListsFormDate;
        private System.Windows.Forms.ToolStripSplitButton ButtChangeOrderStatus;
        private System.Windows.Forms.ToolStripMenuItem ButtOrderStatusNew;
        private System.Windows.Forms.ToolStripMenuItem ButtOrderStatusDelay;
        private System.Windows.Forms.ToolStripMenuItem ButtOrderStatusFinished;
        private System.Windows.Forms.ListBox ListOrderStatuses;
        private System.Windows.Forms.Panel PanelSetQuantity;
        private System.Windows.Forms.Button ButtOKQuantity;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.NumericUpDown NumGoodQuantity;
        private System.Windows.Forms.Button ButtClosePanelSetMinimum;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ToolStripMenuItem ButtDBSettings;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.ToolStripStatusLabel LabelProgress;
        private System.Windows.Forms.Timer TimerDisabler;
        private System.Windows.Forms.CheckBox CheckAlreadyPurchased;
        private System.Windows.Forms.ToolStrip toolStrip4;
        private System.Windows.Forms.ToolStripLabel toolStripLabel1;
        private System.Windows.Forms.ToolStripTextBox TxtOrderFilterValue;
        private System.Windows.Forms.ToolStripComboBox ComboOrderFilterName;
        private System.Windows.Forms.ToolStripLabel toolStripLabel2;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator5;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator10;
        private System.Windows.Forms.ToolStripComboBox ComboOrderFilterSelfget;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator11;
        private System.Windows.Forms.ToolStripButton ButtRefresh;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator12;
        private System.Windows.Forms.ToolStripButton ButtGenerateReceipt;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator13;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator14;
        private System.Windows.Forms.ToolStripButton ButtEditGood;
        private System.Windows.Forms.ToolStripButton ButtDeleteGood;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator15;
        private System.Windows.Forms.ToolStripButton ButtProcess;
        private System.Windows.Forms.ToolStripLabel LabelProcessName;
        private System.Windows.Forms.ToolStripMenuItem ButtGoodsHistory;
        private System.Windows.Forms.ToolStripMenuItem ButtGenerateYandexXML;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem5;
        private System.Windows.Forms.SaveFileDialog DialogSaveYandexXML;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator16;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator17;
        private System.Windows.Forms.ToolStripButton ButtPrevPage;
        private System.Windows.Forms.ToolStripLabel LabelCurrentPage;
        private System.Windows.Forms.ToolStripLabel toolStripLabel4;
        private System.Windows.Forms.ToolStripLabel LabelTotalPages;
        private System.Windows.Forms.ToolStripButton ButtNextPage;
        private System.Windows.Forms.ToolStripLabel toolStripLabel3;
        private System.Windows.Forms.ToolStripComboBox ComboPageSize;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem7;
        private System.Windows.Forms.ToolStripMenuItem ButtSiteContent;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem10;
        private System.Windows.Forms.ToolStripMenuItem ButtLoginHistory;
        private System.Windows.Forms.Button ButtSaveSummChange;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox TxtTotalDescription;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox TxtResultForDriver;
        private System.Windows.Forms.ToolStripMenuItem ButtSetBrandOrder;
        private System.Windows.Forms.ToolStrip toolStrip5;
        private System.Windows.Forms.ToolStripButton RefreshGoodsButt;
        private System.Windows.Forms.ToolStripComboBox ComboGoodFilterName;
        private System.Windows.Forms.ToolStripLabel toolStripLabel5;
        private System.Windows.Forms.ToolStripTextBox GoodFilterValue;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem11;
        private System.Windows.Forms.TextBox TxtCalculatorComment;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.TextBox TxtTotalForDriver;
        private System.Windows.Forms.TextBox TxtDayTotal;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.ToolStripButton ButtPrintCheck;
        private System.Windows.Forms.Button ButtPurchaseSelectByDateTo;
        private System.Windows.Forms.ToolStripButton ButtAddPurchase;
        private System.Windows.Forms.Panel PanelAddPurchase;
        private System.Windows.Forms.Button ButtCancelPurchase;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.ComboBox ComboPurchaseSupplier;
        private System.Windows.Forms.DateTimePicker DatePickerPurchase;
        private System.Windows.Forms.TextBox TxtPurchaseName;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.Button ButtCreatePurchase;
        private System.Windows.Forms.ToolStripMenuItem ButtAdv;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem12;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator18;
        private System.Windows.Forms.ToolStripButton ButtExportGoods;
        private System.Windows.Forms.Timer TimerChecker;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TextBox TxtLog;
        private System.Windows.Forms.ToolStripButton ButtExportOrders;
        private System.Windows.Forms.Panel PanelExportOrders;
        private System.Windows.Forms.Label label20;
        private System.Windows.Forms.Label label19;
        private System.Windows.Forms.DateTimePicker TimePickerDeliverOrderTo;
        private System.Windows.Forms.DateTimePicker TimePickerDeliverOrderFrom;
        private System.Windows.Forms.DateTimePicker TimePickerCreateOrderTo;
        private System.Windows.Forms.DateTimePicker TimePickerCreateOrderFrom;
        private System.Windows.Forms.CheckBox CheckDeliverOrderDate;
        private System.Windows.Forms.CheckBox CheckCreateOrderDate;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.ComboBox ComboExportOrdersStatus;
        private System.Windows.Forms.Button ButtExportOrdersGo;
        private System.Windows.Forms.Panel PanelConcatinatePhones;
        private System.Windows.Forms.Button ButtCancatPhones;
        private System.Windows.Forms.Label label22;
        private System.Windows.Forms.Label label21;
        private System.Windows.Forms.Label label18;
        private System.Windows.Forms.MaskedTextBox TxtPhone3;
        private System.Windows.Forms.MaskedTextBox TxtPhone2;
        private System.Windows.Forms.MaskedTextBox TxtMainPhone;
        private System.Windows.Forms.ToolStripButton toolStripButton1;
    }
}

