package RESTApiJWTAuthMySQL.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Player")
public class Player {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long playerId;
	
	@Column (name = "player_name")
	private String playerName;
	
	@CreationTimestamp
	//@Temporal(TemporalType.DATE)	
	@Column (name = "registration_date", updatable = false)
	private LocalDateTime registrationDate;
	
	@OneToMany (mappedBy="player", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<DiceRoll> diceRolls = new ArrayList<>();
	
	public Player() {
		
	}  
	
	public Player(Long playerId, String playerName, LocalDateTime registrationDate) {
		this.playerId=playerId;
		this.playerName = playerName;
		this.registrationDate = LocalDateTime.now();
	}
	
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

/*	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
*/
	public List<DiceRoll> getRolls() {
		return diceRolls;
	}

	public void setRolls(List<DiceRoll> diceRolls) {
		this.diceRolls = diceRolls;
	}	
	
	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Player))
	      return false;
	    Player player = (Player) o;
	    return Objects.equals(this.playerId, player.playerId) && Objects.equals(this.playerName, player.playerName)
	        && Objects.equals(this.registrationDate, player.registrationDate);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.playerId, this.playerName, this.registrationDate);
	  }

	  @Override
	  public String toString() {
	    return "Player{" + "id=" + this.playerId + ", name='" + this.playerName + '\'' + ", date of registration='" + this.registrationDate + '\'' + '}';
	  }
}
