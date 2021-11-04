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
	public List<BoardVO> getBoardList(BoardVO vo, Criteria cri) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("board", vo);
		cri.setStartNum((cri.getPageNum() -1)*cri.getAmount());
		System.out.println("MyBatis에서 Cri StartNum 찍은거==>" + (cri.getPageNum() -1) * cri.getAmount());
		paramMap.put("criteria", cri);
		System.out.println("===> Mybatis를 통한 getBoardList() 기능 처리");
		//맵을 넣어줌으로써 VO와 cri정보를 같이 담아줄 수 있음
		return mybatis.selectList("boardDAO.getBoardList",paramMap);
	}
	public int selectBoardCount(BoardVO vo) {
		return mybatis.selectOne("boardDAO.selectBoardCount", vo);
	}
}
