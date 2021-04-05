package RESTApiJWTAuthMySQL.controllers;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RESTApiJWTAuthMySQL.model.Player;
import RESTApiJWTAuthMySQL.repositories.PlayerRepository;
import RESTApiJWTAuthMySQL.exceptions.PlayerNotFoundException;
import RESTApiJWTAuthMySQL.dto.PlayerModelAssembler;

@RestController
//@RequestMapping("/")
public class PlayerController {
	
	@Autowired
	private final PlayerRepository repository;
	@Autowired
	private final PlayerModelAssembler assembler;

	PlayerController(PlayerRepository repository, PlayerModelAssembler assembler) {
	    this.repository = repository;
	    this.assembler = assembler;
	}
		
	@GetMapping("/players/")
	public CollectionModel<EntityModel<Player>> all() {

		List<EntityModel<Player>> players = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

		return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all()).withSelfRel());
	}
	
	@GetMapping("/players/{playerId}") 
	public EntityModel<Player> one(@PathVariable Long playerId) {

		Player player = repository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));

		return assembler.toModel(player);
	}
	
	@PostMapping(path="/players", consumes="application/json") 
	public ResponseEntity<?> newPlayer(@RequestBody Player newPlayer) {
	
		if (newPlayer.getPlayerName() == null || newPlayer.getPlayerName() == "") {
			newPlayer.setPlayerName("ANÒNIM");

		} else if (repository.findByPlayerName(newPlayer.getPlayerName()).isPresent()) {
			
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
			
		EntityModel<Player> entityModel = assembler.toModel(repository.save(newPlayer));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	  
	}		 
	
	
	@PutMapping("/players/{playerId}")
	public ResponseEntity<?> replacePlayer(@RequestBody Player newPlayer, @PathVariable Long playerId) {

		Player updatedPlayer = repository.findById(playerId) //
	      .map(player -> {
	        player.setPlayerName(newPlayer.getPlayerName());
	        return repository.save(player);
	      }) //
	      .orElseGet(() -> {
	    	  newPlayer.setPlayerId(playerId);
	        return repository.save(newPlayer);
	      });

	  EntityModel<Player> entityModel = assembler.toModel(updatedPlayer);

	  return ResponseEntity //
	      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	      .body(entityModel);
	}
	
	@DeleteMapping("/players/{playerId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long playerId) {

		  repository.deleteById(playerId);

		  return ResponseEntity.noContent().build();
	}
	
}
