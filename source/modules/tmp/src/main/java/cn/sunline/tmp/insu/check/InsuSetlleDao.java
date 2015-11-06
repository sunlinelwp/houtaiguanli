package cn.sunline.tmp.insu.check;


public interface InsuSetlleDao{
	public InsuSetlle findOne(InsuSetllePK id);
	
	public <S extends InsuSetlle> S save(S entity);
	
	public boolean exists(InsuSetllePK id);
}

