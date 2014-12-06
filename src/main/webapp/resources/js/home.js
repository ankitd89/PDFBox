/**
 * 
 */
 var files = "";
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

function listAllFiles(){
	var fileName = "";
	var listurl = "http://localhost:8080/dropbox/files";
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: listurl,
		dataType: "text",

		success:function(data,status,jqXHR){
			fileName = data;
			files= fileName.split("\n");
			displayFiles();			
		},
		error:function(jqXHR,status,errorThrown){
			alert(status + errorThrown+"error");
		}
	});
}

function displayFiles()
{
	var div = document.createElement("div");
	div.className = "row";
	for(var i=0;i<files.length-1;i++)
	{
		//alert(files[i]);
		var innerDiv= document.createElement("div");
		innerDiv.className="col-xs-4";
		//innerDiv.style.wordWrap ="break-word";
		var aTag = document.createElement('a');
		aTag.className = "thumbnail";
		//var img = new Image();
		var img= document.createElement("IMG");
		img.src = src="/resources/images/pdfIcon.png";
		var newlabel = document.createElement("Label");
		newlabel.innerHTML = files[i];
		newlabel.style.wordWrap = "break-word";
		aTag.appendChild(img);
		aTag.appendChild(newlabel);
		innerDiv.appendChild(aTag);
		div.appendChild(innerDiv);
	}
	var containerId= document.getElementById("divContainer");
	containerId.appendChild(div);
}	
