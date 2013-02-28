package mongo.exception;

/**
 * The MongoFileNotFoundException class indicates that  
 * specified mongo file doesn't exist in class path 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class MongoFileNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public MongoFileNotFoundException() {	
  }

  public MongoFileNotFoundException(String message) {
	super(message);
  }
}