package com.lwohvye;

import com.lwohvye.utils.SpringContextHolder;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;


@EnableAsync
@RestController
@Hidden
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableRetry //开启重试机制
//开启 @ConfigurationProperties 注解
@EnableConfigurationProperties
public class AppSpringBootJpaCascadeRun extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppSpringBootJpaCascadeRun.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppSpringBootJpaCascadeRun.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
