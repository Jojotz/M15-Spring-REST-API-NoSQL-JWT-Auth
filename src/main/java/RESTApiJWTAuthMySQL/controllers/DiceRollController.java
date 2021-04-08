package RESTApiJWTAuthMySQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import RESTApiJWTAuthMySQL.model.DiceRoll;
import RESTApiJWTAuthMySQL.repositories.DiceRollRepository;
import RESTApiJWTAuthMySQL.exceptions.DiceRollNotFoundException;
import RESTApiJWTAuthMySQL.dto.DiceRollModelAssembler;

@RestController
public class DiceRollController {
	
	@Autowired
	private final DiceRollRepository repository;
	@Autowired
	private final DiceRollModelAssembler assembler;

	public DiceRollController(DiceRollRepository repository, DiceRollModelAssembler assembler) {
	    this.repository = repository;
	    this.assembler = assembler;
	}
	
	@GetMapping("/dicerolls/{diceRollId}")
	public EntityModel<DiceRoll> one(@PathVariable Long diceRollId) {

		DiceRoll diceroll = repository.findById(diceRollId).orElseThrow(() -> new DiceRollNotFoundException(diceRollId));

		return assembler.toModel(diceroll);
	}
	
}