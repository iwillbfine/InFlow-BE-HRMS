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

    @Value("${cloud.aws.s3.buckets.inflow-emp-profile}")
    private String inflowEmpProfileBucket;

    @Value("${cloud.aws.s3.buckets.inflow-contract}")
    private String inflowContractBucket;

    @Value("${cloud.aws.s3.buckets.inflow-vacation-attendance}")
    private String inflowVacationAttendanceBucket;

    @Value("${cloud.aws.s3.buckets.inflow-excel-form}")
    private String inflowExcelFormBucket;

    @Bean
    public AmazonS3Client s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
        return (AmazonS3Client) AmazonS3Client.builder()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    //설명. 버킷들 이름 Getters
    //설명.1. 사원별 프로필 버킷
    public String getInflowEmpProfileBucket() {
        return inflowEmpProfileBucket;
    }

    //설명.2. 사원 계약서 버킷
    public String getInflowContractBucket() {
        return inflowContractBucket;
    }

    //설명.3. 근태 및 휴가 버킷
    public String getInflowVacationAttendanceBucket() {
        return inflowVacationAttendanceBucket;
    }

    //설명.4. 엑셀 양식 버킷
    public String getInflowExcelFormBucket() {return inflowExcelFormBucket;}

}
