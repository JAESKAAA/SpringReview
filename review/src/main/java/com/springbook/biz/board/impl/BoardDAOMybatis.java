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
	//������̾ ���� ������ ���Թޱ�
	//applicationContext.xml�� �����س��� SqlSessionFactoryBean�� ������ �����̳ʰ� �Ű������� �ְ�,
	//�ش� ��ü�� �θ��� SqlSessionDaoSupport�� �ϱ� �޼ҵ带 ���� ������ �ִ� ��
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� insertBoard() ��� ó��");
		getSqlSession().insert("boardDAO.insertBoard",vo);
	}
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� updateBoard() ��� ó��");
		getSqlSession().update("boardDAO.updateBoard",vo);
	}
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� deleteBoard() ��� ó��");
		getSqlSession().delete("boardDAO.deleteBoard",vo);
	}
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� getBoard() ��� ó��");
		return (BoardVO) getSqlSession().selectOne("boardDAO.getBoard",vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� getBoardList() ��� ó��");
		return getSqlSession().selectList("boardDAO.getBoardList",vo);
	}
}
