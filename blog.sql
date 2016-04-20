-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cisco-blog
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cisco-blog` ;

-- -----------------------------------------------------
-- Schema cisco-blog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cisco-blog` DEFAULT CHARACTER SET utf8 ;
USE `cisco-blog` ;

-- -----------------------------------------------------
-- Table `cisco-blog`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cisco-blog`.`user` ;

CREATE TABLE IF NOT EXISTS `cisco-blog`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(30) NOT NULL COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `email` VARCHAR(40) NOT NULL COMMENT '',
  `password` VARCHAR(100) NOT NULL COMMENT '',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `score` INT(10) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '',
  `active` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cisco-blog`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cisco-blog`.`questions` ;

CREATE TABLE IF NOT EXISTS `cisco-blog`.`questions` (
  `question_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(150) NOT NULL COMMENT '',
  `text` TEXT NULL COMMENT '',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `views_count` BIGINT(0) NOT NULL COMMENT '',
  `upvote_count` BIGINT(0) NOT NULL COMMENT '',
  `user_id` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`question_id`)  COMMENT '',
  UNIQUE INDEX `title_UNIQUE` (`title` ASC)  COMMENT '',
  UNIQUE INDEX `question_id_UNIQUE` (`question_id` ASC)  COMMENT '',
  INDEX `fk_questions_user1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_questions_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cisco-blog`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cisco-blog`.`answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cisco-blog`.`answers` ;

CREATE TABLE IF NOT EXISTS `cisco-blog`.`answers` (
  `answer_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `text` TEXT NOT NULL COMMENT '',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `upvote_count` BIGINT(20) NOT NULL COMMENT '',
  `questions_question_id` BIGINT(20) NOT NULL COMMENT '',
  `user_id` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`answer_id`)  COMMENT '',
  UNIQUE INDEX `answer_id_UNIQUE` (`answer_id` ASC)  COMMENT '',
  INDEX `fk_answers_questions1_idx` (`questions_question_id` ASC)  COMMENT '',
  INDEX `fk_answers_user1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_answers_questions1`
    FOREIGN KEY (`questions_question_id`)
    REFERENCES `cisco-blog`.`questions` (`question_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answers_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cisco-blog`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cisco-blog`.`session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cisco-blog`.`session` ;

CREATE TABLE IF NOT EXISTS `cisco-blog`.`session` (
  `token` VARCHAR(125) NOT NULL COMMENT '',
  `expiry_time` TIMESTAMP NOT NULL COMMENT '',
  `user_id` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`token`)  COMMENT '',
  UNIQUE INDEX `token_UNIQUE` (`token` ASC)  COMMENT '',
  INDEX `fk_session_user1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_session_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cisco-blog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cisco-blog`.`answer_upvote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cisco-blog`.`answer_upvote` ;

CREATE TABLE IF NOT EXISTS `cisco-blog`.`answer_upvote` (
  `answer_upvote_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `up_or_down` TINYINT(0) NULL COMMENT '',
  `answers_answer_id` BIGINT(20) NOT NULL COMMENT '',
  `answers_user_user_id` BIGINT(20) NOT NULL COMMENT '',
  `user_id` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`answer_upvote_id`)  COMMENT '',
  UNIQUE INDEX `answer_upvote_id_UNIQUE` (`answer_upvote_id` ASC)  COMMENT '',
  INDEX `fk_answer_upvote_answers1_idx` (`answers_answer_id` ASC, `answers_user_user_id` ASC)  COMMENT '',
  INDEX `fk_answer_upvote_user1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_answer_upvote_answers1`
    FOREIGN KEY (`answers_answer_id`)
    REFERENCES `cisco-blog`.`answers` (`answer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_upvote_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cisco-blog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cisco-blog`.`question_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cisco-blog`.`question_view` ;

CREATE TABLE IF NOT EXISTS `cisco-blog`.`question_view` (
  `question_upvote_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `up_or_down` TINYINT(4) NOT NULL COMMENT '',
  `questions_question_id` BIGINT(20) NOT NULL COMMENT '',
  `questions_user_user_id` BIGINT(20) NOT NULL COMMENT '',
  `user_id` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`question_upvote_id`)  COMMENT '',
  UNIQUE INDEX `token_UNIQUE` (`question_upvote_id` ASC)  COMMENT '',
  INDEX `fk_question_view_questions1_idx` (`questions_question_id` ASC, `questions_user_user_id` ASC)  COMMENT '',
  INDEX `fk_question_view_user1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_question_view_questions1`
    FOREIGN KEY (`questions_question_id`)
    REFERENCES `cisco-blog`.`questions` (`question_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_view_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cisco-blog`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
