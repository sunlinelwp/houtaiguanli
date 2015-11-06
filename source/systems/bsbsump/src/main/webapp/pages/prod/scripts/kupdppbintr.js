var kupdppbintr = function() {

	$("#editintrModal input[name='crcycd']").select2({
		data : kupdppbdict.pdcrcyDict,
		allowClear : true
	});
	$("#editintrModal input[name='inbefg']").select2({
		data : kupdppbdict.inbefgDict,
		allowClear : true
	});
	$("#editintrModal input[name='intrtp']").select2({
		data : kupdppbdict.intrtpDict,
		allowClear : true
	});
	$("#editintrModal input[name='txbefg']").select2({
		data : kupdppbdict.acolfgDict,
		allowClear : true
	});
	$("#datatable_intr input[name='txbebs']").select2({
		data : kupdppbdict.txbebsDict,
		allowClear : true
	});
	$("#editintrModal input[name='hutxfg']").select2({
		data : kupdppbdict.hutxfgDict,
		allowClear : true
	});
	$("#editintrModal input[name='intrwy']").select2({
		data : kupdppbdict.intrwyDict,
		allowClear : true
	});
	$("#editintrModal input[name='incdtp']").select2({
		data : kupdppbdict.incdtpDict,
		allowClear : true
	});
	$("#editintrModal input[name='lyinwy']").select2({
		data : kupdppbdict.lyinwyDict,
		allowClear : true
	});
	$("#editintrModal input[name='inammd']").select2({
		data : kupdppbdict.inammdDict,
		allowClear : true
	});
	$("#editintrModal input[name='bldyca']").select2({
		data : kupdppbdict.bldycaDict,
		allowClear : true
	});
	$("#editintrModal input[name='inprwy']").select2({
		data : kupdppbdict.inprwyDict,
		allowClear : true
	});
	$("#editintrModal input[name='reprwy']").select2({
		data : kupdppbdict.reprwyDict,
		allowClear : true
	});
	$("#editintrModal input[name='fvrbfg']").select2({
		data : kupdppbdict.fvrbfgDict,
		allowClear : true
	});
	var handleTable = function(prodcd) {
		var intrgrid = new Datatable();
		var intrurl = Sunline.ajaxPath("prod/dinsel");
		var intrsendData = [ "prodcd", "crcycd", "intrtp" ];
		if (!Sunline.isNull(prodcd)) {
			intrgrid.setAjaxParam("q_prodcd", prodcd);
		}
		intrgrid
				.init({
					src : $("#datatable_intr"),
					deleteData : intrsendData,
					onSuccess : function(intrgrid) {
					},
					onError : function(intrgrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : intrurl,
						},
						"columns" : [
								{
									"data" : "prodcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "crcycd",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.pdcrcyDict.length; i++) {
											if (kupdppbdict.pdcrcyDict[i].id == data) {
												return kupdppbdict.pdcrcyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "intrtp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.intrtpDict.length; i++) {
											if (kupdppbdict.intrtpDict[i].id == data) {
												return kupdppbdict.intrtpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "inbefg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.inbefgDict.length; i++) {
											if (kupdppbdict.inbefgDict[i].id == data) {
												return kupdppbdict.inbefgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "txbefg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.acolfgDict.length; i++) {
											if (kupdppbdict.acolfgDict[i].id == data) {
												return kupdppbdict.acolfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "txbebs",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.txbebsDict.length; i++) {
											if (kupdppbdict.txbebsDict[i].id == data) {
												return kupdppbdict.txbebsDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "hutxfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.hutxfgDict.length; i++) {
											if (kupdppbdict.hutxfgDict[i].id == data) {
												return kupdppbdict.hutxfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "txbefr",
									"sortable" : false,
									"searchable" : false

								},
								{
									"data" : "intrcd",
									"sortable" : false,
									"searchable" : false

								},
								{
									"data" : "intrwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.intrwyDict.length; i++) {
											if (kupdppbdict.intrwyDict[i].id == data) {
												return kupdppbdict.intrwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "incdtp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.incdtpDict.length; i++) {
											if (kupdppbdict.incdtpDict[i].id == data) {
												return kupdppbdict.incdtpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "lyinwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.lyinwyDict.length; i++) {
											if (kupdppbdict.lyinwyDict[i].id == data) {
												return kupdppbdict.lyinwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "inammd",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.inammdDict.length; i++) {
											if (kupdppbdict.inammdDict[i].id == data) {
												return kupdppbdict.inammdDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "bldyca",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.bldycaDict.length; i++) {
											if (kupdppbdict.bldycaDict[i].id == data) {
												return kupdppbdict.bldycaDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "inprwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.inprwyDict.length; i++) {
											if (kupdppbdict.inprwyDict[i].id == data) {
												return kupdppbdict.inprwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "inadlv",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "reprwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.reprwyDict.length; i++) {
											if (kupdppbdict.reprwyDict[i].id == data) {
												return kupdppbdict.reprwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "fvrbfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.fvrbfgDict.length; i++) {
											if (kupdppbdict.fvrbfgDict[i].id == data) {
												return kupdppbdict.fvrbfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "fvrblv",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "taxecd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='delete' href='javascript:;' data-src='"
												+ data.prodcd
												+ ","
												+ data.crcycd
												+ ","
												+ data.intrtp + "'>删除 </a>";
									}
								} ]
					}
				});
		intrgrid.bindTableDelete(intrsendData);
		// 新增窗口
		$("#add_intr_btn").bind("click", function() {
			$("input[name='prodcd']", $("#editintrModal")).val(prodcd);
			$("#editintrModal").modal('show');
			$("#editintrModal").on("hide.bs.modal", function() {
				$(".alert-success", $('form', $("#editintrModal"))).hide();
				$('.alert-danger', $('form', $("#editintrModal"))).hide();
				$(".msg", $('form', $("#editintrModal"))).text("");
				intrgrid.submitFilter();
			});
			return false;
		});

		$("#btn_save_intr").click(function() {
			$('form', $("#editintrModal")).submit();
		});

		var intrValid = Sunline.getValidate($('form', $("#editintrModal")),
				function() {
					var data = {};
					$.each($("input", $("#editintrModal")), function(i, n) {
						if (n.name != undefined && n.name != ""
								&& n.name != null) {
							data[n.name] = n.value;
						}
					});
					var datas = [];
					datas.push(data);
					var dpintrs = {
						"dpintr" : datas
					};
					Sunline.ajaxRouter("prod/dinins", dpintrs, 'post',
							function(ret) {
								if (ret.ret === "success") {
									$(".alert-success",
											$('form', $("#editintrModal")))
											.show();
									$('.alert-danger',
											$('form', $("#editintrModal")))
											.hide();
								} else {
									$(".alert-success",
											$('form', $("#editintrModal")))
											.hide();
									$('.alert-danger',
											$('form', $("#editintrModal")))
											.show();
								}
								$(".msg", $('form', $("#editintrModal"))).text(
										ret.msg);
							});

				}, {
					crcycd : {
						required : true
					},
					intrtp : {
						required : true
					},
					txbefr : {
						maxlength : 8
					},
					intrcd : {
						maxlength : 20
					},
					fvrblv : {
						maxlength : 8
					},
					taxecd : {
						maxlength : 20
					}
				});

	};

	return {
		init : function(prodcd) {
			handleTable(prodcd);
		}
	}
}();