package cn.sunline.repository.endauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.sunline.domain.endauth.SifSysUser;
import cn.sunline.domain.endauth.SifSysUserPk;

public interface SifSysUserRepository extends JpaRepository<SifSysUser, SifSysUserPk>,JpaSpecificationExecutor<SifSysUser> {
	
}
