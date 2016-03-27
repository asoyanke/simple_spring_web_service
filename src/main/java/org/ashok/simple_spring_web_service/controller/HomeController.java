package org.ashok.simple_spring_web_service.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
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
		
		xmlString = "Request Body : START \n" + xmlString + "\nEND"; 
		
		logger.info(xmlString);
		System.out.println(xmlString);
		
		result = xmlString;
		return new ResponseEntity(result, HttpStatus.ACCEPTED);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/readlogger")
	public ResponseEntity<String> readlogger(){
		
		String xmlString ="";
		
		FileAppender fa = (FileAppender) logger.getRootLogger().getAppender("file");
		xmlString = fa.getFile();
		result = readFile(xmlString);
		return new ResponseEntity(result, HttpStatus.ACCEPTED);
	}
	
/*	private String readFile(String filename)
	{
	    String content = null;
	    File file = new File(filename); //for ex foo.txt
	    FileReader reader = null;
	    try {
	        reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        content = new String(chars);
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if(reader !=null){try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
	    }
	    return content;
	}*/
	
	private String readFile(String pathname) {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());

	    Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    String lineSeparator = "\n";

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
	
}
