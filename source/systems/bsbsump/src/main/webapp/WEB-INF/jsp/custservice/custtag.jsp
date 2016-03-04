<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">客户标签查询</span>
				<span class="caption-helper">客户标签查询...</span>
			</div>
		</div>
		<div class="portlet-body">
			<form class="form-horizontal cust-form" id="cust-form" role="form">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">电子帐号</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-credit-card"></i>
						<input type="text" id="custac" name="custac" class="form-control input-inline input-medium"  placeholder="输入电子帐号/平台名称">
					</div>
				</div>
				<div class="form-group col-md-6">
				</div>
				<div class="form-actions cust-action">
					<button type="button" class="btn blue" id="submit" onclick="custtag.queryCust()">查询</button>
					<button type="button" class="btn gray" id="cancle">清空</button>
				</div>
				<div class="cif-pp"></div>
			</form>
			 <div class="table-container">
 			 	<div class="table-actions-wrapper">
					<button id="add_prod_btn"
						class="btn btn-sm green table-group-action-submit">
						<i class="fa fa-plus"></i> 新增
					</button>
				</div>
				<table class="table table-striped table-bordered table-hover"
					id="datatable_prod">
					<thead>
						<tr role="row" class="heading">
							<th scope="col">客户姓名</th>
							<th scope="col">电子账号</th>
							<th scope="col">平台名称</th>
							<th scope="col">标签</th>
							<th scope="col">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="tranModal" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">新增客户标签</h4>
	</div>
	<div class="modal-body">
		<div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="cif_tran_ajax">
					<tr>
						<td>电子账号</td>
						<td><input type="text" placeholder="输入电子账号"  class="form-control input-inline input-medium valid" name="custna" id="custna"></td>
					</tr>
					<tr>
						<td>标签号</td>
						<td><input type="text" placeholder="输入标签号"  class="form-control input-inline input-medium valid" name="tagscd" id="tagscd"></td>
					</tr>
				</table>
			</div>
	</div>
	<div class="modal-footer">
		<button type="button" id="m_save_debt" class="btn blue">保存</button>
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
	</div>
</div>
<script src="${ctx}/pages/custservice/scripts/custtag.js"></script>
<script>
	jQuery(document).ready(function() {    
		custtag.init();
	});
</script>