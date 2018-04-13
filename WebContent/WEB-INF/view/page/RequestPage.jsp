<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- content -->
<div class="w3-container bgimg-1" style="padding:64px 16px; " id="about">
  
  
<div class="w3-section w3-row-padding">
	<div class="w3-quarter">&nbsp;</div>

  <div class="w3-half w3-container ">
  <div class="w3-container w3-white">
      
       <h6>Request List</h6>
      </div>
    <div class="w3-card-4">
      <div class="w3-container " style="overflow:auto; background: rgba(241, 241, 241, 0.75); ">
     
<div class="w3-container">
<!--  
<table class="w3-table  w3-centered" style="width: 100%; border:black;">
    <tr class="w3-black">
      <th class="w3-center" width="50">번 호</th>
      <th class="w3-center" width="250">제 목</th>
      <th class="w3-center" width="100">작성자</th>
      <th class="w3-center" width="150">작성일</th>
      <th class="w3-center" width="50">조 회</th>
    </tr>
		
			<tr class="w3-hover-white">
			<td class="w3-center" width="50">${number}</td>
			<c:set var="number" value="${number-1}"/>
				<td width="250">${article.subject }</td>
				<td class="w3-center" width="100">${article.writer}</td>
				<td class="w3-center" width="150">${article.reg_date}</td>
				<td class="w3-center" width="50">${article.readcount}</td>
			</tr>
  </table>



-->




  <table class="w3-table  ">
    <tr>
      <th>Date</th>
      <th>Group Name</th>
      <th>Status</th>
    </tr>
  	<c:if test="${reqList.size()==0}">
    <tr>
      <td colspan="3" class="w3-center">No impormation</td>
    </tr>
  	</c:if>
  	
  	<c:if test="${reqList.size()!=0}">
  	<c:forEach var="group" items="${reqList}">
     <tr id="acep">
      <td>${group.joinDate }</td>
      <td>${group.studyName}</td>
      <c:if test="${group.status==1 }">
      <td>승인 대기중</td>
      </c:if>
      <c:if test="${group.status==2 }">
      <td>승인  <span onclick="document.getElementById('acep').style.display='none'">&times;</span></td>
      </c:if>
    </tr>
    </c:forEach>	
  	</c:if>
  </table>
</div>


  <!-- Pagination -->
  <div class="w3-center w3-padding-16">
    <div class="w3-bar">
      <a href="#" class="w3-bar-item w3-button w3-hover-black">«</a>
      <a href="#" class="w3-bar-item w3-black w3-button">1</a>
      <a href="#" class="w3-bar-item w3-button w3-hover-black">2</a>
      <a href="#" class="w3-bar-item w3-button w3-hover-black">3</a>
      <a href="#" class="w3-bar-item w3-button w3-hover-black">4</a>
      <a href="#" class="w3-bar-item w3-button w3-hover-black">»</a>
    </div>
  </div>
      </div>
    </div>
  </div>
  
  
</div>
</div>
</html>