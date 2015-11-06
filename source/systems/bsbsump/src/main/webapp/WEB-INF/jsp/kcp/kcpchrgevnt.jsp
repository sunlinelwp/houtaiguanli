<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<!-- Begin: life time stats -->
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift font-green-sharp"></i> <span
				class="caption-subject font-green-sharp bold uppercase">事件收费控制表</span>
			<span class="caption-helper">管理事件收费控制表配置...</span>
		</div>
	</div>
	<div class="portlet-body">
		<div class="table-container">
			<div class="table-actions-wrapper">
				<span> </span>
				<button id="add_evnt_btn"
					class="btn btn-sm green table-group-action-submit">
					<i class="fa fa-plus"></i> 新增
				</button>
			</div>
			<table class="table table-striped table-bordered table-hover"
				id="datatable_evnt">
				<thead>
					<tr role="row" class="heading">
						<th scope="col">收费事件编号</th>
						<th scope="col">产品号</th>
						<th scope="col">序号</th>
						<th scope="col">交易渠道</th>
						<th scope="col">凭证种类</th>
						<th scope="col">客户类型</th>
						<th scope="col">优先级</th>
						<th scope="col">收费代码</th>
						<th scope="col">说明信息</th>
						<th scope="col" colspan="2">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- edit modal -->
		<div id="editevntModal" class="modal fade" tabindex="-1"
			data-backdrop="static" data-keyboard="false" data-width="1100">
			<div >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">事件收费控制表属性</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										输入有误，请检查下面表单！<span class="msg"></span>
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										表单提交成功！<span class="msg"></span>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-6 control-label">收费事件编号</label>
											<div class="col-md-6">
												<input type="text" name="chevno"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="收费事件编号">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">产品号</label>
											<div class="col-md-6">
												<input type="text" name="prodcd"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="产品号">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">序号</label>
											<div class="col-md-6">
												<input type="text" name="sequno"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="序号">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">交易渠道</label>
											<div class="col-md-6">
												<input type="text" name="trnchl"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="交易渠道">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">凭证种类</label>
											<div class="col-md-6">
												<input type="text" name="dcmttp"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="凭证种类">
											</div>
										</div>
									</div>
									<div class="col-md-6">
									<div class="form-group">
											<label class="col-md-6 control-label">客户类型</label>
											<div class="col-md-6">
												<input type="text" name="custtp"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="选择客户类型">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">优先级</label>
											<div class="col-md-6">
												<input type="text" name="prilvl"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="优先级">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">收费代码</label>
											<div class="col-md-6">
												<input type="text" name="chrgcd"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder=收费代码>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">说明信息</label>
											<div class="col-md-6">
												<input type="text" name="expmsg"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="说明信息">
											</div>
										</div>
									</div>
									
									
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
					<button type="button" class="btn blue" id="btn_save_evnt">保存</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/pages/kcp/scripts/kcpchrgevnt.js"></script>
<script>
	jQuery(document).ready(function() {
		kcpchrgevnt.init();
	});
</script>