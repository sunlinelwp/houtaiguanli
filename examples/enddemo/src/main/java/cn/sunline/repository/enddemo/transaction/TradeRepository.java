package cn.sunline.repository.enddemo.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.sunline.domain.enddemo.transaction.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer>{
	
	public Trade findByTradeId(Integer tradeId);
	
	@Query(countQuery="select count(1) from trade t where t.tradeId = ?")
	public int countByTradeId(Integer tradeId);
	
}
