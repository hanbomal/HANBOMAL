<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="dao.StudyDAO"%>
<%@page import="model.StudyVO"%>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
        
      <c:if test="${chk==1}">
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<meta http-equiv="Refresh" content="0; url=study_List?pageNum=${pageNum}">
	<script type="text/javascript">
		alert("${studyName} 이 사라졌습니다");
	</script>
</c:if>	
<c:if test="${chk!=1}">
       <script language="JavaScript">      
         alert("비밀번호가 맞지 않습니다");
         history.go(-1);
       </script>
</c:if>