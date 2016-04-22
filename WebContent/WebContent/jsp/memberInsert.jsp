<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="MemberInsert.js"></script>
<title>회원가입</title>
</head>
<body>
	<div id="memberInsert_body">
		<div><input id="m_id" type="email" placeholder="이메일" required></div>
		<div><input id="m_password" type="password" placeholder="비밀번호" required></div>
		
		<div><input id="m_passwordCheck" type="password" placeholder="비밀번호 재확인" required></div>
		<div><input id="m_name" type="text" placeholder="닉네임" required></div>
		<div>
			<input class="birth" id="year" type="text" placeholder="ex.1991" maxlength="4" required>
			<select class="birth" id="month">
				<option value="0" selected disabled>월</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
			<input class="birth" id="day" type="text" placeholder="일" maxlength="2" required>
		</div>
		<div>
			<input id="man" name="m_gender" type="radio" value="male">
			<label id="manLabel" for="man">남성</label>
			<input id="woman" name="m_gender" type="radio" value="female">
			<label id="womanLabel" for="woman">여성</label>
		</div>
		<div><input id="btnRegister" type="button" value="가입하기"></div>
	</div>
</body>
</html>