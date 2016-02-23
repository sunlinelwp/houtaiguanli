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
			<div class="table-container">
			
				<div class="table-actions-wrapper">
					<span>
					</span>
					<!-- 
					 <button id="add_btn" class="btn btn-sm green table-group-action-submit"><i class="fa fa-plus"></i> 新增</button>
					 -->
				</div>
				<table class="table table-striped table-bordered table-hover" id="datatable_ajax">
					<thead>
						<tr role="row" class="heading">
							<th width="10%">
								客户账号
							</th>
							<th width="13%">
								客户名称
							</th>
							<th width="13%">
								开户行名称
							</th>
							<th width="12%">
								交易金额
							</th>
							<th width="8%">
								前置流水
							</th>
							<th width="8%">
								前置日期
							</th>
						<!-- 
						 	<th width="13%">
								业务编码
							</th>
							<th width="6%">
								银行行号
							</th>
						 -->
							<th width="6%">
								银行卡号
							</th>
						<!-- 
							<th width="6%">
								账户名称
							</th>
							<th width="6%">
								账号类型
							</th>
							<th width="6%">
								币种
							</th>
						-->
							<th width="6%">
								证件号
							</th>
						<!-- 
							<th width="6%">
								证件类型
							</th>
						-->
							<th width="6%">
								电话号码
							</th>
						<!-- 
							<th width="6%">
								手续费
							</th>
							<th width="6%">
								审核原因
							</th>
						-->
							<th width="10%">
								状态
							</th>
							<th width="8%">
								前置时间
							</th>
							<th width="24" colspan="2">
								 操作
							</th> 
						</tr>
						<tr role="row" class="filter">
							<td>
							</td>
							<td>
							</td>
							<td>
							</td>
							<td>
							</td>
							<td>
							</td>
							<td>
								<input type="text" class="form-control form-filter input-sm" id="q_frondt_sign" name="q_frondt_sign" /> 
							</td>
							<!-- 
							<td>
							</td>
							<td>
							</td>
							 -->
							<td>
							</td>
							<!-- 
							<td>
							</td>
							<td>
							</td>
							<td>
							</td>
							 -->
							<td>
							</td>
							<!-- 
							<td>
							</td>
							 -->
							<td>
							</td>
							<!-- 
							<td>
							</td>
							<td>
							</td>
							 -->
							<td>
								<input type="text" class="form-control form-filter input-sm" id="q_status" name="q_status" /> 		
							</td>
							<td>
							</td>
							<td  colspan="2">
								<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> 查询</button>
								<button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> 清空</button>
							</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
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
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/cust/scripts/knlbillsign.js"></script>
<script>
	jQuery(document).ready(function() {    
		knlbillsign.init();
	});
</script>
