var Upcnzw = function(){
	var _acctno = "";

	var  _handler = function(){
	
		//确认按钮
		$("#submit").click(function () {
			
			 var input={};
             input.acctno =$("#acctno").val();
             input.tranam = $("#tranam").val();
         	Sunline.ajaxRouter(
         	"upcntain/upcnsj", 
         	 input,
         	"POST",
             function(redata){
         		if(redata.retCode!='0000'){
	     			bootbox.alert(redata.retMsg);
	     			return;
	     		}
         		var info = '调账成功';
         		ReCheck.hideModal();
        		bootbox.alert(info, function() {
        			$("#acctno").val('');
                    $("#tranam").val('');
                }); 
         	},
         	function(redata){
         		bootbox.alert('失败，请重试');
         	},
         	"json",
         	false); 
         });
		
		//清空
		$("#cancle").click(function(){
			$("#acctno").val('');
            $("#tranam").val('');
		});
	
	};
	return {
		init : function(){
			_handler();
		}
	}
}()