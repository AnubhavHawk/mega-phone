package com.bbps.scheduler;

import com.bbps.scheduler.service.DataWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;


@SpringBootApplication
public class DataWriterApplication implements CommandLineRunner {
    @Autowired
    @Qualifier("inMemorySpringScheduler")
    TaskScheduler inMemorySpringScheduler;

    @Autowired
    DataWriterService dataWriter;

    public static void main(String[] args) {
        SpringApplication.run(DataWriterApplication.class, args);
    }

    @Bean
    TaskScheduler inMemorySpringScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(3);
        return scheduler;
    }

    @Override
    public void run(String... arg0) throws Exception {

//        Trigger trigger = new CronTrigger("0 59 11 1/1 * ?");
        Trigger trigger = new CronTrigger("0 0/1 * * * ?"); // 1 minute
        inMemorySpringScheduler.schedule(() -> {
            try {
                dataWriter.execute();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, trigger);

    }

}

