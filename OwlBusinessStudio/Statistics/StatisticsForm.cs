using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace OwlBusinessStudio.Statistics
{
    public partial class StatisticsForm : Form
    {
        public StatisticsForm()
        {
            InitializeComponent();
            loadFilters();
        }
        private void loadFilters()
        {
            DataTable brands = MainForm.dbProc.executeGet("SELECT distinct company FROM goods WHERE company IS NOT NULL ORDER BY company");
            DataTable animals = MainForm.dbProc.executeGet("SELECT distinct animal FROM goods WHERE animal IS NOT NULL ORDER BY animal");
            DataTable food_types = MainForm.dbProc.executeGet("SELECT distinct food_type FROM goods WHERE food_type IS NOT NULL ORDER BY food_type");
            DataTable food_type_categorys = MainForm.dbProc.executeGet("SELECT distinct food_type_category FROM goods WHERE food_type_category IS NOT NULL ORDER BY food_type_category");


            CheckListBrand.DataSource = brands;
            CheckListBrand.DisplayMember = "company";
            CheckListBrand.ValueMember = "company";

            CheckListAnimal.DataSource = animals;
            CheckListAnimal.DisplayMember = "animal";
            CheckListAnimal.ValueMember = "animal";

            CheckListFoodType.DataSource = food_types;
            CheckListFoodType.DisplayMember = "food_type";
            CheckListFoodType.ValueMember = "food_type";

            CheckListUseType.DataSource = food_type_categorys;
            CheckListUseType.DisplayMember = "food_type_category";
            CheckListUseType.ValueMember = "food_type_category";

            TimePickerFrom.Value = MainForm.dbProc.getServerTime().AddMonths(-1);
        }
        private void ButtPurchase_Click(object sender, EventArgs e)
        {
            if (ButtPurchase.BackColor != System.Drawing.Color.LightGreen)
            {
                ButtPurchase.BackColor = System.Drawing.Color.LightGreen;
                ButtSells.BackColor = System.Drawing.SystemColors.Control;
            }
        }
        private string CreateConditionsForPurchase()
        {
            StringBuilder filter = new StringBuilder();
            filter.Append(" date>=date(");
            filter.Append(MainForm.dbProc.getDateTimeString(TimePickerFrom.Value));
            filter.Append(") AND date<=(");
            filter.Append(MainForm.dbProc.getDateTimeString(TimePickerTo.Value));
            filter.Append(") ");
            for (int i = 0; i < CheckListBrand.CheckedIndices.Count; i++)
            {
                if (i == 0)
                {
                    if (filter.Length > 0)
                    {
                        filter.Append(" AND ");
                    }
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
            return filter.ToString();
        }
        private string CreateConditionsForSelles()
        {
            StringBuilder filter = new StringBuilder();
            filter.Append(" deliver_date>=date(");
            filter.Append(MainForm.dbProc.getDateTimeString(TimePickerFrom.Value));
            filter.Append(") AND deliver_date<=(");
            filter.Append(MainForm.dbProc.getDateTimeString(TimePickerTo.Value));
            filter.Append(") ");
            for (int i = 0; i < CheckListBrand.CheckedIndices.Count; i++)
            {
                if (i == 0)
                {
                    if (filter.Length > 0)
                    {
                        filter.Append(" AND ");
                    }
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
            return filter.ToString();
        }
        private void ButtSells_Click(object sender, EventArgs e)
        {
            if (ButtSells.BackColor != System.Drawing.Color.LightGreen)
            {
                ButtSells.BackColor = System.Drawing.Color.LightGreen;
                ButtPurchase.BackColor = System.Drawing.SystemColors.Control;
            }
        }

        private void ButtShowStatistics_Click(object sender, EventArgs e)
        {
            if (ButtPurchase.BackColor == System.Drawing.Color.LightGreen)
            {
                LoadPurchaseStatistics();
            }
            else
            {
                LoadSellsStatistics();
            }
        }
        private void LoadPurchaseStatistics()
        {
            string selector = "SELECT gs.articul,pl.good_name,company,animal,food_type,food_type_category,sum(pl.good_count*pl.good_purchase_price) as total_sum,sum(pl.good_count) as total_count FROM purchase_lists pl, goods gs WHERE";
            string conditions = CreateConditionsForPurchase();
            if (conditions!="")
            {
                conditions += " AND ";
            }
            conditions += " pl.good_name=gs.name_for_order_full GROUP BY pl.good_name,gs.articul,company,animal,food_type,food_type_category";
            string command = selector + conditions;
            //MessageBox.Show(command);
            DataTable tab = MainForm.dbProc.executeGet(command);
            DataGridViewStatistics.DataSource = tab;
            FTwoFramework.DB.Configurator.translateToRussian(DataGridViewStatistics);
            double summ = 0;
            for (int i = 0; i < tab.Rows.Count; i++)
            {
                summ += (double)tab.Rows[i]["total_sum"];
            }
            DataGridViewStatistics.Columns[0].Width = 60;
            DataGridViewStatistics.Columns[0].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[1].Width = 400;
            DataGridViewStatistics.Columns[1].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[1].DefaultCellStyle.WrapMode = DataGridViewTriState.True;
            DataGridViewStatistics.Columns[2].Width = 80;
            DataGridViewStatistics.Columns[2].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[3].Width = 80;
            DataGridViewStatistics.Columns[3].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[4].Width = 80;
            DataGridViewStatistics.Columns[4].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[5].Width = 100;
            DataGridViewStatistics.Columns[5].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[6].Width = 90;
            DataGridViewStatistics.Columns[6].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;
            DataGridViewStatistics.Columns[7].Width = 90;
            DataGridViewStatistics.Columns[7].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;

            DataGridViewStatistics.CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal;
            DataGridViewStatistics.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            TxtTotal.Text = ((int)summ).ToString() + " руб.";
        }
        private void LoadSellsStatistics()
        {
            string selector = "SELECT gs.articul,gs.name_for_order_full,company,animal,food_type,food_type_category,sum(og.count*og.price) as total_sum,sum(og.count) as total_count FROM orders ord, order_goods og, goods gs WHERE";
            string conditions = CreateConditionsForSelles();
            if (conditions != "")
            {
                conditions += " AND ";
            }
            conditions += " ord.id=og.order_id AND ord.status=3 AND og.good_id=gs.id GROUP BY gs.name_for_order_full,gs.articul,company,animal,food_type,food_type_category";
            string command = selector + conditions;
            //MessageBox.Show(command);
            DataTable tab = MainForm.dbProc.executeGet(command);
            DataGridViewStatistics.DataSource = tab;
            FTwoFramework.DB.Configurator.translateToRussian(DataGridViewStatistics);
            long summ = 0;
            for (int i = 0; i < tab.Rows.Count; i++)
            {
                summ += (long)tab.Rows[i]["total_sum"];
            }
            DataGridViewStatistics.Columns[0].Width = 60;
            DataGridViewStatistics.Columns[0].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[1].Width = 400;
            DataGridViewStatistics.Columns[1].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[1].DefaultCellStyle.WrapMode = DataGridViewTriState.True;
            DataGridViewStatistics.Columns[2].Width = 80;
            DataGridViewStatistics.Columns[2].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[3].Width = 80;
            DataGridViewStatistics.Columns[3].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[4].Width = 80;
            DataGridViewStatistics.Columns[4].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[5].Width = 100;
            DataGridViewStatistics.Columns[5].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopLeft;
            DataGridViewStatistics.Columns[6].Width = 90;
            DataGridViewStatistics.Columns[6].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;
            DataGridViewStatistics.Columns[7].Width = 90;
            DataGridViewStatistics.Columns[7].DefaultCellStyle.Alignment = DataGridViewContentAlignment.TopRight;

            DataGridViewStatistics.CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal;
            DataGridViewStatistics.AutoSizeRowsMode = DataGridViewAutoSizeRowsMode.AllCells;
            TxtTotal.Text = ((int)summ).ToString() + " руб.";
        }
    }
}
