<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<!-- Begin: life time stats -->
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift font-green-sharp"></i> <span
				class="caption-subject font-green-sharp bold uppercase">接入渠道信息查询</span>
			<span class="caption-helper">接入渠道信息查询</span>
		</div>
	</div>
	<div class="portlet-body">
		<div class="table-container">

			<div class="table-actions-wrapper">
				<span> </span>
				<button id="add_btn"
					class="btn btn-sm green table-group-action-submit">
					<i class="fa fa-plus"></i> 新增
				</button>
			</div>
			<table class="table table-striped table-bordered table-hover"
				id="datatable_ajax">
				<thead>
					<tr role="row" class="heading">
						<th width="10%">渠道编码</th>
						<th width="10%">渠道名称</th>
						<th width="10%">渠道状态</th>
						<th width="10%">创建时间</th>
						<th width="10%">创建人</th>
						<th width="10%">修改日期</th>
						<th width="10%">修改人</th>
						<th width="10%">备注</th>
						<th width="15%" colspan="2">操作</th>
					</tr>
					<tr role="row" class="filter">
						<td></td>
						<td><input type="text"
							class="form-control form-filter input-sm" id="n_chnlnm"
							name="n_chnlnm" placeholder="渠道名称" /></td>
						<td></td>
						<td></td>
						<td><input type="text"
							class="form-control form-filter input-sm" id="n_yin"
							style="display: none;" name="n_yin" /></td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="2">
							<button class="btn btn-sm yellow filter-submit margin-bottom">
								<i class="fa fa-search"></i> 查询
							</button>
							<button class="btn btn-sm red filter-cancel">
								<i class="fa fa-times"></i> 清空
							</button>
						</td>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- edit modal -->
		<div id="editModal" class="modal fade" tabindex="-1"
			data-backdrop="static" data-keyboard="false">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">角色信息</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form" id="edit_form">
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									输入有误，请检查下面表单！返回信息 ：<span class="msg"></span>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">渠道编码</label>
									<div class="col-md-9">
										<div>
											<input type="text" id="chnlcd" name="chnlcd"
												class="form-control input-inline input-medium"
												maxlength="10" placeholder="输入渠道编码">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">渠道名称</label>
									<div class="col-md-9">
										<input type="text" id="chnlnm" name="chnlnm"
											class="form-control input-inline input-medium"
											maxlength="100" placeholder="输入渠道名称">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">渠道状态</label>
									<div class="col-md-9">
										<input type="hidden" id="status" name="status"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入渠道状态">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">备注</label>
									<div class="col-md-9">
										<input type="text" id="remark" name="remark"
											class="form-control input-inline input-medium"
											maxlength="200" placeholder="输入备注">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" data-dismiss="modal"
									class="btn btn-default">关闭</button>
								<button type="button" class="btn blue" id="btn_save_edit">保存</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End: life time stats -->
</div>
<script type="text/javascript"
	src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/paychannel/scripts/accessinformament.js"></script>
<script>
	jQuery(document).ready(function() {
		kuprole.init();
	});
</script>
