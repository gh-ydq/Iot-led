package com.qizhi.socket.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClient {
	private static Logger logger = LoggerFactory.getLogger(HttpClient.class);
	public static <T> void sendMsg(String url ,T bodyData){
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
		    //拼接参数
			
			StringEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(bodyData));
			httpPost.setEntity(entity);
		    httpPost.addHeader("content-type", "application/json");
		    CloseableHttpResponse response2;
		    
		    response2 = httpclient.execute(httpPost);

	        logger.info("http 请求response status ={}",response2.getStatusLine());
	        HttpEntity entity2 = response2.getEntity();
	        //消耗掉response
//	        if (response2 != null) {
//				String responseBody = EntityUtils.toString(response2.getEntity(), "UTF-8");
//				System.out.println(responseBody.toString());
//			}
	        EntityUtils.consume(entity2);
	    } catch(Exception e){
		
	    }finally {
	    	
	    }
   }
}
