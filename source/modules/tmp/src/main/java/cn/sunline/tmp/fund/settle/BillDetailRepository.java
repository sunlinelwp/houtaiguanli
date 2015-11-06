package cn.sunline.tmp.fund.settle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BillDetailRepository extends JpaRepository<BillDetail,String>,JpaSpecificationExecutor<BillDetail>{
	
	@Query(value = "select count(1) from BillDetail a where a.trandt = ?1")
	int getBillCountByCheckDate (String trandt);
}
