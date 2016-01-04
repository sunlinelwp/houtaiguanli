<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<!-- Begin: life time stats -->
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift font-green-sharp"></i> <span
				class="caption-subject font-green-sharp bold uppercase">手续费信息查询</span>
			<span class="caption-helper">手续费信息查询</span>
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
						<th width="10%">前置日期</th>
						<th width="10%">前置流水</th>
						<th width="10%">渠道编码</th>
						<th width="10%">渠道名称</th>
						<th width="10%">支付方式</th>
						<th width="10%">生效日期</th>
						<th width="10%">账户属性</th>
						<th width="10%">收费类型</th>
						<th width="10%">计费起始金额</th>
						<th width="10%">计费最大金额</th>
						<th width="10%">最低费用</th>
						<th width="10%">最高费用</th>
						<th width="10%">收费比例</th>
						<th width="15%" colspan="2" style="text-align: center;">操作</th>
					</tr>
					<tr role="row" class="filter">
						<td></td>
						<td></td>
						<td></td>
						<td><input type="text"
							class="form-control form-filter input-sm" id="n_chnlnm"
							name="n_chnlnm" placeholder="渠道名称" /></td>
						<td><input type="hidden"
							class="form-control form-filter input-sm" id="n_pytype"
							name="n_pytype" placeholder="支付方式" /></td>
						<td><input type="text"
							class="form-control form-filter input-sm" id="n_yin"
							style="display: none;" name="n_yin" /></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="2" nowrap="nowrap">
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
									<label class="col-md-3 control-label">前置日期</label>
									<div class="col-md-9">
										<input type="text" id="orfbdt" name="orfbdt"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入渠道编码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">前置流水</label>
									<div class="col-md-9">
										<input type="text" id="orfbsq" name="orfbsq"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入渠道编码">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">渠道编码</label>
									<div class="col-md-9">
										<input type="text" id="chnlcd" name="chnlcd"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入渠道编码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">渠道名称</label>
									<div class="col-md-9">
										<input type="text" id="chnlnm" name="chnlnm"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入渠道名称">
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
									<label class="col-md-3 control-label">生效日期</label>
									<div class="col-md-9">
										<input type="text" id="efctdt" name="efctdt"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入生效日期">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">账户属性</label>
									<div class="col-md-9">
										<input type="hidden" id="acctpp" name="acctpp"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入账户属性">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">收费类型</label>
									<div class="col-md-9">
										<input type="hidden" id="chgetp" name="chgetp"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入收费类型">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">计费起始金额</label>
									<div class="col-md-9">
										<input type="text" id="staram" name="staram"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入计费起始金额">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">计费最大金额</label>
									<div class="col-md-9">
										<input type="text" id="termam" name="termam"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入计费最大金额">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">最低费用</label>
									<div class="col-md-9">
										<input type="text" id="lowfee" name="lowfee"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入最低费用">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">最高费用</label>
									<div class="col-md-9">
										<input type="text" id="higfee" name="higfee"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入最高费用">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">收费比例</label>
									<div class="col-md-9">
										<input type="text" id="rateit" name="rateit"
											class="form-control input-inline input-medium" maxlength="20"
											placeholder="输入收费比例">
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
<script src="${ctx}/pages/paychannel/scripts/counterfeement.js"></script>
<script>
	jQuery(document).ready(function() {
		kuprole.init();
	});
</script>
