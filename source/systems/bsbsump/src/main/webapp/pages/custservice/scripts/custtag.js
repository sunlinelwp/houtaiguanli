var custtag = function() {
	var tagsnoDict=Sunline.getDict("tagsno");
	
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
	var prodgrid = new Datatable();
	var editForm = function(nRowA){
		bootbox.confirm("是否确定删除？", function(result) {
        	if(!result){
        		return;
        	}else{
        		var input  = {};
        		input.custac = $(nRowA[1]).text();
        		input.tagscd = formartDict(tagsnoDict,$(nRowA[4]).text());
        		Sunline.ajaxRouter("custService/detags", input, "POST", function(data) {
        			if(data.retCode=="0000"){
        		       	submitInfo();
        			}else{
        				bootbox.alert("删除失败，"+data.retMsg);
        			}
        		}, function(data) {
        		});
        	}
		});
		return false;
	
	};
	var handleTable = function(){
		var i = 0;
		prodgrid.setAjaxParam("custac","");
		prodgrid.setAjaxParam("idtfno","");
		var produrl = Sunline.ajaxPath("custService/custTag");
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
									"data" : "custna",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "custac",
									"sortable" : false,
									"searchable" : false
									
								},{
									"data" : "idtfno",
									"sortable" : false,
									"searchable" : false
									
								},
								{
									"data" : "custpt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "tagscd",
									"sortable" : false,
									"searchable" : false,
					            	"render" : function(data,type,full){
					            		for(var i=0 ; i<tagsnoDict.length ; i++){
					            			if(data == tagsnoDict[i].dictId){
					            				return tagsnoDict[i].dictName;
					            			}
					            		}
					            		return data;
					            	}
								},
								{
									"data" : "tagscd",
									"sortable" : false,
									"searchable" : false,
									"render" : function(data, type, full) {
										if(!Sunline.isNull(data)){
											return "<a class='edit' href='javascript:;' data-src='" + data + " data-value="+i+"'>删除</a>";
										}
										return "";
									}
								}						
								]
					}
				});
		var sendData = ["custac","tagscd"];
		prodgrid.bindTableEdit(sendData,editForm);
	       }

	//查询
	var submitInfo = function(){
		if(!$('#cust-form').validate().form()){
			return;
		}
		prodgrid.setAjaxParam("q_custac",$('#custac').val());
		prodgrid.setAjaxParam("q_idtfno",$('#idtfno').val());
		prodgrid.submitFilter();
	}
	var click = function(){
		$('#add_prod_btn').bind("click", function() {
			$("#tranModal").modal('show');
		});
		$('#cancle').bind("click", function() {
			$("#custac").val('');
			$("#idtfno").val('');
		});
		$('#m_save_debt').bind("click", function() {
			var input  = {};
			input.custac = $("#custna").val();
			input.tagscd = $("#tagscd").val();
			Sunline.ajaxRouter("custService/intags", input, "POST", function(data) {
				if(data.retCode=="0000"){
					bootbox.alert("新增成功！");
					$("#custna").val('');
					$("#tagscd").select2("val","");
			       	$("#tranModal").modal('hide');
			       	submitInfo();
				}else{
					bootbox.alert("新增失败，"+data.retMsg+"！");
				}
			}, function(data) {
			});
			return false;
		});
		
		$("#tagscd").select2({
			data : tagsnoDict,
			formatSelection: function(item){
				return item.dictName;
			},
		    formatResult: function(item){
				return item.dictName;
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