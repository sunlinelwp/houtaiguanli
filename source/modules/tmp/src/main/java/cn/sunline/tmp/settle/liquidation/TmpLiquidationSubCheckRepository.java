package cn.sunline.tmp.settle.liquidation;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TmpLiquidationSubCheckRepository extends JpaRepository<TmpLiquidationSubCheck, TmpLiquidationSubCheckPK>, JpaSpecificationExecutor<TmpLiquidationSubCheck>{

	@Modifying
	@Query(value = "delete from TmpLiquidationSubCheck a where a.checkDate = ?1 ")
	int deleteByCheckDate(String checkDate);
}
