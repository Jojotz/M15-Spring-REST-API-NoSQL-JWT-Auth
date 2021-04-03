package RESTApiJWTAuthMySQL.exceptions;

public class DiceRollNotFoundException extends RuntimeException {

	  public DiceRollNotFoundException(Long id) {
	    super("Could not find dice roll " + id);
	  }
	}