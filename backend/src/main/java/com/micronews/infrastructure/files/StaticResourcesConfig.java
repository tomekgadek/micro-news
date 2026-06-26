package com.micronews.infrastructure.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {

    @Value("${app.media.directory:${user.home}/micro-news-static}")
    private String mediaDirectory;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = mediaDirectory;
        if (!location.startsWith("file:") && !location.startsWith("classpath:")) {
            location = "file:" + location;
        }
        if (!location.endsWith("/")) {
            location += "/";
        }

        registry.addResourceHandler("/static/**")
                .addResourceLocations(location)
                .setCachePeriod(3600);
    }
}
