package cn.sunline.tmp.yqrx.check;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.sunline.tmp.chinapay.check.TmpChinapayCltnCheck;


public interface TmpYqrxAmouService {
	/**
	 * 保存多条记录并提交到数据
	 * @param entity
	 * @return
	 */
	int saveTmpYqrxAmou(List<TmpYqrxAmou> entity);

	/**
	 * 更新数据处理状态
	 * @param entity
	 * @return
	 */
	int updateStates(String amouid, String states);
	
	/**
	 * 
	 * <p>查询日期amoudt与来源类型comept是否在表中</p>
	 * @param Amoudt
	 * @return true-存在；false：不存在
	 * <p>create by kangyu AT TIME 2015年12月10日 下午9:37:18</p>
	 */
	boolean checkIsExitByAmoudt(String amoudt, String comept);
	

	public Specification<TmpYqrxAmou> getSpecification(final TmpYqrxAmou tmp);
	/**
	 * <p>分页查询出金临时表</p>
	 * @param tmp
	 * @param pageable
	 * @return
	 * <p>create by kangyu AT TIME 2015年12月10日 下午10:31:41</p>
	 */
	Page<TmpYqrxAmou> queryEntitiesByTemplateWithPage(TmpYqrxAmou tmp,Pageable pageable);
	
	
	/**
	 * <p>查询一条信息</p>
	 * @param tmp
	 * @return
	 * <p>create by kangyu AT TIME 2015年12月11日 下午4:07:33</p>
	 */
	TmpYqrxAmou queryOneEntities(TmpYqrxAmouPK amouid);

}
