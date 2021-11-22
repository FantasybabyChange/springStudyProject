package com.fantasybaby.spring.webflux.waiter;

import com.fantasybaby.spring.webflux.waiter.support.LocalDateTimeReadConverter;
import com.fantasybaby.spring.webflux.waiter.support.MoneyReadConverter;
import com.fantasybaby.spring.webflux.waiter.support.MoneyWriteConverter;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.util.Arrays;
import java.util.TimeZone;


/**
 * webflux演示应用程序
 *
 * @author Reid.Liu
 * @date 2021/11/22
 */
@SpringBootApplication
@EnableR2dbcRepositories
public class WebfluxDemoApplication extends AbstractR2dbcConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxDemoApplication.class, args);
    }

    @Override
    public ConnectionFactory connectionFactory() {
            return new H2ConnectionFactory(
                    H2ConnectionConfiguration.builder()
                            .inMemory("testdb")
                            .username("sa")
                            .build());
    }


    @Override
    public R2dbcCustomConversions r2dbcCustomConversions() {
        R2dbcDialect dialect = getDialect(connectionFactory());
        CustomConversions.StoreConversions storeConversions =
                CustomConversions.StoreConversions.of(dialect.getSimpleTypeHolder());
        return new R2dbcCustomConversions(storeConversions,
                Arrays.asList(new MoneyReadConverter(), new MoneyWriteConverter(),new LocalDateTimeReadConverter()));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
        return builder -> builder.indentOutput(true)
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
