var Cntxyc = function(){
	var grid = new Datatable();
	var handlerTable = function(){
		var url = Sunline.ajaxPath("cntxyc/cntxycs");
		grid.init({
	        src: $("#cty_ajax"),
//	        deleteData: sendData,
	        onSuccess: function (grid) {
	        	$('.table-container .alert-danger').css("display","none");
	        },
	        onError: function (grid) {
	        	console.info("It is error!");
	        },
	        dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
	            "ajax": {
	                "url": url, // ajax source
	            },
	            "columns" : [{     
		            	"data": "custac",
		            	"sortable": false,
		            	"searchable": false
		            },{     
		            	"data": "subjcd",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "tranam",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "clerss",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data, type, full) {
							if(data == "2"){
								return "提现异常(2)";
							}else{
								return "错误数据";
							}
						}
		            },{ 
		            	"data": "trandt",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "coresq",
		            	"sortable": false,
		            	"searchable": false
		            }
		            ,{ 
		            	"data": "corecw",
		            	"sortable": false,
		            	"searchable": false
		            }
		            ,{ 
		            	"data" : "coresq",
						"sortable" : false,
						"searchable" : false,
						"render" : function(data, type, full) {
							return "<a href='javascript:;' onclick='Cntxyc.Clicks("+'"'+data+'"'+")'>处理</a>";
						}
		            }
	            ]
	        }
	    });
	};
	
	
	//处理
	var qryjyInfo = function(coresq){
		var input={};
        input.coresq = coresq;
    	Sunline.ajaxRouter(
    	"cntxyc/upfrsqs", 
    	 input,
    	"POST",
        function(redata){
    		if(redata.retCode=='0000'){
    			bootbox.alert('操作成功');
    		}else{
    			bootbox.alert('失败：'+reddata.retMsg);
    		}
    	},
    	"json",
    	false); 
    	$(document).ready(function () {
            $('#cty_ajax').dataTable().fnDestroy(); 
           });
    	handlerTable();
    }
	
	return {
		init : function(){
			handlerTable();
		},
		Clicks:function(coresq){
			qryjyInfo(coresq);
		}
	}
}()