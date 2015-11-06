package cn.sunline.tmp.settle.liquidation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.sunline.tmp.unionpay.check.TmpUnionPayPayCheck;

@Service("TmpLiquidationErrCheckDao")
public class TmpLiquidationErrCheckDaoImpl implements TmpLiquidationErrCheckDao {
	
	@Autowired
	TmpLiquidationErrCheckRepository tmpLiquidationErrCheckRepository;
	
	@Override
	public Specification<TmpLiquidationErrCheck> getSpecification(
			final TmpLiquidationErrCheck tmp) {
		return new Specification<TmpLiquidationErrCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpLiquidationErrCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getCheckDate() != null) {
	                expressions.add(cb.equal(root.<String>get("checkDate"), tmp.getCheckDate()));
	            }
	            return predicate;
	        }
	    };
	}

	@Override
	public Page<TmpLiquidationErrCheck> queryEntitiesByTemplate(
			TmpLiquidationErrCheck tmp, Pageable pageable) {
		Page<TmpLiquidationErrCheck> page = tmpLiquidationErrCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}