package cn.sunline.repository.enddemo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sunline.domain.enddemo.jpa.UserModel;

public interface UserModelRepository extends JpaRepository<UserModel, Integer>{
	
	/*
	 * 根据uuid查询
	 * @Param	uuid
	 * @return  userModel
	 * 
	 */
	List<UserModel> findByUuid(Integer uuid);
	
	/*
	 * 根据名称查询
	 * @Param	name
	 * @return  userModel
	 * 
	 */
	List<UserModel> findByName(String name);
	
}
