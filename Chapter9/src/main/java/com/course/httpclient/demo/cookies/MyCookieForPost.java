package com.course.httpclient.demo.cookies;



import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookieForPost {
    private String uri;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    private void beforeTest(){
        bundle = ResourceBundle.getBundle("application");
        this.uri = bundle.getString("test.url");
    }

    @Test
    private void getCookie() throws IOException {
        String result;
        String testUrl = this.uri + bundle.getString("test.getcookie");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet get = new HttpGet(testUrl);

        HttpResponse response = client.execute(get, context);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);

        this.store = context.getCookieStore();
        List<Cookie> cookieList = this.store.getCookies();
        for (Cookie cookie :
                cookieList) {
            System.out.println("name = " + cookie.getName() + "; value = " + cookie.getValue());
        }
    }

    @Test(dependsOnMethods = "getCookie")
    private void postWithCookie() throws IOException {
        String result;
        String testUrl = this.uri + bundle.getString("test.postwithcookie");

//        声明 client 对象， 进行方法的执行
        CloseableHttpClient client = HttpClients.createDefault();
//        声明 context 对象， 进行 ccookie 等参数的传递
        HttpClientContext context = HttpClientContext.create();
//        声明 post 方法
        HttpPost post = new HttpPost(testUrl);
//        设置 cookie 信息
        context.setCookieStore(this.store);

//        添加请求头
        post.setHeader("content-type","application/json");

//        添加 post 请求体参数
        JSONObject param = new JSONObject();
        param.put("name", "huhansan");
        param.put("age", "18");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

//        执行 post 方法
        HttpResponse response = client.execute(post, context);
//        获取响应结果
        result = EntityUtils.toString(response.getEntity());

        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串转化成为json对象
        JSONObject resultJson = new JSONObject(result);
        String message = resultJson.getString("huhansan");
        String status = resultJson.getString("status");

//        判断获取的返回结果是否符合预期
        Assert.assertEquals("success", message);
        Assert.assertEquals("1", status);

    }
}
