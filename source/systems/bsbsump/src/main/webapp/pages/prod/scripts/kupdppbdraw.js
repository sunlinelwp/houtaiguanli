var kupdppbdraw = function() {

	$("#editdrawModal input[name='crcycd']").select2({
		data : kupdppbdict.pdcrcyDict,
		allowClear : true
	});
	$("#editdrawModal input[name='drawtp']").select2({
		data : kupdppbdict.drawtpDict,
		allowClear : true
	});
	
	$("#editdrawModal input[name='ctrlwy']").select2({
		data : kupdppbdict.ctrlwyDict,
		allowClear : true
	});
	
	$("#editdrawModal input[name='selfwy']").select2({
		data : kupdppbdict.selfwyDict,
		allowClear : true
	});
	
	$("#editdrawModal input[name='ordrwy']").select2({
		data : kupdppbdict.ordrwyDict,
		allowClear : true
	});
	
	$("#editdrawModal input[name='dramwy']").select2({
		data : kupdppbdict.amntwyDict,
		allowClear : true
	});
	
	$("#editdrawModal input[name='drtmwy']").select2({
		data : kupdppbdict.timewyDict,
		allowClear : true
	});
	$("#editdrawModal input[name='drrule']").select2({
		data : kupdppbdict.drruleDict,
		allowClear : true
	});
	var handleTable = function(prodcd) {
		var drawgrid = new Datatable();
		var drawurl = Sunline.ajaxPath("prod/ddrsel");
		var drawsendData = [ "prodcd", "crcycd" ];
		if (!Sunline.isNull(prodcd)) {
			drawgrid.setAjaxParam("q_prodcd", prodcd);
		}
		drawgrid.init({
					src : $("#datatable_draw"),
					deleteData : drawsendData,
					onSuccess : function(drawgrid) {
					},
					onError : function(drawgrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : drawurl,
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
									"data" : "drawtp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.posttpDict.length; i++) {
											if (kupdppbdict.posttpDict[i].id == data) {
												return kupdppbdict.posttpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "ctrlwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.ctrlwyDict.length; i++) {
											if (kupdppbdict.ctrlwyDict[i].id == data) {
												return kupdppbdict.ctrlwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "selfwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.selfwyDict.length; i++) {
											if (kupdppbdict.selfwyDict[i].id == data) {
												return kupdppbdict.selfwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "ordrwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.ordrwyDict.length; i++) {
											if (kupdppbdict.ordrwyDict[i].id == data) {
												return kupdppbdict.ordrwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "dramwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.amntwyDict.length; i++) {
											if (kupdppbdict.amntwyDict[i].id == data) {
												return kupdppbdict.amntwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "drmiam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "drmxam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "drtmwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.timewyDict.length; i++) {
											if (kupdppbdict.timewyDict[i].id == data) {
												return kupdppbdict.timewyDict[i].text;
											}
										}
										return data;
									}
									
								},
								{
									"data" : "drmitm",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "drmxtm",
									"sortable" : false,
									"searchable" : false
									
								},
								{
									"data" : "drrule",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.drruleDict.length; i++) {
											if (kupdppbdict.drruleDict[i].id == data) {
												return kupdppbdict.drruleDict[i].text;
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
		drawgrid.bindTableDelete(drawsendData);
		// 新增窗口
		$("#add_draw_btn").bind("click", function() {
			$("input[name='prodcd']", $("#editdrawModal")).val(prodcd);
			$("#editdrawModal").modal('show');
			$("#editdrawModal").on("hide.bs.modal", function() {
				$(".alert-success", $('form', $("#editdrawModal"))).hide();
				$('.alert-danger', $('form', $("#editdrawModal"))).hide();
				$(".msg", $('form', $("#editdrawModal"))).text("");
				drawgrid.submitFilter();
			});
			return false;
		});

		$("#btn_save_draw").click(function() {
			$('form', $("#editdrawModal")).submit();
		});

		var drawValid = Sunline.getValidate($('form',
				$("#editdrawModal")), function() {
			var data = {};
			$.each($("input", $("#editdrawModal")), function(i, n) {
				if (n.name != undefined && n.name != "" && n.name != null) {
					data[n.name] = n.value;
				}
			});
			var datas = [];
			datas.push(data);
			var dpdwpls = {
				"dpdraw" : datas
			};
			Sunline.ajaxRouter("prod/ddrins", dpdwpls, 'post', function(ret) {
				if (ret.ret === "success") {
					$(".alert-success", $('form', $("#editdrawModal")))
							.show();
					$('.alert-danger', $('form', $("#editdrawModal")))
							.hide();
				} else {
					$(".alert-success", $('form', $("#editdrawModal")))
							.hide();
					$('.alert-danger', $('form', $("#editdrawModal")))
							.show();
				}
				$(".msg", $('form', $("#editdrawModal"))).text(ret.msg);
			});

		}, {
			crcycd : {
				required : true
			},
			drawtp : {
				required : true
			},
			ctrlwy : {
				required : true
			},
			dramwy : {
				required : true
			},
			drtmwy : {
				required : true
			},
		});

	};

	return {
		init : function(prodcd) {
			handleTable(prodcd);
		}
	}
}();