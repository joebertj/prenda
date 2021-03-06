package com.prenda.model.obj.prenda.iface;
import java.util.Date;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: pawn.
 * @author autogenerated
 */

public interface IPawn {



    /**
     * Return the value associated with the column: advanceInterest.
	 * @return A Double object (this.advanceInterest)
	 */
	Double getAdvanceInterest();
	

  
    /**  
     * Set the value related to the column: advanceInterest.
	 * @param advanceInterest the advanceInterest value you wish to set
	 */
	void setAdvanceInterest(final Double advanceInterest);

    /**
     * Return the value associated with the column: appraised.
	 * @return A Double object (this.appraised)
	 */
	Double getAppraised();
	

  
    /**  
     * Set the value related to the column: appraised.
	 * @param appraised the appraised value you wish to set
	 */
	void setAppraised(final Double appraised);

    /**
     * Return the value associated with the column: bcode.
	 * @return A Byte object (this.bcode)
	 */
	Byte getBcode();
	

  
    /**  
     * Set the value related to the column: bcode.
	 * @param bcode the bcode value you wish to set
	 */
	void setBcode(final Byte bcode);

    /**
     * Return the value associated with the column: bpid.
	 * @return A Long object (this.bpid)
	 */
	Long getBpid();
	

  
    /**  
     * Set the value related to the column: bpid.
	 * @param bpid the bpid value you wish to set
	 */
	void setBpid(final Long bpid);

    /**
     * Return the value associated with the column: branch.
	 * @return A Long object (this.branch)
	 */
	Long getBranch();
	

  
    /**  
     * Set the value related to the column: branch.
	 * @param branch the branch value you wish to set
	 */
	void setBranch(final Long branch);

    /**
     * Return the value associated with the column: createDate.
	 * @return A Date object (this.createDate)
	 */
	Date getCreateDate();
	

  
    /**  
     * Set the value related to the column: createDate.
	 * @param createDate the createDate value you wish to set
	 */
	void setCreateDate(final Date createDate);

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
     * Return the value associated with the column: encoder.
	 * @return A String object (this.encoder)
	 */
	String getEncoder();
	

  
    /**  
     * Set the value related to the column: encoder.
	 * @param encoder the encoder value you wish to set
	 */
	void setEncoder(final String encoder);

    /**
     * Return the value associated with the column: extend.
	 * @return A Byte object (this.extend)
	 */
	Byte getExtend();
	

  
    /**  
     * Set the value related to the column: extend.
	 * @param extend the extend value you wish to set
	 */
	void setExtend(final Byte extend);

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
     * Return the value associated with the column: loan.
	 * @return A Double object (this.loan)
	 */
	Double getLoan();
	

  
    /**  
     * Set the value related to the column: loan.
	 * @param loan the loan value you wish to set
	 */
	void setLoan(final Double loan);

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
     * Return the value associated with the column: nameid.
	 * @return A Long object (this.nameid)
	 */
	Long getNameid();
	

  
    /**  
     * Set the value related to the column: nameid.
	 * @param nameid the nameid value you wish to set
	 */
	void setNameid(final Long nameid);

    /**
     * Return the value associated with the column: pt.
	 * @return A Long object (this.pt)
	 */
	Long getPt();
	

  
    /**  
     * Set the value related to the column: pt.
	 * @param pt the pt value you wish to set
	 */
	void setPt(final Long pt);

    /**
     * Return the value associated with the column: serial.
	 * @return A Long object (this.serial)
	 */
	Long getSerial();
	

  
    /**  
     * Set the value related to the column: serial.
	 * @param serial the serial value you wish to set
	 */
	void setSerial(final Long serial);

    /**
     * Return the value associated with the column: serviceCharge.
	 * @return A Double object (this.serviceCharge)
	 */
	Double getServiceCharge();
	

  
    /**  
     * Set the value related to the column: serviceCharge.
	 * @param serviceCharge the serviceCharge value you wish to set
	 */
	void setServiceCharge(final Double serviceCharge);

	// end of interface
}