package cn.sunline.tmp.unionpay.check;

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

@Service("TmpUnionPayPayCheckDao")
public class TmpUnionPayPayCheckDaoImpl implements TmpUnionPayPayCheckDao {
	
	@Autowired
	TmpUnionPayPayCheckRepository tmpUnionPayPayCheckRepository;
	
	@Override
	public Specification<TmpUnionPayPayCheck> getSpecification(
			final TmpUnionPayPayCheck tmp) {
		return new Specification<TmpUnionPayPayCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpUnionPayPayCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<TmpUnionPayPayCheck> queryEntitiesByTemplate(
			TmpUnionPayPayCheck tmp, Pageable pageable) {
		Page<TmpUnionPayPayCheck> page = tmpUnionPayPayCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}
