var lccpReport = function() {

	var opreation = "find";
	var rpt_code;
	var rpt_type;
	var content = $('.inbox-content');
	var listListing = '';
	
	var reportValid = Sunline.getValidate($("#fund_report"), function() {
		if(opreation=="find"){
			content.html('');
			toggleButton($("#fund_report"));
			var data = {};
			$.each($("input", $("#fund_report")), function(i, n) {
				if (n.name != undefined && n.name != "" && n.name != null) {
					data[n.name] = n.value;
				}
			});
			data.rpt_code = rpt_code;
			Sunline.ajaxRouter("repo/qryrpt", data, "post", function(res) {
				content.html(res.html);
				Metronic.initUniform();
			}, function(xhr, ajaxOptions, thrownError) {
				bootbox.alert("获取理财产品报表失败");
			}, "json");
		}else if(opreation=="down"){
			var url = "/rest/repo/downloadReport";
			url = url + "?rpt_code=" + rpt_code + "&rpt_type=" + rpt_type;
			$.each($("input", $("#fund_report")), function(i, n) {
				if (n.name != undefined && n.name != "" && n.name != null) {
					url = url + "&" + n.name + "=" + n.value;
				}
			});
			window.open(Sunline.getBasePath()+url);
		}
	}, {
		acctdt : {
			required : true
		},
		fundtype: {
        	required: true
        }
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
	};
	return {
		init : function() {
			$("#find_report").click(function() {
				opreation = "find";
				rpt_code="lccpxx";
				$("#fund_report").submit();
			});
			
			$("a","#down").on("click",function(){
				opreation = "down";
				rpt_code="lccpxx";
				rpt_type = this.name;
				$("#fund_report").submit();
			});
		}
	}

}();