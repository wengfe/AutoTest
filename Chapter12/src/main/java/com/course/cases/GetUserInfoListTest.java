package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.ConfigFile;
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
import java.util.List;

@Log4j2
public class GetUserInfoListTest {
    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男用户列表")
    public void getUserListInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase", 1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        JSONArray resultJson = getJsonResult(getUserListCase);

        List<User> userList = session.selectList(getUserListCase.getExpected(), getUserListCase);
//        展示获取到的用户数据
        for (User u :
                userList) {
            log.info("获取到的用户数据： " + u.toString());
        }

        JSONArray userArray = new JSONArray(userList);
        Assert.assertEquals(userArray.length(), resultJson.length());
        for (int i = 0; i < resultJson.length(); i++) {
            JSONObject expect = userArray.getJSONObject(i);
            JSONObject actual = resultJson.getJSONObject(i);
            Assert.assertEquals(expect.toString(), actual.toString());
        }
    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);

        JSONObject param = new JSONObject();
        param.put("userName", getUserListCase.getUserName());
        param.put("sex", getUserListCase.getSex());
        param.put("age", getUserListCase.getAge());

        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        TestConfig.context.setCookieStore(TestConfig.cookieStore);
        String result;
        HttpResponse response = TestConfig.httpClient.execute(post, TestConfig.context);
        result = EntityUtils.toString(response.getEntity(), "utf-8");

        System.out.println(result);
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }
}
