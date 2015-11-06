package cn.sunline.tmp.chinapay.check;

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

@Service("TmpChinapayCltnCheckDao")
public class TmpChinapayCltnCheckDaoImpl implements TmpChinapayCltnCheckDao{
	@Autowired
	TmpChinapayCltnCheckRepository tmpChinapayCltnCheckRepository;

	@Override
	public Specification<TmpChinapayCltnCheck> getSpecification(
			final TmpChinapayCltnCheck tmp) {
		return new Specification<TmpChinapayCltnCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpChinapayCltnCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<TmpChinapayCltnCheck> queryEntitiesByTemplate(
			TmpChinapayCltnCheck tmp, Pageable pageable) {
		Page<TmpChinapayCltnCheck> page = tmpChinapayCltnCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}
