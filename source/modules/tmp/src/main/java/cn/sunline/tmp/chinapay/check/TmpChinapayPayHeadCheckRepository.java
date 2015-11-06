package cn.sunline.tmp.chinapay.check;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TmpChinapayPayHeadCheckRepository extends JpaRepository<TmpChinapayPayHeadCheck,String>,JpaSpecificationExecutor<TmpChinapayPayHeadCheck>{
	@Modifying
	@Query(value = "delete from TmpChinapayPayHeadCheck a where a.checkDate = ?1 ")
	int deleteByHeadCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpChinapayPayHeadCheck a set a.checkStatus = ?2 where a.checkDate = ?1 ")
	int upByHeadCheckDate(String checkDate,String checkStatus);
}
