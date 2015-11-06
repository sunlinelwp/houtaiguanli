package cn.sunline.message.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("ApSysColumnServiceImpl")
public class  ApSysColumnServiceImpl{
	@Autowired
	private ApSysColumnRepository apSysColumnRepository;
	
	@Cacheable(value="defaultCache", condition="#msgCd!=null",key="#msgCd")
	public List<ApSysColumn> findByMsgCdRoot(String msgCd){
		return apSysColumnRepository.findByMsgCdRoot(msgCd);
	}
	
	@Cacheable(value="defaultCache", condition="#columnCd!=null&&#msgCd!=null",key="#msgCd + #columnCd")
	public List<ApSysColumn> findChildByMsgCdColumnCd(String columnCd,String msgCd){
		return apSysColumnRepository.findChildByMsgCdColumnCd(columnCd, msgCd);
	}
}
