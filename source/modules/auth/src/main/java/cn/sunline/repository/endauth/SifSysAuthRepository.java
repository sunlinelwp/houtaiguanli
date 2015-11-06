package cn.sunline.repository.endauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.sunline.domain.endauth.SifSysAuth;
import cn.sunline.domain.endauth.SifSysAuthPk;

public interface SifSysAuthRepository extends JpaRepository<SifSysAuth, SifSysAuthPk>, JpaSpecificationExecutor<SifSysAuth> {

}
