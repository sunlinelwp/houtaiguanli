package cn.sunline.tmp.yqrx.check;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service("TmpYqrxAmouService")
public class TmpYqrxAmouServiceImpl implements TmpYqrxAmouService {
	@Resource
	private TmpYqrxAmouRepository tmpYqrxAmouRepository;

	@Override
	public int saveTmpYqrxAmou(List<TmpYqrxAmou> entity) {
		List<TmpYqrxAmou> ret = tmpYqrxAmouRepository.save(entity);

		tmpYqrxAmouRepository.flush();

		return ret.size();
	}

	@Override
	public boolean checkIsExitByKeepdt(String keepdt, String cometp) {
		int count = tmpYqrxAmouRepository.getCountByKeepdt(keepdt, cometp);
		boolean flag = false;
		if(count != 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public Specification<TmpYqrxAmou> getSpecification(final TmpYqrxAmou tmp) {
		return new Specification<TmpYqrxAmou>() {
	        @Override
	        public Predicate toPredicate(Root<TmpYqrxAmou> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        	root.<String>get("keepdt");
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getKeepdt() != null) {
	                expressions.add(cb.equal(root.<String>get("keepdt"), tmp.getKeepdt()));
	            }
	            if (tmp.getCometp() != null) {
	                expressions.add(cb.equal(root.<String>get("cometp"), tmp.getCometp()));
	            }
	            if (tmp.getStates() != null) {
	                expressions.add(cb.equal(root.<String>get("states"), tmp.getStates()));
	            }
	            return predicate;
	        }
	    };
	}


	@Override
	public Page<TmpYqrxAmou> queryEntitiesByTemplateWithPage(TmpYqrxAmou tmp,
			Pageable pageable) {

		Page<TmpYqrxAmou> page = tmpYqrxAmouRepository.findAll(getSpecification(tmp), pageable);
		return page;
	}

	@Override
	@Transactional
	public int updateStates(String frondt, String fronsq, String states) {
		int i = tmpYqrxAmouRepository.changeStates(frondt, fronsq, states);
		return i;
	}
}
