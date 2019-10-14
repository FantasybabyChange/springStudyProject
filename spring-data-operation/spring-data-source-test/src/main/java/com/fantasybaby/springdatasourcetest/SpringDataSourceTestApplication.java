package com.fantasybaby.springdatasourcetest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class SpringDataSourceTestApplication implements CommandLineRunner {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(SpringDataSourceTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		showConnection();

		showData();
	}
	public void showConnection(){
		log.info("start showConnection----------------------");
		log.info(dataSource.toString());
		try {
			Connection connection = dataSource.getConnection();
			log.info(connection.toString());
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private void showData() {
		jdbcTemplate.queryForList("SELECT * FROM FOO")
				.forEach(row -> log.info(row.toString()));
	}
}
