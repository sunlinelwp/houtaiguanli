package cn.sunline.repository.enddemo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sunline.domain.enddemo.jpa.DepModel;

public interface DepModelRepository extends JpaRepository<DepModel, Integer>{

	
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
	
}
