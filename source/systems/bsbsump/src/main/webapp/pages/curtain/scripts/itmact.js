var Itmact = function(){
	var _itemcd = "";
	var _acbrch = "";
	var amntcdDict=Sunline.getDict("amntcd");
	var isOk = false;
	var brchDict=Sunline.getDict("","/brch","brchno","brchna");
	var  _handler = function(){
		jQuery.validator.addMethod("ckisinac", function(value, element, param) {
		    if(param[0]=false){return true}
			if(value.substr(0,1)=='9'){
		    	return true;
		    }else{
		    	return false;
		    }
		}, $.validator.format("输入的帐号不复合内部户帐号规则"));
		//校验输入是否是数字 true时校验，第二个参数校验是否有小数
		jQuery.validator.addMethod("ckisnum", function(value, element, param) { 
			console.info(param[0]);
			if(param[0]==true){
				if(param[1]==true){
					reg=/^[-\+]?\d+(\.\d{0,2})?$/; 
				}else{
					reg=/^[-\+]?\d+$/;    
				}
			        if(!reg.test(value)){
			            return false;
			        }
			}
			return true;
		}, $.validator.format("请输入有效的数字!"));
		$('.itmact-form').validate({
			errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules:{
            	itemcd: {
                    required: true,
                    ckisnum : [true,true]
                },
                brchno: {
                    required: true,
                    ckisnum : [true,true]
                },
                tranam: {
                    required: true,
                    ckisnum : [true,true]
                },
                amntcd: {
                	required: true
                }
            },
            messages: {
                itemcd: {
                    required: "科目号必填"
                },
                acbrch: {
                    required: "机构号必填"
                },
                tranam: {
                	required : "交易金额必填"
                },
                amntcd: {
                	required: "借贷方式必选"
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                $('.alert-danger', $('.itmact-form')).show();
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
		$(".itemcd").inputmask({
            "mask": "9",
            "repeat": 18,
            "greedy": false
        });
		$('#amntcd').select2({
			data:amntcdDict,
			formatSelection: function(item){
				return item.dictName;
			},
		    formatResult: function(item){
				return item.dictName;
			}
		});
		$('#amntcd').select2("val","C");
		$("input[name='brchno']").select2({
			data : brchDict
		});
		$("input[name='brchno']").select2('val','0004');
		//确认按钮
		$("#submit").click(function () {
			if(!$('.itmact-form').validate().form() && !isOk ){
				bootbox.alert("验证失败");
				return;
			}
             var input={};
             input.itemcd =$("#itemcd").val();
             input.acbrch = $("#brchno").val();
             input.tranam = $("#tranam").val();
             input.amntcd = $("#amntcd").select2("val");
             input.crcycd = "01";
             input.quotfs = '1';
         	Sunline.ajaxRouter(
         	"curtain/itmact", 
         	 input,
         	"POST",
             function(redata){
         		if(redata.retCode!='0000'){
	     			bootbox.alert(redata.retMsg);
	     			return;
	     		}
         		var info = '转账成功';
        		bootbox.alert(info, function() {
        			$("#itemcd").val('');
                    $("#acbrch").val('');
                    $("#tranam").val('');
                }); 
         	},
         	function(redata){
         		bootbox.alert('转账失败，请重试');
         	},
         	"json",
         	false); 
         });
		//取消
		$("#cancle").click(function(){
			$("#itemcd").val('');
			$("#acbrch").val('');
            $("#tranam").val('');
		});
	};
	return {
		init : function(){
			_handler();
		}
	}
}()