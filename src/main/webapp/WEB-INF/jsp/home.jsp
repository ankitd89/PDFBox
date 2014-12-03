<%@ page contentType="text/html;charset=UTF-8" %>  
<html>  
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PDF Box</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/home.js"></script>
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
     <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

  </head>  
  <body onload="fetchAccessToken()">  
<!--
<i>Uploading File With Ajax</i><br/>
<form id="form2" method="post" action="/cmpe273-Project-PDFBox-/rest/cont/upload" enctype="multipart/form-data">
  <input name="file2" id="file2" type="file"  /><br/>
</form>
 
<button value="Submit" onclick="uploadFormData()" >Upload</button>
 
<div id="result"></div>



<form class="form-horizontal" id="form2" method="post" enctype="multipart/form-data">
<fieldset>
-->
<!-- Form Name -->
<legend>PDF Box</legend>

<!-- Button Drop Down -->
<div class="form-group">
  <label class="col-md-4 control-label" for="buttondropdown">Browse</label>
  <div class="col-md-6">
    <div class="input-group">
      <input id="file2"  name="file2" class="form-control" placeholder="No file selected" type="file">
      <div class="input-group-btn">
        <button type="button" class="btn btn-info" onclick="uploadFormData()">
          <span class="glyphicon glyphicon-arrow-up"></span>
          Upload
       
        </button>
        
      </div>
    </div>
  </div>
</div>
<div id="result"></div>

</body>
</html>
