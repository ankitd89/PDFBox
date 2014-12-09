
function uploadFormData(){
    $('#result').html('');
 
  var oMyForm = new FormData();
  oMyForm.append("file", file2.files[0]);
 
  $.ajax({
    url: "http://localhost:8080/dropbox/upload",
    data: oMyForm,
    dataType: "text",
    processData: false,
    contentType: false,
    type: "POST",
    success: function(data){
      $('#result').html(data);
      listAllFiles();
    }
  });
}