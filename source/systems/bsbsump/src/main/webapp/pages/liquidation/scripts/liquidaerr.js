var Upcpay = function() {
	var chkStatusDict = Sunline.getDict("chkStatus");
	var paystausDict = Sunline.getDict("payStatus");
	var signstausDict = Sunline.getDict("signStatus");
	var checkstausDict = Sunline.getDict("checkStatus");
	var grid = new Datatable();
	var _isFirst = true;
	var _tranDate = "0000";
	var _formartDict = function(dict, value) {
		for (var i = 0; i < dict.length; i++) {
			if (dict[i].dictName == value) {
				return dict[i].dictId;
			}
		}
		return value;
	};
	var formartM = function(value) {
//		if (value.indexOf('.') == -1) {
//			return value + ".00";
//		} else if (value.split('.')[1].length == 1) {
//			return value + '0';
//		} else {
//			return value;
//		}
		return value.toFixed(2);
	};
	var formarlendingdirection = function(value) {
		if (value=="D") {
			return "D-借";
		} else if (value=="C") {
			return "C-贷";
		} else {
			return value;
		}
	};
	var formarReconciliation = function(value) {
		if (value=="1") {
			return value+"-平";
		} else if (value=="2") {
			return value+"-拆分金额多";
		} else if (value=="3") {
			return value+"-拆分金额少";
		} else {
			return value;
		}
	};
	var formarLoanstatus = function(value) {
		if(value=="I"){
			return value+"-注册但未活动";
		}else if(value=="A"){
			return value+"-活动状态";
		}else if(value=="R"){
			return value+"-分期展期";
		}else if(value=="T"){
			return value+"-终止";
		}else if(value=="F"){
			return value+"-完成";
		}else if(value=="S"){
			return value+"-缩期";
		}else if(value=="C"){
			return value+"-撤销";
		}else{
			return value;
		}
	};
	var handleForm = function() {

		if (jQuery().datepicker) {
			$('.date-picker').datepicker({
				rtl : Metronic.isRTL(),
				orientation : "left",
				autoclose : true,
				language : 'zh-CN',
			});
			// $('body').removeClass("modal-open"); // fix bug when inline
			// picker is used in modal

		}
		;
		$("#checkS").select2({
			width : "100%",
			data : checkstausDict,
			formatSelection : function(item) {
				return item.dictName;
			},
			formatResult : function(item) {
				return item.dictName;
			}
		});
		$("#checkS").select2("val", "N");
	};
	var readFile = function() {
		$('#file-form').validate({
			errorElement : 'span', // default input error message container
			errorClass : 'help-block', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			rules : {
				checkdate : {
					required : true
				}
			},
			messages : {
				checkdate : {
					required : "操作日期必填"
				}
			},

			invalidHandler : function(event, validator) { // display error
															// alert on form
															// submit
				$('.alert-danger', $('#file-form')).show();
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set
																			// error
																			// class
																			// to
																			// the
																			// control
																			// group
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
				label.remove();
			},
			submitHandler : function(form) {
			},
			errorPlacement : function(error, element) {
				error.insertAfter(element.closest('.input-icon'));
			}

		});
		$('#submit').click(
				function() {
					if (!$('#file-form').validate().form()) {
						return;
					}
					var trandt = $('#check-date').val();
					if (_tranDate == $('#check-date').val()) {
						return;
					}
					var input = {};
					input.file = 'st_wb_nomatch_';
					input.trandt = trandt;
					$("#myModal").modal('show');
					Sunline.ajaxRouter("liquidation/check", input, "POST",
							function(redata) {
								// bootbox.alert("读取文件成功");
								// 读取数据区表
								$("#myModal").modal('hide');
								if (redata.retCode == '0000') {
									$('.table-container .alert-danger').css(
											"display", "none");
									grid.setAjaxParam("trandt",
											$('#check-date').val());
									// grid.setAjaxParam("checkStatus",$('#checkS').select2("val"));
									grid.submitFilter();
									$('#allsum').text(
											formartM(redata.totalDebit))
											.css({
												'padding' : '8px 0 0 10px',
												'font-size' : '18px',
												'font-weight' : '700'
											});
									$('#fee').text(
											formartM(redata.totalCredit))
											.css({
												'padding' : '8px 0 0 10px',
												'font-size' : '18px',
												'font-weight' : '700'
											});
									$('#flow').text(redata.operationFlow).css({
										'padding' : '8px 0 0 10px',
										'font-size' : '18px',
										'font-weight' : '700'
									});
									console.info($('#checkS').select2("val"));
									// if(redata.status == 'N' &&
									// $('#checkS').select2("val") == 'N' &&
									// $('#cppchk_ajax tbody tr').length == 1 &&
									// $('#cppchk_ajax tbody tr td').length ==
									// 1) {
									// $('#cel_in').removeAttr('disabled');
									// } else {
									// $('#cel_in').attr('disabled','disabled');
									// }
								} else {
									bootbox.alert(redata.retMsg);
								}
							}, function(redata) {
								$("#myModal").modal('hide');
								bootbox.alert("交易异常哦，请检查网络设置货重新登录");
							}, "json", false);
				});
	};
	var handlerTable = function() {
		var url = Sunline.ajaxPath("liquidation/cppchk");
		var editForm = function(nRowA) {
			$("#m_acctno").val($(nRowA[0]).text());
			$("#m_acctna").val($(nRowA[1]).text());
			$('.mod-form .alert').css('display', 'none');
			$('.mod-form .form-group').removeClass('has-error');
			$("#editModal").modal('show');
		};
		grid.setAjaxParam("trandt", "");
		// grid.setAjaxParam("checkStatus",$('#checkS').select2("val"));
		grid.init({
			src : $("#cppchk_ajax"),
			deleteData : sendData,
			onSuccess : function(grid) {
				// execute some code after table records loaded
				$('.table-container .alert-danger').css("display", "none");
			},
			onError : function(grid) {
				// execute some code on network or other general error
			},
			dataTable : { // here you can define a typical datatable settings
							// from http://datatables.net/usage/options
				"ajax" : {
					"url" : url, // ajax source
				},
				"columns" : [ {
					"data" : "accountingDate",
					"sortable" : false,
					"searchable" : false
				}, {
					"data" : "trafficflow",
					"sortable" : false,
					"searchable" : false
				}, {
					"data" : "lendingdirection",
					"sortable" : false,
					"searchable" : false,
					"render" : function(data, type, full) {
						return formarlendingdirection(data);
					}
				}, {
					"data" : "transactionamount",
					"sortable" : false,
					"searchable" : false,
					"render" : function(data, type, full) {
						return formartM(data);
					}
				}, {
					"data" : "accountnumber",
					"sortable" : false,
					"searchable" : false,
					"sliderable" : true
				}, {
					"data" : "reconciliation",
					"sortable" : false,
					"searchable" : false,
					"render" : function(data, type, full) {
						return formarReconciliation(data);
					}
				}, {
					"data" : "syndicatednumber",
					"sortable" : false,
					"searchable" : false,

				}, {
					"data" : "banknumber",
					"sortable" : false,
					"searchable" : false,

				}, {
					"data" : "mechanismnumber",
					"sortable" : false,
					"searchable" : false,

				}, {
					"data" : "iounumber",
					"sortable" : false,
					"searchable" : false,

				}, {
					"data" : "loanstatus",
					"width" : "8%",
					"sortable" : false,
					"searchable" : false,
					"render" : function(data, type, full) {
						return formarLoanstatus(data);
					}
				}, ]
			}
		});
		// $(".table-group-actions").append("<button id='deal_btn' class='btn
		// btn-sm green table-group-action-submit'><i class='fa
		// fa-rotate-right'></i>&nbsp;差错处理</button></div>");
		var sendData = [ "checkDate" ];
		grid.bindTableDelete(sendData);
		grid.bindTableEdit(sendData, editForm);
		_isFirst = false;

		_tranDate = $('#check-date').val();
	};
	return {
		init : function() {
			readFile();
			handleForm();
			handlerTable();
		}
	}
}()