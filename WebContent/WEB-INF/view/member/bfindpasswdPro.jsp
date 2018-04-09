<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="dao.MemberDAO"%>
<%@page import="model.MemberVO"%>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
        
      <c:if test="${chk==1}">
      <script language="JavaScript"> 
 alert("정답입니다.");
 </script>

  <meta http-equiv="Refresh" 
content="0;url=../page/main" >
</c:if>
<c:if test="${chk!=1}">
       <script language="JavaScript">      
         alert("아이디가 없거나 틀렸습니다.");
         history.go(-1);
       </script>
</c:if>
       