package com.prenda.model.obj.prenda.iface;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: customer.
 * @author autogenerated
 */

public interface ICustomer {



    /**
     * Return the value associated with the column: address.
	 * @return A String object (this.address)
	 */
	String getAddress();
	

  
    /**  
     * Set the value related to the column: address.
	 * @param address the address value you wish to set
	 */
	void setAddress(final String address);

    /**
     * Return the value associated with the column: archive.
	 * @return A Boolean object (this.archive)
	 */
	Boolean isArchive();
	

  
    /**  
     * Set the value related to the column: archive.
	 * @param archive the archive value you wish to set
	 */
	void setArchive(final Boolean archive);

    /**
     * Return the value associated with the column: firstName.
	 * @return A String object (this.firstName)
	 */
	String getFirstName();
	

  
    /**  
     * Set the value related to the column: firstName.
	 * @param firstName the firstName value you wish to set
	 */
	void setFirstName(final String firstName);

    /**
     * Return the value associated with the column: id.
	 * @return A Long object (this.id)
	 */
	Long getId();
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	void setId(final Long id);

    /**
     * Return the value associated with the column: lastName.
	 * @return A String object (this.lastName)
	 */
	String getLastName();
	

  
    /**  
     * Set the value related to the column: lastName.
	 * @param lastName the lastName value you wish to set
	 */
	void setLastName(final String lastName);

    /**
     * Return the value associated with the column: middleName.
	 * @return A String object (this.middleName)
	 */
	String getMiddleName();
	

  
    /**  
     * Set the value related to the column: middleName.
	 * @param middleName the middleName value you wish to set
	 */
	void setMiddleName(final String middleName);

	// end of interface
}