<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br><h1>NOTICE</h1><br>
<div style="display:inline; min-width: 100%;" align="center">
<form method="post" action="list.no" id="list">
   <div style="display:inline; float:left; width: 75%;" align="right">
   <select name="search" style="height: 28px; width: 92px;">
            <option value="all" ${page.search eq 'all'?'selected':'' }>전체</option>
            <option value="title" ${page.search eq 'title'?'selected':'' }>제목</option>
            <option value="content" ${page.search eq 'content'?'selected':'' }>내용</option>
         </select>
         <input type="text" value="${page.keyword }" name="keyword" style="vertical-align: top; width: 350px; height: 22px;">
         <a onclick="$('form').submit()"><img src="img/magnifier.png" style="width:20px"></a>
   </div>
   <div style="display:inline;float:left; width: 25%; text-align:left; ">
   <c:if test="${login_info.admin eq 'y'}">
 	  <a onclick="location='new.no'">&nbsp;&nbsp;&nbsp;&nbsp;글쓰기</a>
   </c:if>
   </div>
<input type="hidden" name="curPage"/>
</form>
</div>
<br>
<table>
<tr><td colspan="5"><hr></td></tr>
<tr>
   <th style="width: 50px;">NO</th>
   <th>제목</th>
   <th style="width: 100px;">작성일</th>
   <th style="width: 50px;">조회수</th>
</tr>
<tr><td colspan="5"><hr></td></tr>
<c:if test="${ empty page.list}">
<tr style="height: 500px;">
<td colspan="5" align="center"><img src="img/caution-sign.png" style="width: 50px; height: 50px;"><br>검색결과가 없습니다</td>
</tr>
</c:if>
<c:forEach items="${page.list}" var="vo">
   <tr><td align="center">${vo.no }</td>
         <td>
            <a onclick="location='detail.no?id=${vo.id}&read=1'">${vo.title }</a>
         </td>
         <td align="center">${vo.writedate}</td>
         <td align="center">${vo.readcnt }</td>
   </tr>
   </c:forEach>
<tr><td colspan="5"><hr></td></tr>
</table>
<jsp:include page="/WEB-INF/views/include/page.jsp"/>

<script type="text/javascript">

</script>
</body>
</html>