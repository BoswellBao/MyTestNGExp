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
			                                { "�¶���" }, 
			                               };
	}

	@DataProvider(name = "excelData")
	public static Object[][] excelDataPro(String strings[]) throws Exception { // String...strings�ɱ䳤�Ȳ����б���ͬ������Ҫ��excel�л�ȡ�Ĳ��������᲻һ��

		//��ȡexcelĳ��sheet��������
		Object[][] theExcelData = ExcelRead.readExcel();
		// ��ȡ��ά����theExcelData������
		int theExcelDataRowNum = theExcelData.length;
		// ��ȡ��ά����theExcelData������
		int theExcelDataColNum = theExcelData[0].length;
		//��ȡ�������Ĳ�������
		int stringsLength = strings.length;
	    for(int i = 0; i<stringsLength; i++){
	    	System.out.println(strings[i]);
	    }
		// װ��ɸѡ��������,filterList
     	//	ArrayList<String[]> filterList = new ArrayList<>();
		// װ��   < ������������Ӧ�к� >   ��ֵ��,map


			
		
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
		// theExcelData��cxcel�����е����ݣ����ڸ��ݴ������Ĳ�������ɸѡ��������Ҫ���������Ϊ����Դ����
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
