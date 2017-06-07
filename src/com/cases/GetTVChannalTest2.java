package com.cases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.common.Constant;
import com.common.HttpRequest;

import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class GetTVChannalTest2 {

	@BeforeClass
	public void beforeClass() {
		System.out.println("----------test start----------");
	}
     /*
      * 从testng.xml获取参数，一次只能执行一条用例！这里获取字段的名称和字段值。
      */
	@Test(parameters ={"paraName","paraValue"})
	public void mytest(String paraName, String paraValue ) throws IOException {
        System.out.println("testing....");
        
				String url = Constant.GET_TV_CHANAAL_URL;
				// 封装提交到服务器的参数信息
				List<NameValuePair> list = new ArrayList<>();
				list.add(new BasicNameValuePair(paraName, paraValue));
				//接收响应
				CloseableHttpResponse response = HttpRequest.sendPost(url, list);
	            //获取响应实体
	            HttpEntity entity = response.getEntity();
	            //把实体转化为字符串
	            String resultString = EntityUtils.toString(entity);
	            //输出响应内容
	            System.out.println(resultString);
	            //获取返回状态码
	            int statcode = response.getStatusLine().getStatusCode() ;
	            //断言：将返回状态码和预期状态码对比
				AssertJUnit.assertEquals(statcode, 200);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("----------test end----------");
	}
}
