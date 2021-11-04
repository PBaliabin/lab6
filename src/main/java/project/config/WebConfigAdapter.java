package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import project.resolver.TVJsonViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfigAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(true)
                .ignoreAcceptHeader(false)
                .useJaf(false)
                .defaultContentType(MediaType.TEXT_HTML)
                .mediaTypes(Map.of("json", MediaType.APPLICATION_JSON, "html", MediaType.TEXT_HTML));
    }

    @Bean
    @Autowired
    public ContentNegotiatingViewResolver viewResolver(ContentNegotiationManager cnManager, SpringTemplateEngine templateEngine) {

        ContentNegotiatingViewResolver cnResolver = new ContentNegotiatingViewResolver();
        cnResolver.setContentNegotiationManager(cnManager);

        // JSON View Resolver
        TVJsonViewResolver jsonResolver = new TVJsonViewResolver();

        ThymeleafViewResolver htmlResolver = new ThymeleafViewResolver();
        htmlResolver.setTemplateEngine(templateEngine);

        // Add views to list
        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(jsonResolver);
        resolvers.add(htmlResolver);

        cnResolver.setViewResolvers(resolvers);
        return cnResolver;
    }
}
