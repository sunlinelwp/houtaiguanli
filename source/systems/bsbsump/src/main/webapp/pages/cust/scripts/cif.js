var Cif = function(){
	var acctstDict=Sunline.getDict("acctst");
	var eccttpDict=Sunline.getDict("eccttp");
	var amntcdDict=Sunline.getDict("amntcd");
	var flowtpDict=Sunline.getDict("flowtp");
	var transtDict=Sunline.getDict("transt");
	var grid = new Datatable();
	var tran_grid = new Datatable();
	var tran_grid_bank = new Datatable();
	var o_ecctno;
	var o_addr;
	var o_email;
	var o_acctst;
	var isNotF = true;
	var bankIsNotF = true;
	var testaccount="";
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
	var formartM = function(value){
		value = value.toString();
		if(value.toString().indexOf('.') == -1){
			return value+".00";
		}else if(value.split('.')[1].length == 1) {
			return value+'0';
		} else {
			return value;
		}
	};
	var formartTime = function(time){
		if(time.length == 8){
			return time.substr(0,1)+":"+time.substr(1,2)+":"+time.substr(3,2);
		}
		return time.substr(0,2)+":"+time.substr(2,2)+":"+time.substr(4,2);
	};
	
	//格式化时间为yyyy-mm-dd hh:mm:ss
	var formartTimes = function (time){
		return time.substr(0,4)+""+time.substr(4,2)+""+time.substr(6,2)+" ";//+time.substr(8,2)+":"+time.substr(10,2)+":"+time.substr(12,2);
		
	}
	var handlerTable = function(){
		
		var editForm = function(nRowA){
			o_ecctno = $(nRowA[1]).text();
			o_addr = $(nRowA[6]).text();
			o_email = $(nRowA[5]).text();
			o_acctst = formartDict(acctstDict,$(nRowA[9]).text());
			$('#m_ecctno').val($(nRowA[1]).text());
			$('#m_ecctna').val($(nRowA[2]).text());
			$('#m_addr').val($(nRowA[6]).text());
			$('#m_email').val($(nRowA[5]).text());
			$('#m_acctst').select2("val",formartDict(acctstDict,$(nRowA[9]).text()));
           	$("#editModal").modal('show');
		};
		var url = Sunline.ajaxPath("cust/custinfo");
		grid.setAjaxParam("ecctno","");
		grid.setAjaxParam("custna","");
		grid.setAjaxParam("idtfno","");
		grid.setAjaxParam("idtftp","");
		grid.setAjaxParam("teleno","");
		grid.init({
	        src: $("#cif_ajax"),
	        deleteData: sendData,
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
			            	"data": "null",
			            	"sortable": false,
			            	"searchable": false,
			            	"width": "2%",
			            	"render": function (data, type, full) {
			            		return '<input type="checkbox" class="checkboxes" value="1"/>';
			            	}
		            	},{     
		            	"data": "ecctno",
		            	"sortable": false,
		            	"searchable": false
		            },{     
		            	"data": "custna",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "idtfno",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "teleno",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "emails",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "addres",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "cardin",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "cardou",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "acctst",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function(data,type,full){
		            		for(var i=0 ; i<acctstDict.length ; i++){
		            			if(data == acctstDict[i].dictId){
		            				return acctstDict[i].dictName;
		            			}
		            		}
		            		return data;
		            	}
		            },{ 
		            	"data": "opendt",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "null",
		            	"sortable": false,
		            	"searchable": false,
		            	"render" : function (data, type, full) {
		            		return "<a class='edit' href='javascript:;' data-src='" + data + "'>编辑 </a>";
		            	}
		            }
	            ]
	        }
	    });
		$(".table-group-actions").append("<button id='bank_tran_btn' class='btn btn-sm blue table-group-action-submit'><i class='fa icon-cloud-download'></i>&nbsp;银行卡查询</button></div>");
		$(".table-group-actions").append("&nbsp;&nbsp;&nbsp;<button id='tran_btn' class='btn btn-sm blue table-group-action-submit'><i class='fa icon-cloud-download'></i>&nbsp;查询交易信息</button></div>");
		//$(".table-group-actions").append("<button id='f_btn'    class='btn btn-sm lightblue table-group-action-submit'><i class='fa icon-lock'></i>&nbsp;账户冻结明细</button></div>");
		var sendData = ["ecctno"];
        grid.bindTableEdit(sendData,editForm);
	};
	var handlerForm = function(){
		
		jQuery.validator.addMethod("id_no", function(value, element, param) {
			if((!Sunline.isNull(value)) && param == true){
				return IdCardValidate(value);
			}
			return true;
		}, $.validator.format("证件号码输入有误"));
		$('#cust-form').validate({
			errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules:{
            	custac: {
                    required: false
            	},
            	idcard : {
            		required: false,
            		id_no : true
            	},
            	telecd : {
            		required: false,
            		rangelength : [11,11]
            	}
            },
            messages: {
            	checkdate: {
                    required: "对账日期必填"
                },
                idcard : {
            		id_no : "证件号输入有误"
            	},
            	telecd : {
            		rangelength : "手机号码位数不正确"
            	}
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                $('.alert-danger', $('#cust-form')).show();
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
		$('#mod-form').validate({
			errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules:{
            	m_addr: {
                    required: true
            	},
            	m_email : {
            		required: true
            	},
            	m_acctst: {
                    required: true
                    //isidtp : true
            	}
            },
            messages: {
            	checkdate: {
                    required: "对账日期必填"
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                $('.alert-danger', $('#mod-form')).show();
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            }
		});
		jQuery.validator.addMethod("istruedt", function(value, element, param) { 
			if(param==true){
				console.info($('#trandt_from').val() + value);
				return parseInt($('#trandt_from').val()) > parseInt($('#trandt_to').val());
			}
			return true;
			
		}, $.validator.format("起始日期不能大于结束日期"));
		$('#cust-tran-form').validate({
			errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules:{
            	tran_custac: {
                    required: true
            	},
            	from : {
            		required: false
            		//istruedt : true
            	},
            	to: {
                    required: false
                    //istruedt : true
            	}
            },
            messages: {
            	tran_custac: {
                    required: "电子账户必填"
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                $('.alert-danger', $('#cust-tran-form')).show();
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            }
		});
	};
	var handlerOperator = function(){
		if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: Metronic.isRTL(),
                orientation: "left",
                autoclose: true,
                language: 'zh-CN'
            });
            //$('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
        }
		//初始化select2
		$("#m_acctst").select2({
			data : acctstDict,
			formatSelection: function(item){
				return item.dictName;
			},
		    formatResult: function(item){
				return item.dictName;
			}
		});
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
		//查询
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
		$('#tran_cancle').click(function(){
			$('#tran_custac').val("");
			$('#eccttp').select2("val","");
		});
		//修改保存
		$('#m_save').click(function(){
			if(!$('#mod-form').validate().form()){
				return;
			}
			var ecctno = $('#m_ecctno').val();
			var address = $('#m_addr').val();
			var email = $('#m_email').val();
			var acctst = $('#m_acctst').select2("val");
			if(address == o_addr && email == o_email && acctst == o_acctst) {
				return ;
			}
			var input = {};
			input.custac = ecctno;
			input.addres = address;
			input.emails = email;
			input.acctst = acctst;
			$("#myModal").modal('show');
			Sunline.ajaxRouter(
		         	"cust/update", 
		         	 input,
		         	"POST",
		             function(redata){
		         		//success func
		         		//console.info("success:",redata);
		         		if(redata.retCode!='0000'){
		         			bootbox.alert(redata.retMsg);
		         			return;
		         		}
		         		$("#myModal").modal('hide');
		         		bootbox.alert("电子账户["+ecctno+"]信息维护成功！",function(){
		         			$('#m_ecctno').val("");
		        			$('#m_ecctna').val("");
		        			$('#m_addr').val("");
		        			$('#m_email').val("");
		        			$('#m_acctst').select2("val","");
		                   	$("#editModal").modal('hide');
		                   	grid.submitFilter();
		         		});
		         	},
		         	function(redata){
		         		//console.info("error:",redata);
		         		$("#myModal").modal('hide');
		         		bootbox.alert("网络异常");
		         	},
		         	"json",
		         	true);
		});
		
		
		//银行卡查询
		$('#bank_tran_btn').click(function(){
			
			var rows = grid.getSelectedRows();
			if(rows.length != 1){
				bootbox.alert("请选择一条信息");
				return;
			}
			var ecctno = $(rows[0].children()[1]).text();
			var url1 = Sunline.ajaxPath("cust/bankcardif");
			
			console.info(bankIsNotF);
			if(bankIsNotF){
				tran_grid_bank.setAjaxParam("custac",ecctno);
				tran_grid_bank.init({
			        src: $("#cif_tran_ajax_bank"),
			        onSuccess: function (grid) {
			        	$('.cif_tran_ajax_wrapper .alert-danger').css("display","none");
			        },
			        onError: function (grid) {
			        	//$('.cif_tran_ajax_wrapper .alert-danger').css("display","none");
			        	//console.info("It is error!");
			        },
			        dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 
			            "ajax": {
			                "url": url1, // ajax source
			            },
			            "columns" : [{
				            	"data": "cardno",
				            	"sortable": false,
				            	"searchable": false
			            	},{     
				            	"data": "brchno",
				            	"sortable": false,
				            	"searchable": false
				            },{     
				            	"data": "acctna",
				            	"sortable": false,
				            	"searchable": false
				            },{ 
				            	"data": "brchna",
				            	"sortable": false,
				            	"searchable": false
				            },{     
				            	"data": "binddt",
				            	"sortable": false,
				            	"searchable": false
				            },{ 
				            	"data": "status",
				            	"sortable": false,
				            	"searchable": false,
				            	"render" : function(data,type,full){
				            		
				            		if((data+"") == "1"){
				            			return "正常";
				            		}else if((data+"") == "2"){
				            			return "关闭";
				            		}else if((data+"") == "3"){
				            			return "睡眠";
				            		}else{
				            			return "";
				            		}
				            		
				            	}
				            },{ 
				            	"data": "uptime",
				            	"sortable": false,
				            	"searchable": false,
				            	"render" : function(data,type,full){
				            		return formartTimes(data);
				            	}
				            }
			            ]
			        }
			    });
				bankIsNotF = false;
			} else {
				console.info(tran_grid_bank.gettableContainer());
				console.info(tran_grid_bank.getDataTable());
				console.info(tran_grid_bank.getTable());
				tran_grid_bank.setAjaxParam("custac",ecctno);
				tran_grid_bank.submitFilter();
			}
			$("#banktranModal").modal('show');
		});
		
		
		//交易明细
		$('#tran_btn').click(function(){
			
			var rows = grid.getSelectedRows();
			if(rows.length != 1){
				bootbox.alert("请选择一条信息");
				return;
			}
			var ecctno = $(rows[0].children()[1]).text();
			$('#tran_custac').val(ecctno);
			var url1 = Sunline.ajaxPath("cust/cutrif");
			console.info(isNotF);
			if(isNotF){
				tran_grid.setAjaxParam("ecctno",ecctno);
				tran_grid.setAjaxParam("from","");
				tran_grid.setAjaxParam("to","");
				tran_grid.setAjaxParam("eccttp","2");
				tran_grid.setAjaxParam("crcycd","01");
				tran_grid.init({
			        src: $("#cif_tran_ajax"),
			        deleteData: sendData,
			        onSuccess: function (grid) {
			            // execute some code after table records loaded
			        	$('.cif_tran_ajax_wrapper .alert-danger').css("display","none");
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
				            	"data": "trandt",
				            	"sortable": false,
				            	"searchable": false
			            	},{     
				            	"data": "trantm",
				            	"sortable": false,
				            	"searchable": false,
				            	"render" : function(data,type,full){
				            		return formartTime(data);
				            	}
				            },{     
				            	"data": "tranam",
				            	"sortable": false,
				            	"searchable": false,
				            	"render" : function(data,type,full){
				            		return formartM(data);
				            	}
				            },{ 
				            	"data": "chnlno",
				            	"sortable": false,
				            	"searchable": false
				            },{ 
				            	"data": "avalbl",
				            	"sortable": false,
				            	"searchable": false,
				            	"render" : function(data,type,full){
				            		return formartM(data);
				            	}
				            },{ 
				            	"data": "smryds",
				            	"sortable": false,
				            	"searchable": false
				            },{ 
				            	"data": "jnlseq",
				            	"sortable": false,
				            	"searchable": false
				            },{ 
				            	"data": "flowtp",
				            	"sortable": false,
				            	"searchable": false,
				            	"render" : function(data,type,full){
				            		 for (var i = 0; i < flowtpDict.length; i++) {
				                          if (flowtpDict[i].id == data) {
				                            return flowtpDict[i].dictName;
				                          }
				                        }
				            	    return data;
				            	}
				            },{ 
				            	"data": "amntcd",
				            	"sortable": false,
				            	"searchable": false,
				            	"render" : function(data,type,full){
				            		 for (var i = 0; i < amntcdDict.length; i++) {
				                          if (amntcdDict[i].id == data) {
				                            return amntcdDict[i].dictName;
				                          }
				                        }
				            	    return data;
				            	}
				            },{ 
				            	"data": "transt",
				            	"sortable": false,
				            	"searchable": false,
				            	"render" : function(data,type,full){
				            		 for (var i = 0; i < transtDict.length; i++) {
				                          if (transtDict[i].id == data) {
				                            return transtDict[i].dictName;
				                          }
				                        }
				            	    return data;
				            	}
				            },{ 
				            	"data": "prcsid",
				            	"sortable": false,
				            	"searchable": false
				            }
			            ]
			        }
			    });
				var sendData = ["transq"];
				isNotF = false;
			} else {
				console.info(tran_grid.gettableContainer());
				console.info(tran_grid.getDataTable());
				console.info(tran_grid.getTable());
				tran_grid.setAjaxParam("ecctno",ecctno);
				tran_grid.setAjaxParam("from",$('#trandt_from').val());
				tran_grid.setAjaxParam("to",$('#trandt_to').val());
				tran_grid.setAjaxParam("eccttp",$('#eccttp').select2("val"));
				tran_grid.setAjaxParam("crcycd","01");
				tran_grid.submitFilter();
			}
			$("#tranModal").modal('show');
		});
		
		//交易查询
		$('#tran_qry').click(function(){
			if(!$('#cust-tran-form').validate().form()){
				return;
			}
			tran_grid.setAjaxParam("ecctno",$('#tran_custac').val());
			tran_grid.setAjaxParam("from",$('#trandt_from').val());
			tran_grid.setAjaxParam("to",$('#trandt_to').val());
			tran_grid.setAjaxParam("eccttp",$('#eccttp').select2("val"));
			tran_grid.setAjaxParam("crcycd","01");
			tran_grid.submitFilter();
		});
	};
	return {
		init : function(){
			handlerTable();
			handlerForm();
			handlerOperator();
		}
	}
}()