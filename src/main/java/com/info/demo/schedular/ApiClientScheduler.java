package com.info.demo.schedular;

import com.info.demo.processor.InstantCashAPIProcessor;
import com.info.demo.processor.RiaAPIProcessor;
import com.info.demo.service.common.ExchangeHousePropertyService;
import com.info.demo.util.ApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class ApiClientScheduler {

    public static final Logger logger = LoggerFactory.getLogger(ApiClientScheduler.class);

    @Autowired
    private InstantCashAPIProcessor instantCashAPIProcessor;

    @Autowired
    private RiaAPIProcessor riaAPIProcessor;

    @Autowired
    private ExchangeHousePropertyService exchangeHousePropertyService;


    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void apiClientScheduler() {
        System.out.println("ICScheduler started.");
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
            System.out.println(ex);
        }  finally {
            riaExecutor.shutdown();
            instantCashExecutor.shutdown();
        }
        System.out.println("ICScheduler ended.");
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
