/* Copyright 2015 Code Avengers */

package org.abhishek.easysupport.spring.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Configures the Spring Web MVC.
 * 
 * @author abhishek
 * @since 1.0
 */
@EnableWebMvc
@Configuration
@ComponentScan({
    "org.abhishek.easysupport.api.rest.impl"
})
public class SpringWebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/docs/**").addResourceLocations("/docs/");
        registry.addResourceHandler("/presentation/**").addResourceLocations("/presentation/");
        registry.addResourceHandler("/metrics/**").addResourceLocations("/metrics/");
        registry.addResourceHandler("/index.html").addResourceLocations("/index.html");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Here we register the Hibernate4Module into an ObjectMapper, then set this
     * custom-configured ObjectMapper to the MessageConverter and return it to
     * be added to the HttpMessageConverters of our application
     */
    /**
     * @return An instance of the
     *         {@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}
     * @author abhishek
     * @since 1.0
     */
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        // Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate4Module());

        messageConverter.setObjectMapper(mapper);
        return messageConverter;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureMessageConverters(java.util.List)
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }
}
