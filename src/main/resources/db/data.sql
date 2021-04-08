INSERT INTO PLAYER (player_id, player_name, password, win_rate, registration_date) 
VALUES
  	(NULL,'John', 'independenceday', '33.33', '2021-03-30 14:14:00'),
  	(NULL,'Munchkin', 'lunalunera', '50.00', '2021-03-30 14:14:00'),
  	(NULL,'Peter', 'abrakadabra', '100.00', '2021-03-30 14:14:00'),
  	(NULL,'Pedra', 'pikachu', '0.00', '2021-03-30 14:14:00');

INSERT INTO 
	DICEROLL (diceRoll_id, d1, d2, result, player_id) 
VALUES
  	(NULL,'4', '3', 'WIN', '1'),
  	(NULL,'5', '2', 'WIN', '2'),
  	(NULL,'4', '5', 'LOSS', '1'),
  	(NULL,'2', '3', 'LOSS', '2'),
  	(NULL,'5', '1', 'LOSS', '1'),
  	(NULL,'3', '4', 'WIN', '3'),
  	(NULL,'2', '3', 'LOSS', '4');