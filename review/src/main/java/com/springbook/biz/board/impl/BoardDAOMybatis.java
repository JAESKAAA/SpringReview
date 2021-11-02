package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis{

	@Autowired
	//SqlSessionTemplate 방식으로 의존성 주입
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 insertBoard() 기능 처리");
		mybatis.insert("boardDAO.insertBoard",vo);
	}
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 updateBoard() 기능 처리");
		mybatis.update("boardDAO.updateBoard",vo);
	}
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 deleteBoard() 기능 처리");
		mybatis.delete("boardDAO.deleteBoard",vo);
	}
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 getBoard() 기능 처리");
		return (BoardVO) mybatis.selectOne("boardDAO.getBoard",vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 getBoardList() 기능 처리");
		return mybatis.selectList("boardDAO.getBoardList",vo);
	}
}
