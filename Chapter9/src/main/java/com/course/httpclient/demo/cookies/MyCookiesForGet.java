package com.course.httpclient.demo.cookies;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
//        定位配置文件
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
//        定位具体 url
        this.url = bundle.getString("test.url");
    }

    @Test
    public void getCookie() throws IOException {
        String result;
        String testUrl = this.url + bundle.getString("test.getcookie");

//        创建连接
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(testUrl);
        HttpClientContext context = HttpClientContext.create();

//        获取响应内容
        HttpResponse response = client.execute(get, context);
        result = EntityUtils.toString(response.getEntity());

        System.out.println(result);
//        获取Cookie内容
        this.store = context.getCookieStore();
        List<Cookie> cookieList = this.store.getCookies();

        for (Cookie cookie :
                cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("Cookie name is: " + name + " ; Cookie value is: " + value);
        }


    }

    //    依赖测试 带 Cookie 信息访问 GET 请求
    @Test(dependsOnMethods = "getCookie")
    public void getWithCookie() throws IOException {
//        System.out.println(this.store.getCookies().get(0).getValue());
        String testUrl = this.url + bundle.getString("test.getwithcookie");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(testUrl);
//        添加 Cookie 信息
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(this.store);
//        获取响应信息
        HttpResponse response = client.execute(get, context);
        String result = EntityUtils.toString(response.getEntity());

        System.out.println(result);

    }
}
