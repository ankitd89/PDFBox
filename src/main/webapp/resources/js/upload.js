var staticUrl = "http://localhost:8080";

function uploadFormData(){
	var filesInput = document.getElementById("file2");
	if(filesInput.files[0] == null || filesInput.files[0] == "")
	{
		 var header="ERROR";
	     var msg="Please Select File!"
	     alertMessage(header,msg);  
	     return;
	}
		
    $('#result').html('');
  var oMyForm = new FormData();
  oMyForm.append("file", file2.files[0]);
  var hitUrl = staticUrl + "/dropbox/" + email + "/upload";
  $.ajax({
    url: hitUrl,
    data: oMyForm,
    dataType: "text",
    processData: false,
    contentType: false,
    type: "POST",
    success: function(data){
      $('#result').html(data);
      listAllFiles();
      var filediv=document.getElementById("file_div");
       filediv.innerHTML="<input id='file2' name='file2' class='form-control' placeholder='No file selected' type='file' style='position: absolute; left: 10px; top: 0px;'>"
    	  +"<div class='input-group-btn' style='position: relative; left: 50px; top: 0px; width: 150px; height: 50px'><button type='button' class='btn btn-info'"
			+" onclick='uploadFormData()' style='position: absolute; left: 120px; top: 0px;'><span class='glyphicon glyphicon-arrow-up'></span> Upload</button></div>";
     var header="Awesome";
     var msg="Upload Successful!!!!"
       alertMessage(header,msg);      
    },
  error: function(jqXHR, status, errorThrown){
	  var header="ERROR";
	    var msg="Could not connect to Dropbox!! Please Try Again!"
	    alertMessage(header,msg);
	    console.log(status + " " + errorThrown);
  }
  });
}