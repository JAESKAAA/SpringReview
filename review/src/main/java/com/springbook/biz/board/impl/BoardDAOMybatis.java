package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis extends SqlSessionDaoSupport{

	@Autowired
	//오토와이어를 통한 의존성 주입받기
	//applicationContext.xml에 설정해놓은 SqlSessionFactoryBean을 스프링 컨테이너가 매개변수로 주고,
	//해당 객체를 부모인 SqlSessionDaoSupport에 하기 메소드를 통해 설정해 주는 것
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 insertBoard() 기능 처리");
		getSqlSession().insert("boardDAO.insertBoard",vo);
	}
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 updateBoard() 기능 처리");
		getSqlSession().update("boardDAO.updateBoard",vo);
	}
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 deleteBoard() 기능 처리");
		getSqlSession().delete("boardDAO.deleteBoard",vo);
	}
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 getBoard() 기능 처리");
		return (BoardVO) getSqlSession().selectOne("boardDAO.getBoard",vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Mybatis를 통한 getBoardList() 기능 처리");
		return getSqlSession().selectList("boardDAO.getBoardList",vo);
	}
}
