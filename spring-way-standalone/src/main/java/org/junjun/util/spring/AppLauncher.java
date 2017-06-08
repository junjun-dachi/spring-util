package org.junjun.util.spring;

import org.apache.log4j.Logger;
import org.junjun.util.spring.config.AppConfig;
import org.junjun.util.spring.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppLauncher {

	private final static Logger logger = Logger.getLogger(AppLauncher.class);

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
