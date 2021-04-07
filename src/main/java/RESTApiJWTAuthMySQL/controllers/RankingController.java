package RESTApiJWTAuthMySQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.function.EntityResponse;

import RESTApiJWTAuthMySQL.rankingService.RankingService;
import RESTApiJWTAuthMySQL.repositories.PlayerRepository;

public class RankingController {

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private RankingService service;
	
	@GetMapping("/players/ranking")
	@ResponseStatus(HttpStatus.OK)
	public EntityResponse getSuccessRate() {
		return service.percentageOfVictoriesForAllRounds();
	}
	
}
