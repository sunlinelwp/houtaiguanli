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

@Service("TmpLiquidationTalCheckDao")
public class TmpLiquidationTalCheckDaoImpl implements TmpLiquidationTalCheckDao {
	
	@Autowired
	TmpLiquidationTalCheckRepository tmpLiquidationTalCheckRepository;
	
	@Override
	public Specification<TmpLiquidationTalCheck> getSpecification(
			final TmpLiquidationTalCheck tmp) {
		return new Specification<TmpLiquidationTalCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpLiquidationTalCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<TmpLiquidationTalCheck> queryEntitiesByTemplate(
			TmpLiquidationTalCheck tmp, Pageable pageable) {
		Page<TmpLiquidationTalCheck> page = tmpLiquidationTalCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}