package RESTApiJWTAuthMySQL.controllers;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RESTApiJWTAuthMySQL.model.DiceRoll;
import RESTApiJWTAuthMySQL.repositories.DiceRollRepository;
import RESTApiJWTAuthMySQL.exceptions.DiceRollNotFoundException;
import RESTApiJWTAuthMySQL.dto.DiceRollModelAssembler;

@RestController
//@RequestMapping("/")
public class DiceRollController {
	
	@Autowired
	private final DiceRollRepository repository;
	@Autowired
	private final DiceRollModelAssembler assembler;

	public DiceRollController(DiceRollRepository repository, DiceRollModelAssembler assembler) {
	    this.repository = repository;
	    this.assembler = assembler;
	}
	
	//@Autowired
	//PlayerService playerService;
		
	@GetMapping("/dicerolls/")
	public CollectionModel<EntityModel<DiceRoll>> all() {

	  List<EntityModel<DiceRoll>> dicerolls = repository.findAll().stream().map
			  (assembler::toModel).collect(Collectors.toList());

	  return CollectionModel.of(dicerolls, linkTo(methodOn(DiceRollController.class).all()).withSelfRel());
	}
	
	@GetMapping("/dicerolls/{id}")
	public EntityModel<DiceRoll> one(@PathVariable Long diceRollId) {

		DiceRoll diceroll = repository.findById(diceRollId).orElseThrow(() -> new DiceRollNotFoundException(diceRollId));

	  return assembler.toModel(diceroll);
	}
	
	@PostMapping("/dicerolls")
	public ResponseEntity<?> newDiceRoll(@RequestBody DiceRoll newDiceRoll) {

	  EntityModel<DiceRoll> entityModel = assembler.toModel(repository.save(newDiceRoll));

	  return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	      .body(entityModel);
	}		 
	
	
	/*@PutMapping("/dicerolls/{id}")
	ResponseEntity<?> replacePlayer(@RequestBody Player newPlayer, @PathVariable Long playerId) {

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
	}*/
	
	@DeleteMapping("/dicerolls/{id}")
	public ResponseEntity<?> deleteDiceRolls(@PathVariable Long diceRollId) {

		  repository.deleteById(diceRollId);

		  return ResponseEntity.noContent().build();
	}
}
