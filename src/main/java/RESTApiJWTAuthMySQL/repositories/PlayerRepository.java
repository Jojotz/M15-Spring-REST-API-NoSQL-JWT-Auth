package RESTApiJWTAuthMySQL.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RESTApiJWTAuthMySQL.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	Optional<Player> findByPlayerName(String playerName);	

}
