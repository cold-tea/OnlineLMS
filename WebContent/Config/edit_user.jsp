<%@ page import="online.lms.models.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="online.lms.database.DBHandler"%>
<div class="container">
	<div class="row">
	    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form role="form" method="post" action="/Online_LMS/PostEditUsersServlet">
				<h2>Edit User</h2>
				<hr class="colorgraph">
				
				<div class="form-group">
                       <input readonly="readonly" type="text" value="${requestScope.user.username }" name="username" id="username" class="form-control input-lg" placeholder="Username" tabindex="1">
				</div>
				
				<div class="form-group">
                       <input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.user.fullName }" type="text" name="fullName" id="fullName" class="form-control input-lg" placeholder="Full Name" tabindex="1">
				</div>
		
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.user.address }" type="text" name="address" id="address" class="form-control input-lg" placeholder="Address" tabindex="3">
				</div>
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.user.email }" type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address" tabindex="4">
				</div>
				<!-- Password Section -->
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-6">
						<div class="form-group">
							<input onkeyup="validatePassword(event,'password_confirmation')" onblur="validatePassword(event,'password_confirmation')" value="${requestScope.user.password }" type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="5">
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-6">
						<div class="form-group">
							<input onkeyup="validatePassword(event,'password')" onblur="validatePassword(event,'password')" value="${requestScope.user.password }" type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" placeholder="Confirm Password" tabindex="6">
						</div>
					</div>
				</div>
				<label>Gender :</label> 
				<div class="row">			
					<div class="col-xs-12 col-sm-6 col-md-6">
						<div class="form-group">
							<input onchange="checkCondition(true)" <% if(((User)request.getAttribute("user")).getGender().equals("male")) out.write("checked"); %> type="radio" name="gender" id="gender" class="form-control input-sm" tabindex="8" value="male" title="Male">
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-6">
						<div class="form-group">
							<input onchange="checkCondition(true)" <% if(((User)request.getAttribute("user")).getGender().equals("female")) out.write("checked"); %> type="radio" name="gender" id="gender" class="form-control input-sm" tabindex="9" value="female" title="Female">
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
					<div style="padding-bottom: 15px;" class="col-xs-12 col-md-6"><input type="submit" id="submit" disabled="disabled" onclick="return register()" value="Edit" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/Javascripts/register_validator.js"></script>