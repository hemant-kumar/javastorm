package com.javastorm.common.exception;

/**
 * The MultipleFileFoundException class indicates that more 
 * than one copies of specified file exist in class path. 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class MultipleFileFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public MultipleFileFoundException() {	
  }

  public MultipleFileFoundException(String message) {
	super(message);
  }
}