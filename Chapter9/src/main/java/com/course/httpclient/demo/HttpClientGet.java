package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpClientGet {
    @Test
    public void getDemo() throws IOException {
//        存放结果
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
//        client 执行 get 方法
        HttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }
}
