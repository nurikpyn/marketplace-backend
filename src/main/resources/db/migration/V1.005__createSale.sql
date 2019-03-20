CREATE TABLE `sales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_receipt`varchar(200) NOT NULL,
  `id_product` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `quantity` int(3) NOT NULL,
  `total_value` DECIMAL(13, 4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_sales_receipt` (`cod_receipt`),
  FOREIGN KEY (`id_product`) REFERENCES `products`(`id`),
  FOREIGN KEY (`id_user`) REFERENCES `users`(`id`)
);