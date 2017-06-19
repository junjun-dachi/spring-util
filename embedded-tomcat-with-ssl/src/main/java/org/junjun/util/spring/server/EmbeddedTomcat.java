package org.junjun.util.spring.server;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
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

	@Value("${tomcat.ssl.enabled}")
	private Boolean isSecured;

	@Value("${tomcat.ssl.port}")
	private String sslPort;

	@Value("${tomcat.ssl.keystore.file}")
	private String keyStoreFile;

	@Value("${tomcat.ssl.keystore.password}")
	private String keyStorePassword;

	@Value("${tomcat.ssl.keystore.key.alias}")
	private String keyAlias;

	// private String keyAlias;

	private Tomcat tomcat = new Tomcat();

	private void addSslConnector(Tomcat tomcat) {

		Connector con = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) con.getProtocolHandler();
		con.setPort(new Integer(sslPort));
		con.setSecure(Boolean.TRUE);
		con.setScheme("https");

		protocol.setSSLEnabled(Boolean.TRUE);
		protocol.setKeystoreFile(keyStoreFile);
		protocol.setKeystorePass(keyStorePassword);
		protocol.setKeyAlias(keyAlias);

		con.setAttribute("clientAuth", Boolean.FALSE);
		con.setAttribute("sslProtocol", "TLS");

		org.apache.catalina.Service service = tomcat.getService();
		service.addConnector(con);

	}

	private void enforceSecuredChannel(StandardContext context) {
		SecurityConstraint securityConstraint = new SecurityConstraint();
		securityConstraint.setUserConstraint("CONFIDENTIAL");
		SecurityCollection collection = new SecurityCollection();
		collection.addPattern("/*");
		securityConstraint.addCollection(collection);
		context.addConstraint(securityConstraint);
	}

	@PostConstruct
	public void start() throws Exception {

		System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
		tomcat.setPort(Integer.valueOf(port));
		tomcat.setBaseDir(webBaseFolder);
		addSslConnector(tomcat);
		StandardContext ctx = (StandardContext) tomcat.addWebapp("", webContentFolder);
		ctx.setParentClassLoader(AppLauncher.class.getClassLoader());

		enforceSecuredChannel(ctx);
		tomcat.getConnector().setRedirectPort(new Integer(sslPort));

		tomcat.start();
		log.info("Tomcat Server Started at " + new Date());
		tomcat.getServer().await();

	}

	@PreDestroy
	public void stop() throws LifecycleException {

		tomcat.stop();
	}

}
