package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	// http://localhost:8080
	@RequestMapping("/")
	public  String  home( ) {
		//System.out.println("첫페이지");
		return  "home";   // jsp 파일을 찾는다 // /WEB-INF/views/home.jsp
	}
	
	// http://localhost:8080/test
	@RequestMapping("/test")
	@ResponseBody              // 서버가 data(html) 을 내려보낸다
	public  String  test() {
		return  "<h2>Test 입니다</h2>";
	}
	
	/*
	 * // 클라이언트 부를때는 fetch()로 
	 * fetch()는 json에 특화되어있음
	 * private BoardMapper boardMapper;
	 * 
	 * @RequestMapping("/test2")
	 * @ResponseBody // 서버가 json을 내려보낸다
	 * public BoardDTO test2() { 
	 * List<BoardDTO> boardList = boardMapper.getBoardList(); 
	 * return "boardList"; }
	 */	
}






