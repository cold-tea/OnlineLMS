<%@page import="java.sql.ResultSet"%>
<%@page import="online.lms.database.DBHandler"%>

<%! String facebook, twitter, linkedIn; int fine;%>

<%
	DBHandler handler = (DBHandler) application.getAttribute("dbHandler");
	ResultSet rs = handler.executeQuery("select * from social_table");
	while (rs.next()) {
		facebook = rs.getString("facebook");
		twitter = rs.getString("twitter");
		linkedIn = rs.getString("linkedIn");
		fine = rs.getInt("fine_per_day");
		
	}
%>
<div class="container">
	<div class="row">
	    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form role="form" method="post" action="/Online_LMS/SettingServlet">
				<h2>Setting</h2>
				<hr class="colorgraph">
				
				<div class="form-group">
                       <input type="text" value="<%=facebook %>" name="facebook" id="facebook" class="form-control input-lg" placeholder="Facebook" tabindex="1">
				</div>
				
				<div class="form-group">
                       <input type="text" value="<%=twitter %>" name="twitter" id="twitter" class="form-control input-lg" placeholder="Twitter" tabindex="2">
				</div>
		
				<div class="form-group">
                       <input type="text" value="<%=linkedIn %>" name="linkedIn" id="linkedIn" class="form-control input-lg" placeholder="linkedIn" tabindex="3">
				</div>	
				
				<div class="form-group">
                       <input type="number" value="<%=fine %>" min="2" max="50" value="<%=linkedIn %>" name="fine" id=""fine"" class="form-control input-lg" placeholder="Fine" tabindex="4">
				</div>	

				<hr class="colorgraph">
				<div class="row">
					<div style="padding-bottom: 15px;" class="col-xs-12 col-md-6"><input id="submit" type="submit" value="Update Info" class="btn btn-primary btn-block btn-lg" tabindex="5"></div>
				</div>
			</form>
		</div>
	</div>
</div>