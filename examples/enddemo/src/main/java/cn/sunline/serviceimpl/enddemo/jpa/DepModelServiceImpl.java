package cn.sunline.serviceimpl.enddemo.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sunline.domain.enddemo.jpa.DepModel;
import cn.sunline.repository.enddemo.jpa.DepModelRepository;
import cn.sunline.service.enddemo.jpa.DepModelService;

@Service("DepModelService")
@Transactional(readOnly=true)
public class DepModelServiceImpl implements DepModelService{

	@Autowired
	private DepModelRepository depModelRepository;

	@Override
	public List<DepModel> findByUuid(Integer uuid) {
		return depModelRepository.findByUuid(uuid);
	}
	
	@Override
	public List<DepModel> findByName(String name) {
		return depModelRepository.findByName(name);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public DepModel saveDepModel(DepModel depModel) {
		return depModelRepository.save(depModel);
	}

}
