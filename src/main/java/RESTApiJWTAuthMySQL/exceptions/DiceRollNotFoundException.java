package RESTApiJWTAuthMySQL.exceptions;

public class DiceRollNotFoundException extends RuntimeException {

	public DiceRollNotFoundException(Long id) {
		super("No diceRolls for player with id: " + id);
	}
}