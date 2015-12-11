<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="col-md-12">
	<!-- Begin: life time stats -->
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift font-green-sharp"></i> <span
					class="caption-subject font-green-sharp bold uppercase">贷款产品还款属性表</span>
				<span class="caption-helper">管理贷款产品还款属性配置...</span>
			</div>
		</div>
		<div class="portlet-body">
			<div class="table-container">
				<div class="table-actions-wrapper">
					<span> </span>
					<button id="add_rule_btn"
						class="btn btn-sm green table-group-action-submit">
						<i class="fa fa-plus"></i> 新增
					</button>
				</div>
				<table class="table table-striped table-bordered table-hover"
					id="datatable_rule">
					<thead>
						<tr role="row" class="heading">
							<th scope="col">产品代码</th>
							<th scope="col">货币代号</th>
							<th scope="col">期供生成方式</th>
							<th scope="col">还款方式</th>
							<th scope="col">还款周期</th>
							<th scope="col">多频率还款标志</th>
							<th scope="col">还本周期</th>
							<th scope="col">默认期限</th>
							<th scope="col">逾期还款周期</th>
							<th scope="col">还款顺序编号</th>
							<th scope="col">结息方式</th>
							<th scope="col">欠款还款时段</th>
							<th scope="col">允许调整还款方式</th>
							<th scope="col">首次还款日确定模式</th>
							<th scope="col">末期还款方式</th>
							<th scope="col">自动扣款标志</th>
							<th scope="col">自动结清贷款标志</th>
							<th scope="col">允许缩期</th>
							<th scope="col">缩期最大次数</th>
							<th scope="col">不足额扣款方式</th>
							<th scope="col">逾期不足额扣款方式</th>
							<th scope="col">欠息时是否减少额度</th>
							<th scope="col">允许行内非同名帐户还款</th>
							<th scope="col">允许内部账户还款</th>
							<th scope="col">允许提前还款</th>
							<th scope="col">提前还款还息方式</th>
							<th scope="col">提前还款是否还贴息</th>
							<th scope="col">提前还款调整计划</th>
							<th scope="col">期限变更调整计划</th>
							<th scope="col">利率变更调整计划</th>
							<th scope="col">是否有宽限期</th>
							<th scope="col">本金宽限期天数</th>
							<th scope="col">利息宽限期天数</th>
							<th scope="col">宽限期最大次数</th>
							<th scope="col">宽限期计息方式</th>
							<th scope="col">宽限期后计息规则</th>
							<th scope="col">宽限期收息规则</th>
							<th scope="col">宽限期转逾期规则</th>
							<th scope="col">宽限期节假日规则</th>
							
							
							<th scope="col">等额处理规则</th>
							<th scope="col">累进值</th>
							<th scope="col">累进区间期数</th>
							<th scope="col">多还款账户标志</th>
							<th scope="col">循环贷款</th>
							<th scope="col">提前出账标志</th>
							<th scope="col">提前出账天数</th>
							<th scope="col">返还合格性编号</th>
							<th scope="col">返还方式</th>
							<th scope="col">返还频率</th>
							<th scope="col">返还计算方式</th>
							<th scope="col">返还金额或比例</th>
							<th scope="col">提前还款锁定期</th>
							<th scope="col">每年可提前还款最大次数</th>
							<th scope="col">期限内提前还款最大次数</th>
							<th scope="col">提前还款最低金额规则</th>
							<th scope="col">提前还款最低金额取值</th>
							<th scope="col">提前还款最高金额规则</th>
							<th scope="col">提前还款最高金额取值</th>
							<th scope="col">多次放款调整计划</th>
							<th scope="col">是否允许部分还款</th>
							<th scope="col">部分还款最低金额规则</th>
							<th scope="col">部分还款最低金额或比例</th>
							<th scope="col" colspan="2">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<!-- edit modal -->
			<div id="editruleModal" class="modal fade" tabindex="-1"
				data-backdrop="static" data-keyboard="false" data-width="1380">
				<div style="height: 620px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">贷款产品还款属性</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<form class="form-horizontal" role="form">
									<div class="form-body">
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
											输入有误，请检查下面表单！返回信息 ：<span class="msg"></span>
										</div>
										<div class="alert alert-success display-hide">
											<button class="close" data-close="alert"></button>
											表单提交成功！返回信息 ：<span class="msg"></span>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="col-md-3 control-label">产品代码</label>
												<div class="col-md-9">
													<input type="text" name="prodcd"
														class="form-control input-inline input-medium form-value"
														readOnly placeholder="产品代码">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">货币代号</label>
												<div class="col-md-4">
													<input type="text" name="crcycd"
														class="form-control input-inline input-medium form-value"
														placeholder="选择币种">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">期供生成方式</label>
												<div class="col-md-4">
													<input type="text" name="schdsr"
														class="form-control input-inline input-medium form-value"
														placeholder="选择期供生成方式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">还款方式</label>
												<div class="col-md-4">
													<input type="text" name="schdtp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择还款方式">
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-4 control-label">还款周期</label>
												<div class="col-md-4">
													<input type="text" name="repyfm"
														class="form-control input-inline input-medium form-value"
														placeholder="还款周期">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">多频率还款标志</label>
												<div class="col-md-4">
													<input type="text" name="ismurp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择多频率还款标志">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">还本周期</label>
												<div class="col-md-4">
													<input type="text" name="prinfm"
														class="form-control input-inline input-medium form-value"
														placeholder="还本周期">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">默认期限</label>
												<div class="col-md-4">
													<input type="text" name="dfperd"
														class="form-control input-inline input-medium form-value"
														placeholder="默认期限">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">逾期还款周期</label>
												<div class="col-md-4">
													<input type="text" name="ovdufm"
														class="form-control input-inline input-medium form-value"
														placeholder="逾期还款周期">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">还款顺序编号</label>
												<div class="col-md-4">
													<input type="text" name="repysq"
														class="form-control input-inline input-medium form-value"
														placeholder="还款顺序编号">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">结息方式</label>
												<div class="col-md-4">
													<input type="text" name="insttp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择结息方式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">欠款还款时段</label>
												<div class="col-md-4">
													<input type="text" name="odrptp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择欠款还款时段">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">允许调整还款方式</label>
												<div class="col-md-4">
													<input type="text" name="isadsc"
														class="form-control input-inline input-medium form-value"
														placeholder="选择允许调整还款方式">
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-3 control-label">等额处理规则</label>
												<div class="col-md-9">
													<input type="text" name="eqmode"
														class="form-control input-inline input-medium form-value"
														placeholder="等额处理规则">
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-3 control-label">累进值</label>
												<div class="col-md-9">
													<input type="text" name="progvl"
														class="form-control input-inline input-medium form-value"
														placeholder="累进值">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">累进区间期数</label>
												<div class="col-md-9">
													<input type="text" name="progpv"
														class="form-control input-inline input-medium form-value"
														placeholder="累进区间期数">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">多还款账户标志</label>
												<div class="col-md-9">
													<input type="text" name="ismuac"
														class="form-control input-inline input-medium form-value"
														placeholder="多还款账户标志">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">循环贷款</label>
												<div class="col-md-9">
													<input type="text" name="iscycl"
														class="form-control input-inline input-medium form-value"
														placeholder="循环贷款">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">提前出账标志</label>
												<div class="col-md-9">
													<input type="text" name="isintf"
														class="form-control input-inline input-medium form-value"
														placeholder="提前出账标志">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">提前出账天数</label>
												<div class="col-md-9">
													<input type="text" name="intfdy"
														class="form-control input-inline input-medium form-value"
														placeholder="提前出账天数">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">返还合格性编号</label>
												<div class="col-md-9">
													<input type="text" name="retncd"
														class="form-control input-inline input-medium form-value"
														placeholder="返还合格性编号">
												</div>
											</div>
											 
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="col-md-4 control-label">首次还款日确定模式</label>
												<div class="col-md-4">
													<input type="text" name="frstmd"
														class="form-control input-inline input-medium form-value"
														placeholder="选择首次还款日确定模式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">末期还款方式</label>
												<div class="col-md-4">
													<input type="text" name="lastmd"
														class="form-control input-inline input-medium form-value"
														placeholder="选择末期还款方式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">自动扣款标志</label>
												<div class="col-md-4">
													<input type="text" name="isaurp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择自动扣款标志">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">自动结清贷款标志</label>
												<div class="col-md-4">
													<input type="text" name="isclos"
														class="form-control input-inline input-medium form-value"
														placeholder="选择自动结清贷款标志">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">允许缩期</label>
												<div class="col-md-4">
													<input type="text" name="isredu"
														class="form-control input-inline input-medium form-value"
														placeholder="选择允许缩期">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">缩期最大次数</label>
												<div class="col-md-4">
													<input type="text" name="mxredu"
														class="form-control input-inline input-medium form-value"
														placeholder="缩期最大次数">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">不足额扣款方式</label>
												<div class="col-md-4">
													<input type="text" name="aurptp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择不足额扣款方式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">逾期不足额扣款方式</label>
												<div class="col-md-4">
													<input type="text" name="auovtp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择逾期不足额扣款方式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">欠息时是否减少额度</label>
												<div class="col-md-4">
													<input type="text" name="isrdcl"
														class="form-control input-inline input-medium form-value"
														placeholder="选择欠息时是否减少额度">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">允许行内非同名帐户还款</label>
												<div class="col-md-4">
													<input type="text" name="isnmrp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择允许行内非同名帐户还款">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">允许内部账户还款</label>
												<div class="col-md-4">
													<input type="text" name="isinrp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择允许内部账户还款">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">允许提前还款</label>
												<div class="col-md-4">
													<input type="text" name="isepym"
														class="form-control input-inline input-medium form-value"
														placeholder="选择允许提前还款">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">提前还款还息方式</label>
												<div class="col-md-4">
													<input type="text" name="epymtp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择提前还款还息方式">
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-3 control-label">返还方式</label>
												<div class="col-md-9">
													<input type="text" name="retntp"
														class="form-control input-inline input-medium form-value"
														placeholder="返还方式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">返还频率</label>
												<div class="col-md-9">
													<input type="text" name="retnfr"
														class="form-control input-inline input-medium form-value"
														placeholder="返还频率">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">返还计算方式</label>
												<div class="col-md-9">
													<input type="text" name="retnca"
														class="form-control input-inline input-medium form-value"
														placeholder="返还计算方式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">返还金额或比例</label>
												<div class="col-md-9">
													<input type="text" name="retnpt"
														class="form-control input-inline input-medium form-value"
														placeholder="返还金额或比例">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">提前还款锁定期</label>
												<div class="col-md-9">
													<input type="text" name="epymlk"
														class="form-control input-inline input-medium form-value"
														placeholder="提前还款锁定期">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">每年可提前还款最大次数</label>
												<div class="col-md-9">
													<input type="text" name="mxyrts"
														class="form-control input-inline input-medium form-value"
														placeholder="每年可提前还款最大次数">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">期限内提前还款最大次数</label>
												<div class="col-md-9">
													<input type="text" name="mxtlts"
														class="form-control input-inline input-medium form-value"
														placeholder="期限内提前还款最大次数">
												</div>
											</div>
											 
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="col-md-4 control-label">提前还款是否还贴息</label>
												<div class="col-md-4">
													<input type="text" name="iseptx"
														class="form-control input-inline input-medium form-value"
														placeholder="选择提前还款是否还贴息">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">提前还款调整计划</label>
												<div class="col-md-4">
													<input type="text" name="epchsc"
														class="form-control input-inline input-medium form-value"
														placeholder="选择提前还款调整计划">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">期限变更调整计划</label>
												<div class="col-md-4">
													<input type="text" name="tmchsc"
														class="form-control input-inline input-medium form-value"
														placeholder="选择期限变更调整计划">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">利率变更调整计划</label>
												<div class="col-md-4">
													<input type="text" name="rtchsc"
														class="form-control input-inline input-medium form-value"
														placeholder="选择利率变更调整计划">
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-4 control-label">是否有宽限期</label>
												<div class="col-md-4">
													<input type="text" name="isgrac"
														class="form-control input-inline input-medium form-value"
														placeholder="选择是否有宽限期">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">本金宽限期天数</label>
												<div class="col-md-4">
													<input type="text" name="grprdy"
														class="form-control input-inline input-medium form-value"
														placeholder="本金宽限期天数">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">利息宽限期天数</label>
												<div class="col-md-4">
													<input type="text" name="grindy"
														class="form-control input-inline input-medium form-value"
														placeholder="利息宽限期天数">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">宽限期最大次数</label>
												<div class="col-md-4">
													<input type="text" name="gracts"
														class="form-control input-inline input-medium form-value"
														placeholder="宽限期最大次数">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">宽限期计息方式</label>
												<div class="col-md-4">
													<input type="text" name="grintp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择宽限期计息方式">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">宽限期后计息规则</label>
												<div class="col-md-4">
													<input type="text" name="grovtp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择宽限期后计息规则">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">宽限期收息规则</label>
												<div class="col-md-4">
													<input type="text" name="grrctp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择宽限期收息规则">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">宽限期转逾期规则</label>
												<div class="col-md-4">
													<input type="text" name="grtftp"
														class="form-control input-inline input-medium form-value"
														placeholder="选择宽限期转逾期规则">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">宽限期节假日规则</label>
												<div class="col-md-4">
													<input type="text" name="grhdtp"
														class="form-control input-inline input-small form-value"
														placeholder="选择宽限期节假日规则">
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-3 control-label">提前还款最低金额规则</label>
												<div class="col-md-9">
													<input type="text" name="mnepru"
														class="form-control input-inline input-medium form-value"
														placeholder="提前还款最低金额规则">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">提前还款最低金额取值</label>
												<div class="col-md-9">
													<input type="text" name="mnepvl"
														class="form-control input-inline input-medium form-value"
														placeholder="提前还款最低金额取值">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">提前还款最高金额规则</label>
												<div class="col-md-9">
													<input type="text" name="mxepru"
														class="form-control input-inline input-medium form-value"
														placeholder="提前还款最高金额规则">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">提前还款最高金额取值</label>
												<div class="col-md-9">
													<input type="text" name="mxepvl"
														class="form-control input-inline input-medium form-value"
														placeholder="提前还款最高金额取值">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">多次放款调整计划</label>
												<div class="col-md-9">
													<input type="text" name="ldchsc"
														class="form-control input-inline input-medium form-value"
														placeholder="多次放款调整计划">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">是否允许部分还款</label>
												<div class="col-md-9">
													<input type="text" name="ispart"
														class="form-control input-inline input-medium form-value"
														placeholder="是否允许部分还款">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">部分还款最低金额规则</label>
												<div class="col-md-9">
													<input type="text" name="parttp"
														class="form-control input-inline input-medium form-value"
														placeholder="部分还款最低金额规则">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">部分还款最低金额或比例</label>
												<div class="col-md-9">
													<input type="text" name="partvl"
														class="form-control input-inline input-medium form-value"
														placeholder="部分还款最低金额或比例">
												</div>
											</div>
											 
										</div>
									</div>

								</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
						<button type="button" class="btn blue" id="btn_save_rule">保存</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/pages/loan/scripts/lnsubobj.js"></script>
<script src="${ctx}/pages/loan/scripts/lnfschdrule.js"></script>
<script>
	jQuery(document).ready(function() {
		lnsubobj.setparam("lnfschdrule", lnfschdrule);
	});
</script>