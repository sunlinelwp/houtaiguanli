var custBill = function() {
	var acctstDict = Sunline.getDict("acctst");
	var eccttpDict = Sunline.getDict("eccttp");
	var amntcdDict = Sunline.getDict("amntcd");
	var flowtpDict = Sunline.getDict("flowtp");
	var transtDict = Sunline.getDict("transt");
	var tran_grid = new Datatable();
	var o_ecctno;
	var o_addr;
	var o_email;
	var o_acctst;
	var isNotF = true;
	var bankIsNotF = true;
	var testaccount = "";
	var formartDict = function(dict, value) {
		for (var i = 0; i < dict.length; i++) {
			if (value == dict[i].dictId) {
				return dict[i].dictName;
			}
			if (value == dict[i].dictName) {
				return dict[i].dictId;
			}
		}
		return value;
	};
	var formartM = function(value) {
		value = value.toString();
		if (value.toString().indexOf('.') == -1) {
			return value + ".00";
		} else if (value.split('.')[1].length == 1) {
			return value + '0';
		} else {
			return value;
		}
	};
	var formartTime = function(time) {
		if (time.length == 8) {
			return time.substr(0, 1) + ":" + time.substr(1, 2) + ":"
					+ time.substr(3, 2);
		}
		return time.substr(0, 2) + ":" + time.substr(2, 2) + ":"
				+ time.substr(4, 2);
	};

	// 格式化时间为yyyy-mm-dd hh:mm:ss
	var formartTimes = function(time) {
		return time.substr(0, 4) + "" + time.substr(4, 2) + ""
				+ time.substr(6, 2) + " ";// +time.substr(8,2)+":"+time.substr(10,2)+":"+time.substr(12,2);

	}
	var handlerTable = function() {
//		var ecctno = $(rows[0].children()[1]).text();
//		var ecctno = "6000050002";
//		$('#tran_custac').val(ecctno);
		var url1 = Sunline.ajaxPath("cust/cutrif");
		console.info(isNotF);
		if (isNotF) {
			tran_grid.setAjaxParam("ecctno", $('#tran_custac').val());
			tran_grid.setAjaxParam("from", "");
			tran_grid.setAjaxParam("to", "");
			tran_grid.setAjaxParam("eccttp", "2");
			tran_grid.setAjaxParam("crcycd", "01");
			tran_grid.init({
				src : $("#cif_tran_ajax"),
				deleteData : sendData,
				onSuccess : function(grid) {
					$('.cif_tran_ajax_wrapper .alert-danger').css("display",
							"none");
				},
				onError : function(grid) {
				},
				dataTable : { 
					"ajax" : {
						"url" : url1, // ajax source
					},
					"columns" : [ {
						"data" : "trandt",
						"sortable" : false,
						"searchable" : false
					}, {
						"data" : "trantm",
						"sortable" : false,
						"searchable" : false,
						"render" : function(data, type, full) {
							return formartTime(data);
						}
					}, {
						"data" : "tranam",
						"sortable" : false,
						"searchable" : false,
						"render" : function(data, type, full) {
							return formartM(data);
						}
					}, {
						"data" : "chnlno",
						"sortable" : false,
						"searchable" : false
					}, {
						"data" : "avalbl",
						"sortable" : false,
						"searchable" : false,
						"render" : function(data, type, full) {
							return formartM(data);
						}
					}, {
						"data" : "smryds",
						"sortable" : false,
						"searchable" : false
					}, {
						"data" : "jnlseq",
						"sortable" : false,
						"searchable" : false
					}, {
						"data" : "flowtp",
						"sortable" : false,
						"searchable" : false,
						"render" : function(data, type, full) {
							for (var i = 0; i < flowtpDict.length; i++) {
								if (flowtpDict[i].id == data) {
									return flowtpDict[i].dictName;
								}
							}
							return data;
						}
					}, {
						"data" : "amntcd",
						"sortable" : false,
						"searchable" : false,
						"render" : function(data, type, full) {
							for (var i = 0; i < amntcdDict.length; i++) {
								if (amntcdDict[i].id == data) {
									return amntcdDict[i].dictName;
								}
							}
							return data;
						}
					}, {
						"data" : "transt",
						"sortable" : false,
						"searchable" : false,
						"render" : function(data, type, full) {
							for (var i = 0; i < transtDict.length; i++) {
								if (transtDict[i].id == data) {
									return transtDict[i].dictName;
								}
							}
							return data;
						}
					}, {
						"data" : "prcsid",
						"sortable" : false,
						"searchable" : false
					} ]
				}
			});
			var sendData = [ "transq" ];
			isNotF = false;
		} else {
			console.info(tran_grid.gettableContainer());
			console.info(tran_grid.getDataTable());
			console.info(tran_grid.getTable());
			tran_grid.setAjaxParam("ecctno", $('#tran_custac').val());
			tran_grid.setAjaxParam("from", $('#trandt_from').val());
			tran_grid.setAjaxParam("to", $('#trandt_to').val());
			tran_grid.setAjaxParam("eccttp", $('#eccttp').select2("val"));
			tran_grid.setAjaxParam("crcycd", "01");
			tran_grid.submitFilter();
		}
	};
	var handlerOperator = function() {
		$("#eccttp").select2({
			data : eccttpDict,
			formatSelection: function(item){
				return item.dictName;
			},
		    formatResult: function(item){
				return item.dictName;
			},
			width:"80% !important"
		});
		// 交易查询
		$('#tran_qry').click(function() {
			if (!$('#cust-tran-form').validate().form()) {
				return;
			}
			tran_grid.setAjaxParam("ecctno", $('#tran_custac').val());
			tran_grid.setAjaxParam("from", $('#trandt_from').val());
			tran_grid.setAjaxParam("to", $('#trandt_to').val());
			tran_grid.setAjaxParam("eccttp", $('#eccttp').select2("val"));
			tran_grid.setAjaxParam("crcycd", "01");
			tran_grid.submitFilter();
		});
		$('#tran_cancle').click(function(){
			$('#tran_custac').val("");
			$('#trandt_from').val("");
			$('#trandt_to').val("");
			$('#eccttp').select2("val","");
		});
	};
	return {
		init : function() {
			handlerTable();
			handlerOperator();
		},
		queryInfo : function(){
			if (!$('#cust-tran-form').validate().form()) {
				return;
			}
			tran_grid.setAjaxParam("ecctno", $('#tran_custac').val());
			tran_grid.setAjaxParam("from", $('#trandt_from').val());
			tran_grid.setAjaxParam("to", $('#trandt_to').val());
			tran_grid.setAjaxParam("eccttp", $('#eccttp').select2("val"));
			tran_grid.setAjaxParam("crcycd", "01");
			tran_grid.submitFilter();
		}
	}
}()