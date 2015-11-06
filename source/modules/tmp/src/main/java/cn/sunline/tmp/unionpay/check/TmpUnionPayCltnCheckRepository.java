package cn.sunline.tmp.unionpay.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TmpUnionPayCltnCheckRepository extends JpaRepository<TmpUnionPayCltnCheck, TmpUnionPayCltnCheckPK>, JpaSpecificationExecutor<TmpUnionPayCltnCheck>{
	@Modifying
	@Query(value = "delete from TmpUnionPayCltnCheck a where a.checkDate = ?1 ")
	int deleteByCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpUnionPayCltnCheck a set a.checkStatus = ?4 where a.checkDate = ?1 and a.merchantDt = ?2 and a.billNo = ?3")
	int updateStatus(String checkDate ,String merchantDt, String billNo ,String checkStatus);
	
	@Query(value = "select count(1) from TmpUnionPayCltnCheck a where a.checkDate = ?1 and a.checkStatus = ?2")
	int getCPCltnCountByStatus (String checkDate , String status);
}
