package com.springbook.view.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
//"board"�� ����� ��ü�� ���ǿ��� ����ǰ� ��
public class BoardController {

	//�˻� ���� ��� ����
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("����", "TITLE" );
		conditionMap.put("����", "CONTENT" );
		return conditionMap;
	}
	
	//�� ���
	@RequestMapping("/insertBoard.do")
	//���� request���� parameter �����ϴ� ��� -> Ŀ�ǵ尴ü �̿��Ͽ� VO�� �����̳ʰ� �ڵ����� �Ķ���͸� �������ָ�, �ش� VO�� ����ϴ� ������� ����
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		boardDAO.insertBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	//�� ����
	@RequestMapping("/updateBoard.do")
	//@ModelAttribute�� "board"�� ����� ��üã�µ�, getBoard �޼ҵ忡�� "board"�� ��ü�� ������ ���� 
	//@SessionAttributes�� ���� ���ǿ��� "board"�� ����Ǿ��� ������,
	//@ModelAttribute���� �˻��� ������ ����
	public String updateBoard(@ModelAttribute("board")BoardVO vo, BoardDAO boardDAO) {
		System.out.println("�ۼ��� ���� :"+vo.getWriter());
		boardDAO.upadateBoard(vo);
		return "redirect:getBoardList.do";
		
	}
	
	//�� ����
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		boardDAO.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	//�� �� ��ȸ
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		BoardVO board = boardDAO.getBoard(vo);
		model.addAttribute("board", board);
		return "getBoard.jsp";
	}
	
	//�� ��� ��ȸ
	@RequestMapping("/getBoardList.do")
	public String getBoardList(/*@RequestParam ��� ���� !
								 * @RequestParam(value="searchCondition", defaultValue="TITLE", required=false)
								 * String keyword,
								 */
								BoardVO vo, BoardDAO boardDAO, Model model) {
		List<BoardVO> boardList = (List<BoardVO>) boardDAO.getBoardList(vo);
		model.addAttribute("boardList",boardList);
		return "getBoardList.jsp";
		
	}
}
