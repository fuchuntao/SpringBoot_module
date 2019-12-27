package com.javen.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTask {
    private int fixedDelayCount = 1;
    private int fixedRateCount = 1;
    private int initialDelayCount = 1;
    private int cronCount = 1;

    @Scheduled(fixedDelay = 5000)
    public void testFixDelay() {
        log.info("fixedDelay: 第{}次执行方法", fixedDelayCount++);
    }

    @Scheduled(fixedRate = 5000)
    public void testFixedRate() {
        log.info("fixedRate: 第{}次执行方法", fixedRateCount++);
    }

    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void testInitialDelay() {
        log.info("initialDelay: 第{}次执行方法", initialDelayCount++);
    }

    @Scheduled(cron = "0/15 * * * * ?")
    public void testCron() {
        log.info("cron: 第{}次执行方法", cronCount++);
    }
}
