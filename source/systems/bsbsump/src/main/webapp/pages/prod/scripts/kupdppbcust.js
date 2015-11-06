var kupdppbcust = function() {

	$("input[name='crcycd']").select2({
		data : kupdppbdict.pdcrcyDict,
		allowClear : true
	});
	$("input[name='madtby']").select2({
		data : kupdppbdict.madtbyDict,
		allowClear : true
	});

	$("input[name='onlyfg']").select2({
		data : kupdppbdict.onlyfgDict,
		allowClear : true
	});
	var handleTable = function(prodcd) {
		var custgrid = new Datatable();
		var custurl = Sunline.ajaxPath("prod/dcusel");
		var custsendData = [ "prodcd", "crcycd" ];
		if (!Sunline.isNull(prodcd)) {
			custgrid.setAjaxParam("q_prodcd", prodcd);
		}
		custgrid
				.init({
					src : $("#datatable_cust"),
					deleteData : custsendData,
					onSuccess : function(custgrid) {
					},
					onError : function(custgrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : custurl,
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
									"data" : "madtby",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.madtbyDict.length; i++) {
											if (kupdppbdict.madtbyDict[i].id == data) {
												return kupdppbdict.madtbyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "onlyfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.onlyfgDict.length; i++) {
											if (kupdppbdict.onlyfgDict[i].id == data) {
												return kupdppbdict.onlyfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "srdpam",
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
		custgrid.bindTableDelete(custsendData);
		// 新增窗口
		$("#add_cust_btn").bind("click", function() {
			$("input[name='prodcd']", $("#editcustModal")).val(prodcd);
			$("#editcustModal").modal('show');
			$("#editcustModal").on("hide.bs.modal", function() {
				$(".alert-success", $('form', $("#editcustModal"))).hide();
				$('.alert-danger', $('form', $("#editcustModal"))).hide();
				$(".msg", $('form', $("#editcustModal"))).text("");
				custgrid.submitFilter();
			});
			return false;
		});

		$("#btn_save_cust").click(function() {
			$('form', $("#editcustModal")).submit();
		});

		var custValid = Sunline.getValidate($('form', $("#editcustModal")),
				function() {
					var data = {};
					$.each($("input", $("#editcustModal")), function(i, n) {
						if (n.name != undefined && n.name != ""
								&& n.name != null) {
							data[n.name] = n.value;
						}
					});
					var datas = [];
					datas.push(data);
					var dpcusts = {
						"kupdpt" : datas
					};
					Sunline.ajaxRouter("prod/dcuins", dpcusts, 'post',
							function(ret) {
								if (ret.ret === "success") {
									$(".alert-success",
											$('form', $("#editcustModal")))
											.show();
									$('.alert-danger',
											$('form', $("#editcustModal")))
											.hide();
								} else {
									$(".alert-success",
											$('form', $("#editcustModal")))
											.hide();
									$('.alert-danger',
											$('form', $("#editcustModal")))
											.show();
								}
								$(".msg", $('form', $("#editcustModal"))).text(
										ret.msg);
							});

				}, {
					crcycd : {
						required : true
					},
					srdpam : {
						maxlength : 21,
						money_gteq0 : true
					}
				});

	};

	return {
		init : function(prodcd) {
			handleTable(prodcd);
		}
	}
}();