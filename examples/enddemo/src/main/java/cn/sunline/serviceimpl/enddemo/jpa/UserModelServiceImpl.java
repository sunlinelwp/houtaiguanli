package cn.sunline.serviceimpl.enddemo.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sunline.domain.enddemo.jpa.UserModel;
import cn.sunline.repository.enddemo.jpa.UserModelRepository;
import cn.sunline.service.enddemo.jpa.UserModelService;

@Service("UserModelService")
@Transactional(readOnly=true)
public class UserModelServiceImpl implements UserModelService{

	@Autowired
	private UserModelRepository userModelRepository;

	@Override
	public List<UserModel> findByUuid(Integer uuid) {
		return userModelRepository.findByUuid(uuid);
	}

	@Override
	public List<UserModel> findByName(String name) {
		return userModelRepository.findByName(name);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public UserModel saveUserModel(UserModel userModel) {
		return userModelRepository.save(userModel);
	}
	
}
