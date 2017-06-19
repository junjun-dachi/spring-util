package org.junjun.util.spring.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "org.junjun.util.spring.server"})
@PropertySource("classpath:server.properties")
public class ServerConfig {

}
