package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
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

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue", description = "更改用户姓名为 hahaha")
    public void updateUserName() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfo", 1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoCase);
        sleep(5000);
//        获取数据库用例中的期望值
//        重新获取sql 连接，让上一步的 sql 先完成请求
        User user = DatabaseUtil.getSqlSession().selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);


        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    @Test(dependsOnGroups = "loginTrue", description = "删除用户")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfo", 2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoCase);
        sleep(5000);
//        获取数据库用例中的期望值
//       重新获取sql 连接，让上一步的 sql 先完成请求
        User user = DatabaseUtil.getSqlSession().selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        System.out.println("+++++++++++++++" + user);

        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id", updateUserInfoCase.getUserId());
        param.put("userName", updateUserInfoCase.getUserName());
        param.put("sex", updateUserInfoCase.getSex());
        param.put("age", updateUserInfoCase.getAge());
        param.put("permission", updateUserInfoCase.getPermission());
        param.put("isDelete", updateUserInfoCase.getIsDelete());

        StringEntity entity = new StringEntity(param.toString(), "utf-8");

        post.setHeader("content-type", "application/json");
        post.setEntity(entity);

        TestConfig.context.setCookieStore(TestConfig.cookieStore);

        String result;
        HttpResponse response = TestConfig.httpClient.execute(post, TestConfig.context);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        return Integer.parseInt(result);
    }
}
