package com.green.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDTO;

@Mapper
public interface BoardPagingMapper {

	int count(BoardDTO boardDTO, String searchType, String keyword);

	List<BoardDTO> getBoardPagingList(String menu_id, String searchType, String keyword,
			int offset, int numOfRows);

	BoardDTO getBoard(BoardDTO boardDTO);

	void insertBoard(BoardDTO boardDTO);

	void deleteBoard(BoardDTO boardDTO);

	void incHit(BoardDTO boardDTO);

	void updateBoard(BoardDTO boardDTO);
	
	
}
