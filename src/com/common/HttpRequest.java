package com.common;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.List;

public class HttpRequest {
    	
    	public static CloseableHttpResponse sendPost(String url, List<NameValuePair> list) throws IOException {
            //����httpclient ʵ��
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //����post����ʵ��
            HttpPost post = new HttpPost(url) ;
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list , Consts.UTF_8) ;
            //���ò�����Ϣ
            post.setEntity(formEntity);
            //�ύpost����
            CloseableHttpResponse response = httpclient.execute(post);
            //������Ӧ
            return  response;
        }
    
}

