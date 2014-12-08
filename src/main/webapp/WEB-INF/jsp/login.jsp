   <!DOCTYPE html>
<html>
  <head>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>PDFBox</title>
    
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
     
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
	<link href="${pageContext.request.contextPath}/resources/css/default.css"  rel="stylesheet">    
	<script>
  function preventBack(){window.history.forward();}
  setTimeout("preventBack()", 0);
  window.onunload=function(){null};
</script>
  </head>
  <body>
  <script type="text/javascript">
window.history.forward(1);
</script>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>
				
				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12">
						 <a class="close" data-dismiss="alert" onclick="closeErrorMsg()">×</a>  
						 <strong>Error!</strong> <label id= "lblError"></label>
					</div>

					<form id="loginform" class="form-horizontal" role="form">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="email" class="form-control" name="username" value=""
								placeholder="email" />
						</div>

				<!-- 		<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="password"
								placeholder="password" />
						</div>-->

						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">
								<a id="btn-login" class="btn btn-success" onclick="fetchSignUpValues()">Login </a> 
								<label>or</label>
								<a class="btn btn-social-icon btn-dropbox" rel="nofollow" href="http://www.dropbox.com/1/oauth2/authorize?client_id=4f242qr19c5vyy8&response_type=token&redirect_uri=http://localhost:8080/home"><i class="fa fa-dropbox"></i></a>								 
							</div>
						</div>

						<div class="input-group">
							<div class="checkbox">
								<label> <input id="login-remember" type="checkbox"
									name="remember" value="1" /> Remember me
								</label>
							</div>
						</div>
						<div style="margin-top: 10px" class="form-group"></div>
						
					</form>
				</div>
			</div>
		</div>
	</div> 
	  
  </body>
</html>
