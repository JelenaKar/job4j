package ru.job4j.parsing;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.JobBuilder.*;

/**
 * Класс - точка входа.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StartUI {

    private static final Logger LOG = LogManager.getLogger(StartUI.class.getName());

    public static void main(String[] args) {
        if (args.length < 1) {
            LOG.error("Укажите файл с конфигурациями!");
        } else {
            try (InputStream in = new FileInputStream(args[0])) {

                SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

                Scheduler sched = schedFact.getScheduler();

                sched.start();

                Properties config = new Properties();
                config.load(in);
                JobDetail job = newJob(SqlJob.class)
                        .withIdentity("parseVacancies", "default")
                        .usingJobData("properties", args[0])
                        .build();

                CronTrigger trigger = newTrigger()
                        .withIdentity("everyDayAt12", "default")
                        .withSchedule(cronSchedule(config.getProperty("cron.time")))
                        .forJob("parseVacancies", "default")
                        .build();

                sched.scheduleJob(job, trigger);

            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

}
