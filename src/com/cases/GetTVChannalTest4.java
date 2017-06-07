package com.cases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.common.Constant;
import com.common.DataPro;
import com.common.ExcelRead;
import com.common.ExcelWrite;
import com.common.HttpRequest;

import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class GetTVChannalTest4 {
    
	String excelUrl = ".\\src\\getChannal.xls";
	String sheetName = "getTVChannal";
	ArrayList<String[]> resultList = new ArrayList<String[]>();
	
	@BeforeClass
	public void beforeClass() throws Exception {
		System.out.println("----------test start----------\r\n");
		ExcelRead.getTestSheetName(excelUrl, sheetName);
		//ͨ������Ҫ�Ĳ����ó���Ҫ��excel����
		String[] paraArr = {"caseName","remarks", "para_tvid" ,"expectedCode","execute"};
     	DataPro.receiveParas(paraArr);
	}
     /*
      * ����@DataProvider��ȡ����
      */
	@Test(dataProvider = "excelData",dataProviderClass = DataPro.class) //����������Դ���ƣ��ͱ�����Դע��ķ�������
	public void mytest(String caseName, String remarks, String tvid, String expectedCode) throws Exception {
		        System.out.println("testing....");
				String url = Constant.GET_TV_CHANAAL_URL;				
				// ��װ�ύ���������Ĳ�����Ϣ			
				List<NameValuePair> list = new ArrayList<>();				
				list.add(new BasicNameValuePair("theTVstationID", tvid));
				//������Ӧ
				CloseableHttpResponse response = HttpRequest.sendPost(url, list);
	            //��ȡ��Ӧʵ��
	            HttpEntity entity = response.getEntity();
	            //��ʵ��ת��Ϊ�ַ���
	            String resultString = EntityUtils.toString(entity);	           
	            //��ȡ����״̬��
	            String statcode = response.getStatusLine().toString().substring(9, 12);//string = HTTP/1.1 200 OK, substring = 200
	            String flag = "fail";
	            //���ԣ�������״̬���Ԥ��״̬��Ա�			
	            try {
	            	AssertJUnit.assertEquals(expectedCode, statcode);
	            	flag = "pass";
				} finally {
					//��excel��Ǹ�������ִ�н�������ѷ�����Ϣ�Ž�returnData
					String[] arr = new String[2];
					arr[0] = flag;
					arr[1] = resultString;
					resultList.add(arr);
				}
				
	}

	@AfterClass
	public void afterClass() throws Exception {
		ExcelWrite.writeExcel(excelUrl, sheetName, resultList);
		System.out.println("\r\n----------test end----------");
	}
}
