package com.fantasybaby.spring.reactor.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

@SpringBootApplication
@Slf4j
public class SimpleReactorDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SimpleReactorDemoApplication.class, args);
	}
	private void fluxTest(){
		Flux.just("Hello", "World").subscribe(System.out::println);
		Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
		Flux.empty().subscribe(System.out::println);
//		Flux.range(1, 10).doOnComplete(()-> System.out.println("----------")).subscribe(System.out::println);
		Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).subscribe(a->{
			System.out.println(a);
		}, throwable -> System.out.println("error"));
		CountDownLatch latch = new CountDownLatch(1);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void fluxScheduler(){
		Flux.create(sink -> {
			sink.next(Thread.currentThread().getName());
			sink.complete();
		})
				.publishOn(Schedulers.single())
				.map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
				.publishOn(Schedulers.elastic())
				.map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
				.subscribeOn(Schedulers.parallel())
				.toStream()
				.forEach(System.out::println);
	}

	/**
	 * {@link reactor.util.concurrent.Queues} SMALL_BUFFER_SIZE 默认 2^8
	 * 当使用publishOn   request获得2^8 反之 是Long的最大值
	 */
	private void testPublisherAndSchedule(){
		Flux.range(1, 6)
				.publishOn(Schedulers.newElastic("publish on elastic"))
				.doOnRequest(n -> log.info("Request {} number", n)) // 注意顺序造成的区别

				.doOnComplete(() -> log.info("Publisher COMPLETE 1"))
				.map(i -> {
					log.info("Publish {}, {}", Thread.currentThread(), i);
//					return 10 / (i - 3);
					return i;
				})
				.publishOn(Schedulers.newElastic("publish on elastic2"))
				.map(i -> {
					log.info("Publish2 {}, {}", Thread.currentThread(), i+10);
//					return 10 / (i - 3);
					return i+10;
				})
				.doOnRequest(n -> log.info("Request1 {} number", n))
				.doOnComplete(() -> log.info("Publisher COMPLETE 2"))
				.subscribeOn(Schedulers.newSingle("subscribeOn"))
//				.onErrorResume(e -> {
//					log.error("Exception {}", e.toString());
//					return Mono.just(-1);
//				})
//				.onErrorReturn(-1)
				.subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
						e -> log.error("error {}", e.toString()),
						() -> log.info("Subscriber COMPLETE")
//						,s -> s.request(4)
				);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		fluxTest();
//		fluxScheduler();
		testPublisherAndSchedule();
//		Flux.range(1,6).doOnRequest(n -> log.info("Request {} number", n)).subscribe();

		Thread.sleep(2000);
	}
}

