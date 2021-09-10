<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reply</title>
</head>
<body>
<br><h1>QNA</h1><br><br><br>

<form action="reply_insert.qa" method="post" enctype="multipart/form-data">
<input type="hidden" name="writer" value="${ login_info.userid}"/>
	<table>
        <tr>
            <td><input type="text" class="need" title="제목" name="title" value="문의하신 질문에 대한 답변입니다:)" style="width: 100%; height: 25px; font-size: 20px;"/></td>
        </tr>
        <tr><td><br></td></tr>
        <tr>
            <td><textarea class="need" id="byteInfo" title="내용" style="width: 100%; height: 500px" name="content"></textarea></td>
        </tr>
        <tr>
            <td>
                <div id="file-upload" >
                <div style="float: left;display:inline; width: 3%"><img src="img/attach.png" style="width:25px">&nbsp;</div>
                    &nbsp;
                    <div style="float: left;display:inline; width: 10%"><label id="file-name">${vo.filename }</label></div>
                    <div style="float: left;display:inline; width: 10%">
                    <label>
                    <img title="파일추가" src="img/add-file.png" class="file-img" />
                    <input type="file" name="file" id="attach-file" multiple="multiple"/>
                    </label>
                    &nbsp;<img title="파일삭제" onclick="$('#attach').val('y')" src="img/cancel.png" class="file-img" id="delete-file">
                    </div>
                </div>
            </td>
        </tr>
	</table>
	<br>
	<div>
        <a class="btn-fill" onclick="if(necessary()){$('form').submit()}">저장</a>
        <a class="btn-empty" onclick="location='list.qa'">취소</a>
	    <input type="hidden" name="id" value="${vo.id }"/>
		<input type="hidden" name="root" value="${vo.root }"/>
		<input type="hidden" name="step" value="${vo.step}"/>
		<input type="hidden" name="indent" value="${vo.indent }"/>
	</div>
</form>
</body>
</html>