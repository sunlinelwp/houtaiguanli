var knlbillsign = function() {
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
		var typeurl = Sunline.ajaxPath("cust/qrhpay"); // URL???
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
									"data" : "brchna", 
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
//								{
//									"data" : "busino", 
//									"sortable" : false,  
//									"searchable" : false
//								},
//								{
//									"data" : "brchno", 
//									"sortable" : false,  
//									"searchable" : false
//								},
								{
									"data" : "cardno", 
									"sortable" : false,  
									"searchable" : false
								},
//								{
//									"data" : "acctna", 
//									"sortable" : false,  
//									"searchable" : false
//								},
//								{
//									"data" : "cacttp", 
//									"sortable" : false,  
//									"searchable" : false
//								},
//								{
//									"data" : "crcycd", 
//									"sortable" : false,  
//									"searchable" : false,
//									"render" : function(data, type, full) {
//										for (var i = 0; i < crcycdDict.length; i++) {
//											if (crcycdDict[i].id == data) {
//												return crcycdDict[i].text;
//											}
//										}
//										return data;
//									}
//								},
//								{
//									"data" : "idtfno", 
//									"sortable" : false,  
//									"searchable" : false
//								},
//								{
//									"data" : "idtftp", 
//									"sortable" : false,  
//									"searchable" : false, 
//									"render" : function(data, type, full) {
//										for (var i = 0; i < idtftpDict.length; i++) {
//											if (idtftpDict[i].id == data) {
//												return idtftpDict[i].text;
//											}
//										}
//										return data;
//									}
//								},
								{
									"data" : "teleno", 
									"sortable" : false,  
									"searchable" : false
								},
//								{
//									"data" : "ysxxfe", 
//									"sortable" : false,  
//									"searchable" : false
//								},
//								{
//									"data" : "reason", 
//									"sortable" : false,  
//									"searchable" : false
//								},
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
									"data" : "frontm", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "servtp", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										if (statfg == "03") {
											return "<a class='edit' href='javascript:;' data-src='"
													 + data.fronsq + "'>登记订单信息 </a>";
										} else if (statfg == "01" || statfg == "02") {
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
					$("input[name='tranam']").val($(nRowA[3]).text());
					$("input[name='fronsq']").val($(nRowA[4]).text());
					$("input[name='frondt']").val($(nRowA[5]).text());
					$("input[name='teleno']").val($(nRowA[7]).text());
					$("input[name='custac']").attr("readOnly",true);
					$("input[name='custna']").attr("readOnly",true);
					$("input[name='fronsq']").attr("readOnly",true);
					$("input[name='frondt']").attr("readOnly",true);
					$("input[name='teleno']").attr("readOnly",true);
					$("input[name='tranam']").attr("readOnly",true);
					if ($(nRowA[8]).text() == ckstatDict[3].text) {
						content.html('');						    
					        $.ajax({
					            type: "GET",
					            url: "../cust/knlordrInfo",
					            dataType: "html",
					            success: function(res) 
					            {    
					                content.html(res);
					                content.ready(function(){               	
					                	  Metronic.initUniform();
					                	  try{      
					                		  if (!Sunline.isNull($(nRowA[4]).text())) {
					                			  knlordrInfo.init($(nRowA[5]).text(),$(nRowA[8]).text(),$(nRowA[4]).text(),$(nRowA[0]).text(),$(nRowA[10]).text());
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
					} else if ($(nRowA[8]).text() == ckstatDict[0].text){
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
								if (ret.ret == "success") {
									$(".alert-success",
											$('form', $("#editModal")))
											.show();
									$('.alert-danger',
											$('form', $("#editModal")))
											.hide();
									$("#editModal").modal('hide');	
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
					if ($("#ckstat").val() != null && $("#ckstat").val() != "" && $("#ckstat").val() == "03") {
						Sunline.ajaxRouter("cust/sendms", data, 'post',
								function(ret) {
									if (ret.ret == "success") {
										$(".alert-success",
												$('form', $("#editModal")))
												.show();
										$('.alert-danger',
												$('form', $("#editModal")))
												.hide();
										$("#editModal").modal('hide');	
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
		
		//下载
		$('#download').click(function(){
			$('#download').attr("disabled",true);
			var input = {};
			input.frondt = $('#q_frondt_sign').val();
			input.status = $('#q_status').val();
			Sunline.ajaxRouter(
		        	"cust/getSignFile",
		        	 input,
		        	"POST",
		            function(redata){
		        		$('#download').attr("disabled",false);
		        		if(redata.retCode == '0000'){
		        			_filename = redata.fileName;
		        			bootbox.confirm("文件["+redata.fileName+"]已生成，确定下载文件？",function(res){
		        				if (!res) {
		        					return;
		        				}
		        				window.location.href = Sunline.getBasePath() + '/rest/download/downLoadFile?path=' + redata.filePath + redata.fileName;
		        			});
		        		} else {
		        			bootbox.alert(redata.retMsg);
		        		}
		        	},
		        	function(redata){
		        		$('#download').attr("disabled",false);
		        		console.info(redata);
		        		bootbox.alert("网络异常，请重试！"); 
		        	},
		        	"json",
		        	false); 
		});
		
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