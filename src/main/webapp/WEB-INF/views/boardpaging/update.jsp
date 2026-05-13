<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardPagingUpdate</title>
<link href="/css/common.css" rel="stylesheet">
<link rel="shortcut icon" href="/img/favicon.png" type="image/x-icon">
<style>
	table {width:100%;}
	#table1 { width : 100%; 
		margin-bottom: 150px;
	}
	td {
		padding : 5px 10px;
		text-align : center;
		&:nth-of-type(1) {
			background : black;
			color : white;
			border : 1px solid white;
		}
	}
	tr:last-child > td {
	background : white;
	border : 1px solid black;
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
	
	textarea {
		width : 100%;
		height : 300px;
		
	}
</style>
</head>
<body>
	<main>
	<%@include file="/WEB-INF/include/menuspaging.jsp" %>
	
		<h2>${menu_name}게시글 수정</h2>
		<form action="/BoardPaging/Update" method="post">
		<input type="hidden" name="menu_id" value="${menu_id}" />
		<input type="hidden" name="nowpage" value="${nowpage}" />
		<input type="hidden" name="idx" value="${board.idx}" />
			<table id="table1">
				<tr>
					<td><span class='red'>*</span>제목</td>
					<td>
					<input type="text" name="title" value="${board.title }"/>
					</td>
				</tr>
				<tr>
					<td><span class='red'>*</span>작성자</td>
					<td><input type="text" name="writer" value="${sessionScope.login.userid}" readonly/></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" placeholder="${board.content }"></textarea></td>
				</tr>
				
				<tr>
					<td colspan="4">
						<input type="submit" value="수정"/>
						<input type="button" value="목록" 
						onclick ="window.location.href='/BoardPaging/List?menu_id=${menu_id}&nowpage=${nowpage}'"/>
					</td>
				</tr>
			</table>
		</form>	
	</main>
	
</body>
</html>