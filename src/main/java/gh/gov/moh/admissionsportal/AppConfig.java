package gh.gov.moh.admissionsportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by molayodecker on 20/01/2017.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "gh.gov.moh.admissionsportal")

public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class,args);
    }
}
