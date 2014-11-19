
function fetchSignUpValues() {
	var email = document.getElementById("email").value;
	var firstName = document.getElementById("firstname").value;
	var lastName = document.getElementById("lastname").value;
	var password = document.getElementById("password").value;
	var str={
			"email" : email,
		    "firstName":firstName,
		    "lastName":lastName,
		    "password":password
		      };
	
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/signup",
        dataType: "json",
        data: JSON.stringify(str),
        success: function(data,textStatus, jqXHR){
            
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert(textStatus + errorThrown);
        }
    });
}
