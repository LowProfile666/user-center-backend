/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : usercenter

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 19/12/2024 23:56:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户的昵称',
  `user_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '唯一的登录账号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像的 URL 地址',
  `gender` tinyint NULL DEFAULT NULL COMMENT '0 - 未知，1 - 男，2 - 女',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户的登录密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户的联系电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户的电子邮件地址',
  `user_status` int NULL DEFAULT 0 COMMENT '0 - 正常，1 - 禁用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据插入时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间',
  `delete_flag` tinyint NULL DEFAULT 0 COMMENT '0 - 未删除，1 - 已删除（逻辑删除）',
  `user_role` tinyint NULL DEFAULT 0 COMMENT '0 - 普通用户，1 - 管理员',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_account`(`user_account` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '多低调', 'lowProfile', 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', 0, 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', NULL, NULL, 0, '2024-12-18 10:22:52', '2024-12-19 15:32:03', 1, 0);
INSERT INTO `user` VALUES (2, '哈哈管理', 'admin', 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', 1, '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', NULL, NULL, 0, '2024-12-18 13:33:01', '2024-12-19 15:32:03', 0, 1);
INSERT INTO `user` VALUES (3, '哈哈哈哈', 'user', 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', 0, '831c237928e6212bedaa4451a514ace3174562f6761f6a157a2fe5082b36e2fb', NULL, NULL, 0, '2024-12-18 13:35:28', '2024-12-19 15:32:03', 1, 0);
INSERT INTO `user` VALUES (5, '张三丰', 'user1', 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', 0, '831c237928e6212bedaa4451a514ace3174562f6761f6a157a2fe5082b36e2fb', NULL, NULL, 0, '2024-12-19 13:34:16', '2024-12-19 15:32:03', 0, 0);
INSERT INTO `user` VALUES (6, '里四强', 'user2', 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', 0, '831c237928e6212bedaa4451a514ace3174562f6761f6a157a2fe5082b36e2fb', NULL, NULL, 0, '2024-12-19 13:34:36', '2024-12-19 15:32:03', 0, 0);
INSERT INTO `user` VALUES (7, '王老五', 'test', 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', 1, '937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244', NULL, NULL, 0, '2024-12-19 14:34:47', '2024-12-19 15:32:03', 0, 0);
INSERT INTO `user` VALUES (8, '赵六德', 'test1', 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', 1, '937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244', NULL, NULL, 0, '2024-12-19 14:35:25', '2024-12-19 15:53:48', 1, 0);
INSERT INTO `user` VALUES (9, '钱七', '1111', 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', 0, 'ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1', NULL, NULL, 0, '2024-12-19 14:35:48', '2024-12-19 15:53:46', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
