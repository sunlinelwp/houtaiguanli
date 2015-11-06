package cn.sunline.sequence;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ap_sequence_no")
public class SequenceNo {
	@Id
	private String name;
	private BigInteger nextval;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getNextval() {
		return nextval;
	}

	public void setNextval(BigInteger nextval) {
		this.nextval = nextval;
	}
}
