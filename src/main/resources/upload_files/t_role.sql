/*
 Navicat Premium Data Transfer

 Source Server         : localhost-3306-MySQL57
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : db_springboot01

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 02/08/2019 20:02:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menus` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (2, '管理员', NULL, '2019-8-2 18:29:53', NULL, NULL);
INSERT INTO `t_role` VALUES (4, '经理', NULL, '2019-8-2 18:29:53', NULL, NULL);
INSERT INTO `t_role` VALUES (6, '普通用户', NULL, '2019-8-2 18:29:53', NULL, NULL);
INSERT INTO `t_role` VALUES (8, '测试', NULL, '2019-8-2 18:29:53', NULL, NULL);
INSERT INTO `t_role` VALUES (10, 'hhh', NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES (12, '老师', NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES (14, '888', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
