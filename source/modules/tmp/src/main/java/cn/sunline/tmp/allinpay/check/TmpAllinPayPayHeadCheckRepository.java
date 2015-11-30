package cn.sunline.tmp.allinpay.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TmpAllinPayPayHeadCheckRepository extends JpaRepository<TmpAllinPayPayHeadCheck,String>,JpaSpecificationExecutor<TmpAllinPayPayHeadCheck>{
	@Modifying
	@Query(value = "delete from TmpAllinPayPayHeadCheck a where a.checkDate = ?1 ")
	int deleteByHeadCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpAllinPayPayHeadCheck a set a.checkStatus = ?2 where a.checkDate = ?1 ")
	int upByHeadCheckDate(String checkDate,String checkStatus);
}
