<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
function showMyInfo(){
	var name="<c:out value='${memberid}'/>";
	var group="<c:out value='${study.num}'/>";
	
	document.getElementById('myStudyInfo').style.display='block';
	
	
	 $.ajax({
		type: 'POST',
		url: 'showMyInfo',
		async:false,
		data: {    "name" : name,
					"group":group
        },
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		success: function(data) {
			$('#myStudyInfo').html(data);
		},
		error: function(request, status, error) {
			alert(error);
		}
	});
	 
}

</script>
</head>
<body>
<div class="w3-container w3-padding" style="height: 600px;">
<div style="width: 100%;" class="w3-container w3-margin-left w3-margin-bottom">
<div class="w3-bar w3-padding ">

  <button class="w3-button w3-padding-small w3-right  w3-black w3-margin-bottom" title="내 정보 수정"
  onclick="showMyInfo();">내 정보 수정</button>
</div>
<div>
<ul class="w3-ul">
<li>
스터디 이름 : ${study.studyName }
</li>
<li>스터디 개설일 : ${study.openDate }</li>
<li>스터디 소개 : ${study.study_intro }</li>
<li>회원 목록 (총 ${memberCount}명) 
	<ul class="w3-ul">
	
	<c:forEach var="member" items="${members }">
	<li>
	<c:if test="${(member.photo!=null)&&(member.photo!='')}"><img src="<%=request.getContextPath()%>/fileSave/${member.photo}" width="30px"></c:if>
	<c:if test="${(member.photo==null)||(member.photo=='')}"><img src="<%=request.getContextPath()%>/imgs/profile.png" width="30px"></c:if>
	${member.nickName } | ${member.memberId } (${member.position })</li>
	
	</c:forEach>
	</ul>

</li> 
</ul>
</div>
</div>
</div>

<!-- 모달 -->
<div id="myStudyInfo" class="w3-modal" style="display: none;" >
   
  </div>
  



</body>
</html>