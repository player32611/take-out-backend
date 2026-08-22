package com.player32611.utils;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.StaticCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class AliOssUtil {

    private String endpoint;
    private String region;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 文件上传
     *
     * @param bytes      文件字节
     * @param objectName OSS 对象名称
     * @return 文件访问 URL
     */
    public String upload(byte[] bytes, String objectName) {

        CredentialsProvider provider =
                new StaticCredentialsProvider(
                        accessKeyId,
                        accessKeySecret
                );

        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .endpoint(endpoint);

        try (OSSClient client = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region)
                .endpoint(endpoint)
                .build()) {

            PutObjectRequest request = PutObjectRequest.newBuilder()
                    .bucket(bucketName)
                    .key(objectName)
                    .body(BinaryData.fromBytes(bytes))
                    .build();

            client.putObject(request);
        } catch (Exception e) {
            log.error("OSS 文件上传失败", e);
            throw new RuntimeException("文件上传失败", e);
        }

        String url = "https://"
                + bucketName
                + "."
                + endpoint
                + "/"
                + objectName;

        log.info("文件上传成功: {}", url);

        return url;
    }
}