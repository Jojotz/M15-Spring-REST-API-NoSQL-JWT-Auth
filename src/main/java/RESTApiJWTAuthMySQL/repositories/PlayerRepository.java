package RESTApiJWTAuthMySQL.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import RESTApiJWTAuthMySQL.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	Optional<Player> findByPlayerName(String playerName);

}