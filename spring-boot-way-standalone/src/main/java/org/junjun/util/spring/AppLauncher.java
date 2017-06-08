package org.junjun.util.spring;

import org.apache.log4j.Logger;
import org.junjun.util.spring.config.AppConfig;
import org.junjun.util.spring.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class AppLauncher {

	private final static Logger logger = Logger.getLogger(AppLauncher.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AppConfig.class);
		TestService service = ctx.getBean(TestService.class);
		logger.info(service.echo("standalone"));
	}
}
