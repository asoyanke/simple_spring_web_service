package org.ashok.simple_spring_web_service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

	private String result;
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/requestlistener")
	public ResponseEntity<String> requestlistener(@RequestBody String xmlString){
		
		logger.info(xmlString);
		
		System.out.println(xmlString);
		
		result = "1234";
		return new ResponseEntity(result, HttpStatus.ACCEPTED);
	}
}
