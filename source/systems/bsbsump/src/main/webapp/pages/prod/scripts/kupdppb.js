var kupdppb = function() {
	
	var handleTable = function() {
		var grid = new Datatable();
		var url = Sunline.ajaxPath("prod/selDppb");
		var editUrl;
		var sendData = [ "prodcd" ];

		grid
				.init({
					src : $("#datatable_ajax"),
					deleteData : sendData,
					onSuccess : function(grid) {
					},
					onError : function(grid) {
					},
					dataTable : {
						"ajax" : {
							"url" : url,
						},
						"columns" : [
								{
									"data" : "prodcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "prodtx",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "pdmktx",
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
										for (var i = 0; i < kupdppbdict.prodstDict.length; i++) {
											if (kupdppbdict.prodstDict[i].id == data) {
												return kupdppbdict.prodstDict[i].text;
											}
										}
										return data;
									}
								},
								
								{
									"data" : "presal",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "pddpfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.pddpfgDict.length; i++) {
											if (kupdppbdict.pddpfgDict[i].id == data) {
												return kupdppbdict.pddpfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "prodtp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.prodtpDict.length; i++) {
											if (kupdppbdict.prodtpDict[i].id == data) {
												return kupdppbdict.prodtpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "pdcrcy",
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
									"data" : "brchfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.brchfgDict.length; i++) {
											if (kupdppbdict.brchfgDict[i].id == data) {
												return kupdppbdict.brchfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "custfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.custfgDict.length; i++) {
											if (kupdppbdict.custfgDict[i].id == data) {
												return kupdppbdict.custfgDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "debttp",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.debttpDict.length; i++) {
											if (kupdppbdict.debttpDict[i].id == data) {
												return kupdppbdict.debttpDict[i].text;
											}
										}
										return data;
									}
								},
								{
									"data" : "mginfg",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										for (var i = 0; i < kupdppbdict.mginfgDict.length; i++) {
											if (kupdppbdict.mginfgDict[i].id == data) {
												return kupdppbdict.mginfgDict[i].text;
											}
										}
										return data;
									}
								},
								
								{
									"data" : null,
									"width" : "18%",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='edit' href='javascript:;' data-src='"
												+ data.prodcd + "'>编辑 </a>";
									}
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='delete' href='javascript:;' data-src='"
												+ data.prodcd + "'>删除 </a>";
									}
								} ]
					}
				});

		var toEditForm = function(nRowA) {
			var data = {
				q_prodcd : $(nRowA[0]).text()
			};
			Sunline.ajaxRouter("prod/sesDpp", eval(data), "post",
					function(ret) {
						loadEdit(ret.infos[0]);
					}, function(ret) {
						bootbox.alert(ret);
					});
			$("#prodcd").attr("readOnly", true);
			clearEditForm();
			$("#bianji").modal("show");
			$("#bianji").on("hide.bs.modal", function() {
				kupdppbedit.clear();
				grid.submitFilter();
			});
		};

		grid.bindTableDelete(sendData);
		grid.bindTableEdit(sendData, toEditForm);
		var editform = $('#kupdppb');
		var editerror = $('.alert-danger', editform);
		var editsuccess = $('.alert-success', editform);
		// 新增窗口
		$("#add_btn_prod").bind("click", function() {
				$('input', editform).attr("readOnly", false);
				$("#editModal").modal('show');
				$("#editModal").on("hide.bs.modal", function(e) {
					if(e.date!=undefined){//modal绑定隐藏事件会影响datapicker隐藏事件，此处判断如是datapicker隐藏事件就return
						return;
					}
					clearEditForm();
					grid.submitFilter();
				});
		});

		var clearEditForm = function() {
			editerror.hide();
			editsuccess.hide();
			$('input', editform).val("").trigger("change");
				kupdppbadd.clearForm();
			$('.msg', editform).text("");
			$('.form-group').removeClass('has-error')
					.removeClass("has-success");
		}

		var loadEdit = function(data) {
			kupdppbedit.setdata(data);
			kupdppbedit.loadPage($('.inbox-nav > li.detail > a'), 'detail');
		}
		
	};

	return {
		init : function() {
			handleTable();
		}
	}
}();