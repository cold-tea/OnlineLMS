<div class="container">
	<div class="row">
	    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form role="form" method="post" action="/Online_LMS/PostEditFacultyServlet">
				<h2>Edit Faculty</h2>
				<hr class="colorgraph">
				
				<div class="form-group">
                       <input readonly="readonly" value="${requestScope.faculty.id }" type="number" name="id" id="id" class="form-control input-lg" placeholder="ID" tabindex="1">
				</div>
				
				<div class="form-group">
                       <input onkeyup="validate(event)" onblur="validate(event)" value="${requestScope.faculty.name }" type="text" name="name" id="name" class="form-control input-lg" placeholder="Name" tabindex="1">
				</div>		
				<div class="form-group">
					  <label for="system">System :</label>
					  <select name="system"  class="form-control" id="system">
					  	<option>yearly</option>
					  	<option>semester-wise</option>
					  </select>
				</div>
				<div class="form-group">
					  <label for="length">Length :</label>
					  <select name="length"  class="form-control" id="length">
					  	<option>1</option>
					  	<option>2</option>
					  	<option>3</option>
					  	<option>4</option>
					  	<option>5</option>
					  	<option>6</option>
					  	<option>7</option>
					  	<option>8</option>
					  </select>
				</div>
				<div class="form-group">
				  <label for="description">Description :</label>
				  <textarea  class="form-control" rows="5" name="description" id="description">
				  	${requestScope.faculty.description }
				  </textarea>
				</div>	
				<hr class="colorgraph">
				<div class="row">
					<div style="padding-bottom: 15px;" class="col-xs-12 col-md-6"><input id="submit" disabled="disabled"  onclick="return createFaculty()" type="submit" value="Edit" class="btn btn-primary btn-block btn-lg" tabindex="7"></div>
				</div>
			</form>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath }/Javascripts/faculty_validator.js"></script>