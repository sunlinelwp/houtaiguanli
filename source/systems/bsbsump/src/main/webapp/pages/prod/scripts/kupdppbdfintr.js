var kupdppbdfintr = function() {

	$("#editdfintrModal input[name='crcycd']").select2({
		data : kupdppbdict.pdcrcyDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='drintp']").select2({
		data : kupdppbdict.drintpDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='intrtp']").select2({
		data : kupdppbdict.intrtpDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='inadtp']").select2({
		data : kupdppbdict.inadtpDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='sminad']").select2({
		data : kupdppbdict.sminadDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='inclfg']").select2({
		data : kupdppbdict.inclfgDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='bsinam']").select2({
		data : kupdppbdict.bsinamDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='bsindt']").select2({
		data : kupdppbdict.bsindtDict,
		allowClear : true
	});
	$("input[name='inedsc']").select2({
		data : kupdppbdict.inedscDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='dtlvsr']").select2({
		data : kupdppbdict.dtlvsrDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='amlvsr']").select2({
		data : kupdppbdict.amlvsrDict,
		allowClear : true
	});
	$("#editdfintrModal input[name='drinsc']").select2({
		data : kupdppbdict.drinscDict,
		allowClear : true
	});
	var handleTable = function(prodcd) {
		var dfintrgrid = new Datatable();
		var dfintrurl = Sunline.ajaxPath("prod/ddfsel");
		var dfintrsendData = [ "prodcd", "crcycd", "drintp", "ingpcd", "intrtp" ];
		if (!Sunline.isNull(prodcd)) {
			dfintrgrid.setAjaxParam("q_prodcd", prodcd);
		}
		dfintrgrid
				.init({
					src : $("#datatable_dfintr"),
					deleteData : dfintrsendData,
					onSuccess : function(dfintrgrid) {
					},
					onError : function(dfintrgrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : dfintrurl,
						},
						"columns" : [
								{
									"data" : "prodcd",//1
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
									"data" : "drintp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.drintpDict.length; i++) {
											if (kupdppbdict.drintpDict[i].id == data) {
												return kupdppbdict.drintpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "ingpcd",
									"sortable" : false,
									"searchable" : false
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
									"data" : "drintx",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "inadtp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.inadtpDict.length; i++) {
											if (kupdppbdict.inadtpDict[i].id == data) {
												return kupdppbdict.inadtpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "sminad",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.sminadDict.length; i++) {
											if (kupdppbdict.sminadDict[i].id == data) {
												return kupdppbdict.sminadDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "adincd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "insrwy",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "inclfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.inclfgDict.length; i++) {
											if (kupdppbdict.inclfgDict[i].id == data) {
												return kupdppbdict.inclfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "bsinam",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.bsinamDict.length; i++) {
											if (kupdppbdict.bsinamDict[i].id == data) {
												return kupdppbdict.bsinamDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "bsindt",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.bsindtDict.length; i++) {
											if (kupdppbdict.bsindtDict[i].id == data) {
												return kupdppbdict.bsindtDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "inedsc",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.inedscDict.length; i++) {
											if (kupdppbdict.inedscDict[i].id == data) {
												return kupdppbdict.inedscDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "bsincd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "bsinrl",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "bsinef",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "dtlvsr",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.dtlvsrDict.length; i++) {
											if (kupdppbdict.dtlvsrDict[i].id == data) {
												return kupdppbdict.dtlvsrDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "dtsrcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "dtlvrl",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "dtlvef",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "amlvsr",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.amlvsrDict.length; i++) {
											if (kupdppbdict.amlvsrDict[i].id == data) {
												return kupdppbdict.amlvsrDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "amlvcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "amlvrl",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "amlvef",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "drinsc",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.drinscDict.length; i++) {
											if (kupdppbdict.drinscDict[i].id == data) {
												return kupdppbdict.drinscDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "drdein",
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
												+ data.drintp
												+ ","
												+ data.ingpcd
												+ ","
												+ data.intrtp + "'>删除 </a>";
									}
								} ]
					}
				});
		dfintrgrid.bindTableDelete(dfintrsendData);
		// 新增窗口
		$("#add_dfintr_btn").bind("click", function() {
			$("input[name='prodcd']", $("#editdfintrModal")).val(prodcd);
			$("#editdfintrModal").modal('show');
			$("#editdfintrModal").on("hide.bs.modal", function() {
				$(".alert-success", $('form', $("#editdfintrModal"))).hide();
				$('.alert-danger', $('form', $("#editdfintrModal"))).hide();
				$(".msg", $('form', $("#editdfintrModal"))).text("");
				dfintrgrid.submitFilter();
			});
			return false;
		});

		$("#btn_save_dfintr").click(function() {
			$('form', $("#editdfintrModal")).submit();
		});

		var dfintrValid = Sunline.getValidate($('form', $("#editdfintrModal")),
				function() {
					var data = {};
					$.each($("input", $("#editdfintrModal")), function(i, n) {
						if (n.name != undefined && n.name != ""
								&& n.name != null) {
							data[n.name] = n.value;
						}
					});
					var datas = [];
					datas.push(data);
					var dpdfits = {
						"dpdfit" : datas
					};
					Sunline.ajaxRouter("prod/ddfins", dpdfits, 'post',
							function(ret) {
								if (ret.ret === "success") {
									$(".alert-success",
											$('form', $("#editdfintrModal")))
											.show();
									$('.alert-danger',
											$('form', $("#editdfintrModal")))
											.hide();
								} else {
									$(".alert-success",
											$('form', $("#editdfintrModal")))
											.hide();
									$('.alert-danger',
											$('form', $("#editdfintrModal")))
											.show();
								}
								$(".msg", $('form', $("#editdfintrModal")))
										.text(ret.msg);
							});

				}, {
					crcycd : {
						required : true
					},
					drintp : {
						required : true
					},
					ingpcd : {
						required : true,
						maxlength : 8
					},
					intrtp : {
						required : true
					},
					adincd : {
						maxlength : 20
					},
					insrwy : {
						maxlength : 1
					},
					bsincd : {
						maxlength : 20
					},
					bsinrl : {
						maxlength : 2
					},
					bsinef : {
						maxlength : 01
					},
					dtsrcd : {
						maxlength : 20
					},
					dtlvrl : {
						maxlength : 2
					},
					dtlvef : {
						maxlength : 1
					},
					amlvcd : {
						maxlength : 20
					},
					amlvrl : {
						maxlength : 2
					},
					amlvef : {
						maxlength : 1
					},
					drdein : {
						maxlength : 1
					}

				});

	};

	return {
		init : function(prodcd) {
			handleTable(prodcd);
		}
	}
}();