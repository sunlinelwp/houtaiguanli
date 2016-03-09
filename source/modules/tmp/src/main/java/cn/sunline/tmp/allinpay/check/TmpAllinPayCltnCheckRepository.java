package cn.sunline.tmp.allinpay.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TmpAllinPayCltnCheckRepository extends JpaRepository<TmpAllinPayCltnCheck, TmpAllinPayCltnCheckPK>, JpaSpecificationExecutor<TmpAllinPayCltnCheck>{
	@Modifying
	@Query(value = "delete from TmpAllinPayCltnCheck a where a.checkDate = ?1 ")
	int deleteByCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpAllinPayCltnCheck a set a.checkStatus = ?4 where a.checkDate = ?1 and a.merchantDt = ?2 and a.billNo = ?3 and a.timetm = ?5")
	int updateStatus(String checkDate ,String merchantDt, String billNo ,String checkStatus, long timetm);
	
	@Query(value = "select count(1) from TmpAllinPayCltnCheck a where a.checkDate = ?1 and a.checkStatus = ?2")
	int getCPCltnCountByStatus (String checkDate , String status);
}
