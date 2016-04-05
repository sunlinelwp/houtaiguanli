var removeCard = function(){
	var handler =function(){
		//解卡
		$("#qry_btn").bind("click", function() {
			var input  = {};
			input.custpt = $("#custpt").val();
			input.cardno = $("#cardno").val();
			Sunline.ajaxRouter("cust/hfcard", input, "POST", function(ret) {
				if(!(ret.retMsg==""||ret.retMsg==null)){
					bootbox.alert(ret.retMsg);
				} else {
					if(ret.retCode=="0000")
						bootbox.alert("操作成功");
				}
			}, function(data) {
			});
			return false;
		});
	}
	 return {
		 init :function(){
			 handler();
		 }
	 }
}();