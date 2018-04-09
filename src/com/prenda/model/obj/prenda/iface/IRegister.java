package com.prenda.model.obj.prenda.iface;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: register.
 * @author autogenerated
 */

public interface IRegister {



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

    /**
     * Return the value associated with the column: password.
	 * @return A String object (this.password)
	 */
	String getPassword();
	

  
    /**  
     * Set the value related to the column: password.
	 * @param password the password value you wish to set
	 */
	void setPassword(final String password);

	// end of interface
}