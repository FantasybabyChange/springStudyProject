package com.fantasybaby.spring.data.mongodemo;

import com.fantasybaby.spring.data.mongodemo.converter.MoneyReadConverter;
import com.fantasybaby.spring.data.mongodemo.model.Coffee;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class MongoDemoApplication implements ApplicationRunner {
    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList((Converter<Document, Money>) source ->
        {
            Document money = (Document) source.get("money");
            double amount = Double.parseDouble(money.getString("amount"));
            String currency = ((Document) money.get("currency")).getString("code");
            return Money.of(CurrencyUnit.of(currency), amount);
        }));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Coffee> coffees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coffee espresso = Coffee.builder()
                    .name("espresso" + i)
                    .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                    .createTime(new Date())
                    .updateTime(new Date()).build();
            coffees.add(espresso);
        }
//		Collection<Coffee> coffees1 = mongoTemplate.insertAll(coffees);
//		log.info("Coffee {}", saved);

        List<Coffee> list = mongoTemplate.find(
                Query.query(Criteria.where("name").regex("espresso*")), Coffee.class);
        log.info("Find {} Coffee", list.size());
        list.forEach(c -> log.info("Coffee {}", c));

        Thread.sleep(1000); // 为了看更新时间
        UpdateResult result = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("espresso")),
                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30))
                        .currentDate("updateTime"),
                Coffee.class);
        log.info("Update Result: {}", result.getModifiedCount());
        Coffee updateOne = mongoTemplate.findById(list.get(0).getId(), Coffee.class);
        log.info("Update Result: {}", updateOne);

//		mongoTemplate.remove(updateOne);
    }
}

