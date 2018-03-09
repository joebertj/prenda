package com.prenda.model.obj.prenda.iface;
import java.util.Date;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: journal.
 * @author autogenerated
 */

public interface IJournal {



    /**
     * Return the value associated with the column: accountid.
	 * @return A Byte object (this.accountid)
	 */
	Byte getAccountid();
	

  
    /**  
     * Set the value related to the column: accountid.
	 * @param accountid the accountid value you wish to set
	 */
	void setAccountid(final Byte accountid);

    /**
     * Return the value associated with the column: amount.
	 * @return A Double object (this.amount)
	 */
	Double getAmount();
	

  
    /**  
     * Set the value related to the column: amount.
	 * @param amount the amount value you wish to set
	 */
	void setAmount(final Double amount);

    /**
     * Return the value associated with the column: branchid.
	 * @return A Byte object (this.branchid)
	 */
	Byte getBranchid();
	

  
    /**  
     * Set the value related to the column: branchid.
	 * @param branchid the branchid value you wish to set
	 */
	void setBranchid(final Byte branchid);

    /**
     * Return the value associated with the column: description.
	 * @return A String object (this.description)
	 */
	String getDescription();
	

  
    /**  
     * Set the value related to the column: description.
	 * @param description the description value you wish to set
	 */
	void setDescription(final String description);

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
     * Return the value associated with the column: journalDate.
	 * @return A Date object (this.journalDate)
	 */
	Date getJournalDate();
	

  
    /**  
     * Set the value related to the column: journalDate.
	 * @param journalDate the journalDate value you wish to set
	 */
	void setJournalDate(final Date journalDate);

    /**
     * Return the value associated with the column: journalGroup.
	 * @return A String object (this.journalGroup)
	 */
	String getJournalGroup();
	

  
    /**  
     * Set the value related to the column: journalGroup.
	 * @param journalGroup the journalGroup value you wish to set
	 */
	void setJournalGroup(final String journalGroup);

	// end of interface
}