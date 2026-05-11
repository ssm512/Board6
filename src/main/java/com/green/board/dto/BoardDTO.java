package com.green.board.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data // Getter,Setter,ToSting,HashCode,Equals,Constructor-BoardDTO()
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 인자 생성자
public class BoardDTO {
	//Field
	private int idx;
	private String menu_id;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private int	hit;
}
