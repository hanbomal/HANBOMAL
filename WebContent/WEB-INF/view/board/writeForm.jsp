<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<html>
<head>
<title>게시판</title>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<!-- arrow_back -->

<div class="w3-container " style="height:600px; width:100%; overflow:auto;  ">
<div class="w3-container " style="width: 100%;">
<div class="w3-card-2" style="margin-top:10px" >
	 <div  id="content" style="height:100%; background: rgba(241, 241, 241, 0.75);" >
  		 	   <font size=6>...게시판</font>|비공개 게시판<br>
  		 	   <br>
 <div class="w3-container">
		<form id="uploadform" method="post" name="writeform" enctype="multipart/form-data">
			  	<input type="hidden" name="writer" value="${memberid}">
			 	<input type="hidden" name="boardid" value="${boardid}">
		<table class="w3-table w3-card-2 w3-white w3-bordered " style="width: 90%;margin-left:5%">
			<tr>
				<th class="w3-black w3-center" colspan="2" >[글쓰기]</th>
			</tr>
		<%-- 	   <tr>
					<td align="right" colspan="2"><a href="#" 
					onclick="$('#content').load('<%=request.getContextPath()%>/board/study_board')">글목록</a></td>
				</tr> --%>
			
			<!--  
			
  <tr>
    <td  width="70" class=" w3-center "><label><b>제 목</b></label>
    </td>
    <td width="330">
       <input type="text" size="40" maxlength="50" name="subject"  required="required" 
       class="w3-input w3-border-0 w3-hover-light-grey">
   </td>
  </tr>
  <tr>
    <td  width="70" class=" w3-center " ><label><b>Email</b></label></td>
    <td  width="330">
       <input type="text" size="40" maxlength="30" name="email" 
       class="w3-input w3-border-0 w3-hover-light-grey" ></td>
  </tr>
  <tr>
    <td  width="70" class=" w3-center " ><label><b>내 용</b></label></td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40" class="w3-input w3-border-0 w3-hover-light-grey" required="required"></textarea> </td>
  </tr>-->
			<!--   <input type="text" size="40" maxlength="50" name="subject"  required="required" 
       class="w3-input w3-border-0 w3-hover-light-grey">  -->
			
				<tr>
					<th  width="10%" class=" w3-center">제 목</th>
					<td width="90%">
						<input type="text" size="40" maxlength="50" 
						name="subject" required="required" class="w3-input w3-border-0 w3-hover-light-grey">
					</td>
				</tr>
				<tr>
					<th  width="10%" class=" w3-center">내 용</th>
					<td width="90%"><textarea name="content" rows="10" cols="40"
					class="w3-input w3-border-0 w3-hover-light-grey" required="required"
					></textarea>
					</td>
				</tr>
			<!-- 	<tr>
					<td width="70" align="center">비밀번호</td>
					<td width="330">
					<input type="password" size="8" maxlength="12" name="passwd" required="required"></td>
				</tr>   -->
				<tr>
					<td colspan=2 align="center">
					 <input type="file" name="uploadfile" id="uploadfile">
					 
					
					<!-- <input type="submit" value="확인" onclick="upload()" 
					class="w3-right"> 
					
					
					-->
					</td>
				</tr>
			</table>
				<div class="w3-center w3-padding-16">
				<button type="submit" onclick="upload()" class=" w3-button w3-black">
					<i class="fa fa-check"></i>확인</button>
			<input type="button" value="목록보기" class="w3-black  w3-button" 
					onClick="$('#content').load('<%=request.getContextPath()%>/board/study_board')">
					<input type="reset" value="다시작성"
					class=" w3-black  w3-button ">
					
			</div>
		</form> 
	</div>  
	
</div></div></div></div>
	<script type="text/javascript">
		function upload(){
			event.preventDefault();
			
			var form=$('#uploadform')[0];
			var formData= new FormData(form);
			formData.append("uploadfile",$("#uploadfile")[0].files[0]);
			
			 $.ajax({
                         type: 'POST',
              			 enctype: 'multipart/form-data',
              			 url: '../board/writePro',
                         data: formData,
                         processData: false,
                         contentType: false,
                         success: function(data){
                            $('#content').html(data);
                         }
                 });
		}
	
	</script>
	

</html>
