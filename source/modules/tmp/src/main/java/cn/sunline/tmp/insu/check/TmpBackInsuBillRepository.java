package cn.sunline.tmp.insu.check;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("TmpBackInsuBillRepository")
public interface TmpBackInsuBillRepository extends JpaRepository<TmpBackInsuBill, TmpBackInsuBillPK>, JpaSpecificationExecutor<TmpBackInsuBill>{
	
	@Modifying
	@Query(value = "update TmpBackInsuBill a set a.checkStatus = ?2 , a.msg = ?3 ,a.transq = ?4 where a.polino = ?1")
	int updateStatus(String polino ,String checkStatus , String msg , String transq);
	
	
	@Query(value = "select sum(a.tranam) from TmpBackInsuBill a where a.dealdt = ?1")
	BigDecimal getTotlam(String dealdt);
}
