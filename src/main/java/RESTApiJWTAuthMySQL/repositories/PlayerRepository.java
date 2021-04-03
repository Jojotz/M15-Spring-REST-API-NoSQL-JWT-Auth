package RESTApiJWTAuthMySQL.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import RESTApiJWTAuthMySQL.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}