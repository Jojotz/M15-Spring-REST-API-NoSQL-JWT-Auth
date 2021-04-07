INSERT INTO PLAYER (player_id, player_name, password, registration_date) 
VALUES
  	(NULL,'John', 'independenceday', '2021-03-30 14:14:00'),
  	(NULL,'Munchkin', 'lunalunera', '2021-03-30 14:14:00');

INSERT INTO 
	DICEROLL (diceRoll_id, d1, d2, result, player_id) 
VALUES
  	(NULL,'4', '3', 'WIN', '1'),
  	(NULL,'5', '2', 'WIN', '2'),
  	(NULL,'4', '5', 'LOSS', '1'),
  	(NULL,'2', '3', 'LOSS', '2');