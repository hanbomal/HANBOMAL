<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="w3-container w3-padding" style="height: 600px;">
<div class="w3-bar w3-padding ">


 <button class="w3-button w3-padding-small w3-right  w3-black w3-margin-bottom" title="게시판 추가"
onclick="document.getElementById('makeBoard').style.display='block'"><i class="fa fa-plus"></i>게시판 추가</button>
<button class="w3-button w3-padding-small w3-right w3-black"
onclick="document.getElementById('addPosition').style.display='block'" style="margin-right:10px"><i class="fa fa-plus"></i>역할 추가</button>


</div>
<div>
<ul class="w3-ul">
<li>게시판 관리
	<ul class="w3-ul w3-hoverable">
			<c:if test="${typeList==null }">
			<li onclick="document.getElementById('makeBoard').style.display='block'">[게시판 추가]</li>
			</c:if>
		<c:forEach var="typeList" items="${typeList }">
			<li onclick="boardInfo(${typeList.boardid},${typeList.studynum},'${typeList.boardname }')">⦁&nbsp;${typeList.boardname }</li>
		</c:forEach> 
	</ul>
</li>
<li>회원 관리(총 ${memberCount}명) 
	<ul class="w3-ul w3-hoverable">
	 <c:forEach var="member" items="${members }">
	<li >
	<c:if test="${(member.photo!=null)&&(member.photo!='')}"><img src="<%=request.getContextPath()%>/fileSave/${member.photo}" width="30px"></c:if>
	<c:if test="${(member.photo==null)||(member.photo=='')}"><img src="<%=request.getContextPath()%>/imgs/profile.png" width="30px"></c:if>
	${member.nickName } | ${member.memberId } (${member.position })</li>
	
	</c:forEach> 
	
	</ul>

</li>
</ul>

</div>
</div>
<!-- 게시판 만드는 모달 -->
  <div id="makeBoard" class="w3-modal "  >
    <div class="w3-modal-content w3-card-4" style="width:40%">
      <header class="w3-container w3-teal"> 
        <span onclick="document.getElementById('makeBoard').style.display='none'" 
        class="w3-button w3-display-topright">&times;</span>
        <h6><i class="fa fa-plus"></i> 게시판 만들기</h6>
      </header>
      <form id="addboardForm" method="post">
      <input type="hidden" name="studynum" value="${group }" >
     
      <div class="w3-container">
      <div style="margin-top:10px">
      <font size=3>⦁ 게시판 이름</font>
 
      </div>
        <input id="clearName" class="w3-input" type="text" name="boardname">
      </div>
      <div class="w3-container">
       	<input type="submit" onclick="addboard()" 
        class="w3-input w3-teal w3-center w3-section" value="만들기">
        </div>
        </form>
    </div>
  </div>

	<script type="text/javascript">
		function addboard(){
			event.preventDefault();
			
			var form=$('#addboardForm')[0];
			var formData= new FormData(form);
			 $.ajax({
                         type: 'POST',
              			 url: '../page/addBoardType',
              		     data: formData,
              		     processData: false,
                         contentType: false,
                         success: function(data){
                            $('#content').html(data);
                         }
                 });
			 document.getElementById('clearName').value=""; 
			 document.getElementById('makeBoard').style.display='none';
			 
		}
	
		
		
		function boardInfo(boardid,studynum,boardname){
			var id=boardid;
			var num=studynum;
			var name=boardname;
			document.getElementById('clickBoard').style.display='block';
			
			
			 $.ajax({
				type: 'POST',
				url: 'boardInfo',
				async:false,
				data: {    "boardid" : id,
							"studynum":num,
							"boardname":name
		        },
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				success: function(data) {
					$('#clickBoard').html(data);
				},
				error: function(request, status, error) {
					alert(error);
				}
			});
			 
		}

		
	</script>
	
	<!-- 역할추가 모달 -->
  <div id="addPosition" class="w3-modal "  >
    <div class="w3-modal-content w3-card-4" style="width:20%">
      <header class="w3-container w3-teal"> 
        <span onclick="document.getElementById('addPosition').style.display='none'" 
        class="w3-button w3-display-topright">&times;</span>
        <h6><i class="fa fa-plus"></i> 역할 추가</h6>
      </header>
      <form id="addPositionForm" method="post">
      <input type="hidden" name="studynum" value="${group }" >
     
      <div class="w3-container">
      <div style="margin-top:10px">
      <font size=3>⦁ 역할명</font>
 
      </div>
        <input id="clearPosition" class="w3-input" type="text" name="groupposition">
      </div>
      <div class="w3-container">
       	<input type="submit" onclick="addposition()" 
        class="w3-input w3-teal w3-center w3-section" value="추가">
        </div>
        </form>
    </div>
  </div>

	<script type="text/javascript">
		function addposition(){
			event.preventDefault();
			
			var form=$('#addPositionForm')[0];
			var formData= new FormData(form);
			 $.ajax({
                         type: 'POST',
              			 url: '../page/addPosition',
              		     data: formData,
              		     processData: false,
                         contentType: false,
                         success: function(data){
                            $('#content').html(data);
                         }
                 });
			 document.getElementById('clearPosition').value=""; 
			 document.getElementById('addPosition').style.display='none';
			 
		}
	
		
		
		function boardInfo(boardid,studynum,boardname){
			var id=boardid;
			var num=studynum;
			var name=boardname;
			document.getElementById('clickBoard').style.display='block';
			
			
			 $.ajax({
				type: 'POST',
				url: 'boardInfo',
				async:false,
				data: {    "boardid" : id,
							"studynum":num,
							"boardname":name
		        },
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				success: function(data) {
					$('#clickBoard').html(data);
				},
				error: function(request, status, error) {
					alert(error);
				}
			});
			 
		}

		
	</script>
	
	
<!-- 게시판 수정/삭제 모달 -->
<div id="clickBoard" class="w3-modal" style="display: none;" >
   
  </div>
	
	
	


</body>
</html>