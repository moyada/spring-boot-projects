package com.xyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyk.service.CacheService;

@RestController
@RequestMapping("cache")
public class CacheController {
	
	@Autowired
	CacheService cacheService;
	
	@RequestMapping("set")
	public void setCache(final String key, final String value){
		cacheService.setValue(key, value);
	}
	
	@RequestMapping("get/{key}")
	public Object getCache(@PathVariable final String key){
		return cacheService.getValue(key);
	}
	
	@RequestMapping("clean")
	public void getCache(){
		cacheService.clean();
	}
}
