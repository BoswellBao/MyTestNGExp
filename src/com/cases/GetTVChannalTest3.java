package com.cases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.common.Constant;
import com.common.DataPro;
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

public class GetTVChannalTest3 {

	@BeforeClass
	public void beforeClass() {
		System.out.println("----------test start----------\r\n");
	}
     /*
      * ����@DataProvider��ȡ����
      */
	@Test(dataProvider = "data",dataProviderClass = DataPro.class) //����������Դ���ƣ��ͱ�����Դע��ķ�������
	public void mytest(String tvid ) throws IOException {
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
	            //�����Ӧ����
	            System.out.println(resultString);
	            //��ȡ����״̬��
	            int statcode = response.getStatusLine().getStatusCode() ;
	            //���ԣ�������״̬���Ԥ��״̬��Ա�
				AssertJUnit.assertEquals(statcode, 200);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("\r\n----------test end----------");
	}
}
