package cn.sunline.service.enddemo.jpa;

import java.util.List;

import cn.sunline.domain.enddemo.jpa.UserModel;

public interface UserModelService {
	
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
	
	/*
	 * 保存
	 * @Param	userModel
	 * @return  userModel
	 * 
	 */
	UserModel saveUserModel(UserModel userModel);
	
}
