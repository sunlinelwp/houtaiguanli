var tranferInfo = function() {
	var crcycdDict=Sunline.getDict("E_CRCYCD");//币种
	var subjstDict=Sunline.getDict("E_SUBJST");//标的状态
	var transtDict=Sunline.getDict("E_SUBJTP");//状态
	
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
		$('#subjcd').text($(nRowA[0]).text());
		$('#subjnm').text($(nRowA[1]).text());
		$('#subjst').text($(nRowA[2]).text());
		$('#instrt').text($(nRowA[3]).text());
		$('#inveam').text($(nRowA[4]).text());
		$('#crcycd').text(formartDict(crcycdDict,$(nRowA[5]).text()));
		$('#onlnbl').text($(nRowA[6]).text());
		$('#ndrcpr').text($(nRowA[7]).text());
		$('#ndrcin').text($(nRowA[8]).text());
		$('#rcpram').text($(nRowA[9]).text());
		$('#rcinam').text($(nRowA[10]).text());
		$('#sequen').text($(nRowA[11]).text());
		$('#instdt').text($(nRowA[12]).text());
		$('#matudt').text($(nRowA[13]).text());
		$('#ndrcam').text($(nRowA[14]).text());
		$('#quamou').text($(nRowA[15]).text());
		$('#qucuam').text($(nRowA[16]).text());
		$('#trcuam').text($(nRowA[17]).text());
		$('#profit').text($(nRowA[18]).text());
		$('#transt').text(formartDict(transtDict,$(nRowA[19]).text()));
		$('#oldjcd').text($(nRowA[20]).text());
		$('#newjnm').text($(nRowA[21]).text());
       	$("#tranModal").modal('show');
	};
	
	var prodgrid = new Datatable();
	var handleTable = function(){
		var i = 0;
		prodgrid.setAjaxParam("custac","");
		var produrl = Sunline.ajaxPath("custService/qrdbsb");
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
									"data" : "subjcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "subjnm",
									"sortable" : false,
									"searchable" : false
									
								},
								{
									"data" : "subjst",
									"sortable" : false,
									"searchable" : false,
					            	"render" : function(data,type,full){
					            		for(var i=0 ; i<subjstDict.length ; i++){
					            			if(data == subjstDict[i].dictId){
					            				return subjstDict[i].dictName;
					            			}
					            		}
					            		return data;
					            	}
								},
								{
									"data" : "instrt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "inveam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "crcycd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "onlnbl",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "ndrcpr",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "ndrcin",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "rcpram",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "rcinam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "sequen",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "instdt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "matudt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "ndrcam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "quamou",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "qucuam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "trcuam",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "profit",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "transt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "oldjcd",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "newjnm",
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
				$("#datatable_prod tbody tr").find('td:eq(17)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(18)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(19)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(20)').css("display","none");
				$("#datatable_prod tbody tr").find('td:eq(21)').css("display","none");
		    }, 500);
		}
	}
}();