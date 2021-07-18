/*
 Navicat Premium Data Transfer

 Source Server         : 140-主库
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 10.100.6.140:3306
 Source Schema         : eladmin2

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 18/07/2021 10:23:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for boss_product
-- ----------------------------
DROP TABLE IF EXISTS `boss_product`;
CREATE TABLE `boss_product`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CODE',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` int NULL DEFAULT NULL COMMENT '类型，1-基础包产品 2-增值包产品 3-套餐产品',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `CODE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of boss_product
-- ----------------------------
INSERT INTO `boss_product` VALUES (1, 'Fee_Month_TEST_AS', '包月-1224', 1);
INSERT INTO `boss_product` VALUES (2, '888000001', 'VIP影视增值包月0.01元', 2);
INSERT INTO `boss_product` VALUES (3, '888000002', '优宝乐园包月100.01元', 3);
INSERT INTO `boss_product` VALUES (4, '888000003', '爱看包月0.01元', 2);
INSERT INTO `boss_product` VALUES (5, '', '测试包', 2);
INSERT INTO `boss_product` VALUES (8, NULL, '包月', 3);
INSERT INTO `boss_product` VALUES (10, NULL, '包月-1222', 1);

-- ----------------------------
-- Table structure for boss_product_0
-- ----------------------------
DROP TABLE IF EXISTS `boss_product_0`;
CREATE TABLE `boss_product_0`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CODE',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` int NULL DEFAULT NULL COMMENT '类型，1-基础包产品 2-增值包产品 3-套餐产品',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `CODE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of boss_product_0
-- ----------------------------
INSERT INTO `boss_product_0` VALUES (5, '', '测试包', 2);

-- ----------------------------
-- Table structure for boss_product_1
-- ----------------------------
DROP TABLE IF EXISTS `boss_product_1`;
CREATE TABLE `boss_product_1`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CODE',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` int NULL DEFAULT NULL COMMENT '类型，1-基础包产品 2-增值包产品 3-套餐产品',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `CODE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of boss_product_1
-- ----------------------------
INSERT INTO `boss_product_1` VALUES (1, 'Fee_Month_TEST_AS', '包月-1224', 1);
INSERT INTO `boss_product_1` VALUES (3, '888000002', '优宝乐园包月100.01元', 3);

-- ----------------------------
-- Table structure for boss_product__service
-- ----------------------------
DROP TABLE IF EXISTS `boss_product__service`;
CREATE TABLE `boss_product__service`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `product_id` bigint NULL DEFAULT NULL COMMENT '产品ID',
  `service_id` bigint NULL DEFAULT NULL COMMENT '服务ID',
  `status` int NULL DEFAULT NULL COMMENT '状态：0-下线，1-上线',
  `sequence` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `PRODUCT_ID`(`product_id`) USING BTREE,
  INDEX `SERVICE_ID`(`service_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品服务关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of boss_product__service
-- ----------------------------
INSERT INTO `boss_product__service` VALUES (2, 2, 4, 1, 1);
INSERT INTO `boss_product__service` VALUES (3, 2, 3, 1, 20);
INSERT INTO `boss_product__service` VALUES (9, 1, 4, 0, 3);
INSERT INTO `boss_product__service` VALUES (10, 8, 3, 0, 17);
INSERT INTO `boss_product__service` VALUES (11, 4, 2, 0, 4);
INSERT INTO `boss_product__service` VALUES (12, 4, 3, 0, 18);
INSERT INTO `boss_product__service` VALUES (13, 4, 1, 1, 21);
INSERT INTO `boss_product__service` VALUES (14, 4, 4, 0, 5);
INSERT INTO `boss_product__service` VALUES (15, 5, 2, 1, 16);
INSERT INTO `boss_product__service` VALUES (16, 5, 4, 1, 24);
INSERT INTO `boss_product__service` VALUES (17, 5, 3, 1, 6);
INSERT INTO `boss_product__service` VALUES (18, 3, 2, 1, 15);
INSERT INTO `boss_product__service` VALUES (19, 3, 1, 1, 25);
INSERT INTO `boss_product__service` VALUES (22, 2, 5, 0, 13);
INSERT INTO `boss_product__service` VALUES (23, 4, 5, 0, 12);
INSERT INTO `boss_product__service` VALUES (24, 2, 1, 1, 8);
INSERT INTO `boss_product__service` VALUES (25, 10, 3, 0, 11);
INSERT INTO `boss_product__service` VALUES (26, 10, 4, 0, 10);
INSERT INTO `boss_product__service` VALUES (28, 1, 5, 0, 9);
INSERT INTO `boss_product__service` VALUES (42, 3, 3, 1, 16);
INSERT INTO `boss_product__service` VALUES (43, 3, 5, 1, 28);

-- ----------------------------
-- Table structure for boss_service
-- ----------------------------
DROP TABLE IF EXISTS `boss_service`;
CREATE TABLE `boss_service`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of boss_service
-- ----------------------------
INSERT INTO `boss_service` VALUES (1, '服务包爱上测试');
INSERT INTO `boss_service` VALUES (3, 'VIP影视增值包');
INSERT INTO `boss_service` VALUES (5, '服务包0625');

SET FOREIGN_KEY_CHECKS = 1;
