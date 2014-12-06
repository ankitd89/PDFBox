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
  <body onload="fetchAccessToken(); listAllFiles()">  
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
<div class="container-fluid">
    <div class="row-fluid">
      <div id="divContainer" class="col-lg-4 "
        style="border: thick; border-style: solid; height: 500px;">
      </div>
      <div class="col-lg-4 "
        style="border: thick; border-style: solid; height: 500px;">
      </div>
      <div class="col-lg-4 "
        style="border: thick; border-style: solid; height: 500px;">
<div class="form-group">
          <label class="col-md-5 control-label" for="buttondropdown"
            style="position: absolute; left: 0px; top: 0px;">Browse</label>
          <div class="col-md-6"
            style="position: relative; left: 50px; top: 0px; width: 550px; height: 100px">
            <div class="input-group"
              style="position: relative; left: 0px; top: 0px; width: 225px; height: 100px">
              <input id="file2" name="file2" class="form-control"
                placeholder="No file selected" type="file"
                style="position: absolute; left: 10px; top: 0px;">
              <div class="input-group-btn"
                style="position: relative; left: 50px; top: 0px; width: 150px; height: 100px">
                <button type="button" class="btn btn-info"
                  onclick="uploadFormData()"
                  style="position: absolute; left: 120px; top: 0px;">
                  <span class="glyphicon glyphicon-arrow-up"></span> Upload

                </button>

              </div>
            </div>
          </div>
        </div>
        <div id="result"></div>

      </div>
    </div>
  </div>
</body>
</html>
