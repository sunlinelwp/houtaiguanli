<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">线下充值操作</span>
				<span class="caption-helper">线下充值操作...</span>
			</div>
		</div>
		<div class="portlet-body" id="editModel">
			<!-- BEGIN SINGLEDEBT FORM -->
			<form class="adjust-form form-horizontal" id="seapon_form">  
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<!-- 
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">借方帐号</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-credit-card"></i>
							<input class="form-control placeholder-no-fix acctno" type="text" maxlength="18" autocomplete="off" placeholder="借方帐号" id="acctno" name="acctno"/>
						</div>
						<div class="acctna">帐号名称：<span class="name" id="d-acct-name"></span></div>
					</div>
				</div>
				 -->
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">贷方帐号</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-credit-card"></i>
							<input class="form-control placeholder-no-fix acctno" type="text" autocomplete="off" maxlength="18" placeholder="贷方帐号" id="acctno" name="acctno"/>
						</div>
						<div class="acctna">帐号名称：<span class="name" id="c-acct-name"></span></div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">交易金额</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-cny"></i>
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="交易金额,保留两位有效数字" id="tranam" name="tranam"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">币种</label>
						<div class="input-icon col-md-9">
							<input type="hidden" id="crcycd" name="crcycd" class="form-control input-inline input-medium" placeholder="选择币种"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">对方卡号</label>
						<div class=" col-md-9">
						    <input type="text" id="cardno" name="cardno" class="form-control input-inline input-medium" placeholder="输入卡号"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">交易费用</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-cny"></i>
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="手续费,保留两位有效数字" id="tranfe" name="tranfe" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-3">清算日期</label>
						<div class="col-md-9">
							<div class="input-group col-md-12 date input-medium date-picker"
								data-date-format="yyyymmdd" data-date-viewmode="years"
								data-date-minviewmode="months">
							<input type="text" class="form-control form-value" name="keepdt" id="keepdt"
									maxlength="8" placeholder="输入清算日期"> <span
							class="input-group-btn">
							<button class="btn" type="button">
									<i class="fa fa-calendar"></i>
							</button>
							</span>
							</div>
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
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/assets/global/scripts/recheck.js" type="text/javascript"></script>
<script src="${ctx}/pages/curtain/scripts/seapon.js"></script>
<script>
	jQuery(document).ready(function() {    
		Seapon.init();
	});
</script>