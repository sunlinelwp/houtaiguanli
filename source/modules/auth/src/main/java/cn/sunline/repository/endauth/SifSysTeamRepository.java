package cn.sunline.repository.endauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.sunline.domain.endauth.SifSysTeam;
import cn.sunline.domain.endauth.SifSysTeamPk;

public interface SifSysTeamRepository extends JpaRepository<SifSysTeam, SifSysTeamPk>,JpaSpecificationExecutor<SifSysTeam> {
	
}
