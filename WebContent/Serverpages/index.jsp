<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% int pageNumber = Integer.valueOf(request.getParameter("page")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath }/Stylesheets/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/Stylesheets/index-navbar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/Stylesheets/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/Stylesheets/login.css">
<title>Home Page</title>
</head>
<body>
	<c:if test="${empty sessionScope.session}">
		<% response.sendRedirect("/Online_LMS/LoginForm"); %>
	</c:if>
	<c:if test="${sessionScope.session.username eq 'admin' }">
		<jsp:include page="../Config/admin_header.jsp"></jsp:include>
	</c:if>
	<c:if test="${sessionScope.session.username ne 'admin' }">
		<jsp:include page="../Config/user_header.jsp"></jsp:include>
	</c:if>	
	<jsp:include page="../Config/carousel.jsp"></jsp:include>
	<div class="container main_content">
	<c:if test='${param["page"] eq 1 }'>
		
		<jsp:include page="../Config/home_content.jsp"></jsp:include>
	</c:if>
	<c:if test='${param["page"] eq 6 }'>
			<jsp:include page="/ListBooksServlet"></jsp:include>
	</c:if>
	<c:if test='${param["page"] eq 9 }'>
		<jsp:include page="/ListAvailableBooksServlet"></jsp:include>
	</c:if>
	<c:if test='${param["page"] eq 11 }'>
			<jsp:include page="/ListRequestsServlet"></jsp:include>
	</c:if>
	<c:if test='${sessionScope.session.username eq "admin" }'>		
		<c:if test='${param["page"] eq 2 }'>
			<jsp:include page="../Config/create_user.jsp"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 3 }'>
			<jsp:include page="/ListUsersServlet"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 4 }'>
			<jsp:include page="/ListRegistrantsServlet"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 5 }'>
			<jsp:include page="../Config/create_book.jsp"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 7 }'>
			<jsp:include page="../Config/create_faculty.jsp"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 8 }'>
			<jsp:include page="/ListFacultiesServlet"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 10 }'>
			<jsp:include page="/ListGrantedBooksServlet"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 12 }'>
			<jsp:include page="/ListFinesServlet"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 13 }'>
			<jsp:include page="../Config/settings.jsp"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 100 }'>
			<jsp:include page="../Config/edit_user.jsp"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 200 }'>
			<jsp:include page="../Config/edit_book.jsp"></jsp:include>
		</c:if>
		<c:if test='${param["page"] eq 300 }'>
			<jsp:include page="../Config/edit_faculty.jsp"></jsp:include>
		</c:if>
	</c:if>
	<c:if test='${sessionScope.session.username ne "admin" }'>
		
	</c:if>
	</div>
	
	<jsp:include page="../Config/footer.jsp"></jsp:include>
	
	<script src="${pageContext.request.contextPath }/Javascripts/jquery.js"></script>
	<script src="${pageContext.request.contextPath }/Javascripts/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/Javascripts/bootbox.min.js"></script>
	<script src="${pageContext.request.contextPath }/Javascripts/search.js"></script>
	
	<script>
      $(function() {
        $('.carousel').carousel({
          interval: 4000
        });
      });
      $('.some_trigger').on('click',function(){
			var $value = $(this).attr('id');
			$('.trigger_target').attr('value',$value);
			$('.trigger_target').attr('readonly','readonly');
	  });
    </script>
    
    <script>
		function printData()
		{
		   var divToPrint=document.getElementById("table");
		   var tblInfo = divToPrint.getAttribute("info");
		   newWin= window.open("");
		   newWin.document.write("<center style='margin-bottom:20px; font-wei'>"+tblInfo+"</center>");
		   newWin.document.write(divToPrint.outerHTML);
		   newWin.document.write("<style> #table, tr, td, th {border: 1px solid black; border-collapse: collapse;} </style>");
		   newWin.document.write("<style> th, td {padding: 7px;} </style>");
		   newWin.document.write("<style> th:last-child{display:none;} </style>");
		   newWin.document.write("<style> td:last-child{display:none;} </style>");
		   newWin.print();
		   //newWin.close();
		}
		
		$('#button').on('click',function(){
		printData();
		})
    </script>
    
</body>
</html>