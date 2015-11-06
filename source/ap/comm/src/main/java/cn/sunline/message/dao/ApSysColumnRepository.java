package cn.sunline.message.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service("ApSysColumnRepository")
public interface ApSysColumnRepository extends
		JpaRepository<ApSysColumn, ApSysColumnPK> {

	@Query(value = "select a from ApSysColumn a where a.msgCd = ?1 and a.cyclingColumn is null order by a.sortSeq ")
	List<ApSysColumn> findByMsgCdRoot(String msgCd);
	
	
	@Query(value = "select a from ApSysColumn a where a.cyclingColumn = ?1 and a.msgCd = ?2 order by a.sortSeq ")
	List<ApSysColumn> findChildByMsgCdColumnCd(String columnCd,String msgCd);
}
