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

		return new Object[][] { { "12" }, { "" }, { "12345678901" }, { "dfe" }, { "�¶���" }, };
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
	public static Object[][] excelDataPro() throws Exception { // String...strings�ɱ䳤�Ȳ����б���ͬ������Ҫ��excel�л�ȡ�Ĳ��������᲻һ��

		// ��ȡexcelĳ��sheet��������
		Object[][] theExcelData = ExcelRead.readExcel();
			// ��ȡ��ά����theExcelData������
			int theExcelDataRowNum = theExcelData.length;
			// ��ȡ��ά����theExcelData������
			int theExcelDataColNum = theExcelData[0].length;
			// ��ȡ�������Ĳ�������
			int stringsLength = strings.length;
			System.out.println(stringsLength);
//			for (int i = 0; i < stringsLength; i++) {
//				System.out.println(strings[i]);
//			}

			// theExcelData��һ���Ǳ��⣬������һ�б��⣬�б���Ͳ���һ���ľͼ���map.put(����,�����к�)
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
			//Ҫmap.keySet����ͨ����ȡֵ
//			Set set = map.keySet();		
			// װ��ɸѡ��������,filterList:��������һ���������У���execute�е�ֵ��y����Yʱ���Ͱ�һά����arr�ӵ�filterList��
			ArrayList<String[]> filterList = new ArrayList<>();			
			for(int i = 1; i < theExcelDataRowNum; i ++){
				if(theExcelData[i][map.get("execute")] == null){
					continue;
				}if(theExcelData[i][map.get("execute")].equals("y") || theExcelData[i][map.get("execute")].equals("Y")){
					String[] arr = new String[stringsLength -1];   //stringsLength - 1����Ϊ���һ����execut��û�б�Ҫ��ȡ������
					for(int ai = 0; ai < stringsLength - 1; ai ++ ){ 
						arr[ai] = (String)theExcelData[i][map.get(strings[ai])];
						System.out.println(arr[ai]);
					}
					filterList.add(arr);
				}else{
					continue;
				}
			}
			//��filterListת��Ϊ��ά����filterArry,������filterListSize,������stringsLength - 1 
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
