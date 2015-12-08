<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<div class="tab-pane">
	<form class="form-horizontal control-label" role="form" id="prod_form">
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
					<label class="col-md-3 control-label">产品代码</label>
					<div class="col-md-9">
						<input type="text" name="prodcd"
							class="form-control input-inline input-medium form-value"
							placeholder="产品代码">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">产品名称</label>
					<div class="col-md-9">
						<input type="text" name="prodna"
							class="form-control input-inline input-medium form-value"
							placeholder="产品名称">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">产品类型</label>
					<div class="col-md-9">
						<input type="text" name="prodtp"
							class="form-control input-inline input-medium form-value"
							placeholder="产品类型">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">产品描述</label>
					<div class="col-md-9">
						<input type="text" name="prodde"
							class="form-control input-inline input-medium form-value"
							placeholder="产品描述">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">产品模式</label>
					<div class="col-md-9">
						<input type="text" name="prodmd"
							class="form-control input-inline input-medium form-value"
							placeholder="产品模式">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">产品状态</label>
					<div class="col-md-9">
						<input type="text" name="prodst"
							class="form-control input-inline input-medium form-value"
							placeholder="产品状态">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">融资来源编号</label>
					<div class="col-md-9">
						<input type="text" name="fcsrcd"
							class="form-control input-inline input-medium form-value"
							placeholder="融资来源编号">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">融资来源名称</label>
					<div class="col-md-9">
						<input type="text" name="fcsrna"
							class="form-control input-inline input-medium form-value"
							placeholder="融资来源名称">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">唯一规则</label>
					<div class="col-md-9">
						<input type="text" name="onlyfg"
							class="form-control input-inline input-medium form-value"
							placeholder="唯一规则">
					</div>
				</div>
				
			</div>
			
			
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-3 control-label">收款业务编号</label>
					<div class="col-md-9">
						<input type="text" name="rvbsno"
							class="form-control input-inline input-medium form-value"
							placeholder="收款业务编号">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">收款业务名称</label>
					<div class="col-md-9">
						<input type="text" name="pybsno"
							class="form-control input-inline input-medium form-value"
							placeholder="收款业务名称">
					</div>
				</div>
				
				
				
				<div class="form-group">
					<label class="col-md-3 control-label">货币代码</label>
					<div class="col-md-9">
						<input type="text" name="crcycd"
							class="form-control input-inline input-medium form-value"
							placeholder="货币代码">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">不足额规则</label>
					<div class="col-md-9">
						<input type="text" name="lesrul"
							class="form-control input-inline input-medium form-value"
							placeholder="不足额规则">
							
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">追加起投规则</label>
					<div class="col-md-9">
						<input type="text" name="addrul"
							class="form-control input-inline input-medium form-value"
							placeholder="追加起投规则">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">追加起投金额</label>
					<div class="col-md-9">
						<input type="text" name="addamt"
							class="form-control input-inline input-medium form-value"
							placeholder="追加起投金额">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">不足额追加规则</label>
					<div class="col-md-9">
						<input type="text" name="learul"
							class="form-control input-inline input-medium form-value"
							placeholder="不足额追加规则">
					</div>
				</div>
								
				<div class="form-group">
					<label class="col-md-3 control-label" id="efctdat">生效日期</label>
					<div class="col-md-9">
						<div class="input-group col-md-12 date input-medium date-picker"
							data-date-format="yyyymmdd" data-date-viewmode="years"
							data-date-minviewmode="months">
							<input type="text" class="form-control" name="efctdt"
								maxlength="8" placeholder="输入生效日期"> <span
								class="input-group-btn">
								<button class="btn default" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label" id="inefect">失效日期</label>
					<div class="col-md-9">
						<div class="input-group col-md-12 date input-medium date-picker"
							data-date-format="yyyymmdd" data-date-viewmode="years"
							data-date-minviewmode="months">
							<input type="text" class="form-control" name="inefdt"
								maxlength="8" placeholder="输入失效日期"> <span
								class="input-group-btn">
								<button class="btn default" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
			<button type="submit" class="btn blue" id="btn_save_edit">保存</button>
		</div>
	</form>
</div>
<script type="text/javascript"
	src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script>
	jQuery(document).ready(function() {
		// 时间插件
		var lnfprodedit = function() {
			var hander = function() {
				if (jQuery().datepicker) {
					$('.date-picker',$("#prod_form")).datepicker({
						rtl : Metronic.isRTL(),
						orientation : "left",
						autoclose : true,
						language : 'zh-CN',
					});
				};
				
				var prodstDict = Sunline.getDict("E_PRODST");
				$("input[name='prodst']").select2({
					data : prodstDict,
					allowClear : true
				});
				
				var onlyfgDict = Sunline.getDict("E_ONLYFG");
				$("input[name='onlyfg']").select2({
					data : onlyfgDict,
					allowClear : true
				});
				
				var prodtpDict = Sunline.getDict("E_PRODTP");
				$("input[name='prodtp']").select2({
					data : prodtpDict,
					allowClear : true
				});
				
				
				var prodmdDict = Sunline.getDict("E_PRODMD");
				$("input[name='prodmd']").select2({
					data : prodmdDict,
					allowClear : true
				}); 
				
				var lesrulDict = Sunline.getDict("E_LESRUL");//不足额规则
				$("input[name='lesrul']").select2({
					data : lesrulDict,
					allowClear : true
				}); 
				
				var addrulDict = Sunline.getDict("E_ADDRUL");//追加起投规则
				$("input[name='addrul']").select2({
					data : addrulDict,
					allowClear : true
				}); 
				
				var learulDict = Sunline.getDict("E_LEARUL");//不足额追加规则
				$("input[name='learul']").select2({
					data : learulDict,
					allowClear : true
				}); 
				
				var crcycdDict = Sunline.getDict("E_CRCYCD");//币种
				$("input[name='crcycd']").select2({
					data : crcycdDict,
					allowClear : true
				});
				//Metronic.initSlimScroll();
				$("#btn_save_edit").click(function(){
				    if($("input[name='inefdt']").val()<=$("input[name='efctdt']").val()){
				      	  $(".alert-success", $("#prod_form")).hide();
				      	  /* $(".msg").text("失效日期"); */
				        	  $('.alert-danger',  $("#prod_form")).show(); 
				        	  return false;
				        } else{
				        	 $('.alert-danger',  $("#prod_form")).hide(); 
				        }
				       
				});
			}

			return {
				init : function() {
					hander();
				}
			}
		}();
		lnfprodedit.init();
	});
</script>
