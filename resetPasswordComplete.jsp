<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3;URL='HomeAction'"/>
<!--  http-equiv 該当文書の処理の仕方や扱いを指定する。 文書の記述言語を指定のために使用。 -->

<link rel="stylesheet" href="./css/password.css">
<link rel="stylesheet" href="./css/home.css">

<title>パスワード再設定完了</title>
</head>

<body>
<jsp:include page="header.jsp" />

<div id="contents">
    <div class="h1">
        <h1>パスワード再設定完了画面</h1>
    </div>
<div class="success">
	パスワード再設定が完了しました。
</div>
</div>
</body>

</html>