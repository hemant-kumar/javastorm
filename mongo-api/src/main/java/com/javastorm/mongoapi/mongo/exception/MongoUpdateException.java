package com.javastorm.mongoapi.mongo.exception;

/**
 * The MongoUpdateException class indicates that an exception
 * occurred during updating document in Mongo DB   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class MongoUpdateException extends Exception {

  private static final long serialVersionUID = 1L;

  public MongoUpdateException() {	
  }

  public MongoUpdateException(String message) {
	super(message);
  }
}