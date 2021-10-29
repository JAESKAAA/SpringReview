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

	//�ʿ��� ��ƿ�� ����
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
		
		//1. Ŭ���̾�Ʈ�� ��û url �̾Ƴ���
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		
		//2. �ڵ鷯 ����Ŭ������ ���� �ش� url�� �ش��ϴ� ��Ʈ�ѷ� �˻����ֱ�
		Controller ctrl = handlerMapping.getController(path);
		
		//3. �ش� ��Ʈ�ѷ� ����
		String viewName = ctrl.handleRequest(request, response);
		
		//4. ViewResolver ���� viewName�� �ش��ϴ� ȭ�� �˻�
		String view =null;
		
		if(!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		} else {
			view = viewName;
		}
		
		//5. �˻��� ȭ������ �̵�������
		response.sendRedirect(view);
		
		
	}

}
