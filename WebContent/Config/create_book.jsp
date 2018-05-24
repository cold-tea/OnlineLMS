<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="online.lms.database.DBHandler"%>
<c:if test='${param.status eq "fail" }'>
	<div  style="margin-top: 15px" class="alert alert-warning alert-dismissible" role="alert">
	<button  type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Provide Valid Credentials.
	</div>
</c:if>
<div class="container">
	<div class="row">
	    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form role="form" method="post" action="/Online_LMS/CreateBookServlet">
				<h2>Create Book</h2>
				<hr class="colorgraph">
				
				<div class="form-group">
                       <input onkeyup="validate(event)" onblur="validate(event)" type="number" name="id" id="id" class="form-control input-lg" placeholder="ID" tabindex="1">
				</div>
				
				<div class="form-group">
                       <input onkeyup="validate(event)" onblur="validate(event)" type="text" name="name" id="name" class="form-control input-lg" placeholder="Name" tabindex="1">
				</div>
		
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" type="text" name="author" id="author" class="form-control input-lg" placeholder="Author" tabindex="3">
				</div>
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" type="text" name="publisher" id="publisher" class="form-control input-lg" placeholder="Publisher" tabindex="4">
				</div>
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" type="text" name="edition" id="edition" class="form-control input-lg" placeholder="Edition" tabindex="4">
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
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" type="number" name="price" id="price" class="form-control input-lg" placeholder="Price" tabindex="4">
				</div>
				<hr class="colorgraph">
				<div class="row">
					<div style="padding-bottom: 15px;" class="col-xs-12 col-md-6"><input id="submit" disabled="disabled"  onclick="return createBook()" type="submit" value="Create" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
				</div>
			</form>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath }/Javascripts/book_validator.js"></script>