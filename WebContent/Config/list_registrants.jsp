<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="h2">Registrants List</h2>
<hr>
<c:if test='${param["user_removed"] ne null }'>
	<div class="alert alert-warning alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Username ${param["user_removed"] } removed successfully.
	</div>
</c:if>
<c:if test='${param["user_approved"] ne null }'>
	<div class="alert alert-warning alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Username ${param["user_approved"] } approved successfully.
	</div>
</c:if>
<c:if test='${param["user_unapproved"] ne null }'>
	<div class="alert alert-warning alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Username ${param["user_unapproved"] } can not be approved.
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
<table info="ALL REGISTRANTS" id="table" class="table table-hover">
  <thead>
  	<tr>
	  	<th>Username</th>
	  	<th>Password</th>
	  	<th>Full Name</th>
	  	<th>Address</th>
	  	<th>Email</th>
	  	<th>Faculty</th>
	  	<th>Gender</th>
	  	<th>Requested Date</th>
	  	<th>Action</th>
  	</tr>
  </thead>
  <tbody>
  	 <c:forEach items='${requestScope.registrantList }' var="item" varStatus="count">
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
  				<a href='${pageContext.request.contextPath }/ApproveRegistrantsServlet?id=${item["username"] }' class="btn btn-primary btn-xs rpad_btn">Approve</a>
  				<a href='${pageContext.request.contextPath }/RemoveRegistrantsServlet?id=${item["username"] }' class="btn btn-danger btn-xs">Decline</a>		
  			</td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>