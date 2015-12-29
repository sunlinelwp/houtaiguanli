var tranferInfo = function() {
	var crcycdDict=Sunline.getDict("E_CRCYCD");//币种
	var subjstDict=Sunline.getDict("E_SUBJST");//标的状态
	var transtDict=Sunline.getDict("E_SUBJTP");//状态
	var repytpDist=Sunline.getDict("E_REPYTP");//收益方式
	var d1 = null;
	var d2 = null;
	var d3 = null;
	
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
		$('#subjnm').text($(nRowA[1]).text());
//		$('#invcyc').text($(nRowA[2]).text()); 投资转让期限
//		$('#subjst').text($(nRowA[2]).text());
		$('#trcuam').text($(nRowA[2]).text());
		$('#instrt').text($(nRowA[4]).text());
		$('#profit').text($(nRowA[5]).text());
//		$('#inveam').text($(nRowA[4]).text());
//		$('#crcycd').text(formartDict(crcycdDict,$(nRowA[5]).text()));
//		$('#ndrcpr').text($(nRowA[7]).text());
//		$('#rcpram').text($(nRowA[9]).text());
//		$('#rcinam').text($(nRowA[10]).text());
//		$('#sequen').text($(nRowA[11]).text());
//		$('#instdt').text($(nRowA[12]).text());
//		$('#matudt').text($(nRowA[13]).text());
//		$('#ndrcam').text($(nRowA[14]).text());
//		$('#quamou').text($(nRowA[15]).text());
//		$('#qucuam').text($(nRowA[16]).text());
//		$('#transt').text(formartDict(transtDict,$(nRowA[19]).text()));
//		$('#oldjcd').text($(nRowA[20]).text());
//		$('#newjnm').text($(nRowA[21]).text());
		$('#remday').text($(nRowA[23]).text());
		$('#zrdate').text($(nRowA[24]).text());
		$('#onlnbl').text($(nRowA[25]).text());
		$('#ndrcin').text($(nRowA[26]).text());
		$('#remeth').text($(nRowA[27]).text());
		$('#invcyc').text($(nRowA[28]).text());//项目原始周期
		$('#coufee').text($(nRowA[29]).text());
		
       	$("#tranModal").modal('show');
	};
	
	var prodgrid = new Datatable();
	var handleTable = function(){
		var i = 0;
		prodgrid.setAjaxParam("custac","");
		prodgrid.setAjaxParam("teleno","");
		var produrl = Sunline.ajaxPath("custService/qrdbsbin");
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
//								{
//									"data" : "subjcd",
//									"sortable" : false,
//									"searchable" : false
//								},
								{//借款标题
									"data" : "subjnm",
									"sortable" : false,
									"searchable" : false,
								},
								{//转入价格
									"data" : "trcuam",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data,type,full){
					            		d1=data;
					            		return data;
					            	}
								},
								{//转让投资期限
									"data" : "invcyc",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data,type,full){
					            		d2=data;
					            		return data;
					            	}
								},
								{//综合收益率
									"data" : "instrt",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data,type,full){
					            		d3=data;
					            		return data;
					            	}
								},
								{//预期收益金额 
									"data" : "profit",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data,type,full){
					            		data = d1*d2/100/365*d3;
					            		return data.toFixed(2);
					            	}
								},
								{//转入日期
									"data" : "zrdate",
									"sortable" : false,
									"searchable" : false
								},
								{//项目原始金额
									"data" : "onlnbl",
									"sortable" : false,
									"searchable" : false
								},
								{//项目原始年化收益率
									"data" : "invsrt",
									"sortable" : false,
									"searchable" : false
								},
								{//收益方式
									"data" : "remeth",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data,type,full){
					            		for(var i=0 ; i<repytpDist.length ; i++){
					            			if(data == repytpDist[i].dictId){
					            				return repytpDist[i].dictName;
					            			}
					            		}
					            		return data;
					            	}
								},
								{//项目原始周期
									"data" : "invcyc",
									"sortable" : false,
									"searchable" : false
									
								},
								{//转让手续费
									"data" : "coufee",
									"sortable" : false,
									"searchable" : false
//								},
//								{
//									"data" : "subjst",
//									"sortable" : false,
//									"searchable" : false,
//					            	"render" : function(data,type,full){
//					            		for(var i=0 ; i<subjstDict.length ; i++){
//					            			if(data == subjstDict[i].dictId){
//					            				return subjstDict[i].dictName;
//					            			}
//					            		}
//					            		return data;
//					            	}
//								},
								
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
		prodgrid.setAjaxParam("q_phoneNo",$('#teleno').val());
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
				 
		    }, 500);
		}
	}
}();

