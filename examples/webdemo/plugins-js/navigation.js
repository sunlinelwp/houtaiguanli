var navigation=function(){
	//添加元素方法
	var _navigationWrite=function(){
	var _nav=$("#nav");	 
	_nav.append("<div class='container-fluid'><ul class='nav navbar-nav'>"+
			 
			//第一个下拉框开始
			"<li class='dropdown'>"+
		    "<a class='dropdown-toggle' data-toggle='dropdown'>基础插件API<span class='caret'></span></a>"+
			"<ul id='index-menu' class='dropdown-menu' role='menu'>"+
			     "<li><a tabindex='-1' href='font-awesome.html'>font-awesome</a></li>"+
		         "<li><a tabindex='-1' href='simple-line-icons.html' >simple-line-icons</a></li>"+
			     "<li class='divider'></li>"+
			     "<li><a tabindex='-1' href='bootstrap-switch.html'>bootstrap-switch</a></li>"+
			     "<li><a tabindex='-1' href='bootstrap-hover-dropdown.html'>bootstrap-hover-dropdown</a></li>"+
			     "<li class='divider'></li>"+
			     "<li><a tabindex='-1' href='#'>jquery-ui</a></li>"+
				 "<li><a tabindex='-1' href='#'>jquery-slimscroll</a></li>"+
				 "<li><a tabindex='-1' href='#'>jquery-blockui</a></li>"+
				 "<li><a tabindex='-1' href='jquery-cookie.html'>jquery-cookie</a></li>"+
				 "<li><a tabindex='-1' href='uniform.html'>jquery-uniform</a></li>"+
				 "<li><a tabindex='-1' href='jquery-json.html'>jquery-json</a></li>"+
				 "<li><a tabindex='-1' href='bootstrap-sessiontimeout.html'>sessiontimeout</a></li>"+
			"</ul></li>"+
			//第一个下拉框结束
			//第二下拉框开始
			"<li><ul/>"+
			  "<a class='dropdown-toggle' data-toggle='dropdown'>工具插件API<span class='caret'></span></a>"+
				"<ul id='index-menu' class='dropdown-menu' role='menu'>"+
				 "<li><a tabindex='-1' href='holder.html'>holder</a></li>"+
				 "<li><a tabindex='-1' href='jquery-sparkline.html'>jquery-sparkline</a></li>"+
				 "<li><a tabindex='-1' href='jquery-zoom.html'>jquery-zoom</a></li>"+
				 "<li><a tabindex='-1' href='mockjax.html'>mockjax</a></li>"+
				 "<li><a tabindex='-1' href='moment.html'>moment</a></li>"+
				 "<li><a tabindex='-1' href='respond.html'>respond</a></li>"+
				 "<li><a tabindex='-1' href='bootstrap-touchspin.html'>bootstrap-touchspin</a></li>"+
				 "<li><a tabindex='-1' href='bootstrap-colorpicker .html'>bootstrap-colorpicker</a></li>"+
				 "<li><a tabindex='-1' href='bootstrap-datepaginator.html'>bootstrap-datepaginator</a></li>"+	
				 "<li><a tabindex='-1' href='bootstrap-datepicker.html'>bootstrap-datepicker</a></li>"+	
				 "<li><a tabindex='-1' href='bootstrap-daterangepicker.html'>bootstrap-daterangepicker</a></li>"+	
				 "<li><a tabindex='-1' href='bootstrap-datetimepicker.html'>bootstrap-datetimepicker</a></li>"+	
				 "<li><a tabindex='-1' href='typeahead.html'>typeahead</a></li>"+
				 "<li><a tabindex='-1' href='bootbox.html'>bootbox</a></li>"+
				 "<li><a tabindex='-1' href='bootpage.html'>bootpage</a></li>"+	
				 "<li><a tabindex='-1' href='jstree.html'>jstree</a></li>"+	
				 "<li><a tabindex='-1' href='flot.html'>flot</a></li>"+				 
			   "</ul></li>"+
			   //第二个下拉框结束
			   //第三个下拉框开始
			   "<li><ul/>"+
				  "<a class='dropdown-toggle' data-toggle='dropdown'>表单插件API<span class='caret'></span></a>"+
					"<ul id='index-menu' class='dropdown-menu' role='menu'>"+
					 
					 "<li><a tabindex='-1' href='jquery-file-upload.html'>jquery-file-upload</a></li>"+
					 "<li><a tabindex='-1' href='jquery-multi-select.html'>jquery-multi-select</a></li>"+
					 "<li><a tabindex='-1' href='jquery-nestable.html'>jquery-nestable</a></li>"+
					 "<li><a tabindex='-1' href='select2.html'>select2</a></li>"+
					 "<li><a tabindex='-1' href='jquery-tags-input.html'>jquery-tags-input</a></li>"+
					 "<li><a tabindex='-1' href='jquery-validate.html'>jquery-validate</a></li>"+
					 "<li><a tabindex='-1' href='rateit.html'>rateit</a></li>"+	 
					 "<li><a tabindex='-1' href='bootstrap-maxlength.html'>bootstrap-maxlength</a></li>"+
					 "<li><a tabindex='-1' href='bootstrap-fileinput.html'>bootstrap-fileinput</a></li>"+
				   "</ul></li>"+
				   //第三个下拉框结束
				   //第四个下拉框开始
				   "<li><ul/>"+
					  "<a class='dropdown-toggle' data-toggle='dropdown'>特效插件API<span class='caret'></span></a>"+
						"<ul id='index-menu' class='dropdown-menu' role='menu'>"+
						 "<li><a tabindex='-1' href='jquery-notific8.html'>jquery-notific8</a></li>"+
						 "<li><a tabindex='-1' href='backstretch.html'>backstretch</a></li>"+
						 "<li><a tabindex='-1' href='bootstrap-wysihtml5.html'>bootstrap-wysihtml5</a></li>"+
						 "<li><a tabindex='-1' href='bootstrap-wizard.html'>bootstrap-wizard</a></li>"+	 
						 "<li><a tabindex='-1' href='jqvmap.html'>jqvmap</a></li>"+
						 "<li><a tabindex='-1' href='bootstrap-editable.html'>bootstrap-editable</a></li>"+	
						 "<li><a tabindex='-1' href='bootstrap-toastr.html'>bootstrap-toastr</a></li>"+
					 "</ul></li>"+
					   //第四个下拉框结束
					 //第五个下拉框开始
					   "<li><ul/>"+
						  "<a class='dropdown-toggle' data-toggle='dropdown'>bootstrap插件API2<span class='caret'></span></a>"+
							"<ul id='index-menu' class='dropdown-menu' role='menu'>"+
							 "<li><a tabindex='-1' href='bootstrap-summernote.html'>bootstrap-summernote</a></li>"+
							 "<li><a tabindex='-1' href='bootstrap-pwstrength.html'>bootstrap-pwstrength</a></li>"+
							 "<li><a tabindex='-1' href='bootstrap-modal.html'>bootstrap-module</a></li>"+
							 "<li><a tabindex='-1' href='bootstrap-markdown.html'>bootstrap-markdown</a></li>"+
							 "<li><a tabindex='-1' href='bootstrap-fileinput.html'>bootstrap-fileinput</a></li>"+
						 "</ul></li>"+
					 //第五个下拉框结束	  
					//第6个下拉框开始
					   "<li><ul/>"+
						  "<a class='dropdown-toggle' data-toggle='dropdown'>长亮封装<span class='caret'></span></a>"+
							"<ul id='index-menu' class='dropdown-menu' role='menu'>"+
							 "<li><a tabindex='-1' href='sunline.html'>sunline</a></li>"+
						 "</ul></li>"+
					 //第6个下拉框结束	
					
			"</ul></div>");	
	}
	return{
		init: function(){
			_navigationWrite();
		}		
	}
	
	
}();