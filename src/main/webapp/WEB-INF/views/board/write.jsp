<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardWrite</title>
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
	<%@include file="/WEB-INF/include/menus.jsp" %>
	
		<h2>${menu_name}새글쓰기</h2>
		<form action="/Board/Write" method="post">
		<input type="hidden" name="menu_id" value="${menu_id}" />
			<table id="table1">
				<tr>
					<td><span class='red'>*</span>제목</td>
					<td>
					<input type="text" name="title"/>
					</td>
				</tr>
				<tr>
					<td><span class='red'>*</span>작성자</td>
					<td><input type="text" name="writer"/></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content"></textarea></td>
				</tr>
				
				<tr>
					<td colspan="4">
						<input type="submit" value="추가"/>
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