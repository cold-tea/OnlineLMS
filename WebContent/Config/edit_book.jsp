<%@ page import="online.lms.models.Book"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="online.lms.database.DBHandler"%>
<div class="container">
	<div class="row">
	    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form role="form" method="post" action="/Online_LMS/PostEditBooksServlet">
				<h2>Edit Book</h2>
				<hr class="colorgraph">
				
				<div class="form-group">
                       <input readonly="readonly" value="${requestScope.book.id }" type="number" name="id" id="id" class="form-control input-lg" placeholder="ID" tabindex="1">
				</div>
				
				<div class="form-group">
                       <input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.book.name }" type="text" name="name" id="name" class="form-control input-lg" placeholder="Name" tabindex="1">
				</div>
		
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.book.author }" type="text" name="author" id="author" class="form-control input-lg" placeholder="Author" tabindex="3">
				</div>
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.book.publisher }" type="text" name="publisher" id="publisher" class="form-control input-lg" placeholder="Publisher" tabindex="4">
				</div>
				<div class="form-group">
					<input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.book.edition }" type="text" name="edition" id="edition" class="form-control input-lg" placeholder="Edition" tabindex="4">
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
					<input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.book.price }" type="number" name="price" id="price" class="form-control input-lg" placeholder="Price" tabindex="4">
				</div>
				<hr class="colorgraph">
				<div class="row">
					<div style="padding-bottom: 15px;" class="col-xs-12 col-md-6"><input id="submit" disabled="disabled"  onclick="return createBook()" type="submit" value="Edit" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
				</div>
			</form>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath }/Javascripts/book_validator.js"></script>