var yqrcvpay = function(){
	var yqrxrvDict=Sunline.getDict("yqrxrv");//处理状态
	var cometpDict=Sunline.getDict("cometp");//来源文件
	var crcycdDict = Sunline.getDict("crcycd");//币种
	var chnlcdDict = Sunline.getDict("chnlcd");//渠道类型
	var pytypeDict = Sunline.getDict("pytype");//支付方式
	var acctprDict = Sunline.getDict("acctpr");//账号属性
	var grid = new Datatable();
	var _tranDate = "0000";
	var _status = "0000";
	var _formartDict = function(dict,value){
		for (var i = 0; i < dict.length; i++) {
            if (dict[i].dictName == value) {
              return dict[i].dictId;
            }
          }
	    return value;
	};
	var formartM = function(value){
		if(value.indexOf('.') == -1){
			return value+".00";
		}else if(value.split('.')[1].length == 1) {
			return value+'0';
		} else {
			return value;
		}
	};
	var handleForm = function () {

		if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: Metronic.isRTL(),
                orientation: "left",
                autoclose: true,
                language: 'zh-CN',
            });
            //$('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
            
        };
        $("#states").select2({width:"100%",
        	data : yqrxrvDict,
        	formatSelection: function(item){
				return item.dictName;
			},
		    formatResult: function(item){
				return item.dictName;
			}
        });

        $("#states").select2("val","0");
        $("#cometp").select2({width:"100%",
        	data : cometpDict,
        	formatSelection: function(item){
				return item.dictName;
			},
		    formatResult: function(item){
				return item.dictName;
			}
        });
    };
	var readFile = function(){
		$('#file-form').validate({
			errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules:{
            	checkdate: {
                    required: true
            	},
            	cometp :{
            		required: true
            	}
            },
            messages: {
            	checkdate: {
                    required: "对账日期必填"
                },
                cometp: {
                    required: "来源渠道必选"
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                $('.alert-danger', $('#file-form')).show();
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            submitHandler: function (form) {
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            }
           
		});
		$('#submit').click(function(){
			if(!$('#file-form').validate().form()){
				return;
			}
			var input = {};
			var trandt = $('#check-date').val();
			if($('#cometp').val() == '1' ){
				input.file = 'inve_fail_';//交易失败文件
			}else if($('#cometp').val() == '2'){
				input.file = 'inve_repy_';//回款交易文件
			}else if($('#cometp').val() == '3'){
				input.file = 'inve_chpa_';//CP对账出金文件
			}
			input.trandt = trandt;
			input.cometp = $('#cometp').val();
			$("#myModal").modal('show');
			Sunline.ajaxRouter(
	        	"yqrx/readFile", 
	        	 input,
	        	"POST",
	            function(redata){
	        		$("#myModal").modal('hide');
	        		//读取数据区表
	        		if(redata.retCode == '0000'){
	        			$('.table-container .alert-danger').css("display","none");
	        			grid.setAjaxParam("trandt",$('#check-date').val());
	        			grid.setAjaxParam("states",$('#states').select2("val"));
	        			grid.setAjaxParam("cometp",$('#cometp').select2("val"));
	        			grid.submitFilter();
	        		} else {
	        			bootbox.alert(redata.retMsg);
	        		}
	        	},
	        	function(redata){
	        		$("#myModal").modal('hide');
	        		console.info(redata);
	        		bootbox.alert("交易异常哦，请检查网络设置货重新登录"); 
	        	},
	        	"json",
	        	false); 
		});
		
	};
	var handlerTable = function(){
		$('.table-container .alert-danger').css("display","none");
			var url = Sunline.ajaxPath("yqrx/selectPage");
			var editForm = function(nRowA){
				bootbox.confirm("确定要出金？", function(result) {
					if(!result){
	            		return;
	            	}
				var input = {};
				input.amouid = $(nRowA[0]).text();
				$("#myModal").modal('show');
				Sunline.ajaxRouter(
		        	"yqrx/senaou", 
		        	 input,
		        	"POST",
		            function(redata){
		        		$("#myModal").modal('hide');
		        		//读取数据区表
		        		if(redata.retCode == '0000'){
		        			$('.table-container .alert-danger').css("display","none");
		        			bootbox.alert("出金成功");
		        			grid.setAjaxParam("trandt",$('#check-date').val());
		        			grid.setAjaxParam("states",$('#states').select2("val"));
		        			grid.setAjaxParam("cometp",$('#cometp').select2("val"));
		        			grid.submitFilter();
		        		} else {
		        			bootbox.alert(redata.retMsg);
		        		}
		        	},
		        	function(redata){
		        		$("#myModal").modal('hide');
		        		console.info(redata);
		        		bootbox.alert("交易异常哦，请检查网络设置货重新登录"); 
		        	},
		        	"json",
		        	false); 
			});

			};
			grid.setAjaxParam("trandt","");
			grid.setAjaxParam("cometp","");
			grid.setAjaxParam("states","");
			console.info("处理状态"+$('#states').select2("val"));
			grid.setAjaxParam("checkStatus","");
			grid.init({
		        src: $("#cppchk_ajax"),
		        deleteData: sendData,
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
		            "columns" : [
		                {     
			            	"data": "amouid",
			            	"sortable": false,
			            	"searchable": false
			            },{ 
			            	"data": "acctno",
			            	"sortable": false,
			            	"searchable": false
			            },{ 
			            	"data": "payeac",
			            	"sortable": false,
			            	"searchable": false
			            },{ 
			            	"data": "payena",
			            	"sortable": false,
			            	"searchable": false
			            },{ 
			            	"data": "tranam",
			            	"sortable": false,
			            	"searchable": false,
			            	"render": function (data, type, full) {
			            	    return formartM(data);
			            	}
			            },{ 
			            	"data": "chgeam",
			            	"sortable": false,
			            	"searchable": false
			            },{ 
			            	"data": "acctpr",
			            	"sortable": false,
			            	"searchable": false,
			            	"render": function (data, type, full) {
			            	    for (var i = 0; i < acctprDict.length; i++) {
			                          if (acctprDict[i].id == data) {
			                            return acctprDict[i].dictName;
			                          }
			                        }
			            	    return acctprDict[1].dictName;
			            	}
			            },{ 
			            	"data": "chnlcd",
			            	"sortable": false,
			            	"searchable": false,
			            	"render": function (data, type, full) {
			            	    for (var i = 0; i < chnlcdDict.length; i++) {
			                          if (chnlcdDict[i].id == data) {
			                            return chnlcdDict[i].dictName;
			                          }
			                        }
			            	    return data;
			            	}
			            },{ 
			            	"data": "pytype",
			            	"sortable": false,
			            	"searchable": false,
			            	"render": function (data, type, full) {
			            	    for (var i = 0; i < pytypeDict.length; i++) {
			                          if (pytypeDict[i].id == data) {
			                            return pytypeDict[i].dictName;
			                          }
			                        }
			            	    return data;
			            	}
			            },{ 
			            	"data": "cometp",
			            	"sortable": false,
			            	"searchable": false,
			            	"render": function (data, type, full) {
			            	    for (var i = 0; i < cometpDict.length; i++) {
			                          if (cometpDict[i].id == data) {
			                            return cometpDict[i].dictName;
			                          }
			                        }
			            	    return data;
			            	}
			            },{ 
			            	"data": "amoudt",
			            	"sortable": false,
			            	"searchable": false
			            },{ 
			            	"data": "states",
			            	"sortable": false,
			            	"searchable": false,
			            	"render": function (data, type, full) {
			            	    for (var i = 0; i < yqrxrvDict.length; i++) {
			                          if (yqrxrvDict[i].id == data) {
			                            return yqrxrvDict[i].dictName;
			                          }
			                        }
			            	    return data;
			            	}
			            },{ 
			            	"data": null,
			            	"sortable": false,
			            	"searchable": false,
							"render" : function(data, type, full) {
								if($("#states").val()=='0'){
									return "<a class='edit' href='javascript:;' data-src='" + data + "'>出金 </a>";
								}
								return "";
							}
			            }
		            ]
		        }
		    });
			$(".table-group-actions").append("<button id='query' class='btn btn-sm blue table-group-action-submit'><i class='fa fa-search'></i>&nbsp;查询</button></div>");
			var sendData = ["checkDate","checkStatus"];
	        grid.bindTableDelete(sendData);
	        grid.bindTableEdit(sendData,editForm);
	        $('#query').click(function(){
	        	grid.setAjaxParam("trandt",$('#check-date').val());
    			grid.setAjaxParam("states",$('#states').select2("val"));
    			grid.setAjaxParam("cometp",$('#cometp').select2("val"));
    			grid.submitFilter();
	        });
	        
		_tranDate = $('#check-date').val();
	};
	return {
		init : function(){
			readFile();
			handleForm();
			handlerTable();
		}
	}
}()