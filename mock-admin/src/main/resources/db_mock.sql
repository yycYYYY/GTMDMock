/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50702
Source Host           : localhost:3306
Source Database       : db_mock

Target Server Type    : MYSQL
Target Server Version : 50702
File Encoding         : 65001

Date: 2020-11-30 20:50:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for error
-- ----------------------------
DROP TABLE IF EXISTS `error`;
CREATE TABLE `error` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(64) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `is_drop_connection` int(1) DEFAULT NULL,
  `delay` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of error
-- ----------------------------

-- ----------------------------
-- Table structure for expectations
-- ----------------------------
DROP TABLE IF EXISTS `expectations`;
CREATE TABLE `expectations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `expectations_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expectations
-- ----------------------------
INSERT INTO `expectations` VALUES ('1', '3', 'hq_test');

-- ----------------------------
-- Table structure for forward
-- ----------------------------
DROP TABLE IF EXISTS `forward`;
CREATE TABLE `forward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(64) NOT NULL,
  `host` varchar(64) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `is_secure` int(1) DEFAULT NULL,
  `delay` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forward
-- ----------------------------

-- ----------------------------
-- Table structure for override_forward
-- ----------------------------
DROP TABLE IF EXISTS `override_forward`;
CREATE TABLE `override_forward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) DEFAULT NULL,
  `host` varchar(64) DEFAULT NULL,
  `is_secure` int(1) DEFAULT NULL,
  `delay` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `method` varchar(64) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `headers` varchar(255) DEFAULT NULL,
  `cookies` varchar(255) DEFAULT NULL,
  `query_params` varchar(255) DEFAULT NULL,
  `path_params` varchar(255) DEFAULT NULL,
  `is_keep_alive` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of override_forward
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(64) CHARACTER SET utf8mb4 NOT NULL,
  `proxy_address` varchar(64) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `is_secure` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', 'test', 'baidu.com', '1081', '0');
INSERT INTO `project` VALUES ('2', 'test2', 'google.com', '1082', '1');
INSERT INTO `project` VALUES ('3', 'hq', 'hq-dev.tigerfintech.com', '1083', '1');
INSERT INTO `project` VALUES ('4', 'auth', null, '1084', null);

-- ----------------------------
-- Table structure for request
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) NOT NULL,
  `expectations_id` int(11) NOT NULL,
  `matcher_type` varchar(64) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `method` varchar(64) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `is_secure` int(1) unsigned NOT NULL DEFAULT '0',
  `is_keep_alive` int(1) DEFAULT '0',
  `headers` varchar(255) DEFAULT NULL,
  `cookies` varchar(255) DEFAULT NULL,
  `query_params` varchar(255) DEFAULT NULL,
  `path_params` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of request
-- ----------------------------
INSERT INTO `request` VALUES ('1', '1', '1', 'response', '/stock_info/dividend/quote/BIIB', 'get', '', '0', '1', null, null, null, null);

-- ----------------------------
-- Table structure for response
-- ----------------------------
DROP TABLE IF EXISTS `response`;
CREATE TABLE `response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) NOT NULL,
  `status_code` int(11) DEFAULT NULL,
  `content_type` varchar(64) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `headers` varchar(255) DEFAULT NULL,
  `cookies` varchar(255) DEFAULT NULL,
  `delay` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of response
-- ----------------------------
INSERT INTO `response` VALUES ('1', '1', null, null, 'hq test success', null, null, null);
