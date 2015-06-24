package org.openhims.oauth2.spring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController 
{
	@RequestMapping("/hello")
	 public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
	  
	   model.addAttribute("name", name);
	   //returns the view name
	   return "helloworld";

	 }
}
