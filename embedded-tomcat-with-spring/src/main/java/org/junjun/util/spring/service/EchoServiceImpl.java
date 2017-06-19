package org.junjun.util.spring.service;

import org.springframework.stereotype.Component;

@Component
public class EchoServiceImpl implements EchoService {

	@Override
	public String execute(String message) {

		return message + " Powered By Embeded Tomcat !";
	}

}
