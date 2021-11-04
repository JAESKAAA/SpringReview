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
//"board"로 선언된 객체가 세션에도 저장되게 됨
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
	public String insertBoard(BoardVO vo) throws IOException {
		//파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			//upload한 파일명을 문자열로 리턴
			String fileName = uploadFile.getOriginalFilename();
			//업로드한 파일을 매개변수의 위치에 저장
			uploadFile.transferTo(new File("C:/Dev211/" + fileName));
		}
		System.out.println("처리됐다");
		boardService.insertBoard(vo);
		return "getBoardList.do";
	}
	
	//글 수정
	@RequestMapping("/updateBoard.do")
	//@ModelAttribute는 "board"로 선언된 객체찾는데, getBoard 메소드에서 "board"로 객체를 저장한 것이 
	//@SessionAttributes을 통해 세션에도 "board"로 저장되었기 때문에,
	//@ModelAttribute에서 검색이 가능한 것임
	public String updateBoard(@ModelAttribute("board")BoardVO vo) {
		System.out.println("작성자 정보 :"+vo.getWriter());
		boardService.updateBoard(vo);
		return "redirect:getBoardList.do";
		
	}
	
	//글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	//글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		BoardVO board = boardService.getBoard(vo);
		model.addAttribute("board", board);
		return "getBoard.jsp";
	}
	
	//글 목록 조회
	@RequestMapping("/getBoardList.do")
	public String getBoardList(/*@RequestParam 사용 에시 !
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
		System.out.println("글 목록 검색 처리");
		
		//데이터를 Json으로 변환해줘야함
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("******searchKeyword => " + vo.getSearchKeyword());
		
		//Null Check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		System.out.println("*******두번째 keyword"+vo.getSearchKeyword());
		map.put("boardList", boardList);
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		System.out.println("****json => " + json);
		return json;
		
	}
}
