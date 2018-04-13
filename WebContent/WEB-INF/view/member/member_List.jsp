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
      
       <h6>회원관리</h6>
      </div>
    <div class="w3-card-4">
      <div class="w3-container " style="overflow:auto; background: rgba(241, 241, 241, 0.75); ">
     
<div class="w3-container">
  <table class="w3-table  ">
    <tr>
    <th>회원번호</th>
      <th>ID</th>
      <th>NICKNAME</th>
      <th>JOINDATE</th>

     <th>LASTDATE</th>
     <th>삭제</th>
      
    </tr>
    
  	<c:if test="${memberList.size()==0}">
    <tr>
      <td colspan="3" class="w3-center">회원이없습니다.</td>
    </tr>
  	</c:if>
  	
  	
  	<c:forEach var="member" items="${memberList}">
     <tr id="member_List">
      <td>${member.num}</td>
      <td>${member.memberid}</td>
      <td>${member.nickname}</td>
       <td>${member.joindate}</td>
        <td>${member.lastdate}</td>
         <td>
     
                  <form method="post" action="<%=request.getContextPath() %>/member/deletePro">
                     <input type="submit" class="w3-button w3-w3-pale-red w3-hover-black" value="삭제">
                           
         
         </td>
         
  
     
     
    </tr>
    </c:forEach>	
  	
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
      <a href="#" class="w3-bar-item w3-button w3-hover-black">>></a>
    </div>
  </div>
      </div>
    </div>
  </div>
  
  
</div>
</div>
</html>