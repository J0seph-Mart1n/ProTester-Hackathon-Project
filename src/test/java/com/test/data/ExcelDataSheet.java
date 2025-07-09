package com.test.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataSheet {
	String fileName;
	FileInputStream fis;
	static FileOutputStream fos;
	static Workbook wb;
	Sheet sheet;

	//Constructor
	public ExcelDataSheet(String excelName, String sheetName) {
		this.fileName = excelName;
		File filep = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\test\\data\\" + fileName);
		//C:\Users\2403740\eclipse-workspace\SeleniumMiniProject\src\test\java\com\test\data - Path for this file
		try {
			fis = new FileInputStream(filep);
			try {
				//Getting sheet from Workbook
				wb = new XSSFWorkbook(fis);
				sheet = wb.getSheet(sheetName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	//Row Count
	public int getRowCount() {
		int rowcount = sheet.getPhysicalNumberOfRows();
		return rowcount;
	}

	//Column Count
	public int getColumnCount() {
		Row row = sheet.getRow(0);
		int columnum = row.getLastCellNum() - 1;
		return columnum;
	}
	
	//Gets Data from Cell
	public String getCellData(int rownum, int columnum) {
		Cell cell = sheet.getRow(rownum).getCell(columnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		String data = cell.toString();
		return data;
	}
	
	//Writes Data to Cell
	public void setCellData(int rowIndex, String columnName, String data) throws IOException {
		/*
		 * To write data into Excel based on column name in Selenium Java using Apache
		 * POI, you'll need to:
		 * 
		 * Locate the column index by column header name.
		 * 
		 * Locate the row by index (or add new row).
		 * 
		 * Write the value into the correct cell.
		 * 
		 * Save the workbook.
		 */

		// Get the first row (header) and find the column index
		Row headerRow = sheet.getRow(0);
		int columnIndex = -1;

		for (Cell cell : headerRow) {
			if (cell.getStringCellValue().trim().equalsIgnoreCase(columnName.trim())) {
				columnIndex = cell.getColumnIndex();
				break;
			}
		}

		if (columnIndex == -1) {
			System.out.println("Column not found: " + columnName);
			return;
		}

		// Get the target row (create if it doesn't exist)
		Row row = sheet.getRow(rowIndex);
		if (row == null)
			row = sheet.createRow(rowIndex);

		// Write the value to the target cell
		Cell cell = row.getCell(columnIndex);
		if (cell == null)
			cell = row.createCell(columnIndex);
		cell.setCellValue(data);

		// Write back to the Excel file
		fis.close(); // Close input stream before writing

		try {
			fos = new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\test\\data\\" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Data written successfully.");
	}

	public void closeFile() {
		try {
			fos.close();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
