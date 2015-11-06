package cn.sunline.sequence;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sunline.sequence.SequenceNo;

public interface SequenceNoRepository extends JpaRepository<SequenceNo,String>{

}
