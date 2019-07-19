package com.bysj.controller;

import com.bysj.beans.User;
import com.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

//首页
@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/index", "/index.html" })
	public String index(ModelMap map, HttpSession session) {
		//ctrl+alt+v 自动补全返回值
		User user = userService.getById(1);
		System.out.println(user);
		map.addAttribute("user", user);
		return "index";
	}
}
