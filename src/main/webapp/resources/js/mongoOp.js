function getEarningOnPaymentType()
{
	alert("in mongoOp getEarningOnPaymentType");
	var type = "Credit";
	var paymentUrl = "http://localhost:8080/getEarningsUponPaymentType/" + type;
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: paymentUrl,
		dataType: "text",

		success: function(data,status,jqXHR){
			var d=data;
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
	var billCondUrl = "http://localhost:8080/getBillsOnCondition/" + cnd +"/" + amt;
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
	alert("in mongoOp getEarningForDate");
	var date = "121214";
	var earningUrl = "http://localhost:8080/getEarningsForDate/" + date;
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: earningUrl,
		dataType: "text",

		success: function(data,status,jqXHR){
			var d=data;
			alert("getEarningsForDate" +d);
		},
		error:function(jqXHR,status,errorThrown){
			alert(status + errorThrown + "error in mongoOp");
		}
	});
}

function getBillsForDate()
{
	alert("in mongoOp getBillsForDate");
	var date = "121214";
	var billUrl = "http://localhost:8080/getBillsForDate/" + date;
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: billUrl,
		dataType: "text",

		success: function(data,status,jqXHR){
			var d=data;
			alert("getBillsForDate" +d);
		},
		error:function(jqXHR,status,errorThrown){
			alert(status + errorThrown + "error in mongoOp");
		}
	});
}