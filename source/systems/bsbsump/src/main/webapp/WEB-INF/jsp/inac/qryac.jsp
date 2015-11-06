<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">内部户信息</span>
				<span class="caption-helper">内部户信息...</span>
			</div>
		</div>
		<div class="portlet-body">
		 <div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="inac_ajax">
					<thead>
						<tr role="row" class="heading">			   		
							<th width="10%">
								内部户帐号
							</th>	
							<th width="14%">
								帐号名称
							</th>
							<th width="6%">
								子户号
							</th>													
							<th width="8%">
								表内外标志
							</th>
							<th width="9%">
								余额方向
							</th>
							<th width="10%">
								借方余额(元)
							</th>
							<th width="10%">
								贷方余额(元)
							</th>
							<th width="8%">
								开户日期
							</th>
							<!--  <th>上日余额日期</th>
							<th>上日余额方向</th>
							<th>上日借方余额</th>
							<th>上日贷方余额</th>-->
							<th width="22%" colspan="4">
								 操作
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
	   </div>
	</div>
	
	<!-- 修改操作员弹出窗口 -->
	<div id="editModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">内部户信息修改</h4>
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
							<label class="col-md-3 control-label">内部户帐号</label>
							<div class="col-md-9">
								<input type="text" id="m_acctno" name="m_acctno" class="form-control input-inline input-medium" maxlength="19" readOnly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">内部户名称</label>
							<div class="col-md-9">
								<input type="text" id="m_acctna" name="m_acctna" class="form-control input-inline input-medium" maxlength="19" placeholder="输入新内部户名称">
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
	<!-- 新增内部户弹出窗口 -->
	<div id="addModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">内部户开户</h4>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN SINGLEDEBT FORM -->
					<form class="add-form form-horizontal" id="add-form" role="form">  
						<div class="alert alert-danger display-hide">
							<button class="close" data-close="alert"></button>
							<span>输入交易参数有误</span>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">科目号</label>
							<div class="input-icon col-md-9">
								<i class="fa fa-table"></i>
								<input class="form-control input-inline input-medium" type="text" maxlength="18" autocomplete="off" placeholder="请输入科目号" id="a_itemcd" name="a_itemcd"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">账户名称</label>
							<div class="input-icon col-md-9">
								<i class="fa fa-user"></i>
								<input class="form-control input-inline input-medium" type="text" autocomplete="off" maxlength="18" placeholder="请输入内部户名称" id="a_itemna" name="a_itemna"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">币种</label>
							<div class="input-icon col-md-9">
								 <select id="a_crcycd">
							        <option value="01" selected>人民币</option>
							        <option value="12">英镑</option>
							        <option value="13">港币</option>
							        <option value="14">美元</option>
							        <option value="15">瑞士法郎</option>
							        <option value="27">日元</option>
							        <option value="28">加拿大元</option>
							        <option value="29">澳大利亚元</option>
							        <option value="18">新加坡元</option>
							        <option value="38">欧元</option>
							        <option value="43">韩元</option>
							       	<option value="81">澳门元</option>
							        <option value="CN">本外合计</option>
							        <option value="FC">外折人民币</option>
							        <option value="US">折合美元</option>
							    </select>
							</div>
						</div>
					</form>
					<!-- END SINGLEDEBT FORM -->
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
			<button type="button" class="btn blue" id="add_save">保存</button>
		</div>
	</div>
	<div id="lastBillModal" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" data-width = "700">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">上日余额信息</h4>
		</div>
		<div class="modal-body">
			<table id="lastBill" class="table table-striped table-bordered table-hover"></table>
		</div>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
		</div>
	</div>
	<div class="inbox-content"></div>
</div>

<script src="${ctx}/pages/inac/scripts/qryac.js"></script>
<script>
	jQuery(document).ready(function() {    
		Qryac.init();
	});
</script>