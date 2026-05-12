<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!-- core library에서 변수 선언하는 방법 -->
<c:set var="startnum" value="${searchDTO.pagination.startPage}" />
<c:set var="endnum" value="${searchDTO.pagination.endPage}" />
<c:set var="totalPageCount" value="${searchDTO.pagination.totalPageCount}" />
<div id="paging">
	<table>
		<tr>
		<!-- 처음과 이전 -->
			<c:if test="${startnum gt 1 }">
				<td>
					<a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=1&searchType=${searchType}&keyword=${keyword}"> 처음 </a>
				</td>
				<td>
					<a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${startnum-1}&searchType=${searchType}&keyword=${keyword}"> 이전 </a>
				</td>
			</c:if>
			
			<c:forEach var="pagenum" begin="${startnum }" end="${endnum }" step="1" >
				<c:if test="${pagenum le totalPageCount }">
					<td>
						<a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${pagenum}&searchType=${searchType}&keyword=${keyword}">
							${ pagenum }
						</a>
					</td>
				</c:if>
			</c:forEach>
			<!-- 다음과 마지막 -->
			<c:if test="${endnum lt totalPageCount }">
				<td>
					<a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${endnum+1}&searchType=${searchType}&keyword=${keyword}"> 다음 </a>
				</td>
				<td>
					<a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${totalPageCount}&searchType=${searchType}&keyword=${keyword}"> 마지막 </a>
				</td>
			</c:if>
		</tr>
	</table>
</div>