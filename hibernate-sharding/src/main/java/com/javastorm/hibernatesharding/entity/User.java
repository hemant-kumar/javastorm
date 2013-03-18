package com.javastorm.hibernatesharding.entity;

import java.util.UUID;

/**
 * This POJO class is intended for holding User Information.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 18/03/2013
 */
public class User extends HibernateEntity
{
	private static final long serialVersionUID = 10296L;
	protected UUID userid_;
	protected String name_;
	protected String gender_;
	protected String country_;	

	public UUID getUserid() {
		return userid_;
	}

	public void setUserid(UUID userid) {
		this.userid_ = userid;
	}

	public String getName() {
		return name_;
	}
	
	public void setName(String name) {
		this.name_ = name;
	}

	public String getGender() {
		return gender_;
	}
	
	public void setGender(String gender) {
		this.gender_ = gender;
	}
	
	public String getCountry() {
		return country_;
	}
	
	public void setCountry(String country) {
		this.country_ = country;
	}

	@Override
	public Integer getShard() {
		if(country_.equalsIgnoreCase("India")) {
			return 1;
		}
		return 2;
	}
}
