package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.Log4jAdvice;
import com.springbook.biz.common.LogAdvice;

@Service("boardService") //Ŭ���̾�Ʈ���� getBean���� �ش簪�� ã�� ������
/*
 * �������� ó���� ����ϴ� Ŭ����
 * ex) ������ü ��� ó���� �� DB�Է��� DAO �ϰ� �Ǵµ� 
 * 	   DB�Է� �� �ʿ��� �۾���(���¾�ȣȭ, �ݾ� �ĸ� �߰�...) �ϴ� �������� ServiceImplŬ�������� ó�����ָ� �� 
 * */
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAOSpring boardDAO;
	
	public void insertBoard(BoardVO vo) {
//		if(vo.getSeq()==0) {
//			throw new IllegalArgumentException("0�� ���� ����� �� �����ϴ�.");
//		}
//		boardDAO.insertBoard(vo);
		boardDAO.insertBoard(vo);
	}
	
	public void updateBoard(BoardVO vo) {
		boardDAO.upadateBoard(vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}
}