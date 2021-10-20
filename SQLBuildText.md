# SQL Build Instructions

* CREATE DATABASE `case_study_v1` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

---

* CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address1` varchar(50) NOT NULL,
  `address2` varchar(50) DEFAULT NULL,
  `city` varchar(165) NOT NULL,
  `country` varchar(60) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `state` varchar(2) DEFAULT NULL,
  `token_expired` bit(1) NOT NULL,
  `zip` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

---

* CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

---

* CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


---

* CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(254) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmivyxk9rmgysrmsqw15lqr5b` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

---

* CREATE TABLE `cart_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `product_id` int(11) DEFAULT NULL,
  `shopping_cart_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
  KEY `FKe89gjdx91fxnmkkssyoim8xfu` (`shopping_cart_id`),
  CONSTRAINT `FKe89gjdx91fxnmkkssyoim8xfu` FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_cart` (`id`),
  CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

---

* CREATE TABLE `purchases` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `purchased_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `quantity` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKccuk1alj023nrf87v4jynejcs` (`product_id`),
  KEY `FKp2xo1y9dxs92eylr0cknba7e3` (`user_id`),
  CONSTRAINT `FKccuk1alj023nrf87v4jynejcs` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKp2xo1y9dxs92eylr0cknba7e3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

---

* CREATE TABLE `shopping_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK254qp5akhuaaj9n5co4jww3fk` (`user_id`),
  CONSTRAINT `FK254qp5akhuaaj9n5co4jww3fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

---
