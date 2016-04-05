var knlbillsigns = function() {
	var crcycdDict = Sunline.getDict("E_CRCYCD");
	var idtftpDict = Sunline.getDict("E_IDTFTP");
	var ckstatDict = Sunline.getDict("E_CKSTAT");
	var amntcdDict=Sunline.getDict("amntcd");
	var flowtpDict=Sunline.getDict("flowtp");
	var transtDict=Sunline.getDict("transt");
	var objDict = [];
	for (var i = 0; i < ckstatDict.length; i++) {
		if (ckstatDict[i].id == "02" || ckstatDict[i].id == "03") {
			objDict.push(ckstatDict[i]);
		}
	}
	
	var formartDict = function(dict,value){
		for(var i=0 ; i<dict.length ; i++){
			if(value == dict[i].dictId){
				return dict[i].dictName;
			}
			if(value == dict[i].dictName){
				return dict[i].dictId;
			}
		}
		return value;
	};
	var formartM = function(value){
		value = value.toString();
		if(value.toString().indexOf('.') == -1){
			return value+".00";
		}else if(value.split('.')[1].length == 1) {
			return value+'0';
		} else {
			return value;
		}
	};
	var formartTime = function(time){
		if(time.length == 8){
			return time.substr(0,1)+":"+time.substr(1,2)+":"+time.substr(3,2);
		}
		return time.substr(0,2)+":"+time.substr(2,2)+":"+time.substr(4,2);
	};
	
	//格式化时间为yyyy-mm-dd hh:mm:ss
	var formartTimes = function (time){
		return time.substr(0,4)+""+time.substr(4,2)+""+time.substr(6,2)+" ";//+time.substr(8,2)+":"+time.substr(10,2)+":"+time.substr(12,2);
		
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
	var typegrid = new Datatable();
	var handleTable = function() {
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
									"searchable" : false,
									"render" : function(data, type, full) {
											return "<a href='javascript:;' onclick='knlbillsigns.qryjyInfos(" +data+ ")'>"+ data +"</a>";
									}
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
									"data" : "canuse", 
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
									"data" : "cardno", 
									"sortable" : false,  
									"searchable" : false
								},
								{
									"data" : "teleno", 
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
					$("input[name='fronsq']").val($(nRowA[5]).text());
					$("input[name='frondt']").val($(nRowA[6]).text());
					$("input[name='teleno']").val($(nRowA[8]).text());
					$("input[name='custac']").attr("readOnly",true);
					$("input[name='custna']").attr("readOnly",true);
					$("input[name='fronsq']").attr("readOnly",true);
					$("input[name='frondt']").attr("readOnly",true);
					$("input[name='teleno']").attr("readOnly",true);
					$("input[name='tranam']").attr("readOnly",true);
					if ($(nRowA[9]).text() == ckstatDict[3].text) {
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
					                			  knlordrInfo.init($(nRowA[6]).text(),$(nRowA[9]).text(),$(nRowA[5]).text(),$(nRowA[0]).text(),$(nRowA[11]).text());
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
					} else if ($(nRowA[9]).text() == ckstatDict[0].text){
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
		
		//下载
		$('#download').click(function(){
			$('#download').attr("disabled",true);
			var input = {};
			input.frondt = $('#q_frondt').val();
			input.status = $('#q_status').val();
			input.custac = $('#q_custac').val();
			input.custna = $('#q_custna').val();
			input.enddat = $('#q_enddat').val();
			input.teleno = $('#q_teleno').val();
			input.servno = $('#q_servno').val();
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

	};
	
	//时间插件
	var hander = function() {
		if (jQuery().datepicker) {
			$('#indate').datepicker({
				rtl : Metronic.isRTL(),
				orientation : "left",
				autoclose : true,
				language : 'zh-CN',
			});
		};
		if (jQuery().datepicker) {
			$('#eddate').datepicker({
				rtl : Metronic.isRTL(),
				orientation : "left",
				autoclose : true,
				language : 'zh-CN',
			});
		};
	}

	
	//查询
	var submitInfo = function(){
		if(!$('#detl-form').validate().form()){
			return;
		}
		typegrid.setAjaxParam("custac",$('#q_custac').val());
		typegrid.setAjaxParam("custna",$('#q_custna').val());
		typegrid.setAjaxParam("teleno",$('#q_teleno').val());
		typegrid.setAjaxParam("servno",$('#q_servno').val());
		typegrid.setAjaxParam("status",$('#q_status').val());
		typegrid.setAjaxParam("frondt",$('#q_frondt').val());
		typegrid.setAjaxParam("enddat",$('#q_enddat').val());
		typegrid.submitFilter();
		
	}
	
	var click = function(){
		//清空
		$('#cancle').bind("click", function() {
			$("input[name='q_custac']").val("");
			$("input[name='q_custna']").val("");
			$("input[name='q_teleno']").val("");
			$("input[name='q_servno']").val("");
			$("input[name='q_status']").select2("val","");
			$("input[name='q_frondt']").val("");
			$("input[name='q_enddat']").val("");
			submitInfo();
		});
	}
	
	
	//交易明细
	var qryjyInfo = function(custac){
		$(document).ready(function () {
            $('#cif_tran_ajax').dataTable().fnDestroy(); 
           });
		
		var ecctno=custac;
		var tran_grid = new Datatable();
		$('#tran_custac').val(ecctno);
		var url1 = Sunline.ajaxPath("cust/cutrif");
		tran_grid.setAjaxParam("ecctno",ecctno);
		tran_grid.setAjaxParam("from","");
		tran_grid.setAjaxParam("to","");
		tran_grid.setAjaxParam("eccttp","2");
		tran_grid.setAjaxParam("crcycd","01");
		tran_grid.init({
	        src: $("#cif_tran_ajax"),
//	        deleteData: sendData,
	        onSuccess: function (tran_grid) {
	            // execute some code after table records loaded
//	        	$('.cif_tran_ajax_wrapper .alert-danger').css("display","none");
	        },
	        onError: function (tran_grid) {
	            // execute some code on network or other general error
	        	//$('.cif_tran_ajax_wrapper .alert-danger').css("display","none");
	        	//console.info("It is error!");
	        },
	        dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
	            "ajax": {
	                "url": url1, // ajax source
	            },
	            "columns" : [{
		            	"data": "trandt",
		            	"sortable": false,
		            	"searchable": false
	            	},{     
		            	"data": "trantm",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		return formartTime(data);
		            	}
		            },{     
		            	"data": "tranam",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		return formartM(data);
		            	}
		            },{ 
		            	"data": "chnlno",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "avalbl",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		return formartM(data);
		            	}
		            },{ 
		            	"data": "smryds",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "jnlseq",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "flowtp",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		 for (var i = 0; i < flowtpDict.length; i++) {
		                          if (flowtpDict[i].id == data) {
		                            return flowtpDict[i].dictName;
		                          }
		                        }
		            	    return data;
		            	}
		            },{ 
		            	"data": "amntcd",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		 for (var i = 0; i < amntcdDict.length; i++) {
		                          if (amntcdDict[i].id == data) {
		                            return amntcdDict[i].dictName;
		                          }
		                        }
		            	    return data;
		            	}
		            },{ 
		            	"data": "transt",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		 for (var i = 0; i < transtDict.length; i++) {
		                          if (transtDict[i].id == data) {
		                            return transtDict[i].dictName;
		                          }
		                        }
		            	    return data;
		            	}
		            },{ 
		            	"data": "prcsid",
		            	"sortable": false,
		            	"searchable": false
		            }
	            ]
	        }
	    });
		$("#tranModal").modal('show');
	}
	

	return {
		init : function() {
			hander();
			handleTable();
			click();
			submitInfo();
		},
		queryDetl : function() {
			submitInfo();
		},
		qryjyInfos : function(custac) {
			qryjyInfo(custac);
		}
	}

}();