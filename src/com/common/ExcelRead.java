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
			// �ҵ�excelλ�ú�sheet
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			// ��ȡ������,��������Ǵ��㿪ʼ�����磺excel��6�У���totalRows=5
			int totalRows = ExcelWSheet.getLastRowNum();
			// ��ȡ������,������ʵ�ʵ����������磺ʵ����8�У�totalcols=8����������Ŵ��㿪ʼ����������ڶ���forѭ��j����ȡ=
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
	 * ���ݵ�Ԫ���ʽ��ͬ���ò�ͬ�Ĳ���ȡֵ
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
				// ֮ǰһֱ��ȡ����float���͵���ֵ��DecimalFormat("0")���Խ��и�ʽ��
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
