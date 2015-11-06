package cn.sunline.tmp.fund.settle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FundSetlleRepository extends JpaRepository<FundSetlle,FundSetllePK>,JpaSpecificationExecutor<FundSetlle>{
	@Modifying
	@Query(value = "update FundSetlle a set a.status = ?3 where a.dealdt = ?1 and a.trantp = ?2")
	int updateStatus(String dealdt,String trantp,String status);
}
