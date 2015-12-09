var lnfprod = function() {    
	
	var prodstDict =Sunline.getDict("E_PRODST");
	var coorprDict =Sunline.getDict("E_COORPR");
	var lncutpDict =Sunline.getDict("E_LNCUTP");
	var lnbztpDict =Sunline.getDict("E_LNBZTP");
	var isotbsDict =Sunline.getDict("E_ISOTBS");
	var iscyclDict =Sunline.getDict("E_ISCYCL");
	var istxlnDict =Sunline.getDict("E_ISTXLN");
	var issyndDict =Sunline.getDict("E_ISSYND");
	var syndioDict =Sunline.getDict("E_SYNDIO");
	var syndtpDict =Sunline.getDict("E_SYNDTP");
	var synditDict =Sunline.getDict("E_SYNDIT");
	var syndotDict =Sunline.getDict("E_SYNDOT");
	var borstpDict =Sunline.getDict("E_BORSTP");
	var ispionDict =Sunline.getDict("E_ISPION");
	var caldtpDict =Sunline.getDict("E_CALDTP");
	var handleTable = function() {
		var prodgrid = new Datatable();
		var produrl = Sunline.ajaxPath("loan/qrprod");
		var prodsendData = [ "prodcd" ];
		prodgrid.init({
					src : $("#datatable_prod"),
					deleteData : prodsendData,
					onSuccess : function(prodgrid) {
					},
					onError : function(prodgrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : produrl,
						},
						"columns" : [
								 {
									"data" : "prodcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "prodna",
									"sortable" : false,
									"searchable" : false
									
								},
								{
									"data" : "prodtx",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "efctdt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "inefdt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "prodst",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < prodstDict.length; i++) {
											if (prodstDict[i].id == data) {
												return prodstDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "coorpr",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < coorprDict.length; i++) {
											if (coorprDict[i].id == data) {
												return coorprDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "lncutp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < lncutpDict.length; i++) {
											if (lncutpDict[i].id == data) {
												return lncutpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "lnbztp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < lnbztpDict.length; i++) {
											if (lnbztpDict[i].id == data) {
												return lnbztpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "isotbs",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < isotbsDict.length; i++) {
											if (isotbsDict[i].id == data) {
												return isotbsDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "iscycl",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < iscyclDict.length; i++) {
											if (iscyclDict[i].id == data) {
												return iscyclDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "istxln",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < istxlnDict.length; i++) {
											if (istxlnDict[i].id == data) {
												return istxlnDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "issynd",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < issyndDict.length; i++) {
											if (issyndDict[i].id == data) {
												return issyndDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "syndio",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < syndioDict.length; i++) {
											if (syndioDict[i].id == data) {
												return syndioDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "syndtp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < syndtpDict.length; i++) {
											if (syndtpDict[i].id == data) {
												return syndtpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "syndit",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < synditDict.length; i++) {
											if (synditDict[i].id == data) {
												return synditDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "syndot",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < syndotDict.length; i++) {
											if (syndotDict[i].id == data) {
												return syndotDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "borstp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < borstpDict.length; i++) {
											if (borstpDict[i].id == data) {
												return borstpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "ispion",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < ispionDict.length; i++) {
											if (ispionDict[i].id == data) {
												return ispionDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "caldtp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < caldtpDict.length; i++) {
											if (caldtpDict[i].id == data) {
												return caldtpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "creadt",
									"sortable" : false,
									"searchable" : false,
								},
								{
									"data" : "creaus",
									"sortable" : false,
									"searchable" : false,
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='edit' href='javascript:;' data-src='"
												+ data.prodcd
												+ ","
												+ data.depttm
											    + "'>编辑 </a>";
									}
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='delete' href='javascript:;' data-src='"
												+ data.prodcd
												+ ","
												+ data.depttm
											    + "'>删除 </a>";
									}
								} ]
					}
				});
		prodgrid.bindTableDelete(prodsendData);
		prodgrid.bindTableEdit(prodsendData,function(nRowA){
			lnsubpage.setdata(nRowA);
			var data = {
					q_prodcd : $(nRowA[0]).text()
				};
		  Sunline.ajaxRouter("loan/qrprsl", eval(data), "post",
						function(ret) {
				           //console.info(ret);
			             $("input[name='prodcd']", $("#prod_form")).attr("readOnly",true);
							lnsubpage.setdata(ret.pinfos[0]);
							lnsubpage.setmode("edit");
							lnsubpage.loadPage($('.inbox-nav > li.lnfprodedit > a'));
						}, function(ret) {
                             bootbox.alert(ret.retMsg);
						});
		        
				$("#bianji").modal("show");
				$("#bianji").on("hide.bs.modal",function(e){
					if(e.date!=undefined){//modal绑定隐藏事件会影响datapicker隐藏事件，此处判断如是datapicker隐藏事件就return
						return;
					}
					clearEditForm();
					prodgrid.submitFilter();
				});        
		});
		
		// 新增窗口
		$("#add_prod_btn").bind("click", function() {
			lnsubpage.setmode("add");
			$("li #editmodal").remove("active");
			$(".lnfprodedit",$("#editmodal")).addClass("active");	
			lnsubpage.loadPage($('.inbox-nav > li.lnfprodedit > a'));
			$("#bianji").modal('show');
			$("#bianji").on("hide.bs.modal", function(e) {
				if(e.date!=undefined){//modal绑定隐藏事件会影响datapicker隐藏事件，此处判断如是datapicker隐藏事件就return
					return;
				}
				$(".alert-success", $("#prod_form")).hide();
	        	$('.alert-danger',$("#prod_form")).hide(); 
	        	$(".msg", $("#prod_form")).text("");
				prodgrid.submitFilter();
			});
			return false;
		});
		
		//重置编辑窗口
		var clearEditForm = function() {
			$(".alert-success", $("#prod_form")).hide();
			$('.alert-danger',  $("#prod_form")).hide();
			$('input', $("#prod_form")).val("").trigger("change");
			$('.msg', $("#prod_form")).text("");
			$('.form-group').removeClass('has-error').removeClass("has-success");
			lnsubpage.clear();
		}
		
	       };

	return {
		init : function() {
			handleTable();
		}
	}
}();