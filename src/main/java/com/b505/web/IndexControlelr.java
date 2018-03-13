package com.b505.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControlelr {
	
	@RequestMapping(value="/index.do")
	public String getIndex(){
		System.out.println("≤‚ ‘");
		return "1";
	}

}
