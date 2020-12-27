/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : db_mock

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 27/12/2020 22:25:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for error
-- ----------------------------
DROP TABLE IF EXISTS `error`;
CREATE TABLE `error` (
  `id` int NOT NULL AUTO_INCREMENT,
  `request_id` int DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `is_drop_connection` int DEFAULT NULL,
  `delay` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for expectations
-- ----------------------------
DROP TABLE IF EXISTS `expectations`;
CREATE TABLE `expectations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_id` int DEFAULT NULL,
  `expectations_name` varchar(64) DEFAULT NULL,
  `is_open` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forward
-- ----------------------------
DROP TABLE IF EXISTS `forward`;
CREATE TABLE `forward` (
  `id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `host` varchar(64) DEFAULT NULL,
  `port` int DEFAULT NULL,
  `is_secure` int DEFAULT NULL,
  `delay` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for override_forward
-- ----------------------------
DROP TABLE IF EXISTS `override_forward`;
CREATE TABLE `override_forward` (
  `id` int NOT NULL AUTO_INCREMENT,
  `request_id` int DEFAULT NULL,
  `host` varchar(64) DEFAULT NULL,
  `is_secure` int DEFAULT NULL,
  `delay` int DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `method` varchar(64) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `headers` varchar(255) DEFAULT NULL,
  `cookies` varchar(255) DEFAULT NULL,
  `query_params` varchar(255) DEFAULT NULL,
  `path_params` varchar(255) DEFAULT NULL,
  `is_keep_alive` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `proxy_address` varchar(64) DEFAULT NULL,
  `port` int DEFAULT NULL,
  `is_secure` int DEFAULT NULL,
  `is_open` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for request
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expectations_id` int NOT NULL,
  `response_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `method` varchar(64) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `is_secure` int unsigned NOT NULL DEFAULT '0',
  `is_keep_alive` int DEFAULT '0',
  `headers` varchar(255) DEFAULT NULL,
  `cookies` varchar(255) DEFAULT NULL,
  `query_params` varchar(255) DEFAULT NULL,
  `path_params` varchar(255) DEFAULT NULL,
  `is_open` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for response
-- ----------------------------
DROP TABLE IF EXISTS `response`;
CREATE TABLE `response` (
  `id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `status_code` int DEFAULT NULL,
  `content_type` varchar(64) DEFAULT NULL,
  `body` text,
  `headers` varchar(1000) DEFAULT NULL,
  `cookies` varchar(1000) DEFAULT NULL,
  `delay` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
