package mongo.exception;

/**
 * The MongoIdMissingException class indicates that an instance   
 * of MongoEntity is being saved without setting _id field 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class MongoIdMissingException extends Exception {

  private static final long serialVersionUID = 1L;

  public MongoIdMissingException() {	
  }

  public MongoIdMissingException(String message) {
	super(message);
  }
}