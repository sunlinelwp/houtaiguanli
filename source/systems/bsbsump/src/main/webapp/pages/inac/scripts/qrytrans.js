var qrytrans = function() {
	var amntcdDict=Sunline.getDict("amntcd");
	var crcycdDict = Sunline.getDict("crcycd");
	var formartM = function(value){
		
		if(value.indexOf('.') == -1){
			return value+".00";
		}else if(value.split('.')[1].length == 1) {
			return value+'0';
		} else {
			return value;
		}
	};
	var handleTable = function(data) {
		var grid = new Datatable();
		var url = Sunline.ajaxPath("inac/qryTrans");
		var editUrl;
		var table = $("#trans_table");
		if (!Sunline.isNull(data.acctno)) {
			grid.setAjaxParam('acctno', data.acctno);
			$('#acctno').val(data.acctno);
		}
		/*
		 * 初始化table
		 */
		grid.init({
					src : table,
					deleteData : sendData,
					onSuccess : function(grid) {
					},
					onError : function(grid) {
					},
					dataTable : { // here you can define a typical
						"ajax" : {
							"url" : url, // ajax source
						},
						"bDestroy" : true,
						"bServerSide" : true,
						"columns" : [
										{
											"data" : "trandt",
											"sortable" : false,
											"searchable" : false
										},{
											"data" : "amntcd",
											"sortable" : false,
											"searchable" : false,
											"render" : function(data,type,full){
							            		 for (var i = 0; i < amntcdDict.length; i++) {
							                          if (amntcdDict[i].id == data) {
							                            return amntcdDict[i].dictName;
							                          }
							                        }
							            	    return data;
							            	}
										},{
											"data" : "itemcd",
											"sortable" : false,
											"searchable" : false
										},{
											"data" : "crcycd",
											"sortable" : false,
											"searchable" : false,
											"render" : function(data, type, full) {
												for (var i = 0; i < crcycdDict.length; i++) {
													if (crcycdDict[i].id == data) {
														return crcycdDict[i].text;
													}
												}
												return data;
											}
										},{
											"data" : "tranam",
											"sortable" : false,
											"searchable" : false,
											"render" : function(data,type,full){
							            		return formartM(data+"");
							            	}
										},{
											"data" : "blncdn",
											"sortable" : false,
											"searchable" : false,
							            	"render": function (data, type, full) {
							            		if(data=='C'){
							            			return '贷方';
							            		} else if(data=='D') {
							            			return '借方';
							            		} else if(data == 'R'){
							            			return '收方';
							            		} else if(data == 'Z') {
							            			return '轧差';
							            		} else {
							            			return data;
							            		}
							            	}
										},{
											"data" : "tranbl",
											"sortable" : false,
											"searchable" : false,
											"render" : function(data,type,full){
							            		return formartM(data+"");
							            	}
										},{
											"data" : "tranus",
											"sortable" : false,
											"searchable" : false
										},{
											"data" : "ckbsus",
											"sortable" : false,
											"searchable" : false
										},{
											"data" : "ioflag",
											"sortable" : false,
											"searchable" : false,
											"render" : function(data,type,full){
												if(data=='I') return '表内';
												else if(data=='O') return '表外';
												else return data;
							            	}
										},{
											"data" : "toacct",
											"sortable" : false,
											"searchable" : false
										},{
											"data" : "tosbac",
											"sortable" : false,
											"searchable" : false
										},{
											"data" : "toacna",
											"sortable" : false,
											"searchable" : false
										},{
											"data" : "smrytx",
											"sortable" : false,
											"searchable" : false
										}]
					}
		});
		jQuery.validator.addMethod("istruedt", function(value, element, param) { 
			if(param==true){
				console.info($('#startdt').val() + value);
				return parseInt($('#startdt').val()) > parseInt($('#enddt').val());
			}
			return true;
			
		}, $.validator.format("起始日期不能大于结束日期"));

		var sendData = [ "acctno","startdt","enddt"];
		//查询
		$('#submit').click(function(){
			grid.setAjaxParam("acctno",$('#acctno').val());
			grid.setAjaxParam("startdt",$('#startdt').val());
			grid.setAjaxParam("enddt",$('#enddt').val());
			grid.submitFilter();
		});
	}
	return {
		init : function(data) {
			handleTable(data);
		}
	}
}();