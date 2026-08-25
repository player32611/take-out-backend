package com.player32611.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http工具类
 */
@Slf4j
public class HttpClientUtil {

    static final int TIMEOUT_MSEC = 5 * 1000;

    public static String doGet(String url, Map<String, String> paramMap) {
        String result = "";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            URIBuilder builder = new URIBuilder(url);

            if (paramMap != null) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }

            URI uri = builder.build();

            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(builderRequestConfig());

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

                if (response.getStatusLine().getStatusCode() == 200) {
                    result = EntityUtils.toString(
                            response.getEntity(),
                            "UTF-8"
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String doPost(
            String url,
            Map<String, String> paramMap
    ) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(url);

            if (paramMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();

                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    paramList.add(
                            new BasicNameValuePair(
                                    param.getKey(),
                                    param.getValue()
                            )
                    );
                }

                UrlEncodedFormEntity entity =
                        new UrlEncodedFormEntity(paramList, "UTF-8");

                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            try (CloseableHttpResponse response =
                         httpClient.execute(httpPost)) {

                return EntityUtils.toString(
                        response.getEntity(),
                        "UTF-8"
                );
            }
        }
    }

    public static String doPost4Json(
            String url,
            Map<String, String> paramMap
    ) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(url);

            if (paramMap != null) {
                JSONObject jsonObject = new JSONObject();

                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    jsonObject.put(
                            param.getKey(),
                            param.getValue()
                    );
                }

                StringEntity entity = new StringEntity(
                        jsonObject.toString(),
                        "UTF-8"
                );

                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");

                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            try (CloseableHttpResponse response =
                         httpClient.execute(httpPost)) {

                return EntityUtils.toString(
                        response.getEntity(),
                        "UTF-8"
                );
            }
        }
    }

    private static RequestConfig builderRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_MSEC)
                .setConnectionRequestTimeout(TIMEOUT_MSEC)
                .setSocketTimeout(TIMEOUT_MSEC)
                .build();
    }
}
