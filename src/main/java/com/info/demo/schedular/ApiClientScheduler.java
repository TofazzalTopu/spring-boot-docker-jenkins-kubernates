package com.info.demo.schedular;

import com.info.demo.processor.InstantCashAPIProcessor;
import com.info.demo.processor.RiaAPIProcessor;
import com.info.demo.service.common.ExchangeHousePropertyService;
import com.info.demo.util.ApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class ApiClientScheduler {

    public static final Logger logger = LoggerFactory.getLogger(ApiClientScheduler.class);

    private final RiaAPIProcessor riaAPIProcessor;
    private final InstantCashAPIProcessor instantCashAPIProcessor;
    private final ExchangeHousePropertyService exchangeHousePropertyService;

    public ApiClientScheduler(RiaAPIProcessor riaAPIProcessor, InstantCashAPIProcessor instantCashAPIProcessor, ExchangeHousePropertyService exchangeHousePropertyService) {
        this.riaAPIProcessor = riaAPIProcessor;
        this.instantCashAPIProcessor = instantCashAPIProcessor;
        this.exchangeHousePropertyService = exchangeHousePropertyService;
    }

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void apiClientScheduler() {
        logger.info("ICScheduler started.");
        ExecutorService instantCashExecutor = Executors.newFixedThreadPool(1);
        ExecutorService riaExecutor = Executors.newFixedThreadPool(1);

        try {
            logger.info("InstantCashAPIProcessor started");
            instantCashExecutor.execute(() -> instantCashAPIProcessor.process(ApiUtil.getICExchangeProperties()));
            logger.info("InstantCashAPIProcessor end");

            riaExecutor.execute(() -> {
//                riaAPIProcessor.process();
                System.out.println("5th");
                System.out.println("6th");
                System.out.println("7th");
            });

        } catch (Exception ex) {
            logger.error("Error in ApiClientScheduler: {}", ex.getMessage());
        }  finally {
            riaExecutor.shutdown();
            instantCashExecutor.shutdown();
        }
        logger.error("ICScheduler ended.");
    }

//    @Bean
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(2);
//        executor.setQueueCapacity(500);
//        executor.setThreadNamePrefix("IC");
//        executor.initialize();
//        return executor;
//    }

}
