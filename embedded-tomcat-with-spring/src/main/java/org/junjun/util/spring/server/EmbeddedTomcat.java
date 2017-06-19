package org.junjun.util.spring.server;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.Logger;
import org.junjun.util.spring.AppLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedTomcat {

	private static final Logger log = org.apache.log4j.Logger.getLogger(EmbeddedTomcat.class);

	@Value("${tomcat.web.content.folder.path:src/main/webapp/}")
	private String webContentFolder;

	@Value("${tomcat.web.base.folder.path:target/}")
	private String webBaseFolder;

	@Value("${tomcat.port:7070}")
	private String port;

	private Tomcat tomcat = new Tomcat();

	@PostConstruct
	public void start() throws Exception {

		System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
		tomcat.setPort(Integer.valueOf(port));
		tomcat.setBaseDir(webBaseFolder);
		StandardContext ctx = (StandardContext) tomcat.addWebapp("", webContentFolder);
		ctx.setParentClassLoader(AppLauncher.class.getClassLoader());
		tomcat.start();
		log.info("Tomcat Server Started at " + new Date());
		tomcat.getServer().await();

	}

	@PreDestroy
	public void stop() throws LifecycleException {

		tomcat.stop();
	}

}
