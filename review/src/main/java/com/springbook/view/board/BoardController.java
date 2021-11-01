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
//"board"로 선언된 객체가 세션에도 저장되게 됨
public class BoardController {

	//검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("제목", "TITLE" );
		conditionMap.put("내용", "CONTENT" );
		return conditionMap;
	}
	
	//글 등록
	@RequestMapping("/insertBoard.do")
	//기존 request에서 parameter 추출하는 방식 -> 커맨드객체 이용하여 VO에 컨테이너가 자동으로 파라미터를 셋팅해주면, 해당 VO를 사용하는 방식으로 변경
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		boardDAO.insertBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	//글 수정
	@RequestMapping("/updateBoard.do")
	//@ModelAttribute는 "board"로 선언된 객체찾는데, getBoard 메소드에서 "board"로 객체를 저장한 것이 
	//@SessionAttributes을 통해 세션에도 "board"로 저장되었기 때문에,
	//@ModelAttribute에서 검색이 가능한 것임
	public String updateBoard(@ModelAttribute("board")BoardVO vo, BoardDAO boardDAO) {
		System.out.println("작성자 정보 :"+vo.getWriter());
		boardDAO.upadateBoard(vo);
		return "redirect:getBoardList.do";
		
	}
	
	//글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		boardDAO.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	//글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		BoardVO board = boardDAO.getBoard(vo);
		model.addAttribute("board", board);
		return "getBoard.jsp";
	}
	
	//글 목록 조회
	@RequestMapping("/getBoardList.do")
	public String getBoardList(/*@RequestParam 사용 에시 !
								 * @RequestParam(value="searchCondition", defaultValue="TITLE", required=false)
								 * String keyword,
								 */
								BoardVO vo, BoardDAO boardDAO, Model model) {
		List<BoardVO> boardList = (List<BoardVO>) boardDAO.getBoardList(vo);
		model.addAttribute("boardList",boardList);
		return "getBoardList.jsp";
		
	}
}
