package com.yc.tmwk.config;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


//@Configuration
public class ThymeleafConfig extends WebMvcConfigurationSupport implements ApplicationContextAware {

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addFormatter(dateFormatter());
    }

    @Bean
    public DateFormatter dateFormatter() {
        return new MyDateFormatter();
    }

    class MyDateFormatter extends DateFormatter{

        @Override
        public String print(Date date, Locale locale) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        }
    }

}
