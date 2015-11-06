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

@Service("TmpUnionPayCltnCheckDao")
public class TmpUnionPayCltnCheckDaoImpl implements TmpUnionPayCltnCheckDao{

	@Autowired
	TmpUnionPayCltnCheckRepository tmpUnionPayCltnCheckRepository;
	
	@Override
	public Specification<TmpUnionPayCltnCheck> getSpecification(
			final TmpUnionPayCltnCheck tmp) {
		return new Specification<TmpUnionPayCltnCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpUnionPayCltnCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<TmpUnionPayCltnCheck> queryEntitiesByTemplate(
			TmpUnionPayCltnCheck tmp, Pageable pageable) {
		Page<TmpUnionPayCltnCheck> page = tmpUnionPayCltnCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}
