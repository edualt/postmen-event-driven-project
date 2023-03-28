package com.school.eventdrivenproject.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AmazonSNSConfiguration {

    private String SNS_ACCESS_KEY = "AKIAZDVLAG2KQMTKCGK7";

    private String SNS_SECRET_KEY = "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";

    @Primary
    @Bean
    public AmazonSNSClient amazonSNSClient() {
        return (AmazonSNSClient) AmazonSNSClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        SNS_ACCESS_KEY,
                                        SNS_SECRET_KEY
                                )
                        )
                )
                .build();
    }
}

