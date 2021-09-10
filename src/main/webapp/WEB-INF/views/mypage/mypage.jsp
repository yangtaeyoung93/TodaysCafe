<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <hr/>
    <c:if test="${!empty mypagelist}">
    <!-- 즐겨찾기 목록 여러건 이미지 -->
        <table style="margin:0 auto;">
            <c:forEach var="dto" items="${mypagelist}">
                <c:if test="${dto.no % 2 == 0 }">
                    <tr class= "row">
                        <td>
                            <a class="imgoverlay">
                                <img id="bookmark" src="<c:url value='/'/>${dto.filepath }"/>
                            </a>
                                <div class="cafeaddress" style="border: none 1px; background-color: white; opacity: 0.4; width:380px; height:70px;"></div>
                                <div class="cafeaddress" >
                                    <div class="cafe">${dto.address}</div>
                                    <div class="cafe">${dto.storename }</div>
                                </div>
                        </td>
                </c:if>
                <c:if test="${dto.no % 2 == 1 }">
                        <td>
                            <a class="imgoverlay">
                                <img id="bookmark" src="<c:url value='/'/>${dto.filepath }"/>
                            </a>
                                <div class="cafeaddress" style="border: none 1px; background-color: white; opacity: 0.4; width:380px; height:70px;"></div>
                                <div class="cafeaddress" >
                                    <div class="cafe" >${dto.address}</div>
                                    <div class="cafe">${dto.storename }</div>
                                </div>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty mypagelist }">
    <div style="font-size: 50px; text-align: center; margin-top: 50px;">즐겨찾기한 카페가 없다.</div>
    </c:if>
</body>
</html>