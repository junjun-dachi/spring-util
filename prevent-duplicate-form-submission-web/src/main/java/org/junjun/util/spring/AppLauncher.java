package org.junjun.util.spring;

import org.apache.log4j.Logger;
import org.junjun.util.spring.server.EmbeddedTomcat;

public class AppLauncher {

	private static final Logger log = Logger.getLogger(AppLauncher.class);

	public static void main(String[] ars) {
		try {
			new EmbeddedTomcat().start();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

}
