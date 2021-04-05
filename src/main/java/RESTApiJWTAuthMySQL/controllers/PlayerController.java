package RESTApiJWTAuthMySQL.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RESTApiJWTAuthMySQL.model.DiceRoll;
import RESTApiJWTAuthMySQL.model.Player;
import RESTApiJWTAuthMySQL.repositories.DiceRollRepository;
import RESTApiJWTAuthMySQL.repositories.PlayerRepository;
import RESTApiJWTAuthMySQL.exceptions.PlayerNotFoundException;
import RESTApiJWTAuthMySQL.dto.DiceRollModelAssembler;
import RESTApiJWTAuthMySQL.dto.PlayerModelAssembler;

@RestController
//@RequestMapping("/")
public class PlayerController {
	
	@Autowired
	private final PlayerRepository playerRepository;
	@Autowired
	private final PlayerModelAssembler playerAssembler;
	
	@Autowired
	private final DiceRollRepository diceRollRepository;
	@Autowired
	private final DiceRollModelAssembler diceRollAssembler;
	
	
	public PlayerController(PlayerRepository playerRepository, PlayerModelAssembler playerAssembler, DiceRollRepository diceRollRepository, DiceRollModelAssembler diceRollAssembler) {
		this.playerRepository = playerRepository;
	    this.playerAssembler = playerAssembler;
		this.diceRollRepository = diceRollRepository;
		this.diceRollAssembler = diceRollAssembler;		
	}
	
	//Gets all players
	@GetMapping("/players/")
	public CollectionModel<EntityModel<Player>> all() {

		List<EntityModel<Player>> players = playerRepository.findAll().stream().map(playerAssembler::toModel).collect(Collectors.toList());

		return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all()).withSelfRel());
	}
	
	//Gets a player by id
	@GetMapping("/players/{playerId}") 
	public EntityModel<Player> one(@PathVariable Long playerId) {

		Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

		return playerAssembler.toModel(player);
	}
	
	//Gets all dicerolls from a player by id
	//@GetMapping("/players/{playerId}/games")
/*	public CollectionModel<EntityModel<DiceRoll>> allThrows(@PathVariable Long playerId) {

		DiceRoll dicerolls = (DiceRoll) playerRepository.findById(playerId).get().getDiceRolls();
	 	  
		return CollectionModel.of(dicerolls, linkTo(methodOn(PlayerController.class).allThrows(playerId)).withSelfRel());
	}*/
	
	//Gets all dicerolls from a player by id
	@GetMapping(value = "/players/{playerId}/games", produces = { "application/hal+json" })
	public CollectionModel<DiceRoll> getAllDiceThrows(@PathVariable(name = "playerId") Long playerId) {
	    List<DiceRoll> dicerolls = playerRepository.findById(playerId).get().getDiceRolls();
	   
	    Link link = linkTo(methodOn(PlayerController.class).getAllDiceThrows(playerId)).withSelfRel();
	    CollectionModel<DiceRoll> result = CollectionModel.of(dicerolls, link);
	    return result;
	}
	
	//Creates a player
	@PostMapping(path="/players", consumes="application/json") 
	public ResponseEntity<?> newPlayer(@RequestBody Player newPlayer) {
	
		if (newPlayer.getPlayerName() == null || newPlayer.getPlayerName() == "") {
			newPlayer.setPlayerName("ANÒNIM");

		} else if (playerRepository.findByPlayerName(newPlayer.getPlayerName()).isPresent()) {
			
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
			
		EntityModel<Player> entityModel = playerAssembler.toModel(playerRepository.save(newPlayer));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	  
	}
	
	//Creates a throw
	@PostMapping(path="/players/{playerId}/games") 
	public ResponseEntity<EntityModel<DiceRoll>> newThrow(@PathVariable(name = "playerId") Long playerId) {
				
		Optional<Player> playerThrowing= playerRepository.findById(playerId);
		DiceRoll newDiceRoll = new DiceRoll (playerThrowing);
		//EntityModel<DiceRoll> entityModel = null;
		//DiceRoll newDiceRoll = DiceRoll.RollDices(playerThrowing).
					
		EntityModel<DiceRoll> entityModel = diceRollAssembler.toModel(diceRollRepository.save(newDiceRoll));
			
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
}
	
	//Updates a player
	@PutMapping("/players/{playerId}")
	public ResponseEntity<?> replacePlayer(@RequestBody Player newPlayer, @PathVariable Long playerId) {

		Player updatedPlayer = null;
		try {
			updatedPlayer = playerRepository.findById(playerId).map(player -> { player.setPlayerName(newPlayer.getPlayerName());
			return playerRepository.save(player);}).orElseGet(() -> { newPlayer.setPlayerId(playerId);
			return playerRepository.save(newPlayer);});
		
		} catch (Exception e) {
			
			throw new PlayerNotFoundException(playerId);
		}   

		EntityModel<Player> entityModel = playerAssembler.toModel(updatedPlayer);

	    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}
	
	//Deletes all dicerolls from a player by id
	@DeleteMapping("/players/{playerId}/games")
	public ResponseEntity<?> deleteDiceRolls(@PathVariable Long playerId) {

		playerRepository.findById(playerId).get().getDiceRolls().clear();  

		  return ResponseEntity.noContent().build();
	}
	
	/*@DeleteMapping("/players/{playerId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long playerId) {

		  repository.deleteById(playerId);

		  return ResponseEntity.noContent().build();
	}*/
	
}
