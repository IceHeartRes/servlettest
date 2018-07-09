package com.test.configuration;

import com.test.utils.PropertiesReader;
import org.flywaydb.core.Flyway;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;

@WebListener
public class FlywayListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        final Properties properties = PropertiesReader.getProperties();
        Flyway flyway = new Flyway();
        flyway.setDataSource(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
        flyway.setLocations("classpath:/db/migration");
        flyway.setBaselineOnMigrate(false);
        flyway.migrate();
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
