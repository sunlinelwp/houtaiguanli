var custInfo = function() {
	var handleTable = function(){
		var input = {};
		input.custac = $("#custac").val();
		Sunline.ajaxRouter("custService/mypage", input, "POST", function(data) {
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
			$("#reppal").html(data.haspal);
			$("#reppay").html(data.hasint);
			$("#foesum").html(data.forsum);
			$("#foepal").html(data.forpal);
			$("#foePay").html(data.custac);
		}, function(data) {
		});
	}
	
	return {
		queryCust : function() {
			handleTable();
		}
	}
}();