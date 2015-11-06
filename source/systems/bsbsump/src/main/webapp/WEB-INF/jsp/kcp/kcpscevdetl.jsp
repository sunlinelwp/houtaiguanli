<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<!-- Begin: life time stats -->
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift font-green-sharp"></i> <span
				class="caption-subject font-green-sharp bold uppercase">场景事件明细表</span>
			<span class="caption-helper">管理场景事件明细表配置...</span>
		</div>
	</div>
	<div class="portlet-body">
		<div class="table-container">
			<div class="table-actions-wrapper">
				<span> </span>
				<button id="add_detl_btn"
					class="btn btn-sm green table-group-action-submit">
					<i class="fa fa-plus"></i> 新增
				</button>
			</div>
			<table class="table table-striped table-bordered table-hover"
				id="datatable_detl">
				<thead>
					<tr role="row" class="heading">
						<th scope="col">场景代码</th>
						<th scope="col">场景描述</th>
						<th scope="col">模块</th>
						<th scope="col">事件编号</th>
						<th scope="col">维度类别</th>
						<th scope="col">维度值</th>
						<th scope="col">事件用途</th>
						<th scope="col">备注信息</th>
						<th scope="col" colspan="2">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- edit modal -->
		<div id="editdetlModal" class="modal fade" tabindex="-1"
			data-backdrop="static" data-keyboard="false" data-width="1100">
			<div >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">场景事件明细表属性</h4>
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
											<label class="col-md-6 control-label">场景代码</label>
											<div class="col-md-6">
												<input type="text" name="scencd"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="场景代码">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">场景描述</label>
											<div class="col-md-6">
												<input type="text" name="scends"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="场景描述">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">模块</label>
											<div class="col-md-6">
												<input type="text" name="module"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="模块">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">事件编号</label>
											<div class="col-md-6">
												<input type="text" name="evetcd"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="事件编号">
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-6 control-label">维度类别</label>
											<div class="col-md-6">
												<input type="text" name="dimecg"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="维度类别">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">维度值</label>
											<div class="col-md-6">
												<input type="text" name="dimevl"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="维度值">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">事件用途</label>
											<div class="col-md-6">
												<input type="text" name="evetus"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="事件用途">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-6 control-label">备注信息</label>
											<div class="col-md-6">
												<input type="text" name="remark"
													class="form-control input-inline input-medium form-value"
													maxlength="10" placeholder="备注信息">
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
					<button type="button" class="btn blue" id="btn_save_detl">保存</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/pages/kcp/scripts/kcpscevdetl.js"></script>
<script>
	jQuery(document).ready(function() {
		kcpscevdetl.init();
	});
</script>