package ru.rushydro.vniig.ias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rushydro.vniig.ias.service.InterrogationService;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yazik on 12.03.2017.
 */
@Configuration
@Controller
@SpringBootApplication
@EnableScheduling
@EnableAsync
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class IASApplication {

    @Value("${bing3.exchange:false}")
    private boolean bing3Exchange;

    @RequestMapping("/")
    String home(Model model) {
        model.addAttribute("bing3Exchange", bing3Exchange);
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
