package com.springbook.biz.board.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOJPA {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("==>JPA insertBoard 메소드 실행");
		em.persist(vo);
	}
	public void updateBoard(BoardVO vo) {
		System.out.println("==>JPA updateBoard 메소드 실행");
		em.merge(vo);
	}
	public void deleteBoard(BoardVO vo) {
		System.out.println("==>JPA deleteBoard 메소드 실행");
		em.remove(em.find(BoardVO.class, vo.getSeq()));
	}
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("==>JPA getBoard 메소드 실행");
		System.out.println("*****em.find의 반환타입 -> "+ em.find(BoardVO.class, vo.getSeq()));
		return (BoardVO)em.find(BoardVO.class, vo.getSeq());
	}
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("==>JPA getBoardList메소드 실행");
		//createQuery는 매개변수로 JPQL을 받고, 그 결과를 getResultList를 통해 받을 수 있음
		//JPQL은 질의의 대상이 객체라서, Entity로 지정된 객체를 from절의 대상으로 사용함
		return em.createQuery("from BoardVO b order by b.seq desc").getResultList();
	}
}
