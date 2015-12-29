var investInfo = function() {
	var repstaDict=Sunline.getDict("E_REPYTP");//还款方式
	var itstatDict=Sunline.getDict("E_SUBJST");//状态
	
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
		$('#sunumb').text($(nRowA[0]).text());
		$('#suname').text($(nRowA[1]).text());
		$('#amount').text($(nRowA[2]).text());
		$('#sttime').text($(nRowA[3]).text());
		$('#forpay').text($(nRowA[4]).text());
		$('#sequen').text($(nRowA[5]).text());
		$('#repsta').text(formartDict(repstaDict,$(nRowA[6]).text()));
		$('#anrate').text($(nRowA[7]).text());
		$('#loterm').text($(nRowA[8]).text());
		$('#journo').text($(nRowA[9]).text());
		$('#itstat').text(formartDict(itstatDict,$(nRowA[10]).text()));
		$('#begndt').text($(nRowA[11]).text());
		$('#termdt').text($(nRowA[12]).text());
		$('#hasint').text($(nRowA[13]).text());
		$('#ndrcpr').text($(nRowA[14]).text());
		$('#lncfno').text($(nRowA[15]).text());
		$('#lotime').text($(nRowA[16]).text());
       	$("#tranModal").modal('show');
	};
	
	var prodgrid = new Datatable();
	var handleTable = function(){
		var i = 0;
		prodgrid.setAjaxParam("custac","");
		prodgrid.setAjaxParam("phoneNo","");
		var produrl = Sunline.ajaxPath("custService/fclnqr");
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
									"data" : "sunumb",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "suname",
									"sortable" : false,
									"searchable" : false
									
								},
								{
									"data" : "amount",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "sttime",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "forpay",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "sequen",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "repsta",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "anrate",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "loterm",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "journo",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "itstat",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "begndt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "termdt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "hasint",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "ndrcpr",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "lncfno",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "lotime",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : null,
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										return "<a class='edit' href='javascript:;' data-src='" + data + " data-value="+i+"'>详情 </a>";
									}
								}							
								]
					}
				});
		
		var sendData = ["custac"];
		prodgrid.bindTableEdit(sendData,editForm);
	       }

	

	
	//查询
	var submitInfo = function(){
		if(!$('#cust-form').validate().form()){
			return;
		}
		prodgrid.setAjaxParam("q_custac",$('#custac').val());
		prodgrid.setAjaxParam("q_phoneNo",$('#phoneNo').val());
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
				$("#datatable_prod tbody tr").find('td:eq(5)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(6)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(7)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(8)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(9)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(10)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(11)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(12)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(13)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(14)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(15)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(16)').css("display","none");
		    }, 500);
		}
	}
}();