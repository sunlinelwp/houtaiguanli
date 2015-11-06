package cn.sunline.tmp.settle.liquidation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TmpLiquidationSubHeadCheckRepository extends
		JpaRepository<TmpLiquidationSubHeadCheck, String>,
		JpaSpecificationExecutor<TmpLiquidationSubHeadCheck> {
	@Modifying
	@Query(value = "delete from TmpLiquidationSubHeadCheck a where a.checkDate = ?1 ")
	int deleteByHeadCheckDate(String checkDate);

}
