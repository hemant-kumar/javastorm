package com.javastorm.hibernatesharding.entity;

import java.io.Serializable;

/**
 * This class is intended for providing a basis for all entities that require sharding support.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 18/03/2013
 */
public abstract class HibernateEntity implements Serializable 
{
	private static final long serialVersionUID = 10234L;

	/**
	 * This function is intended to provide the appropriate shard key.
	 * @return Shard Key
	 */
	public abstract Object getShard();
}
