package cn.sunline.sequence;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sunline.sequence.SequenceNo;
import cn.sunline.sequence.SequenceNoRepository;
import cn.sunline.sequence.SequenceNoService;

@Service("sequenceNoService")
@Transactional(readOnly=true)
public class SequenceNoServiceImpl implements SequenceNoService{

	@Autowired
	private SequenceNoRepository sequenceNoRepository;//注入Repository

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String next(String key) {
		SequenceNo sequenceNo = sequenceNoRepository.findOne(key);
		if (sequenceNo == null) {
			sequenceNo = new SequenceNo();
			sequenceNo.setName(key);
			sequenceNo.setNextval(BigInteger.ONE);
		}else{
			BigInteger nextval = sequenceNo.getNextval();
			sequenceNo.setNextval(nextval.add(BigInteger.ONE));
		}
		
		sequenceNoRepository.saveAndFlush(sequenceNo);
		return String.valueOf(sequenceNo.getNextval());
	}

	/**
	 * 生成用0左占位顺序号
	 * 
	 * @param key
	 * @param scale
	 * @return
	 */
	public String nextLpad(String key, int scale) {
		String nextval = next(key);
		return StringUtils.leftPad(nextval, scale, "0");
	}

	/**
	 * 生成用0右占位顺序号
	 * 
	 * @param key
	 * @param scale
	 * @return
	 */
	public String nextRpad(String key, int scale) {
		String nextval = next(key);
		return StringUtils.rightPad(nextval, scale, "0");
	}
}
