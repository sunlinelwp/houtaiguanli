package cn.sunline.tmp.allinpay.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TmpAllinPayCltnHeadCheckRepository extends JpaRepository<TmpAllinPayCltnHeadCheck,String>,JpaSpecificationExecutor<TmpAllinPayCltnHeadCheck>{
	@Modifying
	@Query(value = "delete from TmpAllinPayCltnHeadCheck a where a.checkDate = ?1 ")
	int deleteByHeadCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpAllinPayCltnHeadCheck a set a.checkStatus = ?2 where a.checkDate = ?1 ")
	int upByHeadCheckDate(String checkDate,String checkStatus);
}
