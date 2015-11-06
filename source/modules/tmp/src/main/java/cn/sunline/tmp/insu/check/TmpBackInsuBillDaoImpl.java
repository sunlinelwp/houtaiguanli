package cn.sunline.tmp.insu.check;

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

@Service("TmpBackInsuBillDao")
public class TmpBackInsuBillDaoImpl implements TmpBackInsuBillDao {
	
	@Autowired
	TmpBackInsuBillRepository tmpBackInsuBillRepository;
	
	@Override
	public Specification<TmpBackInsuBill> getSpecification(final TmpBackInsuBill tmp) {
		return new Specification<TmpBackInsuBill>() {
	        @Override
	        public Predicate toPredicate(Root<TmpBackInsuBill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getDealdt() != null) {
	                expressions.add(cb.equal(root.<String>get("dealdt"), tmp.getDealdt()));
	            }
	            if (tmp.getCheckStatus() != null) {
	                expressions.add(cb.equal(root.<String>get("checkStatus"), tmp.getCheckStatus()));
	            }
	            return predicate;
	        }
	    };
	}

	@Override
	public Page<TmpBackInsuBill> queryEntitiesByTemplate(TmpBackInsuBill tmp,
			Pageable pageable) {
		Page<TmpBackInsuBill> page = tmpBackInsuBillRepository.findAll(getSpecification(tmp), pageable);
	    return page;
	}

	@Override
	public List<TmpBackInsuBill> findAllByTemplate(TmpBackInsuBill tmp) {
		
		return tmpBackInsuBillRepository.findAll(getAllSpecification(tmp));
	}

	@Override
	public Specification<TmpBackInsuBill> getAllSpecification(
			final TmpBackInsuBill tmp) {
		return new Specification<TmpBackInsuBill>() {
	        @Override
	        public Predicate toPredicate(Root<TmpBackInsuBill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getDealdt() != null) {
	                expressions.add(cb.equal(root.<String>get("dealdt"), tmp.getDealdt()));
	            }
	            return predicate;
	        }
	    };
	}

}
