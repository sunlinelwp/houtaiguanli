var redBagSearch = function() {
	var opreation = "find";
	var rpt_code = 'mrtgry';// 'mrtgry';
	var rpt_type;
	var content = $('.inbox-content');
	var listListing = '';
	
	 var crcycdDict=Sunline.getDict("tags_cd");
	 $("input[name='tags']", $("#report_form")).select2({
   	  data:crcycdDict
     });
	 
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
				$("#find_report").removeAttr("disabled");
				content.html(res.html);
				Metronic.initUniform();
			}, function(xhr, ajaxOptions, thrownError) {
				$("#find_report").removeAttr("disabled");
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
		investendTime : {
			required : true
		},
		investStartTime : {
			required : true
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
	}
	return {
		init : function() {
			$("#find_report").click(function(e) {
				$("#find_report").attr("disabled","disabled");
				e.preventDefault();
				opreation = "find";
				if($("#investStartTime").val()=="" || $("#investendTime").val()==""){
					$("#find_report").removeAttr("disabled");
				}
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