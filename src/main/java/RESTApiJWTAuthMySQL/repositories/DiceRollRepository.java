package RESTApiJWTAuthMySQL.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RESTApiJWTAuthMySQL.model.DiceRoll;
import RESTApiJWTAuthMySQL.model.Player;

@Repository
public interface DiceRollRepository extends JpaRepository<DiceRoll, Long> {
	
	List<DiceRoll> findDiceRollsByPlayer (Player player);

}