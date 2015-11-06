package cn.sunline.dict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("apSysDictService")
@Transactional(readOnly = true)
public class ApSysDictServiceImpl implements ApSysDictService {

	@Autowired
	private ApSysDictRepository apSysDictRepository;// 注入Repository

	/***
	 * 根据字典类型获取所有字典
	 * 
	 * @param dictType
	 *            字典类型
	 * @return 字典
	 */
	@Cacheable(value="dictCache", condition="#dictType!=null",key="#dictType.toString()")
	public List<ApSysDict> getDictsByDictType(String dictType) {
		return apSysDictRepository.findByDictType(dictType);
	}

	/**
	 * 根据主键查询字典
	 * 
	 * @param dictType
	 *            字典类型
	 * @param dictId
	 *            字典号
	 * @return 字典
	 */
	public ApSysDict getDictById(String dictType, String dictId) {
		return apSysDictRepository.findByDictTypeAndDictId(dictType, dictId);
	}

	/**
	 * 获取子字典
	 * 
	 * @param dictType
	 *            父字典类型
	 * @param dictId
	 *            父字典号
	 * @return 字典
	 */
	public List<ApSysDict> getChildDicts(String dictType, String dictId) {
		return apSysDictRepository.findByParentDictTypeAndParentDictId(
				dictType, dictId);
	}
}
