package com.green.paging.dto;

import java.util.ArrayList;
import java.util.List;

import com.green.board.dto.BoardDTO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingResponse<T> {
	// 페이징된 실제의 모음 : numOfRows 10개
	private	List<T> list = new ArrayList<>();
	// 페이지와 관련 변수들의 모임
	private Pagination pagination;

	public PagingResponse(List<T> list, Pagination pagination) {
		this.list = list;
		this.pagination = pagination;
	}
	
	
}
