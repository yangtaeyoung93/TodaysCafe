<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Black+Han+Sans|Cabin+Sketch|Fredericka+the+Great|Love+Ya+Like+A+Sister&display=swap" rel="stylesheet">
</head>
<body>
<table>
   <tr>
   <td colspan="6" style="text-align: center; font-size: 30px; font-weight: bold;font-family:'Do Hyeon';">${vo.title }</td>
   </tr>
 <tr><td colspan="6"><hr></td></tr> 
   <tr>
   <td colspan="6" style="text-align: right; width: 20px;">
   <div style="display:inline; min-width:100%;">
   <div align="left" style="display:inline; float:left; width:20%;">
    <a onclick="location='/editor/list'"><img title="목록으로" src="img/back-arrow.png" style="width:20px"></a>&nbsp;
    <c:if test="${login_info.userid eq vo.writer }">
    	<a onclick="location='/editor/update?title=${vo.title}'"><img title="수정" src="img/eraser.png" style="width:20px"></a>&nbsp;
    	<a onclick="if(confirm('정말 삭제하시겠습니까?')){location='/editor/delete?title=${vo.title}'}"><img title="삭제" src="img/delete-button.png" style="width:20px"></a>&nbsp;
    </c:if>
   </div>
   <div style="display:inline; float:left; width:80%;">
   <img src="img/user.png" style="width:15px"> &nbsp;${vo.writer }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <img src="img/calendar.png" style="width:15px"> &nbsp;${vo.writedate }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <img src="img/eye.png" style="width:18px"> &nbsp;${vo.readcnt}
   </div>
   </div>
   </td>
   </tr>
<tr><td colspan="6"><hr></td></tr>   
   <tr>
   <td>
      <div class="slide">
      <img id="back" src="img/back.png" width="30px">
      <ul>
      <c:forEach items="${vo_f}" var="vo_f">
      <li><img style="width: 650px; height: 650px;" src="<c:url value='/'/>${vo_f.filepath }"/></li>
      </c:forEach>
      </ul>
      <img id="next" src="img/next.png" width="30px">
      </div>
   </td>
   <td>
      <div id="scroll" style="-ms-overflow-style: none; overflow:scroll; width:500px; height:650px; padding-left:20px; ">${fn: replace(vo.content,crlf,'<br>') }</div>
   </td>
   </tr>

<tr><td colspan="6"><hr></td></tr>
</table><br>
</body>
</html>