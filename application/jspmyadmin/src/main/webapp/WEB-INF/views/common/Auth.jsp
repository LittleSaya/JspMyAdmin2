<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="m" uri="http://jspmyadmin.com/taglib/jsp/messages"%>
<%@ taglib prefix="jma" uri="http://jspmyadmin.com/taglib/jsp/jma"%>
<m:open />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<style type="text/css">
html {
	font-size: ${sessionScope.fontsize}%;
}
small {
	display: block;
	font-weight: normal;
	color: #ff8000;
}
</style>
<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/components/images/favicon.ico">
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/components/images/favicon.ico">
<title><m:print key="title" /></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/components/jma/jspmyadmin.css">
<style type="text/css">
html {
	overflow: auto;
}

#install-part1 {
	display: inline-block;
	float: left;
	width: 380px;
	vertical-align: top;
}

#install-part2 {
	display: inline-block;
	float: right;
	width: 380px;
	vertical-align: top;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/components/jma/jquery.js"></script>
</head>
<body>
	<div style="width: 100%; text-align: center; margin-top: 20px;">
		<div style="width: 400px; margin-left: auto; margin-right: auto;">
			<img alt="Logo" id="site-logo" src="${pageContext.request.contextPath}/components/images/logo.png">
		</div>
	</div>
	<h2 align="center">
		Authentication is needed to proceed on
	</h2>
	<form action="${pageContext.request.contextPath}/auth.html"
		method="post" id="auth-form" accept-charset="UTF-8">
		<input type="hidden" name="token" value="${requestScope.command.token}">
		<div class="group" style="width: 800px;">
			<div class="group-widget group-header">
				<h3>
					<m:print key="msg.configuration" />
				</h3>
			</div>
			<div class="group-widget group-content">
				<div id="auth-part1">
					<div class="form-input">
						<label>Password</label>
						<input type="text" name="password" id="password"
							placeholder="Password" class="form-control">
					</div>
				</div>
			</div>
			<div class="group-widget group-footer">
				<button type="button" class="btn" id="go_btn">
					<m:print key="lbl.finish" />
				</button>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {

			$('body').keypress(function(e) {
				if (e.which == 13) {
					$('#go_btn').click();
				}
			});

			$('#go_btn').click(function() {
				if ($('#password').val() == '') {
				    alert('Password can not be empty');
					return;
				}
				$('#auth-form').submit();
			});
		});
	</script>
</body>
</html>