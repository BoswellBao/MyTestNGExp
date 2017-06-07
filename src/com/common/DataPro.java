package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.poi.ss.formula.ptg.StringPtg;
import org.testng.annotations.DataProvider;

import com.common.ExcelRead;

import bsh.This;

public class DataPro {

	@DataProvider(name = "data")
	public Object[][] drataPo() {

		return new Object[][] { { "12" }, { "" }, { "12345678901" }, { "dfe" }, { "德尔福" }, };
	}

	
	
	
	
	
	
	public static String[] strings;
	public static void receiveParas(String[] paras){
		if(paras == null){
			return;
		}else{
			strings = paras;
		}
		
	}
	
	
	@DataProvider(name = "excelData")
	public static Object[][] excelDataPro() throws Exception { // String...strings可变长度参数列表，不同用例需要从excel中获取的参数个数会不一样

		// 获取excel某个sheet所有数据
		Object[][] theExcelData = ExcelRead.readExcel();
			// 获取二维数组theExcelData的行数
			int theExcelDataRowNum = theExcelData.length;
			// 获取二维数组theExcelData的列数
			int theExcelDataColNum = theExcelData[0].length;
			// 获取传过来的参数个数
			int stringsLength = strings.length;
			System.out.println(stringsLength);
//			for (int i = 0; i < stringsLength; i++) {
//				System.out.println(strings[i]);
//			}

			// theExcelData第一行是标题，遍历第一行标题，有标题和参数一样的就记下map.put(标题,标题列号)
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (int col = 0; col < theExcelDataColNum; col++) { 
				for (int si = 0; si < stringsLength; si++) {
					if (strings[si].equals(theExcelData[0][col])) { 
						System.out.println((String)theExcelData[0][col]  +  col);
						map.put((String)theExcelData[0][col], col);
					} else {
						continue;
					}
				}
			}
			System.out.println(map.get("remarks"));
			//要map.keySet才能通过键取值
//			Set set = map.keySet();		
			// 装载筛选出来数据,filterList:遍历除第一行外所有行，当execute列的值是y或者Y时，就把一维数组arr加到filterList中
			ArrayList<String[]> filterList = new ArrayList<>();			
			for(int i = 1; i < theExcelDataRowNum; i ++){
				if(theExcelData[i][map.get("execute")] == null){
					continue;
				}if(theExcelData[i][map.get("execute")].equals("y") || theExcelData[i][map.get("execute")].equals("Y")){
					String[] arr = new String[stringsLength -1];   //stringsLength - 1是因为最后一列是execut，没有必要收取并返回
					for(int ai = 0; ai < stringsLength - 1; ai ++ ){ 
						arr[ai] = (String)theExcelData[i][map.get(strings[ai])];
						System.out.println(arr[ai]);
					}
					filterList.add(arr);
				}else{
					continue;
				}
			}
			//把filterList转化为二维数组filterArry,行数是filterListSize,列数是stringsLength - 1 
			int filterListSize = filterList.size();
			String[][]filterArry = filterList.toArray(new String[filterListSize][stringsLength - 1]);
			for(int i=0;i<filterListSize;i++){
				for(int j =0;j<stringsLength - 1;j++){
					System.out.println(filterArry[i][j]);
				}
				System.out.println("\r\n");
			}



			 return filterArry;
		
//		return new Object[][] { { theExcelData[1][3], theExcelData[1][3], theExcelData[1][3] },
//				{ theExcelData[1][3], theExcelData[2][3], theExcelData[1][3] },
//				{ theExcelData[1][3], theExcelData[3][3], theExcelData[1][3] },
//				{ theExcelData[1][3], theExcelData[4][3], theExcelData[1][3] },
//				{ theExcelData[1][3], theExcelData[5][3], theExcelData[1][3] }, };
//
			 }

}
