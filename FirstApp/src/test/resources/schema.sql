DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  version varchar(512) DEFAULT NULL,
  level INT DEFAULT NULL,
  question_Text varchar(512)  DEFAULT NULL,

);

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  version varchar(512) DEFAULT NULL,
  answer_Text varchar(512)  DEFAULT NULL,
  correct BIT DEFAULT NULL,
  question_id INT,
  foreign key (question_id) references question(id)
);

