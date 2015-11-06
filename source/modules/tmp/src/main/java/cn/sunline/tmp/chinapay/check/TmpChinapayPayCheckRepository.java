package cn.sunline.tmp.chinapay.check;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("TmpChinapayPayCheckRepository")
public interface TmpChinapayPayCheckRepository extends JpaRepository<TmpChinapayPayCheck, TmpChinapayPayCheckPK>, JpaSpecificationExecutor<TmpChinapayPayCheck>{

	@Modifying
	@Query(value = "delete from TmpChinapayPayCheck a where a.checkDate = ?1 ")
	int deleteByCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpChinapayPayCheck a set a.checkStatus = ?4 where a.checkDate = ?1 and a.merchantDt = ?2 and a.cpSeqno = ?3")
	int updateStatus(String checkDate , String merchantDt , String cpSeqno ,String checkStatus);
	
	@Query(value = "select count(1) from TmpChinapayPayCheck a where a.checkDate = ?1 and a.checkStatus = ?2")
	int getCPCltnCountByStatus (String checkDate , String status);
}
