<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdate</title>
<link href="/css/common.css" rel="stylesheet">
<link rel="shortcut icon" href="/img/favicon.png" type="image/x-icon">
<style>
	table { width : 100%; }
	#table1 {margin-bottom : 150px;}
	td {
		padding : 5px 10px;
		text-align : center;
		&:nth-of-type(1) {
			background : black;
			color : white;
			border : 1px solid white;
		}
	}
	
	input[type=text], input[type=password], input[type=email] {
		width : 100%;
	}
	input[type=submit] {
	width:100px;
	}
	input[type=button] {
	width:100px;
	}
	input[name=userid] {
	width : 79%;
	}
	#table1 {
		td {
			&:nth-of-type(1) {width:150px; background-color: black; color:white;}
			&:nth-of-type(2) {width:150px; background-color: white; color:black;}
			&:nth-of-type(3) {width:150px; background-color: black; color:white; border-bottom : 1px solid white;}
			&:nth-of-type(4) {width:150px; background-color: white; color:black;}
		}
	}
	
	#table1 tr:nth-of-type(3) td:nth-of-type(2) {
		text-align: left;
	}
	
	#table1 tr:nth-of-type(4) td:nth-of-type(2) {
		height: 400px;
		text-align : left;
		vertical-align: baseline;
	}
	
	#table1 tr:last-of-type > td {
	background : white;
	border : 1px solid black;
	}
	
	textarea {
		width:100%;
		height:400px;
		padding:10px;
	}
	
	input[name="title"]
	, textarea {
		padding:5px;
	}
</style>
</head>
<body>
	<main>
	<%@include file="/WEB-INF/include/menus.jsp" %>
	
		<h2>${menu_name}게시글수정</h2>
		<!-- get, post 둘다 날아감 밑에줄 -->
		<form action="/Board/Update?idx=${board.idx}" method="post">
		<input type="hidden" name="menu_id" value="${menu_id}" />
			<table id="table1">
				<tr>
					<td>글번호</td>
					<td>${ board.idx }</td>
					<td>조회수</td>
					<td>${ board.hit }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${ board.writer }</td>
					<td>작성일</td>
					<td>${ board.regdate }</td>
				</tr>
				<tr>
					<td><span class='red'>*</span>제목</td>
					<td colspan="3">
					<input type="text" name="title" value="${board.title}" />
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3"><textarea name="content"> ${ board.content }</textarea></td>
				</tr>
				
				<tr>
					<td colspan="4">
						<input type="submit" value="수정"/>
						<input type="button" value="목록" 
						onclick ="window.location.href='/Board/List?menu_id=${menu_id}'"/>
					</td>
				</tr>
			</table>
		</form>	
	</main>
	<!-- Javascript 코딩 : client validation -->
	<script>
		
	</script>
	
</body>
</html>