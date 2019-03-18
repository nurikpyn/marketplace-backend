CREATE TABLE `product_images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_product` bigint(20) NOT NULL,
  `url` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_product`) REFERENCES `products`(`id`)
);

ALTER TABLE `products` ADD COLUMN `primary_image_url` varchar(250) DEFAULT NULL;