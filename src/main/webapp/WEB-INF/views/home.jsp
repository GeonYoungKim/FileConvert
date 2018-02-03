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
				<tr><td>File to upload:</td><td><input type="file" name="photo" /></td></tr>
				<tr><td></td><td><input type="text" list="file" name="select_way">
				<datalist id="file">
					<option value="내부온도"/>
					<option value="전압"/>
					<option value="외부온도"/>
					<option value="배터리 잔여량"/>
					<option value="기간"/>
				</datalist>
				</td></tr>
				<tr><td></td><td><input type="submit" value="Convert" /></td></tr>
				
			</table>
		</form>
	</div>
</body>
</html>
