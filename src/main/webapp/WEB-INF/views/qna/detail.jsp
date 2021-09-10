<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qna</title>
</head>
<body>
<br><h1>QNA</h1><br>
<table>
	<tr>
	<th align="left" colspan="5" style="font-size: 20px;">${vo.title }</th>
	</tr>
<tr><td colspan="6"><hr></td></tr>
	<tr >
	<td colspan="6" align="right">

<div style="display:inline; min-width:100%;">
	<div align="left" style="display:inline; float:left; width:20%;">
	<a class="btn-fill" onclick="$('#detail').submit()"><img id="list-back" title="목록으로" src="img/back-arrow.png" style="width:20px"></a>&nbsp;
	
	<c:if test="${login_info.userid eq vo.writer }">
		<a class="btn-fill" onclick="location='modify.qa?id=${vo.id}'"><img title="수정" src="img/eraser.png" style="width:20px"></a>&nbsp;
		<a class="btn-fill" onclick="if(confirm('정말 삭제하시겠습니까?')){location='delete.qa?id=${vo.id}'}"><img title="삭제" src="img/delete-button.png" style="width:20px"></a>&nbsp;
	</c:if>
	<a class="btn-fill" onclick="location='reply.qa?id=${vo.id}'"><img title="답글" src="img/re.png" style="width:20px"></a>
	</div>
	</div>
		<div style="display:inline; float:left; width:80%;">
	<img src="img/user.png" style="width:15px"> &nbsp;${vo.writer }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="img/calendar.png" style="width:15px"> &nbsp;${vo.writedate }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="img/eye.png" style="width:18px"> &nbsp;${vo.readcnt}
	</div>
	</td>
	</tr>
<tr><td colspan="6"><hr></td></tr>
	<tr>
	<c:if test="${!empty vo.filepath }">
	<td colspan="5" class="left">${fn: replace(vo.content,crlf,'<br>') }<br>
		<img style="max-width: 100%; height: auto;" src="<c:url value='/'/>${vo.filepath}"></td>
	</c:if>
	<c:if test="${empty vo.filepath }">
	<td colspan="5" class="left">${fn: replace(vo.content,crlf,'<br>') }<br>
	</c:if>
	</tr>
<tr><td colspan="6"><hr></td></tr>
</table><br>
<div>
	<form id="detail" method="post" action="list.qa">
		<input type="hidden" name="curPage" value="${page.curPage }" /> <input
			type="hidden" name="search" value="${page.search }" /> <input
			type="hidden" name="keyword" value="${page.keyword }" />
	</form>
</body>
</html>