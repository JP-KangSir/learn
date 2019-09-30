package com.learning.system.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.system.feign.util.ApiReturnObject;

@RestController
public class FeignController {
	@Autowired
	DemoRemoteClient demoRemoteClient;
	
	@GetMapping("/remote/demo/getData/{uid}")
	public ApiReturnObject  basePath(@PathVariable String uid ,String data){
		return demoRemoteClient.getData(uid, data);
	}
}
