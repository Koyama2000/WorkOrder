package com.kgc.work.workweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WorkWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkWebApplication.class, args);
    }

}
