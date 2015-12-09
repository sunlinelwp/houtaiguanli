var knlsignafcheck = function() {
	var crcycdDict = Sunline.getDict("E_CRCYCD");
	var idtftpDict = Sunline.getDict("E_IDTFTP");
	var ckstatDict = Sunline.getDict("E_CKSTAT");
	var objDict = [];
	for (var i = 0; i < ckstatDict.length; i++) {
		if (ckstatDict[i].id == "02" || ckstatDict[i].id == "03") {
			objDict.push(ckstatDict[i]);
		}
	}
	$("input[name='ckstat']").select2({
		data : objDict,
		allowClear : true,
		placeholder : "请选择"
	});
	$("input[name='q_status']").select2({
		data : ckstatDict,
		allowClear : true,
		placeholder : "请选择"
	});
	var statfg;//状态标识
	var content = $('#edit_load');//配置子页面
	var handleTable = function() {
		var typegrid = new Datatable();
		var typeurl = Sunline.ajaxPath("cust/qrpyif"); // URL???
		var typesendData = ["frondt", "ckstat", "fronsq", "custac", "remark"]; // 主键
		typegrid.init({
					src : $("#datatable_ajax"),
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
									"data" : "custac", // 额度产品号
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "custna", 
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "tranam", 
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "fronsq", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "frondt", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "busino", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "brchno", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "cardno", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "acctna", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "cacttp", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "cactpt", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "crcycd", 
									"sortable" : false,  
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < crcycdDict.length; i++) {
											if (crcycdDict[i].id == data) {
												return crcycdDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "idtfno", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "idtftp", 
									"sortable" : false,  
									"searchable" : false, 
									"render" : function(data, type, full) {
										for (var i = 0; i < idtftpDict.length; i++) {
											if (idtftpDict[i].id == data) {
												return idtftpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "teleno", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "ysxxfe", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "reason", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "ckstat", 
									"sortable" : false,  
									"searchable" : false, 
									"render" : function(data, type, full) {
										for (var i = 0; i < ckstatDict.length; i++) {
											if (ckstatDict[i].id == data) {
												statfg = data;
												return ckstatDict[i].text;
											}
										}
										statfg = data;
										return data;
									}
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										if (statfg == "01" || statfg == "03") {
											return "<a class='edit' href='javascript:;' data-src='"
													 + data.fronsq + "'>编辑订单信息 </a>";
										} else if (statfg == "02") {
											return "";
										} else if (statfg == "00") {
											return "<a class='edit' href='javascript:;' data-src='"
											 + data.fronsq + "'>审核 </a>";
										}
									}
								}
								 ]
					}
				});

		
		typegrid.bindTableEdit(typesendData,
				function(nRowA) {
			
					// 主键不可修改
					$("input[name='custac']").val($(nRowA[0]).text());
					$("input[name='custna']").val($(nRowA[1]).text());
					$("input[name='fronsq']").val($(nRowA[3]).text());
					$("input[name='frondt']").val($(nRowA[4]).text());
					$("input[name='custac']").attr("readOnly",true);
					$("input[name='custna']").attr("readOnly",true);
					$("input[name='fronsq']").attr("readOnly",true);
					$("input[name='frondt']").attr("readOnly",true);
					if ($(nRowA[17]).text() == ckstatDict[3].text || $(nRowA[17]).text() == ckstatDict[1].text) {
						content.html('');						    
					        $.ajax({
					            type: "GET",
					            url: "../cust/knlordrafcheck",
					            dataType: "html",
					            success: function(res) 
					            {    
					                content.html(res);
					                content.ready(function(){               	
					                	  Metronic.initUniform();
					                	  try{      
					                		  if (!Sunline.isNull($(nRowA[3]).text())) {
					                			  knlordrafcheck.init($(nRowA[4]).text(),$(nRowA[18]).text(),$(nRowA[3]).text(),$(nRowA[0]).text());
					                		  }
					                	  }catch(e){
					                		  bootbox.alert("子页面加载失败！");
					                	  }
					                });             
					            },
					            error: function(xhr, ajaxOptions, thrownError)
					            {
					            },
					            async: false
					        });
						$("#addOrdrModal").modal('show');
						$("#addOrdrModal").on(
								"hide.bs.modal",
								function() {
									$(".alert-success",
											$('form', $("#addOrdrModal"))).hide();
									$('.alert-danger',
											$('form', $("#addOrdrModal"))).hide();
									$(".msg", $('form', $("#addOrdrModal"))).text(
											"");
									typegrid.submitFilter();
								});
					} else if ($(nRowA[17]).text() == ckstatDict[0].text){
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
					}
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
					Sunline.ajaxRouter("cust/ckrtsd", data, 'post',
							function(ret) {
								if (ret.ret === "success") {
									$(".alert-success",
											$('form', $("#editModal")))
											.show();
									$('.alert-danger',
											$('form', $("#editModal")))
											.hide();
								} else {
									$(".alert-success",
											$('form', $("#editModal")))
											.hide();
									$('.alert-danger',
											$('form', $("#editModal")))
											.show();
								}
								$(".msg", $('form', $("#editModal"))).text(
										ret.msg);
							});

				}
				);

//		var ordrValid = Sunline.getValidate($('form', $("#addModal")),
//				function() {
//					var data = {};
//					$.each($("input", $("#addModal")), function(i, n) {
//						if (n.name != undefined && n.name != ""
//								&& n.name != null) {
//							data[n.name] = n.value;
//						}
//					});
//					
////					Sunline.ajaxRouter("cust/qrordr", data, 'post',
////							function(ret) {
////								if (ret.ret === "success") {
////									$(".alert-success",
////											$('form', $("#editModal")))
////											.show();
////									$('.alert-danger',
////											$('form', $("#editModal")))
////											.hide();
////									$(".msg", $('form', $("#editModal"))).text(
////											"请十秒后再次刷新！");
////								} else {
////									$(".alert-success",
////											$('form', $("#editModal")))
////											.hide();
////									$('.alert-danger',
////											$('form', $("#editModal")))
////											.show();
////									$(".msg", $('form', $("#editModal"))).text(
////											ret.msg);
////								}
////							});
//
//				}
//				);
				$(".filter-cancel").click(function(){
					$("input[name='q_frondt_sign']").select2("val","");
					$("input[name='q_status']").select2("val","");
				});
	};

	return {
		init : function() {
			handleTable();
		}
	}

}();