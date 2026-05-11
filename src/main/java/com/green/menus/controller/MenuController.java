package com.green.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

@Controller
public class MenuController {
	
	// @Autowired : spring container에 미리 new된 component를 찾아서 menuMapper 변수에 저장해라
	// @Autowired() - 객체 타입으로 찾아서 연결
	// @Qulified() - 객체 이름으로 찾아서 연결
	// 최근 기법은 생성자를 이용한 방식으로 변화
	@Autowired
	private  MenuMapper  menuMapper;
	
	// 메뉴 목록 조회  http://localhost:9090/Menus/List
	@RequestMapping("/Menus/List")   
	public   String   list( Model model ) {
		// 조회한결과를 ArrayList 로 돌려준다
		List<MenuDTO> menuList = menuMapper.getMenuList(); // Arraylist로 결과를 받는다
		System.out.println(menuList);
		
		model.addAttribute("msg","하하");
		model.addAttribute("menuList",menuList);
		
		return "menus/list";
	}
	
	// /Menus/WriteForm
	@RequestMapping("/Menus/WriteForm")
	public   String writeForm () {
		return	"menus/write"; // write.jsp로 이동
	}	
	
	// /Menus/Write
	// http://localhost:8080/Menus/Wirte?menu_id=MENU04&menu_name=%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8&menu_seq=4
	// public MenuDTO(MenuDRO menuDTO)
	@RequestMapping("/Menus/Write")
	public String Write (MenuDTO menuDTO, Model model) {
		// 넘어온 값
		System.out.println("넘어온값(menuDTO) : " + menuDTO);
		System.out.println("menu_id : " + menuDTO.getMenu_id());
		System.out.println("menu_name : " + menuDTO.getMenu_name());
		System.out.println("menu_seq : " + menuDTO.getMenu_seq());
			
		// db에 저장
		menuMapper.insertMenu(menuDTO);
		
		/*
		//다시 조회 -> menuList List<MenuDTO> menuList = menuMapper.getMenuList();
		model.addAttribute("menuList", menuList);
			
		return "menus/list";
		 */ // 밑에줄로 확 줄임
		
		return "redirect:/Menus/List";
	}

	
	// http://localhost:8080/Menus/Delete?menu_id=MENU01
	// delete방법2 
	@RequestMapping("/Menus/Delete")
	public String	delete (MenuDTO menuDTO) {
		System.out.println("삭제대상 : " + menuDTO); 
		// menu_id만 넘겨 받아도 MenuDTO로 받을 수 있음, menu_id, null, 0으로 MenuDTO에 전달됨

		// db에서 delete
		menuMapper.deleteMenu(menuDTO); // mybatis mapper에는 DTO를 전달한다 
		return "redirect:/Menus/List";
	}
		
	/*
	 * // http://localhost:8080/Menus/Delete?menu_id=MENU01
	 * // delete방법1 
	 * @RequestMapping("/Menus/Delete") public String delete (String menu_id) {
	 * System.out.println("삭제할 menu id : " + menu_id);
	 * 
	 * MenuDTO menuDTO = new MenuDTO (menu_id, null, 0); // db에서 delete
	 * menuMapper.deleteMenu(menuDTO); // mybatis mapper에는 DTO를 전달한다 
	 * return "redirect:/Menus/List"; }
	 */
	
	// http://localhost:8080/Menus/UpdateForm?menu_id=MENU09
	@RequestMapping("/Menus/UpdateForm")
	public String updateForm (MenuDTO menuDTO, Model model) {
		System.out.println("넘어온 정보(menuDTO) : " + menuDTO);
		// 수정할 자료를 db에서 검색 : 수정할 정보가 담긴 조회된 menu
		MenuDTO menu = menuMapper.getMenu(menuDTO);
		model.addAttribute("menu", menu);
		System.out.println("조회한 menuDTO : " + menu);
			
		return "/menus/update";
	}
	
	// http://localhost:8080/Menus/Update?menu_id=MENU09&menu_name=Kotlin&menu_seq=9
	@RequestMapping("/Menus/Update")
	public String Update (MenuDTO menuDTO) {
		// 넘어온 정보로 db를 수정한다
		menuMapper.updateMenu(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
	
	// /Menus/WriteForm2 - 메뉴이름으로만 추가하기
	@RequestMapping("/Menus/WriteForm2")
	public String	WriteForm2 () {
		
		return "menus/write2" ;
	}
	
	// /Menus/Write2?menu_name=C#
	@RequestMapping("/Menus/Write2")
	public String write2 (MenuDTO menuDTO) {
		
		menuMapper.insertMenu2(menuDTO);
		
		return "redirect:/Menus/List";
	}
}













