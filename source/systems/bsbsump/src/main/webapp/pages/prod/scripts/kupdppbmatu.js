var kupdppbmatu = function() {

	$("#editmatuModal input[name='trdpfg']").select2({
		data : kupdppbdict.trdpfgDict,
		allowClear : true
	});
	$("#editmatuModal input[name='trpdfg']").select2({
		data : kupdppbdict.trdpfgDict,
		allowClear : true
	});
	$("#editmatuModal input[name='delyfg']").select2({
		data : kupdppbdict.delyfgDict,
		allowClear : true
	});
	$("#editmatuModal input[name='trinwy']").select2({
		data : kupdppbdict.trinwyDict,
		allowClear : true
	});
	$("#editmatuModal input[name='crcycd']").select2({
		data : kupdppbdict.pdcrcyDict,
		allowClear : true
	});
	var handleTable = function(prodcd) {
		var matugrid = new Datatable();
		var matuurl = Sunline.ajaxPath("prod/dmasel");
		var matusendData = [ "prodcd", "crcycd" ];
		if (!Sunline.isNull(prodcd)) {
			matugrid.setAjaxParam("q_prodcd", prodcd);
		}
		matugrid
				.init({
					src : $("#datatable_matu"),
					deleteData : matusendData,
					onSuccess : function(matugrid) {
					},
					onError : function(matugrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : matuurl,
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
									"data" : "festdl",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.festdlDict.length; i++) {
											if (kupdppbdict.festdlDict[i].id == data) {
												return kupdppbdict.festdlDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "delyfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.delyfgDict.length; i++) {
											if (kupdppbdict.delyfgDict[i].id == data) {
												return kupdppbdict.delyfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "matupd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "trdpfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.trdpfgDict.length; i++) {
											if (kupdppbdict.trdpfgDict[i].id == data) {
												return kupdppbdict.trdpfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "trpdfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.trdpfgDict.length; i++) {
											if (kupdppbdict.trdpfgDict[i].id == data) {
												return kupdppbdict.trdpfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "trprod",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "trinwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.trinwyDict.length; i++) {
											if (kupdppbdict.trinwyDict[i].id == data) {
												return kupdppbdict.trinwyDict[i].text;
											}
										}
										return data;
									}
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
		matugrid.bindTableDelete(matusendData);
		// 新增窗口
		$("#add_matu_btn").bind("click", function() {
			$("input[name='prodcd']", $("#editmatuModal")).val(prodcd);
			$("#editmatuModal").modal('show');
			$("#editmatuModal").on("hide.bs.modal", function() {
				$(".alert-success", $('form', $("#editmatuModal"))).hide();
				$('.alert-danger', $('form', $("#editmatuModal"))).hide();
				$(".msg", $('form', $("#editmatuModal"))).text("");
				matugrid.submitFilter();
			});
			return false;
		});

		$("#btn_save_matu").click(function() {
			$('form', $("#editmatuModal")).submit();
		});

		var matuValid = Sunline.getValidate($('form', $("#editmatuModal")),
				function() {
					var data = {};
					$.each($("input", $("#editmatuModal")), function(i, n) {
						if (n.name != undefined && n.name != ""
								&& n.name != null) {
							data[n.name] = n.value;
						}
					});
					var datas = [];
					datas.push(data);
					var dpmatus = {
						"dpmatu" : datas
					};
					Sunline.ajaxRouter("prod/dmains", dpmatus, 'post',
							function(ret) {
								if (ret.ret === "success") {
									$(".alert-success",
											$('form', $("#editmatuModal")))
											.show();
									$('.alert-danger',
											$('form', $("#editmatuModal")))
											.hide();
								} else {
									$(".alert-success",
											$('form', $("#editmatuModal")))
											.hide();
									$('.alert-danger',
											$('form', $("#editmatuModal")))
											.show();
								}
								$(".msg", $('form', $("#editmatuModal"))).text(
										ret.msg);
							});

				}, {
					crcycd : {
						required : true
					},
					trprod : {
						maxlength : 10
					},
					matupd : {
						maxlength : 6
					}
				});

	};

	return {
		init : function(prodcd) {
			handleTable(prodcd);
		}
	}
}();