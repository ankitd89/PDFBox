<%@ page contentType="text/html;charset=UTF-8" %>  
<html>  
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PDF Box</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/home.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mongoOp.js"></script>
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
     <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

  </head>
<body onload="fetchAccessToken();" style="overflow: hidden;">
	<script type="text/javascript">window.history.forward(1);</script>
	<div style="top: 0px; height: 50px; border-style: ridge; left: 0px;">
		<label
			style="position: relative; top: 0px; left: 600px; font-size: xx-large;">PDF
			Box </label> <a onclick="logout()" class="btn btn-info"
			style="left: 1250px; position: absolute;"><span
			class="glyphicon glyphicon-log-out"></span> Logout </a>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div id="divContainer" class="col-lg-4 "
        style="border: thick; border-style: solid; height: 500px; overflow:scroll;">
      </div>
      <div id= "divFilesDisplay" class="col-lg-4 "
        style="border: thick; border-style: solid; height: 500px; ">
        </object>

      </div>
			<div class="col-lg-4 "
				style="border: thick; border-style: solid; height: 500px;">
				<div class="form-group">
					<label class="col-md-5 control-label" for="buttondropdown"
						style="position: absolute; left: 10px; top: 8px;">Browse</label>
					<div class="col-md-6"
						style="position: relative; left: 50px; top: 0px; width: 550px; height: 50px">
						<div class="input-group"
							style="position: relative; left: 0px; top: 0px; width: 225px; height: 50px">
							<input id="file2" name="file2" class="form-control"
								placeholder="No file selected" type="file"
								style="position: absolute; left: 10px; top: 0px;">
							<div class="input-group-btn"
								style="position: relative; left: 50px; top: 0px; width: 150px; height: 50px">
								<button type="button" class="btn btn-info"
									onclick="uploadFormData()"
									style="position: absolute; left: 120px; top: 0px;">
									<span class="glyphicon glyphicon-arrow-up"></span> Upload

								</button>

							</div>
						</div>
					</div>
					<div class="col-md-6"
						style="position: relative; left: 0px; top: 0px; width: 550px; height: 30px">
						<label id="lblFilter"
							style="position: relative; left: -5px; top: 5px; width: 80px; height: 30px">
							Filter Bills </label> <select id="cboFilter" onchange="filterOptions()"
							style="position: relative; left: 0px; top: 0px; width: 150px; height: 30px">
							<option value=" "></option>
							<option value="Amount">Amount</option>
							<option value="Date">Date</option>
							<option value="Payment Mode">Payment Mode</option>
						</select>
					</div>

					<div class="col-md-6" id="divAmount" hidden="true"
						style="position: relative; left: 0px; top: 0px; width: 550px; height: 30px">
						<label id="lblAmount"
							style="position: relative; left: -5px; top: 5px; width: 80px; height: 30px">
							Amount Condition </label> <select id="cboAmount"
							style="position: relative; left: 0px; top: 0px; width: 150px; height: 30px">
							<option value=" "></option>
							<option value="=">Equal</option>
							<option value=">">Greater than</option>
							<option value="<">Less than</option>
							<option value="<= ">Less than equal to</option>
							<option value=">=">Greater than equal to</option>
						</select> <input type="text" id="txtAmount"
							style="position: relative; left: 0px; top: 0px; width: 100px; height: 30px;"
							placeholder="Enter Amount" />
						<button class="btn btn-default" id="btnAmount" type="button"
							style="position: relative; left: 0px; top: 0px; height: 30px;" onclick = "getBillsOnCondition()">
							<span class="glyphicon glyphicon-search"
								style="position: relative; left: 0px; top: 0px;" ></span>
						</button>
						<button class="btn btn-info" id="btnGetEarningsAmount" type="button"
							style="position: absolute; left: 100px; top: 60px; height: 30px; width: 180px;" onclick = "">Get Total Earnings
						</button>
					</div>
					<div class="col-md-6" id="divDate" hidden="true"
						style="position: relative; left: 0px; top: 0px; width: 550px; height: 30px">
						<label id="lblDate"
							style="position: relative; left: -5px; top: 15px; width: 80px; height: 30px">
							Date Condition </label> <input type="date" id="txtDate"
							style="position: relative; left: 0px; top: 5px; width: 180px; height: 30px;"
							placeholder="Enter Date"; >
						<button class="btn btn-default" id="btnDate" type="button"
							style="position: relative; left: 0px; top: 3px; height: 30px;" onclick="getBillsForDate()">
							<span class="glyphicon glyphicon-search"
								style="position: relative; left: 0px; top: 0px;"></span>
						</button>
						<button class="btn btn-info" id="btnGetEarningsAmount" type="button"
							style="position: absolute; left: 100px; top: 60px; height: 30px; width: 180px;" onclick = "">Get Total Earnings
						</button>
					</div>
					<div class="col-md-6" id="divPayment" hidden="true"
						style="position: relative; left: 0px; top: 0px; width: 550px; height: 30px">
						<label id="lblPayment"
							style="position: relative; left: -5px; top: 15px; width: 80px; height: 30px">
							Payment Condition </label> <select id="cboPayment"
							style="position: relative; left: 0px; top: 0px; width: 150px; height: 30px">
							<option value=" "></option>
							<option value="card">Card</option>
							<option value="cash">Cash</option>
						</select>
						<button class="btn btn-default" id="btnPayment" type="button"
							style="position: relative; left: 0px; top: 0px; height: 30px;" onclick= "getEarningOnPaymentType()">
							<span class="glyphicon glyphicon-search"
								style="position: relative; left: 0px; top: 0px;"></span>
						</button>
						<button class="btn btn-info" id="btnGetEarningsAmount" type="button"
							style="position: absolute; left: 100px; top: 60px; height: 30px; width: 180px;" onclick = "">Get Total Earnings
						</button>
					</div>
				</div>
				<div id="result" hidden="true"></div>
			</div>
		</div>
	</div>
</body>
</html>
