package com.javastorm.hibernatesharding.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javastorm.hibernatesharding.datasource.ShardCache;
import com.javastorm.hibernatesharding.entity.User;

/**
 * This class is intended for providing a sample implementation of this api.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 18/03/2013
 */
public class Service
{
	public static void main(String[] args) throws Exception {
		// Getting user information
		User user = new User();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Name");
		String name = br.readLine();
		user.setName(name);
		System.out.println("Enter Gender");
		String gender = br.readLine();
		user.setGender(gender);
		System.out.println("Enter Country");
		String country = br.readLine();
		user.setCountry(country);
		
		// Setting user to cache
		ShardCache.setEntity(user);
		
		// Getting session factory
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "conf/shard-config.xml" });
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		
		// Saving user information
		Session session = sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(user);
		transaction.commit();
	}
}
