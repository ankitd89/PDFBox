var staticUrl = "http://localhost:8080";

function uploadFormData(){
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
    },
  error: function(jqXHR, status, errorThrown){
      alert(status + errorThrown);
  }
  });
}