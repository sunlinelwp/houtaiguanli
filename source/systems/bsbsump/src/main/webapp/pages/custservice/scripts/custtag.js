var investInfo = function() {
	var prodgrid = new Datatable();
	var handleTable = function(){
		var i = 0;
		prodgrid.setAjaxParam("custac","");
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
								}							
								]
					}
				});
	       }

	//查询
	var submitInfo = function(){
		if(!$('#cust-form').validate().form()){
			return;
		}
		prodgrid.setAjaxParam("q_custac",$('#custac').val());
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