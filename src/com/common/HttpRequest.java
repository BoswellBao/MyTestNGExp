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
            //创建httpclient 实例
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //创建post方法实例
            HttpPost post = new HttpPost(url) ;
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list , Consts.UTF_8) ;
            //设置参数信息
            post.setEntity(formEntity);
            //提交post方法
            CloseableHttpResponse response = httpclient.execute(post);
            //返回响应
            return  response;
        }
    
}

