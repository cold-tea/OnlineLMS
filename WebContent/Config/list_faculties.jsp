<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="h2">Faculties List</h2>
<hr>
<c:if test="${param.faculty ne null }">
	<div class="alert alert-warning alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Faculty ${param.faculty} removed successfully.
	</div>
</c:if>
<c:if test='${param["faculty_added"] ne null }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Faculty ${param["faculty_added"] } added successfully.
	</div>
</c:if>
<c:if test='${param["faculty_edited"] ne null && param["faculty_edited"] eq "edited" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Faculty ${param.id } edited successfully.
	</div>
</c:if>
<c:if test='${param["faculty_edited"] ne null && param["faculty_edited"] eq "unedited" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Faculty ${param.id } cannot be edited.
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
<table info="ALL FACULTIES" id="table" class="table table-hover">
  <thead>
  	<tr>
	  	<th>Id</th>
	  	<th>Name</th>
	  	<th>System</th>
	  	<th>Length</th>
	  	<th>Description</th>
	  	<th>Action</th>
  	</tr>
  </thead>
  <tbody>
  	 <c:forEach items='${requestScope["facultyList"] }' var="item" varStatus="count">
  		<tr>
  			<td>${item["id"] }</td>
  			<td>${item["name"] }</td>
  			<td>${item["system"] }</td>
  			<td>${item["length"] }</td>
  			<td>${item["description"] }</td>
  			<td>
  				<a href='${pageContext.request.contextPath }/EditFacultyServlet?id=${item["id"] }' class="btn btn-primary btn-xs rpad_btn">Edit</a>
  				<a href='${pageContext.request.contextPath }/RemoveFacultyServlet?id=${item["id"] }' class="btn btn-danger btn-xs">Remove</a>		
  			</td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>