package com.prenda.model.obj;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class InterestPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7165589234208661714L;
	/** Field mapping. */
	private Branch interestid;
	/** Field mapping. */
	
	private Byte day;
	
	@Basic( optional = false )
	@Column( name = "day", nullable = false  )
	public Byte getDay() {
		return day;
	}

	public void setDay(Byte day) {
		this.day = day;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result
				+ ((interestid == null) ? 0 : interestid.hashCode());
		return result;
	}

	@ManyToOne
	@JoinColumn(name="interestid")
	public Branch getInterestid() {
		return interestid;
	}

	public void setInterestid(Branch interestid) {
		this.interestid = interestid;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterestPK other = (InterestPK) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (interestid == null) {
			if (other.interestid != null)
				return false;
		} else if (!interestid.equals(other.interestid))
			return false;
		return true;
	}


}
