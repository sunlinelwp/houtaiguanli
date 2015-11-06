var kupdppbpost = function() {

	
	$("#editpostModal input[name='crcycd']").select2({
		data : kupdppbdict.pdcrcyDict,
		allowClear : true
	});
	$("#editpostModal input[name='posttp']").select2({
		data : kupdppbdict.posttpDict,
		allowClear : true
	});
	$("#editpostModal input[name='postwy']").select2({
		data : kupdppbdict.postwyDict,
		allowClear : true
	});
	$("#editpostModal input[name='amntwy']").select2({
		data : kupdppbdict.amntwyDict,
		allowClear : true
	});
	$("#editpostModal input[name='timewy']").select2({
		data : kupdppbdict.timewyDict,
		allowClear : true
	});
	$("#editpostModal input[name='detlfg']").select2({
		data : kupdppbdict.acolfgDict,
		allowClear : true
	});
	var handleTable = function(prodcd) {
		var postgrid = new Datatable();
		var posturl = Sunline.ajaxPath("prod/dposel");
		var postsendData = [ "prodcd", "crcycd"];
		if (!Sunline.isNull(prodcd)) {
			postgrid.setAjaxParam("q_prodcd", prodcd);
		}
		postgrid.init({
					src : $("#datatable_post"),
					deleteData : postsendData,
					onSuccess : function(postgrid) {
					},
					onError : function(postgrid) {
					},
					dataTable : {
						"ajax" : {
							"url" : posturl,
						},
						"columns" : [
								{
									"data" : "prodcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "crcycd",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.pdcrcyDict.length; i++) {
											if (kupdppbdict.pdcrcyDict[i].id == data) {
												return kupdppbdict.pdcrcyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "posttp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.posttpDict.length; i++) {
											if (kupdppbdict.posttpDict[i].id == data) {
												return kupdppbdict.posttpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "postwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.postwyDict.length; i++) {
											if (kupdppbdict.postwyDict[i].id == data) {
												return kupdppbdict.postwyDict[i].text;
											}
										}
										return data;
									}
								},{
									"data" : "amntwy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.amntwyDict.length; i++) {
											if (kupdppbdict.amntwyDict[i].id == data) {
												return kupdppbdict.amntwyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "miniam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "maxiam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "timewy",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.timewyDict.length; i++) {
											if (kupdppbdict.timewyDict[i].id == data) {
												return kupdppbdict.timewyDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "minitm",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "maxitm",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "detlfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.acolfgDict.length; i++) {
											if (kupdppbdict.acolfgDict[i].id == data) {
												return kupdppbdict.acolfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "svrule",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='delete' href='javascript:;' data-src='"
												+ data.prodcd
												+ ","
												+ data.crcycd
											    + "'>删除 </a>";
									}
								} ]
					}
				});
		postgrid.bindTableDelete(postsendData);
		// 新增窗口
		$("#add_post_btn").bind("click", function() {
			$("input[name='prodcd']", $("#editpostModal")).val(prodcd);
			$("#editpostModal").modal('show');
			$("#editpostModal").on("hide.bs.modal", function() {
				$(".alert-success",$('form', $("#editpostModal"))).hide();
	        	$('.alert-danger', $('form', $("#editpostModal"))).hide(); 
	        	$(".msg",$('form', $("#editpostModal"))).text("");
				postgrid.submitFilter();
			});
			return false;
		});
		
		$("#btn_save_post").click(function(){
			$('form', $("#editpostModal")).submit();
		});
		
		var postValid = Sunline.getValidate($('form', $("#editpostModal")),
				function() {
					var data = {};
					$.each($("input", $("#editpostModal")), function(i, n) {
						if (n.name != undefined&&n.name!=""&&n.name!=null) {
							data[n.name] = n.value;
						}
					});
					var datas=[];
					datas.push(data);					
					var dpposts = {"dppost":datas};
					Sunline.ajaxRouter("prod/dpoins", 
						dpposts
					, 'post', function(ret) {
                          if(ret.ret==="success"){              
                        	   $(".alert-success",$('form', $("#editpostModal"))).show();
                        	   $('.alert-danger', $('form', $("#editpostModal"))).hide(); 		   
                          }else{                        	 
		                   	   $(".alert-success",$('form', $("#editpostModal"))).hide();
		                   	   $('.alert-danger', $('form', $("#editpostModal"))).show();                        	  
                          }
                          $(".msg",$('form', $("#editpostModal"))).text(ret.msg);
					});

				}, {
					crcycd:{required : true},
					miniam:{maxlength : 21 , number : true , money_gteq0 : true},
					maxiam:{maxlength : 21 , number : true , money_gteq0 : true},
					minitm:{maxlength : 19 , number : true},
					maxitm:{maxlength : 19 , number : true},
					posttp:{required : true},
					postwy:{required : true},
					amntwy:{required : true},
					timewy:{required : true}
					
				});

		
	};

	return {
		init : function(prodcd) {
			handleTable(prodcd);
		}
	}
}();