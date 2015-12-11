var deptInfo = function(){
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
	
	var handlerTable = function(){
		var grid = new Datatable();
		var url = Sunline.ajaxPath("fcadept/qralld");
		grid.init({
	        src: $("#cif_ajax"),
	        onSuccess: function (grid) {
	        	$('.table-container .alert-danger').css("display","none");
	        },
	        onError: function (grid) {
	        },
	        dataTable: {
	            "ajax": {
	                "url": url, 
	            },
	            "columns" : [{     
		            	"data": "prodcd",
		            	"sortable": false,
		            	"searchable": false
		            },{     
		            	"data": "prodna",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "period",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "minrat",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "maxrat",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "balamo",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "adbalm",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "limtsq",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "minbal",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "maxbal",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": "ratevl",
		            	"sortable": false,
		            	"searchable": false
		            },{ 
		            	"data": null,
		            	"sortable": false,
		            	"searchable": false,
		            	"render": function (data, type, full) {
		            		return '';
		            	}
		            }
	            ]
	        }
	    });
	};
	
	var clickInsert = function(){
		// 新增窗口
		$("#add_prod_btn").bind("click", function() {
			$("#btn-insert").modal('show');
			return false;
		});

		// 新增规则窗口
		$("#insert-debt").bind("click", function() {
			var input  = {};
			Sunline.ajaxRouter("fcadept/qryrbm", input, "POST", function(data) {
				$("#prodcd").select2({
					data : data.data,
					formatSelection : function(item) {
						return  item.text+ "[" + item.id + "]";
					},
					formatResult : function(item) {
						return  item.text+ "[" + item.id + "]";
					}
				});
			}, function(data) {
			});
			$("#btn-insert-debt").modal('show');
			return false;
		});
		
		// 保存规则按钮
		$("#m_save_debt").bind("click", function() {
			var input  = {};
			input.prodcd = $("#prodcd").val();
			input.period = $("#period").val();
			input.minrat = $("#minrat").val();
			input.maxrat = $("#maxrat").val();
			input.balamo = $("#balamo").val();
			input.adbalm = $("#adbalm").val();
			Sunline.ajaxRouter("fcadept/indebt", input, "POST", function(data) {
				if(data.retCode=="0000"){
					$("#btn-insert-debt").modal('hide');
					$("#btn-insert").modal('hide');
					$.ajax({
						type : "POST",
						url : Sunline.getBasePath() + "/path/dept/deptInfo",
						success : function(data) {
							$("#main-content").html(data);
						},
						statusCode : {
							404 : function() {
								var err = Sunline.getBasePath() + "/error/404";
								$("#main-content").load(err);
							}
						}
					});
				}else{
					bootbox.alert("新增失败，"+data.retMsg);
				}
			}, function(data) {
			});
			return false;
		});
		
		
		// 新增范围窗口
		$("#insert-limt").bind("click", function() {
			var input  = {};
			Sunline.ajaxRouter("fcadept/qrdepr", input, "POST", function(data) {
				$("#prodcd1").select2({
					data : data.data,
					formatSelection : function(item) {
						return  item.text+ "[" + item.id + "]";
					},
					formatResult : function(item) {
						return  item.text+ "[" + item.id + "]";
					}
				});
			}, function(data) {
			});
			$("#btn-insert-limt").modal('show');
			return false;
		});
		
		// 保存范围按钮
		$("#m_save_limt").bind("click", function() {
			var input  = {};
			input.prodcd = $("#prodcd1").val();
			input.minbal = $("#minbal").val();
			input.maxbal = $("#maxbal").val();
			Sunline.ajaxRouter("fcadept/inlimt", input, "POST", function(data) {
				if(data.retCode=="0000"){
					$("#btn-insert-limt").modal('hide');
					$("#btn-insert").modal('hide');
					$.ajax({
						type : "POST",
						url : Sunline.getBasePath() + "/path/dept/deptInfo",
						success : function(data) {
							$("#main-content").html(data);
						},
						statusCode : {
							404 : function() {
								var err = Sunline.getBasePath() + "/error/404";
								$("#main-content").load(err);
							}
						}
					});
				}else{
					bootbox.alert("新增失败，"+data.retMsg);
				}
			}, function(data) {
			});
			return false;
		});
		
		// 新增比例窗口
		$("#insert-rate").bind("click", function() {
			var input  = {};
			Sunline.ajaxRouter("fcadept/qrdepr", input, "POST", function(data) {
				$("#prodcd2").select2({
					data : data.data,
					formatSelection : function(item) {
						return  item.text+ "[" + item.id + "]";
					},
					formatResult : function(item) {
						return  item.text+ "[" + item.id + "]";
					}
				});
			}, function(data) {
			});
			$("#prodcd2").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd2").val();
				Sunline.ajaxRouter("fcadept/qrlimt", input, "POST", function(data) {
					$("#balnam").select2({
						data : data.data,
						formatSelection : function(item) {
							return  item.text+ "[" + item.id + "]";
						},
						formatResult : function(item) {
							return  item.text+ "[" + item.id + "]";
						}
					});
				}, function(data) {
				});
			});
			$("#btn-insert-rate").modal('show');
			return false;
		});
		
		// 保存比例按钮
		$("#m_save_rate").bind("click", function() {
			var input  = {};
			input.prodcd = $("#prodcd2").val();
			input.limtsq = $("#balnam").val();
			input.ratevl = $("#ratevl").val();
			Sunline.ajaxRouter("fcadept/inrate", input, "POST", function(data) {
				if(data.retCode=="0000"){
					$("#btn-insert-rate").modal('hide');
					$("#btn-insert").modal('hide');
					$.ajax({
						type : "POST",
						url : Sunline.getBasePath() + "/path/dept/deptInfo",
						success : function(data) {
							$("#main-content").html(data);
						},
						statusCode : {
							404 : function() {
								var err = Sunline.getBasePath() + "/error/404";
								$("#main-content").load(err);
							}
						}
					});
				}else{
					bootbox.alert("新增失败，"+data.retMsg);
				}
			}, function(data) {
			});
			return false;
		});
	}
	
	var clickUpdate = function(){
		// 修改窗口
		$("#upd_prod_btn").bind("click", function() {
			$("#btn-update").modal('show');
			return false;
		});
		
		// 修改规则窗口
		$("#update-debt").bind("click", function() {
			var input  = {};
			Sunline.ajaxRouter("fcadept/qryrbm", input, "POST", function(data) {
				$("#prodcd3").select2({
					data : data.data,
					formatSelection : function(item) {
						return  item.text+ "[" + item.id + "]";
					},
					formatResult : function(item) {
						return  item.text+ "[" + item.id + "]";
					}
				});
			}, function(data) {
			});
			
			$("#prodcd3").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd3").val();
				Sunline.ajaxRouter("fcadept/qonede", input, "POST", function(data) {
					$("#period1").val(data.period);
					$("#minrat1").val(data.minrat);
					$("#maxrat1").val(data.maxrat);
					$("#balamo1").val(data.balamo);
					$("#adbalm1").val(data.adbalm);
				}, function(data) {
				});
			});
			$("#btn-update-debt").modal('show');
			return false;
		});
		
		// 修改规则按钮
		$("#m_save_debt1").bind("click", function() {
			var input  = {};
			input.prodcd = $("#prodcd3").val();
			input.period = $("#period1").val();
			input.minrat = $("#minrat1").val();
			input.maxrat = $("#maxrat1").val();
			input.balamo = $("#balamo1").val();
			input.adbalm = $("#adbalm1").val();
			Sunline.ajaxRouter("fcadept/updept", input, "POST", function(data) {
				if(data.retCode=="0000"){
					$("#btn-update-debt").modal('hide');
					$("#btn-update").modal('hide');
					$.ajax({
						type : "POST",
						url : Sunline.getBasePath() + "/path/dept/deptInfo",
						success : function(data) {
							$("#main-content").html(data);
						},
						statusCode : {
							404 : function() {
								var err = Sunline.getBasePath() + "/error/404";
								$("#main-content").load(err);
							}
						}
					});
				}else{
					bootbox.alert("修改失败，"+data.retMsg);
				}
			}, function(data) {
			});
			return false;
		});
		
		// 修改范围窗口
		$("#update-limt").bind("click", function() {
			var input  = {};
			Sunline.ajaxRouter("fcadept/qrdepr", input, "POST", function(data) {
				$("#prodcd4").select2({
					data : data.data,
					formatSelection : function(item) {
						return  item.text+ "[" + item.id + "]";
					},
					formatResult : function(item) {
						return  item.text+ "[" + item.id + "]";
					}
				});
			}, function(data) {
			});
			
			$("#prodcd4").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd4").val();
				Sunline.ajaxRouter("fcadept/qrlimt", input, "POST", function(data) {
					$("#balnam1").select2({
						data : data.data,
						formatSelection : function(item) {
							return  item.text+ "[" + item.id + "]";
						},
						formatResult : function(item) {
							return  item.text+ "[" + item.id + "]";
						}
					});
				}, function(data) {
				});
			});
			
			$("#balnam1").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd4").val();
				input.limtsq = $("#balnam1").val();
				Sunline.ajaxRouter("fcadept/qonelm", input, "POST", function(data) {
					$("#minbal1").val(data.minbal);
					$("#maxbal1").val(data.maxbal);
				}, function(data) {
				});
			});
			$("#btn-update-limt").modal('show');
			return false;
		});
		
		// 修改范围按钮
		$("#m_save_limt1").bind("click", function() {
			var input  = {};
			input.prodcd = $("#prodcd4").val();
			input.limtsq = $("#balnam1").val();
			input.maxbal = $("#maxbal1").val();
			input.minbal = $("#minbal1").val();
			Sunline.ajaxRouter("fcadept/uplmit", input, "POST", function(data) {
				if(data.retCode=="0000"){
					$("#btn-update-limt").modal('hide');
					$("#btn-update").modal('hide');
					$.ajax({
						type : "POST",
						url : Sunline.getBasePath() + "/path/dept/deptInfo",
						success : function(data) {
							$("#main-content").html(data);
						},
						statusCode : {
							404 : function() {
								var err = Sunline.getBasePath() + "/error/404";
								$("#main-content").load(err);
							}
						}
					});
				}else{
					bootbox.alert("修改失败，"+data.retMsg);
				}
			}, function(data) {
			});
			return false;
		});
		
	}
	var clickDelete = function(){
		// 删除窗口
		$("#del_prod_btn").bind("click", function() {
			$("#btn-delete").modal('show');
			return false;
		});
		// 删除规则窗口
		$("#delete-debt").bind("click", function() {
			var input  = {};
			Sunline.ajaxRouter("fcadept/qrdepr", input, "POST", function(data) {
				$("#prodcd5").select2({
					data : data.data,
					formatSelection : function(item) {
						return  item.text+ "[" + item.id + "]";
					},
					formatResult : function(item) {
						return  item.text+ "[" + item.id + "]";
					}
				});
			}, function(data) {
			});
			
			$("#prodcd5").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd5").val();
				Sunline.ajaxRouter("fcadept/qonede", input, "POST", function(data) {
					$("#period2").val(data.period);
					$("#minrat2").val(data.minrat);
					$("#maxrat2").val(data.maxrat);
					$("#balamo2").val(data.balamo);
					$("#adbalm2").val(data.adbalm);
				}, function(data) {
				});
			});
			$("#btn-delete-debt").modal('show');
			return false;
		});
		
		// 删除规则按钮
		$("#m_dele_debt").bind("click", function() {
			var input  = {};
			input.prodcd = $("#prodcd5").val();
			Sunline.ajaxRouter("fcadept/dedebt", input, "POST", function(data) {
				if(data.retCode=="0000"){
					$("#btn-delete-debt").modal('hide');
					$("#btn-delete").modal('hide');
					$.ajax({
						type : "POST",
						url : Sunline.getBasePath() + "/path/dept/deptInfo",
						success : function(data) {
							$("#main-content").html(data);
						},
						statusCode : {
							404 : function() {
								var err = Sunline.getBasePath() + "/error/404";
								$("#main-content").load(err);
							}
						}
					});
				}else{
					bootbox.alert("删除失败，"+data.retMsg);
				}
			}, function(data) {
			});
			return false;
		});
		
		// 删除范围窗口
		$("#delete-limt").bind("click", function() {
			var input  = {};
			Sunline.ajaxRouter("fcadept/qrdepr", input, "POST", function(data) {
				$("#prodcd6").select2({
					data : data.data,
					formatSelection : function(item) {
						return  item.text+ "[" + item.id + "]";
					},
					formatResult : function(item) {
						return  item.text+ "[" + item.id + "]";
					}
				});
			}, function(data) {
			});
			
			$("#prodcd6").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd6").val();
				Sunline.ajaxRouter("fcadept/qrlimt", input, "POST", function(data) {
					$("#balnam2").select2({
						data : data.data,
						formatSelection : function(item) {
							return  item.text+ "[" + item.id + "]";
						},
						formatResult : function(item) {
							return  item.text+ "[" + item.id + "]";
						}
					});
				}, function(data) {
				});
			});
			
			$("#balnam2").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd6").val();
				input.limtsq = $("#balnam2").val();
				Sunline.ajaxRouter("fcadept/qonelm", input, "POST", function(data) {
					$("#minbal2").val(data.minbal);
					$("#maxbal2").val(data.maxbal);
				}, function(data) {
				});
			});
			$("#btn-delete-limt").modal('show');
			return false;
		});
		
		// 删除范围按钮
		$("#m_dele_limt").bind("click", function() {
			var input  = {};
			input.prodcd = $("#prodcd6").val();
			input.limtsq = $("#balnam2").val();
			Sunline.ajaxRouter("fcadept/dedeli", input, "POST", function(data) {
				if(data.retCode=="0000"){
					$("#btn-delete-limt").modal('hide');
					$("#btn-delete").modal('hide');
					$.ajax({
						type : "POST",
						url : Sunline.getBasePath() + "/path/dept/deptInfo",
						success : function(data) {
							$("#main-content").html(data);
						},
						statusCode : {
							404 : function() {
								var err = Sunline.getBasePath() + "/error/404";
								$("#main-content").load(err);
							}
						}
					});
				}else{
					bootbox.alert("删除失败，"+data.retMsg);
				}
			}, function(data) {
			});
			return false;
		});
		
		// 删除比例窗口
		$("#delete-rate").bind("click", function() {
			var input  = {};
			Sunline.ajaxRouter("fcadept/qrdepr", input, "POST", function(data) {
				$("#prodcd7").select2({
					data : data.data,
					formatSelection : function(item) {
						return  item.text+ "[" + item.id + "]";
					},
					formatResult : function(item) {
						return  item.text+ "[" + item.id + "]";
					}
				});
			}, function(data) {
			});
			$("#prodcd7").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd7").val();
				Sunline.ajaxRouter("fcadept/qrlimt", input, "POST", function(data) {
					$("#balnam3").select2({
						data : data.data,
						formatSelection : function(item) {
							return  item.text+ "[" + item.id + "]";
						},
						formatResult : function(item) {
							return  item.text+ "[" + item.id + "]";
						}
					});
				}, function(data) {
				});
			});

			$("#balnam3").on("change", function() {
				var input  = {};
				input.prodcd = $("#prodcd7").val();
				input.limtsq = $("#balnam3").val();
				Sunline.ajaxRouter("fcadept/qonera", input, "POST", function(data) {
					$("#ratevl2").select2({
						data : data.data,
						formatSelection : function(item) {
							return  item.text+ "[" + item.id + "]";
						},
						formatResult : function(item) {
							return  item.text+ "[" + item.id + "]";
						}
					});
				}, function(data) {
				});
			});
			$("#btn-delete-rate").modal('show');
			return false;
		});
		
		// 删除比例按钮
		$("#m_dele_rate").bind("click", function() {
			var input  = {};
			input.prodcd = $("#prodcd7").val();
			input.limtsq = $("#balnam3").val();
			input.ratevl = $("#ratevl2").val();
			Sunline.ajaxRouter("fcadept/derate", input, "POST", function(data) {
				if(data.retCode=="0000"){
					$("#btn-delete-rate").modal('hide');
					$("#btn-delete").modal('hide');
					$.ajax({
						type : "POST",
						url : Sunline.getBasePath() + "/path/dept/deptInfo",
						success : function(data) {
							$("#main-content").html(data);
						},
						statusCode : {
							404 : function() {
								var err = Sunline.getBasePath() + "/error/404";
								$("#main-content").load(err);
							}
						}
					});
				}else{
					bootbox.alert("删除失败，"+data.retMsg);
				}
			}, function(data) {
			});
			return false;
		});
	}
	return {
		init : function(){
			handlerTable();
			clickInsert();
			clickUpdate();
			clickDelete();
		}
	}
}()