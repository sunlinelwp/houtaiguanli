var kupdppbpostplan = function() {

	$("#editpostplanModal input[name='crcycd']").select2({
		data : kupdppbdict.pdcrcyDict,
		allowClear : true
	});
	$("#editpostplanModal input[name='planfg']").select2({
		data : kupdppbdict.planfgDict,
		allowClear : true
	});
	$("#editpostplanModal input[name='planwy']").select2({
		data : kupdppbdict.planwyDict,
		allowClear : true
	});
	$("#editpostplanModal input[name='endtwy']").select2({
		data : kupdppbdict.endtwyDict,
		allowClear : true
	});
	$("#editpostplanModal input[name='gentwy']").select2({
		data : kupdppbdict.gentwyDict,
		allowClear : true
	});
	$("#editpostplanModal input[name='svlewy']").select2({
		data : kupdppbdict.svlewyDict,
		allowClear : true
	});
	$("#editpostplanModal input[name='dfltsd']").select2({
		data : kupdppbdict.dfltsdDict,
		allowClear : true
	});
	$("#editpostplanModal input[name='dfltwy']").select2({
		data : kupdppbdict.dfltwyDict,
		allowClear : true
	});
	$("#editpostplanModal input[name='pscrwy']").select2({
		data : kupdppbdict.pscrwyDict,
		allowClear : true
	});

	var handleTable = function(prodcd) {
		var postplangrid = new Datatable();
		var postplanurl = Sunline.ajaxPath("prod/dplsel");
		var postplansendData = [ "prodcd", "crcycd" ];
		if (!Sunline.isNull(prodcd)) {
			postplangrid.setAjaxParam("q_prodcd", prodcd);
		}
		postplangrid
				.init({
					src : $("#datatable_postplan"),
					deleteData : postplansendData,
					onSuccess : function(postplangrid) {
					},
					onError : function(postplangrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : postplanurl,
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
									"data" : "planfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.planfgDict.length; i++) {
											if (kupdppbdict.planfgDict[i].id == data) {
												return kupdppbdict.planfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "planwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.planwyDict.length; i++) {
											if (kupdppbdict.planwyDict[i].id == data) {
												return kupdppbdict.planwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "adjtpd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "endtwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.endtwyDict.length; i++) {
											if (kupdppbdict.endtwyDict[i].id == data) {
												return kupdppbdict.endtwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "gentwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.gentwyDict.length; i++) {
											if (kupdppbdict.gentwyDict[i].id == data) {
												return kupdppbdict.gentwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "svlepd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "svlewy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.svlewyDict.length; i++) {
											if (kupdppbdict.svlewyDict[i].id == data) {
												return kupdppbdict.svlewyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "maxisp",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "dfltsd",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.dfltsdDict.length; i++) {
											if (kupdppbdict.dfltsdDict[i].id == data) {
												return kupdppbdict.dfltsdDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "svletm",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "dfltwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.dfltwyDict.length; i++) {
											if (kupdppbdict.dfltwyDict[i].id == data) {
												return kupdppbdict.dfltwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "pscrwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.pscrwyDict.length; i++) {
											if (kupdppbdict.pscrwyDict[i].id == data) {
												return kupdppbdict.pscrwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "maxibl",
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
												+ data.crcycd + "'>删除 </a>";
									}
								} ]
					}
				});
		postplangrid.bindTableDelete(postplansendData);
		// 新增窗口
		$("#add_postplan_btn").bind("click", function() {
			$("input[name='prodcd']", $("#editpostplanModal")).val(prodcd);
			$("#editpostplanModal").modal('show');
			$("#editpostplanModal").on("hide.bs.modal", function() {
				$(".alert-success", $('form', $("#editpostplanModal"))).hide();
				$('.alert-danger', $('form', $("#editpostplanModal"))).hide();
				$(".msg", $('form', $("#editpostplanModal"))).text("");
				postplangrid.submitFilter();
			});
			return false;
		});

		$("#btn_save_postplan").click(function() {
			$('form', $("#editpostplanModal")).submit();
		});

		var postplanValid = Sunline.getValidate($('form',
				$("#editpostplanModal")), function() {
			var data = {};
			$.each($("input", $("#editpostplanModal")), function(i, n) {
				if (n.name != undefined && n.name != "" && n.name != null) {
					data[n.name] = n.value;
				}
			});
			var datas = [];
			datas.push(data);
			var dpptpls = {
				"dpptpl" : datas
			};
			Sunline.ajaxRouter("prod/dplins", dpptpls, 'post', function(ret) {
				if (ret.ret === "success") {
					$(".alert-success", $('form', $("#editpostplanModal")))
							.show();
					$('.alert-danger', $('form', $("#editpostplanModal")))
							.hide();
				} else {
					$(".alert-success", $('form', $("#editpostplanModal")))
							.hide();
					$('.alert-danger', $('form', $("#editpostplanModal")))
							.show();
				}
				$(".msg", $('form', $("#editpostplanModal"))).text(ret.msg);
			});

		}, {
			crcycd : {
				required : true
			},
			maxibl : {
				maxlength : 21,
				number : true
			},
			adjtpd : {
				maxlength : 8
			},
			svlepd : {
				maxlength : 8
			},
			maxisp : {
				maxlength : 19,
				number : true
			},
			svletm : {
				maxlength : 19,
				number : true
			}
		});

	};

	return {
		init : function(prodcd) {
			handleTable(prodcd);
		}
	}
}();