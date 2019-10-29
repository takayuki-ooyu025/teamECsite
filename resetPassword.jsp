<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/password.css">
<link rel="stylesheet" href="./css/home.css">

<title>パスワード再設定</title>
</head>

<body>
<jsp:include page="header.jsp"/>
<div id="contents">
    <div class="h1">
        <h1>パスワード再設定画面</h1>
    </div>

	<s:if test="userIdErrorMessageList != null &&  userIdErrorMessageList.size()>0">
<!-- userIdErrorMessageListが空っぽじゃ無ければかつListの中身が0以上ならエラーメッセージを出す -->
	<div class="error">
	<div class="error-message">
		<s:iterator value="userIdErrorMessageList">
<%-- 	userIdErrorMessageListのデータ型がStringなので指定せずに<s:property/>で表示できる --%>
		<s:property/>
		<br>
		</s:iterator>
	</div>
	</div>
	</s:if>

	<s:if test="passwordErrorMessageList != null && passwordErrorMessageList.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="passwordErrorMessageList">
		<s:property/>
		<br>
		</s:iterator>
	</div>
	</div>
	</s:if>

	<s:if test="newPasswordErrorMessageList != null && newPasswordErrorMessageList.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="newPasswordErrorMessageList">
		<s:property/>
		<br>
		</s:iterator>
	</div>
	</div>
	</s:if>

	<s:if test="reNewPasswordErrorMessageList != null && reNewPasswordErrorMessageList.size()>0">
	<div class="error">
	<div class="error-message">
		<s:iterator value="reNewPasswordErrorMessageList">
		<s:property/>
		<br>
		</s:iterator>
	</div>
	</div>
	</s:if>

	<s:if test="passwordDatabaseErrorMessage != null && !passwordDatabaseErrorMessage.isEmpty()">
<!-- passwordDatabaseErrorMessageに何かしら値が入っていればエラーメッセージを出す -->
	<div class="error">
	<div class="error-message">
		<s:iterator value="passwordDatabaseErrorMessage">
		<s:property/>
		<br>
		</s:iterator>
	</div>
	</div>
	</s:if>

	<s:if test="newPasswordDatabaseErrorMessage != null && !newPasswordDatabaseErrorMessage.isEmpty()">
	<div class="error">
	<div class="error-message">
		<s:property  value="newPasswordDatabaseErrorMessage"/>
		<br>
	</div>
	</div>
	</s:if>

<s:form action="ResetPasswordConfirmAction">
	<table class="vertical-list-table">
		<tr>
			<th scope="row"><s:label value="ユーザーID"/></th><%-- scope="row"同じ行の見出しセル --%>
			<td><s:textfield name="userId" value="%{#session.userIdResetPassword}" placeholder="ユーザーID" class="txt" /></td>
		</tr>

		<tr>
			<th scope="row"><s:label value="現在のパスワード"/></th>
			<td><s:password name="password" value="" placeholder="パスワード" class="txt"/></td>
		</tr>

		<tr>
			<th scope="row"><s:label value="新しいパスワード"/></th>
			<td><s:password name="newPassword" value="" placeholder="新しいパスワード" class="txt"/></td>
		</tr>

		<tr>
			<th scope="row"><s:label value="新しいパスワード(再確認)"/></th>
			<td><s:password name="reNewPassword" value="" placeholder="新しいパスワード(再確認)" class="txt"/></td>
		</tr>
	</table>
	<div class="rsubmit_btn_box">
		<s:submit value="確認" class="submit_btn" />
	</div>
</s:form>

</div>

</body>
</html>