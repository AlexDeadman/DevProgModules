CREATE DATABASE IF NOT EXISTS `dpm`;
USE `dpm`;

CREATE TABLE IF NOT EXISTS `entities`
(
    `id`              bigint      NOT NULL AUTO_INCREMENT,
    `title`           varchar(30) NOT NULL,
    `create_datetime` datetime    NOT NULL,
    `death_datetime`  datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `players`
(
    `id`       bigint NOT NULL,
    `nickname` varchar(30) DEFAULT NULL,
    `exp`      double      DEFAULT NULL,
    KEY `players_entities_id_fk` (`id`),
    CONSTRAINT `players_entities_id_fk` FOREIGN KEY (`id`) REFERENCES `entities` (`id`)
);

CREATE TABLE IF NOT EXISTS `battle_logs`
(
    `log_id`        bigint NOT NULL AUTO_INCREMENT,
    `killer_id`     bigint   DEFAULT NULL,
    `victim_id`     bigint   DEFAULT NULL,
    `kill_datetime` datetime DEFAULT NULL,
    PRIMARY KEY (`log_id`),
    KEY `killer___fk` (`killer_id`),
    KEY `victim___fk` (`victim_id`),
    CONSTRAINT `killer___fk` FOREIGN KEY (`killer_id`) REFERENCES `entities` (`id`),
    CONSTRAINT `victim___fk` FOREIGN KEY (`victim_id`) REFERENCES `entities` (`id`)
);