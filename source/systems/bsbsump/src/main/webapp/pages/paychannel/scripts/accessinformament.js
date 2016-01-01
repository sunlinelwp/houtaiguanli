var kuprole = function() {
	// var inclfgDict = Sunline.getDict("E_INCLFG");
	var syscodDict = Sunline.getDict("PayChannel");// 状态
	// var status = Sunline.getDict("E_PYTYPE");//支付方式

	// $("input[name='n_pytype']").select2({
	// data : status,
	// allowClear : true
	// });
	// $("input[name='pytype']").select2({
	// data : status,
	// allowClear : true
	// });
	$("input[name='status']").select2({
		data : syscodDict,
		allowClear : true
	});
	$("input[name='n_status']").select2({
		data : syscodDict,
		allowClear : true
	});
	var handleTable = function() {
		var typegrid = new Datatable();
		var typeurl = Sunline.ajaxPath("paychannel/qyinch"); // URL???
		var typesendData = [ "chnlcd" ]; // 主键
		typegrid
				.init({
					src : $("#datatable_ajax"),
					deleteData : typesendData,
					onSuccess : function(typegrid) {
					},
					onError : function(typegrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : typeurl,
						},
						"columns" : [
								{
									"data" : "chnlcd", // 渠道编码
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "chnlnm", // 渠道名称
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "status", // 渠道状态
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < syscodDict.length; i++) {
											if (syscodDict[i].id == data) {
												return syscodDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "creatm", // 创建时间
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "cruser", // 创建人
									"sortable" : false,
									"searchable" : false,
								},

								{
									"data" : "modydt", // 修改日期
									"sortable" : false,
									"searchable" : false,
								},
								{
									"data" : "mduser", // 修改人
									"sortable" : false,
									"searchable" : false,
								},
								{
									"data" : "remark", // 备注
									"sortable" : false,
									"searchable" : false,
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='edit' href='javascript:;' data-src='"
												+ data.chnlcd + "'>编辑 </a>";
									}
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='delete' href='javascript:;' data-src='"
												+ data.chnlcd + "'>删除 </a>";
									}
								} ]
					}
				});
		typegrid.bindTableDelete(typesendData); // 绑定数据删除？？？？
		typegrid.bindTableEdit(typesendData,
				function(nRowA) {
					// 主键不可修改
					$("input[name='chnlcd']").attr("readOnly", true);
					$("input[name='chnlcd']").val($(nRowA[0]).text());
					$("input[name='chnlnm']").val($(nRowA[1]).text());
					$("input[name='status']").val(
							$(nRowA[2]).text().substring(
									$(nRowA[2]).text().indexOf("[") + 1,
									$(nRowA[2]).text().indexOf("]"))).trigger(
							"change");
					$("input[name='creatm']").val($(nRowA[3]).text());
					$("input[name='cruser']").val($(nRowA[4]).text());
					$("input[name='modydt']").val($(nRowA[5]).text());
					$("input[name='mduser']").val($(nRowA[6]).text());
					$("input[name='remark']").val($(nRowA[7]).text());
					$("input[name='n_yin']").val($(nRowA[0]).text());
					$("#editModal").modal('show');
					$("#editModal").on("hide.bs.modal", function() {
						$(".alert-success", $('form', $("#editModal"))).hide();
						$('.alert-danger', $('form', $("#editModal"))).hide();
						$(".msg", $('form', $("#editModal"))).text("");
						$("#n_yin").val("");
						typegrid.submitFilter();
					});
				});

		// 新增窗口
		$("#add_btn").bind("click", function() {
			// 解除input readOnly属性
			$('input', $("#edit_form")).removeAttr("readOnly");
			// 清空 input值
			$("input", $("#editModal")).val("").trigger("change");
			$("#remark").val("");
			$("#editModal").modal('show');
			$("#editModal").on("hide.bs.modal", function() {
				$(".alert-success", $('form', $("#editModal"))).hide();
				$('.alert-danger', $('form', $("#editModal"))).hide();
				$(".msg", $('form', $("#editModal"))).text("");
				typegrid.submitFilter();
			});
			return false;
		});

		$("#btn_save_edit").click(function() { // 保存按钮
			$('form', $("#editModal")).submit();
		});

		// 赋值DIV
		var typeValid = Sunline.getValidate($('form', $("#editModal")),
				function() {
					var data = {};
					$.each($("input", $("#editModal")), function(i, n) {
						if (n.name != undefined && n.name != ""
								&& n.name != null) {
							data[n.name] = n.value;
						}
					});
					data.n_yin = $('#n_yin').val();
					Sunline.ajaxRouter("paychannel/adinch", data, 'post',
							function(resmap) {
								if (resmap.retMsg === "success") {
									bootbox.alert("交易成功！");
								} else {
									bootbox.alert(resmap.msg + "！");
								}
								$(".msg", $('form', $("#editModal"))).text(
										resmap.msg);
							});

				});
		$(".filter-cancel").click(function() {
			$("input[name='q_status']").select2("val", "");
		});
	};

	return {
		init : function() {
			handleTable();
		}
	}

}();