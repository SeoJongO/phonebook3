<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Form</title>
</head>
<body>
	<h1>전화번호 수정폼</h1>
	<p>
		전화번호를 수정하려면 <br> 아래 항목을 수정하고 "수정" 버튼을 클릭하세요.
	</p>

	<form action="/phonebook3/update" method="post">
		 이름: <input type="text" name="name" value="${pMap.NAME }"> <br> 
		 핸드폰: <input type="text" name="hp" value="${pMap.HP }"> <br> 
		 회사: <input type="text" name="company" value="${pMap.COMPANY }"> <br> 
		 <input type="hidden" name="id" value="${pMap.PERSON_ID }"> <br>

		<button type="submit">수정</button>
	</form>
</body>
</html>