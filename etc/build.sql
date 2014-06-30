CREATE TABLE `user` (
  `user_id` varchar(200) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `ins_datetime` datetime DEFAULT NULL,
  `upd_datetime` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

INSERT INTO `user` VALUES ('user1','user1','pass','2014-06-30 19:32:32','2014-06-30 19:32:32','2014-06-30 19:32:32');
INSERT INTO `user` VALUES ('user2','user2','pass','2014-06-30 19:32:32','2014-06-30 19:32:32','2014-06-30 19:32:32');
