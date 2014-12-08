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
	var containerId= document.getElementById("divContainer");
	containerId.innerHTML="";
	var divContainerId= document.getElementById("divFilesDisplay");
	var div = document.createElement("div");
	div.className = "row";
	for(var i=0;i<files.length-1;i++)
	{
		var innerDiv= document.createElement("div");
		innerDiv.className="col-xs-4";
		innerDiv.id="division"+i;
		//innerDiv.style.wordWrap ="break-word";
		var aTag = document.createElement('a');
		aTag.className = "thumbnail";
		aTag.id = "aTag"+i;
		//var img = new Image();
		var img= document.createElement("IMG");
		img.src = src="/resources/images/pdfIcon.png";
		img.id="img"+i;
		var newlabel = document.createElement("Label");
		newlabel.id="label"+i;
		var labelid= newlabel.id;
		//alert(labelid);
		newlabel.innerHTML = files[i];
		newlabel.style.wordWrap = "break-word";
		newlabel.style.height = "30px";
		newlabel.style.width= "100px";
		aTag.appendChild(img);
		aTag.appendChild(newlabel);
		innerDiv.appendChild(aTag);
		div.appendChild(innerDiv);
		newlabel.onclick=function(){
			divContainerId.innerHTML="";
			showFiles(this.id);
		}
		//aTag.setAttribute("onclick","showFiles();");
		//alert(document.getElementById("aTag" + i));
		//document.getElementById("aTag" + i).onclick=function(){
			
		//	alert(tempid);
		//	showFiles(aTag);
			//alert(this.labelid);
		//}
		//aTag.addEventListener("onclick",showFiles(this.labelid));
	}
	//var containerId= document.getElementById("divContainer");
	containerId.appendChild(div);
}	

function showFiles(a)
{
	var tempid = document.getElementById(a).innerHTML;
	alert(tempid)
	//alert("Label is " + tempid);
	//alert(a);
	var downloadurl = "http://localhost:8080/dropbox/download/" +tempid;
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: downloadurl,
		dataType: "text",

		success:function(data,status,jqXHR){
			//fileName = data;
			//files= fileName.split("\n");
			//displayFiles();			
			alert("File downloaded");
			showClickedFile(tempid);

		},
		error:function(jqXHR,status,errorThrown){
			alert(status + errorThrown+"error");
		}
	});

}

function showClickedFile(t)
{
	alert("in showClickedFile" +t);
	var containerId=document.getElementById("divFilesDisplay");
	//containerId.innerHTML="";
	var fileObj = document.createElement("object");
	fileObj.data="/resources/images/" +t;
	fileObj.type="application/pdf";
	fileObj.left="0px";
	fileObj.width="400px";
	fileObj.height="490px";

	containerId.appendChild(fileObj);
}
