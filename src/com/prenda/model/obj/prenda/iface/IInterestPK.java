package com.prenda.model.obj.prenda.iface;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: interest.
 * @author autogenerated
 */

public interface IInterestPK {



    /**
     * Return the value associated with the column: day.
	 * @return A Byte object (this.day)
	 */
	Byte getDay();
	

  
    /**  
     * Set the value related to the column: day.
	 * @param day the day value you wish to set
	 */
	void setDay(final Byte day);

    /**
     * Return the value associated with the column: interestid.
	 * @return A Integer object (this.interestid)
	 */
	Integer getInterestid();
	

  
    /**  
     * Set the value related to the column: interestid.
	 * @param interestid the interestid value you wish to set
	 */
	void setInterestid(final Integer interestid);

	// end of interface
}