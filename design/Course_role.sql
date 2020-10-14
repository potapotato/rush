/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : rush

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 14/10/2020 22:01:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Course_role
-- ----------------------------
DROP TABLE IF EXISTS `Course_role`;
CREATE TABLE `Course_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Course_role_Course_id_fk`(`course_id`) USING BTREE,
  INDEX `Course_role_Role_id_fk`(`role_id`) USING BTREE,
  CONSTRAINT `Course_role_Course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Course_role_Role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
