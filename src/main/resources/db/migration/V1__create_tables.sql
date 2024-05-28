-- Criação da tabela user_type
CREATE TABLE IF NOT EXISTS `user_type`
(
    `user_type_id` INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`         VARCHAR(255) NOT NULL,
    `description`  TEXT
);

-- Criação da tabela subscriptions_type
CREATE TABLE IF NOT EXISTS `subscriptions_type`
(
    `subscriptions_type_id` INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`                  VARCHAR(255)   NOT NULL,
    `access_months`         INT,
    `price`                 DECIMAL(10, 2) NOT NULL,
    `product_key`           VARCHAR(255)   NOT NULL
);

-- Criação da tabela users
CREATE TABLE IF NOT EXISTS `users`
(
    `users_id`              INT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`                  CHAR(255) NOT NULL,
    `email`                 CHAR(255) NOT NULL,
    `phone`                 CHAR(255) NOT NULL,
    `cpf`                   CHAR(255) NOT NULL,
    `dt_subscription`       DATE      NOT NULL,
    `dt_expiration`         DATE      NOT NULL,
    `user_type_id`          INT,
    `subscriptions_type_id` INT
);

-- Criação da tabela user_credentials
CREATE TABLE IF NOT EXISTS `user_credentials`
(
    `user_credentials_id` INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username`            VARCHAR(255) NOT NULL,
    `password`            VARCHAR(255) NOT NULL,
    `user_type_id`        INT
);

-- Criação da tabela user_payment_info
CREATE TABLE IF NOT EXISTS `user_payment_info`
(
    `user_payment_info_id`  INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `card_number`           CHAR(255)      NOT NULL,
    `card_expiration_month` INT            NOT NULL,
    `card_expiration_year`  INT            NOT NULL,
    `card_security_code`    CHAR(255)      NOT NULL,
    `price`                 DECIMAL(10, 2) NOT NULL,
    `instalments`           INT            NOT NULL,
    `dt_payment`            DATE           NOT NULL,
    `user_id`               INT
);

-- Adicionando constraints de unicidade
ALTER TABLE `users`
    ADD UNIQUE (`cpf`);
ALTER TABLE `users`
    ADD UNIQUE (`email`);
ALTER TABLE `subscriptions_type`
    ADD UNIQUE (`product_key`);
ALTER TABLE `user_payment_info`
    ADD UNIQUE (`card_number`);

-- Adicionando constraints foreign key
ALTER TABLE `users`
    ADD CONSTRAINT `fk_user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`);

ALTER TABLE `users`
    ADD CONSTRAINT `fk_subscriptions_type_id` FOREIGN KEY (`subscriptions_type_id`) REFERENCES `subscriptions_type` (`subscriptions_type_id`);

ALTER TABLE `user_credentials`
    ADD CONSTRAINT `fk_3_user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`);

ALTER TABLE `user_payment_info`
    ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`users_id`);

-- Inserindo dados na tabela subscriptions_type
INSERT INTO `subscriptions_type` (`name`, `access_months`, `price`, `product_key`)
VALUES ('PLANO MENSAL', 1, 75.00, 'MONTH22'),
       ('PLANO ANUAL', 12, 697.00, 'YEAR22'),
       ('PLANO VITALICIO', NULL, 1497.00, 'PERPETUAL22');

-- Inserindo dados na tabela user_type
INSERT INTO `user_type` (`name`, `description`)
VALUES ('PROFESSOR', 'Professores da plataforma - cadastro administrativo'),
       ('ADMINISTRADOR', 'Administrador da plataforma - cadastro administrativo'),
       ('ALUNO', 'Aluno da plataforma - cadastro via fluxo normal');
