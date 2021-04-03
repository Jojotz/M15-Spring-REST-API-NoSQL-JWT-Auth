package RESTApiJWTAuthMySQL.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table (name = "DiceRoll")
public class DiceRoll {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long diceRollId;
	
	@Column (name = "d1")
	private int d1;
	
	@Column (name = "d2")
	private int d2;
	
	@Column (name = "result")
	private String result;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name = "diceRoll_registration", updatable = false)
	private Date diceRollRegistration;
		
	@ManyToOne
	@JoinColumn(name ="player_id", nullable=false)
	private Player player;
	
	public DiceRoll() {
		
	}
	
	public DiceRoll(Long diceRollId, int d1, int d2, String result, Date diceRollRegistration) {
		this.diceRollId = diceRollId;
		this.d1 = d1;
		this.d2 = d2;
		this.result = result;
		this.diceRollRegistration = diceRollRegistration;
	}
	
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
	
	public Date getDiceRollRegistration() {
		return diceRollRegistration;
	}
	public void setDiceRollRegistration(Date diceRollRegistration) {
		this.diceRollRegistration = diceRollRegistration;
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
	        && Objects.equals(this.d2, diceroll.d2) && Objects.equals(this.result, diceroll.result) 
	        && Objects.equals(this.diceRollRegistration, diceroll.diceRollRegistration) ;
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.diceRollId, this.d1, this.d2, this.result, this.diceRollRegistration);
	  }

	  @Override
	  public String toString() {
	    return "DiceRoll{" + "id=" + this.diceRollId + ", dice1='" + this.d1 + '\'' + ", dice2='" + this.d2 + '\'' 
	    		+ ", result='" + this.result + ", date of registration='" + this.diceRollRegistration + '\'' + '}';
	  }
	
}