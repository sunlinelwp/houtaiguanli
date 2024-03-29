package cn.sunline.tmp.chinapay.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TmpChinapayCltnCheckRepository extends JpaRepository<TmpChinapayCltnCheck, TmpChinapayCltnCheckPK>, JpaSpecificationExecutor<TmpChinapayCltnCheck>{

	@Modifying
	@Query(value = "delete from TmpChinapayCltnCheck a where a.checkDate = ?1 ")
	int deleteByCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpChinapayCltnCheck a set a.checkStatus = ?4 where a.checkDate = ?1 and a.merchantDt = ?2 and a.billNo = ?3")
	int updateStatus(String checkDate ,String merchantDt, String billNo ,String checkStatus);
	
	@Query(value = "select count(1) from TmpChinapayCltnCheck a where a.checkDate = ?1 and a.checkStatus = ?2")
	int getCPCltnCountByStatus (String checkDate , String status);
}
