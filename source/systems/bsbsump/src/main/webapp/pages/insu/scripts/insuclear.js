var Insuclear =function(){
	var trantpDict=Sunline.getDict("clertp");
	var insubstDict=Sunline.getDict("clerst");
	var _formartDict=function (dict, value) {
	    for (var i = 0; i < dict.length; i++) {
              if (dict[i].id == value) {
                return dict[i].dictName;
              }
            }
	    return value;
	};
	
	var _formartDict2=function (dict, value) {
	    for (var i = 0; i < dict.length; i++) {
              if (dict[i].dictName == value) {
                return dict[i].id;
              }
            }
	    return value;
	};
	
	var handlerInsuclear = function(){
		if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: Metronic.isRTL(),
                orientation: "left",
                autoclose: true,
                language: 'zh-CN',
            });
		}
		$('#trantp').select2({
			data:trantpDict,
			formatSelection: function(item){
				return item.dictName;
			},
		    formatResult: function(item){
				return item.dictName;
			}
		});
		$('#trantp').select2("val","0");
		$('#trantp').on("change",function(e){
			console.info(e);
			$('#sucler').attr("disabled",true);
		});
		$('#select').click(function(){
			$('#select').attr("disabled",true);
			if(Sunline.isNull($('#dealdt').val())){
				$('#select').attr("disabled",false);
				bootbox.alert("请选择日期！"); 
				return;
			}
			$('#tranam').val("");
			$('#checkS').val("");
			var input = {};
			input.trandt = $('#dealdt').val();
			input.trantp = $('#trantp').val();

			Sunline.ajaxRouter(
		        	"insu/getTranam",
		        input,
		        	"POST",
		            function(redata){
		        		$('#select').attr("disabled",false);
		        		if(redata.retCode!='0000'){
		         			bootbox.alert(redata.retMsg);
		         			return;
		         		}
		        		if(redata.insuSetlle.status=="0"){
	        				$('#sucler').attr("disabled",false);
	        			}else{
	        				$('#sucler').attr("disabled",true);
	        			}
	        			$('#tranam').val(redata.insuSetlle.totlam);
	        			$('#checkS').val(_formartDict(insubstDict,redata.insuSetlle.status));
		        	},
		        	function(redata){
		        	
		        		$('#select').attr("disabled",false);
		        		bootbox.alert("网络异常，请重试！"); 
		        	},
		        	"json",
		        	false); 
		});
		$('#sucler').click(function(){
			if(Sunline.isNull($('#tranam').val())){
				bootbox.alert("请输入金额！"); 
				return;
			}
			if(Sunline.isNull($('#checkS').val()!="0")){
				bootbox.alert("清算状态不能为空！"); 
				return;
			}
			
			var _status = _formartDict2(insubstDict,$('#checkS').val());
			if(_status!="0"){
				bootbox.alert("清算状态不正确！"); 
				return;
			}
			bootbox.confirm("确定清算？",function(result){
				if(!result){
					return
				}
				$('#sucler').attr("disabled",true);
				var input = {};
				input.tranam =  $('#tranam').val();
				input.trandt =  $('#dealdt').val();
				input.trantp =  $('#trantp').val();
				input.crcycd =  "01";
				Sunline.ajaxRouter(
			         	"insu/clearTran", 
			         	 input,
			         	"POST",
			             function(redata){
			         		//success func
			         		if(redata.retCode !="0000"){
			         			$('#sucler').attr("disabled",false);
			         		}
			         		$('#checkS').val("已清算");
			         		bootbox.alert(redata.retMsg);
			         	},
			         	function(redata){
			         		$('#sucler').attr("disabled",false);
			         		bootbox.alert("网络异常");
			         	},
			         	"json",
			         	false); 
			});
						
		});
		
	};
	return {
		init : function(){
			handlerInsuclear();
		}
	}
}()