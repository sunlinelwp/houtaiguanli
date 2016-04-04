<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<!-- Begin: life time stats -->
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i> <span
					class="caption-subject font-green-sharp bold uppercase">查询报表</span>
				<span class="caption-helper">查询理财产品信息报表</span>
			</div>
		</div>
		<div class="portlet-body">
			<form class="form-horizontal control-label" role="form"
				id="fund_report">
				<div class="form-body">
					<div class="alert alert-danger display-hide">
						<button class="close" data-close="alert"></button>
						输入有误，请检查下面表单！<span class="msg"></span>
					</div>
					<div class="alert alert-success display-hide">
						<button class="close" data-close="alert"></button>
						表单提交成功！<span class="msg"></span>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-3 control-label">用户账号</label>
							<div class="col-md-9">
							<input type="text" name="tags" id ="tags"
									class="form-control input-inline input-medium form-value"
									 placeholder="选择会员标签">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div>
							<button type="button" id="find_report" class="btn blue">查看报表</button>
							<div class="btn-group">
							  <button type="button" class="btn blue dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							   	 下载报表 <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu" id="down">
							    <li><a href="#" name="xlsx">.xlsx格式</a></li>
							  </ul>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="portlet light">
			<div class="portlet-body  profile tabbable">
				<div class="row inbox">
					<div class=" col-md-12">
						<div class="inbox-loading"></div>
						<div class="inbox-content"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/report/scripts/lccpReport.js"></script>
<script>
	jQuery(document).ready(function() {
		if (jQuery().datepicker) {
			$('.date-picker').datepicker({
				rtl : Metronic.isRTL(),
				orientation : "left",
				autoclose : true,
				language : 'zh-CN',
			});
		};
		lccpReport.init();
	});
</script>