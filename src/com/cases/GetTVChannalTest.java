package com.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.Constant;
import com.common.HttpRequest;

public class GetTVChannalTest {

	@BeforeClass
	public void beforeClass() {
		System.out.println("----------test start----------");
		
	}

	@Test
	public void mytest() throws IOException {
        System.out.println("testing....");

		        String url = Constant.GET_TV_CHANAAL_URL;
				// ��װ�ύ���������Ĳ�����Ϣ
				List<NameValuePair> list = new ArrayList<>();
				list.add(new BasicNameValuePair("theTVstationID", "1"));
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
