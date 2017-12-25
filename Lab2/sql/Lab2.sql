/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : J2EELab

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 12/25/2017 11:46:48 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `myuser`
-- ----------------------------
DROP TABLE IF EXISTS `myuser`;
CREATE TABLE `myuser` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `myuser_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `myuser`
-- ----------------------------
BEGIN;
INSERT INTO `myuser` VALUES ('skx', 'skx'), ('szs', 'szs'), ('zgq', 'zgq'), ('zyz', 'zyz'), ('zzc', 'zzc'), ('宋奎熹', '123');
COMMIT;

-- ----------------------------
--  Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `ordertime` datetime NOT NULL,
  `ordername` varchar(255) CHARACTER SET latin1 NOT NULL,
  `ordercount` int(11) NOT NULL,
  `orderprice` double NOT NULL,
  `username` varchar(255) NOT NULL,
  `isoutofstock` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`oid`),
  KEY `order_myuser_username_fk` (`username`),
  CONSTRAINT `order_myuser_username_fk` FOREIGN KEY (`username`) REFERENCES `myuser` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `order`
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES ('1', '2017-12-13 20:44:15', 'Apple', '123', '123.12', 'skx', '1'), ('2', '2017-06-13 18:44:36', 'Orange', '64', '234.62', 'zzc', '0'), ('3', '2017-08-16 20:45:24', 'Banana', '8', '30.2', '宋奎熹', '0'), ('4', '2016-06-16 23:45:36', 'Watermelon', '421', '888.49', 'szs', '0'), ('5', '2016-09-19 16:48:19', 'Peach', '451', '999.21', 'szs', '0'), ('6', '2017-10-03 17:53:55', 'Pear', '23', '145.67', 'szs', '0'), ('7', '2014-08-28 01:49:16', 'Lemon', '99', '467.83', 'skx', '0'), ('8', '2016-09-20 09:42:25', 'Cherry', '138', '99.12', 'szs', '0'), ('9', '2015-07-23 06:13:08', 'Kiwifruit', '29', '388.41', 'szs', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
