package cn.sunline.daoimpl.endauth;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.sunline.dao.endauth.SifSysAuthDao;
import cn.sunline.domain.endauth.SifSysAuth;
import cn.sunline.domain.endauth.SifSysAuthPk;
import cn.sunline.repository.endauth.SifSysAuthRepository;

@Service("SifSysAuthDao")
public class SifSysAuthDaoImpl implements SifSysAuthDao {
    @Autowired
    SifSysAuthRepository sifSysAuthRepository;
	
	public List<SifSysAuth> findAll() {
		return sifSysAuthRepository.findAll();
	}

	
	public List<SifSysAuth> findAll(Sort sort) {
		return sifSysAuthRepository.findAll(sort);
	}

	
	public List<SifSysAuth> findAll(Iterable<SifSysAuthPk> ids) {
		return sifSysAuthRepository.findAll(ids);
	}

	
	public <S extends SifSysAuth> List<S> save(Iterable<S> entities) {
		return sifSysAuthRepository.save(entities);
	}

	
	public void flush() {
		sifSysAuthRepository.flush();
	}

	
	public <S extends SifSysAuth> S saveAndFlush(S entity) {
		return sifSysAuthRepository.saveAndFlush(entity);
	}

	
	public void deleteInBatch(Iterable<SifSysAuth> entities) {
		sifSysAuthRepository.deleteInBatch(entities);
	}

	
	public void deleteAllInBatch() {
		sifSysAuthRepository.deleteAllInBatch();
	}

	
	public SifSysAuth getOne(SifSysAuthPk id) {
		return sifSysAuthRepository.getOne(id);
	}

	
	public Page<SifSysAuth> findAll(Pageable pageable) {
		return sifSysAuthRepository.findAll(pageable);
	}

	
	public <S extends SifSysAuth> S save(S entity) {
		return sifSysAuthRepository.save(entity);
	}

	
	public SifSysAuth findOne(SifSysAuthPk id) {
		return sifSysAuthRepository.findOne(id);
	}

	
	public boolean exists(SifSysAuthPk id) {
		return sifSysAuthRepository.exists(id);
	}

	
	public long count() {
		return sifSysAuthRepository.count();
	}

	
	public void delete(SifSysAuthPk id) {
		sifSysAuthRepository.delete(id);
	}

	
	public void delete(SifSysAuth entity) {
		sifSysAuthRepository.delete(entity);
	}

	
	public void delete(Iterable<? extends SifSysAuth> entities) {
		sifSysAuthRepository.delete(entities);
	}

	
	public void deleteAll() {
		sifSysAuthRepository.deleteAll();
	}

	
	public SifSysAuth findOne(Specification<SifSysAuth> spec) {
		return sifSysAuthRepository.findOne(spec);
	}

	
	public List<SifSysAuth> findAll(Specification<SifSysAuth> spec) {
		return sifSysAuthRepository.findAll(spec);
	}

	
	public Page<SifSysAuth> findAll(Specification<SifSysAuth> spec,
			Pageable pageable) {
		return sifSysAuthRepository.findAll(spec, pageable);
	}

	
	public List<SifSysAuth> findAll(Specification<SifSysAuth> spec, Sort sort) {
		return sifSysAuthRepository.findAll(spec, sort);
	}

	
	public long count(Specification<SifSysAuth> spec) {
		return sifSysAuthRepository.count(spec);
	}
	
	@Override
	public Specification<SifSysAuth> getSpecification(final SifSysAuth tmp){
		return new Specification<SifSysAuth>() {
	        @Override
	        public Predicate toPredicate(Root<SifSysAuth> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getRegisterCd() != null) {
	                expressions.add(cb.equal(root.<String>get("registerCd"), tmp.getRegisterCd()));
	            }
	            if (tmp.getAuthType() != null) {
	                expressions.add(cb.equal(root.<String>get("authType"), tmp.getAuthType()));
	            }
	            if (tmp.getAuthCd() != null) {
	                expressions.add(cb.equal(root.<String>get("authCd"),tmp.getAuthCd()));
	            }
	            if (tmp.getMenuName() != null) {
	                expressions.add(cb.like(root.<String>get("menuName"), "%" + tmp.getMenuName() + "%"));
	            }
	            if (tmp.getAuthUrl() != null) {
	                expressions.add(cb.like(root.<String>get("authUrl"), "%" + tmp.getAuthUrl() + "%"));
	            }
	            if (tmp.getParentAuthCd() != null) {
	                expressions.add(cb.equal(root.<String>get("parentAuthCd"), tmp.getParentAuthCd()));
	            }
	            if (tmp.getRank() != null) {
	                expressions.add(cb.equal(root.<String>get("rank"), tmp.getRank()));
	            }
	            if (tmp.getSortno() != null) {
	                expressions.add(cb.equal(root.<String>get("sortno"), tmp.getSortno()));
	            }
	            
	            return predicate;
	        }
	    };
	}


	@Override
	public List<SifSysAuth> queryEntitiesByTemplate(SifSysAuth tmp) {
		List<SifSysAuth> resultList = sifSysAuthRepository.findAll(getSpecification(tmp));
	    return resultList;
	}

	@Override
	public List<SifSysAuth> queryEntitiesByTemplate(final SifSysAuth tmp, Sort sort){
		
		List<SifSysAuth> resultList = sifSysAuthRepository.findAll(getSpecification(tmp),sort);
	    return resultList;
	}

	@Override
	public Page<SifSysAuth> queryEntitiesByTemplate(final SifSysAuth tmp,
			Pageable pageable) {
		Page<SifSysAuth> page = sifSysAuthRepository.findAll(getSpecification(tmp),pageable);
	    return page;
	}

}
