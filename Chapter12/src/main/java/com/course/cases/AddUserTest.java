package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;


public class AddUserTest {
    @Test(dependsOnGroups = "loginTrue", description = "添加用户接口测试")
    public void addUser() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        com.course.model.AddUserCase addUserCase = session.selectOne("addUserCase", 1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);

//        发请求 获取结果
        String result = getResult(addUserCase);
        sleep(2000);
//       重新获取sql 连接，让上一步的 sql 先完成请求
        User user = DatabaseUtil.getSqlSession().selectOne("addUser", addUserCase);
//       验证返回结果
        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(), result);

    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
//        HttpClientContext context = new HttpClientContext(TestConfig.context);


        JSONObject param = new JSONObject();
        param.put("userName", addUserCase.getUserName());
        param.put("password", addUserCase.getPassword());
        param.put("age", addUserCase.getAge());
        param.put("sex", addUserCase.getSex());
        param.put("permission", addUserCase.getPermission());
        param.put("isDelete", addUserCase.getIsDelete());

        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

//        将  cookie 存入请求上下文中， TestConfig.cookieStore 中的 cookie 会在登录接口存入
        TestConfig.context.setCookieStore(TestConfig.cookieStore);

        String result;
        HttpResponse response = TestConfig.httpClient.execute(post, TestConfig.context);

        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        return result;
    }
}
