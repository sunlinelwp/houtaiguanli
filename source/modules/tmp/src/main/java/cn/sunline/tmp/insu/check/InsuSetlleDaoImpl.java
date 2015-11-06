package cn.sunline.tmp.insu.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("InsuSetlleDao")


public class InsuSetlleDaoImpl implements InsuSetlleDao{

	
	 @Autowired
	 InsuSetlleRepository insuSetlleRepository;
	 
	@Override
	public InsuSetlle findOne(InsuSetllePK id) {
		return insuSetlleRepository.findOne(id);
	}

	@Override
	public <S extends InsuSetlle> S save(S entity) {
		return insuSetlleRepository.save(entity);
	}
	
	@Override
	public boolean exists(InsuSetllePK id) {
		return insuSetlleRepository.exists(id);
	}
}
