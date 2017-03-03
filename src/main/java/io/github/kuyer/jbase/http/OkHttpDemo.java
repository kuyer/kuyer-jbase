package io.github.kuyer.jbase.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpDemo {

	public static void main(String[] args) throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://piaoniu.io/yong-spring-mvcyou-ya-de-shi-xian-301tiao-zhuan/").build();
		Response response = client.newCall(request).execute();
		if(response.isSuccessful()) {
			String content = response.body().string();
			System.out.println("content: "+content);
		} else {
			System.err.println("unexpected code: "+response);
		}
	}

}
