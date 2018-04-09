package com.prenda.model.obj.prenda.iface;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: accounts.
 * @author autogenerated
 */

public interface IAccounts {



    /**
     * Return the value associated with the column: accountname.
	 * @return A String object (this.accountname)
	 */
	String getAccountname();
	

  
    /**  
     * Set the value related to the column: accountname.
	 * @param accountname the accountname value you wish to set
	 */
	void setAccountname(final String accountname);

    /**
     * Return the value associated with the column: id.
	 * @return A Integer object (this.id)
	 */
	Integer getId();
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	void setId(final Integer id);

	// end of interface
}