<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Example</h1>
<img src="http://10.10.10.90:8080/getFile?fname=aaa.jpg">

<a href='http://10.10.10.90:8080/getFile?fname=아무무  그림.jpg'>
<h2>아무무 그림 파일</h2>
</a>


<button type="button"
onclick="document.getElementById('demo').innerHTML = '추연훈 이냐?'">
누구냐 넌.</button>

<p id="demo">

<form action="/upload1" method="post" enctype="multipart/form-data">
	<input type="text" name="mname">
	<input type="number" name="price">
	
	<input type="file" name="files" multiple="multiple">
	
	<button>Save</button>
</form>

</body>
</html>