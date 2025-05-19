package com.example.example1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/courses").setViewName("courses");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/contact").setViewName("contact");
//        registry.addViewController("/saveMsg").setViewName("contact");
    }
}
