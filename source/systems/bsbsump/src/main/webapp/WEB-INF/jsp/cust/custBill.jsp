<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="scroller"   style="overflow-y:scroll;height:600px;" id="editmodal">
	<div class="modal-body">
		<form class="form-horizontal cust-tran-form" id="cust-tran-form" role="form">
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span>输入交易参数有误</span>
				</div>
				<div class="form-group col-md-4">
						<label class="control-label col-md-3">帐号</label>
						<div class="input-icon col-md-9">
							<i class="fa fa-credit-card"></i>
							<input type="text" id="tran_custac" name="tran_custac" class="form-control input-inline input-small" maxlength="10" placeholder="输入电子帐号">
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="control-label col-md-3">类型</label>
						<div class="input-icon col-md-9">
							<input type="hidden" id="eccttp" name="eccttp" class="form-control input-inline input-small" placeholder="账户类型"/>
						</div>
					</div>
				<div class="form-group col-md-4">
					<label class="control-label col-md-3">日期</label>
					<div class="input-icon col-md-9">
						<div class="input-group input-large date-picker input-daterange"  data-date-format="yyyymmdd">
							<input type="text" class="form-control" id="trandt_from" name="from" placeholder="输入起始日期">
							<span class="input-group-addon">
							到 </span>
							<input type="text" class="form-control" id="trandt_to" name="to" placeholder="输入结束日期">
						</div>
					</div>
				</div>
				<div class="cif-clear"></div>
				<div class="form-actions cust-action">
					<button type="button" class="btn blue" id="tran_qry">查询</button>
					<button type="button" class="btn gray" id="tran_cancle">清空</button>
				</div>
				<div class="cif-pp"></div>
			</form>
		<div class="table-container">
				<table class="table table-striped table-bordered table-hover" id="cif_tran_ajax">
					<thead>
						<tr role="row" class="heading">
							<th width="8%">
								交易日期
							</th>	
							<th width="8%">
								交易时间
							</th>
							<th width="10%">
								交易金额
							</th>													
							<th width="8%">
								渠道号
							</th>
							<th width="9%">
								可用余额
							</th>
							<th width="10%">
								摘要描述
							</th>
							<th width="8%">
								流水号
							</th>
							<th width="8%">
								资金流动类型
							</th>
							<th width="8%">
								借贷标志
							</th>
							<th width="6%">
								 交易标志
							</th>
							<th width="6%">
								 交易码
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
<script src="${ctx}/pages/cust/scripts/custBill.js"></script>
<script>
</script>
