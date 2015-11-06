var kcpchrgevnt = function() {

	var trnchlDict = Sunline.getDict("E_SERVTP");//交易渠道
	var custtpDict = Sunline.getDict("E_CUSTTP");//客户类型
	
	$("input[name='trnchl']").select2({data:trnchlDict,allowClear:true});
	$("input[name='custtp']").select2({data:custtpDict,allowClear:true});

	var handleTable = function() {
		var evntgrid = new Datatable();
		var evnturl = Sunline.ajaxPath("kcp/qrevnt");
		var evntsendData = [ "chevno","chrgcd"];

		evntgrid.init({
					src : $("#datatable_evnt"),
					deleteData : evntsendData,
					onSuccess : function(evntgrid) {
					},
					onError : function(evntgrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : evnturl,
						},
						"columns" : [
								{
									"data" : "chevno",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "prodcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "sequno",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "trnchl",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < trnchlDict.length; i++) {
											if (trnchlDict[i].id == data) {
												return trnchlDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "dcmttp",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "custtp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < custtpDict.length; i++) {
											if (custtpDict[i].id == data) {
												return custtpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "prilvl",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "chrgcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "expmsg",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='edit' href='javascript:;' data-src='"
												+ data.chevn
												+","
												+data.chrgcd
											    + "'>编辑 </a>";
									}
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='delete' href='javascript:;' data-src='"
												+ data.chevno
												+","
												+data.chrgcd
											    + "'>删除 </a>";
									}
								} ]
					}
				});
		evntgrid.bindTableDelete(evntsendData);
		
		evntgrid.bindTableEdit(evntsendData,function(nRowA){
//			$("input[name='prodcd']").attr("readOnly",true);
			
			
			$("input[name='chevno']").val($(nRowA[0]).text()); 
			$("input[name='prodcd']").val($(nRowA[1]).text()); 
			$("input[name='sequno']").val($(nRowA[2]).text()); 
			$("input[name='trnchl']").val($(nRowA[3]).text().substring($(nRowA[3]).text().indexOf("[")+1,$(nRowA[3]).text().indexOf("]"))).trigger("change");
			$("input[name='dcmttp']").val($(nRowA[4]).text()); 
			$("input[name='custtp']").val($(nRowA[5]).text().substring($(nRowA[5]).text().indexOf("[")+1,$(nRowA[5]).text().indexOf("]"))).trigger("change");
			$("input[name='prilvl']").val($(nRowA[6]).text()); 
			$("input[name='chrgcd']").val($(nRowA[7]).text()); 
			$("input[name='expmsg']").val($(nRowA[8]).text()); 
			
			
			$("#editevntModal").modal('show');
			$("#editevntModal").on("hide.bs.modal", function() {
				$(".alert-success",$('form', $("#editevntModal"))).hide();
	        	$('.alert-danger', $('form', $("#editevntModal"))).hide(); 
	        	$(".msg",$('form', $("#editevntModal"))).text("");
				evntgrid.submitFilter();
			});
		})
		
		// 新增窗口
		$("#add_evnt_btn").bind("click", function() {
//			$("input[name='crcycd']").attr("readOnly",false);
//			$("input[name='prodcd']", $("#editevntModal")).val(prodcd);
			$("input", $("#editruleModal")).val("").trigger("change");
			$("#editevntModal").modal('show');
			$("#editevntModal").on("hide.bs.modal", function() {
				$(".alert-success",$('form', $("#editevntModal"))).hide();
	        	$('.alert-danger', $('form', $("#editevntModal"))).hide(); 
	        	$(".msg",$('form', $("#editevntModal"))).text("");
				evntgrid.submitFilter();
			});
			return false;
		});
		
		$("#btn_save_evnt").click(function(){
			$('form', $("#editevntModal")).submit();
		});
		
		var evntValid = Sunline.getValidate($('form', $("#editevntModal")),
				function() {
					var data = {};
					$.each($("input", $("#editevntModal")), function(i, n) {
						if (n.name != undefined&&n.name!=""&&n.name!=null) {
							data[n.name] = n.value;
						}
					});
					
					Sunline.ajaxRouter("kcp/edevnt", 
						data
					, 'post', function(ret) {
                          if(ret.ret==="success"){              
                        	   $(".alert-success",$('form', $("#editevntModal"))).show();
                        	   $('.alert-danger', $('form', $("#editevntModal"))).hide(); 		   
                          }else{                        	 
		                   	   $(".alert-success",$('form', $("#editevntModal"))).hide();
		                   	   $('.alert-danger', $('form', $("#editevntModal"))).show();                        	  
                          }
                          $(".msg",$('form', $("#editevntModal"))).text(ret.msg);
					});

				}
//				{
//					 depttm:{required : true},
//					 defncd:{maxlength : 20}	
//				}
				);

		
	};

	return {
		init : function() {
			handleTable();
		}
	}
}();