package org.junjun.util.spring.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	@Override
	public String echo(String message) {

		return "echo by test service : " + message;
	}

}
