package com.junjun.util.spring;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.junjun.util.spring.config.AppConfig;
import com.junjun.util.spring.service.TestService;

public class Launcher {

	private final static Logger logger = Logger.getLogger(Launcher.class);

	public static void main(String[] args) {
		// use ClassPathXmlApplicationContext if your configuration is XML
		// instead of annotation based.
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		ctx.start();

		TestService service = ctx.getBean(TestService.class);
		logger.info(service.echo("standalone"));

		ctx.close();
		System.exit(0);
	}
}
