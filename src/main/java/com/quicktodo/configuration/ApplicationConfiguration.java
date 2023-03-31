package com.quicktodo.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.quicktodo.handling",
        "com.quicktodo.http.endpoint",
        "com.quicktodo.utility"
})
@EntityScan(basePackages = "com.quicktodo.persistence.entity")
@EnableJpaRepositories(basePackages = "com.quicktodo.persistence.repository")
@Import({
        BeansDefinitions.class,
        SecurityConfiguration.class
})
public class ApplicationConfiguration {
}
