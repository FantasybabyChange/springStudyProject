package com.fantasybaby.reactive.mongodbdemo;

import com.fantasybaby.reactive.mongodbdemo.converter.MoneyReadConverter;
import com.fantasybaby.reactive.mongodbdemo.converter.MoneyWriteConverter;
import com.fantasybaby.reactive.mongodbdemo.model.Coffee;
import com.fantasybaby.reactive.mongodbdemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootApplication
@Slf4j
@EnableReactiveMongoRepositories
public class MongodbDemoApplication implements ApplicationRunner {
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	@Resource
	private CoffeeRepository coffeeRepository;
	private CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) {
		SpringApplication.run(MongodbDemoApplication.class, args);
	}

	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(
				Arrays.asList(new MoneyReadConverter(),
						new MoneyWriteConverter()));
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		startFromInsertion(() -> log.info("Runnable"));
		startFromInsertion(() -> {
			log.info("Runnable");
			decreaseHighPrice();
		});

		log.info("after starting");

//		decreaseHighPrice();

		cdl.await();
	}

	private void startFromInsertion(Runnable runnable) {

//		mongoTemplate.insertAll(initCoffee())
		coffeeRepository.saveAll(initCoffee())
				.publishOn(Schedulers.elastic())
				.doOnNext(c -> log.info("Next: {}", c))
				.doOnComplete(runnable)
				.doFinally(s -> {
					cdl.countDown();
					log.info("Finnally 1, {}", s);
				})
				.count()
				.subscribe(c -> log.info("Insert {} records", c));
	}

	private void decreaseHighPrice() {
//		reactiveMongoRepository.
		mongoTemplate.updateMulti(query(where("price").gte(3000L)),
				new Update().inc("price", -500L)
						.currentDate("updateTime"), Coffee.class)
				.doFinally(s -> {
					cdl.countDown();
					log.info("Finnally 2, {}", s);
				})
				.subscribe(r -> log.info("Result is {}", r));
	}

	private List<Coffee> initCoffee() {
		Coffee espresso = Coffee.builder()
				.name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();
		Coffee latte = Coffee.builder()
				.name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();

		return Arrays.asList(espresso, latte);
	}
}
