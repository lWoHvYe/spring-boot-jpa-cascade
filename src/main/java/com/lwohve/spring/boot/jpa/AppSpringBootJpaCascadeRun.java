package com.lwohve.spring.boot.jpa;

import me.zhengjie.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
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
