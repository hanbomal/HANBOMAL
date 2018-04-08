<%@page import="model.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="../api/fullcalendar-3.9.0/lib/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- content -->

<div class="w3-container " style="height:600px; overflow:auto; ">
 <div class="w3-container"><div class="w3-bar w3-padding ">
</div></div>
<b>글내용 보기</b>
	<div class="container">
		<table class="w3-table-all" style="width: 80%;">
			<tr height="30">
				<td width="125" align="center">글번호</td>
				<td width="125" align="center">${article.num}</td>
				<td width="125">조회수</td>
				<td width="125" align="center">${article.readcount}</td>
			</tr>
			<tr height="30">
				<td width="125">작성자</td>
				<td width="125" align="center">${article.writer}</td>
				<td width="125" align="center">작성일</td>
				<td align="center" width="125" align="center">${article.reg_date}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125">글제목</td>
				<td align="center" width="375" colspan="3">${article.subject}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125">글내용</td>
				<td align="left" width="375" colspan="3"><pre>${article.content}</pre></td>
			</tr>
			<tr height="30">
				<td colspan="4" class="w3-center"><input type="button"
					value="글수정"
					onclick="document.location.href='updateForm?num=${article.num}&pageNum=${pageNum}'">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글삭제"
					onclick="document.location.href='deleteForm?num=${article.num}&pageNum=${article.num}'">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="답글쓰기"
					onclick="document.location.href='writeForm?num=${num}&ref=${ref}&re_step=${re_step}&re_level=${re_level}&pageNum=${pageNum}'">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글목록"
					onclick="document.location.href='list?pageNum=${pageNum}'">
				</td>
			</tr>
		</table> 
	</div> 

      </div>

	<%-- <br>
	<br>
	<b>글내용 보기</b>
	<div class="container">
		<table class="w3-table-all" style="width: 80%;">
			<tr height="30">
				<td width="125" align="center">글번호</td>
				<td width="125" align="center">${article.num}</td>
				<td width="125">조회수</td>
				<td width="125" align="center">${article.readcount}</td>
			</tr>
			<tr height="30">
				<td width="125">작성자</td>
				<td width="125" align="center">${article.writer}</td>
				<td width="125" align="center">작성일</td>
				<td align="center" width="125" align="center">${article.reg_date}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125">글제목</td>
				<td align="center" width="375" colspan="3">${article.subject}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125">글내용</td>
				<td align="left" width="375" colspan="3"><pre>${article.content}</pre></td>
			</tr>
			<tr height="30">
				<td colspan="4" class="w3-center"><input type="button"
					value="글수정"
					onclick="document.location.href='updateForm?num=${article.num}&pageNum=${pageNum}'">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글삭제"
					onclick="document.location.href='deleteForm?num=${article.num}&pageNum=${article.num}'">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="답글쓰기"
					onclick="document.location.href='writeForm?num=${num}&ref=${ref}&re_step=${re_step}&re_level=${re_level}&pageNum=${pageNum}'">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글목록"
					onclick="document.location.href='list?pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
	</div> --%>





</html>