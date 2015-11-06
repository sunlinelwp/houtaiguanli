package cn.sunline.tmp.unionpay.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TmpUnionPayCltnHeadCheckRepository extends JpaRepository<TmpUnionPayCltnHeadCheck,String>,JpaSpecificationExecutor<TmpUnionPayCltnHeadCheck>{
	@Modifying
	@Query(value = "delete from TmpUnionPayCltnHeadCheck a where a.checkDate = ?1 ")
	int deleteByHeadCheckDate(String checkDate);
	
	@Modifying
	@Query(value = "update TmpUnionPayCltnHeadCheck a set a.checkStatus = ?2 where a.checkDate = ?1 ")
	int upByHeadCheckDate(String checkDate,String checkStatus);
}
