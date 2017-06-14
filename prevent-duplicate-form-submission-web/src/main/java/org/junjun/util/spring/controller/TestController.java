package org.junjun.util.spring.controller;

import org.apache.log4j.Logger;
import org.junjun.util.spring.token.annotations.CheckToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller

public class TestController {

	private static final Logger log = Logger.getLogger(TestController.class);

	@CheckToken
	@RequestMapping(value = "/test.htm", method = RequestMethod.POST)
	@ResponseBody
	public String test(WebRequest request) {
		String token = (String) request.getParameter("token");
		log.info(token);
		return token;
	}

	@RequestMapping(value = "/error.htm", method = RequestMethod.GET)
	@ResponseBody
	public String error(WebRequest request) {
		return "error";
	}

}
