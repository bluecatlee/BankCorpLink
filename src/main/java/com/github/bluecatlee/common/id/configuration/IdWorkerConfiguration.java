package com.github.bluecatlee.common.id.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
@ConfigurationProperties(prefix = "machine")
@Data
public class IdWorkerConfiguration {

    private long id;

    @Bean
    @Description("Provides a single IdWorker")
    public IdWorker idWorker() {
        return new IdWorker(id);
    }

}
