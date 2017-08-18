<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/student/css/login.css">
	</head>

	<body>
		
		<div class="wrap-content-header">sgdsgfsd</div>
			<div class="wrap-content">
				<div class="wrap-content-right">sdfgsdfg</div>
				<div class="wrap-content-left">
					<div style="height:100px;"></div>
					<div class="wrap-content-login">
						<c:url var="loginUrl" value="/login" />
						<form action="${loginUrl}" method="post" class="form-horizontal">
						  <div class="imgcontainer">
								<img src="${pageContext.request.contextPath }/static/student/icon/users.png" alt="Avatar" class="avatar">
						  </div>
		
						  <div class="container">
							<label><b>User ID</b></label>
							<input type="text" placeholder="Enter Username" name="username" required>
		
							<label><b>Password</b></label>
							<input type="password" placeholder="Enter Password" name="password" required>
								
							<button type="submit">Login</button>
							<input type="checkbox" checked="checked"> Remember me
						  </div>
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
						  <div class="container" style="background-color:#f1f1f1">
							<button type="button" class="cancelbtn">Cancel</button>
							<span class="psw">Forgot <a href="#">password?</a></span>
						  </div>
						</form>
					</div>
					<div></div>
				</div>
			</div>
			<div class="wrap-content-footer">sdfgsdgf</div>
	</body>
</html>