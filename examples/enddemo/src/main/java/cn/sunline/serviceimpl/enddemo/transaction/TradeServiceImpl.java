package cn.sunline.serviceimpl.enddemo.transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sunline.domain.enddemo.transaction.Trade;
import cn.sunline.repository.enddemo.transaction.TradeRepository;
import cn.sunline.service.enddemo.transaction.TradeService;

@Service("TradeService")
public class TradeServiceImpl implements TradeService{

	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private TradeService1Impl tradeService1Impl;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Trade findByTradeId(Integer tradeId){
		return tradeRepository.findByTradeId(tradeId);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int countByTradeId(Integer tradeId){
		return tradeRepository.countByTradeId(tradeId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Trade insertTrade01(Trade trade){
		return tradeRepository.save(trade);
	}
	
	@Override
	@Transactional(readOnly=false)
	public Trade insertTrade02(Trade trade){
		return tradeRepository.save(trade);
	}

	@Override
	@Transactional
	public Trade insertTrade1(Trade trade) throws Exception{
		tradeRepository.save(trade);
		throw new Exception("User Define Exception");
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Trade insertTrade2(Trade trade) throws Exception{
		tradeRepository.save(trade);
		throw new Exception("User Define Exception");
	}

	@Override
	@Transactional
	public Trade insertTrade3(Trade trade) throws RuntimeException{
		tradeRepository.save(trade);
		throw new RuntimeException("User Define RuntimeException");
	}

	@Override
	@Transactional(noRollbackFor=RuntimeException.class)
	public Trade insertTrade4(Trade trade) throws RuntimeException{
		tradeRepository.save(trade);
		throw new RuntimeException("User Define RuntimeException");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Trade insertTrade5(Trade trade) {
		tradeService1Impl.insertTrade1(trade);
		throw new RuntimeException("User Define RuntimeException");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertTrade6(Trade trade){
		tradeService1Impl.insertTrade2(trade);
		throw new RuntimeException("User Define RuntimeException");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Trade insertTrade71(Trade trade) {
		return tradeService1Impl.insertTrade3(trade);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Trade insertTrade72(Trade trade) {
		return tradeService1Impl.insertTrade3(trade);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Trade insertTrade81(Trade trade) {
		tradeService1Impl.insertTrade4(trade);
		throw new RuntimeException("User Define RuntimeException");
	}
	
	@Override
	public Trade insertTrade82(Trade trade) {
		tradeService1Impl.insertTrade4(trade);
		throw new RuntimeException("User Define RuntimeException");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Trade insertTrade91(Trade trade) {
		return tradeService1Impl.insertTrade5(trade);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Trade insertTrade92(Trade trade) {
		return tradeService1Impl.insertTrade5(trade);
	}

	@Override
	@Transactional(propagation=Propagation.NESTED)
	public Trade insertTrade101(Trade trade) {
		tradeRepository.save(trade);
		throw new RuntimeException("User Define RuntimeException");
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Trade insertTrade102(Trade trade) {
		tradeService1Impl.insertTrade6(trade);
		throw new RuntimeException("User Define RuntimeException");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Trade insertTrade111(Trade trade) {
		tradeService1Impl.insertTrade7(trade);
		throw new RuntimeException("User Define RuntimeException");
	}
	
	@Override
	public Trade insertTrade112(Trade trade) {
		tradeService1Impl.insertTrade7(trade);
		throw new RuntimeException("User Define RuntimeException");
	}
	
	@Override
	@Transactional(timeout=5)
	public Trade insertTrade121(Trade trade) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return tradeRepository.save(trade);
	}
	
	@Override
	@Transactional(timeout=1)
	public Trade insertTrade122(Trade trade) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}
		return tradeRepository.save(trade);
	}
	
}
