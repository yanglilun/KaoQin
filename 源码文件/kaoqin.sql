/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50550
 Source Host           : 127.0.0.1:3306
 Source Schema         : kaoqin

 Target Server Type    : MySQL
 Target Server Version : 50550
 File Encoding         : 65001

 Date: 23/09/2019 08:33:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `week` int(11) NULL DEFAULT NULL,
  `day` int(11) NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES (2, '17203010217', 1, 1, 'yllzjz.jpg', 1, '补课');
INSERT INTO `apply` VALUES (5, '17203010217', 1, 2, '15686387710981966976028.jpg', 0, '请假');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `classid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `subname` varchar(60) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `classname` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `subid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `year` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`classid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('102', '计算机应用技术', '计应2班', '2030', '2017');
INSERT INTO `class` VALUES ('101', '计算机应用技术', '计应1班', '2030', '2017');
INSERT INTO `class` VALUES ('103', '计算机应用技术', '计应3班', '2030', '2017');
INSERT INTO `class` VALUES ('104', '计算机应用技术', '计应4班', '2030', '2017');
INSERT INTO `class` VALUES ('111', '软件工程', '软工1班', '2031', '2017');
INSERT INTO `class` VALUES ('112', '软件工程', '软工2班', '2031', '2017');
INSERT INTO `class` VALUES ('113', '软件工程', '软工3班', '2031', '2017');
INSERT INTO `class` VALUES ('105', '计算机应用技术', '计应5班', '2030', '2017');
INSERT INTO `class` VALUES ('106', '计算机应用技术', '计应6班', '2030', '2017');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `gid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `gname` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('2', '计算机科学学院');

-- ----------------------------
-- Table structure for late
-- ----------------------------
DROP TABLE IF EXISTS `late`;
CREATE TABLE `late`  (
  `sid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `week` int(11) NULL DEFAULT NULL,
  `day` int(11) NULL DEFAULT NULL,
  `time` int(11) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of late
-- ----------------------------
INSERT INTO `late` VALUES ('17203010212', 1, 2, 2);
INSERT INTO `late` VALUES ('17203010223', 1, 2, 2);
INSERT INTO `late` VALUES ('17203010217', 2, 4, 1);
INSERT INTO `late` VALUES ('17203010210', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010209', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010208', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010207', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010206', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010205', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010204', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010203', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010202', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010201', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010214', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010217', 1, 5, 1);
INSERT INTO `late` VALUES ('17203010217', 2, 1, 1);
INSERT INTO `late` VALUES ('17203010218', 2, 1, 1);
INSERT INTO `late` VALUES ('17203010229', 2, 1, 2);
INSERT INTO `late` VALUES ('17203010220', 2, 1, 1);
INSERT INTO `late` VALUES ('17203010234', 2, 1, 1);
INSERT INTO `late` VALUES ('17203010217', 2, 2, 1);
INSERT INTO `late` VALUES ('17203010218', 2, 2, 1);
INSERT INTO `late` VALUES ('17203010229', 2, 2, 2);
INSERT INTO `late` VALUES ('17203010220', 2, 2, 1);
INSERT INTO `late` VALUES ('17203010234', 2, 2, 1);
INSERT INTO `late` VALUES ('17203010101', 1, 2, 2);
INSERT INTO `late` VALUES ('17203010103', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010125', 1, 2, 2);
INSERT INTO `late` VALUES ('17203010130', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010102', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010108', 1, 2, 2);
INSERT INTO `late` VALUES ('17203010105', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010109', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010115', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010118', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010102', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010106', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010108', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010105', 1, 1, 2);
INSERT INTO `late` VALUES ('17203010109', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010125', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010126', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010124', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010123', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010106', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010114', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010118', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010119', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010102', 1, 5, 1);
INSERT INTO `late` VALUES ('17203010103', 1, 5, 1);
INSERT INTO `late` VALUES ('17203010101', 10, 1, 1);
INSERT INTO `late` VALUES ('17203010101', 1, 2, 1);
INSERT INTO `late` VALUES ('17203010101', 1, 3, 2);
INSERT INTO `late` VALUES ('17203010103', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010102', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010105', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010106', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010109', 1, 3, 1);
INSERT INTO `late` VALUES ('17203010101', 1, 1, 2);
INSERT INTO `late` VALUES ('17203010102', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010103', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010104', 1, 1, 1);
INSERT INTO `late` VALUES ('17203010204', 2, 4, 2);
INSERT INTO `late` VALUES ('17203010201', 3, 4, 1);
INSERT INTO `late` VALUES ('17203010202', 3, 4, 1);
INSERT INTO `late` VALUES ('17203010203', 3, 4, 1);
INSERT INTO `late` VALUES ('17203010214', 3, 4, 2);
INSERT INTO `late` VALUES ('17203010215', 3, 4, 1);
INSERT INTO `late` VALUES ('17203010217', 3, 4, 1);
INSERT INTO `late` VALUES ('17203010219', 3, 4, 1);
INSERT INTO `late` VALUES ('17203010218', 3, 4, 1);
INSERT INTO `late` VALUES ('17203010217', 3, 5, 1);
INSERT INTO `late` VALUES ('17203010219', 3, 5, 1);
INSERT INTO `late` VALUES ('17203010227', 3, 5, 1);
INSERT INTO `late` VALUES ('17203010225', 3, 5, 3);
INSERT INTO `late` VALUES ('17203010224', 3, 5, 1);
INSERT INTO `late` VALUES ('17203010226', 3, 5, 1);
INSERT INTO `late` VALUES ('17203010101', 3, 1, 1);
INSERT INTO `late` VALUES ('17203010104', 3, 1, 1);
INSERT INTO `late` VALUES ('17203010109', 3, 1, 1);
INSERT INTO `late` VALUES ('17203010114', 3, 1, 1);
INSERT INTO `late` VALUES ('17203010115', 3, 1, 1);
INSERT INTO `late` VALUES ('17203010116', 3, 1, 1);
INSERT INTO `late` VALUES ('17203010101', 3, 2, 2);
INSERT INTO `late` VALUES ('17203010104', 3, 2, 1);
INSERT INTO `late` VALUES ('17203010105', 3, 2, 2);
INSERT INTO `late` VALUES ('17203010106', 3, 2, 1);
INSERT INTO `late` VALUES ('17203010204', 4, 1, 2);
INSERT INTO `late` VALUES ('17203010205', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010206', 4, 1, 2);
INSERT INTO `late` VALUES ('17203010207', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010212', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010214', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010201', 4, 1, 2);
INSERT INTO `late` VALUES ('17203010202', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010204', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010205', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010216', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010214', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010215', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010217', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010221', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010225', 4, 1, 1);
INSERT INTO `late` VALUES ('17203010227', 4, 1, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` varchar(30) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `sname` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `classid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `subname` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `num` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('17203010232', '方凛', '102', NULL, NULL, '计算机应用技术', '32');
INSERT INTO `student` VALUES ('17203010231', '田睿涵', '102', NULL, NULL, '计算机应用技术', '31');
INSERT INTO `student` VALUES ('17203010229', '陈真', '102', NULL, NULL, '计算机应用技术', '29');
INSERT INTO `student` VALUES ('17203010228', '张金', '102', NULL, NULL, '计算机应用技术', '28');
INSERT INTO `student` VALUES ('17203010227', '王熙敏', '102', NULL, NULL, '计算机应用技术', '27');
INSERT INTO `student` VALUES ('17203010226', '沈文华', '102', NULL, NULL, '计算机应用技术', '26');
INSERT INTO `student` VALUES ('17203010225', '卢小奥', '102', NULL, NULL, '计算机应用技术', '25');
INSERT INTO `student` VALUES ('17203010224', '来学勇', '102', NULL, NULL, '计算机应用技术', '24');
INSERT INTO `student` VALUES ('17203010223', '钱俊', '102', NULL, NULL, '计算机应用技术', '23');
INSERT INTO `student` VALUES ('17203010222', '万俊杰', '102', NULL, NULL, '计算机应用技术', '22');
INSERT INTO `student` VALUES ('17203010221', '闫圣豪', '102', NULL, NULL, '计算机应用技术', '21');
INSERT INTO `student` VALUES ('17203010220', '姜光耀', '102', NULL, NULL, '计算机应用技术', '20');
INSERT INTO `student` VALUES ('17203010219', '孙鹏', '102', NULL, NULL, '计算机应用技术', '19');
INSERT INTO `student` VALUES ('17203010218', '黄威威', '102', NULL, NULL, '计算机应用技术', '18');
INSERT INTO `student` VALUES ('17203010217', '杨立伦', '102', '123123', 1, '计算机应用技术', '17');
INSERT INTO `student` VALUES ('17203010216', '张文斌', '102', NULL, 0, '计算机应用技术', '16');
INSERT INTO `student` VALUES ('17203010215', '熊柳', '102', NULL, NULL, '计算机应用技术', '15');
INSERT INTO `student` VALUES ('17203010214', '吴闵奇', '102', NULL, NULL, '计算机应用技术', '14');
INSERT INTO `student` VALUES ('17203010213', '杨镁娟子', '102', NULL, NULL, '计算机应用技术', '13');
INSERT INTO `student` VALUES ('17203010212', '文健溪', '102', NULL, NULL, '计算机应用技术', '12');
INSERT INTO `student` VALUES ('17203010211', '周豪', '102', NULL, NULL, '计算机应用技术', '11');
INSERT INTO `student` VALUES ('17203010210', '刘炼', '102', NULL, NULL, '计算机应用技术', '10');
INSERT INTO `student` VALUES ('17203010209', '杨康', '102', NULL, NULL, '计算机应用技术', '9');
INSERT INTO `student` VALUES ('17203010208', '胡雅琴', '102', NULL, NULL, '计算机应用技术', '8');
INSERT INTO `student` VALUES ('17203010207', '王健', '102', NULL, NULL, '计算机应用技术', '7');
INSERT INTO `student` VALUES ('17203010206', '谢汉成', '102', NULL, NULL, '计算机应用技术', '6');
INSERT INTO `student` VALUES ('17203010205', '周潇雨', '102', NULL, NULL, '计算机应用技术', '5');
INSERT INTO `student` VALUES ('17203010204', '王焦虎', '102', NULL, NULL, '计算机应用技术', '4');
INSERT INTO `student` VALUES ('17203010203', '刘心雨', '102', NULL, NULL, '计算机应用技术', '3');
INSERT INTO `student` VALUES ('17203010202', '罗子贤', '102', NULL, NULL, '计算机应用技术', '2');
INSERT INTO `student` VALUES ('17203010201', '方俊烽', '102', NULL, 0, '计算机应用技术', '1');
INSERT INTO `student` VALUES ('17203010233', '何冲', '102', NULL, NULL, '计算机应用技术', '33');
INSERT INTO `student` VALUES ('17203010234', '毛世博', '102', NULL, NULL, '计算机应用技术', '34');
INSERT INTO `student` VALUES ('17203010235', '徐哲林', '102', NULL, NULL, '计算机应用技术', '35');
INSERT INTO `student` VALUES ('17203010236', '雷小虎', '102', NULL, NULL, '计算机应用技术', '36');
INSERT INTO `student` VALUES ('17203010238', '陈奕州', '102', NULL, NULL, '计算机应用技术', '38');
INSERT INTO `student` VALUES ('17203010136', '张镕萍', '101', NULL, NULL, '计算机应用技术', '36');
INSERT INTO `student` VALUES ('17203010135', '王万成', '101', NULL, NULL, '计算机应用技术', '35');
INSERT INTO `student` VALUES ('17203010134', '李坤', '101', NULL, NULL, '计算机应用技术', '34');
INSERT INTO `student` VALUES ('17203010133', '李力', '101', NULL, NULL, '计算机应用技术', '33');
INSERT INTO `student` VALUES ('17203010132', '赵培', '101', NULL, NULL, '计算机应用技术', '32');
INSERT INTO `student` VALUES ('17203010131', '关阳', '101', NULL, NULL, '计算机应用技术', '31');
INSERT INTO `student` VALUES ('17203010130', '余传扬', '101', NULL, NULL, '计算机应用技术', '30');
INSERT INTO `student` VALUES ('17203010129', '兰砣', '101', NULL, NULL, '计算机应用技术', '29');
INSERT INTO `student` VALUES ('17203010128', '江湖', '101', NULL, NULL, '计算机应用技术', '28');
INSERT INTO `student` VALUES ('17203010127', '余荧', '101', NULL, NULL, '计算机应用技术', '27');
INSERT INTO `student` VALUES ('17203010126', '陈卓', '101', NULL, NULL, '计算机应用技术', '26');
INSERT INTO `student` VALUES ('17203010125', '杨闻政', '101', NULL, NULL, '计算机应用技术', '25');
INSERT INTO `student` VALUES ('17203010124', '王逸飞', '101', NULL, NULL, '计算机应用技术', '24');
INSERT INTO `student` VALUES ('17203010123', '李卓伦', '101', NULL, NULL, '计算机应用技术', '23');
INSERT INTO `student` VALUES ('17203010122', '陈舒磊', '101', NULL, NULL, '计算机应用技术', '22');
INSERT INTO `student` VALUES ('17203010121', '彭子瑾', '101', NULL, NULL, '计算机应用技术', '21');
INSERT INTO `student` VALUES ('17203010120', '李松', '101', NULL, NULL, '计算机应用技术', '20');
INSERT INTO `student` VALUES ('17203010119', '夏淦', '101', NULL, NULL, '计算机应用技术', '19');
INSERT INTO `student` VALUES ('17203010118', '肖子凯', '101', NULL, NULL, '计算机应用技术', '18');
INSERT INTO `student` VALUES ('17203010117', '刘子威', '101', NULL, NULL, '计算机应用技术', '17');
INSERT INTO `student` VALUES ('17203010116', '刘备', '101', NULL, NULL, '计算机应用技术', '16');
INSERT INTO `student` VALUES ('17203010115', '葛春燕', '101', NULL, NULL, '计算机应用技术', '15');
INSERT INTO `student` VALUES ('17203010114', '张乙栋', '101', NULL, NULL, '计算机应用技术', '14');
INSERT INTO `student` VALUES ('17203010113', '别格', '101', NULL, NULL, '计算机应用技术', '13');
INSERT INTO `student` VALUES ('17203010112', '陈星', '101', NULL, NULL, '计算机应用技术', '12');
INSERT INTO `student` VALUES ('17203010111', '刘欣龙', '101', '123123', 0, '计算机应用技术', '11');
INSERT INTO `student` VALUES ('17203010110', '陈宇琪', '101', NULL, NULL, '计算机应用技术', '10');
INSERT INTO `student` VALUES ('17203010109', '张佳文', '101', NULL, NULL, '计算机应用技术', '9');
INSERT INTO `student` VALUES ('17203010108', '散月', '101', NULL, NULL, '计算机应用技术', '8');
INSERT INTO `student` VALUES ('17203010106', '陈虎', '101', '123123', 0, '计算机应用技术', '6');
INSERT INTO `student` VALUES ('17203010105', '黄朝道', '101', NULL, NULL, '计算机应用技术', '5');
INSERT INTO `student` VALUES ('17203010104', '雷宇', '101', NULL, NULL, '计算机应用技术', '4');
INSERT INTO `student` VALUES ('17203010103', '左星月', '101', '123123', 1, '计算机应用技术', '3');
INSERT INTO `student` VALUES ('17203010102', '刘志毅', '101', NULL, NULL, '计算机应用技术', '2');
INSERT INTO `student` VALUES ('17203010101', '羿昌帅', '101', '123123', 0, '计算机应用技术', '1');
INSERT INTO `student` VALUES ('17203010137', '江豪', '101', NULL, NULL, '计算机应用技术', '37');
INSERT INTO `student` VALUES ('17203010138', '李瑞卿', '101', NULL, NULL, '计算机应用技术', '38');
INSERT INTO `student` VALUES ('2015030311030', '潘宏志', '101', NULL, NULL, '计算机应用技术', '30');
INSERT INTO `student` VALUES ('17203010302', '张天蕊', '103', '123123', 1, '计算机应用技术', '2');
INSERT INTO `student` VALUES ('17203010303', '覃遵顺', '103', NULL, NULL, '计算机应用技术', '3');
INSERT INTO `student` VALUES ('17203010304', '马崇翔', '103', NULL, NULL, '计算机应用技术', '4');
INSERT INTO `student` VALUES ('17203010305', '吴志煌', '103', NULL, NULL, '计算机应用技术', '5');
INSERT INTO `student` VALUES ('17203010306', '杨柳', '103', NULL, NULL, '计算机应用技术', '6');
INSERT INTO `student` VALUES ('17203010307', '陈盈盈', '103', NULL, NULL, '计算机应用技术', '7');
INSERT INTO `student` VALUES ('17203010308', '刘凯', '103', NULL, NULL, '计算机应用技术', '8');
INSERT INTO `student` VALUES ('17203010309', '彭子豪', '103', NULL, NULL, '计算机应用技术', '9');
INSERT INTO `student` VALUES ('17203010310', '黄鑫', '103', NULL, NULL, '计算机应用技术', '10');
INSERT INTO `student` VALUES ('17203010311', '温西豪', '103', NULL, NULL, '计算机应用技术', '11');
INSERT INTO `student` VALUES ('17203010312', '范丽娜', '103', NULL, NULL, '计算机应用技术', '12');
INSERT INTO `student` VALUES ('17203010313', '杨鑫', '103', NULL, NULL, '计算机应用技术', '13');
INSERT INTO `student` VALUES ('17203010314', '曹帅', '103', NULL, NULL, '计算机应用技术', '14');
INSERT INTO `student` VALUES ('17203010315', '薛林', '103', NULL, NULL, '计算机应用技术', '15');
INSERT INTO `student` VALUES ('17203010316', '陈嘉龙', '103', NULL, NULL, '计算机应用技术', '16');
INSERT INTO `student` VALUES ('17203010317', '吴松杰', '103', NULL, NULL, '计算机应用技术', '17');
INSERT INTO `student` VALUES ('17203010318', '李相泉', '103', NULL, NULL, '计算机应用技术', '18');
INSERT INTO `student` VALUES ('17203010319', '张政国', '103', NULL, NULL, '计算机应用技术', '19');
INSERT INTO `student` VALUES ('17203010320', '吴凌霄', '103', NULL, NULL, '计算机应用技术', '20');
INSERT INTO `student` VALUES ('17203010322', '袁运佳', '103', NULL, NULL, '计算机应用技术', '22');
INSERT INTO `student` VALUES ('17203010323', '董颖', '103', NULL, NULL, '计算机应用技术', '23');
INSERT INTO `student` VALUES ('17203010324', '刘文卓', '103', NULL, NULL, '计算机应用技术', '24');
INSERT INTO `student` VALUES ('17203010326', '张浩霖', '103', NULL, NULL, '计算机应用技术', '26');
INSERT INTO `student` VALUES ('17203010327', '朱大伟', '103', NULL, NULL, '计算机应用技术', '27');
INSERT INTO `student` VALUES ('17203010328', '黄聪聪', '103', NULL, NULL, '计算机应用技术', '28');
INSERT INTO `student` VALUES ('17203010329', '涂一驰', '103', NULL, NULL, '计算机应用技术', '29');
INSERT INTO `student` VALUES ('17203010331', '宋纪元', '103', NULL, NULL, '计算机应用技术', '31');
INSERT INTO `student` VALUES ('17203010332', '金帆', '103', NULL, NULL, '计算机应用技术', '32');
INSERT INTO `student` VALUES ('17203010333', '张震宇', '103', NULL, NULL, '计算机应用技术', '33');
INSERT INTO `student` VALUES ('17203010334', '黎昶', '103', NULL, NULL, '计算机应用技术', '34');
INSERT INTO `student` VALUES ('17203010335', '阙泓洲', '103', NULL, NULL, '计算机应用技术', '35');
INSERT INTO `student` VALUES ('17203010336', '孟振', '103', NULL, NULL, '计算机应用技术', '36');
INSERT INTO `student` VALUES ('17203010337', '余珂', '103', NULL, NULL, '计算机应用技术', '37');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `subid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `subname` varchar(60) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `gid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`subid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('2030', '计算机应用技术', '2');
INSERT INTO `subject` VALUES ('2031', '软件工程', '2');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `tname` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `subid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `gid` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('2001', '何芝花', '1234', '2030', '2');
INSERT INTO `teacher` VALUES ('2002', '辅导员1', '123123', '2031', '2');

-- ----------------------------
-- View structure for end
-- ----------------------------
DROP VIEW IF EXISTS `end`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `end` AS select student.sname,late.`week`,SUM(late.time) as time from student,late
	WHERE (student.subname =  convert('计算机应用技术' using gbk))
		AND student.sid = late.sid
			AND late.`week`=1
				GROUP BY student.sname
UNION
select student.sname,late.`week`,SUM(late.time) as time from student,late
	WHERE (student.subname =  convert('计算机应用技术' using gbk))
		AND student.sid = late.sid
			AND late.`week`=2
				GROUP BY student.sname
UNION
select student.sname,late.`week`,SUM(late.time) as time from student,late
	WHERE (student.subname =  convert('计算机应用技术' using gbk))
		AND student.sid = late.sid
			AND late.`week`=3
				GROUP BY student.sname 
UNION
select student.sname,late.`week`,SUM(late.time) as time from student,late
	WHERE (student.subname =  convert('计算机应用技术' using gbk))
		AND student.sid = late.sid
			AND late.`week`=4
				GROUP BY student.sname ;

SET FOREIGN_KEY_CHECKS = 1;
