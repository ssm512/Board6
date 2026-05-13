package com.green.paging.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDTO;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.SearchDTO;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	
	@Autowired
	private MenuMapper menuMapper;
		
	@Autowired
	private BoardPagingMapper boardPagingMapper;
	
	// /BoardPaging/List?menu_id=MENU01&nowpage=1 // searchType과 keyword는 null이 전달됨
	// /BoardPaging/List?menu_id=MENU01&nowpage=6&searchType=&keyword=	// searchType과 keyword는 ''(빈문자열)가 전달됨 
	@RequestMapping("/List")
	public ModelAndView list (BoardDTO boardDTO, int nowpage, String searchType, String keyword) {
		
		// 메뉴목록 : menus.jsp용
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 게시물 목록 조회(페이징해서)
		// 해당 메뉴의 자료수 갯수 구하는 코딩 필요
		int totalCount	= boardPagingMapper.count(boardDTO, searchType, keyword); // menu_id	
		// System.out.println("totalcount: " + totalcount);
		
			
		// 페이징을 위한 초기 설정
		SearchDTO	searchDTO	=	new	SearchDTO();
		searchDTO.setPageNo(nowpage); 		// 현재페이지 정보
		searchDTO.setNumOfRows(10);			// 한페이지에 출력될 자료수
		searchDTO.setPageSize(10);			// paging.jsp에 출력될 페이지 번호 수 : 처음 이전 1 2 3 .... 10 다음 마지막
		
		// Pagination 설정
		Pagination	pagination = new Pagination(totalCount, searchDTO);
		searchDTO.setPagination(pagination);
		
		int	offset	=	searchDTO.getOffset();
		int numOfRows	=	searchDTO.getNumOfRows();
				
		String menu_id	=	boardDTO.getMenu_id();
		
		//System.out.println("menu_id : " + menu_id);
		
		
		List<BoardDTO> list = boardPagingMapper.getBoardPagingList(
				menu_id, searchType, keyword, offset, numOfRows);
		
		ModelAndView mv	=	new	ModelAndView();
		mv.setViewName("boardpaging/list"); // .jsp 
		mv.addObject("menuList", menuList);
		mv.addObject("nowpage", nowpage);
		mv.addObject("menu_id", menu_id); // 현재 메뉴정보
		mv.addObject("bList", list);
		mv.addObject("searchDTO", searchDTO);
		mv.addObject("searchType", searchType);
		mv.addObject("keyword", keyword);
		
		return mv;
	}
	// /BoardPaging/View?idx=208&menu_id=MENU01&nowpage=1
		@RequestMapping("/View")
		public ModelAndView view (BoardDTO boardDTO, int nowpage) {
			
			// 전체 메뉴목록 : menus.jsp 용
			List<MenuDTO>  menuList =  menuMapper.getMenuList();
			
			// 조회수 증가
			boardPagingMapper.incHit(boardDTO);
			
			// idx로 게시글 한 개 조회
			BoardDTO board	=	boardPagingMapper.getBoard(boardDTO);
			
			// content안에 있는 엔터 \n를 <br>로 변경 -> content
			String		content	= board.getContent();
			if (content != null)
				board.setContent(content.replace("\n", "<br>"));
			
			String	menu_id	=	boardDTO.getMenu_id();
			
			ModelAndView   mv       =  new ModelAndView();
			mv.setViewName("boardpaging/view");
			mv.addObject("menuList", menuList);
			mv.addObject("board", board);
			mv.addObject("menu_id", menu_id);
			mv.addObject("nowpage", nowpage);
			
			return  mv;
		}
		
		// /BoardPaging/WriteForm?menu_id=MENU01&nowpage=1
		@RequestMapping("/WriteForm")
		public ModelAndView writeForm (BoardDTO boardDTO, int nowpage) {
			
			// 전체 메뉴목록 : menus.jsp 용
			List<MenuDTO>  menuList =  menuMapper.getMenuList();
			System.out.println("1:" + menuList);
			String menu_id		=	boardDTO.getMenu_id();
			
			// menu_name 넘겨주기
			String menu_name	=	menuMapper.getMenuName(menu_id);
			ModelAndView   mv       =  new ModelAndView();
			mv.setViewName("boardpaging/write");
			mv.addObject("menuList", menuList);
			mv.addObject("nowpage", nowpage);
			mv.addObject("menu_id", menu_id);
			mv.addObject("menu_name", menu_name);
						
			return mv;
		}
		
		// /BoardPaging/Write
		// 넘어온 값들
		// db저장 : menu_id=MENU01, title=제목, writer=admin, content=내용, nowpage=1
		// 돌아가기위해 필요한 변수 : menu_id=MENU01, nowpage=1
		@RequestMapping("/Write")
		public ModelAndView write (BoardDTO boardDTO, int nowpage) {
			System.out.println("boardDTO : " + boardDTO);
			
			// 전체 메뉴목록 : menus.jsp 용
			List<MenuDTO>  menuList =  menuMapper.getMenuList();
			
			//새글 저장 -> db 저장
			boardPagingMapper.insertBoard(boardDTO);
			
			// 목록으로 돌아가기
			String menu_id	= boardDTO.getMenu_id();
		    //String loc		=	"?menu_id=" + menu_id + "&nowpage=" + nowpage;
		    String fmt		=	"redirect:/BoardPaging/List?menu_id=%s&nowpage=%d";
			String loc		=	String.format(fmt, menu_id, nowpage);
			ModelAndView mv	=	new	ModelAndView();
			mv.setViewName(loc); // redirect를 붙이면 뒤의 주소로 이동해서 그 주소가 controller에 전달되었을때 수행하는 명령을 수행해라
			mv.addObject("menuList", menuList);
			return mv;
		}
		
		// /BoardPaging/Delete?idx=${board.idx}&menu_id=${board.menu_id}&nowpage=${nowpage}
		@RequestMapping("/Delete")
		public ModelAndView delete (BoardDTO boardDTO, int nowpage) {
			
			// 삭제하기
			boardPagingMapper.deleteBoard(boardDTO);
			
			String menu_id	=   boardDTO.getMenu_id();
			String fmt		=	"redirect:/BoardPaging/List?menu_id=%s&nowpage=%d";
			String loc		=	String.format(fmt, menu_id, nowpage);
			/*
			String loc		=	"""
					redirect:/BoardPaging/List?menu_id=%s&nowpage=%d
					""".formatted(menu_id, nowpage);
			*/
			ModelAndView mv	=	new	ModelAndView();
			mv.setViewName(loc);
			
			return mv;
		}
		
		// /BoardPaging/UpdateForm?idx=${board.idx}&menu_id=${board.menu_id}&nowpage=${nowpage}
		// /BoardPaging/UpdateForm?idx=1412&menu_id=MENU01&nowpage=1
		@RequestMapping("/UpdateForm")
		public ModelAndView updateForm( BoardDTO boardDTO, int nowpage) {
			// 전체 메뉴목록 : menus.jsp 용
			List<MenuDTO>  menuList =  menuMapper.getMenuList();
			
			// 수정할 페이지에 출력할 자료를 idx로 조회
			BoardDTO board = boardPagingMapper.getBoard(boardDTO);
			
			String menu_id	= boardDTO.getMenu_id();
			ModelAndView mv	=	new	ModelAndView();
			mv.setViewName("boardpaging/update");
			mv.addObject("menuList", menuList);
			mv.addObject("menu_id", menu_id);
			mv.addObject("nowpage", nowpage);
			mv.addObject("board", board);
			return mv;
		}
		
		// /BoardPaging/Update
		// 넘어온 데이터 : menu_id=MENU01, title=제목, writer=admin, content=내용, nowpage=1, 
		// db update : title, content
		// 넘겨줄 data : menu_id, nowpage
		@RequestMapping("/Update")
		public ModelAndView update (BoardDTO boardDTO, int nowpage) {
			
			// 넘어온 값으로 db 정보 수정
			boardPagingMapper.updateBoard(boardDTO);
			
			String menu_id	=   boardDTO.getMenu_id();
			String loc		=	"""
					redirect:/BoardPaging/List?menu_id=%s&nowpage=%d
					""".formatted(menu_id, nowpage);
			ModelAndView mv	=	new	ModelAndView();
			mv.setViewName(loc);
			return mv;
			
		}
}	
