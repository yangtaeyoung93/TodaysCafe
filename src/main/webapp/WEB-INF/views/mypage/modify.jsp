<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
<h3>회원 정보 수정</h3>
<form action="/mypage/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="email" value="${vo.email}"/>
<table>
	<tr>
		<th>프로필</th>
		<td>
		<c:if test="${!empty login_info.dbimgpath }">
			<img id="radius-box" alt="없음" src="<c:url value='/'/>${login_info.dbimgpath}">
		</c:if>
		<c:if test="${empty login_info.dbimgpath}">
			<img id="radius-box" alt="없음" src="img/base.png"/>
		</c:if>	
		</td>
		
		<td class="left">
			<a onclick="df()" id="delete-file" class="file-img">기본 이미지로 변경</a>
			<label><input type="file" name="file" id="attach-file"/></label>
		</td>
				
	</tr>

	<tr>
		<th>이메일</th><td class="left">${login_info.email} </td>
	</tr>
	<%-- <tr>
		<th style="width: 20%">비밀번호</th><td><input type="text" title="제목" 
		name="userpwd" style="width: 98.5%" class="need" value="${login_info.userpwd }"/> </td>
	</tr> --%>
	
	<tr align="center">
		<td colspan="2">
		<input type="hidden"  name="attach"  id="attach"/>
			<input type="submit" value="회원 정보 수정" id="usermodify"/>
			<a class="btn-empty" onclick="location='/mypage/detail?email=${login_info.email}'" id="cancel">수정 취소</a>
		</td>
		
	</tr>
</table>
</form>
</body>
</html>