package ru.rushydro.vniig.ias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

/**
 * Created by yazik on 12.03.2017.
 */
@Configuration
@Controller
@SpringBootApplication
@EnableScheduling
@EnableGlobalMethodSecurity(securedEnabled = true)
public class IASApplication {

    @RequestMapping("/")
    String home() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(IASApplication.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public DataSourceProperties mainDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public DataSource mainDataSource() {
        return mainDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("app.datasource.exchange")
    public DataSourceProperties exchangeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.exchange")
    public DataSource exchangeDataSource() {
        return exchangeDataSourceProperties().initializeDataSourceBuilder().build();
    }

}
