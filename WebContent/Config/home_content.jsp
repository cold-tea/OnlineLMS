<%@page import="online.lms.models.User"%>
<%@page import="online.lms.models.Admin"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
  <div class="row">
    <div class="col-xs-12">
      <h2>FOUR MODES</h2><hr>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-6 col-md-3">
      <img class="img-responsive img-circle" src="${pageContext.request.contextPath }/Images/individual.png" />
      <h3>Users</h3>
      <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
      <c:if test='${sessionScope.session.username eq "admin" }'>
      	<p><a class="btn btn-default" href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=3">View Users &raquo;</a></p>
      </c:if>
    </div>
    <div class="col-sm-6 col-md-3">
      <img class="img-responsive img-circle" src="${pageContext.request.contextPath }/Images/book.png" />
      <h3>Book</h3>
      <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
      <c:if test='${sessionScope.session.username eq "admin" }'>
      	<p><a class="btn btn-default" href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=6">View Books &raquo;</a></p>
      </c:if>
    </div>
    <div class="clearfix hidden-md hidden-lg"></div>
    <div class="col-sm-6 col-md-3">
      <img class="img-responsive img-circle" src="${pageContext.request.contextPath }/Images/faculty.jpg" />
      <h3>Faculty</h3>
      <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
      <c:if test='${sessionScope.session.username eq "admin" }'>
      	<p><a class="btn btn-default" href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=8">View Faculties &raquo;</a></p>
      </c:if>
    </div>
    <div class="col-sm-6 col-md-3">
      <img class="img-responsive img-circle" src="${pageContext.request.contextPath }/Images/borrow-books.jpg" />
      <h3>Transaction</h3>
      <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim</p>
      <c:if test='${sessionScope.session.username eq "admin" }'>
      	<p><a class="btn btn-default" href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=10">Transacted Books &raquo;</a></p>
      </c:if>
    </div>
  </div>
</div>