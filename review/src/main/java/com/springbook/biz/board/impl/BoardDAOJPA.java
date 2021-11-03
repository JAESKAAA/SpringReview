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
		System.out.println("==>JPA insertBoard �޼ҵ� ����");
		em.persist(vo);
	}
	public void updateBoard(BoardVO vo) {
		System.out.println("==>JPA updateBoard �޼ҵ� ����");
		em.merge(vo);
	}
	public void deleteBoard(BoardVO vo) {
		System.out.println("==>JPA deleteBoard �޼ҵ� ����");
		em.remove(em.find(BoardVO.class, vo.getSeq()));
	}
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("==>JPA getBoard �޼ҵ� ����");
		System.out.println("*****em.find�� ��ȯŸ�� -> "+ em.find(BoardVO.class, vo.getSeq()));
		return (BoardVO)em.find(BoardVO.class, vo.getSeq());
	}
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("==>JPA getBoardList�޼ҵ� ����");
		//createQuery�� �Ű������� JPQL�� �ް�, �� ����� getResultList�� ���� ���� �� ����
		//JPQL�� ������ ����� ��ü��, Entity�� ������ ��ü�� from���� ������� �����
		return em.createQuery("from BoardVO b order by b.seq desc").getResultList();
	}
}
