package com.prenda.model.obj.prenda;

import com.github.wwadge.hbnpojogen.persistence.IPojoGenEntity;
import com.prenda.model.obj.prenda.iface.IJewelryPK;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;


/** 
 * Object mapping for hibernate-handled table: jewelry.
 * @author autogenerated
 */

@Embeddable
public class JewelryPK implements Cloneable, Serializable,  IJewelryPK {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559018151L;

	

	/** Field mapping. */
	@Column( nullable = false  )
	private Integer branchid;

	/** Field mapping. */
	@Column( nullable = false  )
	private Byte caratid;

 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return JewelryPK.class;
	}
 

    /**
     * Return the value associated with the column: branchid.
	 * @return A Integer object (this.branchid)
	 */
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
     * Return the value associated with the column: caratid.
	 * @return A Byte object (this.caratid)
	 */
	public Byte getCaratid() {
		return this.caratid;
		
	}
	

  
    /**  
     * Set the value related to the column: caratid.
	 * @param caratid the caratid value you wish to set
	 */
	public void setCaratid(final Byte caratid) {
		this.caratid = caratid;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public JewelryPK clone() throws CloneNotSupportedException {
		
        final JewelryPK copy = (JewelryPK)super.clone();

		copy.setBranchid(this.getBranchid());
		copy.setCaratid(this.getCaratid());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("branchid: " + this.getBranchid() + ", ");
		sb.append("caratid: " + this.getCaratid());
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

		if (aThat == null)  {
			 return false;
		}
		
		final JewelryPK that; 
		try {
			that = (JewelryPK) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((getBranchid() == null) && (that.getBranchid() == null)) || (getBranchid() != null && getBranchid().equals(that.getBranchid())));
		result = result && (((getCaratid() == null) && (that.getCaratid() == null)) || (getCaratid() != null && getCaratid().equals(that.getCaratid())));
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see java.lang.Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
	int hash = 0;
		hash = hash + getBranchid().hashCode();
		hash = hash + getCaratid().hashCode();
	return hash;
	}
	

	
}
