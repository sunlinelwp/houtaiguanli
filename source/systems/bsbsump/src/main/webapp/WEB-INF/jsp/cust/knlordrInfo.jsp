<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<!-- Begin: life time stats -->
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">订单信息列表</span>
				<span class="caption-helper">订单信息...</span>
			</div>
		</div>
		<div class="portlet-body">
			<div class="form-group">
				<label class="col-md-6" style = "margin-top:5px">已录入金额:&nbsp;&nbsp;<span id="inptam" style = "font-size:18px;font-weight:700;color:blue;"></span></label> 
				<label class="col-md-6" style = "margin-top:5px">总金额:&nbsp;&nbsp;<span id="totlam" style = "font-size:18px;font-weight:700;color:blue;"></span></label> 
			</div>
			<div class="table-container">
			
				<div class="table-actions-wrapper">
					<span>
					</span>
					<button id="add_btn1" class="btn btn-sm green table-group-action-submit"><i class="fa fa-plus"></i> 新增</button>
				</div>
				<table class="table table-striped table-bordered table-hover" id="ordrinfo_ajax">
					<thead>
						<tr role="row" class="heading">	   		
							<th width="20%">
								订单号
							</th>	
							<th width="10%">
								订单金额
							</th>
							<th width="10%">
								清算日期
							</th>
							<th width="15%">
								手续费
							</th>
							<th width="15%">
								渠道号
							</th>
							<th width="15%">
								操作员工
							</th>
							<!-- <th width="24" colspan="2">
								 操作
							</th>  -->
						</tr>
						<tr role="row" class="filter" style="display:none">
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
							<td  colspan="2">
								<input type="hidden" id="q_fronsq" name="q_fronsq" class="form-control form-filter input-sm" maxlength="40" >
								<input type="hidden" id="q_frondt" name="q_frondt" class="form-control form-filter input-sm" maxlength="20" >
							</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<!-- edit modal -->
			<div id="editModal1" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title">订单信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="edit_form1">
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
										<label class="col-md-3 control-label">订单号</label>
										<div class="col-md-9">
											<div>
												<input type="text" id="ordrid" name="ordrid" readOnly class="form-control input-inline input-medium" maxlength="40" placeholder="输入订单号">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">订单金额</label>
										<div class="col-md-9">
											<input type="text" id="ordram" name="ordram"  class="form-control input-inline input-medium" maxlength="21" placeholder="输入订单金额" onkeyup="value=value.replace(/[^\d.]/g,'')">
										</div>
									</div>
	 								<div class="form-group">
										<label class="col-md-3 control-label">清算日期</label>
										<div class="col-md-9">
											<div class="input-group col-md-12 date input-medium date-picker"
												data-date-format="yyyymmdd" data-date-viewmode="years"
												data-date-minviewmode="months">
											<input type="text" class="form-control form-value" name="keepdt"
													maxlength="8" placeholder="输入清算日期"> <span
											class="input-group-btn">
											<button class="btn" type="button">
													<i class="fa fa-calendar"></i>
											</button>
											</span>
											</div>
										</div>
									</div>
		 
		 							<!-- <div class="form-group" style="display: none">
										<label class="col-md-3 control-label">前置流水</label>
										<div class="col-md-9">
											<input type="hidden" id="orfbsq" name="orfbsq" readOnly class="form-control input-inline input-medium" maxlength="40" >
										</div>
									</div>
									<div class="form-group" style="display: none">
										<label class="col-md-3 control-label">前置日期</label>
										<div class="col-md-9">
											<input type="hidden" id="orfbdt" name="orfbdt" readOnly class="form-control input-inline input-medium" maxlength="20" >
										</div>
									</div> -->
									<div class="form-group">
										<label class="col-md-3 control-label">手续费</label>
										<div class="col-md-9">
										   <input type="text" id="chgeam" name="chgeam" class="form-control input-inline input-medium" maxlength="21" placeholder="输入手续费" onkeyup="value=value.replace(/[^\d.]/g,'')">						
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">渠道号</label>
										<div class="col-md-9">
										   	<input type="text" id="scapno" name="scapno" class="form-control input-inline input-medium" maxlength="4" placeholder="输入渠道号">	
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">订单顺序号</label>
										<div class="col-md-9">
										   	<input type="text" id="ordrsq" name="ordrsq" class="form-control input-inline input-medium" placeholder="输入订单顺序号">	
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<input type="hidden" id="orfbsq" name="orfbsq" readOnly class="form-control input-inline input-medium" maxlength="40" >
									<input type="hidden" id="orfbdt" name="orfbdt" readOnly class="form-control input-inline input-medium" maxlength="20" >
									<input type="hidden" id="servtp" name="servtp" class="form-control input-inline input-medium" >	
									<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
									<button type="submit" class="btn blue" id="btn_save_edit1">保存</button>
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
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/cust/scripts/knlordrInfo.js"></script>

