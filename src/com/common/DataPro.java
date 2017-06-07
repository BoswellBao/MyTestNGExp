package com.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.DataProvider;

import com.common.ExcelRead;

public class DataPro {

	@DataProvider(name = "data")
	public Object[][] drataPo() {

		return new Object[][] { { "12" }, 
			                                { "" }, 
			                                { "12345678901" }, 
			                                { "dfe" }, 
			                                { "德尔福" }, 
			                               };
	}

	@DataProvider(name = "excelData")
	public static Object[][] excelDataPro(String strings[]) throws Exception { // String...strings可变长度参数列表，不同用例需要从excel中获取的参数个数会不一样

		//获取excel某个sheet所有数据
		Object[][] theExcelData = ExcelRead.readExcel();
		// 获取二维数组theExcelData的行数
		int theExcelDataRowNum = theExcelData.length;
		// 获取二维数组theExcelData的列数
		int theExcelDataColNum = theExcelData[0].length;
		//获取传过来的参数个数
		int stringsLength = strings.length;
	    for(int i = 0; i<stringsLength; i++){
	    	System.out.println(strings[i]);
	    }
		// 装载筛选出来数据,filterList
     	//	ArrayList<String[]> filterList = new ArrayList<>();
		// 装载   < 参数，参数对应列号 >   键值对,map


			
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		for(int i = 0; i < theExcelDataColNum; i ++ ){
			for(int si =0; si < stringsLength; si ++ ){
				if(strings[si] == theExcelData[0][i]){
					map.put(theExcelData[i][si], i);
				}else{
					continue;
				}
			}
		}
		System.out.println(map.get(strings[0]).toString() +"\r\n\n\r\r\n"+"sffsfsfsdf ");
		
		System.out.println(strings);
		// theExcelData是cxcel中所有的数据，现在根据传过来的参数，再筛选出个符合要求的数组作为数据源返回
		//String[][] theFilterData = new String[theExcelDataRowNum][stringsLength];

//		return theFilterData;
		 return new Object[][] {
		 {theExcelData[1][3],theExcelData[1][3],theExcelData[1][3]},
		 {theExcelData[1][3],theExcelData[2][3],theExcelData[1][3]},
		 {theExcelData[1][3],theExcelData[3][3],theExcelData[1][3]},
		 {theExcelData[1][3],theExcelData[4][3],theExcelData[1][3]},
		 {theExcelData[1][3],theExcelData[5][3],theExcelData[1][3]},
		 };

	}

}
