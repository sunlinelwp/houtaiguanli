<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12" onclick = "tranferInfo.clickWindow()">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">转让信息</span>
				<span class="caption-helper">转让信息...</span>
			</div>
		</div>
		<div class="portlet-body">
			<form class="form-horizontal cust-form" id="cust-form" role="form">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">电子帐号</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-credit-card"></i>
						<input type="text" id="custac" name="custac" class="form-control input-inline input-medium" maxlength="10" placeholder="输入电子帐号">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">手机号</label>
					<div class="input-icon col-md-9">
						<i class="fa fa-credit-card"></i>
						<input type="text" id="teleno" name="teleno" class="form-control input-inline input-medium" maxlength="10" placeholder="输入手机号">
					</div>
				</div>
				
				
				<div class="form-actions cust-action">
					<button type="button" class="btn blue" id="submit" onclick="tranferInfo.queryCust()">查询</button>
					<button type="button" class="btn gray" id="cancle">清空</button>
				</div>
				<div class="cif-pp"></div>
			</form>
			 <div class="table-container">
			<!-- 	<div class="form-group col-md-6">
					<label class="col-md-3 control-label">总投入本金</label>
					<div class="input-icon col-md-9">
						<span></span>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-md-3 control-label">总收益</label>
					<div class="input-icon col-md-9">
						<span></span>
					</div>
				</div> -->
				<table class="table table-striped table-bordered table-hover"
					id="datatable_prod">
					<thead>
						<tr role="row" class="heading">
							<th scope="col"></th>
							<th scope="col">借款标题</th>
							<th scope="col">转让总额(元)</th>
							<th scope="col">剩余期限</th>
							<th scope="col">转出金额(元)</th>
							<th scope="col">投资金额(元)</th>
							<th scope="col">收益(元)</th>
							
							
							
							 
							
							
						
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="tranModal" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" data-width = "1100">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">转让详细信息</h4>
	</div>
	<div class="modal-body">
		<div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="cif_tran_ajax">
				
					<tr>
						<td>标的编号</td>
						<td><span id = "subjcd"></span></td>
						<td>借款标题</td>
						<td><span id = "subjnm"></span></td>
						<td>货币代码</td>
						<td><span id = "crcycd"></span></td>
					</tr>
					
					<tr>
						<td>转让总额</td>
						<td><span id = "quamou"></span></td>
						<td>剩余期限</td>
						<td><span id = ""></span></td>
						<td>转出金额</td>
						<td><span id = "qucuam"></span></td>
					</tr>
					<tr>
						<td>投资金额</td>
						<td><span id = "trcuam"></span></td>
						<td>收益</td>
						<td><span id = "profit"></span></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>受让人</td>
						<td><span id = ""></span></td>
						<td>受让金额</td>
						<td><span id = "profit"></span></td>
						<td>转让日期</td>
						<td></td>
					</tr>
					<!-- 
					<tr>
						<td>账户余额</td>
						<td><span id = "onlnbl"></span></td>
						<td>待收本金</td>
						<td><span id = "ndrcpr"></span></td>
						<td>待收利息</td>
						<td><span id = "ndrcin"></span></td>
					</tr>
					<tr>
						<td>已收本金</td>
						<td><span id = "rcpram"></span></td>
						<td>已收利息</td>
						<td><span id = "rcinam"></span></td>
						<td>标的状态</td>
						<td><span id = "subjst"></span></td>
					</tr>
					<tr>
						<td>收益利率</td>
						<td><span id = "instrt"></span></td>
						<td>顺序号</td>
						<td><span id = "sequen"></span></td>
						<td>起息日期</td>
						<td><span id = "instdt"></span></td>
					</tr><tr>
						<td>到期日期</td>
						<td><span id = "matudt"></span></td>
						<td>投资金额</td>
						<td><span id = "inveam"></span></td>
						<td>待收本息</td>
						<td><span id = "ndrcam"></span></td>
					</tr><tr>
						<td>转让总额</td>
						<td><span id = "quamou"></span></td>
						<td>转出金额</td>
						<td><span id = "qucuam"></span></td>
						<td>投资金额</td>
						<td><span id = "trcuam"></span></td>
					</tr><tr>
						<td>收益</td>
						<td><span id = "profit"></span></td>
						<td>状态</td>
						<td><span id = "transt"></span></td>
						<td>转让标的编号</td>
						<td><span id = "oldjcd"></span></td>
					</tr><tr>
						<td>转让标的名称</td>
						<td><span id = "newjnm"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					 -->
				</table>
			</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
	</div>
</div>

<!-- 转让记录 -->
<div id="tranferhistory" class="modal fade bs-modal-sm" tabindex="-1" data-backdrop="static" data-keyboard="false" data-width = "1100">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">转让详情</h4>
	</div>
	<div class="modal-body">
		<div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="tran_history_ajax">
					<thead>
						<tr role="row" class="heading">
							<th width="8%">
								受让人
							</th>	
							<th width="8%">
								受让金额(元)
							</th>
							<th width="10%">
								转让日期
							</th>													
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
	</div>
</div>

<script src="${ctx}/pages/custservice/scripts/tranferInfo.js"></script>
<script>
	jQuery(document).ready(function() {    
		tranferInfo.init();
	});
</script>