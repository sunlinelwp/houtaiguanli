<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">客户信息</span>
				<span class="caption-helper">客户基本信息...</span>
			</div>
		</div>
		<div class="portlet-body">
			<form class="form-horizontal cust-form" id="cust-form" role="form">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">要查询的信息</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-credit-card"></i>
						<input type="text" id="custac" name="custac" class="form-control input-inline input-medium" maxlength="35" placeholder="输入电子帐号或平台名称">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">手机号码</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-credit-card"></i>
						<input type="text" id="phoneNo" name="phoneNo" class="form-control input-inline input-medium" maxlength="11" placeholder="输入手机号码">
					</div>
				</div>
				<div class="form-actions cust-action">
					<button type="button" class="btn blue" id="submit" onclick="custInfo.queryCust()">查询</button>
					<button type="button" class="btn gray" id="cancle">清空</button>
				</div>
				<div class="cif-pp"></div>
			</form>
			 <div class="table-container">
			 	<div class="table-actions-wrapper">
					<button id="add_prod_btn"
						class="btn btn-sm green table-group-action-submit">
						<i class="fa fa-plus"></i> 导出EXCEL
					</button>
				</div>
			 	<table class="table table-striped table-bordered table-hover"
					id="datatable_cust">
					<thead>
						<tr role="row" class="heading">
							<th scope="col">电子账户</th>
							<th scope="col">借款记录</th>
							<th scope="col">投资记录</th>
							<th scope="col">账户总额</th>
							<th scope="col">可用余额</th>
							<th scope="col">冻结金额</th>
							<th scope="col">总收益</th>
							<th scope="col">累计净收益</th>
							<th scope="col">其他收益</th>
							<th scope="col">已收总额</th>
							<th scope="col">已收本金</th>
							<th scope="col">已收利息</th>
							<th scope="col">待收总额</th>
							<th scope="col">待收本金</th>
							<th scope="col">待收利息</th>
							<th scope="col">已还总额</th>
							<th scope="col">已还本金</th>
							<th scope="col">已还利息</th>
							<th scope="col">待还总额</th>
							<th scope="col">待还本金</th>
							<th scope="col">待还利息</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/assets/global/plugins/jquery-validation/js/acdInput.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/custservice/scripts/custInfo.js"></script>
<script>
	jQuery(document).ready(function() {
		custInfo.init();
	});
</script>