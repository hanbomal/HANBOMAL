
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>Insert title here</title>
</head>


<c:if test="${result}">
<body>
	<div class="w3-container w3-pink w3-center w3-round" style="height: 150px;">
		<div class="w3-container w3-white w3-round" style="padding: 4%; margin: 40px 0% 0% 0%;">
			<span>이미 사용중인 ID입니다.</span>
		</div>
	</div>
</c:if>	
<c:if test="${!result}"><%-- <% } else { %> --%>
	<div class="w3-container w3-pink w3-center w3-round" style="height: 150px;">
		<div class="w3-container w3-white w3-round" style="padding: 4%; margin: 40px 0% 0% 0%;">
			<span><b>${memberid}</b>은 사용하실 수 있는 이메일 입니다.</span>
		</div>
	</div>
</c:if>
</body>
</html>