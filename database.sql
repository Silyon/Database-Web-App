DROP SCHEMA IF EXISTS `tp-database`;

CREATE SCHEMA `tp-database`;

use `tp-database`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user_table`;

CREATE TABLE `user_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `EMAIL_UNIQUE` (`email`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `playlist_table`;

CREATE TABLE `playlist_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(128) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  CONSTRAINT `FK_USER` 
  FOREIGN KEY (`user_id`) 
  REFERENCES `user_table` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;
