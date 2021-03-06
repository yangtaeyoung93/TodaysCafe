<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.page_list{margin:0 auto; width: 60%;}
/* 글씨를 안보이게 하고 */
.page_on, .page_off, .page_next,.page_prev,.page_first,.page_last {display: inline-block; width: 30px; line-height: 28px;}

.page_on{border: 1px solid gray; font-weight: bold; color: #fff; background-color: gray;}

.page_next,.page_prev,.page_first,.page_last {border: 1px solid #d0d0d0; text-indent: -99999px;}

.page_next{background: url("img/page_next.jpg") center no-repeat;}
.page_prev{background: url("img/page_prev.jpg") center no-repeat;}
.page_first{background: url("img/page_first.jpg") center no-repeat;}
.page_last{background: url("img/page_last.jpg") center no-repeat;}
</style>
<!-- 게시글 하단  페이지 수 표시-->
<p class="page_list">
<!--          첫블럭인 경우 이전 블럭 이동 불가능          -->
<c:if test="${page.curBlock gt 1 }">
<!-- 이전 블럭의 첫페이지로 이동 ,-page.blockPage(-10): 보여지는 게시물의 갯수만큼 빼줌 -->
<!-- 처음으로: 첫블럭의 첫페이지로 이동          -->
<a class="page_first" title="처음" onclick="go_page(1)">처음</a>
<a class="page_prev" title="이전" onclick="go_page(${page.beginPage-page.blockPage})">이전</a>
<!-- 이전 블럭의 마지막 페이지로 이동 
<a class="page_prev" title="이전" onclick="go_page(${page.beginPage-1})">이전</a>-->
</c:if>
<c:forEach var="i" begin="${page.beginPage }" end="${page.endPage }">
<c:if test="${i eq page.curPage }">
   <span class="page_on">${i }</span>   
   </c:if>
<c:if test="${i ne page.curPage }">
   <a class="page_off" onclick="go_page(${i})">${i }</a>   
   </c:if>
</c:forEach>
<!-- 현재 블럭이 마지막 블럭이 아닌 경우 다음 블럭으로 이동 가능 -->
<c:if test="${page.curBlock lt page.totalBlock }">
<a class="page_next" title="다음" onclick="go_page(${page.endPage+1})">다음</a>
<a class="page_last" title="마지막" onclick="go_page(${page.totalPage})">마지막</a>
 </c:if>
</p>