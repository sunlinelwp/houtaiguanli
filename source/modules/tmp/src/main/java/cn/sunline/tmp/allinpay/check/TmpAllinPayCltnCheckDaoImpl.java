package cn.sunline.tmp.allinpay.check;

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

@Service("TmpAllinPayCltnCheckDao")
public class TmpAllinPayCltnCheckDaoImpl implements TmpAllinPayCltnCheckDao{

	@Autowired
	TmpAllinPayCltnCheckRepository tmpAllinPayCltnCheckRepository;
	
	@Override
	public Specification<TmpAllinPayCltnCheck> getSpecification(
			final TmpAllinPayCltnCheck tmp) {
		return new Specification<TmpAllinPayCltnCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpAllinPayCltnCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<TmpAllinPayCltnCheck> queryEntitiesByTemplate(
			TmpAllinPayCltnCheck tmp, Pageable pageable) {
		Page<TmpAllinPayCltnCheck> page = tmpAllinPayCltnCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}
