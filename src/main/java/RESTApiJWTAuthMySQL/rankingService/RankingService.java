package RESTApiJWTAuthMySQL.rankingService;

import org.springframework.stereotype.Service;

@Service
public class RankingService { //extends PlayerRepository {

	//public static final PlayerRepository playerRepository = null;

/*	public default BigDecimal calculateWinRate(Long playerId) {
		
		int dicerolls =	playerRepository.findById(playerId).get().getDiceRolls().size();
		int wins = (int) playerRepository.findById(playerId).get().getDiceRolls().stream()
				.filter(t -> t.getResult() =="WIN").count();
		
		BigDecimal winRate =  BigDecimal.valueOf((wins/dicerolls)*100) ;
		
		return winRate;
		
	}*/
	
}
