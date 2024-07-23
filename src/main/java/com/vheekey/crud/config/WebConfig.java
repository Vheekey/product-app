package com.vheekey.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //create a mediatype that returns response how a user wants it. Json or xml

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
                .parameterName("mediaType")
                .defaultContentType(MediaType.APPLICATION_JSON) //default application should accept and return json
                .mediaType("xml", MediaType.APPLICATION_XML) //favour xml when mediatype is application/xml
                .mediaType("json", MediaType.APPLICATION_JSON); //favour json when mediatype is application/json;

    }
}
