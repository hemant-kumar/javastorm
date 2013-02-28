package mongo.exception;

/**
 * The MongoDeleteException class indicates that an exception
 * occurred during deleting document from Mongo DB   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 13/01/2013 
 */
public class MongoDeleteException extends Exception {

  private static final long serialVersionUID = 1L;

  public MongoDeleteException() {	
  }

  public MongoDeleteException(String message) {
	super(message);
  }
}