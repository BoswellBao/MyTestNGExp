package com.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelRead {

	public static String sheetName = null;
	public static String excelUrl = null;
	public static HSSFSheet ExcelWSheet;
	public static HSSFWorkbook ExcelWBook;
	public static HSSFCell Cell;
	public static HSSFRow Row;

	public static void getTestSheetName(String testExcelUrl, String testSheetName) {
		sheetName = testSheetName;
		excelUrl = testExcelUrl;
	}

	public static Object[][] readExcel() throws Exception {
		Object[][] allData = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(excelUrl);
			// 找到excel位置和sheet
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			// 获取总行数,这里计数是从零开始，例如：excel有6行，但totalRows=5
			int totalRows = ExcelWSheet.getLastRowNum();
			// 获取总列数,列数是实际的列数，例如：实际有8列，totalcols=8，但数组序号从零开始，导致下面第二层for循环j不能取=
            Row = ExcelWSheet.getRow(0);
			int totalCols = Row.getPhysicalNumberOfCells();
			allData = new String[totalRows + 1][totalCols + 1];
			for (int i = 0; i <= totalRows; i++) {
				for (int j = 0; j < totalCols; j++) {
					allData[i][j] = getCellData(i, j);
					System.out.println(allData[i][j]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return allData;
	}
    
	/*
	 * 根据单元格格式不同，用不同的策略取值
	 */
	public static Object getCellData(int RowNum, int ColNum) throws Exception {
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		if (Cell == null) {
			return "";
		}
		switch (Cell.getCellType()) {
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
			return Cell.getRichStringCellValue().getString();
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(Cell)) {
				return Cell.getDateCellValue().toString();
			} else {
				// 之前一直读取的是float类型的数值，DecimalFormat("0")可以进行格式化
				DecimalFormat df = new DecimalFormat("0");
				String str = df.format(Cell.getNumericCellValue());
				return str;
			}
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
			return Cell.getBooleanCellValue();
		default:
			return null;
		}
	}

}
