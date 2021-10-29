package com.springbook.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.springbook.view.board.GetBoardListController;
import com.springbook.view.user.LoginController;

public class HandlerMapping {

	Map<String, Controller> mappings;
	
	public void setMappings() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
	}
	
	public Controller getController(String path) {
		System.out.println("핸들러에서 받는 path = " + path);
		System.out.println("핸들러에서 path에 따른 리턴값 = " + mappings.get(path));
		return mappings.get(path);
	}
}
