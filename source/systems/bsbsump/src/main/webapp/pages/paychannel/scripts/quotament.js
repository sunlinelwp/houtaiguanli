var kuprole = function() {
//	var inclfgDict = Sunline.getDict("E_INCLFG");
	var syscodDict = Sunline.getDict("PayChannel");//状态
	var status = Sunline.getDict("E_PYTYPE");//支付方式
	$("#transt").show();
	$("input[name='n_pytype']").select2({
		data : status,
		allowClear : true
	});
	$("input[name='pytype']").select2({
		data : status,
		allowClear : true
	});
	$("input[name='transt']").select2({
		data : syscodDict,
		allowClear : true
	});
	
	var handleTable = function() {
		var typegrid = new Datatable();
		var typeurl = Sunline.ajaxPath("paychannel/qylimt"); // URL???
		var typesendData = ["chnlcd"]; // 主键
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
											"data" : "ftbkcd", // 前置规定银行代码
											"sortable" : false,
											"searchable" : false
										},
										{
											"data" : "bankna", // 银行名称
											"sortable" : false,  
											"searchable" : false
										},
										{
											"data" : "bankcd", // 是渠道规定银行代码
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "pytype", // 支付方式
											"sortable" : false,  
											"searchable" : false,
											"render" : function(data, type, full) {
												for (var i = 0; i < status.length; i++) {
													if (status[i].id == data) {
														return status[i].text;
													}
												}
												return data;
											}
										},
										{
											"data" : "pypslt", // 对个人单笔限额
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "pypdlt", // 对个人日累限额
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "pycslt", // 对公司单笔限额
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "pycdlt", // 对公司日累限额
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "transt", // 状态
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
											"data" : null,
											"sortable" : false,
											"searchable" : false,
											"render" : function(data, type, full) {
												return "<a class='edit' href='javascript:;' data-src='"
														+ data.chnlcd+ "'>编辑 </a>";
											}
										} ]
							}
						});
		typegrid.bindTableDelete(typesendData); // 绑定数据删除？？？？
		typegrid.bindTableEdit(typesendData,
				function(nRowA) {
					// 主键不可修改
				$("input[name='chnlcd']").attr("readOnly",true);
				$("input[name='transt']").removeAttr("readOnly");
				$("input[name='chnlcd']").val($(nRowA[0]).text()); 
				$("input[name='chnlnm']").val($(nRowA[1]).text());
				$("input[name='ftbkcd']").val($(nRowA[2]).text());
				$("input[name='bankna']").val($(nRowA[3]).text());
				$("input[name='bankcd']").val($(nRowA[4]).text());
				$("input[name='pytype']").val($(nRowA[5]).text().substring($(nRowA[5]).text().indexOf("[")+1,$(nRowA[5]).text().indexOf("]"))).trigger("change");
				$("input[name='pypslt']").val($(nRowA[6]).text());
				$("input[name='pypdlt']").val($(nRowA[7]).text());
				$("input[name='pycslt']").val($(nRowA[8]).text());
				$("input[name='pycdlt']").val($(nRowA[9]).text());
				$("input[name='transt']").val($(nRowA[10]).text().substring($(nRowA[10]).text().indexOf("[")+1,$(nRowA[10]).text().indexOf("]"))).trigger("change");
				$("input[name='n_yin']").val($(nRowA[0]).text());
					$("#editModal").modal('show');
					$("#editModal").on(
							"hide.bs.modal",
							function() {
								$(".alert-success",
										$('form', $("#editModal"))).hide();
								$('.alert-danger',
										$('form', $("#editModal"))).hide();
								$(".msg", $('form', $("#editModal"))).text(
										"");
								$("#n_yin").val("");
								typegrid.submitFilter();
							});
				});

		// 新增窗口
		$("#add_btn").bind(
				"click",
				function() {
					// 解除input readOnly属性
//					$("#transt").show();
//					$("#transt").hide();
					$('input', $("#edit_form")).removeAttr("readOnly");
					$("input[name='transt']").attr("readOnly",true);
					// 清空 input值
					$("input", $("#editModal")).val("").trigger("change");
					$("#remark").val("");
					$("#editModal").modal('show');
					$("#editModal").on(
							"hide.bs.modal",
							function() {
								$(".alert-success",
										$('form', $("#editModal"))).hide();
								$('.alert-danger',
										$('form', $("#editModal"))).hide();
								$(".msg", $('form', $("#editModal"))).text(
										"");
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
					Sunline.ajaxRouter("paychannel/adlimt", data, 'post',
							function(resmap) {
								if (resmap.retMsg === "success") {
									bootbox.alert("交易成功！");
								} else {
									bootbox.alert("交易失败！");
								}
								$(".msg", $('form', $("#editModal"))).text(
										resmap.msg);
							});

				}
				);
				$(".filter-cancel").click(function(){
					$("input[name='n_pytype']").select2("val","");
				});
	};

	return {
		init : function() {
			handleTable();
		}
	}

}();