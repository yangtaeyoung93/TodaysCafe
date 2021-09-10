<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>오 늘  카 페</title>
</head>
<body id="lbody">

   <section class="container">
      <article class="half">
         <h1 style="color: black;">회원 정보</h1>
         <div class="content">
                        
            <div class="signup-cont cont">
               <form action="update.my" method="post" enctype="multipart/form-data" onsubmit="return fnCheck()">
                  <input type="hidden" name="admin" value="n" />
                  <input type="hidden" name="authstatus" value="1" />
                  
                  
                  <div align="center" style="margin-bottom: 1%;">
                     <label  id="profile_mouse">
                        <c:if test="${!empty login_info.dbimgpath }">
			<img id="radius-box" alt="없음" src="<c:url value='/'/>${login_info.dbimgpath}">
		</c:if>
		<c:if test="${empty login_info.dbimgpath}">
			<img id="radius-box" alt="없음" src="img/base.png"/>
		</c:if>	
                        <input style="visibility: hidden;" type="file" name="file"  id="attach-file" />
                     </label>
                  </div> 
                  
                  <input type="hidden" name="email" id="email_join" class="inpt" value="${login_info.email}">
                  <div align="center" style="font-size: 25px;">${login_info.email}</div><br>
                  <div id="email_status" class="status"></div>
                  <input type="password" name="userpwd" id="userpwd_join" class="inpt"   placeholder="Your password">
                  <div id="pwd_status" class="status"></div>
                  <input type="password" name="pwd_check" id="userpwd_ck"    class="inpt" placeholder="Your password check">
                  <div id="pwd_ck_status" class="status"></div>
					
				  <div>
				  	<input style="position: absolute; top: 350px;" type="button" value="My page" class="submit"  id="sign"
				  	 onclick="location='mypage?email=${login_info.email}'" />
				  </div>	                
                  <div class="submit-wrap">
                	    
                     <input type="submit" value="수정하기" class="submit"  id="sign" />
                  </div>
                  <div>
				  	<input style="position: absolute; top: 445px;" type="button" value="회원 탈퇴" class="submit"  id="sign"
				  	 onclick="if(confirm('정말 탈퇴 하겠습니까? 탈퇴 하셔도 활동 하는동안 남기신 기록들은 유지 됩니다.')){location='delete.my?email=${login_info.email}'}" />
				  </div>	       
               </form>
            </div>
         </div>
      </article>
      <div class="half bg"></div>
   </section>

  
</body>
</html>