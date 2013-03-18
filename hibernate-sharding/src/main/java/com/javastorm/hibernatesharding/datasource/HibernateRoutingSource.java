package com.javastorm.hibernatesharding.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.javastorm.hibernatesharding.entity.HibernateEntity;

/**
 * This class is intended for providing the shard key.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 18/03/2013
 */
public class HibernateRoutingSource extends AbstractRoutingDataSource
{
	@Override
	protected Object determineCurrentLookupKey() {
		HibernateEntity entity = ShardCache.getEntity();
		if(entity == null) {
			return "";
		}
		else {
			return entity.getShard();
		}
	}
}
