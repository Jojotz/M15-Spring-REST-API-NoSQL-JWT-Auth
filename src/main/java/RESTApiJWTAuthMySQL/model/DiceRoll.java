package RESTApiJWTAuthMySQL.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name = "DICEROLL")
public class DiceRoll implements Serializable {

	@Id
	@Column (name = "diceRoll_Id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long diceRollId;
	
	@Column (name = "d1")
	private int d1;
	
	@Column (name = "d2")
	private int d2;
	
	@Column (name = "result")
	private String result;
			
	@ManyToOne
	@JoinColumn(name ="player_id", nullable=false)
	@JsonBackReference
	private Player player;
	
	public DiceRoll() {
		
	}
	
/*	public DiceRoll(Long diceRollId, int d1, int d2, String result, LocalDateTime diceRollRegistration) {
		this.diceRollId = diceRollId;
		this.d1 = (int) Math.random() * 7;
		this.d2 = (int) Math.random() * 7;
		this.result = Result();
		this.diceRollRegistration = LocalDateTime.now();
	}*/
	
	public DiceRoll(Player playerThrowing) {
		this.d1 = (int) (Math.random() * 7);
		this.d2 = (int) (Math.random() * 7);
		this.result = Result();
		this.player = playerThrowing;
	}

	public String Result ()  {
		
		String result;
		
		if (this.getD1() + this.getD2() == 7) {
			result ="WIN";
		} else {
			result ="LOSS";
		}		
		return result;
	}	
	
/*	public RollDices (Optional<Player> playerThrowing) {
		this.setD1((int) Math.random() * 7);
		this.setD2((int) Math.random() * 7);
		
		if (this.getD1() + this.getD2() == 7) {
			this.setResult("WIN");
		} else {
			this.setResult("LOSS");
		}
		
		return this.RollDices(playerThrowing);
	} */
	
	public Long getDiceRollId() {
		return diceRollId;
	}
	public void setDiceRollId(Long diceRollId) {
		this.diceRollId = diceRollId;
	}
	
	public int getD1() {
		return d1;
	}
	public void setD1(int d1) {
		this.d1 = d1;
	}
	public int getD2() {
		return d2;
	}
	public void setD2(int d2) {
		this.d2 = d2;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}	
	
	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof DiceRoll))
	      return false;
	    DiceRoll diceroll = (DiceRoll) o;
	    return Objects.equals(this.diceRollId, diceroll.diceRollId) && Objects.equals(this.d1, diceroll.d1)
	        && Objects.equals(this.d2, diceroll.d2) && Objects.equals(this.result, diceroll.result);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.diceRollId, this.d1, this.d2, this.result);
	  }

	  @Override
	  public String toString() {
	    return "DiceRoll{" + "id=" + this.diceRollId + ", dice1='" + this.d1 + '\'' + ", dice2='" + this.d2 + '\'' 
	    		+ ", result='" + this.result + '\'' + '}';
	  }
	
}