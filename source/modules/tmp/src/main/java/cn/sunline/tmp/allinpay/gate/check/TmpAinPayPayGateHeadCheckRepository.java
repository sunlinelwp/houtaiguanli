package cn.sunline.tmp.allinpay.gate.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TmpAinPayPayGateHeadCheckRepository extends JpaRepository<TmpAinPayPayGateHeadCheck,String>,JpaSpecificationExecutor<TmpAinPayPayGateHeadCheck>{
	@Modifying
	@Query(value = "delete from TmpAinPayPayGateHeadCheck a where a.checkDate = ?1 ")
	int deleteByHeadCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpAinPayPayGateHeadCheck a set a.checkStatus = ?2 where a.checkDate = ?1 ")
	int upByHeadCheckDate(String checkDate,String checkStatus);
}
