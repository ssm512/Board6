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
import com.green.paging.dto.PagingResponse;
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
			
			// idx로 게시글 한 개 조회
			BoardDTO board	=	boardPagingMapper.getBoard(boardDTO);
			String	menu_id	=	boardDTO.getMenu_id();
			
			ModelAndView   mv       =  new ModelAndView();
			mv.setViewName("boardpaging/view");
			mv.addObject("menuList", menuList);
			mv.addObject("board", board);
			mv.addObject("menu_id", menu_id);
			mv.addObject("nowpage", nowpage);
			
			return  mv;
		}
		
		// /BoardPaging/WriteForm?menu_id=${board.menu_id}&nowpage=${nowpage}
		@RequestMapping("/WriteForm")
		public ModelAndView writeForm (BoardDTO boardDTO, int nowpage) {
			
			// 전체 메뉴목록 : menus.jsp 용
			List<MenuDTO>  menuList =  menuMapper.getMenuList();
			
			String menu_id		=	boardDTO.getMenu_id();
			
			// menu_name 넘겨주기
			String menu_name	=	menuMapper.getMenuName(menu_id);
			ModelAndView   mv       =  new ModelAndView();
			mv.setViewName("boardpaging/write");
			mv.addObject("menuList", menuList);
			mv.addObject("nowpage", nowpage);
			mv.addObject("menu_id", menu_id);
			mv.addObject("boardDTO", boardDTO);
			mv.addObject("menu_name", menu_name);
			
			return mv;
		}
		
		// /BoardPaging/Write
		@RequestMapping("/Write")
		public ModelAndView write (BoardDTO boardDTO, String menu_id, int nowpage) {
			System.out.println("boardDTO : " + boardDTO);
			System.out.println("menu_id" + menu_id);
			System.out.println("nowpage : " + nowpage);
			// 전체 메뉴목록 : menus.jsp 용
			List<MenuDTO>  menuList =  menuMapper.getMenuList();
			
			boardPagingMapper.insertBoard(boardDTO);
			
		
			ModelAndView mv	=	new	ModelAndView();
			mv.setViewName("redirect:/BoardPaging/List");
			mv.addObject("menuList", menuList);
			mv.addObject("nowpage", nowpage);
			mv.addObject("menu_id", menu_id);
			return mv;
		}
		
		
		// /BoardPaging/UpdateForm?idx=${board.idx}&menu_id=${board.menu_id}&nowpage=${nowpage}
		
		// /BoardPaging/Delete?idx=${board.idx}&menu_id=${board.menu_id}&nowpage=${nowpage}
}
