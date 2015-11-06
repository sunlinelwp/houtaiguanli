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

import cn.sunline.dao.endauth.SifSysRoleAuthDao;
import cn.sunline.domain.endauth.RoleAuth;
import cn.sunline.domain.endauth.SifSysRoleAuth;
import cn.sunline.domain.endauth.SifSysRoleAuthPk;
import cn.sunline.repository.endauth.SifSysRoleAuthRepository;

@Service("SifSysRoleAuthDao")
public class SifSysRoleAuthDaoImpl implements SifSysRoleAuthDao {
    @Autowired
    SifSysRoleAuthRepository sifSysRoleAuthRepository;
	
	@Override
	public List<SifSysRoleAuth> findAll() {
		return sifSysRoleAuthRepository.findAll();
	}

	@Override
	public List<SifSysRoleAuth> findAll(Sort sort) {
		return sifSysRoleAuthRepository.findAll(sort);
	}

	@Override
	public List<SifSysRoleAuth> findAll(Iterable<SifSysRoleAuthPk> ids) {
		return sifSysRoleAuthRepository.findAll(ids);
	}

	@Override
	public <S extends SifSysRoleAuth> List<S> save(Iterable<S> entities) {
		return sifSysRoleAuthRepository.save(entities);
	}

	@Override
	public void flush() {
		sifSysRoleAuthRepository.flush();
	}

	@Override
	public <S extends SifSysRoleAuth> S saveAndFlush(S entity) {
		return sifSysRoleAuthRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<SifSysRoleAuth> entities) {
		sifSysRoleAuthRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch() {
		sifSysRoleAuthRepository.deleteAllInBatch();
	}

	@Override
	public SifSysRoleAuth getOne(SifSysRoleAuthPk id) {
		return sifSysRoleAuthRepository.getOne(id);
	}

	@Override
	public Page<SifSysRoleAuth> findAll(Pageable pageable) {
		return sifSysRoleAuthRepository.findAll(pageable);
	}

	@Override
	public <S extends SifSysRoleAuth> S save(S entity) {
		return sifSysRoleAuthRepository.save(entity);
	}

	@Override
	public SifSysRoleAuth findOne(SifSysRoleAuthPk id) {
		return sifSysRoleAuthRepository.findOne(id);
	}

	@Override
	public boolean exists(SifSysRoleAuthPk id) {
		return sifSysRoleAuthRepository.exists(id);
	}

	@Override
	public long count() {
		return sifSysRoleAuthRepository.count();
	}

	@Override
	public void delete(SifSysRoleAuthPk id) {
		sifSysRoleAuthRepository.delete(id);
	}

	@Override
	public void delete(SifSysRoleAuth entity) {
		sifSysRoleAuthRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends SifSysRoleAuth> entities) {
		sifSysRoleAuthRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		sifSysRoleAuthRepository.deleteAll();
	}

	@Override
	public SifSysRoleAuth findOne(Specification<SifSysRoleAuth> spec) {
		return sifSysRoleAuthRepository.findOne(spec);
	}

	@Override
	public List<SifSysRoleAuth> findAll(Specification<SifSysRoleAuth> spec) {
		return sifSysRoleAuthRepository.findAll(spec);
	}

	@Override
	public Page<SifSysRoleAuth> findAll(Specification<SifSysRoleAuth> spec,
			Pageable pageable) {
		return sifSysRoleAuthRepository.findAll(spec, pageable);
	}

	@Override
	public List<SifSysRoleAuth> findAll(Specification<SifSysRoleAuth> spec,
			Sort sort) {
		return sifSysRoleAuthRepository.findAll(spec, sort);
	}

	@Override
	public long count(Specification<SifSysRoleAuth> spec) {
		return sifSysRoleAuthRepository.count(spec);
	}

	@Override
	public List<String> getAuthCdListByRegisterCdAndAuthTypeAndRoleCd(
			String registerCd, String authType, String roleCd) {
		return sifSysRoleAuthRepository.getAuthCdListByRegisterCdAndAuthTypeAndRoleCd(registerCd, authType, roleCd);
	}
	
	public Specification<SifSysRoleAuth> getSpecification(final RoleAuth tmp){
		return new Specification<SifSysRoleAuth>() {
	        @Override
	        public Predicate toPredicate(Root<SifSysRoleAuth> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if (tmp.getRegisterCd() != null) {
	                expressions.add(cb.equal(root.<String>get("registerCd"), tmp.getRegisterCd()));
	            }
	            if (tmp.getAuthType() != null) {
	                expressions.add(cb.equal(root.<String>get("authType"), tmp.getAuthType()));
	            }
	            if (tmp.getRoleCd() != null) {
	                expressions.add(cb.equal(root.<String>get("roleCd"),tmp.getRoleCd()));
	            }
	            if (tmp.getAuthCd() != null) {
	                expressions.add(cb.equal(root.<String>get("authCd"), tmp.getAuthCd()));
	            }
	            
	            return predicate;
	        }
	    };
	}

	@Override
	public List<SifSysRoleAuth> queryEntitiesByTemplate(RoleAuth tmp) {
		return sifSysRoleAuthRepository.findAll(getSpecification(tmp));
	}
	
	@Override
	public List<SifSysRoleAuth> queryEntitiesByTemplate(RoleAuth tmp, Sort sort) {
		return sifSysRoleAuthRepository.findAll(getSpecification(tmp), sort);
	}

	@Override
	public Page<SifSysRoleAuth> queryEntitiesByTemplate(final RoleAuth tmp,
			Pageable pageable) {
		Page<SifSysRoleAuth> page = sifSysRoleAuthRepository.findAll(getSpecification(tmp),pageable);
	    return page;
	}

}
