package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})// memo :今はデータベースの自動設定を除外しているが必要なら外す
public class DemoApplication {

	public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

	}

}
