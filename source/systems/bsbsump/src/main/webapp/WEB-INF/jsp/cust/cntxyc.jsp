<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">橙牛提现异常信息</span>
				<span class="caption-helper">橙牛提现异常信息...</span>
			</div>
		</div>
		<div class="portlet-body">
			 <div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="cty_ajax">
					<thead>
						<tr role="row" class="heading">	   	
							<th scope="col">
								电子帐号
							</th>	
							<th scope="col">
								标的编号
							</th>
							<th scope="col">
								交易金额
							</th>													
							<th scope="col">
								提现状态
							</th>
							<th scope="col">
								交易日期
							</th>
							<th scope="col">
								交易顺序号
							</th>
							<th scope="col">
								异常信息
							</th>
							<th scope="col">
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
</div>
<script src="${ctx}/assets/global/plugins/jquery-validation/js/acdInput.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/pages/cust/scripts/cntxyc.js"></script>
<script>
	jQuery(document).ready(function() {    
		Cntxyc.init();
	});
</script>