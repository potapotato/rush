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

 Date: 14/10/2020 22:01:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Question
-- ----------------------------
DROP TABLE IF EXISTS `Question`;
CREATE TABLE `Question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) NOT NULL,
  `question_url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `question_text` varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `answer_url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `answer_text` varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_user_id` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Question_answer_text_uindex`(`answer_text`) USING BTREE,
  UNIQUE INDEX `Question_answer_url_uindex`(`answer_url`) USING BTREE,
  UNIQUE INDEX `Question_question_text_uindex`(`question_text`) USING BTREE,
  UNIQUE INDEX `Question_question_url_uindex`(`question_url`) USING BTREE,
  INDEX `Question_Section_id_fk`(`section_id`) USING BTREE,
  INDEX `Question_User_id_fk`(`create_user_id`) USING BTREE,
  CONSTRAINT `Question_Section_id_fk` FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Question_User_id_fk` FOREIGN KEY (`create_user_id`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
