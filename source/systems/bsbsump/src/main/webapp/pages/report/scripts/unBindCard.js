var unBindCard = function() {

	var opreation = "find";
	var rpt_code = 'unBindCard';
	var rpt_type;
	
	 var tags_cdDict=Sunline.getDict("tags_cd");
	 $("input[name='tags_cd']", $("#report_form")).select2({
		data : tags_cdDict
	});
//	 $("input[name='tags_cd']", $("#report_form")).select2('val','0004');
	var content = $('.inbox-content');
	var listListing = '';

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
				bootbox.alert("获取未绑卡客户信息明细表失败");
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
			$("#find_report").click(function() {
				opreation = "find";
				$("#report_form").submit();
			});

			$("a","#down").on("click",function(){
				opreation = "down";
				rpt_type = this.name;
				$("#report_form").submit();
			});
		}
	}

}();