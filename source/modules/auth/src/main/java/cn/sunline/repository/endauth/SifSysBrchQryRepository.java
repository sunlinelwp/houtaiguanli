package cn.sunline.repository.endauth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.sunline.domain.endauth.SifSysBrchQry;
import cn.sunline.domain.endauth.SifSysBrchQryPk;

public interface SifSysBrchQryRepository extends JpaRepository<SifSysBrchQry, SifSysBrchQryPk>,JpaSpecificationExecutor<SifSysBrchQry> {
	
	/**
	 * 根据注册机构号和机构号查询
	 * @param registerCd 注册机构号
	 * @param branchCd	机构号
	 * @return 查询到的实体对象集合
	 */
	List<SifSysBrchQry> findByRegisterCdAndBranchCd(String registerCd, String branchCd);
}
