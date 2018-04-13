<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

  
    <div class="w3-modal-content w3-light-grey w3-card-4" style="max-width: 400px;">
     <div class="w3-container w3-center w3-teal">
       <h6><b>내 정보 수정</b></h6>
      </div>
      <div class="w3-container w3-padding" >
        <span onclick="document.getElementById('myStudyInfo').style.display='none';" class="w3-button w3-display-topright">&times;</span>
       
<div class=" w3-center  w3-container w3-padding" id="modal">
	<form id="memberInfoForm" >
	
	
	<ul class="w3-ul w3-light-grey"><li><label>닉네임</label>
	<input type="text" id="title" name="title" class="w3-input w3-border" value="${memberInfo.nickName }"></li>
	
	
	<li><label>프로필 사진</label>
	<c:if test="${(memberInfo.photo!=null)&&(memberInfo.photo!='')}">
	<img src="<%=request.getContextPath() %>/fileSave/${memberInfo.photo }" width="100%" class="w3-margin-top">
	</c:if>
	<c:if test="${(memberInfo.photo==null)||(memberInfo.photo=='') }">
	<img src="<%=request.getContextPath() %>/imgs/profile.png" width="100%" class="w3-margin-top">
	</c:if>
	
	
	<li><button class="w3-button" id="commitbtn" onclick="checkUpdateValue();">전송</button><input type="reset" class="w3-button" value="다시쓰기"></li>
	
	
	</ul> 
	<input type="hidden" name="id" value="${calendar.num }">
	<input type="hidden" name="studynum" value="${calendar.studynum }">
	

	</form>

</div>
   </div>
    </div>
    





</body>
</html>