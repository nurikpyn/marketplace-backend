CREATE TABLE `product_types` (
  `id` int(2) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_description` (`description`)
);

CREATE TABLE `providers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_description` (`name`)
);

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_product_provider` varchar(50) NOT NULL,
  `id_provider` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `value` DECIMAL(13, 4) NOT NULL,
  `id_product_type` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_provider`) REFERENCES `providers`(`id`),
  FOREIGN KEY (`id_product_type`) REFERENCES `product_types`(`id`)
);
