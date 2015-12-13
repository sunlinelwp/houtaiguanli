package cn.sunline.tmp.yqrx.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("TmpYqrxAmouRepository")
public interface TmpYqrxAmouRepository extends JpaRepository<TmpYqrxAmou, TmpYqrxAmouPK>, JpaSpecificationExecutor<TmpYqrxAmou>{

	@Modifying
	@Query(value = "update TmpYqrxAmou a set a.states = ?3 where a.frondt = ?1 and a.fronsq = ?2 ")
	int changeStates(String frondt, String fronsq, String states);
	
	@Query(value = "select count(1) from TmpYqrxAmou a where a.keepdt = ?1 and a.cometp = ?2 ")
	int getCountByKeepdt(String keepdt, String cometp);
}
