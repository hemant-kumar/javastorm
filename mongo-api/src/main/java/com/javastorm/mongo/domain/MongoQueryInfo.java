package com.javastorm.mongo.domain;

import java.util.List;
import java.util.Map;

/**
 * This MongoQueryInfo class contains all the info related to any mongo query.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013
 */
public class MongoQueryInfo 
{
  /**
   * This fields holds a list of fields to be selected 	
   */
  private List<String> selectFields_;

  /**
   * This field holds the count of maximum no of records to be fetched at one time   
   */
  private int maxNoOfRecords_ = 1000;

  /**
   * This field holds the mongo query in form of a string 
   */
  private String queryString_;

  /**
   * This field holds the mongo query in form of a map
   */
  private Map<Object,Object> queryMap_;
  
  /**
   * This field indicated whether records are to be fetched as JSON string or not 
   */
  private boolean findAsJson_ = false;

  public List<String> getSelectFields() {
	return selectFields_;
  }

  public void setSelectFields(List<String> selectFields) {
	this.selectFields_ = selectFields;
  }
  
  public int getMaxNoOfRecords() {
	return maxNoOfRecords_;
  }
  
  public void setMaxNoOfRecords(int maxNoOfRecords) {
	if(maxNoOfRecords>1000)
	  return;
	this.maxNoOfRecords_ = maxNoOfRecords;
  }

  public String getQueryString() {
	return queryString_;
  }

  public void setQueryString(String queryString) {
    this.queryString_ = queryString;
  }

  public Map<Object, Object> getQueryMap() {
	return queryMap_;
  }

  public void setQueryMap(Map<Object, Object> queryMap) {
	this.queryMap_ = queryMap;
  }

  public boolean isFindAsJson() {
	return findAsJson_;
  }

  public void setFindAsJson(boolean findAsJson) {
	this.findAsJson_ = findAsJson;
  }
}