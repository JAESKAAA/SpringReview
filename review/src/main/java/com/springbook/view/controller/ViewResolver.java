package com.springbook.view.controller;

public class ViewResolver {
	private String prefix;
	private String suffix;
	
	//1. 변수 초기화
	public ViewResolver(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	//2. view 네임 반환해주기
	public String getView(String viewName) {
		return prefix+viewName+suffix;
	}
}
