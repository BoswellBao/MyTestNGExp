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
      * ��testng.xml��ȡ������һ��ֻ��ִ��һ�������������ȡ�ֶε����ƺ��ֶ�ֵ��
      */
	@Test(parameters ={"paraName","paraValue"})
	public void mytest(String paraName, String paraValue ) throws IOException {
        System.out.println("testing....");
        
				String url = Constant.GET_TV_CHANAAL_URL;
				// ��װ�ύ���������Ĳ�����Ϣ
				List<NameValuePair> list = new ArrayList<>();
				list.add(new BasicNameValuePair(paraName, paraValue));
				//������Ӧ
				CloseableHttpResponse response = HttpRequest.sendPost(url, list);
	            //��ȡ��Ӧʵ��
	            HttpEntity entity = response.getEntity();
	            //��ʵ��ת��Ϊ�ַ���
	            String resultString = EntityUtils.toString(entity);
	            //�����Ӧ����
	            System.out.println(resultString);
	            //��ȡ����״̬��
	            int statcode = response.getStatusLine().getStatusCode() ;
	            //���ԣ�������״̬���Ԥ��״̬��Ա�
				AssertJUnit.assertEquals(statcode, 200);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("----------test end----------");
	}
}
