var redbogRewQuery = function() {

	var opreation = "find";
	var rpt_code = 'hbjlcx';
	var rpt_type;
	var content = $('.inbox-content');
	var listListing = '';
	
	/* $("input[name='redbogType']", $("#report_form")).select2({
		 data:[{id:'11',text:'代金券'},
			   {id:'12',text:'投资红包'},
			   {id:'21',text:'加息券'},
			   {id:'31',text:'返现'},
			   {id:'91',text:'促销收益'},]
	});
	 $("input[name='status']", $("#report_form")).select2({
		 data:[{id:'1',text:'未兑现'},
		       {id:'2',text:'已兑现'},
		       {id:'3',text:'无效'},]
		  
     });*/
	var reportValid = Sunline.getValidate($("#report_form"), function() {
		if(opreation=="find"){
			content.html('');
			toggleButton($("#report_form"));
			var data = {};
			$.each($("input", $("#report_form")), function(i, n) {
				if (n.name != undefined && n.name != "" && n.name != null) {
					data[n.name] = n.value;
				}
			});
			data.rpt_code = rpt_code;
			Sunline.ajaxRouter("repo/qryrpt", data, "post", function(res) {
				content.html(res.html);
				Metronic.initUniform();
			}, function(xhr, ajaxOptions, thrownError) {
				bootbox.alert("获取报表失败");
			}, "json");
		}else if(opreation=="down"){
			var url = "/rest/repo/downloadReport";
			url = url + "?rpt_code=" + rpt_code + "&rpt_type=" + rpt_type;
			$.each($("input", $("#report_form")), function(i, n) {
				if (n.name != undefined && n.name != "" && n.name != null) {
					url = url + "&" + n.name + "=" + n.value;
				}
			});
			window.open(Sunline.getBasePath()+url);
		}

	}, {
		teleno : {
			required : true
		}/*,
		prodName : {
			required : true
		},
		acctdt : {
			required : true
		},
		redbogType : {
			required : true
		},
		status : {
			required : true
		}*/
	});
	var toggleButton = function(el) {
		if (typeof el == 'undefined') {
			return;
		}
		if (el.attr("disabled")) {
			el.attr("disabled", false);
		} else {
			el.attr("disabled", true);
		}
	}
	return {
		init : function() {
			$("#find_report").click(function(e) {
				e.preventDefault();
				opreation = "find";
				$("#report_form").submit();
			});
			$("a","#down").on("click",function(e){
				e.preventDefault();
				opreation = "down";
				rpt_type = this.name;
				$("#report_form").submit();
			});
		}
	}
}();