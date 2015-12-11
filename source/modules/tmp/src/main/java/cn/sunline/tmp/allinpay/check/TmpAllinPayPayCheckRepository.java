package cn.sunline.tmp.allinpay.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("TmpAllinPayPayCheckRepository")
public interface TmpAllinPayPayCheckRepository extends JpaRepository<TmpAllinPayPayCheck, TmpAllinPayPayCheckPK>, JpaSpecificationExecutor<TmpAllinPayPayCheck>{
	@Modifying
	@Query(value = "delete from TmpAllinPayPayCheck a where a.checkDate = ?1 ")
	int deleteByCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpAllinPayPayCheck a set a.checkStatus = ?4 where a.checkDate = ?1 and a.merchantDt = ?2 and a.billno = ?3")
	int updateStatus(String checkDate , String merchantDt , String billno ,String checkStatus);
	
	@Query(value = "select count(1) from TmpAllinPayPayCheck a where a.checkDate = ?1 and a.checkStatus = ?2")
	int getCPCltnCountByStatus (String checkDate , String status);
}
