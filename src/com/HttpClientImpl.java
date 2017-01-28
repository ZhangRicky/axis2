package com;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientImpl{

	private static HttpClient httpClient;
	static {
		PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
		cm.setMaxTotal(50);
		cm.setDefaultMaxPerRoute(50);
		//cm.getSchemeRegistry().register(new Scheme(HTTPS, 443, getSSLSocketFactory()));

		DefaultHttpClient defaultHttpClient = new DefaultHttpClient(cm);
		defaultHttpClient.getParams().setIntParameter("http.socket.timeout", 10000);
		httpClient = defaultHttpClient;
	}

	public String doGet(String url) throws IOException{

		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(get);
			String res;
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode < 300){
				HttpEntity entity = response.getEntity();
				res = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
			} else{
				throw new IOException("http get[" + url + "] failed,statuCode [" + statusCode + "].");
			}
			System.out.println("返回信息"+res);
			return res;
		} catch (Exception e){
			if(!get.isAborted()) {
				get.abort();
			}
			throw new IOException(e);
		}
	}




	public static void main(String[] args) throws IOException {
		//测试
//		String urlString = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb8501c02ae21fe7b&secret=c7353c80752a68138d2e9d2d9ff8e3fa";
		String urlString = "http://10.63.24.50/mps/mps/yd/registe.ajax?userName=caorj&phoneNo=18909231888&passwd=123456&idCardNo=&realName=&registType=phone_no&userType=normal";
		HttpClientImpl gai = new HttpClientImpl();
		//gai.pushMsg(urlString);
	}
}
