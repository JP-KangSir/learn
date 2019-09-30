package com.learning.system.demo.controller;

import com.learning.system.demo.util.ApiReturnObject;
import com.learning.system.demo.util.ApiReturnUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping("/index")
	public ApiReturnObject index(String data){

		return ApiReturnUtil.success("data");
	}

}
