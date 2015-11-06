package cn.sunline.service.enddemo.transaction;

import cn.sunline.domain.enddemo.transaction.Trade;

public interface TradeService {
	
	public Trade findByTradeId(Integer tradeId);
	
	public int countByTradeId(Integer tradeId);

	public Trade insertTrade01(Trade trade);

	public Trade insertTrade02(Trade trade);
	
	public Trade insertTrade1(Trade trade) throws Exception;

	public Trade insertTrade2(Trade trade) throws Exception;

	public Trade insertTrade3(Trade trade) throws RuntimeException;

	public Trade insertTrade4(Trade trade) throws RuntimeException;

	public Trade insertTrade5(Trade trade);

	public void insertTrade6(Trade trade);

	public Trade insertTrade71(Trade trade);

	public Trade insertTrade72(Trade trade);

	public Trade insertTrade81(Trade trade);

	public Trade insertTrade82(Trade trade);

	public Trade insertTrade91(Trade trade);

	public Trade insertTrade92(Trade trade);

	public Trade insertTrade101(Trade trade);

	public Trade insertTrade102(Trade trade);

	public Trade insertTrade111(Trade trade);

	public Trade insertTrade112(Trade trade);

	public Trade insertTrade121(Trade trade);

	public Trade insertTrade122(Trade trade);
	
}
