<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<br><h1>EDITOR REVIEW</h1><br><br>

<div style="display:inline; min-width: 100%">
<form method="get" action="/editor/list" id="list">
   <div style="display:inline; float:left; width: 95%; text-align:center;">
   <input type="text" value="${keyword }" name="keyword" style="vertical-align: top; width: 650px; height: 30px;" placeholder="검색어를 입력해주세요">
   <a class="btn-fill" onclick="$('form').submit()"><img src="img/magnifier.png" style="width:30px"></a>
   </div>

</form>
   <div style="display:inline;float:left; width: 5%; text-align:left;">
   <c:if test="${!empty login_info.userid}">
   		<a onclick="location='/editor/new'">글쓰기</a>
   	</c:if>
   </div>
</div>
<br><br>

<c:if test="${ empty vo}">
<div align="center" style="width: 100%; height: 500px; line-height: 500px; ">
<div style="width: 100%; height: 40px;" ><img src="img/caution-sign.png" style="width: 50px; height: 50px;"></div>
<div style="width: 100%; height: 50px; ">검색된 결과가 없습니다</div>
</div>
</c:if>

<div id="imagelist">
   <c:forEach items="${vo}" var="vo">
   <div id="image">
   <a onclick="location='/editor/detail?title=${vo.title}&read=1'"><img style="max-width: 350px; height: 350px;" src="<c:url value=''/>${vo.filepath }"/></a>
   </div>
   </c:forEach>
</div>
</body>
</html>