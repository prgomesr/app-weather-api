CREATE TABLE IF NOT EXISTS `location`
(
    `id`      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(150)    NOT NULL,
    `lat`     DECIMAL(10, 7)  NOT NULL,
    `lon`     DECIMAL(10, 7)  NOT NULL,
    `country` VARCHAR(100)    NOT NULL,
    `state`   VARCHAR(50)     NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    default charset = utf8;