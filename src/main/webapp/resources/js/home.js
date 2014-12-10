/**
 * 
 */
var url = "http://localhost:8080";
var email="";
function filterOptions()
{	
	var selectId = document.getElementById("cboFilter");
	var selectedValue = selectId.options[selectId.selectedIndex].value;
	var divAmt = document.getElementById("divAmount");
	var divDate = document.getElementById("divDate");
	var divPaymentMode = document.getElementById("divPayment");
	switch(selectedValue)
	{
	case "Amount":
		divAmt.hidden = false;
		divDate.hidden = true;
		divPaymentMode.hidden = true;
		break;
	case "Date":
		divAmt.hidden = true;
		divDate.hidden = false
		divPaymentMode.hidden = true;
		break;
	case "Payment Mode":
		divAmt.hidden = true;
		divDate.hidden = true;
		divPaymentMode.hidden = false;
		break;
	default:
		divAmt.hidden = true;
		divDate.hidden = true;
		divPaymentMode.hidden = true;
		break;
	}
	listAllFiles();

}
 
function fetchAccessToken()
{
	var access_token ="";
	var windowUrl = window.location.href;
	var query = windowUrl.split("#");
	var query2 = windowUrl.split("?");
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
		var urlPara =  url + "/accesstokens?access_token="+ access_token.toString();
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: urlPara,
	        dataType: "text",
	        success:function(data,status,jqXHR){
				email = data;
				listAllFiles();
			},
			error:function(jqXHR,status,errorThrown){
			}   
	    });
	}
	else if(query2[1] != null)
	{
		
		if(query2[1]!=null){
			var emailHolder = query2[1].split("=");
			email = emailHolder[1];
			listAllFiles();
		}
	}
	else
	{
		window.open("/login", "_self");
	}
}	

function listAllFiles(){
	var files = "";
	var fileName = "";
	var listurl = url + "/dropbox/" + email + "/files";
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: listurl,
		dataType: "text",

		success:function(data,status,jqXHR){
			fileName = data;
			files= fileName.split("\n");
			displayFiles(files);			
		},
		error:function(jqXHR,status,errorThrown){
			alert(status + errorThrown+"error");
		}
	});
}

function displayFiles(displayFilesArray)
{
	var containerId= document.getElementById("divContainer");
	containerId.innerHTML="";
	var divContainerId= document.getElementById("divFilesDisplay");
	var div = document.createElement("div");
	div.className = "row";
	for(var i=0;i<displayFilesArray.length-1;i++)
	{
		var innerDiv= document.createElement("div");
		innerDiv.className="col-xs-4";
		innerDiv.id="division"+i;
		var aTag = document.createElement('a');
		aTag.className = "thumbnail";
		aTag.id = "aTag"+i;
		var img= document.createElement("IMG");
		img.src = src="/resources/images/pdfIcon.png";
		img.id="img"+i;
		var newlabel = document.createElement("Label");
		newlabel.id="label"+i;
		var labelid= newlabel.id;
		newlabel.innerHTML = displayFilesArray[i];
		newlabel.style.wordWrap = "break-word";
		newlabel.style.height = "30px";
		newlabel.style.width= "100px";
		aTag.appendChild(img);
		aTag.appendChild(newlabel);
		innerDiv.appendChild(aTag);
		div.appendChild(innerDiv);
		newlabel.onclick=function(){
			divContainerId.innerHTML="";
			showClickedFile(this.id);
		}
	}
	containerId.appendChild(div);
}	

function showFiles(a)
{
	var tempid = document.getElementById(a).innerHTML;
	var downloadurl = url + "/dropbox/" + email + "/download/" +tempid;
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: downloadurl,
		dataType: "text",
		success:function(data,status,jqXHR){
			showClickedFile(tempid);
		},
		error:function(jqXHR,status,errorThrown){
		}
	});

}

function showClickedFile(t)
{
	var tempid = document.getElementById(t).innerHTML;
	var refe = tempid;
	var clickedUrl = url + "/dropbox/" + email + "/getMetaDataFroBill/"+refe;
	$.ajax({
		type : "GET",
		url: clickedUrl,
		dataType: "text",
		success:function(data){
			var containerId=document.getElementById("divFilesDisplay");		
			containerId.innerHTML=data;
		},
		error:function(jqXHR,status,errorThrown){
		 	alert(status + errorThrown+"error");
		 }

	});
}


function logout() {
	window.open("/login", "_self");
}
