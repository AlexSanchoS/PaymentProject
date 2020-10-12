-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema PaymentDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema PaymentDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PaymentDB` DEFAULT CHARACTER SET utf8 ;
USE `PaymentDB` ;

-- -----------------------------------------------------
-- Table `PaymentDB`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `language` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`currency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`currency` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  `course` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`account_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`account_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('blocked', 'unlocked') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`bank_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`bank_account` (
  `number` VARCHAR(20) NOT NULL,
  `balance` BIGINT NOT NULL,
  `currency_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `account_status_id` INT NOT NULL,
  PRIMARY KEY (`number`, `currency_id`, `user_id`, `account_status_id`),
  INDEX `fk_bankAccount_currency1_idx` (`currency_id` ASC),
  INDEX `fk_bankAccount_user1_idx` (`user_id` ASC),
  INDEX `fk_bankAccount_accountStatus1_idx` (`account_status_id` ASC),
  CONSTRAINT `fk_bankAccount_currency1`
    FOREIGN KEY (`currency_id`)
    REFERENCES `PaymentDB`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bankAccount_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `PaymentDB`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bankAccount_accountStatus1`
    FOREIGN KEY (`account_status_id`)
    REFERENCES `PaymentDB`.`account_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`card_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`card_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('blocked', 'unlocked') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`credit_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`credit_card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(16) NOT NULL,
  `validity` DATE NOT NULL,
  `bank_account_number` VARCHAR(20) NOT NULL,
  `user_id` INT NOT NULL,
  `card_status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `bank_account_number`, `user_id`, `card_status_id`),
  INDEX `fk_creditCard_bankAccount1_idx` (`bank_account_number` ASC),
  INDEX `fk_creditCard_user1_idx` (`user_id` ASC),
  INDEX `fk_creditCard_cardStatus1_idx` (`card_status_id` ASC),
  CONSTRAINT `fk_creditCard_bankAccount1`
    FOREIGN KEY (`bank_account_number`)
    REFERENCES `PaymentDB`.`bank_account` (`number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_creditCard_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `PaymentDB`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_creditCard_cardStatus1`
    FOREIGN KEY (`card_status_id`)
    REFERENCES `PaymentDB`.`card_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`payment_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`payment_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('sent', 'prepared') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `amount` BIGINT UNSIGNED NOT NULL,
  `payment_status_id` INT NOT NULL,
  `recipient_account_number` VARCHAR(20) NOT NULL,
  `sender_account_number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`, `payment_status_id`, `recipient_account_number`, `sender_account_number`),
  INDEX `fk_payment_paymentStatus1_idx` (`payment_status_id` ASC),
  INDEX `fk_payment_bank_account1_idx` (`recipient_account_number` ASC),
  INDEX `fk_payment_bank_account2_idx` (`sender_account_number` ASC),
  CONSTRAINT `fk_payment_paymentStatus1`
    FOREIGN KEY (`payment_status_id`)
    REFERENCES `PaymentDB`.`payment_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_bank_account1`
    FOREIGN KEY (`recipient_account_number`)
    REFERENCES `PaymentDB`.`bank_account` (`number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_bank_account2`
    FOREIGN KEY (`sender_account_number`)
    REFERENCES `PaymentDB`.`bank_account` (`number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `language` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
