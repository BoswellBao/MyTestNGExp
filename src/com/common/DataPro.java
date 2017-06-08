package com.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.DataProvider;

import com.common.ExcelRead;

public class DataPro {

	@DataProvider(name = "data")
	public Object[][] drataPo() {

		return new Object[][] { { "12" }, { "" }, { "12345678901" }, { "dfe" }, { "�¶���" }, };
	}

	public static String[] strings;

	// ���ܲ��������������Ĳ���
	public static void receiveParas(String[] paras) {
		if (paras == null) {
			return;
		} else {
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

		// theExcelData��һ���Ǳ��⣬������һ�б��⣬�б���Ͳ���һ���ľͼ���map.put(����,�����к�)
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int col = 0; col < theExcelDataColNum; col++) {
			for (int si = 0; si < stringsLength; si++) {
				if (strings[si].equals(theExcelData[0][col])) {
					map.put((String) theExcelData[0][col], col);
					break;
				}
			}
		}

		// װ��ɸѡ��������,filterList:��������һ���������У���execute�е�ֵ��y����Yʱ���Ͱ�һά����arr�ӵ�filterList��
		ArrayList<String[]> filterList = new ArrayList<>();
		for (int i = 1; i < theExcelDataRowNum; i++) {
			if (theExcelData[i][map.get("execute")] == null) {
				continue;
			}
			if (theExcelData[i][map.get("execute")].equals("y") || theExcelData[i][map.get("execute")].equals("Y")) {
				String[] arr = new String[stringsLength - 1]; // stringsLength -
																// 1����Ϊ���һ����execut��û�б�Ҫ��ȡ������
				for (int ai = 0; ai < stringsLength - 1; ai++) {
					arr[ai] = (String) theExcelData[i][map.get(strings[ai])];
				}
				filterList.add(arr);
			}
		}

		// ��filterListת��Ϊ��ά����filterArry,������filterListSize,������stringsLength - 1
		int filterListSize = filterList.size();
		String[][] filterArry = filterList.toArray(new String[filterListSize][stringsLength - 1]);
		// ��ɸѡ������������Ϊ����Դ����������Test
		return filterArry;
	}

}
