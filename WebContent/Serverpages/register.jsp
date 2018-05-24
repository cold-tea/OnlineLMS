<%@page import="java.sql.ResultSet"%>
<%@page import="online.lms.database.DBHandler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/Stylesheets/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/Stylesheets/login.css">

</head>
<body>
	<div class="container">
		<div class="row">
		    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
				<form role="form" method="post" action="/Online_LMS/Register">
					<h2>Please Register</h2>
					<hr class="colorgraph">
					
					<div class="form-group">
                        <input onkeyup="validate(event)" onblur="validate(event)" type="text" name="username" id="username" class="form-control input-lg" placeholder="Username" tabindex="1">
					</div>
					
					<div class="form-group">
                        <input onkeyup="validate(event)" onblur="validate(event)" type="text" name="fullName" id="fullName" class="form-control input-lg" placeholder="Full Name" tabindex="1">
					</div>
			
					<div class="form-group">
						<input onkeyup="validate(event)" onblur="validate(event)" type="text" name="address" id="address" class="form-control input-lg" placeholder="Address" tabindex="3">
					</div>
					<div class="form-group">
						<input onkeyup="validate(event)" onblur="validate(event)" type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address" tabindex="4">
					</div>
					<!-- Password Section -->
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<input onkeyup="validatePassword(event,'password_confirmation')" onblur="validatePassword(event,'password_confirmation')" type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="5">
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<input onkeyup="validatePassword(event,'password')" onblur="validatePassword(event,'password')" type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" placeholder="Confirm Password" tabindex="6">
							</div>
						</div>
					</div>
					<label>Gender :</label> 
					<div class="row">			
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<input onchange="checkCondition(true)" type="radio" name="gender" id="gender" class="form-control input-sm" tabindex="8" value="male" title="Male">
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<input onchange="checkCondition(true)" type="radio" name="gender" id="gender" class="form-control input-sm" tabindex="9" value="female" title="Female">
							</div>
						</div>
					</div>		
					<div class="form-group">
					  <label for="faculty">Faculty Id :</label>
					  <select name="faculty"  class="form-control" id="faculty">
					<%
						DBHandler handler = (DBHandler) application.getAttribute("dbHandler");
						ResultSet rs = handler.executeQuery("Select id From faculty_table");
						while (rs.next()) {
					%>
							<option><%=rs.getInt("id") %></option>
					<%
						}
					%>
					  </select>
					</div>
					
					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-12"><input type="submit" id="submit" disabled="disabled" onclick="return register()" value="Register" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
  <script src="${pageContext.request.contextPath }/Javascripts/jquery.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/Javascripts/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/Javascripts/bootbox.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/Javascripts/register_validator.js"></script>
    	
</body>
</html>