package RESTApiJWTAuthMySQL.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import RESTApiJWTAuthMySQL.controllers.DiceRollController;
import RESTApiJWTAuthMySQL.model.DiceRoll;

@Component
public class DiceRollModelAssembler implements RepresentationModelAssembler<DiceRoll, EntityModel<DiceRoll>> {

  @Override
  public EntityModel<DiceRoll> toModel(DiceRoll diceroll) {

    // Unconditional links to single-item resource and aggregate root

    EntityModel<DiceRoll> diceRollModel = EntityModel.of(diceroll,
        linkTo(methodOn(DiceRollController.class).one(diceroll.getDiceRollId())).withSelfRel(),
        linkTo(methodOn(DiceRollController.class).all()).withRel("dicerolls"));

    // Conditional links based on state of the order

    /*if (diceroll.getStatus() == Status.IN_PROGRESS) {
      orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
      orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
    }*/

    return diceRollModel;
  }
}