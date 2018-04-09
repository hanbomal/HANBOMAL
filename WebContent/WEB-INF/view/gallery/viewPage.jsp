<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<img class="w3-card-4" id="img01" src="<%=request.getContextPath() %>/fileSave/${gallery.filename}" style="max-width:100%">

<p/>

<div class="w3-container w3-center w3-light-grey">
       <button class="w3-button" onclick="imageToChat();">다시 보내기</button>
       <button class="w3-button" onclick=" document.getElementById('viewModal').style.display='none';deleteImage(${gallery.num});">삭제하기</button>
      </div>
</html>