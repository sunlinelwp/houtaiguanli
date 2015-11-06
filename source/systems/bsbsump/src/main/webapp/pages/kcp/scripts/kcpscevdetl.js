var kcpscevdetl = function() {

	var moduleDict = Sunline.getDict("E_MODULE");//维度类型
	
	$("input[name='module']").select2({data:moduleDict,allowClear:true});

	var handleTable = function() {
		var detlgrid = new Datatable();
		var detlurl = Sunline.ajaxPath("kcp/qrdetl");
		var detlsendData = [ "scencd","module","evetcd","dimecg","evetus"];

		detlgrid.init({
					src : $("#datatable_detl"),
					deleteData : detlsendData,
					onSuccess : function(detlgrid) {
					},
					onError : function(detlgrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : detlurl,
						},
						"columns" : [
								{
									"data" : "scencd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "scends",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "module",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < moduleDict.length; i++) {
											if (moduleDict[i].id == data) {
												return moduleDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "evetcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "dimecg",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "dimevl",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "evetus",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "remark",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='edit' href='javascript:;' data-src='"
												+ data.scencd
												+","
												+ data.module
												+","
												+ data.evetcd
												+","
												+ data.dimecg
												+","
												+ data.evetus
											    + "'>编辑 </a>";
									}
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='delete' href='javascript:;' data-src='"
												+ data.scencd
												+","
												+ data.module
												+","
												+ data.evetcd
												+","
												+ data.dimecg
												+","
												+ data.evetus
											    + "'>删除 </a>";
									}
								} ]
					}
				});
		detlgrid.bindTableDelete(detlsendData);
		
		detlgrid.bindTableEdit(detlsendData,function(nRowA){
//			$("input[name='prodcd']").attr("readOnly",true);
			
			$("input[name='scencd']").val($(nRowA[0]).text()); 
			$("input[name='scends']").val($(nRowA[1]).text()); 
			$("input[name='module']").val($(nRowA[2]).text().substring($(nRowA[2]).text().indexOf("[")+1,$(nRowA[2]).text().indexOf("]"))).trigger("change");
			$("input[name='evetcd']").val($(nRowA[3]).text()); 
			$("input[name='dimecg']").val($(nRowA[4]).text()); 
			$("input[name='dimevl']").val($(nRowA[5]).text()); 
			$("input[name='evetus']").val($(nRowA[6]).text()); 
			$("input[name='remark']").val($(nRowA[7]).text()); 
			
			$("#editdetlModal").modal('show');
			$("#editdetlModal").on("hide.bs.modal", function() {
				$(".alert-success",$('form', $("#editdetlModal"))).hide();
	        	$('.alert-danger', $('form', $("#editdetlModal"))).hide(); 
	        	$(".msg",$('form', $("#editdetlModal"))).text("");
				detlgrid.submitFilter();
			});
		})
		
		// 新增窗口
		$("#add_detl_btn").bind("click", function() {
//			$("input[name='crcycd']").attr("readOnly",false);
//			$("input[name='prodcd']", $("#editdetlModal")).val(prodcd);
			$("input", $("#editruleModal")).val("").trigger("change");
			$("#editdetlModal").modal('show');
			$("#editdetlModal").on("hide.bs.modal", function() {
				$(".alert-success",$('form', $("#editdetlModal"))).hide();
	        	$('.alert-danger', $('form', $("#editdetlModal"))).hide(); 
	        	$(".msg",$('form', $("#editdetlModal"))).text("");
				detlgrid.submitFilter();
			});
			return false;
		});
		
		$("#btn_save_detl").click(function(){
			$('form', $("#editdetlModal")).submit();
		});
		
		var detlValid = Sunline.getValidate($('form', $("#editdetlModal")),
				function() {
					var data = {};
					$.each($("input", $("#editdetlModal")), function(i, n) {
						if (n.name != undefined&&n.name!=""&&n.name!=null) {
							data[n.name] = n.value;
						}
					});
					
					Sunline.ajaxRouter("kcp/eddetl", 
						data
					, 'post', function(ret) {
                          if(ret.ret==="success"){              
                        	   $(".alert-success",$('form', $("#editdetlModal"))).show();
                        	   $('.alert-danger', $('form', $("#editdetlModal"))).hide(); 		   
                          }else{                        	 
		                   	   $(".alert-success",$('form', $("#editdetlModal"))).hide();
		                   	   $('.alert-danger', $('form', $("#editdetlModal"))).show();                        	  
                          }
                          $(".msg",$('form', $("#editdetlModal"))).text(ret.msg);
					});

				}
//				{
//					 depttm:{required : true},
//					 detlcd:{maxlength : 20}	
//				}
				);

		
	};

	return {
		init : function() {
			handleTable();
		}
	}
}();