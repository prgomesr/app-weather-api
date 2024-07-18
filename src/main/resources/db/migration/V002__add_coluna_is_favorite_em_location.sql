ALTER TABLE `location`
    ADD COLUMN `is_favorite` TINYINT NOT NULL DEFAULT 0 AFTER `state`;