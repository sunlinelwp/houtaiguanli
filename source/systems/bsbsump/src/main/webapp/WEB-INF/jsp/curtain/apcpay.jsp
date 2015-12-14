<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">通联渠道代付差错处理及清算</span>
				<span class="caption-helper">通联渠道代付差错处理及清算</span>
			</div>
		</div>
		<div class="portlet-body">
			<!-- BEGIN FILE FORM -->
			<form class="file-form form-horizontal" id = "file-form">  
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label class="control-label col-md-4">对账日期</label>
						<div class="input-group col-md-8 input-small date date-picker" data-date-format="yyyymmdd" data-date-viewmode="years" data-date-minviewmode="months">
							<input type="text" id = "check-date" class="form-control" name="checkdate">
							<span class="input-group-btn">
							<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label class="control-label col-md-4">处理状态</label>
						<div class="input-group col-md-8">
							<input type="hidden" id="checkS" name="checkS" class="form-control input-inline input-small" placeholder="选择处理状态"/>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label class="control-label col-md-4">清算账户</label>
						<div class="input-group col-md-8">
							<input id="inacno" name="inacno" class="form-control input-inline" placeholder="清算账户"/>
						</div>
					</div>
				</div>
				<div class="form-actions file-action col-md-3">
					<button type="button" class="btn blue" id="submit">导入差错信息</button>
				</div>
			</form>
			<!-- END FILE FORM -->
			<div class="clean"></div>
		</div>
		<div class="portlet-body">
		 <div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="cppchk_ajax">
					<thead>
						<tr role="row" class="heading">	
							<th width="2%">
								<input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes"/>
							</th>
							<th width="8%">
								核对日期
							</th>
							<th width="8%">
								商户日期
							</th>
							<th width="8%">
								订单号
							</th>
							<th width="6%">
								核心日期
							</th>
							<th width="100">
								核心流水
							</th>
							<th width="8%">
								收款账号
							</th>
							<th width="8%">
								金额
							</th>
							<th width="6%">
								 手续费
							</th>
							<th width="8%">
								对账结果
							</th>
							<th width="8%">
								验签结果
							</th>
							<th width="8%">
								关键要素
							</th>
							<th width="8%">
								处理状态
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
	   </div>
	   <div class="portlet-body">
	   		<form class="file-form form-horizontal" id = "sum-form">
	   			<div class="col-md-3">
					<div class="form-group row">
						<label class="control-label col-md-4">总资金：</label>
						<div class="input-group col-md-8">
							<div id="allsum"></div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group row">
						<label class="control-label col-md-4">费用：</label>
						<div class="input-group col-md-8">
							<div id="fee"></div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group row">
						<label class="control-label col-md-5">清算状态：</label>
						<div class="input-group col-md-7">
							<div id="c_status"></div>
						</div>
					</div>
				</div>
				<div class="col-md-3 aligh-right" style="display:none">
					<button type="button" class="btn blue" disabled = "disabled" id="cel_in">清  算</button>
				</div>
	   		</form>
		</div>
		<div id="ordrModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title">订单信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="ordr_form">
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
												<input type="text" id="ordrid" name="ordrid" readOnly class="form-control input-inline input-medium" maxlength="40" placeholder="输入订单号"onkeyup="value=value.replace(/[\W]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))">
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
								</div>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
									<button type="submit" class="btn blue" id="btn_save_edit1">保存</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		<div class="clean_t"></div>
	</div>
</div>
<div id="myModal" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" >
	<div class="modal-body">
		<img alt="" src="${ctx}/assets/global/img/loading-spinner-grey.gif"><span>处理中</span>
	</div>
</div>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/curtain/scripts/apcpay.js"></script>
<script>
	jQuery(document).ready(function() {    
		Apcpay.init();
	});
</script>