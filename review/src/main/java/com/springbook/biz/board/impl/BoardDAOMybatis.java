package com.springbook.biz.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.Criteria;

@Repository
public class BoardDAOMybatis{

	@Autowired
	//SqlSessionTemplate ������� ������ ����
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� insertBoard() ��� ó��");
		mybatis.insert("boardDAO.insertBoard",vo);
	}
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� updateBoard() ��� ó��");
		mybatis.update("boardDAO.updateBoard",vo);
	}
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� deleteBoard() ��� ó��");
		mybatis.delete("boardDAO.deleteBoard",vo);
	}
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� ���� getBoard() ��� ó��");
		return (BoardVO) mybatis.selectOne("boardDAO.getBoard",vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo, Criteria cri) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("board", vo);
		cri.setStartNum((cri.getPageNum() -1)*cri.getAmount());
		System.out.println("MyBatis���� Cri StartNum ������==>" + (cri.getPageNum() -1) * cri.getAmount());
		paramMap.put("criteria", cri);
		System.out.println("===> Mybatis�� ���� getBoardList() ��� ó��");
		//���� �־������ν� VO�� cri������ ���� ����� �� ����
		return mybatis.selectList("boardDAO.getBoardList",paramMap);
	}
	public int selectBoardCount(BoardVO vo) {
		return mybatis.selectOne("boardDAO.selectBoardCount", vo);
	}
}
