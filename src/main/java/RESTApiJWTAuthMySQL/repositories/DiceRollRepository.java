package RESTApiJWTAuthMySQL.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import RESTApiJWTAuthMySQL.model.DiceRoll;

public interface DiceRollRepository extends JpaRepository<DiceRoll, Long> {

}