package com.honeacademy.webclientexample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class WebClientExampleApplication {

	@Bean(name = "devJdbc")
	public JdbcTemplate devJdbcTemplate(@Qualifier("dataSource") DataSource devDataSource) {
		return new JdbcTemplate(devDataSource);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebClientExampleApplication.class, args);
	}

}
