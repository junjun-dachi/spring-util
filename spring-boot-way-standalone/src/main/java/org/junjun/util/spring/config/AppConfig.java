package org.junjun.util.spring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = { "org.junjun.util.spring" })
@Configuration
@EnableAutoConfiguration
public class AppConfig {

}
