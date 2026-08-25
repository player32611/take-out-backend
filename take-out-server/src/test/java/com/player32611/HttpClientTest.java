package com.player32611;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class HttpClientTest{

    @Test
    public void testGET() throws Exception{
        // 创建 httpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");

        // 发送请求，接受响应结果
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 获取服务端返回的状态码
        Integer statusCode = response.getStatusLine().getStatusCode();
        log.info("testGET 响应状态码: {}", statusCode);

        // 获取服务端返回的数据
        HttpEntity httpEntity = response.getEntity();
        String body = EntityUtils.toString(httpEntity);
        log.info("testGET 响应数据: {}", body);

        // 关闭资源
        response.close();
        httpClient.close();
    }

    @Test
    public void testPOST() throws Exception{
        // 创建 httpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");

        // 构造请求数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "test");
        jsonObject.put("password", "123456");
        StringEntity entity = new StringEntity(jsonObject.toString());

        // 设置数据格式
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");

        // 封装请求数据
        httpPost.setEntity(entity);

        // 发送请求，接受响应结果
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取服务端返回的状态码
        Integer statusCode = response.getStatusLine().getStatusCode();
        log.info("testPOST 响应状态码: {}", statusCode);

        // 获取服务端返回的数据
        HttpEntity httpEntity = response.getEntity();
        String body = EntityUtils.toString(httpEntity);
        log.info("testPOST 响应数据: {}", body);

        // 关闭资源
        response.close();
        httpClient.close();
    }
}
