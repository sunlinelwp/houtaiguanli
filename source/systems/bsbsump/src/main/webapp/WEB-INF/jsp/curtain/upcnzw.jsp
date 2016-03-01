<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">橙牛调账</span>
				<span class="caption-helper">橙牛调账操作...</span>
			</div>
		</div>
		<div class="portlet-body">
			<!-- BEGIN SINGLEDEBT FORM -->
			<form class="intran-form"  >  
				<div class="col-md-4">
					<div class="form-group">
						<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
						<label class="control-label">电子帐号：</label>
						<div class="input-icon">
							<i class="fa fa-credit-card"></i>
							<input class="form-control placeholder-no-fix acctno" type="text" maxlength="10" autocomplete="off" placeholder="电子帐号" id="acctno" name="acctno"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label">交易金额:</label>
						<div class="input-icon">
							<i class="fa fa-cny"></i>
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="交易金额,保留两位有效数字" id="tranam" name="tranam"/>
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
<script src="${ctx}/assets/global/scripts/recheck.js" type="text/javascript"></script>
<script src="${ctx}/pages/curtain/scripts/upcnzw.js" type="text/javascript" ></script>
<script>
	jQuery(document).ready(function() {    
		Upcnzw.init();
	});
</script>