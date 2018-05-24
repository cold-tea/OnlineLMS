<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="h2">Fines List</h2>
<hr>
<c:if test='${param["removed_fine"] ne null }'>
	<div class="alert alert-warning alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	Serial ${param["removed_fine"] } removed from fine and the granted list.
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
<table info="FINES LIST" id="table" class="table table-hover">
  <thead>
  	<tr>
	  	<th>Book Serial</th>
	  	<th>Book Name</th>
	  	<th>Username</th>
	  	<th>Full Name</th>
	  	<th>Return Date</th>
	  	<th>Today's Date</th>
	  	<th>Days Exceeded</th>
	  	<th>Fine Amount</th>
	  	<th>Action</th>
  	</tr>
  </thead>
  <tbody>
  	 <c:forEach items='${requestScope["fineList"] }' var="item" varStatus="count">
  		<tr>
  			<td>${item["bookSerial"] }</td>
  			<td>${item["bookName"] }</td>
  			<td>${item["username"] }</td>
  			<td>${item["userName"] }</td>
  			<td>${item["returnDate"] }</td>
  			<td>${item["todayDate"] }</td>
  			<td>${item["daysExceeded"] }</td>
  			<td>${item["fine"] }</td>
  			<td>
  				<a href='${pageContext.request.contextPath }/RemoveFineServlet?id=${item["bookSerial"] }' class="btn btn-danger btn-xs">Remove</a>
  			</td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>