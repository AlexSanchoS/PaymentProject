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
  `status` ENUM('block', 'unblock') NOT NULL,
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
  `status` ENUM('blocked', 'unlocked', 'expectation') NOT NULL,
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
  `status` ENUM('sent', 'prepared', 'rejected') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PaymentDB`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PaymentDB`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` TIMESTAMP NOT NULL,
  `amount` DOUBLE UNSIGNED NOT NULL,
  `number` INT NOT NULL,
  `payment_status_id` INT NOT NULL,
  `recipient_credit_card` INT NOT NULL,
  `sender_credit_card` INT NOT NULL,
  PRIMARY KEY (`id`, `payment_status_id`, `recipient_credit_card`, `sender_credit_card`),
  INDEX `fk_payment_paymentStatus1_idx` (`payment_status_id` ASC),
  INDEX `fk_recipient_credit_card_idx` (`recipient_credit_card` ASC),
  INDEX `fk_sender_credit_card_idx` (`sender_credit_card` ASC),
  CONSTRAINT `fk_payment_paymentStatus1`
    FOREIGN KEY (`payment_status_id`)
    REFERENCES `PaymentDB`.`payment_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipient_credit_card`
    FOREIGN KEY (`recipient_credit_card`)
    REFERENCES `PaymentDB`.`credit_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sender_credit_card`
    FOREIGN KEY (`sender_credit_card`)
    REFERENCES `PaymentDB`.`credit_card` (`id`)
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


INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('1', '1234', '1234', 'Zam Alex', '1997-08-01', 'en', 'unblock');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('2', '2222', '2222', 'Zam Tanua', '2002-01-05', 'ua', 'block');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('3', '1111', '1111', 'Dyn Nastya', '2003-02-04', 'ua', 'unblock');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('4', 'marya', 'marya', 'Поліщук Марія', '1970-10-04', 'ua', 'unblock');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('5', 'andr', 'andr', 'Андрієв Андрій', '1974-10-09', 'ua', 'unblock');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('6', '4422', '4422', 'Сидоренко Сидор', '1997-09-1', 'ua', 'unblock');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('7', '1313', '1313', 'Ivanol Ivan', '1986-03-03', 'ua', 'block');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('8', 'alex', 'alex', 'Alexov Alex', '1987-03-05', 'ua', 'block');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('9', '3366', '3366', 'Angelina Gener', '1941-12-21', 'ua', 'block');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('10', '1441', '1441', 'Gnat Roman', '1949-08-17', 'en', 'unblock');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('11', '2233', '2233', 'Ковальов Олександр', '1996-12-19', 'en', 'unblock');
INSERT INTO `paymentdb`.`client` (`id`, `login`, `password`, `name`, `date_of_birth`, `language`, `status`) VALUES ('12', '4747', '4747', 'Петров Потро', '1997-02-06', 'en', 'unblock');

INSERT INTO `paymentdb`.`admin` (`id`, `login`, `password`, `language`) VALUES ('1', 'admin', 'admin', 'en');
INSERT INTO `paymentdb`.`admin` (`id`, `login`, `password`, `language`) VALUES ('2', 'admin2', 'admin2', 'ua');
INSERT INTO `paymentdb`.`admin` (`id`, `login`, `password`, `language`) VALUES ('3', 'admin3', 'admin3', 'en');
INSERT INTO `paymentdb`.`admin` (`id`, `login`, `password`, `language`) VALUES ('4', 'admin4', 'admin4', 'ua');
INSERT INTO `paymentdb`.`admin` (`id`, `login`, `password`, `language`) VALUES ('5', 'admin5', 'admin5', 'en');

INSERT INTO `paymentdb`.`account_status` (`id`, `status`) VALUES ('1', 'unlocked');
INSERT INTO `paymentdb`.`account_status` (`id`, `status`) VALUES ('2', 'blocked');
INSERT INTO `paymentdb`.`account_status` (`id`, `status`) VALUES ('3', 'expectation');

INSERT INTO `paymentdb`.`card_status` (`id`, `status`) VALUES ('1', 'unlocked');
INSERT INTO `paymentdb`.`card_status` (`id`, `status`) VALUES ('2', 'blocked');

INSERT INTO `paymentdb`.`payment_status` (`id`, `status`) VALUES ('1', 'sent');
INSERT INTO `paymentdb`.`payment_status` (`id`, `status`) VALUES ('2', 'prepared');
INSERT INTO `paymentdb`.`payment_status` (`id`, `status`) VALUES ('3', 'rejected');

INSERT INTO `paymentdb`.`currency` (`id`, `name`, `course`) VALUES ('1', 'EUR', '33,32');
INSERT INTO `paymentdb`.`currency` (`id`, `name`, `course`) VALUES ('2', 'USD', '28,34');
INSERT INTO `paymentdb`.`currency` (`id`, `name`, `course`) VALUES ('3', 'UAH', '1');

INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895301', '7896', '1', '1', '1');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895302', '12356', '2', '2', '2');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895303', '14456', '3', '3', '3');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895304', '896', '1', '4', '1');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895305', '12354', '2', '5', '2');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895306', '0', '3', '6', '3');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895307', '123', '3', '1', '1');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895308', '321', '2', '1', '1');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895309', '692', '1', '1', '2');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895310', '987', '1', '1', '3');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895311', '1365', '2', '1', '3');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895312', '7789', '3', '3', '3');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895313', '963', '1', '3', '1');
INSERT INTO `paymentdb`.`bank_account` (`number`, `balance`, `currency_id`, `user_id`, `account_status_id`) VALUES ('98987814563217895314', '896', '2', '3', '3');

INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('1', '5106212335487001', '2022-11-01', '98987814563217895301', '1', '1');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('2', '5106212335487002', '2022-12-01', '98987814563217895302', '2', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('3', '5106212335487003', '2023-01-01', '98987814563217895303', '3', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('4', '5106212335487004', '2023-01-01', '98987814563217895304', '4', '1');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('5', '5106212335487005', '2023-02-01', '98987814563217895305', '5', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('6', '5106212335487006', '2023-03-01', '98987814563217895306', '6', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('7', '5106212335487007', '2023-04-01', '98987814563217895307', '1', '1');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('8', '5106212335487008', '2023-04-01', '98987814563217895308', '1', '1');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('9', '5106212335487009', '2023-03-01', '98987814563217895309', '1', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('10', '5106212335487010', '2023-04-01', '98987814563217895310', '1', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('11', '5106212335487011', '2021-04-01', '98987814563217895311', '1', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('12', '5106212335487012', '2020-04-01', '98987814563217895312', '3', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('13', '5106212335487013', '2020-05-01', '98987814563217895313', '3', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('14', '5106212335487014', '2022-12-01', '98987814563217895314', '3', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('15', '5106212335487015', '2022-12-01', '98987814563217895308', '1', '1');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('16', '5106212335487016', '2021-12-01', '98987814563217895308', '1', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('17', '5106212335487017', '2021-12-01', '98987814563217895307', '1', '1');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('18', '5106212335487018', '2021-12-01', '98987814563217895307', '1', '1');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('19', '5106212335487019', '2021-11-01', '98987814563217895311', '1', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('20', '5106212335487020', '2021-08-01', '98987814563217895311', '1', '2');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('21', '5106212335487021', '2021-09-01', '98987814563217895307', '1', '1');
INSERT INTO `paymentdb`.`credit_card` (`id`, `number`, `validity`, `bank_account_number`, `user_id`, `card_status_id`) VALUES ('22', '5106212335487022', '2021-11-01', '98987814563217895311', '1', '2');
