package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

public class LoginController implements Controller{

	//1. 
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//1. 요청정보 받아주기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		ModelAndView mav = new ModelAndView();
		
		if(user != null) {
			mav.setViewName("redirect:getBoardList.do"); 
		} else {
			mav.setViewName("redirect:login.jsp");
		}
		return mav;
	
	}
}
