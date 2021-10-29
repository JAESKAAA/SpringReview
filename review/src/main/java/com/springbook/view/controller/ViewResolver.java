package com.springbook.view.controller;

public class ViewResolver {
	private String prefix;
	private String suffix;
	
	//1. ���� �ʱ�ȭ
	public ViewResolver(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	//2. view ���� ��ȯ���ֱ�
	public String getView(String viewName) {
		return prefix+viewName+suffix;
	}
}
