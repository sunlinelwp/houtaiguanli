package cn.sunline.dict;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApSysDictRepository extends JpaRepository<ApSysDict, String> {

	@Query(value = "select a from ApSysDict a where a.dictType = ?1 and a.status = 'Y' order by a.sortNo ")
	List<ApSysDict> findByDictType(String dictType);

	ApSysDict findByDictTypeAndDictId(String dictType, String dictId);

	@Query(value = "select a from ApSysDict a where a.parentDictType = ?1 and a.parentDictId = ?2 and a.status = 'Y' order by a.sortNo ")
	List<ApSysDict> findByParentDictTypeAndParentDictId(String parentDictType,
			String parentDictId);
}
