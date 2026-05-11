package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDTO;
import com.green.menus.dto.MenuDTO;


@Mapper
public interface BoardMapper {

	List<BoardDTO> getBoardList(MenuDTO menuDTO);

	void deleteBoard(BoardDTO boardDTO);

	BoardDTO getBoard(BoardDTO boardDTO);

	void incHit(BoardDTO boardDTO);

	void insertBoard(BoardDTO boardDTO);

	void updateBoard(BoardDTO boardDTO);

}
