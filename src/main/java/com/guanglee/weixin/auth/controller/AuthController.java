package com.guanglee.weixin.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	@RequestMapping("/403")
	public String noAuth() {
		return "403";
	}
}
