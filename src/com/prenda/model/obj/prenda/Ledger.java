package com.prenda.model.obj.prenda;

import com.github.wwadge.hbnpojogen.persistence.IPojoGenEntity;
import com.prenda.model.obj.prenda.iface.ILedger;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.WeakHashMap;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;


/** 
 * Object mapping for hibernate-handled table: ledger.
 * @author autogenerated
 */

@Entity
@Table(name = "ledger", catalog = "prenda")
public class Ledger implements Cloneable, Serializable, IPojoGenEntity, ILedger {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559018149L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Long> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Long>());
	
	/** hashCode temporary storage. */
	private volatile Long hashCode;
	

	/** Field mapping. */
	private Integer accountid;
	/** Field mapping. */
	private Double amount;
	/** Field mapping. */
	private Integer branchid;
	/** Field mapping. */
	private String description;
	/** Field mapping. */
	private Boolean drcr;
	/** Field mapping. */
	private String encoder;
	/** Field mapping. */
	private Long id;
	/** Field mapping. */
	private Date ledgerDate;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Ledger() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Ledger(Long id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param accountid Integer object;
	 * @param amount Double object;
	 * @param branchid Integer object;
	 * @param encoder String object;
	 * @param id Long object;
	 * @param ledgerDate Date object;
	 */
	public Ledger(Integer accountid, Double amount, Integer branchid, 					
			String encoder, Long id, Date ledgerDate) {

		this.accountid = accountid;
		this.amount = amount;
		this.branchid = branchid;
		this.encoder = encoder;
		this.id = id;
		this.ledgerDate = ledgerDate;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Ledger.class;
	}
 

    /**
     * Return the value associated with the column: accountid.
	 * @return A Integer object (this.accountid)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Integer getAccountid() {
		return this.accountid;
		
	}
	

  
    /**  
     * Set the value related to the column: accountid.
	 * @param accountid the accountid value you wish to set
	 */
	public void setAccountid(final Integer accountid) {
		this.accountid = accountid;
	}

    /**
     * Return the value associated with the column: amount.
	 * @return A Double object (this.amount)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Double getAmount() {
		return this.amount;
		
	}
	

  
    /**  
     * Set the value related to the column: amount.
	 * @param amount the amount value you wish to set
	 */
	public void setAmount(final Double amount) {
		this.amount = amount;
	}

    /**
     * Return the value associated with the column: branchid.
	 * @return A Integer object (this.branchid)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Integer getBranchid() {
		return this.branchid;
		
	}
	

  
    /**  
     * Set the value related to the column: branchid.
	 * @param branchid the branchid value you wish to set
	 */
	public void setBranchid(final Integer branchid) {
		this.branchid = branchid;
	}

    /**
     * Return the value associated with the column: description.
	 * @return A String object (this.description)
	 */
	@Basic( optional = true )
	@Column( length = 100  )
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
     * Return the value associated with the column: drcr.
	 * @return A Boolean object (this.drcr)
	 */
	public Boolean isDrcr() {
		return this.drcr;
		
	}
	

  
    /**  
     * Set the value related to the column: drcr.
	 * @param drcr the drcr value you wish to set
	 */
	public void setDrcr(final Boolean drcr) {
		this.drcr = drcr;
	}

    /**
     * Return the value associated with the column: encoder.
	 * @return A String object (this.encoder)
	 */
	@Basic( optional = false )
	@Column( nullable = false, length = 50  )
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
     * Return the value associated with the column: id.
	 * @return A Long object (this.id)
	 */
    @Id 
	@Basic( optional = false )
	@Column( name = "ledgerid", nullable = false  )
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
     * Return the value associated with the column: ledgerDate.
	 * @return A Date object (this.ledgerDate)
	 */
	@Basic( optional = false )
	@Column( name = "ledger_date", nullable = false  )
	public Date getLedgerDate() {
		return this.ledgerDate;
		
	}
	

  
    /**  
     * Set the value related to the column: ledgerDate.
	 * @param ledgerDate the ledgerDate value you wish to set
	 */
	public void setLedgerDate(final Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Ledger clone() throws CloneNotSupportedException {
		
        final Ledger copy = (Ledger)super.clone();

		copy.setAccountid(this.getAccountid());
		copy.setAmount(this.getAmount());
		copy.setBranchid(this.getBranchid());
		copy.setDescription(this.getDescription());
		copy.setDrcr(this.isDrcr());
		copy.setEncoder(this.getEncoder());
		copy.setId(this.getId());
		copy.setLedgerDate(this.getLedgerDate());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("accountid: " + this.getAccountid() + ", ");
		sb.append("amount: " + this.getAmount() + ", ");
		sb.append("branchid: " + this.getBranchid() + ", ");
		sb.append("description: " + this.getDescription() + ", ");
		sb.append("drcr: " + this.isDrcr() + ", ");
		sb.append("encoder: " + this.getEncoder() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("ledgerDate: " + this.getLedgerDate());
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
		
		final Ledger that; 
		try {
			that = (Ledger) proxyThat;
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
		result = result && (((getAccountid() == null) && (that.getAccountid() == null)) || (getAccountid() != null && getAccountid().equals(that.getAccountid())));
		result = result && (((getAmount() == null) && (that.getAmount() == null)) || (getAmount() != null && getAmount().equals(that.getAmount())));
		result = result && (((getBranchid() == null) && (that.getBranchid() == null)) || (getBranchid() != null && getBranchid().equals(that.getBranchid())));
		result = result && (((getDescription() == null) && (that.getDescription() == null)) || (getDescription() != null && getDescription().equals(that.getDescription())));
		result = result && (((isDrcr() == null) && (that.isDrcr() == null)) || (isDrcr() != null && isDrcr().equals(that.isDrcr())));
		result = result && (((getEncoder() == null) && (that.getEncoder() == null)) || (getEncoder() != null && getEncoder().equals(that.getEncoder())));
		result = result && (((getLedgerDate() == null) && (that.getLedgerDate() == null)) || (getLedgerDate() != null && getLedgerDate().equals(that.getLedgerDate())));
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
