package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;
import com.springbook.view.controller.Controller;

public class LoginController implements Controller{

	//1. 
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//1. 요청정보 받아주기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		if(user != null) {
			return "getBoardList.do";
		} else {
			return "login";
		}
	
	}
}
