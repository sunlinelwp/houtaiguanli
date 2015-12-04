var fcsetprod = function() {    
	
	var prodstDict =Sunline.getDict("F_PRODST");//产品状态
	var prodtpDict =Sunline.getDict("F_PRODTP");//产品类型
	var onlyfgDict =Sunline.getDict("F_ONLYFG");//唯一规则
	
	//增加
	var crcycdDict = Sunline.getDict("E_CRCYCD");//币种
	var prodmdDict = Sunline.getDict("E_PRODMD");//产品模式
	var lesrulDict = Sunline.getDict("E_LESRUL");//不足额规则
	var addrulDict = Sunline.getDict("E_ADDRUL");//追加起投规则
	var learulDict = Sunline.getDict("E_LEARUL");//不足额追加规则
	//结束
	var handleTable = function() {
		var prodgrid = new Datatable();
		var produrl = Sunline.ajaxPath("fc/fcqpro");
		var prodsendData = [ "prodcd" ];//主键
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
									"data" : "prodtp",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "prodde",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "prodmd",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < prodmdDict.length; i++) {
											if (prodmdDict[i].id == data) {
												return prodmdDict[i].text;
											}
										}
										return data;
									}
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
									"data" : "fcsrcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "fcsrna",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "onlyfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < onlyfgDict.length; i++) {
											if (onlyfgDict[i].id == data) {
												return onlyfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "rvbson",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "pybson",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "crcycd",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < lesrulDict.length; i++) {
											if (lesrulDict[i].id == data) {
												return lesrulDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "lesrul",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < lesrulDict.length; i++) {
											if (lesrulDict[i].id == data) {
												return lesrulDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "addrul",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < addrulDict.length; i++) {
											if (addrulDict[i].id == data) {
												return addrulDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "addamt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "learul",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < learulDict.length; i++) {
											if (learulDict[i].id == data) {
												return learulDict[i].text;
											}
										}
										return data;
									}
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
								}								
								]
					}
				});
		prodgrid.bindTableDelete(prodsendData);
		prodgrid.bindTableEdit(prodsendData,function(nRowA){
			fcsubpage.setdata(nRowA);
			var data = {
					q_prodcd : $(nRowA[0]).text()
				};
		  Sunline.ajaxRouter("fc/fcqrpr", eval(data), "post",
						function(ret) {
				           //console.info(ret);
			             $("input[name='prodcd']", $("#prod_form")).attr("readOnly",true);
							fcsubpage.setdata(ret.proInfos[0]);
							fcsubpage.setmode("edit");
							fcsubpage.loadPage($('.inbox-nav > li.lnfprodedit > a'));
						}, function(ret) {
                             bootbox.alert(ret.retMsg);
						});
		        
				$("#bianji").modal("show");
				$("#bianji").on("hide.bs.modal",function(e){
					if(e.date!=undefined){
						return ;
					}
					clearEditForm();
					prodgrid.submitFilter();
				});        
		});
		
		// 新增窗口
		$("#add_prod_btn").bind("click", function() {
			fcsubpage.setmode("add");
			$("li #editmodal").remove("active");
			$(".lnfprodedit",$("#editmodal")).addClass("active");	
			fcsubpage.loadPage($('.inbox-nav > li.lnfprodedit > a'));
			$("#bianji").modal('show');
			$("#bianji").on("hide.bs.modal", function(e) {
				if(e.date!=undefined){
					return ;
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
			fcsubpage.clear();
		}
		
	       };

	return {
		init : function() {
			handleTable();
		}
	}
}();