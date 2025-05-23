/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayxu
 */
@Slf4j
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        log.info("{}", new Date());
    }

}
