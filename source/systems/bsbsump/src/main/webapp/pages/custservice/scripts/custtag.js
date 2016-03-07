var custtag = function() {
	var prodgrid = new Datatable();
	var editForm = function(nRowA){
		bootbox.confirm("是否确定删除？", function(result) {
        	if(!result){
        		return;
        	}else{
        		var input  = {};
        		input.custac = $(nRowA[1]).text();
        		input.tagscd = $(nRowA[3]).text();
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
									
								},
								{
									"data" : "custpt",
									"sortable" : false,
									"searchable" : false
								},
								{
									"data" : "tagscd",
									"sortable" : false,
									"searchable" : false
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
		$('#m_save_debt').bind("click", function() {
			var input  = {};
			input.custac = $("#custna").val();
			input.tagscd = $("#tagscd").val();
			Sunline.ajaxRouter("custService/intags", input, "POST", function(data) {
				if(data.retCode=="0000"){
					bootbox.alert("新增成功！");
			       	$("#tranModal").modal('hide');
			       	submitInfo();
				}else{
					bootbox.alert("新增失败，"+data.retMsg+"！");
				}
			}, function(data) {
			});
			return false;
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