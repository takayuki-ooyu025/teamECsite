<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html
>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/password.css">
<link rel="stylesheet" href="./css/home.css">

<title>パスワード再設定確認</title>
</head>

<body>
<script src="./js/arizona.js"></script>
<jsp:include page="header.jsp" />

<div id="contents">
    <div class="h1-pass">
	   <h1>パスワード再設定確認画面</h1>
    </div>
	<s:form id="resetPasswordForm">
		<table class="vertical-list-table">
			<tr>
				<th scope="row"><s:label value="ユーザーID"/></th>
				<td><s:property value="userId"/></td>
			</tr>

			<tr>
				<th scope="row"><s:label value="新しいパスワード"/></th>
				<td><s:property value="concealedPassword"/></td>
			</tr>
		</table>

		<div class="rsubmit_btn_box">
			<s:submit value="パスワード再設定" class="submit_btn" onclick="goResetPasswordCompleteAction()"/>
		</div>

		<div class="rsubmit_btn_box">
			<s:submit value="戻る" class="submit_btn" onclick="goResetPasswordAction()"/>
		</div>

		<s:hidden id="backFlag" name="backFlag" value=""/>
	</s:form>
</div>

</body>
</html>