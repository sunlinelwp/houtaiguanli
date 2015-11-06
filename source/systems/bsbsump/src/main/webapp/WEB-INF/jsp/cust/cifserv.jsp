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
					<label class="col-md-3 control-label">电子帐号</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-credit-card"></i>
						<input type="text" id="custac" name="custac" class="form-control input-inline input-medium" maxlength="10" placeholder="输入电子帐号">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">证件号码</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-credit-card"></i>
						<input type="text" id="idcard" name="idcard" class="form-control input-inline input-medium" maxlength="18" placeholder="输入证件号码">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">姓名</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-book"></i>
						<input type="text" id="custna" name="custna" class="form-control input-inline input-medium" maxlength="19" placeholder="输入姓名">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">手机号码</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-mobile-phone"></i>
						<input type="text" id="phone" name="phone" class="form-control input-inline input-medium" maxlength="11" placeholder="输入手机号码">
					</div>
				</div>
				<div class="cif-clear"></div>
				<div class="form-actions cust-action">
					<button type="button" class="btn blue" id="submit">查询</button>
					<button type="button" class="btn gray" id="cancle">清空</button>
				</div>
				<div class="cif-pp"></div>
			</form>
			 <div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="cif_ajax">
					<thead>
						<tr role="row" class="heading">
							<th width="2%">
								
							</th>	   		
							<th width="8%">
								电子帐号
							</th>	
							<th width="8%">
								客户名称
							</th>
							<th width="10%">
								证件号码
							</th>													
							<th width="8%">
								手机号码
							</th>
							<th width="9%">
								邮箱
							</th>
							<th width="10%">
								地址
							</th>
							<th width="8%">
								内部卡号
							</th>
							<th width="8%">
								绑定卡号
							</th>
							<th width="8%">
								账户状态
							</th>
							<th width="8%">
								开户日期
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- 修改操作员弹出窗口 -->
<div id="editModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">客户信息修改</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal mod-form" id="mod-form" role="form">
					<div class="alert alert-danger display-hide">
						<button class="close" data-close="alert"></button>
						<span>输入交易参数有误</span>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">电子帐号</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-credit-card"></i>
							<input type="text" id="m_ecctno" name="m_ecctno" class="form-control input-inline input-medium" readOnly>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">账户名称</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-credit-card"></i>
							<input type="text" id="m_ecctna" name="m_ecctna" class="form-control input-inline input-medium" readOnly>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">通讯地址</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-map-marker"></i>
							<input type="text" id="m_addr" name="m_addr" class="form-control input-inline input-medium" maxlength="100" >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">邮件地址</label>
						<div class=" input-icon col-md-9">
							<i class="fa fa-envelope-o"></i>
							<input type="text" id="m_email" name="m_email" class="form-control input-inline input-medium" maxlength="19" placeholder="输入邮件地址">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">账户状态</label>
						<div class="input-icon col-md-9">
							<input type="hidden" id="m_acctst" name="m_acctst" class="form-control input-inline input-medium" placeholder="账户状态"/>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_save" class="btn blue">保存</button>
	</div>
</div>
<div id="myModal" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" data-width = "300">
	<div class="modal-body">
		<img alt="" src="${ctx}/assets/global/img/loading-spinner-grey.gif"><span>处理中</span>
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
<script src="${ctx}/pages/cust/scripts/cifserv.js"></script>
<script>
	jQuery(document).ready(function() {    
		Cifserv.init();
	});
</script>