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
	
	// /BoardPaging/List?menu_id=MENU01&nowpage=1
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
		
		// 검색조건 추가
		// 추가된 검색조건
		String	title	=	boardDTO.getTitle();
		String	writer	=	boardDTO.getWriter();
		String	content	=	boardDTO.getContent();
		String menu_id	=	boardDTO.getMenu_id();
		
		System.out.println("menu_id : " + menu_id);
		
		int	offset	=	searchDTO.getOffset();
		int numOfRows	=	searchDTO.getNumOfRows();
		
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
	
	// /BoardPaging/WriteForm?menu_id=MENU01&nowpage=1 
}
