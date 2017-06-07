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
	public void beforeClass() {
		System.out.println("----------test start----------\r\n");
		ExcelRead.getTestSheetName(excelUrl, sheetName);
	}
     /*
      * ����@DataProvider��ȡ����
      */
	@Test(dataProvider = "excelData",dataProviderClass = DataPro.class) //����������Դ���ƣ��ͱ�����Դע��ķ�������
	public void mytest( ) throws Exception {
		        System.out.println("testing....");
		        String tvid=null;
				String url = Constant.GET_TV_CHANAAL_URL;
				//ͨ������Ҫ�Ĳ����ó���Ҫ��excel����
//				DataPro.excelDataPro("remarks", "para_tvid" ,"expectedCode" ,"execute");
				
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
	            int statcode = response.getStatusLine().getStatusCode();
	            String flag = "fail";
	            //���ԣ�������״̬���Ԥ��״̬��Ա�			
	            try {
	            	AssertJUnit.assertEquals(statcode, 200);
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
	public void afterClass() {
		ExcelWrite.writeExcel(excelUrl, sheetName, resultList);
		System.out.println("\r\n----------test end----------");
	}
}