<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="../api/fullcalendar-3.9.0/lib/jquery.min.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 

<script>
$(document).ready(function(){    $('#content').load('<%=request.getContextPath()%>/calcontroller/listview?num=<%=request.getParameter("group")%>');
document.getElementById('contentTitle').innerHTML='달력';
$('#chat').load('<%=request.getContextPath()%>/chatcontroller/intro?group=<%=request.getParameter("group")%>&name=<%=session.getAttribute("memberid")%>'); });

 $( function() {
    $( "#draggable" ).draggable({ handle: "#handle" });
    $( "#draggable2" ).draggable({ handle: "#contentTitle" });
    $( "#resizable" ).resizable({
    	
         minHeight: 600,
         minWidth: 500
    });
  } );
 
 
  </script>


<link href="../api/fullcalendar-3.9.0/fullcalendar.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
<style>
 
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
</style>

<!-- content -->
<div class="w3-container bgimg-1" style="padding:128px 16px; " >
  
  
<div class="w3-section w3-row-padding">


<div class="w3-third w3-container w3-margin-bottom"  id="draggable" >
   
      <div class="w3-container w3-white">
      <h6 style="display: inline-block; cursor: move;" id="handle">실시간 토론</h6> 
      <span class=" w3-tag w3-white w3-middle w3-margin-top" style="float: right; "><font style="font-color:grey; font-size:12px;" id="curMember" ></font></span>
      </div>
     <div class="w3-card-4">
 <!-- 채팅 div -->
  <div class="w3-container ui-widget-content" id="chat" ondrop="drop(event)" ondragover="allowDrop(event)" style="padding:0; margin:0; overflow:auto; background: rgba(241, 241, 241, 0.75); ">

      </div>
  
      </div>
    </div>


  <div class="w3-twothird w3-container resizable1" id="draggable2" >
  <div class="w3-container w3-white"><h6  style="display: inline-block; cursor: move;" id="contentTitle"></h6>
  <button class="w3-button w3-right "
  onclick="$('#content').load('<%=request.getContextPath()%>/gallery/list?memberid=${memberid }');document.getElementById('contentTitle').innerHTML='사진첩'">사진첩</button>
  
      <div class="w3-dropdown-hover w3-right"> 
    <button class="w3-button " onclick="$('#content').load('<%=request.getContextPath()%>/board/study_board');document.getElementById('contentTitle').innerHTML='게시판'">게시판</button>
    <div class="w3-dropdown-content w3-bar-block w3-border" style="z-index: 5;">
      <a href="#" class="w3-bar-item w3-button" onclick="document.getElementById('makeBoard').style.display='block'">게시판추가</a>
      <a href="#" class="w3-bar-item w3-button" onclick="$('#content').load('<%=request.getContextPath()%>/board/study_board');document.getElementById('contentTitle').innerHTML='게시판'">게시판2</a>
      <a href="#" class="w3-bar-item w3-button" onclick="$('#content').load('<%=request.getContextPath()%>/board/study_board');document.getElementById('contentTitle').innerHTML='게시판'">게시판3</a>
    </div>
  </div>
  <button class="w3-button w3-right " onclick="$('#content').load('<%=request.getContextPath()%>/calcontroller/listview');document.getElementById('contentTitle').innerHTML='달력'">달력</button>
      
      </div>
    <div class="w3-card-4" >
      
      <!-- 내용 div -->
      <div  id="content" style="height:100%; background: rgba(241, 241, 241, 0.75);" >

      </div>
      
      <!-- 내용끝 -->
      
      <div class="w3-container w3-padding w3-light-grey ">
      <span class="w3-right w3-margin-right">
      <button class="w3-button" 
      onclick="$('#content').load('<%=request.getContextPath()%>/page/study_admin');document.getElementById('contentTitle').innerHTML='스터디 관리'">스터디 관리</button></span>
      
      <span class="w3-right w3-margin-right">
      <button class="w3-button" onclick="$('#content').load('<%=request.getContextPath()%>/page/study_info');document.getElementById('contentTitle').innerHTML='스터디 정보'">스터디 정보</button></span> 
      
      </div>
    </div>
  </div>
  

</div>

</div>



  <div id="makeBoard" class="w3-modal "  >
    <div class="w3-modal-content w3-card-4" style="width:40%">
      <header class="w3-container w3-teal"> 
        <span onclick="document.getElementById('makeBoard').style.display='none'" 
        class="w3-button w3-display-topright">&times;</span>
        <h6><i class="fa fa-plus"></i> 게시판 만들기</h6>
      </header>
      <div class="w3-container">
      <div style="margin-top:10px">
      <font size=3>⦁ 게시판 이름</font>
      <font size=2>[비공개 여부</font>
       <input type="checkbox" name="chk" value="1">]
      </div>
        <input class="w3-input" type="text" name="boardname">
      </div>
      <div class="w3-container">
       	<input type="submit" 
        class="w3-input w3-teal w3-center w3-section" value="만들기">
        </div>
    </div>
  </div>
</html>