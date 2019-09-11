ALTER TABLE `lolik_web`.`ocenka` ADD INDEX `OCENKA_CHISLO_IDX` (`chislo` ASC), ADD INDEX `OCENKA_IP_ADRES_IDX` (`ip_adres` ASC);
ALTER TABLE `lolik_web`.`polzovatel_otvet` ADD INDEX `POLZOVATEL_OTVET_CHISLO_IDX` (`chislo` ASC), ADD INDEX `IP_ADRES_IDX` (`ip_adres` ASC);
ALTER TABLE `lolik_web`.`video_klip` ADD INDEX `VIDEO_AKTIVNOE_IDX` (`aktivnii` ASC);
ALTER TABLE `lolik_web`.`opros` ADD INDEX `OPROS_AKTIVNII_IDX` (`aktivnii` ASC);
ALTER TABLE `lolik_web`.`tranzakcia` ADD INDEX `POKUPATEL_EMAIL_IDX` (`pokupatel_email` ASC);