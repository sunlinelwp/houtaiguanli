var custInfo = function() {
	var handleTable = function(){
		var input = {};
		input.custac = $("#custac").val();
		input.mobile = $("#phoneNo").val();
		Sunline.ajaxRouter("custService/mypage", input, "POST", function(data) {
			if(data.retCode == "0000"){
				$("#acctno").html(data.custac);
				$("#loanrd").html(data.loanrd);
				$("#invest").html(data.invest);
				$("#amount").html(data.amount);
				$("#usable").html(data.usable);
				$("#freeze").html(data.freeze);
				$("#earsum").html(data.earsum);
				$("#rateen").html(data.rateen);
				$("#othear").html(data.othear);
				$("#hassum").html(data.hassum);
				$("#haspal").html(data.haspal);
				$("#hasint").html(data.hasint);
				$("#forsum").html(data.forsum);
				$("#forpal").html(data.forpal);
				$("#forpay").html(data.forpay);
				$("#repsum").html(data.repsum);
				$("#reppal").html(data.reppal);
				$("#reppay").html(data.reppay);
				$("#foesum").html(data.foesum);
				$("#foepal").html(data.foepal);
				$("#foePay").html(data.foepay);
			}else{
				bootbox.alert("无此客户信息");
				$("#acctno").html("");
				$("#loanrd").html("");
				$("#invest").html("");
				$("#amount").html("");
				$("#usable").html("");
				$("#freeze").html("");
				$("#earsum").html("");
				$("#rateen").html("");
				$("#othear").html("");
				$("#hassum").html("");
				$("#haspal").html("");
				$("#hasint").html("");
				$("#forsum").html("");
				$("#forpal").html("");
				$("#forpay").html("");
				$("#repsum").html("");
				$("#reppal").html("");
				$("#reppay").html("");
				$("#foesum").html("");
				$("#foepal").html("");
				$("#foePay").html("");
			}
		}, function(data) {
		});
	}
	
	return {
		queryCust : function() {
			handleTable();
		}
	}
}();