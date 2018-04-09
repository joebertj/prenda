package com.prenda.model.obj.prenda.iface;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: jewelry.
 * @author autogenerated
 */

public interface IJewelryPK {



    /**
     * Return the value associated with the column: branchid.
	 * @return A Integer object (this.branchid)
	 */
	Integer getBranchid();
	

  
    /**  
     * Set the value related to the column: branchid.
	 * @param branchid the branchid value you wish to set
	 */
	void setBranchid(final Integer branchid);

    /**
     * Return the value associated with the column: caratid.
	 * @return A Byte object (this.caratid)
	 */
	Byte getCaratid();
	

  
    /**  
     * Set the value related to the column: caratid.
	 * @param caratid the caratid value you wish to set
	 */
	void setCaratid(final Byte caratid);

	// end of interface
}