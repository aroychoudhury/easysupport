/* Copyright 2015 Code Avengers */

package org.abhishek.easysupport.spring.config;

import org.abhishek.fileanalytics.orchestrate.Orchestrater;
import org.abhishek.fileanalytics.orchestrate.impl.BasicLogFileOrchestrator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Initializes the Spring Root context.
 * 
 * @author abhishek
 * @since 1.0
 */
@Configuration
@ComponentScan(basePackages = {
    "org.abhishek.easysupport.service"
})
public class SpringRootConfig {
    public SpringRootConfig() {
        System.out.println("Spring Root");
    }

    @Bean(name = "orchestrater")
    public Orchestrater orchestrater() {
        return new BasicLogFileOrchestrator();
    }
}
