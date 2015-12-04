package cn.sunline.tmp.allinpay.gate.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("TmpAllinPayPayGateCheckRepository")
public interface TmpAinPayPayGateCheckRepository extends JpaRepository<TmpAinPayPayGateCheck, TmpAinPayPayGateCheckPK>, JpaSpecificationExecutor<TmpAinPayPayGateCheck>{
	@Modifying
	@Query(value = "delete from TmpAinPayPayGateCheck a where a.checkDate = ?1 ")
	int deleteByCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpAinPayPayGateCheck a set a.checkStatus = ?4 where a.checkDate = ?1 and a.merchantDt = ?2 and a.billno = ?3")
	int updateStatus(String checkDate , String merchantDt , String billno ,String checkStatus);
	
	@Query(value = "select count(1) from TmpAinPayPayGateCheck a where a.checkDate = ?1 and a.checkStatus = ?2")
	int getCPCltnCountByStatus (String checkDate , String status);
}
