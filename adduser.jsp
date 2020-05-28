<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous"> -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <link href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet"> -->
<link href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<!-- <link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP"
	crossorigin="anonymous"> -->
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/jquery.dataTables.min.js" />"></script>

</head>
<body>
	<div class="container ">
		<div class="d-flex justify-content-center h-100">
		
		</div>

		<hr class="my-3">
        
       <form:form class="welcome_card" style="color:white;"
						action="${pageContext.request.contextPath}/admin/submituser"
						modelAttribute="ssouserModel" method="post" enctype="multipart/form-data"
						id="register_form">

							<h4 style="text-align:center">Add New User</h4>				
					<div class="col-md-10 col-md-offset-1 ">
						<div class="input-group ">
							<label class="labelClass" for="email">First Name</label>
						</div>
						<div class="input-group mb-3">	
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control input_user" id="firstName"
								placeholder="Enter firstname" name="firstName">
						</div>
						<div class="input-group ">
							<label class="labelClass" for="email">Last Name</label>
							</div>
						<div class="input-group mb-3">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control input_user" id="lastName"
								placeholder="Enter lastname" name="lastName">
						</div>
						
						
						
						
						<div class="input-group ">
							  <label class="labelClass" for="email">Unique Email(this will serve as username for login)</label>
							  </div>
						<div class="input-group mb-3">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							</div>
							<input type="email" class="form-control input_user" id="username"
								placeholder="Enter unique email id" name="username">
						</div>
						
						<div class="row ">
						
						<form method="POST" th:action="@{/upload-csv-file}" enctype="multipart/form-data">
    <div class="form-group mt-3">
        <label for="file">Select a CSV file</label>
        <input type="file" name="file" class="form-control-file" id="file" accept=".csv">
    </div>
    <button type="submit" class="btn btn-primary">Import Users</button>
</form>

						<div class="d-flex justify-content-center  login_container">
							<button type="submit" class="btn btn-danger">Add User</button>&nbsp;&nbsp;&nbsp;
							
							
						</div>
						</div>
						
					</form:form>
	
		<font color="green">${successMsg}</font>
		<font color="red"> ${errorMsg}</font>




	</div>



	<font color="green">${successMsg}</font>
	<font color="red"> ${errorMsg}</font>

	

</body>
</html>
