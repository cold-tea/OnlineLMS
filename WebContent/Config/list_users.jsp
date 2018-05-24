<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="h2">Users List</h2>
<hr>
<c:if test="${param.user ne null }">
	<div class="alert alert-warning alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Username ${param.user} removed successfully.
	</div>
</c:if>
<c:if test='${param["user_added"] ne null }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	User ${param["user_added"] } added successfully.
	</div>
</c:if>
<c:if test='${param["user_edited"] ne null && param["user_edited"] eq "edited" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	User ${param.username } edited successfully.
	</div>
</c:if>
<c:if test='${param["user_edited"] ne null && param["user_edited"] eq "unedited" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	User ${param.username } cannot be edited.
	</div>
</c:if>
<div class="bpad_search row">
  <div class="col-md-offset-6 col-md-6">
    <div class="input-group">
      <input type="text" id="search" class="form-control" placeholder="Search By Fields..">
      <span class="input-group-btn">
        <button class="btn btn-default" id="button" type="button">Print</button>
      </span>
    </div>
  </div>
</div>
<table info="ALL USERS" id="table" class="table table-hover">
  <thead>
  	<tr>
	  	<th>Username</th>
	  	<th>Password</th>
	  	<th>Full Name</th>
	  	<th>Address</th>
	  	<th>Email</th>
	  	<th>Faculty</th>
	  	<th>Gender</th>
	  	<th>Joined Date</th>
	  	<th>Action</th>
  	</tr>
  </thead>
  <tbody>
  	 <c:forEach items='${requestScope["userList"] }' var="item" varStatus="count">
  		<tr>
  			<td>${item["username"] }</td>
  			<td>${item["password"] }</td>
  			<td>${item["fullName"] }</td>
  			<td>${item["address"] }</td>
  			<td>${item["email"] }</td>
  			<td>${item["facultyId"] }</td>
  			<td>${item["gender"] }</td>
  			<td>${item["joinedDate"] }</td>
  			<td>
  				<a href='${pageContext.request.contextPath }/EditUsersServlet?id=${item["username"] }' class="btn btn-primary btn-xs rpad_btn">Edit</a>
  				<a href='${pageContext.request.contextPath }/RemoveUserServlet?id=${item["username"] }' class="btn btn-danger btn-xs">Remove</a>		
  			</td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>