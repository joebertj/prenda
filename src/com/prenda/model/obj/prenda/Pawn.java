package com.prenda.model.obj.prenda;

import com.github.wwadge.hbnpojogen.persistence.IPojoGenEntity;
import com.prenda.model.obj.prenda.iface.IPawn;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.WeakHashMap;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;


/** 
 * Object mapping for hibernate-handled table: pawn.
 * @author autogenerated
 */

@Entity
@Table(name = "pawn", catalog = "prenda")
public class Pawn implements Cloneable, Serializable, IPojoGenEntity, IPawn {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559018145L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Long> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Long>());
	
	/** hashCode temporary storage. */
	private volatile Long hashCode;
	

	/** Field mapping. */
	private Double advanceInterest;
	/** Field mapping. */
	private Double appraised;
	/** Field mapping. */
	private Byte bcode;
	/** Field mapping. */
	private Long bpid;
	/** Field mapping. */
	private Long branch;
	/** Field mapping. */
	private Timestamp createDate;
	/** Field mapping. */
	private String description;
	/** Field mapping. */
	private String encoder;
	/** Field mapping. */
	private Byte extend;
	/** Field mapping. */
	private Long id = 0L; // init for hibernate bug workaround
	/** Field mapping. */
	private Double loan;
	/** Field mapping. */
	private Date loanDate;
	/** Field mapping. */
	private Long nameid;
	/** Field mapping. */
	private Long pt;
	/** Field mapping. */
	private Long serial;
	/** Field mapping. */
	private Double serviceCharge;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Pawn() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Pawn(Long id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param advanceInterest Double object;
	 * @param appraised Double object;
	 * @param bcode Byte object;
	 * @param bpid Long object;
	 * @param branch Long object;
	 * @param createDate Timestamp object;
	 * @param extend Byte object;
	 * @param id Long object;
	 * @param loan Double object;
	 * @param loanDate Date object;
	 * @param nameid Long object;
	 * @param pt Long object;
	 * @param serial Long object;
	 * @param serviceCharge Double object;
	 */
	public Pawn(Double advanceInterest, Double appraised, Byte bcode, 					
			Long bpid, Long branch, Timestamp createDate, 					
			Byte extend, Long id, Double loan, 					
			Date loanDate, Long nameid, Long pt, 					
			Long serial, Double serviceCharge) {

		this.advanceInterest = advanceInterest;
		this.appraised = appraised;
		this.bcode = bcode;
		this.bpid = bpid;
		this.branch = branch;
		this.createDate = createDate;
		this.extend = extend;
		this.id = id;
		this.loan = loan;
		this.loanDate = loanDate;
		this.nameid = nameid;
		this.pt = pt;
		this.serial = serial;
		this.serviceCharge = serviceCharge;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Pawn.class;
	}
 

    /**
     * Return the value associated with the column: advanceInterest.
	 * @return A Double object (this.advanceInterest)
	 */
	@Basic( optional = false )
	@Column( name = "advance_interest", nullable = false  )
	public Double getAdvanceInterest() {
		return this.advanceInterest;
		
	}
	

  
    /**  
     * Set the value related to the column: advanceInterest.
	 * @param advanceInterest the advanceInterest value you wish to set
	 */
	public void setAdvanceInterest(final Double advanceInterest) {
		this.advanceInterest = advanceInterest;
	}

    /**
     * Return the value associated with the column: appraised.
	 * @return A Double object (this.appraised)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Double getAppraised() {
		return this.appraised;
		
	}
	

  
    /**  
     * Set the value related to the column: appraised.
	 * @param appraised the appraised value you wish to set
	 */
	public void setAppraised(final Double appraised) {
		this.appraised = appraised;
	}

    /**
     * Return the value associated with the column: bcode.
	 * @return A Byte object (this.bcode)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Byte getBcode() {
		return this.bcode;
		
	}
	

  
    /**  
     * Set the value related to the column: bcode.
	 * @param bcode the bcode value you wish to set
	 */
	public void setBcode(final Byte bcode) {
		this.bcode = bcode;
	}

    /**
     * Return the value associated with the column: bpid.
	 * @return A Long object (this.bpid)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Long getBpid() {
		return this.bpid;
		
	}
	

  
    /**  
     * Set the value related to the column: bpid.
	 * @param bpid the bpid value you wish to set
	 */
	public void setBpid(final Long bpid) {
		this.bpid = bpid;
	}

    /**
     * Return the value associated with the column: branch.
	 * @return A Long object (this.branch)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Long getBranch() {
		return this.branch;
		
	}
	

  
    /**  
     * Set the value related to the column: branch.
	 * @param branch the branch value you wish to set
	 */
	public void setBranch(final Long branch) {
		this.branch = branch;
	}

    /**
     * Return the value associated with the column: createDate.
	 * @return A Timestamp object (this.createDate)
	 */
	@Basic( optional = false )
	@Column( name = "create_date", nullable = false  )
	public Timestamp getCreateDate() {
		return this.createDate;
		
	}
	

  
    /**  
     * Set the value related to the column: createDate.
	 * @param createDate the createDate value you wish to set
	 */
	public void setCreateDate(final Timestamp createDate) {
		this.createDate = createDate;
	}

    /**
     * Return the value associated with the column: description.
	 * @return A String object (this.description)
	 */
	@Basic( optional = true )
	@Column( length = 255  )
	public String getDescription() {
		return this.description;
		
	}
	

  
    /**  
     * Set the value related to the column: description.
	 * @param description the description value you wish to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

    /**
     * Return the value associated with the column: encoder.
	 * @return A String object (this.encoder)
	 */
	@Basic( optional = true )
	@Column( length = 20  )
	public String getEncoder() {
		return this.encoder;
		
	}
	

  
    /**  
     * Set the value related to the column: encoder.
	 * @param encoder the encoder value you wish to set
	 */
	public void setEncoder(final String encoder) {
		this.encoder = encoder;
	}

    /**
     * Return the value associated with the column: extend.
	 * @return A Byte object (this.extend)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Byte getExtend() {
		return this.extend;
		
	}
	

  
    /**  
     * Set the value related to the column: extend.
	 * @param extend the extend value you wish to set
	 */
	public void setExtend(final Byte extend) {
		this.extend = extend;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A Long object (this.id)
	 */
    @Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic( optional = false )
	@Column( name = "pid", nullable = false  )
	public Long getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final Long id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null || this.id == 0L) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}

    /**
     * Return the value associated with the column: loan.
	 * @return A Double object (this.loan)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Double getLoan() {
		return this.loan;
		
	}
	

  
    /**  
     * Set the value related to the column: loan.
	 * @param loan the loan value you wish to set
	 */
	public void setLoan(final Double loan) {
		this.loan = loan;
	}

    /**
     * Return the value associated with the column: loanDate.
	 * @return A Date object (this.loanDate)
	 */
	@Basic( optional = false )
	@Column( name = "loan_date", nullable = false  )
	public Date getLoanDate() {
		return this.loanDate;
		
	}
	

  
    /**  
     * Set the value related to the column: loanDate.
	 * @param loanDate the loanDate value you wish to set
	 */
	public void setLoanDate(final Date loanDate) {
		this.loanDate = loanDate;
	}

    /**
     * Return the value associated with the column: nameid.
	 * @return A Long object (this.nameid)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Long getNameid() {
		return this.nameid;
		
	}
	

  
    /**  
     * Set the value related to the column: nameid.
	 * @param nameid the nameid value you wish to set
	 */
	public void setNameid(final Long nameid) {
		this.nameid = nameid;
	}

    /**
     * Return the value associated with the column: pt.
	 * @return A Long object (this.pt)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Long getPt() {
		return this.pt;
		
	}
	

  
    /**  
     * Set the value related to the column: pt.
	 * @param pt the pt value you wish to set
	 */
	public void setPt(final Long pt) {
		this.pt = pt;
	}

    /**
     * Return the value associated with the column: serial.
	 * @return A Long object (this.serial)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Long getSerial() {
		return this.serial;
		
	}
	

  
    /**  
     * Set the value related to the column: serial.
	 * @param serial the serial value you wish to set
	 */
	public void setSerial(final Long serial) {
		this.serial = serial;
	}

    /**
     * Return the value associated with the column: serviceCharge.
	 * @return A Double object (this.serviceCharge)
	 */
	@Basic( optional = false )
	@Column( name = "service_charge", nullable = false  )
	public Double getServiceCharge() {
		return this.serviceCharge;
		
	}
	

  
    /**  
     * Set the value related to the column: serviceCharge.
	 * @param serviceCharge the serviceCharge value you wish to set
	 */
	public void setServiceCharge(final Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Pawn clone() throws CloneNotSupportedException {
		
        final Pawn copy = (Pawn)super.clone();

		copy.setAdvanceInterest(this.getAdvanceInterest());
		copy.setAppraised(this.getAppraised());
		copy.setBcode(this.getBcode());
		copy.setBpid(this.getBpid());
		copy.setBranch(this.getBranch());
		copy.setCreateDate(this.getCreateDate());
		copy.setDescription(this.getDescription());
		copy.setEncoder(this.getEncoder());
		copy.setExtend(this.getExtend());
		copy.setId(this.getId());
		copy.setLoan(this.getLoan());
		copy.setLoanDate(this.getLoanDate());
		copy.setNameid(this.getNameid());
		copy.setPt(this.getPt());
		copy.setSerial(this.getSerial());
		copy.setServiceCharge(this.getServiceCharge());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("advanceInterest: " + this.getAdvanceInterest() + ", ");
		sb.append("appraised: " + this.getAppraised() + ", ");
		sb.append("bcode: " + this.getBcode() + ", ");
		sb.append("bpid: " + this.getBpid() + ", ");
		sb.append("branch: " + this.getBranch() + ", ");
		sb.append("createDate: " + this.getCreateDate() + ", ");
		sb.append("description: " + this.getDescription() + ", ");
		sb.append("encoder: " + this.getEncoder() + ", ");
		sb.append("extend: " + this.getExtend() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("loan: " + this.getLoan() + ", ");
		sb.append("loanDate: " + this.getLoanDate() + ", ");
		sb.append("nameid: " + this.getNameid() + ", ");
		sb.append("pt: " + this.getPt() + ", ");
		sb.append("serial: " + this.getSerial() + ", ");
		sb.append("serviceCharge: " + this.getServiceCharge());
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;
		
		if ( this == aThat ) {
			 return true;
		}

		
		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation(); 
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}
		
		final Pawn that; 
		try {
			that = (Pawn) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getAdvanceInterest() == null) && (that.getAdvanceInterest() == null)) || (getAdvanceInterest() != null && getAdvanceInterest().equals(that.getAdvanceInterest())));
		result = result && (((getAppraised() == null) && (that.getAppraised() == null)) || (getAppraised() != null && getAppraised().equals(that.getAppraised())));
		result = result && (((getBcode() == null) && (that.getBcode() == null)) || (getBcode() != null && getBcode().equals(that.getBcode())));
		result = result && (((getBpid() == null) && (that.getBpid() == null)) || (getBpid() != null && getBpid().equals(that.getBpid())));
		result = result && (((getBranch() == null) && (that.getBranch() == null)) || (getBranch() != null && getBranch().equals(that.getBranch())));
		result = result && (((getCreateDate() == null) && (that.getCreateDate() == null)) || (getCreateDate() != null && getCreateDate().equals(that.getCreateDate())));
		result = result && (((getDescription() == null) && (that.getDescription() == null)) || (getDescription() != null && getDescription().equals(that.getDescription())));
		result = result && (((getEncoder() == null) && (that.getEncoder() == null)) || (getEncoder() != null && getEncoder().equals(that.getEncoder())));
		result = result && (((getExtend() == null) && (that.getExtend() == null)) || (getExtend() != null && getExtend().equals(that.getExtend())));
		result = result && (((getLoan() == null) && (that.getLoan() == null)) || (getLoan() != null && getLoan().equals(that.getLoan())));
		result = result && (((getLoanDate() == null) && (that.getLoanDate() == null)) || (getLoanDate() != null && getLoanDate().equals(that.getLoanDate())));
		result = result && (((getNameid() == null) && (that.getNameid() == null)) || (getNameid() != null && getNameid().equals(that.getNameid())));
		result = result && (((getPt() == null) && (that.getPt() == null)) || (getPt() != null && getPt().equals(that.getPt())));
		result = result && (((getSerial() == null) && (that.getSerial() == null)) || (getSerial() != null && getSerial().equals(that.getSerial())));
		result = result && (((getServiceCharge() == null) && (that.getServiceCharge() == null)) || (getServiceCharge() != null && getServiceCharge().equals(that.getServiceCharge())));
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see java.lang.Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
		if ( this.hashCode == null ) {
			synchronized ( this ) {
				if ( this.hashCode == null ) {
					Long newHashCode = null;

					if ( getId() != null ) {
					newHashCode = SAVED_HASHES.get( getId() );
					}
					
					if ( newHashCode == null ) {
						if ( getId() != null && getId() != 0L) {
							newHashCode = getId();
						} else {
							newHashCode = (long) super.hashCode();

						}
					}
					
					this.hashCode = newHashCode;
				}
			}
		}
		return (int) (this.hashCode & 0xffffff);
	}
	

	
}
