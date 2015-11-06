package cn.sunline.tmp.fund.settle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TmpFundProfitRepository extends JpaRepository<TmpFundProfit,String>,JpaSpecificationExecutor<TmpFundProfit>{

	@Modifying
	@Query(value = "update TmpFundProfit a set a.chekst = ?2 where a.dealdt = ?1 ")
	int updateStatus(String dealdt,String status);
}
