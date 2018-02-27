package com.prenda.model.obj.iface;
import java.util.Date;

import com.prenda.model.obj.Branch;


/** 
 * Object interface mapping for hibernate-handled table: users.
 * @author autogenerated
 */

public interface IUsers {



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
     * Return the value associated with the column: branch.
	 * @return A Long object (this.branch)
	 */
	Branch getBranch();
	

  
    /**  
     * Set the value related to the column: branch.
	 * @param branch the branch value you wish to set
	 */
	void setBranch(final Branch branch);

    /**
     * Return the value associated with the column: firstname.
	 * @return A String object (this.firstname)
	 */
	String getFirstname();
	

  
    /**  
     * Set the value related to the column: firstname.
	 * @param firstname the firstname value you wish to set
	 */
	void setFirstname(final String firstname);

    /**
     * Return the value associated with the column: id.
	 * @return A Byte object (this.id)
	 */
	Byte getId();
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	void setId(final Byte id);

    /**
     * Return the value associated with the column: lastname.
	 * @return A String object (this.lastname)
	 */
	String getLastname();
	

  
    /**  
     * Set the value related to the column: lastname.
	 * @param lastname the lastname value you wish to set
	 */
	void setLastname(final String lastname);

    /**
     * Return the value associated with the column: level.
	 * @return A Byte object (this.level)
	 */
	Byte getLevel();
	

  
    /**  
     * Set the value related to the column: level.
	 * @param level the level value you wish to set
	 */
	void setLevel(final Byte level);

    /**
     * Return the value associated with the column: loanDate.
	 * @return A Date object (this.loanDate)
	 */
	Date getLoanDate();
	

  
    /**  
     * Set the value related to the column: loanDate.
	 * @param loanDate the loanDate value you wish to set
	 */
	void setLoanDate(final Date loanDate);

    /**
     * Return the value associated with the column: mi.
	 * @return A String object (this.mi)
	 */
	String getMi();
	

  
    /**  
     * Set the value related to the column: mi.
	 * @param mi the mi value you wish to set
	 */
	void setMi(final String mi);

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

    /**
     * Return the value associated with the column: username.
	 * @return A String object (this.username)
	 */
	String getUsername();
	

  
    /**  
     * Set the value related to the column: username.
	 * @param username the username value you wish to set
	 */
	void setUsername(final String username);

	// end of interface
}