<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/css/common.css" rel="stylesheet">
<style>
   table { width:100%;  }
   td {
      padding    : 5px;
      text-align : center;
   }
   #list {
	   td:nth-of-type(1)  {width:100px;}	
	   td:nth-of-type(2)  {width:300px;}	
	   td:nth-of-type(3)  {width:100px;}	
	   td:nth-of-type(4)  {width:100px;}	
	   td:nth-of-type(5)  {width:100px;}
   }
     
   tr:first-of-type {
      background-color: black;
      color :  white;      
      td  {
         border : 1px solid white;        
      } 
   }
   tr:nth-of-type(2) td {
	  text-align :right;
	  padding-right: 20px;
   }
   .title { text-align:left;  } 
 
   
   main {
      margin-bottom : 150px; 
   }
   
   .menu td > a.${menu_id} {	background-color: #04AA6D;  } 
</style>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<main>
		<%@include file="/WEB-INF/include/menus.jsp" %>
		
	  <h2 class=".h2">게시물 목록 ${menu.menu_name}</h2> <!-- menu라는 객체에서 menu_name을 뽑는겨, menu.getMenu_name -->
		<table id="list" class="table table-hover">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>게시날짜</td>
				<td>조회수</td>
			</tr>
			<tr>
				<td colspan="5">
					[<a href="/Board/WriteForm?menu_id=${menu_id}">새 게시글 등록</a>]&nbsp;&nbsp;&nbsp;
					[<a href="/">Home</a>]
				</td>
			</tr>
			
			<c:forEach var="board" items="${bList}">
			<tr>
				<td>${ board.idx }</td>			
				<td class="title"><a href="/Board/View?idx=${board.idx}&menu_id=${menu_id}">${ board.title }</a></td>			
				<td>${ board.writer }</td>		
				<td>${ board.regdate }</td>		
				<td>${ board.hit }</td>		
			</tr>
			</c:forEach>
		</table>
	</main>
</body>
</html>