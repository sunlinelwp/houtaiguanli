<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">科目记账</span>
				<span class="caption-helper">科目记账操作...</span>
			</div>
		</div>
		<div class="portlet-body">
			<!-- BEGIN SINGLEDEBT FORM -->
			<form class="itmact-form form-horizontal"  >  
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
						<label class="control-label col-md-3">科目号：</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-credit-card"></i>
							<input class="form-control placeholder-no-fix itemcd" type="text" maxlength="18" autocomplete="off" placeholder="科目号" id="itemcd" name="itemcd"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-3 control-label">机构号</label>
							<div class="input-icon col-md-9">
								<div>
									<input type="text" name="brchno" id = "brchno"
										class="form-control input-inline input-medium form-value"
										maxlength="8" placeholder="选择机构号">
								</div>
							</div>
						</div>
					</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">交易金额:</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-cny"></i>
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="交易金额,保留两位有效数字" id="tranam" name="tranam"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">借贷标志</label>
						<div class="input-icon col-md-9">
							<input type="hidden" id="amntcd" name="amntcd" class="form-control input-inline input-medium" placeholder="选择借贷标志"/>
						</div>
					</div>
				</div>
				<div class="padd"></div>
				<div class="form-actions intran-action">
					<button type="button" class="btn blue" id="submit">确认</button>
					<button type="button" class="btn gray" id="cancle">清空</button>
				</div>
				
			</form>
			<!-- END SINGLEDEBT FORM -->
		</div>
	</div>
</div>
<div id="myModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<i class="fa fa-cogs font-green-sharp"></i>
		<span class="caption-subject font-green-sharp bold uppercase">转账结果</span>
	</div>
	<div class="modal-body">
	</div>
</div>
<script src="${ctx}/pages/curtain/scripts/itmact.js" type="text/javascript" ></script>
<script>
	jQuery(document).ready(function() {    
		Itmact.init();
	});
</script>