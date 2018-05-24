<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In Here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/Stylesheets/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/Stylesheets/login.css">

</head>
<body>
<div class="container">
	<c:if test='${param.login eq "unsuccessful" }'>
		<div class="alert alert-warning alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		Invalid username and password combination.
		</div>
	</c:if>
	<c:if test='${param.info eq "sent" }'>
		<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		Password is sent to your email account.
		</div>
	</c:if>
	<c:if test='${param.info eq "nosent" }'>
		<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		No such account exist.
		</div>
	</c:if>
	<div class="row" style="margin-top: 20px">
		<div
			class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form role="form" method="post" action="/Online_LMS/Login">
				<fieldset>
					<h2>Please Sign In</h2>
					<hr class="colorgraph">
					<div class="form-group">
						<input type="text" name="username" id="username"
							onkeyup="validate(event)" onblur="validate(event)" class="form-control input-lg" placeholder="Username">
					</div>
					<div class="form-group">
						<input type="password" name="password" id="password"
							onkeyup="validate(event)" onblur="validate(event)" class="form-control input-lg" placeholder="Password">
					</div>
					<span class="button-checkbox">
						 <a data-toggle="modal" data-target="#myModal" class="btn btn-link">Forgot Password?</a>
					</span>
					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<input id="submit" disabled="disabled" onclick="return signin()" type="submit" class="btn btn-lg btn-success btn-block"
								value="Sign In">
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6">
							<a href="/Online_LMS/RegisterForm" class="btn btn-lg btn-primary btn-block"
							  >Register</a>
						</div>											
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Provide Username</h4>
      </div>
      <div class="modal-body">
      	<form role="form" method="post" action="/Online_LMS/ForgetPasswordServlet">
	        <div class="form-group">
	             <input type="text" name="user" id="user" class="form-control input-lg" placeholder="Username" tabindex="1">
			</div>
	      </div>
	      <div class="form-group modal-footer">
	        <button id="sub" type="submit" class="btn btn-primary">Send Password</button>
	      </div>
      </form>
    </div>
  </div>
</div>

  <script src="${pageContext.request.contextPath }/Javascripts/forget_validator.js"></script>
  <script src="${pageContext.request.contextPath }/Javascripts/jquery.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/Javascripts/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/Javascripts/bootbox.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/Javascripts/login_validator.js"></script>
</body>
</html>
