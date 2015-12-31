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
						<input type="text" id="custac" name="custac" class="form-control input-inline input-medium" maxlength="10" placeholder="输入电子帐号或平台名称">
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
				<table class="table table-striped table-bordered table-hover" id="">
					<tr>
						<td>电子账户</td>
						<td><span id = "acctno"></span></td>
						<td>借款记录</td>
						<td><span id = "loanrd"></span></td>
						<td>投资记录</td>
						<td><span id = "invest"></span></td>
					</tr>
					<tr>
						<td>账户总额</td>
						<td><span id = "amount"></span></td>
						<td>可用余额</td>
						<td><span id = "usable"></span></td>
						<td>冻结金额</td>
						<td><span id = "freeze"></span></td>
					</tr>
					<tr>
						<td>总收益</td>
						<td><span id = "earsum"></span></td>
						<td>累计净收益</td>
						<td><span id = "rateen"></span></td>
						<td>其他收益</td>
						<td><span id = "othear"></span></td>
					</tr>
					<tr>
						<td>已收总额</td>
						<td><span id = "hassum"></span></td>
						<td>已收本金</td>
						<td><span id = "haspal"></span></td>
						<td>已收利息</td>
						<td><span id = "hasint"></span></td>
					</tr>
					<tr>
						<td>待收总额</td>
						<td><span id = "forsum"></span></td>
						<td>待收本金</td>
						<td><span id = "forpal"></span></td>
						<td>待收利息</td>
						<td><span id = "forpay"></span></td>
					</tr>
					<tr>
						<td>已还总额</td>
						<td><span id = "repsum"></span></td>
						<td>已还本金</td>
						<td><span id = "reppal"></span></td>
						<td>已还利息</td>
						<td><span id = "reppay"></span></td>
					</tr>
					<tr>
						<td>待还总额</td>
						<td><span id = "foesum"></span></td>
						<td>待还本金</td>
						<td><span id = "foepal"></span></td>
						<td>待还利息</td>
						<td><span id = "foePay"></span></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="tranModal" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" data-width = "1100">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">客户交易明细</h4>
	</div>
	<div class="modal-body">
		<form class="form-horizontal cust-tran-form" id="cust-tran-form" role="form">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group col-md-4">
						<label class="control-label col-md-3">帐号</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-credit-card"></i>
							<input type="text" id="tran_custac" name="tran_custac" class="form-control input-inline input-small" maxlength="10" placeholder="输入电子帐号">
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="control-label col-md-3">类型</label>
						<div class="input-icon col-md-9">
							<input type="hidden" id="eccttp" name="eccttp" class="form-control input-inline input-small" placeholder="账户类型"/>
						</div>
					</div>
				<div class="form-group col-md-4">
					<label class="control-label col-md-3">日期</label>
					<div class="input-icon col-md-9">
						<div class="input-group input-large date-picker input-daterange"  data-date-format="yyyymmdd">
							<input type="text" class="form-control" id="trandt_from" name="from" placeholder="输入起始日期">
							<span class="input-group-addon">
							到 </span>
							<input type="text" class="form-control" id="trandt_to" name="to" placeholder="输入结束日期">
						</div>
					</div>
				</div>
				<div class="cif-clear"></div>
				<div class="form-actions cust-action">
					<button type="button" class="btn blue" id="tran_qry">查询</button>
					<button type="button" class="btn gray" id="tran_cancle">清空</button>
				</div>
				<div class="cif-pp"></div>
			</form>
		<div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="cif_tran_ajax">
					<thead>
						<tr role="row" class="heading">
							<th width="8%">
								交易日期
							</th>	
							<th width="8%">
								交易时间
							</th>
							<th width="10%">
								交易金额
							</th>													
							<th width="8%">
								渠道号
							</th>
							<th width="9%">
								可用余额
							</th>
							<th width="10%">
								摘要描述
							</th>
							<th width="8%">
								流水号
							</th>
							<th width="8%">
								资金流动类型
							</th>
							<th width="8%">
								借贷标志
							</th>
							<th width="6%">
								 交易标志
							</th>
							<th width="6%">
								 交易码
							</th>	
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
	</div>
</div>
<script src="${ctx}/assets/global/plugins/jquery-validation/js/acdInput.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/custservice/scripts/custInfo.js"></script>
<script>
	jQuery(document).ready(function() {    
	});
</script>