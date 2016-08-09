package com.park.oss.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("index");
		return view;
	}

/*	@RequestMapping(value="2",method=RequestMethod.GET)
	public ModelAndView tindex(){
		int i = 12 / 0;
		return new ModelAndView("index.btl");
	}*/
}
