<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="h2">Requests</h2>
<hr>
<c:if test='${param["removed_username"] ne null && param["removed_book"] ne null}'>
	<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Request from  ${param["removed_username"] } for book ${param["removed_book"] } is removed.
	</div>
</c:if>
<c:if test='${param["granted_username"] ne null && param["granted_book"] ne null}'>
	<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Request from  ${param["removed_username"] } for book ${param["removed_book"] } is approved.
	</div>
</c:if>
<c:if test='${param["sn_unproceed"] ne null}'>
	<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	SN ${param["sn_unproceed"] } cannot be processed because the book is unavailable.
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
<table info="ALL REQUESTS" id="table" class="table table-hover">
  <thead>
  	<tr>
  		<th>SN</th>
	  	<th>Username</th>
	  	<th>Full Name</th>
	  	<th>Book ID</th>
	  	<th>Book Name</th>
	  	<th>Request Date</th>	  	
  		<th>Action</th>
  	</tr>
  </thead>
  <tbody>
  	 <c:forEach items='${requestScope["requestList"] }' var="item" varStatus="count">
  		<tr>
  			<td>${item["sn"] }</td>
  			<td>${item["username"] }</td>
  			<td>${item["userName"] }</td>
  			<td>${item["bookId"] }</td>
  			<td>${item["bookName"] }</td>
  			<td>${item["requestDate"] }</td>
  			
  			<td>
  				<a href='${pageContext.request.contextPath }/RemoveRequestsServlet?id=${item["sn"] }' class="btn btn-danger btn-xs">Decline</a>
  				<c:if test='${sessionScope.session.username eq "admin" }'>
					<button id='${item["sn"] }' type="button" class="some_trigger btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">
					  Grant
					</button>
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
      	<form role="form" method="post" action="/Online_LMS/GrantRequestedBooksServlet">
      	    <div class="form-group">
	             <input type="number" name="sn" id="sn" class="trigger_target form-control input-lg" placeholder="SN" tabindex="1">
			</div>

			<div class="form-group">
	              <input onkeyup="validate(event)" onblur="validate(event)" type="date" name="return_date" id="return_date" class="form-control input-lg" placeholder="Return Date" tabindex="2">
			</div>
	      </div>
	      <div class="form-group modal-footer">
	        <button id="submit" disabled="disabled"  onclick="return renew()" type="submit" class="btn btn-primary">Grant</button>
	      </div>
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath }/Javascripts/renew_granted.js"></script>