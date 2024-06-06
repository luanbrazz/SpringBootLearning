-- Esta linha está alterando a tabela `user_payment_info`, especificamente mudando o nome da coluna de `instalments` para `installments` e mantendo o tipo de dado como `INT`.
ALTER TABLE `user_payment_info` CHANGE `instalments` `installments` INT;
