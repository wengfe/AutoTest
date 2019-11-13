package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.servlet.http.Cookie;
import java.io.IOException;

@Log4j2
public class LoginTest {

    @BeforeTest(groups = "loginTrue", description = "测试准备工作")
    public void beforeTest(){
//        从 TestConfig 类中获取 测试url 变量，通过 ConfigFile 类中的方法拼接测试 url
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);

//        从 TestConfig 类中获取 httpClient 对象是为什么？
        TestConfig.httpClient = HttpClients.createDefault();
    }

    @Test(groups = "loginTrue", description = "用户登录成功接口测试")
    public void loginTrue() throws IOException {
//        从数据库工具类中获取 sqlSession 变量， 执行 sql
        SqlSession session = DatabaseUtil.getSqlSession();
//        selectOne 映射 databaseConfig.xml 文件resources/mapper/ 下的 sql 语句 login 为 sql id， 1 为参数
        LoginCase loginCase = session.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

//        第一步 发送请求
        String result = getResult(loginCase);
//        验证结果
        Assert.assertEquals(loginCase.getExpected(), result);

    }

    @Test(groups = "loginFalse", description = "用户登录失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        String result = getResult(loginCase);
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);

        HttpClientContext context = TestConfig.context;
        JSONObject param = new JSONObject();
        param.put("userName", loginCase.getUserName());
        param.put("password", loginCase.getPassword());

        post.setHeader("content-type", "application/json");

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.httpClient.execute(post, context);
        result = EntityUtils.toString(response.getEntity());

//        TestConfig 中的 cookieStore 赋值， 供其他用例调用

        TestConfig.cookieStore =context.getCookieStore();


        return result;
    }
}
