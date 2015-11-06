package cn.sunline.tmp.insu.check;

import cn.sunline.exception.SumpException;


public interface InsuSetlleService {
	/**
	 * 根据主键扩展实体对象
	 * @param entity 实体对象
	 * @return 查询到的实体对象
	 */
	InsuSetlle expandEntit (InsuSetlle entity) ;
	
	/**
	 * 保存单条记录
	 * @param entity 实体对象
	 * @return 保存后实体对象
	 * @throws CommonException 
	 */
	InsuSetlle saveEntity(InsuSetlle entity) throws SumpException ;
	
	/**
	 * 检查主键是否唯一
	 * @param entity 实体对象
	 * @return 是否唯一
	 */
	boolean checkUnique(InsuSetlle entity);
	
	
}
