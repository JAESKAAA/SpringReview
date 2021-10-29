package com.springbook.view.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//필요한 유틸들 선언
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver("./",".jsp");
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);

	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//1. 클라이언트의 요청 url 뽑아내기
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		
		//2. 핸들러 매핑클래스를 통해 해당 url에 해당하는 컨트롤러 검색해주기
		Controller ctrl = handlerMapping.getController(path);
		
		//3. 해당 컨트롤러 실행
		String viewName = ctrl.handleRequest(request, response);
		
		//4. ViewResolver 통해 viewName에 해당하는 화면 검색
		String view =null;
		
		if(!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		} else {
			view = viewName;
		}
		
		//5. 검색된 화면으로 이동시켜줌
		response.sendRedirect(view);
		
		
	}

}
