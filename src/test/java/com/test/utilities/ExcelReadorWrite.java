package com.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.objectRepository.DistrictEventsPage.Event;

public class ExcelReadorWrite {
	static String filepath;
	static FileInputStream fis;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static String sheetName;
	static FileOutputStream fos;
	static XSSFRow row;
	static File f;
	static XSSFCell cell;
	static int rows;
	static int col;
    static Object[][] loginData;

	public ExcelReadorWrite(String filepath, String sheetName) {
	    
		ExcelReadorWrite.filepath = filepath;
		f = new File(System.getProperty("user.dir") + "\\" + filepath);
		System.out.println(f);
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getRowsCount() {
		rows = sheet.getPhysicalNumberOfRows();
		return rows;
	}

	public static int getColumnCount() {
		Row row = sheet.getRow(0);
		col = row.getLastCellNum();
		System.out.println("column count "+col);
		return col;
	}

	public static String getCellData(int rows, int column) {
		Cell cell = sheet.getRow(rows).getCell(column,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		return cell.toString();
	}

	public static void setCellData(String xlsheet,int rownum,int colnum,String data )throws IOException{
		fis=new FileInputStream(f);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(xlsheet);

		row=sheet.getRow(rownum);
		if (row == null) {
			row = sheet.createRow(rownum);
		}

		row=sheet.getRow(rownum);

		cell=row.createCell(colnum);
		cell.setCellValue(data);

		fos=new FileOutputStream(f);
		wb.write(fos);
	}
	
	public static Object[][] excelDataFetch() {
		System.out.println(filepath);
		System.out.println(sheet);
        int rows = getRowsCount();
        int cols = getColumnCount();
        System.out.println("cols: " + cols);
        loginData = new Object[rows - 1][cols - 1];
        
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols-1; j++) {
                loginData[i - 1][j] = getCellData(i, j);
            }
        }
        return loginData;
    }
	
	public static void writeEvents(String filePath, String sheetName, List<Event> events) throws IOException {
        // 1) Prepare file & workbook
        File file = new File(System.getProperty("user.dir") + File.separator + filePath);
        XSSFWorkbook workbook;
        XSSFSheet sheet;

        if (file.exists() && file.length() > 0) {
            // load existing
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
            }
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
        }
        else {
            // create new
            workbook = new XSSFWorkbook();
            sheet    = workbook.createSheet(sheetName);
        }

        // 2) Write headers
        XSSFRow header = sheet.getRow(0) != null
            ? sheet.getRow(0)
            : sheet.createRow(0);

        header.createCell(0).setCellValue("Date");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Location");
        header.createCell(3).setCellValue("Price");

        // 3) Write event rows
        for (int i = 0; i < events.size(); i++) {
            Event ev = events.get(i);
            XSSFRow row = sheet.getRow(i + 1) != null
                ? sheet.getRow(i + 1)
                : sheet.createRow(i + 1);

            row.createCell(0).setCellValue(ev.getDate());
            row.createCell(1).setCellValue(ev.getName());
            row.createCell(2).setCellValue(ev.getLocation());
            row.createCell(3).setCellValue(ev.getPrice());
        }

        // 4) Persist to disk
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}
