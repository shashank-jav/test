<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP"
	crossorigin="anonymous">
	
	<!-- <script>
	$(document).ready(function() {
		$('#register_form').bootstrapValidator({
			// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				username : {
					validators : {
						notEmpty : {
							message : 'Please enter your Email Address'
						},
						regexp : {
							regexp : '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
							message : 'The value is not a valid email address'
						}
					}
				},
			}
		})
	});
	</script> -->
 <script>

function changeCaptcha() {
	
	document.getElementById("captcha").value = ""; 
	document.getElementById("captchaImage").src = null;
	var captchaUrl = "<c:url value='/simpleCaptchaImg' />";
	var capFullUrl = captchaUrl+"?"+new Date().getTime();
	document.getElementById("captchaImage").src = capFullUrl;
	
}

function submitForm() {
    var req = $.ajax(
   		  {
   			  url: "${pageContext.request.contextPath}/getCaptVal",
   			  type: "GET",
   			   success: function(result){
   				  $("#captchaVal").html(result);
   			  } 
   		  }
     	  );
    req.done(function(ans) { 
   	    var register_form = document.getElementById("register_form");
	  	var captchaText = document.getElementById("captcha").value;
	  	if(ans == captchaText) { //alert('hi');
	  	/* document.getElementById("captchaVal").innerHTML = captchaText ; */
		  	register_form.action = "${pageContext.request.contextPath}/saveUser";
		  	register_form.method = "POST";
		  	register_form.submit();
		 } else {
			 document.getElementById("captcha").value = "";
			 alert("Please enter valid captcha");
		 }
   	 });
}

</script>
<style>
body, html {
	margin: 0;
	padding: 0;
	height: 100%;
	background: #60a3bc !important;
}

.register_card {
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
	padding: 15px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-moz-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	border-radius: 5px;
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
	font-size: 0.80em;
	padding-top: 10px;
}
</style>
</head>
<body>
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="register_card">
				<div class="d-flex justify-content-center">
					<div class="brand_logo_container">
						<img src="<c:url value = '/resources/assets/images/logo.png' />"
							class="brand_logo" alt="Logo">
						<!-- <img src="/logo.png" class="brand_logo" alt="Logo"> -->
					</div>

				</div>
				<div class="d-flex justify-content-center title ">
					<img
						src="<c:url value = '/resources/assets/images/logo_dgh.png' />"
						class="" alt="Logo"
						style="width: 100%; padding-left: 5px; padding-right: 5px">
				</div>
				<!-- <div class=" justify-content-center  ">
			<h3>DGH One Click</h3>
			</div> -->
				<div class=" justify-content-center form_container">
					<%-- <form:form class="well form-horizontal"
						action="${pageContext.request.contextPath}/saveUser"
						modelAttribute="registrationForm" method="post"
						id="register_form"> --%>
						<form:form class="form-login"  name='register_form' id='register_form' method='POST' modelAttribute='registrationForm'>

						
						<div class="input-group mb-3">
							<!--   <label class="labelClass" for="email">Unique Email</label> -->
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							</div>
							<input type="email" class="form-control input_user" id="username"
								placeholder="Enter unique email id" name="username">
						</div>
						
						
						<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="form-group">
								<label for="captcha" class="" style="color:red;text-shadow:-1px 0 white, 0 1px white, 1px 0 white, 0 -1px white;">Enter Captcha</label> <input type='text' <input type='text'
									name='captcha' value='' id="captcha" autocomplete="off" class="form-control input_user" />
								<div id="capchaMessage" class="help-block">${captchamsg}</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<label class="hidden-xs">&nbsp;</label>
							<c:url var="captchaImgVar" value="/simpleCaptchaImg" />
							<c:set var="currentCaptcha"></c:set>
							<div id="captchaImageDiv">
								<img src="${captchaImgVar}" border="5" id="captchaImage" /> <img
									alt="refresh" class="refresh-icon" title="Refresh Captcha"
									src="<c:url value = '/resources//images/refreshcaptchatrans.png'/>" width="10%"
									onclick="changeCaptcha();">
							</div>
						</div>
					</div>
						
<!-- <div><a style="font-size: small;" href="#" onclick="checkAll();">(Select All)</a></div> -->

						<div class="d-flex justify-content-center mt-3 login_container">
							<button type="button" class="btn login_btn" onclick="submitForm()" >Create my
								OneClick Account</button>
						</div>
					</form:form>
<font color="green">${successMsg}</font>
<font color="red"> ${errorMsg}</font>
					
					<div class="mt-4">
					
				</div>
				
				</div>
			</div>
		</div>
</body>
</html>
