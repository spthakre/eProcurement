package com.jbk.EProc.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelSheetTest  {

	static XSSFWorkbook excelWorkbook = null;
	static XSSFSheet excelSheet = null;
	static XSSFRow row = null;
	static XSSFCell cell = null;
	// static WebDriver driver = null;

	public static Object[][] getData(String filePath, String sheetName) throws IOException {

		FileInputStream fis = new FileInputStream(filePath);// Your .xlsx file
															// name along with
															// path

		excelWorkbook = new XSSFWorkbook(fis);

		// Read sheet inside the workbook by its name
		excelSheet = excelWorkbook.getSheet(sheetName); // Your sheet name

		// Find number of rows in excel file
		System.out.println(
				"First Row Number:- " + excelSheet.getFirstRowNum() + "Last Row Number:-" + excelSheet.getLastRowNum());

		int rowCount = excelSheet.getLastRowNum() /*- excelSheet.getFirstRowNum()+1*/;
		int colCount = excelSheet.getRow(0).getLastCellNum();
		System.out.println("Row Count is:- " + rowCount + "Column count is:- " + colCount);

		Object data[][] = new Object[rowCount - 1][colCount];

		for (int row = 2; row <= rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				System.out.print(getCellData(sheetName, col, row) + " "); // print
																			// Your
																			// sheet
																			// name
				data[row - 2][col] = getCellData(sheetName, col, row); // stored
																		// Your
																		// sheet
																		// name
			}
			System.out.println();
		}
		return data;
	}
	// Function will always used as below. It returns the data from a cell - No
	// need to make any changes

	public static String getCellData(String sheetName, int colNum, int rowNum)  {
		try {
			if (rowNum <= 0)
				return "";
			int index = excelWorkbook.getSheetIndex(sheetName);
			if (index == -1)
				return "";
			excelSheet = excelWorkbook.getSheetAt(index);
			row = excelSheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist in xls";
		}
	}

	public void write_Data() throws Exception {
		int num = 0;
		File file = new File("E:\\workspace3\\TestNG\\MyNewProject\\vaibhav123.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.createSheet("data");
		Row row = sh.createRow(num);
		row.createCell(1).setCellValue("Pass");
		row.createCell(2).setCellValue("Fail");

		FileOutputStream fout = new FileOutputStream(file);
		wb.write(fout);
		fout.close();

	}
}
