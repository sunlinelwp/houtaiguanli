package cn.sunline.tmp.insu.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("InsuSetlleService")
public class InsuSetlleServiceImpl implements InsuSetlleService{

	@Autowired
	private InsuSetlleDao insuSetlleDao;

	
	@Override
	public InsuSetlle expandEntit(InsuSetlle entity) {
		InsuSetllePK id = new InsuSetllePK(entity.getDealdt(),entity.getTrantp());
		return insuSetlleDao.findOne(id);
	}

	@Override
	public boolean checkUnique(InsuSetlle entity) {
		InsuSetllePK id = new InsuSetllePK(entity.getDealdt(),entity.getTrantp());
		return !insuSetlleDao.exists(id);
	}
	
	@Override
	public InsuSetlle saveEntity(InsuSetlle entity)  {
		return insuSetlleDao.save(entity);
	}

}
