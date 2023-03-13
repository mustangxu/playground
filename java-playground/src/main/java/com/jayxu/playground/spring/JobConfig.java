/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jayxu.playground.spring.job.SimpleJob;

/**
 * @author xujiajing
 */
@Configuration
public class JobConfig {
    @Bean
    JobDetail simpleJob() {
        return JobBuilder.newJob(SimpleJob.class).withIdentity("simpleJob")
            .storeDurably().build();
    }

    @Bean
    Trigger trigger() {
        var scheduler = SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInSeconds(30).repeatForever();

        return TriggerBuilder.newTrigger().withIdentity("trigger-30s")
            .forJob(this.simpleJob()).withSchedule(scheduler).build();
    }
}
