<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<html>
<head>
<title>게시판</title>
</head>
 <script type="text/javascript" src="../api/httpRequest.js"></script> 
  			
		<%-- 	<input type="hidden" name="boardid" value="${boardid}">
			<input type="hidden" name="num" value="${num}">
				<input type="text" size="10" maxlength="10"	name="writer">
				subject content passwd
				
	function helloToServer(){
		var params ="subject="+encodeURIComponent(document.writeform.subject.value);
										f는 폼네임  name은 인풋의 name 
		sendRequest("hello.jsp",params,helloFromServer,"POST");
	}
	
					mv.addObject("boardid", boardid);
		mv.addObject("num", article.getNum());
		mv.addObject("pageNum", pageNum);
			 --%>
			 
<!-- <script type="text/javascript">
	function writeFormToServer(){
		var params ="boardid=${boardid}&num=${num}&writer=${memberid}&"
		+"subject="+encodeURIComponent(document.writeform.subject.value)+"&content="
		+encodeURIComponent(document.writeform.content.value)+"&passwd="
		+encodeURIComponent(document.writeform.passwd.value); 
		sendRequest("../board/writePro",params,responseFromServer,"POST");
	}
	function responseFromServer(){
		if(httpRequest.readyState==4){
			if(httpRequest.status==200){
	 			document.getElementById("content").innerHTML=httpRequest.responseText 
			}
		}
	}
</script>  -->


<body>


<div class="w3-container " style="height:600px; width:100%; overflow:auto;  ">
     
<div class="w3-container " style="width: 100%;">
  <div class="w3-card-4" >
 <div  id="content" style="height:100%; background: rgba(241, 241, 241, 0.75);" >



	<div class="w3-container">
		<b>글쓰기</b> <br>
		<form method="post" id="form" name="writeform" enctype="multipart/form-data">
			
		<%-- 	<input type="hidden" name="boardid" value="${boardid}">
			<input type="hidden" name="num" value="${num}">
				<input type="text" size="10" maxlength="10"	name="writer">
				subject content passwd
			 --%>
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
					 <input type="file" name="uploadfile">  
					<input type="button" value="글쓰기" onclick="send()">
					<input type="reset" value="다시작성"> 
					<input type="button" value="목록보기" OnClick="window.location='list'"></td>
				</tr>
				<tr>
				
				</tr>
			</table>
			
		</form>
	</div>
</div></div></div></div>
	
	
	<script>
	function send() {
/* 	      	    var form = $('#form')[0]; */
	      		var formData = $("#form").serialize();
	      	    /* var formData = new FormData(form); */
	  			/* formData.append("content", ${boardid});
	  			formData.append("num", ${num});
	  			formData.append("writer", ${memberid});
	  			formData.append("subject", document.writeform.subject.value);
	  			formData.append("content", document.writeform.content.value);
	  			formData.append("passwd", document.writeform.passwd.value);
	  			 */
	      	    $.ajax({
	      	        type: "POST",
	      	        enctype: 'multipart/form-data',
	      	        url: "../board/writePro",
	      	        data: formData,
	      	        processData: false,
	      	        contentType: false,
	      	        cache: false,
	      	        timeout: 600000,
	      	        success: function (data) {
	      	            console.log("SUCCESS : ", data);
	      	        },
	      	        error: function (e) {
	      	            console.log("ERROR : ", e);
	      	        }
	      	    });
	}
	</script>
	
</body>
</html>
