package cn.sunline.tmp.settle.liquidation;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository("TmpLiquidationErrCheckRepository")
public interface TmpLiquidationErrCheckRepository extends JpaRepository<TmpLiquidationErrCheck, TmpLiquidationErrCheckPK>, JpaSpecificationExecutor<TmpLiquidationErrCheck>{

	@Modifying
	@Query(value = "delete from TmpLiquidationErrCheck a where a.checkDate = ?1 ")
	int deleteByCheckDate(String checkDate);
}
