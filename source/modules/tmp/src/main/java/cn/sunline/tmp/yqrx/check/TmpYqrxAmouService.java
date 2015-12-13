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
	int updateStates(String frondt, String fronsq, String states);
	
	/**
	 * 
	 * <p>查询清算日期keepdt与来源类型comept是否在表中</p>
	 * @param keepdt
	 * @return true-存在；false：不存在
	 * <p>create by kangyu AT TIME 2015年12月10日 下午9:37:18</p>
	 */
	boolean checkIsExitByKeepdt(String keepdt, String comept);
	

	public Specification<TmpYqrxAmou> getSpecification(final TmpYqrxAmou tmp);
	/**
	 * <p>分页查询出金临时表</p>
	 * @param tmp
	 * @param pageable
	 * @return
	 * <p>create by kangyu AT TIME 2015年12月10日 下午10:31:41</p>
	 */
	Page<TmpYqrxAmou> queryEntitiesByTemplateWithPage(TmpYqrxAmou tmp,Pageable pageable);

}
