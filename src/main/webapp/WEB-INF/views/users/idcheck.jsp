<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idcheck</title>
<style>
.red { color : red; }

.green { color : green; }
</style>
</head>
<body>
	<h2>아이디 중복 확인</h2>
	<form action="/Users/DupCheck" method="get">
	<input type="text" name="userid" value="${param.userid}"/>
	<input type="submit" value="중복확인" /><br>
	<div id="msg">${ msg }</div>
	<input type="button" value="사용하기" id="btnClose" />
	
	</form>
	
	<script>
	// 새창이 열릴때
	document.addEventListener("DOMContentLoaded", function () {
		// 페이지를 처음 호출 했는가 체크
		// 방법 1
		if( '${first}' == 'true' ) {		
			const thisUseridEl = window.document.querySelector('[name="userid"]');
			const	parentUseridEl		=	window.opener.document.querySelector('[name="userid"]');
			thisUseridEl.value = parentUseridEl.value;
		} 
		
		
		// 위의 3줄을 풀면 문제가 생김, 입력한 값이 중복확인 새창으로 그대로 가져감, 만약 새로운 아이디를 사용하고자 하면 새로운 아이디에 대한
		// 사용가능 여부 확인은 가능하지만 기존의 값으로 돌아가는 현상이 생김
		//이를 해결하기 위해서는 아이디값이 처음입력한 것인지 알아야됨
	})
	
	// 사용하기 버튼을 클릭
	const	btnCloseEl = document.querySelector('#btnClose')
	btnCloseEl.addEventListener('click', function () {
		alert('ok')
		const	thisUseridEl			=	window.document.querySelector('[name=userid]');
		const	parentUseridEl		=	window.opener.document.querySelector('[name="userid"]');
		parentUseridEl.value	= thisUseridEl.value;
		window.close();
		
	})
	
	
	</script>
</body>
</html>