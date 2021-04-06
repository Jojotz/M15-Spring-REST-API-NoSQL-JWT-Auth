USE `dicegame`;

DROP TABLE IF EXISTS `Player`;

CREATE TABLE IF NOT EXISTS `Player` (
  `player_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `player_name` VARCHAR(45),
  `password` VARCHAR(45),
  `registration_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

DROP TABLE IF EXISTS `DiceRoll`;

CREATE TABLE IF NOT EXISTS `DiceRoll` (
  `diceRoll_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `d1` INT(55),
  `d2` INT(55),
  `result` VARCHAR(45),
  `diceRoll_registration` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `player_id` BIGINT NOT NULL);
  
ALTER TABLE DiceRoll
ADD constraint FK_PLAYER_ID  FOREIGN KEY (player_id) 
      REFERENCES Player (player_id);
