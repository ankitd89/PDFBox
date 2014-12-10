var url = "http://localhost:8080";

function getEarningOnPaymentType()
{
	
	var selectId =document.getElementById("cboPayment");
	var type=selectId.options[selectId.selectedIndex].value;
	var paymentUrl = "/dropbox/"+email+"/getEarningsUponPaymentType/" + type;

			if(document.getElementById("cboPayment").selectedIndex=="0")
			{
			 var header="ERROR";
             var msg="Please Select Payment Option!"
             alertMessage(header,msg); 
             return;
            }
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: paymentUrl,
		dataType: "text",

		success: function(data,status,jqXHR){
			var d=data;
			var filterBills = d.split("\n");
			displayFiles(filterBills);
			
		},
		error:function(jqXHR,status,errorThrown){
			alert(status + errorThrown + "error in mongoOp");
		}
	});
}

function getBillsOnCondition()
{	
	var selectId = document.getElementById("cboAmount");
	var cnd = selectId.options[selectId.selectedIndex].value;
	var amtId = document.getElementById("txtAmount");
	var amt = amtId.value;
	var billCondUrl = url + "/dropbox/" + email + "/getBillsOnCondition/" + cnd +"/" + amt;

	if(document.getElementById("txtAmount").value=='' || document.getElementById("cboAmount").selectedIndex=="0")
			{
			 var header="ERROR";
             var msg="Required Fields Cannot Be Empty!"
             alertMessage(header,msg); 
             return;
            }
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: billCondUrl,
		dataType: "text",
		success: function(data,status,jqXHR){
			var d=data;
			var filterBills = d.split("\n");
			displayFiles(filterBills);
		},
		error:function(jqXHR,status,errorThrown){
			alert(status + errorThrown + "error in mongoOp");
		}
	});
}

function getBillsForDate()
{
	var dateId = document.getElementById("txtDate"); 
	var date = dateId.value;
	var billUrl = url + "/dropbox/" + email + "/getBillsForDate/" + date;

if(document.getElementById("txtDate").value=='')
			{
			 var header="ERROR";
             var msg="Please Select Date!"
             alertMessage(header,msg); 
             return;
            }

	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: billUrl,
		dataType: "text",
		success: function(data,status,jqXHR){
			var d=data;
			var filterBills = d.split("\n");
			displayFiles(filterBills);
		},
		error:function(jqXHR,status,errorThrown){
			alert(status + errorThrown + "error in mongoOp");
		}
	});
}