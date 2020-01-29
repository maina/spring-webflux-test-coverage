package com.honeacademy.webclientexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class WebClientExampleApplication {

	@Bean(name = "devJdbc")
	public JdbcTemplate devJdbcTemplate(@Qualifier("dataSource") DataSource devDataSource) {
		return new JdbcTemplate(devDataSource);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebClientExampleApplication.class, args);
	}


	@Bean
	public RouterFunction<ServerResponse> imgRouter() {
		log.info(">>>initializing images router bean >>>");
		return RouterFunctions.resources("/images/**", new ClassPathResource("images/"));
	}

}
