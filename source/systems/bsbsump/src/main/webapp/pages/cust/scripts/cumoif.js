var Cumoif = function(){
	var grid = new Datatable();
	var handlerTable = function(){
		grid.setAjaxParam("ecctno","");
		grid.setAjaxParam("custna","");
		grid.setAjaxParam("idtfno","");
		grid.setAjaxParam("idtftp","");
		grid.setAjaxParam("teleno","");
		var url = Sunline.ajaxPath("cumoif/cumoifs");
		grid.init({
	        src: $("#cif_ajax"),
//	        deleteData: sendData,
	        onSuccess: function (grid) {
	            // execute some code after table records loaded
	        	$('.table-container .alert-danger').css("display","none");
	        },
	        onError: function (grid) {
	            // execute some code on network or other general error
	        	//$('.table-container .alert-danger').css("display","none");
	        	console.info("It is error!");
	        },
	        dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
	            "ajax": {
	                "url": url, // ajax source
	            },
	            "columns" : [{     
		            	"data": "ecctno",
		            	"sortable": false,
		            	"searchable": false
		            },{     
		            	"data": "onlnbl",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "hlzhye",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "orlnbl",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "tranam",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "ortram",
		            	"sortable": false,
		            	"searchable": false
		            }
		            ,{ 
		            	"data": "frohin",
		            	"sortable": false,
		            	"searchable": false
		            }
		            ,{ 
		            	"data": "frocin",
		            	"sortable": false,
		            	"searchable": false
		            }
		            ,{ 
		            	"data": "txycje",
		            	"sortable": false,
		            	"searchable": false
		            }
	            ]
	        }
	    });
	};

	var handlerOperator = function(){
		$('#submit').click(function(){
			if(!$('#cust-form').validate().form()){
				return;
			}
			grid.setAjaxParam("ecctno",$('#custac').val());
			grid.setAjaxParam("custna",$('#custna').val());
			grid.setAjaxParam("idtfno",$('#idcard').val());
			var idtftp = '';
			if($('#idcard').val() != ""){
				idtftp = "10";
			}
			grid.setAjaxParam("idtftp",idtftp);
			grid.setAjaxParam("teleno",$('#phone').val());
			grid.submitFilter();
		});
		//取消
		$('#cancle').click(function(){
			$('#custac').val("");
			$('#custna').val("");
			$('#idcard').val("");
			$('#phone').val("");
		});

	};
	return {
		init : function(){
			handlerTable();
			handlerOperator();
		}
	}
}()