/* Copyright 2015 Code Avengers */

package org.abhishek.easysupport.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Initializes the Spring Root context.
 * 
 * @author abhishek
 * @since 1.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({
    "org.abhishek.easysupport"
})
public class SpringRootConfig {
    public SpringRootConfig() {
        System.out.println("Spring Root");
    }
}
