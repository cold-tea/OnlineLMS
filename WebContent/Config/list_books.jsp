<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="h2">Books List</h2>
<hr>
<c:if test='${param["book_added"] ne null && param["email_status"] eq "sent"}'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Book ${param["book_added"] } added successfully and email sent.
	</div>
</c:if>
<c:if test='${param["book_added"] ne null && param["email_status"] ne "sent"}'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Book ${param["book_added"] } added successfully and email cannot be sent.
	</div>
</c:if>
<c:if test='${param["book"] ne null }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Book ${param["book"] } removed successfully.
	</div>
</c:if>
<c:if test='${param["book_edited"] ne null && param["book_edited"] eq "edited" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Book ${param.id } edited successfully.
	</div>
</c:if>
<c:if test='${param["book_edited"] ne null && param["book_edited"] eq "unedited" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Book ${param.id } cannot be edited.
	</div>
</c:if>
<c:if test='${param["serial_added"] ne null && param["book_serialed"] ne null }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Serial ${param["serial_added"] } is added on book ${param["book_serialed"] }.
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
<table info="ALL BOOKS" id="table" class="table table-hover">
  <thead>
  	<tr>
	  	<th>ID</th>
	  	<th>Name</th>
	  	<th>Author</th>
	  	<th>Publisher</th>
	  	<th>Edition</th>
	  	<th>Faculty</th>
	  	<th>Quantity</th>
	  	<th>Price</th>
	  	<c:if test='${sessionScope.session.username eq  "admin"}'>
	  		<th>Action</th>
	  	</c:if>
  	</tr>
  </thead>
  <tbody>
  	 <c:forEach items='${requestScope["bookList"] }' var="item" varStatus="count">
  		<tr>
  			<td>${item["id"] }</td>
  			<td>${item["name"] }</td>
  			<td>${item["author"] }</td>
  			<td>${item["publisher"] }</td>
  			<td>${item["edition"] }</td>
  			<td>${item["facultyId"] }</td>
  			<td>${item["quantity"] }</td>
  			<td>${item["price"] }</td>
  			<c:if test='${sessionScope.session.username eq  "admin"}'>
	  			<td>
	  				<a href='${pageContext.request.contextPath }/EditBooksServlet?id=${item["id"] }' class="btn btn-primary btn-xs rpad_btn">Edit</a>
	  				<a href='${pageContext.request.contextPath }/RemoveBookServlet?id=${item["id"] }' class="btn btn-danger btn-xs">Remove</a>
	  				<!-- Button trigger modal -->
					<button id='${item["id"] }' type="button" class="some_trigger btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">
					  Serials
					</button>
	  			</td>
  			</c:if>
  		</tr>
  	</c:forEach>
  </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Add Serial</h4>
      </div>
      <div class="modal-body">
      	<form role="form" method="post" action="/Online_LMS/CreateSerialServlet">
	        <div class="form-group">
	             <input onkeyup="validate(event)" onblur="validate(event)" type="number" name="id" id="serial" class="form-control input-lg" placeholder="ID" tabindex="1">
			</div>
			
			<div class="form-group">
	              <input type="number" name="book_id" id="book_id" class="trigger_target form-control input-lg" placeholder="Book ID" tabindex="2">
			</div>
	      </div>
	      <div class="form-group modal-footer">
	        <button id="submit" disabled="disabled"  onclick="return createSerial()" type="submit" class="btn btn-primary">Save</button>
	      </div>
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath }/Javascripts/bookSerial_validator.js"></script>
