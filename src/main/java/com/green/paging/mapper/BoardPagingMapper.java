package com.green.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDTO;

@Mapper
public interface BoardPagingMapper {

	int count(BoardDTO boardDTO);

	List<BoardDTO> getBoardPagingList(String menu_id, String title, String writer, String content, int offset,
			int numOfRows);
	
	
	
}
