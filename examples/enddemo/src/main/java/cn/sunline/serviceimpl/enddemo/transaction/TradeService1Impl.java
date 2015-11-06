package cn.sunline.serviceimpl.enddemo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sunline.domain.enddemo.transaction.Trade;
import cn.sunline.repository.enddemo.transaction.TradeRepository;
import cn.sunline.service.enddemo.transaction.TradeService1;

@Service("TradeService1")
public class TradeService1Impl implements TradeService1{

	@Autowired
	private TradeRepository tradeRepository;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Trade insertTrade1(Trade trade){
		return tradeRepository.save(trade);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Trade insertTrade2(Trade trade){
		return tradeRepository.save(trade);
	}
	
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public Trade insertTrade3(Trade trade){
		return tradeRepository.save(trade);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Trade insertTrade4(Trade trade){
		return tradeRepository.save(trade);
	}
	
	@Override
	@Transactional(propagation=Propagation.NEVER)
	public Trade insertTrade5(Trade trade){
		return tradeRepository.save(trade);
	}
	
	@Override
	@Transactional(propagation=Propagation.NESTED)
	public Trade insertTrade6(Trade trade){
		return tradeRepository.save(trade);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Trade insertTrade7(Trade trade){
		return tradeRepository.save(trade);
	}
}
