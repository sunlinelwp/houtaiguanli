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

@Service("TmpLiquidationSubCheckDao")
public class TmpLiquidationSubCheckDaoImpl implements TmpLiquidationSubCheckDao {
	
	@Autowired
	TmpLiquidationSubCheckRepository tmpLiquidationSubCheckRepository;
	
	@Override
	public Specification<TmpLiquidationSubCheck> getSpecification(
			final TmpLiquidationSubCheck tmp) {
		return new Specification<TmpLiquidationSubCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpLiquidationSubCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<TmpLiquidationSubCheck> queryEntitiesByTemplate(
			TmpLiquidationSubCheck tmp, Pageable pageable) {
		Page<TmpLiquidationSubCheck> page = tmpLiquidationSubCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}