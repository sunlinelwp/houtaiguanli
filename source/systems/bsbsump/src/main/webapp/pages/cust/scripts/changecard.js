var changecard = function(){
	
	var cardtpDict=Sunline.getDict("E_CARDTP");
	$("input[name='cardtp']").select2({
		data : cardtpDict,
		allowClear : true
	});
	var acctprDict=Sunline.getDict("E_CUSTBZ");
	$("input[name='acctpr']").select2({
		data : acctprDict,
		allowClear : true
	});
	var handler =function(){
	 Sunline.getValidate($("#qry_form"),
			function() {
		 		if($("#cardno").val()==$("#odcdno").val()){
		 			bootbox.alert("绑定卡号和原绑定卡号不能是同一张卡")
		 			return false;
		 		}
				var data = {};
				$.each($("input", $("#qry_form")), function(i, n) {
					if (n.name != undefined&&n.name!=""&&n.name!=null) {
						data[n.name] = n.value;
					}
				});
				data.flagch = '1';//换绑卡标志交易，1-换卡，0-解卡
				Sunline.ajaxRouter("cust/qzcard", 
						data
				, 'post', function(ret) {
					if(!(ret.retMsg==""||ret.retMsg==null)){
						bootbox.alert(ret.retMsg);
					} else {
						if(ret.retCode=="0000")
							bootbox.alert("操作成功");
					}
				});

			}, {
				custac : {	
					maxlength:80
				},
				brchno : {	
					maxlength:8
				}
				,
				cakhmc : {	
					maxlength:80
				}
				,
				brchna : {	
					maxlength:8
				}
				,
				cardno : {
					maxlength:32
				}
				,
				odcdno : {	
					maxlength:32
				}
				,
				cardtp : {	
					maxlength:1
				},
				custbz : {
					required : true,
					maxlength:1
				}
			});
     	}
	 return {
		 init :function(){
			 handler();
		 }
	 }
}();