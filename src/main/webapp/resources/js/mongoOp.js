var url = "http://localhost:8080";

function getEarningOnPaymentType()
{
	alert("in mongoOp getEarningOnPaymentType");
	
	var selectId =document.getElementById("cboPayment");
	var type=selectId.options[selectId.selectedIndex].value;
	var paymentUrl = "/dropbox/"+email+"/getEarningsUponPaymentType/" + type;
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: paymentUrl,
		dataType: "text",

		success: function(data,status,jqXHR){
			var d=data;
			var filterBills = d.split("\n");
			displayFiles(filterBills);
			alert("getEarningOnPaymentType" +d);
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

function getEarningForDate()
{
	var date = "121214";
	var earningUrl = "http://localhost:8080/getEarningsForDate/" + date;
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: earningUrl,
		dataType: "text",

		success: function(data,status,jqXHR){
			var d=data;
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