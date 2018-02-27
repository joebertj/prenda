package com.prenda.model.obj;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import com.prenda.model.obj.iface.IUsers;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.WeakHashMap;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;


/** 
 * Object mapping for hibernate-handled table: users.
 * @author autogenerated
 */

@Entity
@Table(name = "users", catalog = "prenda")
public class Users implements Cloneable, Serializable, IPojoGenEntity, IUsers {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559018148L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Byte> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Byte>());
	
	/** hashCode temporary storage. */
	private volatile Byte hashCode;
	

	/** Field mapping. */
	private Boolean archive;
	
	private Branch branch;
	/** Field mapping. */
	private String firstname;
	/** Field mapping. */
	private Byte id = new Byte((byte)0); // init for hibernate bug workaround
	/** Field mapping. */
	private String lastname;
	/** Field mapping. */
	private Byte level;
	/** Field mapping. */
	private Date loanDate;
	/** Field mapping. */
	private String mi;
	/** Field mapping. */
	private String password;
	/** Field mapping. */
	private String username;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Users() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Users(Byte id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param archive Boolean object;
	 * @param branch Long object;
	 * @param id Byte object;
	 */
	public Users(Boolean archive, Branch branch, Byte id) {

		this.archive = archive;
		this.branch = branch;
		this.id = id;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Users.class;
	}
 

    /**
     * Return the value associated with the column: archive.
	 * @return A Boolean object (this.archive)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Boolean isArchive() {
		return this.archive;
		
	}
	

  
    /**  
     * Set the value related to the column: archive.
	 * @param archive the archive value you wish to set
	 */
	public void setArchive(final Boolean archive) {
		this.archive = archive;
	}

    /**
     * Return the value associated with the column: firstname.
	 * @return A String object (this.firstname)
	 */
	@Basic( optional = true )
	@Column( length = 50  )
	public String getFirstname() {
		return this.firstname;
		
	}
	

  
    /**  
     * Set the value related to the column: firstname.
	 * @param firstname the firstname value you wish to set
	 */
	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A Byte object (this.id)
	 */
    @Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic( optional = false )
	@Column( name = "uid", nullable = false  )
	public Byte getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final Byte id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null ) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}

    /**
     * Return the value associated with the column: lastname.
	 * @return A String object (this.lastname)
	 */
	@Basic( optional = true )
	@Column( length = 50  )
	public String getLastname() {
		return this.lastname;
		
	}
	

  
    /**  
     * Set the value related to the column: lastname.
	 * @param lastname the lastname value you wish to set
	 */
	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

    /**
     * Return the value associated with the column: level.
	 * @return A Byte object (this.level)
	 */
	public Byte getLevel() {
		return this.level;
		
	}
	

  
    /**  
     * Set the value related to the column: level.
	 * @param level the level value you wish to set
	 */
	public void setLevel(final Byte level) {
		this.level = level;
	}

    /**
     * Return the value associated with the column: loanDate.
	 * @return A Date object (this.loanDate)
	 */
	@Basic( optional = true )
	@Column( name = "loan_date"  )
	public Date getLoanDate() {
		return this.loanDate;
		
	}
	

  
    /**  
     * Set the value related to the column: loanDate.
	 * @param loanDate the loanDate value you wish to set
	 */
	public void setLoanDate(final Date loanDate) {
		this.loanDate = loanDate;
	}

    /**
     * Return the value associated with the column: mi.
	 * @return A String object (this.mi)
	 */
	@Basic( optional = true )
	@Column( length = 2  )
	public String getMi() {
		return this.mi;
		
	}
	

  
    /**  
     * Set the value related to the column: mi.
	 * @param mi the mi value you wish to set
	 */
	public void setMi(final String mi) {
		this.mi = mi;
	}

    /**
     * Return the value associated with the column: password.
	 * @return A String object (this.password)
	 */
	@Basic( optional = true )
	@Column( length = 50  )
	public String getPassword() {
		return this.password;
		
	}
	

  
    /**  
     * Set the value related to the column: password.
	 * @param password the password value you wish to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

    /**
     * Return the value associated with the column: username.
	 * @return A String object (this.username)
	 */
	@Basic( optional = true )
	@Column( length = 50  )
	public String getUsername() {
		return this.username;
		
	}
	

  
    /**  
     * Set the value related to the column: username.
	 * @param username the username value you wish to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Users clone() throws CloneNotSupportedException {
		
        final Users copy = (Users)super.clone();

		copy.setArchive(this.isArchive());
		copy.setBranch(this.getBranch());
		copy.setFirstname(this.getFirstname());
		copy.setId(this.getId());
		copy.setLastname(this.getLastname());
		copy.setLevel(this.getLevel());
		copy.setLoanDate(this.getLoanDate());
		copy.setMi(this.getMi());
		copy.setPassword(this.getPassword());
		copy.setUsername(this.getUsername());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("archive: " + this.isArchive() + ", ");
		sb.append("branch: " + this.getBranch() + ", ");
		sb.append("firstname: " + this.getFirstname() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("lastname: " + this.getLastname() + ", ");
		sb.append("level: " + this.getLevel() + ", ");
		sb.append("loanDate: " + this.getLoanDate() + ", ");
		sb.append("mi: " + this.getMi() + ", ");
		sb.append("password: " + this.getPassword() + ", ");
		sb.append("username: " + this.getUsername());
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
		
		final Users that; 
		try {
			that = (Users) proxyThat;
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
		result = result && (((isArchive() == null) && (that.isArchive() == null)) || (isArchive() != null && isArchive().equals(that.isArchive())));
		result = result && (((getBranch() == null) && (that.getBranch() == null)) || (getBranch() != null && getBranch().equals(that.getBranch())));
		result = result && (((getFirstname() == null) && (that.getFirstname() == null)) || (getFirstname() != null && getFirstname().equals(that.getFirstname())));
		result = result && (((getLastname() == null) && (that.getLastname() == null)) || (getLastname() != null && getLastname().equals(that.getLastname())));
		result = result && (((getLevel() == null) && (that.getLevel() == null)) || (getLevel() != null && getLevel().equals(that.getLevel())));
		result = result && (((getLoanDate() == null) && (that.getLoanDate() == null)) || (getLoanDate() != null && getLoanDate().equals(that.getLoanDate())));
		result = result && (((getMi() == null) && (that.getMi() == null)) || (getMi() != null && getMi().equals(that.getMi())));
		result = result && (((getPassword() == null) && (that.getPassword() == null)) || (getPassword() != null && getPassword().equals(that.getPassword())));
		result = result && (((getUsername() == null) && (that.getUsername() == null)) || (getUsername() != null && getUsername().equals(that.getUsername())));
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
					Byte newHashCode = null;

					if ( getId() != null ) {
					newHashCode = SAVED_HASHES.get( getId() );
					}
					
					if ( newHashCode == null ) {
						if ( getId() != null ) {
							newHashCode = getId();
						} else {
							newHashCode = (byte)super.hashCode();

						}
					}
					
					this.hashCode = newHashCode;
				}
			}
		}
	return this.hashCode;
	}

	@ManyToOne
	@JoinColumn(name="branch")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	

	
}
