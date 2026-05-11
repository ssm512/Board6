package com.green.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDTO;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private	MenuMapper menuMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	// /Board/List
	@RequestMapping("/List")
	// /Board/List?menu_id=MENU01
	public ModelAndView list (MenuDTO menuDTO) {
		// 메뉴 전체 목록 조회 - menus.jsp
		List<MenuDTO> menuList = menuMapper.getMenuList();
		log.info("menuList" + menuList);
		
		// 게시물 목록 조회 - list.jsp (menu_id=MENU01)
		List<BoardDTO> boardList = boardMapper.getBoardList(menuDTO);	
		log.info("boardList :" + boardList);
		
		// 넘어온 menu_id 동시에 List에 넘겨줄 menu_id ( List페이지에서 새글등록할때 menu_id필요함)
		String menu_id	=	menuDTO.getMenu_id();
		MenuDTO menu 	=	menuMapper.getMenu(menuDTO);
		
		ModelAndView	mv	=	new	ModelAndView();
		
		mv.setViewName("board/list");
		mv.addObject("menuList", menuList);
		mv.addObject("bList", boardList);
		mv.addObject("menu_id", menu_id); // 현재 메뉴 정보
		mv.addObject("menu", menu); // 현재 메뉴 전체 정보 (menu_id, menu_name, menu_seq)
		return mv;
	}
	
	// http://localhost:8080/Board/View?idx=2&menu_id=MENU02
	@RequestMapping("/View")
	public ModelAndView view (BoardDTO boardDTO) {
			
		// 전체 메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 해당 idx 게시글의 조회수를 1 증가
		boardMapper.incHit(boardDTO);
		
		// idx로 조회한 게시글
		BoardDTO	board = boardMapper.getBoard(boardDTO);
		//System.out.println("board : " + board);
		// board : boardDTO [idx=3, menu_id=MENU01, title=JAVA2, content=null, writer=java, regdate=2026-05-06, hit=0]
		
		// content안에 있는 엔터 \n를 <br>로 변경 -> content
		if (board != null && board.getContent() != null)
			board.setContent(board.getContent().replace("\n", "<br>"));
		
		
		
		ModelAndView	mv	=	new	ModelAndView();
		mv.setViewName("board/view");
		mv.addObject("menuList", menuList);
		mv.addObject("board", board); // 화면에 뿌려줄 한개의 게시글 정보를 담은거
		mv.addObject("menu_id", board.getMenu_id());
		return mv;
	}
	
	// /Board/WriteForm
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm( BoardDTO boardDTO ) {
		
		// 메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		String	menu_id		=	boardDTO.getMenu_id();
		String	menu_name	=	menuMapper.getMenuName(menu_id);
		System.out.println("writeform doardDTO" + boardDTO);
		ModelAndView mv		=	new	ModelAndView();
		mv.setViewName("board/write");
		mv.addObject("menu_id", menu_id);
		mv.addObject("menu_name", menu_name);		
		mv.addObject("menuList", menuList);
		return mv;
	}
	
	// /Board/Write?menu_id=MENU01&title=a&content=a&writer=a
	@RequestMapping("/Write")
	public ModelAndView write (BoardDTO boardDTO) {
				
		// 넘어오는 boardDTO 확인
		System.out.println("write boardDTO : " + boardDTO);
		// write boardDTO : BoardDTO(idx=0, menu_id=MENU01, title=a, content=aa, writer=aaa, regdate=null, hit=0)
		
		// db에 저장
		boardMapper.insertBoard(boardDTO);
		
		String menu_id	=	boardDTO.getMenu_id();
		// 페이지 이동
		ModelAndView mv	=	new	ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id="+menu_id);
		
		return mv;
	}
	
	// 게시물 삭제
	// idx=7 : 삭제할 글 번호
	// menu_id=MENU01 : 삭제후 돌아올 메뉴 정보
	// http://localhost:8080/Board/Delete?idx=7&menu_id=MENU01
	@RequestMapping("/Delete")
	public ModelAndView delete ( BoardDTO boardDTO ) {
		// db에서 idx에 해당하는 글 삭제
		boardMapper.deleteBoard(boardDTO);
		
		// menu_id의 해당 목록으로 돌아간다
		String menu_id	=	boardDTO.getMenu_id();
		ModelAndView mv	=	new	ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id="+menu_id);
		return mv;
	}
	
	// 게시물 수정 요청
	// Board/UpdateForm?idx=${board.idx}&menu_id=${board.menu_id}
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm(BoardDTO boardDTO) {
		
		// 전체 메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList(); 
		
		// 넘어온 데이터(idx)로 수정할 정보(board) 정보 조회
		BoardDTO board 		= boardMapper.getBoard(boardDTO);
		
		// 수정할 정보를 입력받는 페이지로 이동 : update.jsp
		String	menu_id 	= boardDTO.getMenu_id();
		String	menu_name	=	menuMapper.getMenuName(menu_id);
		ModelAndView mv		=	new	ModelAndView();
		mv.setViewName("board/update");
		mv.addObject("board", board);
		mv.addObject("menuList", menuList);
		mv.addObject("menu_id", menu_id);
		mv.addObject("menu_name", menu_name);
		return mv;
	}
	
	// board 수정
	// http://localhost:8080/Board/Update?idx=6
	// menu_id=MENU01, titel=", content=""
	@RequestMapping("/Update")
	public ModelAndView update( BoardDTO boardDTO ) {
			
		
		//넘어온 데이터로 db 수정
		boardMapper.updateBoard(boardDTO);
		
		// 수정후 목록 페이지로 이동
		ModelAndView mv	=	new	ModelAndView();
		String menu_id	=	boardDTO.getMenu_id();
		mv.setViewName("redirect:/Board/List?menu_id="+menu_id);
		return mv;
	}
	
}
