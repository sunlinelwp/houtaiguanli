package cn.sunline.tmp.yqrx.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("TmpYqrxAmouRepository")
public interface TmpYqrxAmouRepository extends JpaRepository<TmpYqrxAmou, TmpYqrxAmouPK>, JpaSpecificationExecutor<TmpYqrxAmou>{

	@Modifying
	@Query(value = "update TmpYqrxAmou a set a.states = ?2 where a.amouid = ?1")
	int changeStates(String amouid, String states);
	
	@Query(value = "select count(1) from TmpYqrxAmou a where a.amoudt = ?1 and a.cometp = ?2 ")
	int getCountByAmoudt(String amoudt, String cometp);
}
