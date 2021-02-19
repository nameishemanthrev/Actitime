package com.actimind.actitime.generic;
import java.io.FileInputStream;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {
	public static Double getNumericData(String filePath, String sheetName, int row, int cell) {
		try {
			FileInputStream file = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(file);
			return workbook.getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getStringData(String filePath, String sheetName, int row, int cell) {
		try {
			FileInputStream file = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(file);
			return workbook.getSheet(sheetName).getRow(row).getCell(cell).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean getBooleanCellData(String filePath, String sheetName, int row, int cell) {
		try {
			FileInputStream file = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(file);
			return workbook.getSheet(sheetName).getRow(row).getCell(cell).getBooleanCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static LocalDateTime getDateCellData(String filePath, String sheetName, int row, int cell) {
		try {
			FileInputStream file = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(file);
			return workbook.getSheet(sheetName).getRow(row).getCell(cell).getLocalDateTimeCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getMultipleDataInString(String filePath, String sheetName) {
		try {
			FileInputStream file = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet=workbook.getSheet(sheetName);
			
			int rowNumber = sheet.getPhysicalNumberOfRows();
			int cellNumber = sheet.getRow(0).getPhysicalNumberOfCells();
			
			String[][] sarr = new String[rowNumber-1][cellNumber];
			
			
			for(int i=1,k=0;i<=rowNumber-1;i++,k++) {
				for(int j=0;j<=cellNumber-1;j++) {
					sarr[k][j]=sheet.getRow(i).getCell(j).toString();
				}
			}
			
			return sarr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}