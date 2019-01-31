package com.wissensalt.rnd.sts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@ComponentScan("com.wissensalt.rnd.sts.web")
@SpringBootApplication
public class STSWebApplication {

    @Autowired
    private ThymeleafProperties properties;

    @Value("${spring.thymeleaf.templates_root}")
    private String templatesRoot;

    @Bean
    public ITemplateResolver defaultTemplateResolver() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());
        resolver.addTemplateAlias("content", "templates/content");
        resolver.addTemplateAlias("content-header", "templates/content/content-header");
        return resolver;
    }
    public static void main(String [] args) {
        SpringApplication.run(STSWebApplication.class);
    }
}
