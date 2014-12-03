/**
 * 
 */
function fetchAccessToken()
{
	var access_token ="";
	var url = window.location.href;
	var query = url.split("#");
	if(query[1] != null )
	{
		var vars = query[1].split("&");
		
		for (var i=0;i<vars.length;i++) 
		{
				var pair = vars[i].split("=");
				if(pair[0] == "access_token")
				{
					access_token = pair[1];
					break;
				}
		}
		var urlPara =  "http://localhost:8080/accesstokens?access_token="+ access_token.toString();
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: urlPara,
	        dataType: "json",
	        
	    });
	}
	
}		
