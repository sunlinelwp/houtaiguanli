package cn.sunline.tmp.allinpay.gate.check;

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

@Service("TmpAllinPayPayGateCheckDao")
public class TmpAinPayPayGateCheckDaoImpl implements TmpAinPayPayGateCheckDao {
	
	@Autowired
	TmpAinPayPayGateCheckRepository tmpAllinPayPayGateCheckRepository;
	
	@Override
	public Specification<TmpAinPayPayGateCheck> getSpecification(
			final TmpAinPayPayGateCheck tmp) {
		return new Specification<TmpAinPayPayGateCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpAinPayPayGateCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getCheckDate() != null) {
	                expressions.add(cb.equal(root.<String>get("checkDate"), tmp.getCheckDate()));
	            }
	            if (tmp.getCheckStatus() != null) {
	                expressions.add(cb.equal(root.<String>get("checkStatus"), tmp.getCheckStatus()));
	            }
	            
	            return predicate;
	        }
	    };
	}

	@Override
	public Page<TmpAinPayPayGateCheck> queryEntitiesByTemplate(
			TmpAinPayPayGateCheck tmp, Pageable pageable) {
		Page<TmpAinPayPayGateCheck> page = tmpAllinPayPayGateCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}
