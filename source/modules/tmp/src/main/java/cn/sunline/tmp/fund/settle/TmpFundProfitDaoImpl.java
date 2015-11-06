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


@Service("TmpFundProfitDao")
public class TmpFundProfitDaoImpl implements TmpFundProfitDao {
	@Autowired
	TmpFundProfitRepository repository;
	
	@Override
	public Specification<TmpFundProfit> getAllSpecification(final TmpFundProfit tmp) {
		return new Specification<TmpFundProfit>() {
	        @Override
	        public Predicate toPredicate(Root<TmpFundProfit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getDealdt()!= null) {
	                expressions.add(cb.equal(root.<String>get("dealdt"), tmp.getDealdt()));
	            }
	            return predicate;
	        }
	    };
	}

	@Override
	public List<TmpFundProfit> findAllByTemplate(TmpFundProfit tmp) {
		return repository.findAll(getAllSpecification(tmp));
	}

}
