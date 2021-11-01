package com.springbook.view.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
//"board"�� ����� ��ü�� ���ǿ��� ����ǰ� ��
public class BoardController {

	@Autowired
	private BoardService boardService;
	
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
	public String insertBoard(BoardVO vo) {
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	//�� ����
	@RequestMapping("/updateBoard.do")
	//@ModelAttribute�� "board"�� ����� ��üã�µ�, getBoard �޼ҵ忡�� "board"�� ��ü�� ������ ���� 
	//@SessionAttributes�� ���� ���ǿ��� "board"�� ����Ǿ��� ������,
	//@ModelAttribute���� �˻��� ������ ����
	public String updateBoard(@ModelAttribute("board")BoardVO vo) {
		System.out.println("�ۼ��� ���� :"+vo.getWriter());
		boardService.updateBoard(vo);
		return "redirect:getBoardList.do";
		
	}
	
	//�� ����
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	//�� �� ��ȸ
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		BoardVO board = boardService.getBoard(vo);
		model.addAttribute("board", board);
		return "getBoard.jsp";
	}
	
	//�� ��� ��ȸ
	@RequestMapping("/getBoardList.do")
	public String getBoardList(/*@RequestParam ��� ���� !
								 * @RequestParam(value="searchCondition", defaultValue="TITLE", required=false)
								 * String keyword,
								 */
								BoardVO vo, Model model) {
		List<BoardVO> boardList = (List<BoardVO>) boardService.getBoardList(vo);
		model.addAttribute("boardList",boardList);
		return "getBoardList.jsp";
		
	}
}
