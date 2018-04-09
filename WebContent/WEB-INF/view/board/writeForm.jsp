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


<body>


<div class="w3-container " style="height:600px; width:100%; overflow:auto;  ">
     
<div class="w3-container " style="width: 100%;">
  <div class="w3-card-4" >
 <div  id="content" style="height:100%; background: rgba(241, 241, 241, 0.75);" >

<!-- <form id="uploadform" method="post" name="writeform" enctype="multipart/form-data">
		 <input type="file" name="uploadfile" id="uploadfile">
		 <input type="submit" value="글쓰기" onclick="upload()"> 
</form>

 -->



 <div class="w3-container">
		<b>글쓰기</b> <br>
		<form id="uploadform" method="post" name="writeform" enctype="multipart/form-data">
			
			  	<input type="hidden" name="writer" value="${memberid}">
			 	<input type="hidden" name="boardid" value="${boardid}">
			
			<table class="w3-table-all" style="width: 70%;">
			  <tr>
					<td align="right" colspan="2"><a href="list"> 글목록</a></td>
				</tr>
				<tr>
					<td width="70" align="center">제 목</td>
					<td width="330">
						<input type="text" size="40" maxlength="50" name="subject">
					</td>
				</tr>
				<tr>
					<td width="70" align="center">내 용</td>
					<td width="330"><textarea name="content" rows="13" cols="40"></textarea>
					</td>
				</tr>
				<tr>
					<td width="70" align="center">비밀번호</td>
					<td width="330">
					<input type="password" size="8" maxlength="12" name="passwd" required="required"></td>
				</tr>  
				<tr>
					<td colspan=2 align="center">
					 <input type="file" name="uploadfile" id="uploadfile">
					<input type="submit" value="글쓰기" onclick="upload()"> 
					<input type="reset" value="다시작성"> 
					<input type="button" value="목록보기" OnClick="window.location='list'">
				
					</td>
				</tr>
				<tr>
				
				</tr>
			</table>
			
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
	

</body>
</html>
