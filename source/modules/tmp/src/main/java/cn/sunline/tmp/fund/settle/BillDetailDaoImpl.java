package cn.sunline.tmp.fund.settle;

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


@Service("BillDetailDao")
public class BillDetailDaoImpl implements BillDetailDao {
	@Autowired
	BillDetailRepository repository;
	
	@Override
	public Specification<BillDetail> getAllSpecification(final BillDetail tmp) {
		return new Specification<BillDetail>() {
	        @Override
	        public Predicate toPredicate(Root<BillDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getTrandt()!= null) {
	                expressions.add(cb.equal(root.<String>get("trandt"), tmp.getTrandt()));
	            }
	            return predicate;
	        }
	    };
	}

	@Override
	public Page<BillDetail> billDetailByTemplate(
			BillDetail tmp, Pageable pageable) {
		Page<BillDetail> page = repository.findAll(getAllSpecification(tmp), pageable);
	    return page;
	}

}
