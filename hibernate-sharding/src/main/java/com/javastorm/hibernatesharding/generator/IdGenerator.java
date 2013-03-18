package com.javastorm.hibernatesharding.generator;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * This class is intended for generating primary keys.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 18/03/2013
 */
public class IdGenerator implements IdentifierGenerator 
{
	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
		return UUID.randomUUID();
	}
}