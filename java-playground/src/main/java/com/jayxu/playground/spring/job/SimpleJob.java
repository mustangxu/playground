/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xujiajing
 */
@Slf4j
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        log.info("{}", new Date());
    }

}
