var kuprole = function() {
	var inclfgDict = Sunline.getDict("E_INCLFG");
//	var syscodDict = Sunline.getDict("PayChannel");//状态
	var status = Sunline.getDict("E_PYTYPE");//支付方式
	var acctpp = Sunline.getDict("E_ACCTPP");//账户属性
	var chgetp = Sunline.getDict("E_CHGETP");//收费类型
	$("input[name='n_pytype']").select2({
		data : status,
		allowClear : true
	});
	$("input[name='pytype']").select2({
		data : status,
		allowClear : true
	});
//	$("input[name='status']").select2({
//		data : syscodDict,
//		allowClear : true
//	});
	
	$("input[name='acctpp']").select2({
		data : acctpp,
		allowClear : true
	});
	$("input[name='n_acctpp']").select2({
		data : acctpp,
		allowClear : true
	});
	
	$("input[name='chgetp']").select2({
		data : chgetp,
		allowClear : true
	});
	$("input[name='n_chgetp']").select2({
		data : chgetp,
		allowClear : true
	});
	
	var handleTable = function() {
		var typegrid = new Datatable();
		var typeurl = Sunline.ajaxPath("paychannel/qychge"); // URL???
		var typesendData = ["orfbdt","orfbsq"]; // 主键
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
											"data" : "orfbdt", // 前置日期
											"sortable" : false,
											"searchable" : false
										},
										{
											"data" : "orfbsq", // 前置流水
											"sortable" : false,
											"searchable" : false
										},
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
											"data" : "efctdt", // 生效日期
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "acctpp", // 账户属性
											"sortable" : false,  
											"searchable" : false,
											"render" : function(data, type, full) {
												for (var i = 0; i < acctpp.length; i++) {
													if (acctpp[i].id == data) {
														return acctpp[i].text;
													}
												}
												return data;
											}
										},
										{
											"data" : "chgetp", // 收费类型
											"sortable" : false,  
											"searchable" : false,
											"render" : function(data, type, full) {
												for (var i = 0; i < chgetp.length; i++) {
													if (chgetp[i].id == data) {
														return chgetp[i].text;
													}
												}
												return data;
											}
										},
										{
											"data" : "staram", // 计费起始金额
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "termam", // 计费最大金额
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "lowfee", // 最低费用（单笔收费费用）
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "higfee", // 最高费用
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : "rateit", // 收费比例
											"sortable" : false,  
											"searchable" : false,
										},
										{
											"data" : null,
											"sortable" : false,
											"searchable" : false,
											"render" : function(data, type, full) {
												return "<a class='edit' href='javascript:;' data-src='"
														+ data.chnlcd+ "'>编辑 </a>";
											}
										},
										{
											"data" : null,
											"sortable" : false,
											"searchable" : false,
											"render" : function(data, type, full) {
												return "<a class='delete' href='javascript:;' data-src='"
														+ data.orfbdt+ ","+data.orfbsq+"'>删除 </a>";
											}
										} ]
							}
						});
		typegrid.bindTableDelete(typesendData); // 绑定数据删除？？？？
		typegrid.bindTableEdit(typesendData,
				function(nRowA) {
					// 主键不可修改
				$("input[name='orfbdt']").attr("readOnly",true);
				$("input[name='orfbsq']").attr("readOnly",true);
				$("input[name='chnlnm']").attr("readOnly",true);
				$("input[name='chnlcd']").attr("readOnly",true);
				$("input[name='orfbdt']").val($(nRowA[0]).text());
				$("input[name='orfbsq']").val($(nRowA[1]).text());
				$("input[name='chnlcd']").val($(nRowA[2]).text());
				$("input[name='chnlnm']").val($(nRowA[3]).text());
				$("input[name='pytype']").val($(nRowA[4]).text().substring($(nRowA[4]).text().indexOf("[")+1,$(nRowA[4]).text().indexOf("]"))).trigger("change");
				$("input[name='efctdt']").val($(nRowA[5]).text());
				$("input[name='acctpp']").val($(nRowA[6]).text().substring($(nRowA[6]).text().indexOf("[")+1,$(nRowA[6]).text().indexOf("]"))).trigger("change");
				$("input[name='chgetp']").val($(nRowA[7]).text().substring($(nRowA[7]).text().indexOf("[")+1,$(nRowA[7]).text().indexOf("]"))).trigger("change");				
				$("input[name='staram']").val($(nRowA[8]).text()); 
				$("input[name='termam']").val($(nRowA[9]).text());
				$("input[name='lowfee']").val($(nRowA[10]).text());
				$("input[name='higfee']").val($(nRowA[11]).text());
				$("input[name='rateit']").val($(nRowA[12]).text());
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
					$('input', $("#edit_form")).removeAttr("readOnly");				
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
					Sunline.ajaxRouter("paychannel/adchge", data, 'post',
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
					$("input[name='q_pytype']").select2("val","");
				});
	};

	return {
		init : function() {
			handleTable();
		}
	}

}();