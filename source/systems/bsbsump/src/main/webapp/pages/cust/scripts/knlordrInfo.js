var knlordrInfo = function() {
	//渠道号
	var scapnoDict = Sunline.getDict("E_SCAPNO");
	$("input[name='scapno']").select2({
		data : scapnoDict,
		allowClear : true,
		placeholder : "请选择"
	});
	var handleTable = function(q_frondt, q_ckstat,q_fronsq,q_custac) {
		var grid = new Datatable();
		var typeurl1 = Sunline.ajaxPath("cust/qrordr"); // URL???
		var typesendData1 = ["ordrid", "ordram"]; // 主键
		if(!Sunline.isNull(q_fronsq)){
			grid.setAjaxParam("q_fronsq",q_fronsq);
        	$("#orfbsq",$("#editModal1")).val(q_fronsq);
        	$("#q_fronsq").val(q_fronsq);
        }
//		if(!Sunline.isNull(q_custac)){
//			grid.setAjaxParam("q_custac",q_custac);
//        	$("#q_custac",$("#editModal1")).val(q_custac);
//        }
		if(!Sunline.isNull(q_frondt)){
			grid.setAjaxParam("q_frondt",q_frondt);
        	$("#orfbdt",$("#editModal1")).val(q_frondt);
        	$("#q_frondt").val(q_frondt);
        }
//		if(!Sunline.isNull(q_ckstat)){
//			grid.setAjaxParam("q_ckstat",q_ckstat);
//        	$("#q_ckstat",$("#editModal1")).val(q_ckstat);
//        }
		grid.init({
					src : $("#ordrinfo_ajax"),
					deleteData : typesendData1,
					onSuccess : function(grid) {
						$("#inptam").text(grid.getDataTable().context['0'].json['inptam']);
						$("#totlam").text(grid.getDataTable().context['0'].json['totlam']);
					},
					onError : function(grid) {
					},
					dataTable : {
						"ajax" : {
							"url" : typeurl1,
						},
						"bDestroy": true,
						"columns" : [
								{
									"data" : "ordrid", // 额度产品号
									"sortable" : false,
									"searchable" : false
								},
//								{
//									"data" : "trantp", 
//									"sortable" : false,
//									"searchable" : false
//								},
//								{
//									"data" : "ordrst", 
//									"sortable" : false,
//									"searchable" : false,
//								},
								{
									"data" : "ordram", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "keepdt", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "chgeam", 
									"sortable" : false,  
									"searchable" : false,
								},
								{
									"data" : "scapno", 
									"sortable" : false,  
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < scapnoDict.length; i++) {
											if (scapnoDict[i].id == data) {
												return scapnoDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "chckno", 
									"sortable" : false,  
									"searchable" : false
								}
/**								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='edit' href='javascript:;' data-src='"
												+ data.ordrid+ "'>编辑 </a>";
									}
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='delete' href='javascript:;' data-src='"
												+ data.ordrid+ "'>删除 </a>";
									}
								} **/]
					}
				});
		grid.bindTableDelete(typesendData1); // 绑定数据删除？？？？
		grid.bindTableEdit(typesendData1,
				function(nRowA) {
					// 主键不可修改
				$("input[name='ordrid']").attr("readOnly",true);
				$("input[name='ordram']").attr("readOnly",true);
				$("input[name='keepdt']").attr("readOnly",true);	
				$("input[name='ordrid']").val($(nRowA[0]).text()); 
				$("input[name='ordram']").val($(nRowA[1]).text());
				$("input[name='keepdt']").val($(nRowA[2]).text());
				$("input[name='chgeam']").val($(nRowA[3]).text());
				$("input[name='scapno']").val($(nRowA[4]).text());
				$("input[name='chckno']").val($(nRowA[5]).text());
				$("input[name='orfbdt']").val(q_frondt);
				$("input[name='orfbsq']").val(q_fronsq);
					$("#editModal1").modal('show');
					$("#editModal1").on(
							"hide.bs.modal",
							function() {
								$(".alert-success",
										$('form', $("#editModal1"))).hide();
								$('.alert-danger',
										$('form', $("#editModal1"))).hide();
								$(".msg", $('form', $("#editModal1"))).text(
										"");
								grid.submitFilter();
							});
				});

		// 时间插件
		$('.date-picker',$("#editModal1")).datepicker({
			rtl : Metronic.isRTL(),
			orientation : "left",
			autoclose : true,
			language : 'zh-CN',
		});
		
		// 新增窗口
		$("#add_btn1").bind(
				"click",
				function() {
					// 解除input readOnly属性
					$('input', $("#edit_form1")).removeAttr("readOnly");
				
					// 清空 input值
					$("input", $("#editModal1")).val("").trigger("change");
//					$("input[name='typecd']", $("#edittypeModal")).val(prodcd);
					$("#orfbsq").val(q_fronsq);
					$("#orfbdt").val(q_frondt);
					$("#editModal1").modal('show');
					$("#editModal1").on(
							"hide.bs.modal",
							function() {
								$(".alert-success",
										$('form', $("#editModal1"))).hide();
								$('.alert-danger',
										$('form', $("#editModal1"))).hide();
								$(".msg", $('form', $("#editModal1"))).text(
										"");
								grid.submitFilter();
							});
					return false;
				});

//		$("#btn_save_edit1").click(function() { // 保存按钮
//			console.info("111999");
//			$('form', $("#editModal1")).submit();
//		});

//		// 赋值DIV
//		var typeValid = Sunline.getValidate($('form', $("#editModal1")),
//				function() {
//					var data = {};
//					$.each($("input", $("#editModal1")), function(i, n) {
//						if (n.name != undefined && n.name != ""
//								&& n.name != null) {
//							data[n.name] = n.value;
//						}
//					});
//					
//					Sunline.ajaxRouter("cust/hrutsg", data, 'post',
//							function(ret) {
//								if (ret.ret === "success") {
//									$(".alert-success",
//											$('form', $("#editModal1")))
//											.show();
//									$('.alert-danger',
//											$('form', $("#editModal1")))
//											.hide();
//								} else {
//									$(".alert-success",
//											$('form', $("#editModal1")))
//											.hide();
//									$('.alert-danger',
//											$('form', $("#editModal1")))
//											.show();
//								}
//								$(".msg", $('form', $("#editModal1"))).text(
//										ret.msg);
//							});
//
//				}
//				);
//				$(".filter-cancel").click(function(){
//					$("input[name='q_roleid']").select2("val","");
//					$("input[name='q_rolena']").select2("val","");
//				});
		
		/*
		 * 表单验证方法
		 */
		var editform = $("#edit_form1");
		var editerror = $('.alert-danger', editform);
		var editsuccess = $('.alert-success', editform);
		var validator = editform.validate({
			errorElement : 'span',
			errorClass : 'help-block help-block-error',
			focusInvalid : false,
			ignore : "",
			rules : { // 验证规则
				ordrid : {
					required : true,
					maxlength : 40
				},
				ordram : {
					required : true,
					maxlength : 21
				},
				keepdt : {
					required : true,
					maxlength : 8
				},
				scapno : {
					required : true,
					maxlength : 4
				},
				chgeam : {
					maxlength : 21
				},
			},

			invalidHandler : function(event, validator) {
				editsuccess.hide();
				editerror.show();
				Metronic.scrollTo(editerror, -200);
			},

			errorPlacement : function(error, element) {
				element.parent().append(error);
			},

			highlight : function(element) {
				$(element).closest('.form-group').removeClass("has-success")
						.addClass('has-error');
			},

			unhighlight : function(element) {

			},

			success : function(label, element) {
				var icon = $(element).parent('.input-icon').children('i');
				$(element).closest('.form-group').removeClass('has-error')
						.addClass('has-success'); // set success class to the
													// control group
				icon.removeClass("fa-warning").addClass("fa-check");
			},
			submitHandler : function(form) {
				/*
				 * 提交数据,必须是json对象 返回也必须是json对象
				 */
				var data = {};
				$.each($("input", editform), function(i, n) {
					if (n.name != "") {
						data[n.name] = n.value;
						console.info(n.name);
						console.info(n.value);
					}
				});
				
				var r = confirm('数据提交后将不允许修改，请检查数据！');
				if (r == false) {
					return;
				}
				Sunline.ajaxRouter("cust/hrutsg", data, "post", function(redata) {
					$('.msg', editform).text(redata.retMsg);
					if (redata.retCode == "0000") {
						$('.alert-success', editform).show();
						$('.alert-danger', editform).hide();
						
					} else {
						$('.alert-success', editform).hide();
						$('.alert-danger', editform).show();
					}
				}, function() {
					$('.msg', editform).text("请求出错!");
					$('.alert-success', editform).hide();
					$('.alert-danger', editform).show();
				}, "json");
			}
		});
	};

	return {
		init : function(q_frondt, q_ckstat,q_fronsq,q_custac) {
			handleTable(q_frondt, q_ckstat,q_fronsq,q_custac);
		}
	}

}();