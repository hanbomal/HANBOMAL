<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../api/fullcalendar-3.9.0/lib/jquery.min.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 

<script>
$(document).ready(
	function(){
    $('#memberList').load('<%=request.getContextPath()%>/page/admin_memberList');
    });
</script>
<body>


<div class="w3-container w3-padding" style="height: 600px;">

<div class="w3-half" id="memberList" style="margin-top:40px">
<%-- <div class="w3-center w3-padding"><b>회원 목록</b>(총 ${memberCount}명)</div>
 <table class="w3-table  w3-centered" style="width: 100%; border:black;">
    <tr class="w3-black">
      <th class="w3-center" width="25%">닉네임</th>
      <th class="w3-center" width="25%">역할</th>
      <th class="w3-center" width="25%">가입일</th>
      <th class="w3-center" width="25%">최종접속일</th>
    </tr>
  <c:forEach var="member" items="${members }">
 	<tr class="w3-hover-white">
 	  <td class="w3-center" width="25%">${member.nickName }</td>
 	  <td class="w3-center" width="25%">${member.position }</td>
 	  <td class="w3-center" width="25%">${member.joinDate }</td>
 	  <td class="w3-center" width="25%">${member.lastDate }</td>
 	</tr>
  </c:forEach>
 </table> --%>
  <!-- Pagination -->
 <%--  <c:if test="${count>0 }">
			 <div class="w3-center w3-padding-16">
    <div class="w3-bar">
			<c:if test="${startPage>bottomLine }">
			<a href="<%=request.getContextPath()%>/page/RequestPage?pageNum=${startPage-bottomLine}" class="w3-bar-item w3-button w3-hover-black">«</a>
			</c:if>
			
	<c:forEach var="i" begin="${startPage }" end="${endPage}">
				<c:if test="${i!=currentPage }">  <a href="<%=request.getContextPath()%>/page/RequestPage?pageNum=${i }" class="w3-bar-item w3-button w3-hover-black">${i }</a>
				</c:if>
				<c:if test="${i==currentPage }">  <a href="<%=request.getContextPath()%>/page/RequestPage?pageNum=${i }" class="w3-bar-item w3-black w3-button">${i }</a>
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCount }">
				
				<a href="<%=request.getContextPath()%>/page/RequestPage?pageNum=${startPage+bottomLine}" class="w3-bar-item w3-button w3-hover-black">»</a>
			</c:if>
			    </div>
  </div>
		</c:if>   --%>

</div>

<div class="w3-quarter">
<div class="w3-bar">
<button class="w3-button w3-padding-small w3-right  w3-black w3-margin-bottom"
onclick="document.getElementById('addPosition').style.display='block'" style="margin-right:10px"><i class="fa fa-plus"></i>역할 추가</button>
</div>
<div class="w3-center"><b>=역할 목록=</b></div>
<div class="w3-border w3-border-black w3-margin" style="height:400px;">
	<ul class="w3-ul w3-hoverable">
		<c:if test="${positionList.size()==0 }">
			<li onclick="document.getElementById('addPosition').style.display='block'">역할을 추가하세요.</li>
		</c:if>
		<c:forEach var="positionList" items="${positionList }">
			<li onclick="postionInfo(${positionList.studynum})">⦁&nbsp;${positionList.groupposition }</li>
		</c:forEach>
	
	</ul>
</div>
</div>

<div class="w3-quarter">
<div class="w3-bar">
 <button class="w3-button w3-padding-small w3-right  w3-black w3-margin-bottom" title="게시판 추가"
onclick="document.getElementById('makeBoard').style.display='block'"><i class="fa fa-plus"></i>게시판 추가</button>
</div>
<div class="w3-center"><b>=게시판 목록=</b></div>
<div class="w3-border w3-border-black w3-margin" style="height:400px;">
	<ul class="w3-ul w3-hoverable">
			<c:if test="${typeList.size()==0 }">
			<li onclick="document.getElementById('makeBoard').style.display='block'">게시판을 추가하세요.</li>
			</c:if>
		<c:forEach var="typeList" items="${typeList }">
			<li onclick="boardInfo(${typeList.boardid},${typeList.studynum},'${typeList.boardname }')">⦁&nbsp;${typeList.boardname }</li>
		</c:forEach> 
	</ul>
</div>

</div>

<div>
<button class="w3-button w3-red w3-right w3-margin">그룹해제</button>
</div>


</div>

<!-- 게시판 만드는 모달 -->
  <div id="makeBoard" class="w3-modal "  >
    <div class="w3-modal-content w3-card-4" style="width:20%">
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

	  function postionInfo(studynum){
			var num=studynum;
			document.getElementById('clickPosition').style.display='block';
			
			 $.ajax({
				type: 'POST',
				url: 'PositionInfo',
				async:false,
				data: {   
							"studynum":num
		        },
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				success: function(data) {
					$('#clickPosition').html(data);
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
<!-- 역할 수정/삭제 모달 -->
<div id="clickPosition" class="w3-modal" style="display: none;" >
  </div>
	
	
	


</body>
</html>