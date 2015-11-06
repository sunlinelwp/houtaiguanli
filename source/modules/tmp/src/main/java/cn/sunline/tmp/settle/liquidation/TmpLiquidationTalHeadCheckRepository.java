package cn.sunline.tmp.settle.liquidation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TmpLiquidationTalHeadCheckRepository extends
		JpaRepository<TmpLiquidationTalHeadCheck, String>,
		JpaSpecificationExecutor<TmpLiquidationTalHeadCheck> {
	@Modifying
	@Query(value = "delete from TmpLiquidationTalHeadCheck a where a.checkDate = ?1 ")
	int deleteByHeadCheckDate(String checkDate);

}
