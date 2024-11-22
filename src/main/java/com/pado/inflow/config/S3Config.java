package com.pado.inflow.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String accessSecret;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.buckets.inflow-company}")
    private String inflowCompanyBucket;

    @Value("${cloud.aws.s3.buckets.inflow-contract}")
    private String inflowContractBucket;

    @Value("${cloud.aws.s3.buckets.inflow-vacation-attendance}")
    private String inflowVacationAttendanceBucket;

    @Bean
    public AmazonS3Client s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
        return (AmazonS3Client) AmazonS3Client.builder()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    // Getters for bucket names
    public String getInflowCompanyBucket() {
        return inflowCompanyBucket;
    }

    public String getInflowContractBucket() {
        return inflowContractBucket;
    }

    public String getInflowVacationAttendanceBucket() {
        return inflowVacationAttendanceBucket;
    }
}
