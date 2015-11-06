package cn.sunline.dict;

import java.util.List;

public interface ApSysDictService {
	
	/***
	 * 根据字典类型获取所有字典
	 * @param dictType 字典类型
	 * @return 字典
	 */
	List<ApSysDict> getDictsByDictType(String dictType);
	
	/**
	 * 根据主键查询字典
	 * @param dictType 字典类型
	 * @param dictId 字典号
	 * @return 字典
	 */
	ApSysDict getDictById(String dictType,String dictId);
	
	/**
	 * 获取子字典
	 * @param dictType 父字典类型
	 * @param dictId 父字典号
	 * @return 字典
	 */
	List<ApSysDict> getChildDicts(String dictType,String dictId);
}
