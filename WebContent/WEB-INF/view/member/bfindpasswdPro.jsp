<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="dao.MemberDAO"%>
<%@page import="model.MemberVO"%>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
        
      <c:if test="${chk==1}">
      <script language="JavaScript"> 
 alert("아이디가 존재합니다 힌트로 이동합니다");
 </script>

  <meta http-equiv="Refresh" 
content="0;url=../page/main" >
</c:if>
<c:if test="${chk!=1}">
       <script language="JavaScript">      
         alert("아이디가없습니다.");
         history.go(-1);
       </script>
</c:if>
       