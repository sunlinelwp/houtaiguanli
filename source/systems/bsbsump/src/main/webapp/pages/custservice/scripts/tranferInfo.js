var tranferInfo = function() {
	var crcycdDict=Sunline.getDict("E_CRCYCD");//币种
	var subjstDict=Sunline.getDict("E_SUBJST");//标的状态
	var transtDict=Sunline.getDict("E_SUBJTP");//状态
	var feramoqucuam=0;
	var bankIsNotF=true;
	var custac = "";
	var trangrid = new Datatable();
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
	
	var editForm = function(nRowA){
		 
	};
	
	var prodgrid = new Datatable();
	var handleTable = function(){
		var i = 0;
		prodgrid.setAjaxParam("custac","");
		prodgrid.setAjaxParam("teleno","");
		var produrl = Sunline.ajaxPath("custService/qrdbsb");
		prodgrid.init({
					src : $("#datatable_prod"),
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
									"data": "null",
									"sortable": false,
									"searchable": false,
									"width": "2%",
									"render": function (data, type, full) {
										return '<input type="checkbox" class="checkboxes" value="1"/>';
									}
								},     
								{
									"data" : "subjnm",
									"sortable" : false,
									"searchable" : false
									
								},
								{
									"data" : "quamou",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "remday",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "traamo",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data,type,full){
										feramoqucuam =data;
										return data;  
					            	}
								},
								{
									"data" : "qucuam",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data,type,full){
										feramoqucuam =feramoqucuam-data
										return data;  
					            	}
								},
							    {
									"data" : "null",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data,type,full){
										 
										return feramoqucuam.toFixed(2);  
					            	}
								} 						
								]
					}
				});
		$(".table-group-actions").append("<button id='tran_history_btn' class='btn btn-sm blue table-group-action-submit'><i class='fa icon-cloud-download'></i>&nbsp;查看转让记录</button></div>");
		var sendData = ["custac"];
		prodgrid.bindTableEdit(sendData,editForm);
		//转让记录查询
		$('#tran_history_btn').click(function(){
			
			var rows = prodgrid.getSelectedRows();
			if(rows.length != 1){
				bootbox.alert("请选择一条信息");
				return;
			}
			
			var debtcd = $(rows[0].children()[1]).text();
			var url1 = Sunline.ajaxPath("custService/tranhistory");
			
			console.info(bankIsNotF);
			
			if(bankIsNotF){
				trangrid.setAjaxParam("custac",custac);
				trangrid.setAjaxParam("debtcd",debtcd);
				
				trangrid.init({
			        src: $("#tran_history_ajax"),
			        onSuccess: function (grid) {
//			        	$('.cif_tran_ajax_wrapper .alert-danger').css("display","none");
			        },
			        onError: function (grid) {
			            // execute some code on network or other general error
			        	//$('.cif_tran_ajax_wrapper .alert-danger').css("display","none");
			        	//console.info("It is error!");
			        },
			        dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
			            "ajax": {
			                "url": url1, // ajax source
			            },
			            "columns" : [{
				            	"data": "loantm",
				            	"sortable": false,
				            	"searchable": false
			            	},{
				            	"data": "invtam",
				            	"sortable": false,
				            	"searchable": false
				            },{     
				            	"data": "trandt",
				            	"sortable": false,
				            	"searchable": false
				            }
			            ]
			        }
			    });
				bankIsNotF = false;
			} else {
				console.info(trangrid.gettableContainer());
				console.info(trangrid.getDataTable());
				console.info(trangrid.getTable());
				trangrid.setAjaxParam("custac",custac);
				trangrid.submitFilter();
			}
			$("#tranferhistory").modal('show');
		});
	       }
	
	
  
	//查询
	var submitInfo = function(){
		if(!$('#cust-form').validate().form()){
			return;
		}
		prodgrid.setAjaxParam("q_custac",$('#custac').val());
		custac = $('#custac').val();//每次查询重新赋值用户的电子账号
		prodgrid.setAjaxParam("q_phoneNo",$('#teleno').val());
		prodgrid.submitFilter();
	}
	return {
		init : function() {
			handleTable();
		},
		queryCust : function() {
			submitInfo();
		},
		clickWindow : function(){
			setTimeout(function () { 
				
		    }, 500);
		}
	}
}();