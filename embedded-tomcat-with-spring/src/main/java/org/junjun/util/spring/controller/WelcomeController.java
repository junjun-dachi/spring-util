package org.junjun.util.spring.controller;

import org.junjun.util.spring.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class WelcomeController {

	@Autowired
	private EchoService echoService;

	@RequestMapping(value = "/echo")
	public String test(WebRequest request, Model model) {
		model.addAttribute("message", echoService.execute("ECHO"));
		return "welcome";
	}

}
