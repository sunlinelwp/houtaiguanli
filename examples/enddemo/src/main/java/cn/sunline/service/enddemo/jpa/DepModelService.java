package cn.sunline.service.enddemo.jpa;

import java.util.List;

import cn.sunline.domain.enddemo.jpa.DepModel;

public interface DepModelService {
	
	/*
	 * 根据uuid查询
	 * @Param	uuid
	 * @return  depModel
	 * 
	 */
	List<DepModel> findByUuid(Integer uuid);
	
	/*
	 * 根据名称查询
	 * @Param	name
	 * @return  depModel
	 * 
	 */
	List<DepModel> findByName(String name);
	
	/*
	 * 保存
	 * @Param	depModel
	 * @return  depModel
	 * 
	 */
	DepModel saveDepModel(DepModel depModel);

}
