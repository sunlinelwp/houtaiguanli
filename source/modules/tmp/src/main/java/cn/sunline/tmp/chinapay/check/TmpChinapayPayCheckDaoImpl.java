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



@Service("TmpChinapayPayCheckDao")
public class TmpChinapayPayCheckDaoImpl implements TmpChinapayPayCheckDao {
	@Autowired
	TmpChinapayPayCheckRepository tmpChinapayPayCheckRepository;
	
	@Override
	public Specification<TmpChinapayPayCheck> getSpecification(final TmpChinapayPayCheck tmp){
		return new Specification<TmpChinapayPayCheck>() {
	        @Override
	        public Predicate toPredicate(Root<TmpChinapayPayCheck> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<TmpChinapayPayCheck> queryEntitiesByTemplate(
			TmpChinapayPayCheck tmp, Pageable pageable) {
		Page<TmpChinapayPayCheck> page = tmpChinapayPayCheckRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

}
