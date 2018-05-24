<% int pageNumber = Integer.valueOf(request.getParameter("page")); %>
<%@page import="java.sql.ResultSet"%>
<%@page import="online.lms.database.DBHandler"%>

<%! String facebook, twitter, linkedIn; %>

<%
	DBHandler handler = (DBHandler) application.getAttribute("dbHandler");
	ResultSet rs = handler.executeQuery("select * from social_table");
	while (rs.next()) {
		facebook = rs.getString("facebook");
		twitter = rs.getString("twitter");
		linkedIn = rs.getString("linkedIn");
	}
%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container"> 
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span> 
            </button>
            <a target="_top" href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=1" class="navbar-brand">Library</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li <%if(pageNumber == 1) out.write("class=\"active\"");%>><a href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=1">Home</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Burrow
                    <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                    	<li class="dropdown-header">My</li>
                    	<li><a href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=11">Requests</a></li>
                    	<li class="dropdown-header">Books</li>
                    	<li><a href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=6">List All</a></li>
                        <li><a href="${pageContext.request.contextPath }/Serverpages/index.jsp?page=9">List Available</a></li>                    
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Social Sites
                    <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a target="blank" href="<%=facebook %>">Facebook</a></li>
                        <li><a target="blank" href="<%=twitter %>">Twitter</a></li>
                        <li><a target="blank" href="<%=linkedIn %>">LinkedIn</a></li>
                    </ul>
                 </li>              
             </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span> 
                        <strong>${sessionScope.session.username }</strong>
                        <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <div class="navbar-login">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <p class="text-center">
                                            <span class="glyphicon glyphicon-user icon-size"></span>
                                        </p>
                                    </div>
                                    <div class="col-lg-8">
                                        <p class="text-left"><strong>${sessionScope.session.fullName }</strong></p>
                                        <p class="text-left small">${sessionScope.session.email }</p>
                                       <!--  <p class="text-left">
                                            <a href="#" class="btn btn-primary btn-block btn-sm">Edit Credentials</a>
                                        </p> -->
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <div class="navbar-login navbar-login-session">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <p>
                                            <a href="/Online_LMS/HeaderServlet" class="btn btn-danger btn-block">Sign Out</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>