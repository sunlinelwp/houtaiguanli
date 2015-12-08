<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i> <span
					class="caption-subject font-green-sharp bold uppercase">融资产品转让规则配置</span>
				<span class="caption-helper">融资产品转让规则配置...</span>
			</div>
		</div>
		<div class="portlet-body">
			<div class="table-container">
				<div class="table-actions-wrapper">
					<button id="add_prod_btn"
						class="btn btn-sm green table-group-action-submit">
						<i class="fa fa-plus"></i> 新增
					</button>
					<button id="upd_prod_btn"
						class="btn btn-sm yellow table-group-action-submit">
						<i class="fa fa-plus"></i> 修改
					</button>
					<button id="del_prod_btn"
						class="btn btn-sm red table-group-action-submit">
						<i class="fa fa-plus"></i> 删除
					</button>
				</div>
				<table class="table table-striped table-bordered table-hover"
					id="cif_ajax">
					<thead>
						<tr role="row" class="heading">
							<th width="8%">产品编号</th>
							<th width="8%">产品名称</th>
							<th width="10%">转让期限</th>
							<th width="8%">出让综合收益下限</th>
							<th width="9%">出让综合收益上限</th>
							<th width="10%">起投金额</th>
							<th width="8%">增购起投金额</th>
							<th width="8%">主键顺序号</th>
							<th width="8%">项目剩余金额下限</th>
							<th width="8%">项目剩余金额上限</th>
							<th width="6%">比例值</th>
							<th width="6%">操作</th>
						</tr>
						<tr role="row" class="filter">
							<td><input type="text" name="q_prodcd" id="q_prodcd"
								class="form-control form-filter input-sm"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><button
									class="btn btn-sm yellow filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button></td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- 新增按钮弹出框开始 -->
<div id="btn-insert" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">新增</h4>
	</div>
	<div class="modal-body">
		<button type="button" id="insert-debt" class="btn blue">新增转让规则</button>
		<button type="button" id="insert-limt" class="btn blue">新增转让范围</button>
		<button type="button" id="insert-rate" class="btn blue">新增转让比例</button>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
	</div>
</div>
<!-- 新增按钮弹出框结束 -->

<!-- 新增转让规则开始 -->
<div id="btn-insert-debt" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">新增转让规则</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">产品</label>
					<div class="input-icon col-md-9">
						<input type="text" id="prodcd" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">转让期限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="period" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">出让综合收益下限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="minrat" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">出让综合收益上限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="maxrat" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">起投金额</label>
					<div class="input-icon col-md-9">
						<input type="text" id="balamo" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">增购起投金额</label>
					<div class="input-icon col-md-9">
						<input type="text" id="adbalm" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_save_debt" class="btn blue">保存</button>
	</div>
</div>
<!-- 新增转让规则结束 -->

<!-- 新增转让范围开始 -->
<div id="btn-insert-limt" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">新增转让范围</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">产品</label>
					<div class="input-icon col-md-9">
						<input type="text" id="prodcd1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额下限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="minbal" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额上限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="maxbal" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_save_limt" class="btn blue">保存</button>
	</div>
</div>
<!-- 新增转让范围结束 -->

<!-- 新增转让比例开始 -->
<div id="btn-insert-rate" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">新增转让比例</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">产品</label>
					<div class="input-icon col-md-9">
						<input type="text" id="prodcd2" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额区间</label>
					<div class="input-icon col-md-9">
						<input type="text" id="balnam" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">比例值</label>
					<div class="input-icon col-md-9">
						<input type="text" id="ratevl" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_save_rate" class="btn blue">保存</button>
	</div>
</div>
<!-- 新增转让比例结束 -->

<!-- 修改按钮弹出框开始 -->
<div id="btn-update" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">修改</h4>
	</div>
	<div class="modal-body">
		<button type="button" id="update-debt" class="btn yellow">修改转让规则</button>
		<button type="button" id="update-limt" class="btn yellow">修改转让范围</button>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
	</div>
</div>
<!-- 修改按钮弹出框结束 -->

<!-- 修改转让规则开始 -->
<div id="btn-update-debt" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">修改转让规则</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">产品</label>
					<div class="input-icon col-md-9">
						<input type="text" id="prodcd3" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">转让期限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="period1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">出让综合收益下限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="minrat1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">出让综合收益上限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="maxrat1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">起投金额</label>
					<div class="input-icon col-md-9">
						<input type="text" id="balamo1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">增购起投金额</label>
					<div class="input-icon col-md-9">
						<input type="text" id="adbalm1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_save_debt1" class="btn blue">保存</button>
	</div>
</div>
<!-- 修改转让规则结束 -->

<!-- 修改转让范围开始 -->
<div id="btn-update-limt" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">修改转让范围</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">产品</label>
					<div class="input-icon col-md-9">
						<input type="text" id="prodcd4" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额区间</label>
					<div class="input-icon col-md-9">
						<input type="text" id="balnam1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额下限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="minbal1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额上限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="maxbal1" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_save_limt1" class="btn blue">保存</button>
	</div>
</div>
<!-- 修改转让范围结束 -->

<!-- 删除按钮弹出框开始 -->
<div id="btn-delete" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">删除</h4>
	</div>
	<div class="modal-body">
		<button type="button" id="delete-debt" class="btn red">删除转让规则</button>
		<button type="button" id="delete-limt" class="btn red">删除转让范围</button>
		<button type="button" id="delete-rate" class="btn red">删除转让比例</button>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
	</div>
</div>
<!-- 删除按钮弹出框结束 -->

<!-- 删除转让规则开始 -->
<div id="btn-delete-debt" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">删除转让规则</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">产品</label>
					<div class="input-icon col-md-9">
						<input type="text" id="prodcd5" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">转让期限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="period2" name="m_ecctno"
							class="form-control input-inline input-medium" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">出让综合收益下限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="minrat2" name="m_ecctno"
							class="form-control input-inline input-medium" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">出让综合收益上限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="maxrat2" name="m_ecctno"
							class="form-control input-inline input-medium" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">起投金额</label>
					<div class="input-icon col-md-9">
						<input type="text" id="balamo2" name="m_ecctno"
							class="form-control input-inline input-medium" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">增购起投金额</label>
					<div class="input-icon col-md-9">
						<input type="text" id="adbalm2" name="m_ecctno"
							class="form-control input-inline input-medium" readonly>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_dele_debt" class="btn red">删除</button>
	</div>
</div>
<!-- 删除转让规则结束 -->

<!-- 删除转让范围开始 -->
<div id="btn-delete-limt" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">删除转让范围</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">产品</label>
					<div class="input-icon col-md-9">
						<input type="text" id="prodcd6" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额区间</label>
					<div class="input-icon col-md-9">
						<input type="text" id="balnam2" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额下限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="minbal2" name="m_ecctno"
							class="form-control input-inline input-medium" readonly>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额上限</label>
					<div class="input-icon col-md-9">
						<input type="text" id="maxbal2" name="m_ecctno"
							class="form-control input-inline input-medium" readonly>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_dele_limt" class="btn blue">删除</button>
	</div>
</div>
<!-- 删除转让范围结束 -->

<!-- 删除转让比例开始 -->
<div id="btn-delete-rate" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">删除转让比例</h4>
	</div>
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">产品</label>
					<div class="input-icon col-md-9">
						<input type="text" id="prodcd7" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">项目剩余金额区间</label>
					<div class="input-icon col-md-9">
						<input type="text" id="balnam3" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">比例值</label>
					<div class="input-icon col-md-9">
						<input type="text" id="ratevl2" name="m_ecctno"
							class="form-control input-inline input-medium">
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		<button type="button" id="m_dele_rate" class="btn blue">删除</button>
	</div>
</div>
<!-- 删除转让比例结束 -->
<script
	src="${ctx}/assets/global/plugins/jquery-validation/js/acdInput.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/dept/scripts/deptInfo.js"></script>
<script>
	jQuery(document).ready(function() {
		deptInfo.init();
	});
</script>