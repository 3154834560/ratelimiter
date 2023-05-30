/*
 Navicat Premium Data Transfer

 Source Server         : Y
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : shuzi

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 06/04/2023 21:25:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` bigint(0) NOT NULL,
  `version` bigint(0) NOT NULL,
  `delete_flag` int(0) NOT NULL,
  `create_at` timestamp(0) NOT NULL,
  `update_at` timestamp(0) NOT NULL,
  `customer_id` bigint(0) NOT NULL,
  `account_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fund` decimal(25, 5) NOT NULL,
  `type_id` bigint(0) NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_number_unique`(`account_number`) USING BTREE,
  INDEX `account_acountType_fk`(`type_id`) USING BTREE,
  INDEX `account_customer_fk`(`customer_id`) USING BTREE,
  CONSTRAINT `account_acountType_fk` FOREIGN KEY (`type_id`) REFERENCES `account_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `account_customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (11, 17, 0, '2023-04-06 14:37:52', '2023-04-06 14:37:55', 1, '11111111111111', '$2a$10$zpXdENw7EZUi7G9Vjo9MWubbvfu6alKveWANlfKBYrIBqWAL6n7BS', 484.00000, 1, 'normal');
INSERT INTO `account` VALUES (12, 17, 0, '2023-04-06 14:37:52', '2023-04-06 14:37:55', 2, '11111111111112', '$2a$10$zpXdENw7EZUi7G9Vjo9MWubbvfu6alKveWANlfKBYrIBqWAL6n7BS', 16.00000, 1, 'normal');
INSERT INTO `account` VALUES (13, 1, 0, '2023-04-06 14:37:52', '2023-04-06 14:37:55', 1, '11111111111113', '$2a$10$zpXdENw7EZUi7G9Vjo9MWubbvfu6alKveWANlfKBYrIBqWAL6n7BS', 11111111.00000, 1, 'freeze');

-- ----------------------------
-- Table structure for account_type
-- ----------------------------
DROP TABLE IF EXISTS `account_type`;
CREATE TABLE `account_type`  (
  `id` bigint(0) NOT NULL,
  `version` bigint(0) NOT NULL,
  `delete_flag` int(0) NOT NULL,
  `create_at` timestamp(0) NOT NULL,
  `update_at` timestamp(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_type
-- ----------------------------
INSERT INTO `account_type` VALUES (1, 1, 0, '2023-04-06 14:37:39', '2023-04-06 14:37:42', 'account_type1', NULL);

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` bigint(0) NOT NULL,
  `version` bigint(0) NOT NULL,
  `delete_flag` int(0) NOT NULL,
  `create_at` timestamp(0) NOT NULL,
  `update_at` timestamp(0) NOT NULL,
  `customer_id` bigint(0) NOT NULL,
  `account_id` bigint(0) NOT NULL,
  `target_account_id` bigint(0) NOT NULL,
  `amount` decimal(25, 5) NOT NULL,
  `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `target_customer_id` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bill_account_fk`(`account_id`) USING BTREE,
  INDEX `bill_targeAccount_fk`(`target_account_id`) USING BTREE,
  INDEX `bill_customer_fk`(`customer_id`) USING BTREE,
  INDEX `bill_targetCustomer_fk`(`target_customer_id`) USING BTREE,
  CONSTRAINT `bill_account_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bill_customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bill_targetAccount_fk` FOREIGN KEY (`target_account_id`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bill_targetCustomer_fk` FOREIGN KEY (`target_customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(0) NOT NULL,
  `version` bigint(0) NOT NULL,
  `delete_flag` int(0) NOT NULL,
  `create_at` timestamp(0) NOT NULL,
  `update_at` timestamp(0) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `head_portrait_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `credit_score` decimal(5, 2) NOT NULL,
  `type_id` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `customer_customerType_fk`(`type_id`) USING BTREE,
  CONSTRAINT `customer_customerType_fk` FOREIGN KEY (`type_id`) REFERENCES `customer_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 1, 0, '2023-04-06 14:37:05', '2023-04-06 14:37:08', 'wjy', '$2a$10$zpXdENw7EZUi7G9Vjo9MWubbvfu6alKveWANlfKBYrIBqWAL6n7BS', 'MALE', '110', NULL, NULL, 100.00, 1);
INSERT INTO `customer` VALUES (2, 1, 0, '2023-04-06 14:37:05', '2023-04-06 14:37:08', 'yqy', '$2a$10$zpXdENw7EZUi7G9Vjo9MWubbvfu6alKveWANlfKBYrIBqWAL6n7BS', 'FEMALE', '110', NULL, NULL, 100.00, 1);

-- ----------------------------
-- Table structure for customer_type
-- ----------------------------
DROP TABLE IF EXISTS `customer_type`;
CREATE TABLE `customer_type`  (
  `id` bigint(0) NOT NULL,
  `version` bigint(0) NOT NULL,
  `delete_flag` int(0) NOT NULL,
  `create_at` timestamp(0) NOT NULL,
  `update_at` timestamp(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_type
-- ----------------------------
INSERT INTO `customer_type` VALUES (1, 1, 0, '2023-04-06 14:36:53', '2023-04-06 14:36:56', 'customer_type1', NULL);

-- ----------------------------
-- Table structure for identity_card
-- ----------------------------
DROP TABLE IF EXISTS `identity_card`;
CREATE TABLE `identity_card`  (
  `id` bigint(0) NOT NULL,
  `version` bigint(0) NOT NULL,
  `delete_flag` int(0) NOT NULL,
  `create_at` timestamp(0) NOT NULL,
  `update_at` timestamp(0) NOT NULL,
  `customer_id` bigint(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ethnic_group` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `birthday` timestamp(0) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `issuing_authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `begin_at` timestamp(0) NULL DEFAULT NULL,
  `end_at` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idNumber_unique`(`id_number`) USING BTREE,
  INDEX `identityCard_customer_fk`(`customer_id`) USING BTREE,
  CONSTRAINT `identityCard_customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of identity_card
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
