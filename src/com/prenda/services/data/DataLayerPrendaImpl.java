package com.prenda.services.data;

import com.prenda.factories.prenda.*;
import com.prenda.model.obj.prenda.Accounts;
import com.prenda.model.obj.prenda.Branch;
import com.prenda.model.obj.prenda.Customer;
import com.prenda.model.obj.prenda.Genkey;
import com.prenda.model.obj.prenda.Interest;
import com.prenda.model.obj.prenda.InterestPK;
import com.prenda.model.obj.prenda.Jewelry;
import com.prenda.model.obj.prenda.JewelryPK;
import com.prenda.model.obj.prenda.Journal;
import com.prenda.model.obj.prenda.Ledger;
import com.prenda.model.obj.prenda.Level;
import com.prenda.model.obj.prenda.Limits;
import com.prenda.model.obj.prenda.Page;
import com.prenda.model.obj.prenda.Pawn;
import com.prenda.model.obj.prenda.Pullout;
import com.prenda.model.obj.prenda.Redeem;
import com.prenda.model.obj.prenda.Register;
import com.prenda.model.obj.prenda.Users;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import java.util.Collection;
import com.github.wwadge.hbnpojogen.persistence.IPojoGenEntity;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import com.github.wwadge.hbnpojogen.persistence.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
/** 
 * Data layer.
 * @author autogenerated
 */
@org.springframework.stereotype.Component
public class DataLayerPrendaImpl implements DataLayerPrenda {
	/** Singleton reference to this class. */
	private static DataLayerPrenda instance;
	/** map lock. */
	private static Object daoMapLock = new Object();

	/** Internal handle. */
	private static Map<Class<?>, GenericDAO<?, ?>> daoMap = null; 
	
		/** Inner handle. */
	private static ApplicationContext context;
 /** Sessionfactory in use. Filled in by Spring. */ 
    private SessionFactory sessionFactory = null;
	
	/** Handle to get back ourselves and perform correct injection. 
	 * @param ctxt filled by spring
	 */
	@Autowired
	public void setApplicationContext(ApplicationContext ctxt) {
		DataLayerPrendaImpl.context = ctxt;
	}
	
	
		
 	/**
     * Set session factory.
     * @param sessionFactory sessionfactory to use. 
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /** 
	* Returns a DAO instance of the given type.
	* @param <T> Type
	* @param persistentObject to get
	* @return GenericDAO<T, ?> object
     */
    @SuppressWarnings("unchecked")
    private <T> GenericDAO<T, ?> getDAO(final T persistentObject) {
		T persistent = persistentObject;

		synchronized (daoMapLock) {
    		if (daoMap == null) {
    			daoMap = new ConcurrentHashMap<Class<?>, GenericDAO<?, ?>>(); 
	 	   		daoMap.put(Accounts.class, HibernatePrendaDaoFactory.getAccountsDao());
	 	   		daoMap.put(Branch.class, HibernatePrendaDaoFactory.getBranchDao());
	 	   		daoMap.put(Customer.class, HibernatePrendaDaoFactory.getCustomerDao());
	 	   		daoMap.put(Genkey.class, HibernatePrendaDaoFactory.getGenkeyDao());
	 	   		daoMap.put(Interest.class, HibernatePrendaDaoFactory.getInterestDao());
	 	   		daoMap.put(Jewelry.class, HibernatePrendaDaoFactory.getJewelryDao());
	 	   		daoMap.put(Journal.class, HibernatePrendaDaoFactory.getJournalDao());
	 	   		daoMap.put(Ledger.class, HibernatePrendaDaoFactory.getLedgerDao());
	 	   		daoMap.put(Level.class, HibernatePrendaDaoFactory.getLevelDao());
	 	   		daoMap.put(Limits.class, HibernatePrendaDaoFactory.getLimitsDao());
	 	   		daoMap.put(Page.class, HibernatePrendaDaoFactory.getPageDao());
	 	   		daoMap.put(Pawn.class, HibernatePrendaDaoFactory.getPawnDao());
	 	   		daoMap.put(Pullout.class, HibernatePrendaDaoFactory.getPulloutDao());
	 	   		daoMap.put(Redeem.class, HibernatePrendaDaoFactory.getRedeemDao());
	 	   		daoMap.put(Register.class, HibernatePrendaDaoFactory.getRegisterDao());
	 	   		daoMap.put(Users.class, HibernatePrendaDaoFactory.getUsersDao());
    		}
		 }
		if (persistentObject instanceof HibernateProxy) {
			persistent = (T) ((HibernateProxy) persistentObject).getHibernateLazyInitializer().getImplementation();
		} 
		
		GenericDAO<T, ?> result = (GenericDAO<T, ?>) daoMap.get(persistent.getClass());
		if (result == null) {
			throw new IllegalAccessError("The given object is of an incorrect type. ");
		}
		return result;
    }

    /**
     * Deletes the given object from disk.
     * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to delete
     */
    public <T> void delete(T persistentObject) {
    	    	getDAO(persistentObject).delete(persistentObject);
    }
    /**
     * Refresh the object $class.className from disk.
     * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to reload
     */
    public <T> void refresh(T persistentObject) {
	    	getDAO(persistentObject).refresh(persistentObject);
    }

    /**
     * Saves the given object to disk.
     * @param persistentObject Object to save
	 * @param <T> A DataLayerObject-derived type
     * @return Identifier of saved object 
     */
    public <T> Serializable save(T persistentObject) {
        return getDAO(persistentObject).save(persistentObject);
    }
    /**
     * Saves or updates the given $class.className object to disk.
	 * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to save 
     */
    public <T> void saveOrUpdate(T persistentObject) {
        getDAO(persistentObject).saveOrUpdate(persistentObject);
    }
    /**
     * Updates the given object to disk.
	 * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to update 
     */
    public <T> void update(T persistentObject) {
        getDAO(persistentObject).update(persistentObject);
    }


    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Accounts obj) directly
     * @param id Identifier to delete
     */
    public void deleteAccounts(final Byte id)  {
        HibernatePrendaDaoFactory.getAccountsDao().delete(loadAccounts(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Accounts object
     */
    public Accounts loadAccounts(final Byte id) {
        return HibernatePrendaDaoFactory.getAccountsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Accounts getAccounts(final Byte id) {
        return HibernatePrendaDaoFactory.getAccountsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Branch obj) directly
     * @param id Identifier to delete
     */
    public void deleteBranch(final Integer id)  {
        HibernatePrendaDaoFactory.getBranchDao().delete(loadBranch(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Branch object
     */
    public Branch loadBranch(final Integer id) {
        return HibernatePrendaDaoFactory.getBranchDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Branch getBranch(final Integer id) {
        return HibernatePrendaDaoFactory.getBranchDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Customer obj) directly
     * @param id Identifier to delete
     */
    public void deleteCustomer(final Long id)  {
        HibernatePrendaDaoFactory.getCustomerDao().delete(loadCustomer(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Customer object
     */
    public Customer loadCustomer(final Long id) {
        return HibernatePrendaDaoFactory.getCustomerDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Customer getCustomer(final Long id) {
        return HibernatePrendaDaoFactory.getCustomerDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Genkey obj) directly
     * @param id Identifier to delete
     */
    public void deleteGenkey(final Long id)  {
        HibernatePrendaDaoFactory.getGenkeyDao().delete(loadGenkey(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Genkey object
     */
    public Genkey loadGenkey(final Long id) {
        return HibernatePrendaDaoFactory.getGenkeyDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Genkey getGenkey(final Long id) {
        return HibernatePrendaDaoFactory.getGenkeyDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Interest obj) directly
     * @param id Identifier to delete
     */
    public void deleteInterest(final InterestPK id)  {
        HibernatePrendaDaoFactory.getInterestDao().delete(loadInterest(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Interest object
     */
    public Interest loadInterest(final InterestPK id) {
        return HibernatePrendaDaoFactory.getInterestDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Interest getInterest(final InterestPK id) {
        return HibernatePrendaDaoFactory.getInterestDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Jewelry obj) directly
     * @param id Identifier to delete
     */
    public void deleteJewelry(final JewelryPK id)  {
        HibernatePrendaDaoFactory.getJewelryDao().delete(loadJewelry(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Jewelry object
     */
    public Jewelry loadJewelry(final JewelryPK id) {
        return HibernatePrendaDaoFactory.getJewelryDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Jewelry getJewelry(final JewelryPK id) {
        return HibernatePrendaDaoFactory.getJewelryDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Journal obj) directly
     * @param id Identifier to delete
     */
    public void deleteJournal(final Long id)  {
        HibernatePrendaDaoFactory.getJournalDao().delete(loadJournal(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Journal object
     */
    public Journal loadJournal(final Long id) {
        return HibernatePrendaDaoFactory.getJournalDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Journal getJournal(final Long id) {
        return HibernatePrendaDaoFactory.getJournalDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Ledger obj) directly
     * @param id Identifier to delete
     */
    public void deleteLedger(final Long id)  {
        HibernatePrendaDaoFactory.getLedgerDao().delete(loadLedger(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Ledger object
     */
    public Ledger loadLedger(final Long id) {
        return HibernatePrendaDaoFactory.getLedgerDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Ledger getLedger(final Long id) {
        return HibernatePrendaDaoFactory.getLedgerDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Level obj) directly
     * @param id Identifier to delete
     */
    public void deleteLevel(final Byte id)  {
        HibernatePrendaDaoFactory.getLevelDao().delete(loadLevel(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Level object
     */
    public Level loadLevel(final Byte id) {
        return HibernatePrendaDaoFactory.getLevelDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Level getLevel(final Byte id) {
        return HibernatePrendaDaoFactory.getLevelDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Limits obj) directly
     * @param id Identifier to delete
     */
    public void deleteLimits(final Integer id)  {
        HibernatePrendaDaoFactory.getLimitsDao().delete(loadLimits(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Limits object
     */
    public Limits loadLimits(final Integer id) {
        return HibernatePrendaDaoFactory.getLimitsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Limits getLimits(final Integer id) {
        return HibernatePrendaDaoFactory.getLimitsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Page obj) directly
     * @param id Identifier to delete
     */
    public void deletePage(final Integer id)  {
        HibernatePrendaDaoFactory.getPageDao().delete(loadPage(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Page object
     */
    public Page loadPage(final Integer id) {
        return HibernatePrendaDaoFactory.getPageDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Page getPage(final Integer id) {
        return HibernatePrendaDaoFactory.getPageDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Pawn obj) directly
     * @param id Identifier to delete
     */
    public void deletePawn(final Long id)  {
        HibernatePrendaDaoFactory.getPawnDao().delete(loadPawn(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Pawn object
     */
    public Pawn loadPawn(final Long id) {
        return HibernatePrendaDaoFactory.getPawnDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Pawn getPawn(final Long id) {
        return HibernatePrendaDaoFactory.getPawnDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Pullout obj) directly
     * @param id Identifier to delete
     */
    public void deletePullout(final Long id)  {
        HibernatePrendaDaoFactory.getPulloutDao().delete(loadPullout(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Pullout object
     */
    public Pullout loadPullout(final Long id) {
        return HibernatePrendaDaoFactory.getPulloutDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Pullout getPullout(final Long id) {
        return HibernatePrendaDaoFactory.getPulloutDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Redeem obj) directly
     * @param id Identifier to delete
     */
    public void deleteRedeem(final Long id)  {
        HibernatePrendaDaoFactory.getRedeemDao().delete(loadRedeem(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Redeem object
     */
    public Redeem loadRedeem(final Long id) {
        return HibernatePrendaDaoFactory.getRedeemDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Redeem getRedeem(final Long id) {
        return HibernatePrendaDaoFactory.getRedeemDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Register obj) directly
     * @param id Identifier to delete
     */
    public void deleteRegister(final Long id)  {
        HibernatePrendaDaoFactory.getRegisterDao().delete(loadRegister(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Register object
     */
    public Register loadRegister(final Long id) {
        return HibernatePrendaDaoFactory.getRegisterDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Register getRegister(final Long id) {
        return HibernatePrendaDaoFactory.getRegisterDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (Users obj) directly
     * @param id Identifier to delete
     */
    public void deleteUsers(final Integer id)  {
        HibernatePrendaDaoFactory.getUsersDao().delete(loadUsers(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a Users object
     */
    public Users loadUsers(final Integer id) {
        return HibernatePrendaDaoFactory.getUsersDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public Users getUsers(final Integer id) {
        return HibernatePrendaDaoFactory.getUsersDao().get(id);
    }  
    /** Returns a singleton instance of this class.
     * @return an singleton instance
     */
    public static synchronized DataLayerPrenda getInstance() {
        
        if (instance == null) {
        	if (context == null) {
        		throw new IllegalStateException("Context is null. Make sure Spring is initialized.");
        	}
     		instance = (DataLayerPrenda) context.getBean("dataLayerPrendaImpl");
        }
        
        return instance; 
    }
    /** Returns a query handle.
     * @param query Query to use
     * @return A query instance
     */
     public Query createQuery(final String query) {
        return this.sessionFactory.getCurrentSession().createQuery(query);
    }
    /** Returns a criteria handle.
     * @param criteria Criteria to use
     * @return A criteria instance
     */
     public Criteria createCriteria(final String criteria) {
        return this.sessionFactory.getCurrentSession().createCriteria(criteria);
    }
    /** Returns a Query handle based on your package-level named query.
     * @param query Query to use
     * @return A query instance
     */
     public Query getNamedQuery(final String query) {
        return this.sessionFactory.getCurrentSession().getNamedQuery(query);
    }
    /** Create a new Criteria instance, for the given entity class, or a superclass of an entity class.
	* @param persistentObject a class, which is persistent, or has persistent subclasses 
	* @return Criteria instance
	*/
	@SuppressWarnings("unchecked")
	public Criteria createCriteria(Class persistentObject) {
        return this.sessionFactory.getCurrentSession().createCriteria(persistentObject);
    }
    /** Flushes the currently open session.
	*/
	public void flushSession() {
        this.sessionFactory.getCurrentSession().flush();
    }
    /** Clears the currently open session.
	*/
	public void clearSession() {
        this.sessionFactory.getCurrentSession().clear();
    }
    /** Flushes and clears the currently open session.
	*/
	public void flushAndClearSession() {
		flushSession();
		clearSession();
    }
	/** Call currentSession.replicate.
	 * @param obj to replicate
	 * @param replicationMode mode
	 */ 
	public void replicate(Object obj, ReplicationMode replicationMode) {
		this.sessionFactory.getCurrentSession().replicate(obj, replicationMode);
	}
	/** Hibernate Merge. 
	 * @param obj to merge
	 * @return obj merged.
	 */
	public Object merge(Object obj) {
		return this.sessionFactory.getCurrentSession().merge(obj);
	}
	/** Returns the current session.
	 * @return the currently active session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	/** Returns a query handle.
     * @param query Query to use
     * @return A query instance
     */
     public SQLQuery createSQLQuery(final String query) {
        return this.sessionFactory.getCurrentSession().createSQLQuery(query);
    }
    /** Remove this instance from the session cache. 
	 * Changes to the instance will not be synchronized with the database
	 * @param obj object to evict
	 */
	public void evict(Object obj) {
        this.sessionFactory.getCurrentSession().evict(obj);
    }
    /**
     * Return the persistent instance of the given entity class with the given 
     * identifier, or null if there is no such persistent instance. 
     * (If the instance, or a proxy for the instance, is already 
     * associated with the session, return that instance or proxy)
     *
     * @param clazz a persistent class
     * @param id a valid identifier of an existing persistent instance of the class
     * @return a persistent instance or null
     * @throws HibernateException
     */
	public Object get(Class<?> clazz, Serializable id) throws HibernateException {
        return this.sessionFactory.getCurrentSession().get(clazz, id);
    }	


    /**
     * Return the persistent instance of the given entity class with the given identifier, assuming that the instance exists.
     *You should not use this method to determine if an instance exists (use get() instead). Use this only to retrieve an instance that you assume exists, where non-existence would be an actual error.
     *
     * @param clazz a persistent class
     * @param id a valid identifier of an existing persistent instance of the class
     * @return the persistent instance or proxy
     * @throws HibernateException
     */
	public Object load(Class<?> clazz, Serializable id) throws HibernateException {
        return this.sessionFactory.getCurrentSession().load(clazz, id);  
    }
	/**
	 * Reattaches the given entity to the current session using LockMode.NONE
	 *
	 * @param entity to reattach
	 */
	public void reattachEntityWithNoLock(IPojoGenEntity entity) {
		if (entity != null) {
        		this.sessionFactory.getCurrentSession().lock(entity, LockMode.NONE);
    		}
	}
	/**
	 * Reattaches the given entities to the current session.
	 *
	 * @param entities to attach
	 */
	public void reattachEntitiesWithNoLock(Collection<? extends IPojoGenEntity> entities) {
   		if (entities != null) {
		       for (IPojoGenEntity entity : entities) {
            		this.sessionFactory.getCurrentSession().lock(entity, LockMode.NONE);
        		 }
    		}
	}}

