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
      
       <h6>My Info</h6>
      </div>
    <div class="w3-card-4">
      <div class="w3-container " style="overflow:auto; background: rgba(241, 241, 241, 0.75); ">
     
<div class="w3-container">
  <table class="w3-table  ">
    <form method="post" name="memberInfo" class="w3-container w3-card-4 w3-white w3-text-black w3-margin w3-animate-bottom w3-round" 
action="member_updatePro" onsubmit="return checkValue()">
<input type="hidden" name="listid" value="${article.listid }">
<input type="hidden" name="num" value="${article.num }">
<input type="hidden" name="memberid" value="${sessionScope.id}">
<input type="hidden" name="passwd" value="${sessionScope.passwd}">
<h2 class="w3-center">My Info</h2>
<%----<h6 class="w3-right">*는 필수 입력입니다.</h6> --%>

<!-- 아이디 -->
<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      <input class="w3-input w3-border w3-light-grey"  name="memberid" type="text" readonly="readonly" value="${sessionScope.memberid}">
    </div>
</div>

<!-- 닉네임  -->
<div class="w3-row w3-section ">
  <div class="w3-col" style="width:50px" ></div>
    <div class="w3-rest">
      <input class="w3-input w3-border w3-light-grey"  name="nickname" type="text"  value="${article.nickname}">
    </div>
</div>

<!-- 패스워드 -->
<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      <input class="w3-input w3-border w3-light-grey"  name="passwd" type="password"  value="${passwd}">
    </div>
</div>

<!-- 패스워드 확인  -->
<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"></div>
    <div class="w3-rest">
      <input class="w3-input w3-border w3-light-grey"  name="passchk" type="password" placeholder="Password Check*">
    </div>
</div>


<p class="w3-center">

<input type="reset" value="RETRY" class="w3-button w3-grey" OnClick="view/index.jsp">  
<input type="submit" value="SEND" class="w3-button w3-black"  OnClick="view/index.jsp">

<input type="button" value="Back" class="w3-button w3-grey" OnClick="view/index.jsp">  
</p>
</form>
  </table>
</div>


 
      </div>
    </div>
  </div>
  
  
</div>
</div>
</html>