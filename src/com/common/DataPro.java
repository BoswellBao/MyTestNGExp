package com.common;

import org.testng.annotations.DataProvider;

import com.common.ExcelRead;

public class DataPro {

	@DataProvider(name = "data")
	public Object[][] drataPo(){

          return new Object[][] {
        	  {"12"},
        	  {""},
        	  {"12345678901"},
        	  {"dfe"},
        	  {"�¶���"},
        	 };
	}
	
	@DataProvider(name = "excelData")
	public static Object[][] excelDataPro( ) throws Exception{ //String...strings�ɱ䳤�Ȳ����б���ͬ������Ҫ��excel�л�ȡ�Ĳ��������᲻һ��
//		String...strings
//		  int length = strings.length;
//		  System.out.println(strings[1]);
		  Object[][] theExcelData= ExcelRead.readExcel();
		  
		  
		
		
          return new Object[][] {
        	  {theExcelData[1][3]},
        	  {theExcelData[2][3]},
        	  {theExcelData[3][3]},
        	  {theExcelData[4][3]},
        	  {theExcelData[5][3]},
        	 };
	}
	

}
