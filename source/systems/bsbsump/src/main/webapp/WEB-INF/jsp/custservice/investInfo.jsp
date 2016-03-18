<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12" onclick = "investInfo.clickWindow()">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">投资信息</span>
				<span class="caption-helper">投资信息...</span>
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
						<input type="text" id="custac" name="custac" class="form-control input-inline input-medium" maxlength="10" placeholder="输入电子帐号">
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
					<button type="button" class="btn blue" id="submit" onclick="investInfo.queryCust()">查询</button>
					<button type="button" class="btn gray" id="cancle">清空</button>
				</div>
				<div class="cif-pp"></div>
			</form>
			 <div class="table-container">
			<!-- 	<div class="form-group col-md-6">
					<label class="col-md-3 control-label">总投入本金</label>
					<div class="input-icon col-md-9">
						<span></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">总收益</label>
					<div class="input-icon col-md-9">
						<span></span>
					</div>
				</div> -->
				<table class="table table-striped table-bordered table-hover"
					id="datatable_prod">
					<thead>
						<tr role="row" class="heading">
							<th scope="col">标的编号</th>
							<th scope="col">标题</th>
							<th scope="col">投资金额</th>
							<th scope="col">发布时间</th>
							<th scope="col">预期收益</th>
							
							
							<th scope="col" style="display:none;">顺序号</th>
							<th scope="col" style="display:none;">还款方式</th>
							<th scope="col" style="display:none;">实际年利率</th>
							<th scope="col" style="display:none;">期限</th>
							<th scope="col" style="display:none;">分录编号</th>
							<th scope="col" style="display:none;">状态</th>
							<th scope="col" >起息日期</th>
							<th scope="col" >到期日期</th>
							<th scope="col" style="display:none;">已到帐收益</th>
							<th scope="col" style="display:none;">待收本金</th>
							<th scope="col" style="display:none;">借据号</th>
							<th scope="col" style="display:none;">借款时间</th>
							
							
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
<div id="tranModal" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" data-width = "1100">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">投资详细信息</h4>
	</div>
	<div class="modal-body">
		<div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="cif_tran_ajax">
					<tr>
						<td>顺序号</td>
						<td><span id = "sequen"></span></td>
						<td>标题</td>
						<td><span id = "suname"></span></td>
						<td>投资金额</td>
						<td><span id = "amount"></span></td>
					</tr>
					<tr>
						<td>还款方式</td>
						<td><span id = "repsta"></span></td>
						<td>实际年利率</td>
						<td><span id = "anrate"></span></td>
						<td>期限</td>
						<td><span id = "loterm"></span></td>
					</tr><tr>
						<td>发布时间</td>
						<td><span id = "sttime"></span></td>
						<td>标的编号</td>
						<td><span id = "sunumb"></span></td>
						<td>分录编号</td>
						<td><span id = "journo"></span></td>
					</tr><tr>
						<td>状态</td>
						<td><span id = "itstat"></span></td>
						<td>起息日期</td>
						<td><span id = "begndt"></span></td>
						<td>到期日期</td>
						<td><span id = "termdt"></span></td>
					</tr><tr>
						<td>预计收益</td>
						<td><span id = "forpay"></span></td>
						<td>已到账收益</td>
						<td><span id = "hasint"></span></td>
						<td>待收本金</td>
						<td><span id = "ndrcpr"></span></td>
					</tr><tr>
						<td>借据号</td>
						<td><span id = "lncfno"></span></td>
						<td>借款时间</td>
						<td><span id = "lotime"></span></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
	</div>
</div>
<script src="${ctx}/pages/custservice/scripts/investInfo.js"></script>
<script>
	jQuery(document).ready(function() {    
		investInfo.init();
	});
</script>