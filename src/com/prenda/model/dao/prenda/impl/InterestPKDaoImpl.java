package com.prenda.model.dao.prenda.impl;

import com.github.wwadge.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.prenda.model.obj.prenda.InterestPK;
import org.springframework.stereotype.Repository;
import com.prenda.model.dao.prenda.InterestPKDao;
import java.io.Serializable;
 
/**
 * DAO for table: InterestPK.
 * @author autogenerated
 */
@Repository
public class InterestPKDaoImpl extends GenericHibernateDaoImpl<InterestPK, Serializable> implements InterestPKDao {
	
	/** Constructor method. */
		public InterestPKDaoImpl() {
			super(InterestPK.class);
		}
	}

