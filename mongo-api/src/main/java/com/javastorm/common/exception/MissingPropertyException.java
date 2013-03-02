package com.javastorm.common.exception;

/**
 * The MissingPropertyException class indicates that specified  
 * property is not found in properties file and system properties 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class MissingPropertyException extends Exception {

  private static final long serialVersionUID = 1L;

  public MissingPropertyException() {	
  }

  public MissingPropertyException(String message) {
	super(message);
  }
}