package com.javastorm.hibernatesharding.datasource;

import com.javastorm.hibernatesharding.entity.HibernateEntity;

/**
 * This class is intended for providing a cache for threads.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 18/03/2013
 */
public class ShardCache 
{
	private static final ThreadLocal<HibernateEntity> cache_ = new ThreadLocal<HibernateEntity>();
	
	public static void setEntity(HibernateEntity entity) {
		cache_.set(entity);
	}
	
	public static HibernateEntity getEntity() {
		return (HibernateEntity)cache_.get();
	}
	
	public static void clearEntity() {
		cache_.remove();
	}
}
