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

	public static void writeExcel(String excelUrl, String sheetName, ArrayList<String[]> resultList) {

		// listԪ�ظ�����ÿ��Ԫ���Ǹ���������string��һά����
		int rownum = resultList.size();
		// ��listת��Ϊrownum��2�����飬���2�ǹ̶��ģ���ΪֻҪ��excel��д��executeResult��retrurnData
		String[][] arr = resultList.toArray(new String[rownum][2]);
		for (int i = 0; i < rownum; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(arr[i][j]);
			}
		}

		// poi����excel����������arr��ֵ�Ž�ȥ
		try {
			FileInputStream ExcelFile = new FileInputStream(excelUrl);
			// �ҵ�excelλ�ú�sheet
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			// ��ȡ����������������Ǵ��㿪ʼ�����磺excel��6�У���totalRows=5
			int totalRows = ExcelWSheet.getLastRowNum();
			// ��ȡ��������������ʵ�ʵ����������磺ʵ����8�У�totalcols=8
			Row = ExcelWSheet.getRow(0);
			int totalCols = Row.getPhysicalNumberOfCells();
			// ���executeResult��returnData���ڵ�����ţ���Ŷ��Ǵ��㿪ʼ��
			System.out.println("hanghao liehao:"+totalRows+"     "+totalCols);
			int executeResultColNum = totalCols - 2;
			int returnDataColNum = totalCols - 1;
			// д������
			FileOutputStream out = new FileOutputStream(excelUrl);
			for (int i = 1; i <= totalRows; i++) {
				Row = ExcelWSheet.getRow(i);
				//�ж�executeResult�еĵ�Ԫ���Ƿ�Ϊ�գ�Ϊ�վ���createCell��setCellValue����Ϊ�վ�getCell��setCellValue
				Cell = ExcelWSheet.getRow(i).getCell(executeResultColNum);
				if(Cell == null){
					Row.createCell(executeResultColNum).setCellValue(arr[i - 1][0]);
				}else{
					Row.getCell(executeResultColNum).setCellValue(arr[i - 1][0]);
				}
				//�ж�returnData�еĵ�Ԫ���Ƿ�Ϊ�գ�Ϊ�վ���createCell��setCellValue����Ϊ�վ�getCell��setCellValue
				Cell = ExcelWSheet.getRow(i).getCell(returnDataColNum);
				if(Cell == null){
					Row.createCell(returnDataColNum).setCellValue(arr[i - 1][1]);
				}else{
					Row.getCell(returnDataColNum).setCellValue(arr[i - 1][1]);
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