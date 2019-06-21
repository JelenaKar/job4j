package ru.job4j.parsing;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.quartz.*;

/**
 * Класс - задание для выполнения Quartz.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class SqlJob implements Job {

    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobKey key = context.getJobDetail().getKey();
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String link = dataMap.getString("properties");

            try (SqlRuParser parser = new SqlRuParser(link)) {
                parser.parse();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
