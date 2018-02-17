<%@page import="java.util.List"%>

<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <html xmlns:th="http://www.thymeleaf.org">
<head>
	<title>Home</title>
</head>
<body>
<!-- <a href="filedown">파일 다운받기</a> -->

<div>
		<form method="POST" enctype="multipart/form-data" action="/kr/photo">
			<table>
				<tr><td>전압 업로드:</td><td><input type="file" name="voltage" /></td></tr>
				<tr><td>온도 업로드:</td><td><input type="file" name="degree" /></td></tr>
				<tr><td>배터리 잔여량 업로드:</td><td><input type="file" name="battery" /></td></tr>
				<tr><td>버퍼기간 업로드:</td><td><input type="file" name="buffer" /></td></tr>
				<tr><td>사용 기간 업로드:</td><td><input type="file" name="period" /></td></tr>
				<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
			</table>
		</form>
		<button onclick="location='/kr/down'">result 파일 받기</button><br>
		<a href="/kr/filedown?filename=batteryRemains.txt">(배터리 잔여량 샘플 다운) <br></a>
		<a href="/kr/filedown?filename=buffer_time.txt">(버퍼시간  샘플 다운)<br></a>
		<a href="/kr/filedown?filename=degree.txt">(온도 샘플 다운)<br></a>
		<a href="/kr/filedown?filename=period.txt">(사용기간 샘플 다운)<br></a>
		
		<a href="/kr/filedown?filename=voltage.txt">(전압 샘플 다운)<br></a>
	</div>
</body>
</html>
