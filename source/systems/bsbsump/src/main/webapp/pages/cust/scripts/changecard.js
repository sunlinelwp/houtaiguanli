var changecard = function(){
	
	var cardtpDict=Sunline.getDict("E_CARDTP");
	$("input[name='cardtp']").select2({
		data : cardtpDict,
		allowClear : true
	});
	
	var handler =function(){
	 Sunline.getValidate($("#qry_form"),
			function() {
				var data = {};
				$.each($("input", $("#qry_form")), function(i, n) {
					if (n.name != undefined&&n.name!=""&&n.name!=null) {
						data[n.name] = n.value;
					}
				});
				Sunline.ajaxRouter("cust/qzcard", 
						data
				, 'post', function(ret) {
					bootbox.alert(ret.retMsg);
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
				}
			});
     	}
	 return {
		 init :function(){
			 handler();
		 }
	 }
}();