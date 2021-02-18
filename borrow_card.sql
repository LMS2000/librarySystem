/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : libraryproject

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-18 16:30:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `borrow_card`
-- ----------------------------
DROP TABLE IF EXISTS `borrow_card`;
CREATE TABLE `borrow_card` (
  `bc_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `reader` varchar(20) DEFAULT NULL,
  `rule_id` bigint(11) DEFAULT NULL,
  `is_borrow` tinyint(1) DEFAULT '0' COMMENT '0濠电偛顦崝鎴﹀绩閵忋倖鏅?濠殿喗绻愮徊浠嬫偉?',
  `login_account_id` bigint(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bc_id`),
  KEY `borrow_card_rule_id` (`rule_id`),
  KEY `borrow_card_login_id` (`login_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1805010222 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_card
-- ----------------------------
INSERT INTO `borrow_card` VALUES ('1805010202', '老王', '7', '1', '1', null, null);
INSERT INTO `borrow_card` VALUES ('1805010203', '黄白白', '2', '0', '2', null, null);
INSERT INTO `borrow_card` VALUES ('1805010204', '黄土', '1', '1', '3', null, null);
INSERT INTO `borrow_card` VALUES ('1805010205', '白云', '1', '1', '4', null, null);
INSERT INTO `borrow_card` VALUES ('1805010206', '月', '6', '1', '5', null, null);
INSERT INTO `borrow_card` VALUES ('1805010207', '明月', '1', '1', '6', null, null);
INSERT INTO `borrow_card` VALUES ('1805010208', '明月复苏', '1', '1', '7', null, null);
INSERT INTO `borrow_card` VALUES ('1805010210', '张沙', '1', '1', '8', null, null);
INSERT INTO `borrow_card` VALUES ('1805010211', '星星', '1', '1', null, null, null);
INSERT INTO `borrow_card` VALUES ('1805010212', '明月', '1', '1', null, null, null);
INSERT INTO `borrow_card` VALUES ('1805010213', '辰', '1', '1', null, null, null);
INSERT INTO `borrow_card` VALUES ('1805010214', '小红', '1', '1', null, null, null);
INSERT INTO `borrow_card` VALUES ('1805010217', '赵雪', '2', '1', null, null, null);
INSERT INTO `borrow_card` VALUES ('1805010219', '测试', '1', '0', null, null, null);
