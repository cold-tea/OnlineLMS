<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="h2">Books Available</h2>
<hr>
<c:if test='${param["serial_granted"] eq "granted" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Serial ${param["serial"] } is successfully granted to user ${param["username"] }.
	</div>
</c:if>
<c:if test='${param["serial_granted"] eq "ungranted" }'>
	<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Serial ${param["serial"] } cannot be granted to user ${param["username"] }. User might not exist.
	</div>
</c:if>
<c:if test='${param["requester_username"] ne null}'>
	<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Request for book ${param["requester_book"] } sent by user ${param["requester_username"] }.
	</div>
</c:if>
<c:if test='${param["requester_failed"] eq "failed" }'>
	<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Request failed.
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
<table info="AVAILABLE BOOKS" id="table" class="table table-hover">
  <thead>
  	<tr>
	  	<th>Serial</th>
	  	<th>ID</th>
	  	<th>Name</th>
	  	<th>Author</th>
	  	<th>Edition</th>
	  	<th>Action</th>
  	</tr>
  </thead>
  <tbody>
  	 <c:forEach items='${requestScope["availableList"] }' var="item" varStatus="count">
  		<tr>
  			<td>${item["bookSerial"] }</td>
  			<td>${item["bookId"] }</td>
  			<td>${item["bookName"] }</td>
  			<td>${item["bookAuthor"] }</td>
  			<td>${item["bookEdition"] }</td>
  			<td>
  				<c:if test='${sessionScope.session.username eq "admin" }'>
					<button id='${item["bookSerial"] }' type="button" class="some_trigger btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">
					  Grant
					</button>
				</c:if>
				<c:if test='${sessionScope.session.username ne "admin" }'>
					<a href='${pageContext.request.contextPath }/RequestBooksServlet?id=${item["bookId"] }' class="btn btn-primary btn-xs rpad_btn">Request</a>
				</c:if>
  			</td>
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
        <h4 class="modal-title" id="myModalLabel">Grant Book</h4>
      </div>
      <div class="modal-body">
      	<form role="form" method="post" action="/Online_LMS/GrantBooksServlet">
      	    <div class="form-group">
	             <input type="number" name="serial" id="serial" class="trigger_target form-control input-lg" placeholder="Serial" tabindex="1">
			</div>
      	
	        <div class="form-group">
	             <input onkeyup="validate(event)" onblur="validate(event)" type="text" name="username" id="username" class="form-control input-lg" placeholder="Username" tabindex="1">
			</div>
			
			<div class="form-group">
	              <input onkeyup="validate(event)" onblur="validate(event)" type="date" name="return_date" id="return_date" class="form-control input-lg" placeholder="Return Date" tabindex="2">
			</div>
	      </div>
	      <div class="form-group modal-footer">
	        <button id="submit" disabled="disabled"  onclick="return grantBook()" type="submit" class="btn btn-primary">Grant</button>
	      </div>
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath }/Javascripts/grant_available.js"></script>