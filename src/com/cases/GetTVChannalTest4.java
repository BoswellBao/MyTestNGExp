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
		//通过传需要的参数得出需要的excel数据
		String[] paraArr = {"caseName","remarks", "para_tvid" ,"expectedCode","execute"};
     	DataPro.receiveParas(paraArr);
	}
     /*
      * 利用@DataProvider获取数据
      */
	@Test(dataProvider = "excelData",dataProviderClass = DataPro.class) //参数：数据源名称，和被数据源注解的方法名称
	public void mytest(String caseName, String remarks, String tvid, String expectedCode) throws Exception {
		        System.out.println("testing....");
				String url = Constant.GET_TV_CHANAAL_URL;				
				// 封装提交到服务器的参数信息			
				List<NameValuePair> list = new ArrayList<>();				
				list.add(new BasicNameValuePair("theTVstationID", tvid));
				//接收响应
				CloseableHttpResponse response = HttpRequest.sendPost(url, list);
	            //获取响应实体
	            HttpEntity entity = response.getEntity();
	            //把实体转化为字符串
	            String resultString = EntityUtils.toString(entity);	           
	            //获取返回状态码
	            String statcode = response.getStatusLine().toString().substring(9, 12);//string = HTTP/1.1 200 OK, substring = 200
	            String flag = "fail";
	            //断言：将返回状态码和预期状态码对比			
	            try {
	            	AssertJUnit.assertEquals(expectedCode, statcode);
	            	flag = "pass";
				} finally {
					//往excel标记该条用例执行结果，并把返回信息放进returnData
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
