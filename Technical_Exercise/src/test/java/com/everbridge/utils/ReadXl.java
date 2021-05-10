package com.everbridge.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXl {
	
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet  sheet;
	static int RowCount = 0;
	static int CollCount = 0;
	//static String cellValue = "";
	public static Object[][] ReadXlsx(String sheetName) throws InvalidFormatException, IOException{
		
		InputStream inp = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\everbridge\\testdata\\TestData.xlsx");	
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheet(sheetName);

		RowCount = sheet.getLastRowNum();
		System.out.println("Row = "+RowCount);
	    CollCount = sheet.getRow(0).getLastCellNum();
	    System.out.println("Coll = "+CollCount);
	    Object[][] dataProvider = new Object[RowCount][CollCount];
	    for(int i = 0 ; i<RowCount;i++){
	    	Row row = sheet.getRow(i);
	    	for(int j=0;j<1;j++){
	    		Cell cell = row.getCell(j);
	    		dataProvider[i][j] =cell.getStringCellValue();
	    	}
	    }
		return dataProvider;
	}

}
