package cn.sunline.repository.endauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.sunline.domain.endauth.SifSysRole;
import cn.sunline.domain.endauth.SifSysRolePk;

public interface SifSysRoleRepository extends
		JpaRepository<SifSysRole, SifSysRolePk>, JpaSpecificationExecutor<SifSysRole> {
	
}
