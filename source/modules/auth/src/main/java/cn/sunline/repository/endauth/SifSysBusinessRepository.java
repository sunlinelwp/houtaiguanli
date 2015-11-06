package cn.sunline.repository.endauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.sunline.domain.endauth.SifSysBusiness;
import cn.sunline.domain.endauth.SifSysBusinessPk;

public interface SifSysBusinessRepository extends JpaRepository<SifSysBusiness, SifSysBusinessPk>,JpaSpecificationExecutor<SifSysBusiness> {
	
}
