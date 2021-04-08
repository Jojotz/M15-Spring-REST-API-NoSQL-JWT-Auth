package RESTApiJWTAuthMySQL.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {

	@Id
	@Column(name = "player_Id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long playerId;
	
	@Column (name = "player_name")
	private String playerName;
	
	@Column (name = "password")
	private String password;
	
	@Column (name = "win_rate")  //, precision = 5, scale =2
	private double winRate;
	
	@CreationTimestamp
	@Column (name = "registration_date", updatable = false)
	private LocalDateTime registrationDate;
	
	@OneToMany (mappedBy="player", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private List<DiceRoll> diceRolls = new ArrayList<>();
	
	public Player() {
		
	}  
	
	public Player(Long playerId, String playerName, String password, LocalDateTime registrationDate) {
		this.playerId=playerId;
		this.playerName = playerName;
		this.password = password;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

/*	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
*/
	public List<DiceRoll> getDiceRolls() {
		return diceRolls;
	}

	public void setDiceRolls(List<DiceRoll> diceRolls) {
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
    		&& Objects.equals(this.password, player.password) && Objects.equals(this.winRate, player.winRate)
    		&& Objects.equals(this.registrationDate, player.registrationDate)
    		&& Objects.equals(this.diceRolls, player.diceRolls);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.playerId, this.playerName, this.password, this.registrationDate);
	}

	@Override
	public String toString() {
	   	return "Player{" + "id=" + this.playerId + ", name='" + this.playerName + '\'' + ", date of registration='" + this.registrationDate + '\'' + '}';
	}

	public double calculateWinRate(Player playerThrowing) {
		
		double dicerolls = playerThrowing.getDiceRolls().size();
		double wins = (double) playerThrowing.getDiceRolls().stream()
				.filter(w -> w.getResult().equals("WIN")).count();
		
		double winRate =  (wins/dicerolls)*100;
		
		return winRate;
	}
}
