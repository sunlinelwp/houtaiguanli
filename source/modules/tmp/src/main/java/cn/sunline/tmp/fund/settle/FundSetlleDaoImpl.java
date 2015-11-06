package cn.sunline.tmp.fund.settle;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service("FundSetlleDao")
public class FundSetlleDaoImpl implements FundSetlleDao {
	
	@Autowired
	FundSetlleRepository repository;
	
	@Override
	public Specification<FundSetlle> getAllSpecification(final FundSetlle tmp) {
		return new Specification<FundSetlle>() {
	        @Override
	        public Predicate toPredicate(Root<FundSetlle> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getDealdt()!= null) {
	                expressions.add(cb.equal(root.<String>get("dealdt"), tmp.getDealdt()));
	            }
	            if (tmp.getTrantp()!= null) {
	                expressions.add(cb.equal(root.<String>get("trantp"), tmp.getTrantp()));
	            }
	            return predicate;
	        }
	    };
	}

	@Override
	public List<FundSetlle> findAllByTemplate(FundSetlle tmp) {
		// TODO Auto-generated method stub
		return repository.findAll(getAllSpecification(tmp));
	}

}
