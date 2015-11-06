<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<!-- Begin: life time stats -->
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i> <span
					class="caption-subject font-green-sharp bold uppercase">贷款产品分类</span>
				<span class="caption-helper">管理贷款产品分类配置...</span>
			</div>
		</div>
		<div class="portlet-body">
			<div class="table-container">
				<div class="table-actions-wrapper">
					<span> </span>
					<button id="add_grup_btn"
						class="btn btn-sm green table-group-action-submit">
						<i class="fa fa-plus"></i> 新增
					</button>
				</div>
				<table class="table table-striped table-bordered table-hover"
					id="datatable_grup">
					<thead>
						<tr role="row" class="heading">
							<th scope="col">产品代码</th>
							<th scope="col">产品名称</th>
							<th scope="col">产品组代码</th>
							<th scope="col">产品组名称</th>
							<th scope="col" colspan="2">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<!-- edit modal -->
			<div id="editgrupModal" class="modal fade" tabindex="-1"
				data-backdrop="static" data-keyboard="false" data-width="800">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">贷款产品分类</h4>
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
											<label class="col-md-3 control-label">产品代码</label>
											<div class="col-md-9">
												<input type="text" name="prodcd"
													class="form-control input-inline input-medium form-value"
													readOnly placeholder="产品代码">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">产品名称</label>
											<div class="col-md-9">
												<input type="text" name="prodna"
													class="form-control input-inline input-medium form-value"
													placeholder="产品名称">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-md-3 control-label">产品组代码</label>
											<div class="col-md-9">
												<input type="text" name="grupcd"
													class="form-control input-inline input-medium form-value"
													placeholder="产品组代码">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">产品组名称</label>
											<div class="col-md-9">
												<input type="text" name="grupna"
													class="form-control input-inline input-medium form-value"
													placeholder="产品组名称">
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
					<button type="button" class="btn blue" id="btn_save_grup">保存</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/pages/loan/scripts/lnsubobj.js"></script>
<script src="${ctx}/pages/loan/scripts/lnfgrup.js"></script>
<script>
	$(document).ready(function() {
		lnsubobj.setparam("lnfgrup", lnfgrup);
	});
</script>