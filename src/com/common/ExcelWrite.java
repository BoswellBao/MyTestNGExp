package com.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelWrite {

	public static HSSFSheet ExcelWSheet;
	public static HSSFWorkbook ExcelWBook;
	public static HSSFCell Cell;
	public static HSSFRow Row;
	public static String executeResult = "executeResult";
	public static String returnData = "returnData";

	public static void writeExcel(String excelUrl, String sheetName, ArrayList<String[]> resultList) throws Exception {

		if (resultList == null || resultList.size() == 0) {
			return;
		}
		// list元素个数，每个元素是个包含两个string的一维数组
		int rownum = resultList.size();
		// System.out.println("list元素个数："+ rownum);
		// 把list转化为rownum×2的数组，这个2是固定的，因为只要往excel中写入executeResult和retrurnData
		String[][] arr = resultList.toArray(new String[rownum][2]);
		// for (int i = 0; i < rownum; i++) {
		// for (int j = 0; j < 2; j++) {
		// System.out.println(arr[i][j]);
		// }
		// }

		// poi操作excel，并把数组arr的值放进去
		try {
			FileInputStream ExcelFile = new FileInputStream(excelUrl);
			// 找到excel位置和sheet
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			// 获取总行数，这里计数是从零开始，例如：excel有6行，但totalRows=5
			int totalRows = ExcelWSheet.getLastRowNum();
			// 获取总列数，列数是实际的列数，例如：实际有8列，totalcols=8
			Row = ExcelWSheet.getRow(0);
			int totalCols = Row.getPhysicalNumberOfCells();
			// 算出executeResult、returnData所在的列序号，序号都是从零开始的
			int executeResultColNum = totalCols - 2;
			int returnDataColNum = totalCols - 1;
			// 写入数据
			FileOutputStream out = new FileOutputStream(excelUrl);
			int j = 0;// 计数器，当execute为y或者Y时才增1
			for (int i = 1; i <= totalRows; i++) {
				int executeColNum = totalCols - 3;
				Object obj = ExcelRead.getCellData(i, executeColNum);
				if (obj == null) {
					continue;
				}
				if (!(obj == null)) {
					String yY = obj.toString();

					if (yY.equals("y") || yY.equals("Y")) {
						Row = ExcelWSheet.getRow(i);
						// 判断executeResult列的单元格是否为空，为空就先createCell再setCellValue；不为空就getCell再setCellValue
						Cell = ExcelWSheet.getRow(i).getCell(executeResultColNum);
						if (Cell == null) {
							Row.createCell(executeResultColNum).setCellValue(arr[j][0]);
						} else {
							Row.getCell(executeResultColNum).setCellValue(arr[j][0]);
						}
						// 判断returnData列的单元格是否为空，为空就先createCell再setCellValue；不为空就getCell再setCellValue
						Cell = ExcelWSheet.getRow(i).getCell(returnDataColNum);
						if (Cell == null) {
							Row.createCell(returnDataColNum).setCellValue(arr[j][1]);
						} else {
							Row.getCell(returnDataColNum).setCellValue(arr[j][1]);
						}
						j++;
					} else {
						continue;
					}
				}
			}
			out.flush();
			ExcelWBook.write(out);
			out.close();

		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

	}
}
