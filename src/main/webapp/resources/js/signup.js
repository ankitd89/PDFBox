//var host = "http://pdfbox.elasticbeanstalk.com";
var host = "http://localhost:8080";
function fetchSignUpValues() {
	var email = document.getElementById("login-username").value;
	var alertId = document.getElementById("login-alert");
	if(email == "")
	{
		alertId.style.display = "inline";
		document.getElementById("lblError").innerHTML = "Email cannot be blank";
		return false
	}
	var blnValidate = validateEmail(email);
	if(!blnValidate)
	{
		alertId.style.display = "inline";
		document.getElementById("lblError").innerHTML = "Email format is invalid";
		return blnValidate;
	}
	var url = host + "/signin/" + email;
	var homeurl = "/home"+"?email="+email;
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url:  url,
        dataType: "text",
        success: function(data,status, jqXHR){
        	if(data == "success")
        			window.open(homeurl, "_self");  
        	else
        	{
        		alertId.style.display = "inline";
        		document.getElementById("lblError").innerHTML = "Email entered is not registered. Please Sign Up with Dropbox.";
        	}
        },
        error: function(jqXHR, status, errorThrown){
            alert(status + errorThrown);
        }
    });
}

function validateEmail(email) {
    
    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=email.length)
        return false;
    else 
    	return true;
}

function closeErrorMsg()
{
	var alertId = document.getElementById("login-alert");
	alertId.style.display = "none";

}