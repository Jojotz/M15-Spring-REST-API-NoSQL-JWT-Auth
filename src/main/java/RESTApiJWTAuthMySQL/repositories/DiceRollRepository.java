package RESTApiJWTAuthMySQL.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import RESTApiJWTAuthMySQL.model.DiceRoll;
import RESTApiJWTAuthMySQL.model.Player;

public interface DiceRollRepository extends JpaRepository<DiceRoll, Long> {
	
	List<DiceRoll> findDiceRollsByPlayer (Player player);

}