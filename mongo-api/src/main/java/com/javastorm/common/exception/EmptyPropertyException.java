package com.javastorm.common.exception;

/**
 * The EmptyPropertyException class indicates that no value  
 * has been provided against the specified property 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class EmptyPropertyException extends Exception {

  private static final long serialVersionUID = 1L;

  public EmptyPropertyException() {	
  }

  public EmptyPropertyException(String message) {
	super(message);
  }
}