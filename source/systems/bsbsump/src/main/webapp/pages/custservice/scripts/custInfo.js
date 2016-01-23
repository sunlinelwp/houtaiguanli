var custInfo = function() {
	//查询输入条件所有符合的数据
	var submitInfo = function(){                
		if(!$('#cust-form').validate().form()){
			return;
		}
		prodgrid.setAjaxParam("custac",$('#custac').val());
		prodgrid.setAjaxParam("mobile",$('#phoneNo').val());
		prodgrid.submitFilter();//包含自动刷新部分
	}
	var click = function(){
		$('#cancle').bind("click", function() {
			$("#custac").val("");
			$("#phoneNo").val("");
			submitInfo();
		});
	}
	var prodgrid = new Datatable();
	var handleTable = function(){
		prodgrid.setAjaxParam("custac",$('#custac').val());
		prodgrid.setAjaxParam("mobile",$('#phoneNo').val());
		var produrl = Sunline.ajaxPath("custService/qrcuif");//分页查询客户信息
		prodgrid.init({
			src : $("#datatable_cust"),
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
							"data" : "custac",//电子账户
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "loanrd",//借款记录
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "invest",//投资记录
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "amount",//账户总额
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "usable",//可用余额
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "freeze",//冻结金额
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "earsum",//总收益
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "rateen",//累计净收益
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "othear",//其他收益
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "hassum",//已收总额
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "haspal",//已收本金
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "hasint",//已收利息
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "forsum",//待收总额
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "forpal",//待收本金
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "forpay",//待收利息
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "repsum",//已还总额
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "reppal",//已还本金
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "reppay",//已还利息
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "foesum",//待还总额
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "foepal",//待还本金
							"sortable" : false,
							"searchable" : false
						},
						{
							"data" : "foepay",//待还利息
							"sortable" : false,
							"searchable" : false
						}
						]
			}
		});
	}
	return {
		init : function() {
			handleTable();
			click();
		},
		queryCust : function() {
			submitInfo();
		}
	}
}();