package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
//"board"�� ����� ��ü�� ���ǿ��� ����ǰ� ��
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public BoardListVO dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
	
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
	public String insertBoard(BoardVO vo) throws IOException {
		//���� ���ε� ó��
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			//upload�� ���ϸ��� ���ڿ��� ����
			String fileName = uploadFile.getOriginalFilename();
			//���ε��� ������ �Ű������� ��ġ�� ����
			uploadFile.transferTo(new File("C:/Dev211/" + fileName));
		}
		System.out.println("ó���ƴ�");
		boardService.insertBoard(vo);
		return "getBoardList.do";
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
		//Null Check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		List<BoardVO> boardList = (List<BoardVO>) boardService.getBoardList(vo);
		model.addAttribute("boardList",boardList);
		return "getBoardList.jsp";
		
	}
	
	@RequestMapping(value = "/searchBoardList.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String searchBoardList(BoardVO vo) throws JsonProcessingException {
		System.out.println("�� ��� �˻� ó��");
		
		//�����͸� Json���� ��ȯ�������
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("******searchKeyword => " + vo.getSearchKeyword());
		
		//Null Check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		System.out.println("*******�ι�° keyword"+vo.getSearchKeyword());
		map.put("boardList", boardList);
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		System.out.println("****json => " + json);
		return json;
		
	}
}
