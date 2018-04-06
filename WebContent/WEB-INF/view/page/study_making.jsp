<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script>
$( function() {
   
    $( "#resizable" ).resizable({
    	
    });
  } );
  
$(document).ready(function(){
	  var fileTarget = $('.upload-hidden');

	    fileTarget.on('change', function(){
	        if(window.FileReader){
	            var filename = $(this)[0].files[0].name;
	        } else {
	            var filename = $(this).val().split('/').pop().split('\\').pop();
	        }
	
	        $('.upload-name').val(filename);
	    document.getElementById('upload-nameBox').style.display='block';
	       
	    
	    
	    });
	}); 
	
</script>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- content -->
<div class="w3-container bgimg-1" style="padding:128px 16px; " id="about">
  
  
<!-- <div class="w3-section w3-row-padding">
  <div class="w3-container w3-half">
  <div class="w3-container w3-white">
       <h6>방 만들기</h6>
      </div>
    <div class="w3-card-4">
      <div class="w3-container " style="overflow:auto; background: rgba(241, 241, 241, 0.75); ">
			     
			     <form action="makingPro" enctype="multipart/form-data"  method="post">
				 <p> 
				   방 이름:&nbsp;&nbsp; <input type="text" name="studyName"/><br><br>
				    방 소개:&nbsp;&nbsp;<br>
				    <textarea name="study_intro" style="margin-left:60px"></textarea>
			     <p>
				    프로필 설정:&nbsp;&nbsp;
				    <input type="file" size="40" name="study_pro" style="color: red;">
			  		<p>
				    배경 설정:&nbsp;&nbsp;
			    <input type="file" size="40" name="study_back" style="color: red;">  
				    <p>
			      <p><input class="w3-button w3-blue w3-section" type="submit" value="만들기"></p>
			    </form>
      </div>
    </div>
  </div>
</div>

//버튼으로 파일 업로드 시키기
 <button class="w3-button w3-padding-small " onclick="resetFile();">&times;</button>
 
 <script>
function resetFile(){
	 document.getElementById('upload-nameBox').style.display='none';
	 document.getElementById('upload-display').style.display='none';
	 document.getElementById('fileUpload').value="";
}

  <div id="upload-nameBox" style="display: none;">
   <span  class="w3-tag w3-white " style="font-size:12px;"> 
 <input class="upload-name " id="upload-name" disabled="disabled">
 <button class="w3-button w3-padding-small " onclick="resetFile();">&times;</button>
 </span>
 </div>


</script>
 


 -->

<div class="w3-section w3-row-padding">
	<div class="w3-third">&nbsp;</div>

  <div class="w3-third w3-container ">
  <div class="w3-container w3-teal">
  
      
       <h6>방 만들기</h6>
      </div>
    <div class="w3-card-4">
      <div class="w3-container " style="overflow:auto; background: rgba(241, 241, 241, 0.75); ">
     
<div class="w3-container">
  <form action="makingPro" enctype="multipart/form-data"  method="post">
<ul>
<li style="height:50px">그룹명:&nbsp;&nbsp; 
<input  type="text" name="studyName" style="border:none;width:70%" required ></li>
<li style="height:50px">방소개:&nbsp;&nbsp; 
<input  type="text" name="study_intro" style="border:none;width:70%" required> </li>

<!--  <input type="file" id="IMG_FILE" >
            <div id="PREVIEW_IMG_DIV" align="center">
            </div>
 -->

<li>그룹 프로필: 
<label for="ex_filename" class="w3-button w3-padding-small">
<i class="fa fa-file-image-o" style="font-size:50px;" ></i>
</label> 
<input type="file" name="study_pro" class="upload-hidden" id="ex_filename">
</li>

<!-- 			
   <label for="fileUpload" class="w3-button w3-padding-small" id="addfilebtn">
    <i class="fa fa-file-image-o" style="font-size:24px;" ></i></label> 
  <input type="file" id="fileUpload" name="uploadfile" class="upload-hidden"> 


	<label for="ex_filename">업로드</label> 
						  <input type="file" id="ex_filename" class="upload-hidden"> 
						  
						   -->


<li>그룹 배경: <input type="file" size="40" name="study_back" ></li>
</ul>
</form>



</div>


      </div>
    </div>
  </div>
</div>





</div>

</html>
