<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

</head>
<body>
   <br><h1>EDITOR REVIEW</h1><br><br>
<form action="/editor/insert" method="post" enctype="multipart/form-data">
<input type="hidden" name="writer" value="${ login_info.userid}"/>
   <table>
   <tr>
      <td><input type="text" class="need" title="제목" name="title" placeholder="제목" style="width:100%; height:25px; font-size: 20px;"/></td>
   </tr>
      <tr><td><br></td></tr>
   <tr>
      <td><textarea class="need" id="byteInfo" title="내용" style="width: 100%; height: 500px" name="content"></textarea></td>
   </tr>
   <tr>
      <td>
      <img src="img/attach.png" style="width:25px"> <input multiple="multiple" id="putinfile" type="file" name="file" />
            <img src="img/cancel.png" class="file-img" id="delete-file" style="width:20px">
      </td>
   </tr>
   </table><br>
   <div align="center">
   <a class="btn-fill" onclick="if(necessary()){$('form').submit()}">저장</a>
   <a class="btn-empty" onclick="location='/editor/list'">취소</a>
   </div>
</form>
</body>
</html>