<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<!-- Begin: life time stats -->
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">大额提现审核列表</span>
				<span class="caption-helper">大额提现审核...</span>
			</div>
		</div>
		<div class="portlet-body"> 
			<form class="form-horizontal detl-form" id="detl-form" role="form">
				<div class="form-group col-md-5">
						<label class="col-md-3 control-label">电子账号</label>
						<div class="input-icon col-md-9">
							<input type="text" id="q_custac" name="q_custac" class="form-control input-inline input-medium" maxlength="10" >
						</div>
					</div>
					<div class="form-group col-md-5">
						<label class="col-md-3 control-label">账户名称</label>
						<div class="input-icon col-md-9">
							<input type="text" id="q_custna" name="q_custna" class="form-control input-inline input-medium" maxlength="10" >
						</div>
					</div>
					<div class="form-group col-md-5">
						<label class="col-md-3 control-label">电话号码</label>
						<div class="input-icon col-md-9">
							<input type="text" id="q_teleno" name="q_teleno" class="form-control input-inline input-medium" maxlength="11" >
						</div>
					</div>
					<div class="form-group col-md-5">
						<label class="col-md-3 control-label">渠道号</label>
						<div class="input-icon col-md-9">
							<input type="text" id="q_servno" name="q_servno" class="form-control input-inline input-medium" maxlength="10" >
						</div>
					</div>
					<div class="form-group col-md-5">
						<label class="col-md-3 control-label">状态</label>
						<div class="input-icon col-md-9">
							<input type="text" id="q_status" name="q_status" class="form-control input-inline input-medium" maxlength="10" placeholder="请选择">
						</div>
					</div>
					<div class="form-group col-md-7">
						<label class="col-md-2 control-label">日期</label>
						<div class="col-md-5">
							<div class="input-group col-md-12 date input-medium date-picker"
								data-date-format="yyyymmdd" data-date-viewmode="years"
								data-date-minviewmode="months" id="indate">
								<input type="text" class="form-control" name="q_frondt" id="q_frondt"
									maxlength="8" placeholder="输入开始日期"> <span
									class="input-group-btn">
									<button class="btn default" type="button">
										<i class="fa fa-calendar"></i>
									</button>
								</span>
							</div>
							</div>
							<div class="col-md-5">
							<div class="input-group col-md-12 date input-medium date-picker"
								data-date-format="yyyymmdd" data-date-viewmode="years"
								data-date-minviewmode="months" id="eddate">
								<input type="text" class="form-control" name="q_enddat" id="q_enddat"
									maxlength="8" placeholder="输入结束日期"> <span
									class="input-group-btn">
									<button class="btn default" type="button">
										<i class="fa fa-calendar"></i>
									</button>
								</span>
							</div>
							</div>
						</div>
				<div class="form-actions cust-action col-md-12">
					<button type="button" class="btn blue" id="" onclick="knlbillsigns.queryDetl()">查询</button>
					<button type="button" class="btn gray" id="cancle">清空</button>
				</div>
				
			</form>
			<div class="form-group col-md-5">
						<label class="col-md-3 control-label"></label>
						<div class="input-icon col-md-9">
						</div>
					</div>
			<div class="table-container">
				<div class="table-actions-wrapper">
						 <button id="download" class="btn btn-sm green table-group-action-submit"><i class="fa fa-plus"></i> 导出数据</button>
				</div>
				<table class="table table-striped table-bordered table-hover" id="datatable_ajax">
					<thead>
						<tr role="row" class="heading">
							<th scope="col">
								客户账号
							</th>
							<th scope="col">
								客户名称
							</th>
							<th scope="col">
								开户行名称
							</th>
							<th scope="col">
								交易金额
							</th>
							<th scope="col">
								可用金额
							</th>
							<th scope="col">
								前置流水
							</th>
							<th scope="col">
								前置日期
							</th>
							<th scope="col">
								银行卡号
							</th>
							<th scope="col">
								电话号码
							</th>
							<th scope="col">
								状态
							</th>
							<th scope="col">
								前置时间
							</th>
							<th scope="col">
								渠道号
							</th>
							<th width="24" colspan="2">
								 操作
							</th> 
						</tr>
					</thead>
				</table>
			</div>
			
			<!-- edit modal -->
			<div id="editModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title">审核信息</h4>
				</div>
				<div class="modal-body">
					<div class="row profile">
						<ul class="nav nav-tabs">
							<li class="active tab_1"><a href="#tab_1" data-toggle="tab">
									审核 </a></li>
						</ul>
						<div class="col-md-12">
							<div class="tab-content">
								<div class="tab-pane tab_1 active">
								<form class="form-horizontal control-label" role="form" id="knlbillsign" name="knlbillsign">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										输入有误，请检查下面表单！返回信息 ：<span class="msg"></span>
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										表单提交成功！返回信息 ：<span class="msg"></span>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">客户账号</label>
										<div class="col-md-9">
											<input type="text" id="custac" name="custac" class="form-control input-inline input-medium">
										</div>									
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">客户名称</label>
										<div class="col-md-9">
											<input type="text" id="custna" name="custna" class="form-control input-inline input-medium">
										</div>									
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">前置流水</label>
										<div class="col-md-9">
											<input type="text" id="fronsq" name="fronsq" class="form-control input-inline input-medium">
										</div>									
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">前置日期</label>
										<div class="col-md-9">
											<input type="text" id="frondt" name="frondt" class="form-control input-inline input-medium">
										</div>									
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">审核状态</label>
										<div class="col-md-9">
											<input type="text" id="ckstat" name="ckstat" class="form-control input-inline input-medium">
										</div>									
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">备注信息</label>
										<div class="col-md-9">
											<input type="text" id="remark" name="remark" class="form-control input-inline input-medium" maxlength="200" placeholder="输入备注信息">
											<input type="text" id="teleno" name="teleno" class="form-control input-inline input-medium"  style="display: none;">
											<input type="text" id="tranam" name="tranam" class="form-control input-inline input-medium"  style="display: none;">
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
									<button type="button" class="btn blue" id="btn_save_edit">保存</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> 
	
	
	<div id="tranModal" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" data-width = "1100" style="height:620px;">
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
							<input type="text" readOnly="readOnly" id="tran_custac" name="tran_custac" class="form-control input-inline input-small" maxlength="10">
						</div>
					</div>
				<div class="form-group col-md-4">
				</div>
				<div class="cif-clear"></div>
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

			<!-- 新增订单信息 -->
			<div id="addOrdrModal" class="modal fade out" tabindex="-1"
			data-backdrop="static" data-keyboard="false" data-width="1280">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title">订单信息</h4>
				</div>
				<div class="modal-body"id="edit_load">
				</div> 
			</div>
			<!-- 新增订单信息结束 -->
	<!-- End: life time stats -->
	</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/cust/scripts/knlbillsign.js"></script>
<script>
	jQuery(document).ready(function() {    
		knlbillsigns.init();
	});
</script>
