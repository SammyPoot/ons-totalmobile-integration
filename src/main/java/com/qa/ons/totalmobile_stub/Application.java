package com.qa.ons.totalmobile_stub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(com.qa.ons.ui_csv_uploader.Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.debug("--Stub Application Started--");
    }
}
