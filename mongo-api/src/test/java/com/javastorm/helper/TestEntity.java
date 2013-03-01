package com.leadtheworld.helper;

import java.util.ArrayList;

import com.leadtheworld.mongo.domain.MongoEntity;

/**
 * This TestEntity class is extending MongoEntity and also defining its business requirements fields
 * 
 * @author hemant singh
 * @version 1.0 Dated: 13/01/2013
 */
public class TestEntity extends MongoEntity 
{
  private String info;
	
  private ArrayList<String> myList;
  
  public TestEntity() {
  }
  
  public TestEntity(String id) {
    super(id);
  }

  public String getInfo() {
	return info;
  }

  public void setInfo(String info) {
	this.info = info;
  }

  public ArrayList<String> getMyList() {
	return myList;
  }

  public void setMyList(ArrayList<String> myList) {
	this.myList = myList;
  }
}