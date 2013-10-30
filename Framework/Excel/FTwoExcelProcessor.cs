using System;
using System.Reflection;
using System.Drawing;
using System.Collections.Generic;
using System.Text;
using Microsoft.Office.Core;
using Microsoft.Office.Interop;
using Microsoft.Office.Interop.Excel;

namespace FTwoFramework.Excel
{
    public class FTwoExcelProcessor
    {
        private string[] letters = new string[26] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" 
        ,"m","n","o","p","q","r","s","t"
        ,"u","v","w","x","y","z"};

        private Microsoft.Office.Interop.Excel.Application ExcelApp;
        private Microsoft.Office.Interop.Excel.Workbook ExcelBook;
        private Microsoft.Office.Interop.Excel.Worksheet ExcelCurrentSheet;
        private Microsoft.Office.Interop.Excel.Sheets Sheets;
        public delegate void ExcelCloseHandler(Workbook Wb, Window Wn,Application app,Workbook book,Sheets sheets,FTwoExcelProcessor proc);
        public event ExcelCloseHandler OnExcelClose;
        private List<object> comObjects = new List<object>();

        public void InitApplication(bool visible)
        {
            ExcelApp = new Application();
            ExcelApp.DisplayAlerts = false;
            ExcelApp.Visible = visible;
            ExcelBook = ExcelApp.Workbooks.Add(1);
            Sheets = ExcelApp.Worksheets;
            ExcelCurrentSheet = ExcelBook.Sheets[1];
            ExcelApp.WindowDeactivate += new AppEvents_WindowDeactivateEventHandler(ExcelApp_WindowDeactivate);
            comObjects.Add(ExcelApp);
            comObjects.Add(ExcelBook);
            comObjects.Add(Sheets);
            comObjects.Add(ExcelCurrentSheet);
        }
        public void InitApplication(bool visible, string path)
        {
            ExcelApp = new Application();
            ExcelApp.DisplayAlerts = false;
            ExcelApp.Visible = visible;
            ExcelBook = ExcelApp.Workbooks.Open(path, 0, true, 5, "", "", true, Microsoft.Office.Interop.Excel.XlPlatform.xlWindows, "\t", false, false, 0, true, 1, 0);
            Sheets = ExcelApp.Worksheets;
            ExcelCurrentSheet = ExcelBook.Worksheets.get_Item(1);
            ExcelApp.WindowDeactivate += new AppEvents_WindowDeactivateEventHandler(ExcelApp_WindowDeactivate);
            comObjects.Add(ExcelApp);
            comObjects.Add(ExcelBook);
            comObjects.Add(Sheets);
            comObjects.Add(ExcelCurrentSheet);
        }
        void ExcelApp_WindowDeactivate(Workbook Wb, Window Wn)
        {
            if (OnExcelClose != null)
            {
                OnExcelClose(Wb, Wn, ExcelApp, ExcelBook, Sheets,this);
            }
        }
        public string[] getValues(int row_index)
        {
            string addressStart = generateCellAddress(1, row_index);
            string addressFinish = generateCellAddress(getColumnsCount(row_index), row_index);
            Range workSheet_range = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            Range lastCell = workSheet_range.SpecialCells(Microsoft.Office.Interop.Excel.XlCellType.xlCellTypeLastCell, Type.Missing);
            int columnCount = lastCell.Column;
            System.Array myvalues = (System.Array)workSheet_range.Cells.Value;
            int lenn = myvalues.Length;
            string[] result = new string[columnCount];
            for (int i = 0; i < columnCount; i++)
            {
                object o = myvalues.GetValue(1, i+1);
                if (o != null)
                {
                    result[i] = o.ToString();
                }
            }
            return result;
        }
        public int getRowCount()
        {
            return ExcelCurrentSheet.UsedRange.Rows.Count;
        }
        public int getColumnsCount(int row_index)
        {
            string addressStart = generateCellAddress(1, row_index);
            string addressFinish = generateCellAddress(255, row_index);
            Range workSheet_range = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            Range lastCell = workSheet_range.SpecialCells(Microsoft.Office.Interop.Excel.XlCellType.xlCellTypeLastCell, Type.Missing);
            int columnCount = lastCell.Column;
            return columnCount;
        }
        public void Save(string fileName)
        {
            ExcelBook.SaveAs(fileName, Type.Missing, Type.Missing, Type.Missing,
                        Type.Missing, Type.Missing, XlSaveAsAccessMode.xlNoChange,
            Type.Missing, Type.Missing, Type.Missing, Type.Missing,
                        Type.Missing);
        }
        public void moveToNextSheet()
        {
            ExcelBook.Sheets.Add(Type.Missing, Type.Missing, Type.Missing, Type.Missing);
            ExcelCurrentSheet = ExcelBook.Sheets[1];
            comObjects.Add(ExcelCurrentSheet);
        }
        public void moveToNextSheet(string name)
        {
            ExcelBook.Sheets.Add(Type.Missing, Type.Missing, Type.Missing, Type.Missing);
            ExcelCurrentSheet = ExcelBook.Sheets[1];
            ExcelCurrentSheet.Name = name;
            comObjects.Add(ExcelCurrentSheet);
        }
        public void changeOrientation(XlPageOrientation orientation)
        {
            ExcelCurrentSheet.PageSetup.Orientation = orientation;
        }
        public void renameSheet(string name)
        {
            ExcelCurrentSheet.Name = name;
        }
        public void makeFormula(int row, int column, string formula)
        {
            ((Range)ExcelCurrentSheet.Cells[row, column]).Formula = formula;
        }
        public void mergeCells(int x1, int y1, int x2, int y2)
        {
            string addressStart = generateCellAddress(x1, y1);
            string addressFinish = generateCellAddress(x2, y2);
            Range workSheet_range = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            workSheet_range.Merge(false);
        }
        public void makeBorder(int x1, int y1, int x2, int y2,Microsoft.Office.Interop.Excel.XlBorderWeight width,bool fill)
        {
            string addressStart = generateCellAddress(x1, y1);
            string addressFinish = generateCellAddress(x2, y2);
            Range workSheet_range = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            if (fill)
            {
                workSheet_range.get_Resize(Missing.Value, x2-x1+1).Borders.Weight = Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin;
            }
            else
            {
                workSheet_range.BorderAround(Microsoft.Office.Interop.Excel.XlLineStyle.xlContinuous
                    , width
                    , Microsoft.Office.Interop.Excel.XlColorIndex.xlColorIndexAutomatic
                    , Microsoft.Office.Interop.Excel.XlColorIndex.xlColorIndexAutomatic);
            }

        }
        public void makeBorderThin(int x1, int y1, int x2, int y2)
        {
            string addressStart = generateCellAddress(x1, y1);
            string addressFinish = generateCellAddress(x2, y2);
            Range workSheet_range = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            workSheet_range.BorderAround(Microsoft.Office.Interop.Excel.XlLineStyle.xlContinuous
                , Microsoft.Office.Interop.Excel.XlBorderWeight.xlThin
                , Microsoft.Office.Interop.Excel.XlColorIndex.xlColorIndexAutomatic
                , Microsoft.Office.Interop.Excel.XlColorIndex.xlColorIndexAutomatic);

        }
        public void makeFont(int x1, int y1, int x2, int y2,bool bold,bool italic,bool underline,int size)
        {
            string addressStart = generateCellAddress(x1, y1);
            string addressFinish = generateCellAddress(x2, y2);
            Range workSheet_range = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            workSheet_range.Font.Bold = bold;
            workSheet_range.Font.Italic = italic;
            workSheet_range.Font.Underline = underline;

            workSheet_range.Font.Size = size;
        }
        public void makeFont(int x1, int y1, int x2, int y2,string font_name)
        {
            string addressStart = generateCellAddress(x1, y1);
            string addressFinish = generateCellAddress(x2, y2);
            Range workSheet_range = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            workSheet_range.Font.Name = font_name;
        }
        public void text(int row, int column, string text)
        {
            ExcelCurrentSheet.Cells[row, column] = text;
        }
        public void text(int row, int column, string text,string number_format)
        {
            ExcelCurrentSheet.Cells[row, column] = text;
            ExcelCurrentSheet.Range[generateCellAddress(column, row), generateCellAddress(column, row)].NumberFormat = number_format;
        }
        public void setTextAlignment(int startRow, int startColumn, int finishRow, int finishColumn, ExcelTextHorizontalAlignment horizontalAlign, ExcelTextVerticalAlignment verticalAlign)
        {
            string addressStart = generateCellAddress(startColumn, startRow);
            string addressFinish = generateCellAddress(finishColumn, finishRow);
            Range cells = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            //Range cells = ((Range)ExcelCurrentSheet.Cells[starRow, startColumn]);
            // Horizontal
            if (horizontalAlign == ExcelTextHorizontalAlignment.AlignDistributed)
            {
                cells.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignDistributed;
            }
            if (horizontalAlign == ExcelTextHorizontalAlignment.AlignFill)
            {
                cells.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignFill;
            }
            if (horizontalAlign == ExcelTextHorizontalAlignment.AlignGeneral)
            {
                cells.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignGeneral;
            }
            if (horizontalAlign == ExcelTextHorizontalAlignment.AlignJustify)
            {
                cells.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignJustify;
            }
            if (horizontalAlign == ExcelTextHorizontalAlignment.AlignLeft)
            {
                cells.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignLeft;
            }
            if (horizontalAlign == ExcelTextHorizontalAlignment.AlignRight)
            {
                cells.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignRight;
            }
            if (horizontalAlign == ExcelTextHorizontalAlignment.Center)
            {
                cells.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignCenter;
            }
            if (horizontalAlign == ExcelTextHorizontalAlignment.CenterAcrossSelection)
            {
                cells.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignCenterAcrossSelection;
            }
            // Verical
            if (verticalAlign == ExcelTextVerticalAlignment.AlignBottom)
            {
                cells.VerticalAlignment = Microsoft.Office.Interop.Excel.XlVAlign.xlVAlignBottom;
            }
            if (verticalAlign == ExcelTextVerticalAlignment.AlignCenter)
            {
                cells.VerticalAlignment = Microsoft.Office.Interop.Excel.XlVAlign.xlVAlignCenter;
            }
            if (verticalAlign == ExcelTextVerticalAlignment.AlignDistributed)
            {
                cells.VerticalAlignment = Microsoft.Office.Interop.Excel.XlVAlign.xlVAlignDistributed;
            }
            if (verticalAlign == ExcelTextVerticalAlignment.AlignJustify)
            {
                cells.VerticalAlignment = Microsoft.Office.Interop.Excel.XlVAlign.xlVAlignJustify;
            }
            if (verticalAlign == ExcelTextVerticalAlignment.AlignTop)
            {
                cells.VerticalAlignment = Microsoft.Office.Interop.Excel.XlVAlign.xlVAlignTop;
            }
        }
        public void setImage(float top, float left, float width, float height, String imagePath)
        {
            ExcelCurrentSheet.Shapes.AddPicture(imagePath, Microsoft.Office.Core.MsoTriState.msoFalse,
            Microsoft.Office.Core.MsoTriState.msoCTrue, left, top, width, height);
        }
        public void formatCell(int row, int column,int height,int width)
        {
            ((Range)ExcelCurrentSheet.Cells[row, column]).EntireColumn.ColumnWidth = width;
            ((Range)ExcelCurrentSheet.Cells[row, column]).EntireColumn.RowHeight = height;
        }
        public void setWidth(int row, int column, double width)
        {
            ((Range)ExcelCurrentSheet.Cells[row, column]).EntireColumn.ColumnWidth = width;
        }
        public void setHeight(int row, int column, double height)
        {
            ((Range)ExcelCurrentSheet.Cells[row, column]).EntireRow.RowHeight = height;
        }
        public void setWrapText(int column,bool wrap)
        {
            ((Range)ExcelCurrentSheet.Cells[1, column]).EntireColumn.WrapText = wrap;
        }
        public void setAutofitColumn(int column)
        {
            ((Range)ExcelCurrentSheet.Cells[1, column]).EntireColumn.AutoFit();
        } 
        public void setAutofitRow(int row)
        {
            ((Range)ExcelCurrentSheet.Cells[row, 1]).EntireRow.AutoFit();
        }
        public void setBackgroundColor(int x1, int y1, int x2, int y2,Color c)
        {
            string addressStart = generateCellAddress(x1, y1);
            string addressFinish = generateCellAddress(x2, y2);
            Range workSheet_range = ExcelCurrentSheet.get_Range(addressStart, addressFinish);
            workSheet_range.Interior.Color = c;
        }

        private string generateCellAddress(int x, int y)
        {
            List<int> digits = new List<int>();
            bool stop = false;
            int rank = 1;
            while (!stop)
            {
                if ((x / (int)(Math.Pow(26, rank))) > 0)
                {
                    rank++;
                }
                else
                {
                    stop = true;
                }
            }

            stop = false;
            while (!stop)
            {
                int dementor = (int)(Math.Pow(26, rank - 1));
                int currDig = x / dementor;
                digits.Add(currDig);
                x = x - currDig * dementor;
                rank -= 1;
                if (rank == 1)
                {
                    digits.Add(x);
                    break;
                }
                if (rank < 1)
                {
                    break;
                }
            }
            string letter = "";
            for (int i = 0; i < digits.Count; i++)
            {
                letter += letters[digits[i] - 1];
            }
            return letter + y.ToString();
        }
        public void close()
        {
            ExcelApp.ActiveWorkbook.Saved = true;
            ExcelBook.Close(true, Type.Missing, Type.Missing);
            ExcelApp.Quit();
            for (int i = 0; i < comObjects.Count; i++)
            {
                releaseObject(comObjects[i]);
            }
        }
        private void releaseObject(object obj)
        {
            try
            {
                System.Runtime.InteropServices.Marshal.ReleaseComObject(obj);
                obj = null;
            }
            catch (Exception ex)
            {
                obj = null;
            }
            finally
            {
                GC.Collect();
            }
        } 
    }
    
    public enum ExcelTextHorizontalAlignment
    {
        Center,
        CenterAcrossSelection,
        AlignDistributed,
        AlignFill,
        AlignGeneral,
        AlignJustify,
        AlignLeft,
        AlignRight
    }
    public enum ExcelTextVerticalAlignment
    {
        AlignBottom,
        AlignCenter,
        AlignDistributed,
        AlignTop,
        AlignJustify
    }
}

