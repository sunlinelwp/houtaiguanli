<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<!-- Begin: life time stats -->
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift font-green-sharp"></i> <span
				class="caption-subject font-green-sharp bold uppercase">接入渠道配置信息查询</span>
			<span class="caption-helper">接入渠道配置信息查询</span>
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
						<th width="10%">接入渠道编码</th>
						<th width="10%">接入渠道名称</th>
						<th width="10%">支付方式</th>
						<th width="10%">前端规定银行代码</th>
						<th width="10%">银行名称</th>
						<th width="10%">支付渠道编码</th>
						<th width="10%">支付渠道名称</th>
						<th width="10%">配置信息是否生效</th>
						<th width="15%" colspan="2">操作</th>
					</tr>
					<tr role="row" class="filter">
						<td></td>
						<td><input type="text"
							class="form-control form-filter input-sm" id="n_chnlnm"
							name="n_chnlnm" placeholder="渠道名称" /></td>
						<td><input type="hidden"
							class="form-control form-filter input-sm" id="n_pytype"
							name="n_pytype" placeholder="支付方式" /></td>
						<td></td>
						<td><input type="text"
							class="form-control form-filter input-sm" id="n_banknm"
							name="n_banknm" placeholder="银行名称" /></td>
						<td><input type="text"
							class="form-control form-filter input-sm" id="n_yin"
							style="display: none;" name="n_yin" /></td>
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
									<label id="leixing" class="col-md-3 control-label" >修改类型</label>
									<div id ="leixing1" class="col-md-9" >
										<input type="hidden" id="transt" name="transt"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入修改类型">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">接入渠道编码</label>
									<div class="col-md-9">
										<div>
											<input type="text" id="inchnl" name="inchnl"
												class="form-control input-inline input-medium"
												maxlength="10" placeholder="输入接入渠道编码">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">支付方式</label>
									<div class="col-md-9">
										<input type="hidden" id="pytype" name="pytype"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入支付方式">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">前端规定银行代码</label>
									<div class="col-md-9">
										<input type="text" id="bankcd" name="bankcd"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入前端规定银行代码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">支付渠道编码</label>
									<div class="col-md-9">
										<input type="text" id="pychnl" name="pychnl"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入支付渠道编码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">配置信息是否生效</label>
									<div class="col-md-9">
										<input type="hidden" id="status" name="status"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入配置信息是否生效">
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
<script src="${ctx}/pages/paychannel/scripts/accessconfigment.js"></script>
<script>
	jQuery(document).ready(function() {
		kuprole.init();
	});
</script>
