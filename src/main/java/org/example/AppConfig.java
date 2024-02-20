package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("org.example")
@Configuration
@PropertySource("classpath:application.properties.yaml")
public class AppConfig {
    @Bean
    public InitSavePaths simplePath() {
        return new InitSavePaths();
    }
}
