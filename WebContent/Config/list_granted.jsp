<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="h2">Books Granted</h2>
<hr>
<c:if test='${param["serial_renewed"] eq "renewed" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Serial ${param["serial"] } is successfully renewed till ${param["returnDate"] }.
	</div>
</c:if>
<c:if test='${param["serial_renewed"] eq "unrenewed" }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Serial ${param["serial"] } cannot be renewed till ${param["returnDate"] }.
	</div>
</c:if>
<c:if test='${param["serial_available"] ne null }'>
	<div class="alert alert-success alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Serial ${param["serial_available"] } is available now.
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
<table info="GRANTED BOOKS" id="table" class="table table-hover">
  <thead>
  	<tr>
	  	<th>Serial</th>
	  	<th>Book Name</th>
	  	<th>Username</th>
	  	<th>Full Name</th>
	  	<th>Received Date</th>
	  	<th>Return Date</th>
	  	<th>Action</th>
  	</tr>
  </thead>
  <tbody>
  	 <c:forEach items='${requestScope["grantedList"] }' var="item" varStatus="count">
  		<tr>
  			<td>${item["bookSerial"] }</td>
  			<td>${item["bookName"] }</td>
  			<td>${item["username"] }</td>
  			<td>${item["userName"] }</td>
  			<td>${item["receivedDate"] }</td>
  			<td>${item["returnDate"] }</td>
  			<td>
  				<a href='${pageContext.request.contextPath }/RemoveGrantedBookServlet?id=${item["bookSerial"] }' class="btn btn-danger btn-xs">Remove</a>
				<button id='${item["bookSerial"] }' type="button" class="some_trigger btn btn-success btn-xs" data-toggle="modal" data-target="#myModal">
				  Renew
				</button>
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
        <h4 class="modal-title" id="myModalLabel">Renew Book</h4>
      </div>
      <div class="modal-body">
      	<form role="form" method="post" action="/Online_LMS/RenewGrantedBooksServlet">
      	    <div class="form-group">
	             <input type="number" name="serial" id="serial" class="trigger_target form-control input-lg" placeholder="Serial" tabindex="1">
			</div>

			<div class="form-group">
	              <input type="date" onkeyup="validate(event)" onblur="validate(event)" name="return_date" id="return_date" class="form-control input-lg" placeholder="Return Date" tabindex="2">
			</div>
	      </div>
	      <div class="form-group modal-footer">
	        <button id="submit" disabled="disabled"  onclick="return renew()" type="submit" class="btn btn-primary">Renew</button>
	      </div>
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath }/Javascripts/renew_granted.js"></script>