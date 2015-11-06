package cn.sunline.tmp.insu.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InsuSetlleRepository extends JpaRepository<InsuSetlle, InsuSetllePK>,JpaSpecificationExecutor<InsuSetlle> {
	
}