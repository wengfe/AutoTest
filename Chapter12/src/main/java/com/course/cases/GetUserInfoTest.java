package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue", description = "获取用户信息")
    public void getUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        JSONArray resultJson = getJsonResult(getUserInfoCase);
//        获取数据库用例中的期望值
        User user = session.selectOne(getUserInfoCase.getExpected(), getUserInfoCase);
        List userList = new ArrayList();
        userList.add(user);
        JSONArray userArray = new JSONArray(userList);

//        将接口返回的数据提取格式与 userArray 一致
        JSONArray resultArray = new JSONArray(resultJson.getString(0));

        Assert.assertEquals(userArray.toString(), resultArray.toString());
    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("id", getUserInfoCase.getId());
//        param.put("userId", getUserInfoCase.getUserId());

        post.setHeader("content-type", "application/json");

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.context.setCookieStore(TestConfig.cookieStore);

        String result;
        HttpResponse response = TestConfig.httpClient.execute(post, TestConfig.context);

        result = EntityUtils.toString(response.getEntity(), "utf-8");
        log.info("GetUserInfoTest result 格式： " + result);
//        将 String转换为 List;
        List resultList = Arrays.asList(result);
        log.info("GetUserInfoTest resultList 格式： " + resultList);
//        将List 转为 JSON
        JSONArray resultJson = new JSONArray(resultList);
        log.info("GetUserInfoTest resultJson 格式： " + resultJson);
        return resultJson;
    }
}
