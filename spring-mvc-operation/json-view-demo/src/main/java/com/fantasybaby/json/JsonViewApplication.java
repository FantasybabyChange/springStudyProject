package com.fantasybaby.json;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * json视图应用程序
 *
 * @author Reid.Liu
 * @date 2021/11/10
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class JsonViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonViewApplication.class, args);
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /**
     * 打开缩进
     *
     * @return {@link Jackson2ObjectMapperBuilderCustomizer}
     */
    @Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> builder.indentOutput(true);
	}
}
