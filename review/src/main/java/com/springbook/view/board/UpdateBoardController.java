package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

public class UpdateBoardController implements Controller {

		@Override
		public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardVO vo = new BoardVO();
			BoardDAO boardDAO = new BoardDAO();
			
			vo.setSeq(Integer.parseInt(seq));
			vo.setTitle(title);
			vo.setContent(content);
			
			boardDAO.upadateBoard(vo);
			
		
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:getBoardList.do");

			return mav;
			
		}
}
