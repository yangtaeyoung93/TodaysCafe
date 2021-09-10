<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

</style>
</head>
<body>
<h1>NOTICE</h1>
<script type="text/javascript">
</script>
<table >
   <tr>
   <th align="left" colspan="2" style="font-size: 20px;">${vo.title }</th>
   <td align="right">
   <a class="btn-fill" onclick="$('#detail').submit()"><img title="목록으로" src="img/back-arrow.png" style="width:20px"></a>&nbsp;
   <c:if test="${login_info.admin eq 'y'}">
   		<a class="btn-fill" onclick="location='modify.no?id=${vo.id}'"><img title="수정" src="img/eraser.png" style="width:20px"></a>&nbsp;
   		<a class="btn-fill" onclick="if(confirm('정말 삭제하시겠습니까?')){location='delete.no?id=${vo.id}'}"><img title="삭제" src="img/delete-button.png" style="width:20px"></a>
   </c:if>
   </td>
   </tr>
<tr><td colspan="3"><hr></td></tr>

   <tr>
<c:if test="${!empty vo.filepath }">
	<td colspan="5" class="left">${fn: replace(vo.content,crlf,'<br>') }<br>
		<img style="max-width: 100%; height: auto;" src="<c:url value='/'/>${vo.filepath}"></td>
	</c:if>
	<c:if test="${empty vo.filepath }">
	<td colspan="5" class="left">${fn: replace(vo.content,crlf,'<br>') }<br>
	</c:if>
   </tr>
<tr><td colspan="3"><hr></td></tr>
</table>
<br><br>
<table>
   <tr>
   <th align="left" style="width:10%">다음글</th>
   <td>
   <a onclick="location='detail.no?id=${vo.next_idx}&read=1'">${vo.nexttitle}<img src="img/top-arrow.png" style="width: 18px" align="right"></a>
   <c:if test="${vo.next_idx eq 0}">다음 글이 없습니다</c:if>
   </td>
   </tr>
   <tr>
   <th align="left">이전글</th>
   <td ><a onclick="location='detail.no?id=${vo.pre_idx}&read=1'">${vo.pretitle}<img src="img/down-arrow.png" style="width: 18px" align="right"></a>
   <c:if test="${vo.pre_idx eq 0}">이전 글이 없습니다</c:if></td>
   
   </tr>
</table>
<br><br>
   <form id="detail" method="post" action="list.no">
      <input type="hidden" name="curPage" value="${page.curPage }" /> <input
         type="hidden" name="search" value="${page.search }" /> <input
         type="hidden" name="keyword" value="${page.keyword }" />
   </form>
</body>
</html>