package system.config;

import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Collection;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("system")
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").
                addResourceLocations("/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }

//    @Bean
//    public FreeMarkerViewResolver viewResolver() {
//        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
//        viewResolver.setSuffix(".html");
//        viewResolver.setContentType("text/html;charset=UTF-8");
//        viewResolver.setCache(false);
//        return viewResolver;
//    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCache(false);
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    private SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

//    @Bean
//    public FreeMarkerConfigurer freeMarkerConfigurer () {
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/templates");
//        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
//        freeMarkerConfigurer.setFreemarkerSettings(new Properties(){{
//            this.put("default_encoding", "UTF-8");
//        }});
//        return freeMarkerConfigurer;
//    }
//
//    @Bean
//    public CacheManager cacheManager() {
//        return new CacheManager() {
//            @Override
//            public Cache getCache(String name) {
//                return null;
//            }
//
//            @Override
//            public Collection<String> getCacheNames() {
//                return null;
//            }
//        };
//    }

}
