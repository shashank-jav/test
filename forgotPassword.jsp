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
<link
	href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> 
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/jquery.dataTables.min.js" />"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<script>
$(document).ready(function() {
$(".toggle-password").click(function() {

  $(this).toggleClass("fa-eye fa-eye-slash");
  var input = $($(this).attr("toggle"));
  if (input.attr("type") == "password") {
    input.attr("type", "text");
  } else {
    input.attr("type", "password");
  }
})
});
</script>
<script>
function check() { 
	if (document.getElementById('password').value ==
    document.getElementById('confirm_password').value) {
    document.getElementById('message').style.color = 'white';
    document.getElementById('message').innerHTML = 'matching';
    document.getElementById("resetbutton").disabled = false;
  } else {
    document.getElementById('message').style.color = 'yellow';
    document.getElementById('message').innerHTML = 'not matching';
    document.getElementById("resetbutton").disabled = true;
  }
}
</script>

<style>
body, html {
	margin: 0;
	padding: 0;
	height: 100%;
	background: #60a3bc !important;
}

.user_card {
	height: 500px;
	width: 400px;
	margin-top: auto;
	margin-bottom: auto;
	/*  background: #f39c12; */
	background: #f5af41;
	position: relative;
	display: flex;
	justify-content: center;
	flex-direction: column;
	padding: 10px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-moz-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	border-radius: 5px;
}

.register_card {
	height: 600px;
	width: 600px;
	margin-top: auto;
	margin-bottom: auto;
	/*  background: #f39c12; */
	background: #f5af41;
	position: relative;
	display: flex;
	justify-content: center;
	flex-direction: column;
	padding: 10px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-moz-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	border-radius: 5px;
}

.header_card {
	height: 100px;
	width: 1100px;
	margin-top: 80px;
	margin-bottom: auto;
	/*  background: #f39c12; */
	background: #f5af41;
	position: relative;
	display: flex;
	/* justify-content: center; 
	flex-direction: column;*/
	padding: 10px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-moz-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	border-radius: 5px;
}

.welcome_card {
	min-height: 20px;
	padding: 19px;
	margin-bottom: 20px;
	background-color: #6B8E23;;
	border: 1px solid #e3e3e3;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05)
}

.tile_card {
	min-height: 20px;
	padding: 19px;
	margin-bottom: 20px;
	background-color: #60a3bc;
	border: 1px solid #e3e3e3;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05)
}

.brand_logo_container {
	position: absolute;
	height: 170px;
	width: 170px;
	top: -75px;
	border-radius: 50%;
	background: #60a3bc;
	padding: 10px;
	text-align: center;
}

.brand_logo {
	height: 150px;
	width: 150px;
	border-radius: 50%;
	border: 2px solid white;
}

.form_container {
	margin-top: 20px;
}

.title {
	margin-top: 50px;
}

.login_btn {
	width: 100%;
	background: #ed1c24 !important;
	color: white !important;
}

.login_btn:focus {
	box-shadow: none !important;
	outline: 0px !important;
}

.login_container {
	padding: 0 2rem;
}

.input-group-text {
	background: #ed1c24 !important;
	color: white !important;
	border: 0 !important;
	border-radius: 0.25rem 0 0 0.25rem !important;
}

.input_user, .input_pass:focus {
	box-shadow: none !important;
	outline: 0px !important;
}

.custom-checkbox .custom-control-input:checked ~.custom-control-label::before
	{
	background-color: #ed1c24 !important;
}

.labelClass {
	/* font-size: 0.80em;
	padding-top: 10px; 
	color:white;*/
	
}

ul {
	border-left: 5px solid red;
	/*background-color: #f1f1f1;*/
	background-color: pink;
	list-style-type: none;
	padding: 10px 20px;
}

.switch {
	position: relative;
	display: inline-block;
	width: 60px;
	height: 34px;
}

.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 26px;
	width: 26px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}
.field-icon {
  float: right;
  margin-left: -25px;
  margin-top: -25px;
  position: relative;
  z-index: 2;
}
</style>

</head>
<body>
	<div class="container ">
		<div class="d-flex justify-content-center h-100">
			<div class="header_card">
				<div class="d-flex justify-content-center">
					<div class="brand_logo_container">
						<img src="<c:url value = '/resources/assets/images/logo.png' />"
							class="brand_logo" alt="Logo">
						<!-- <img src="/logo.png" class="brand_logo" alt="Logo"> -->

					</div>

				</div>

							
				<div class=" justify-content-center  ">

					<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Single
						Window Clearance System</h1>
					<h3>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="white">Your OneClick Dashboard</font>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<%-- <p style="font-size:0.5em">${operatorname } ${companyname }</p> --%>
						<a href="${pageContext.request.contextPath}/logout"> <i
							class="icon-share"></i>Log out
						</a>
				</div>
			</div>
		</div>

		<hr class="my-3">
		<form:form
		action="${pageContext.request.contextPath}/sendPasswordResetLink"
		modelAttribute="sendLinkForm" method="post" id="sendLinkForm">
			<h4 style="text-align: center">Change Password</h4>
			<div class="form-group">
					<label class="col-md-4 control-label">Your Username</label>
					<input type="text" name="username"/>
					
				
			</div>
			
			<span id='message'></span>
			<!-- <div class="form-group">
				<label class="col-xs-4 control-label"></label>
				<div class="col-xs-5">
					<button type="submit" name="submit" value="submit" id="resetbutton"
						class="btn btn-primary">Reset</button>

				</div>
				
			</div> -->

				<div class="d-flex justify-content-center  login_container">
							<button type="submit" class="btn btn-danger" id="resetbutton">Reset</button>&nbsp;&nbsp;&nbsp;
							
							<a href="${pageContext.request.contextPath}/user/dashboard" class="btn btn-primary">Back to Dashboard</a>
						</div>
		</form:form>
		



	</div>



	<font color="green">${successMsg}</font>
	<font color="red"> ${errorMsg}</font>


	<div style="padding-top: 400px"></div>
	<hr class="my-3">
	<%@include file="footer.jsp"%>


</body>
</html>
