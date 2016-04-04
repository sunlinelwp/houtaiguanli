var Myfriends = function(){
	var shifouDict=Sunline.getDict("E_ISOTBS");
	var grid = new Datatable();
	var handlerTable = function(){
		grid.setAjaxParam("custac","");
		grid.setAjaxParam("custpt","");
		grid.setAjaxParam("idtfno","");
		grid.setAjaxParam("teleno","");
		var url = Sunline.ajaxPath("myfriends/myfriend");
		grid.init({
	        src: $("#myfriends_ajax"),
	        onSuccess: function (grid) {
	        	$('.table-container .alert-danger').css("display","none");
	        },
	        onError: function (grid) {
	        	console.info("It is error!");
	        },
	        dataTable: { 
	            "ajax": {
	                "url": url,
	            },
	            "columns" : [{     
		            	"data": "custna",
		            	"sortable": false,
		            	"searchable": false
		            },{     
		            	"data": "teleno",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "opendt",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "bkyono",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		for(var i=0 ; i<shifouDict.length ; i++){
		            			if(data == shifouDict[i].dictId){
		            				return shifouDict[i].dictName;
		            			}
		            		}
		            		return data;
		            	}
		            },{ 
		            	"data": "tzyono",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		for(var i=0 ; i<shifouDict.length ; i++){
		            			if(data == shifouDict[i].dictId){
		            				return shifouDict[i].dictName;
		            			}
		            		}
		            		return data;
		            	}
		            }
	            ]
	        }
	    });
	};

	var handlerOperator = function(){
		$('#submit').click(function(){
			grid.setAjaxParam("custac",$('#custac').val());
			grid.setAjaxParam("custpt",$('#custpt').val());
			grid.setAjaxParam("idtfno",$('#idtfno').val());
			grid.setAjaxParam("teleno",$('#teleno').val());
			grid.submitFilter();
		});
		$('#cancle').click(function(){
			$('#custac').val("");
			$('#custpt').val("");
			$('#idtfno').val("");
			$('#teleno').val("");
		});

	};
	return {
		init : function(){
			handlerTable();
			handlerOperator();
		}
	}
}()