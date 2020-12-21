/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : gper_mall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-26 23:35:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` bigint(20) NOT NULL COMMENT '商品id，同时也是商品编号',
  `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(100) DEFAULT NULL COMMENT '商品卖点',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
  `num` int(11) DEFAULT NULL COMMENT '库存数量',
  `limit_num` int(11) DEFAULT NULL COMMENT '售卖数量限制',
  `image` varchar(2000) DEFAULT NULL COMMENT '商品图片',
  `cid` bigint(11) DEFAULT NULL COMMENT '所属分类',
  `status` int(1) DEFAULT '1' COMMENT '商品状态 1正常 0下架',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `status` (`status`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES ('100023501', 'Smartisan 双口 & 快充车载充电器', '铝合金机身、双口 & 快充、智能调节', '79.00', '73', '100', 'https://resource.smartisan.com/resource/d4480234a2f24b0ff5acd98288fd902d.jpg,https://resource.smartisan.com/resource/69ebf4ca620e6d5a1bb7cb54741e24d3.jpg,https://resource.smartisan.com/resource/214a422b7d250333bec4398d47eac601.jpg,https://resource.smartisan.com/resource/f512a3c4b97d204555f864d4aa17e7e9.jpg,https://resource.smartisan.com/resource/fc8a5d50ed260d9798cfba39ff5234d0.jpg', '221', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100026701', 'Smartisan 原装快充充电器 18W', '18W 安全快充、支持主流 QC3.0, MTK PE+2.0 快充协议', '49.00', '100', '100', 'https://resource.smartisan.com/resource/dc53bd870ee64d2053ecc51750ece43a.jpg,https://resource.smartisan.com/resource/83ab82fa6d9637d29d6af79d912ee572.jpg,https://resource.smartisan.com/resource/47461596fad00d37cb7a032a03d79286.jpg,https://resource.smartisan.com/resource/f4f6346bea727862087b4761fc8b01d2.jpg,https://resource.smartisan.com/resource/0286c84dba36577f37591f1af2b97402.jpg', '215', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100026801', 'Smartisan 耳机转接头', '即插即用、全面兼容', '19.00', '86', '100', 'https://resource.smartisan.com/resource/45312fb748d54aa2e58a8f4d637e9e65.jpg,https://resource.smartisan.com/resource/1dddddf6488ba89d592a37e9db93ffa2.jpg,https://resource.smartisan.com/resource/31b291594192c568b9fff9190a0d8f44.jpg,https://resource.smartisan.com/resource/561c002e74f6a5982dfaf3b4a44c9af4.jpg', '214', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100036501', '畅呼吸除霾除甲醛高效复合滤芯', '精选双层防护材质，过滤更精细，去味更有效', '699.00', '100', '100', 'https://resource.smartisan.com/resource/00eee903962f17d75950397843117e6e.jpg,https://resource.smartisan.com/resource/7a1f7380f2f2851fe133bd84115c42fe.jpg,https://resource.smartisan.com/resource/e2cd33328fe96214c2bff3ef0652350a.jpg', '228', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100039702', 'Smartisan 帆布鞋', '用于支付测试使用', '1.00', '100', '100', 'https://resource.smartisan.com/resource/578116bddf1d170c89e9af7ba5073fb6.jpg,https://resource.smartisan.com/resource/ebb01298315bf2ebdb6b21ee2c8e4237.jpg,https://resource.smartisan.com/resource/bd634d820859032b4c0f7a521eda486d.jpg,https://resource.smartisan.com/resource/51958a0a771f24e405f1b5de98108528.jpg,https://resource.smartisan.com/resource/e8791dd06c1e964d89436407f8827fe4.jpg', '236', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100040501', '畅呼吸智能空气净化器 · 超级除甲醛版', '800CADR 超强空气净化能力，400CADR超强除甲醛能力，app远程操控，多种专业滤芯可供选择', '2999.00', '100', '100', 'https://resource.smartisan.com/resource/71432ad30288fb860a4389881069b874.png,https://resource.smartisan.com/resource/6ff92d05a3bfab4fad489ca04d3eea5a.png', '226', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100040607', '坚果 3', '坚果 3 意外碎屏保修服务（碎屏险）', '2999.00', '100', '100', 'https://resource.smartisan.com/resource/13e91511f6ba3227ca5378fd2e93c54b.png,https://resource.smartisan.com/resource/fac4130efc39ed4db697cc8d137890e9.png,https://resource.smartisan.com/resource/91dc3f577960e30ca11b632e7b6ebd0f.png,https://resource.smartisan.com/resource/61586b59793ac16bd973010aecad2ca9.png', '210', '1', '2019-08-12 13:06:26', '2019-08-12 13:06:28');
INSERT INTO `tb_item` VALUES ('100042203', '坚果“电池形电池”移动电源', 'Type-C 接口、轻巧便携、多重电路保护', '49.00', '100', '100', 'https://resource.smartisan.com/resource/33954b3f6a2f1614c5482ef130af9cc8.jpg,https://resource.smartisan.com/resource/1910dba5f999debab84c97c55845c74d.jpg,https://resource.smartisan.com/resource/3e62068911a78fb4b7c4ac20520a5216.jpg,https://resource.smartisan.com/resource/0329e3f7d4fd64659b36a9f3726ccf37.jpg', '218', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100042801', 'Smartisan 半入耳式耳机', '经典配色、专业调音、高品质麦克风', '59.00', '100', '100', 'https://resource.smartisan.com/resource/ce632bd67465027861707ec221b37c2d.jpg,https://resource.smartisan.com/resource/10525c4b21f039fc8ccb42cd1586f5cd.jpg,https://resource.smartisan.com/resource/d14645b66ff52c2e5958cd866a7d91e5.jpg,https://resource.smartisan.com/resource/7a4257950f953d6a7048d72de374530f.jpg,https://resource.smartisan.com/resource/dbe085a6f133b944e4e23bbb515c31ff.jpg', '217', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100046401', '坚果 R1', '骁龙 845 处理器 · 光学防抖双摄像头 · 6.17 英寸压力感应屏幕 · 10W快速无线充电功能', '2999.00', '100', '100', 'https://resource.smartisan.com/resource/06c2253354096f5e9ebf0616f1af2086.png', '210', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100047001', '坚果 QuickCharge 4+ 快速充电器', '全面兼容的 18W 快速充电', '1.00', '100', '100', 'https://resource.smartisan.com/resource/a668d1a5f41b04ece82d76ded1e94d3a.jpg,https://resource.smartisan.com/resource/c2375861762d557f65cf880b00161a41.jpg,https://resource.smartisan.com/resource/630dc5c945e78c0613d872cb83222b9e.jpg', '215', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100047101', '坚果 Type-C To Type-C 数据线', 'TPE 环保材质，PTC 过温保护', '39.00', '100', '100', 'https://resource.smartisan.com/resource/8635cb91f2cdbbc5576e069c52b99412.jpg,https://resource.smartisan.com/resource/a9a02318cb09ab38562092a556d0dedc.jpg', '214', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100051701', '坚果 Pro 2S', '双系统，无限屏，骁龙 ™ 710 处理器 · 前置 1600 万像素摄像头 · 6.01 英寸全高清全面屏 · AI 通话降噪 · 人脸解锁 + 指纹解锁 ', '1798.00', '100', '100', 'https://resource.smartisan.com/resource/b07b9765e272f866da6acda4ee107d88.png', '210', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100052801', '坚果砖式蓝牙小音箱', '一款设计出色、音质出众的随身音箱', '149.00', '100', '100', 'https://resource.smartisan.com/resource/6e96ccea3bd56bdd2243eb20330cec30.jpg,https://resource.smartisan.com/resource/a99de61d502b2f29b4a6d847751cf478.jpg,https://resource.smartisan.com/resource/3f6594f3537db91a3a4d6196111429df.jpg,https://resource.smartisan.com/resource/9e45ff0ce5d60627f0b07b1df4c56ed6.jpg,https://resource.smartisan.com/resource/830389bcd0e66569acd5ce05a304a3ea.jpg', '223', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100053001', '坚果彩虹数据线', '七彩配色随机发货，为生活增添一份小小惊喜', '19.00', '100', '100', 'https://resource.smartisan.com/resource/82aab62886740f165a3631ce6cffe895.jpg', '214', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100053202', '地平线 8 号商务旅行箱', '为了野心和远方', '999.00', '100', '100', 'https://resource.smartisan.com/resource/d1dcca9144e8d13ffb33026148599d0a.png', '238', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100053312', '地平线 8 号旅行箱', '简约设计、德国拜耳 PC 箱体', '299.00', '100', '100', 'https://resource.smartisan.com/resource/db4895e45ee6f3339037dbf7200e63f2.png', '238', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100055301', 'Smartisan 快充移动电源 10000mAh', '10000mAh 双向快充、轻盈便携、高标准安全保护', '129.00', '100', '100', 'https://resource.smartisan.com/resource/b7105b0d819e610a9c38d7ca2a813e58.jpg,https://resource.smartisan.com/resource/e47687c8288b324fb997c5bd7b709e80.jpg,https://resource.smartisan.com/resource/c933dd520c84c32edd9f50f664ec53ff.jpg,https://resource.smartisan.com/resource/1ae4fda7154eb92196f78fe9efb0c25f.jpg,https://resource.smartisan.com/resource/422ec86b9924bd5e45d5caa3ba1eaf7d.jpg', '218', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100055601', '各色DNA检测套装', '国内唯一聚焦于行为-基因关联性分析的基因检测解读', '499.00', '100', '100', 'https://resource.smartisan.com/resource/9bffe702b1f0aea221b1f18ddf886958.jpg,https://resource.smartisan.com/resource/30a1fce6a4280847eebf1b412fca39b0.jpg,https://resource.smartisan.com/resource/6681f43f88b9d867a0f33639cbeb47bf.jpg,https://resource.smartisan.com/resource/4fa597703a83cf326713faf2648744ac.jpg,https://resource.smartisan.com/resource/760637b38ba5ec5792e1e99c0d893462.jpg', '263', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100057401', 'Smartisan T恤 迪特拉姆斯', '', '149.00', '100', '100', 'https://resource.smartisan.com/resource/005c65324724692f7c9ba2fc7738db13.png,https://resource.smartisan.com/resource/5068afef4f8866fae065d8c0d450e244.png,https://resource.smartisan.com/resource/a8dfe8f52dfb15c17e2e5c504c7ae2c6.png,https://resource.smartisan.com/resource/d6a6c06e5b51f0c18d8bfc45318163ea.png,https://resource.smartisan.com/resource/46724a81b037d1eca31d665c223b77a1.png', '231', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100057501', 'Smartisan T恤 毕加索', '', '149.00', '100', '100', 'https://resource.smartisan.com/resource/e9cd634b62470713f6b9c5a6065f4a10.jpg,https://resource.smartisan.com/resource/2ea973de25dffab6373dbe5e343f76c8.jpg,https://resource.smartisan.com/resource/57c12d9b6788d005341fe4aefd209fab.jpg,https://resource.smartisan.com/resource/25fb00a88fe6ababcd580a2cf0a14032.jpg,https://resource.smartisan.com/resource/bab385bd6811378389a12d7b7254ed7e.jpg', '231', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100057601', 'Smartisan T恤 皇帝的新装', '', '149.00', '100', '100', 'https://resource.smartisan.com/resource/d9586f7c5bb4578e3128de77a13e4d85.png,https://resource.smartisan.com/resource/07f77245d0f5f78f8ea580e181ec3dce.jpg,https://resource.smartisan.com/resource/0c9c397c8ac68a2ad327e1da8a5cb7d0.jpg,https://resource.smartisan.com/resource/154b35897ed3c1cb8dc1c7aae7b88f1f.jpg,https://resource.smartisan.com/resource/4a1686f2fde86e0aaac49c92395d4b32.jpg', '231', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');
INSERT INTO `tb_item` VALUES ('100057701', 'Smartisan T恤 丑小鸭', '', '149.00', '100', '100', 'https://resource.smartisan.com/resource/c23837ddfa3de0103be11bcbbb744066.png,https://resource.smartisan.com/resource/dad3d8d5ed151ad235ca9215815bc38b.png,https://resource.smartisan.com/resource/95f78a96e20b8e697e9df1c221d585c4.png,https://resource.smartisan.com/resource/33b0c45b3036d2a4267a05d192ccc45f.png,https://resource.smartisan.com/resource/b8bb658cf5cc22f23fb81a4c2ea028ac.png', '231', '1', '2019-07-29 14:37:02', '2019-07-29 14:37:02');

-- ----------------------------
-- Table structure for tb_item_cat
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_cat`;
CREATE TABLE `tb_item_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID=0时代表一级根分类',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `status` int(1) DEFAULT '1' COMMENT '状态 1启用 0禁用',
  `sort_order` int(4) DEFAULT NULL COMMENT '排列序号',
  `is_parent` tinyint(1) DEFAULT '1' COMMENT '是否为父分类 1为true 0为false',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`,`status`) USING BTREE,
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=utf8 COMMENT='商品类目';

-- ----------------------------
-- Records of tb_item_cat
-- ----------------------------
INSERT INTO `tb_item_cat` VALUES ('1', '1', '官方配件', '1', '0', '1', 'https://www.smartisan.com/category/185?type=shop', null, '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('2', '1', '耳机', '1', '0', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('3', '1', '手机充电配件', '1', '1', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('4', '1', '自拍杆', '1', '2', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('5', '1', '音箱', '1', '3', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('6', '1', '车载配件', '1', '4', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('7', '1', '服饰箱包', '1', '1', '1', 'https://www.smartisan.com/category/157', null, '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('8', '7', '服饰', '1', '0', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('9', '7', '鞋', '1', '1', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('10', '7', '箱包', '1', '2', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('11', '1', '畅呼吸', '1', '2', '1', 'https://www.smartisan.com/category/296?type=shop', null, '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('12', '11', '生活电器', '1', '0', '0', '', 'shop', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('202', '8', 'T恤', '1', '0', '0', 'https://resource.smartisan.com/resource/d01dcb91b34b26cda7064a3c9bf655c4.png', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('204', '8', 'POLO衫', '1', '1', '0', 'https://resource.smartisan.com/resource/daa975651d6d700c0f886718c520ee19.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('206', '9', '帆布鞋', '1', '0', '0', 'https://resource.smartisan.com/resource/1db1a8bfee3623fded41c9115b5a5335.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('207', '10', '拉杆箱', '1', '0', '0', 'https://resource.smartisan.com/resource/9960e83a55544fbf7b046013a6f7f414.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('231', '3', '数据线', '1', '0', '0', 'https://resource.smartisan.com/resource/c79a73ffc6f8e782160d978f49f543dc.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('232', '3', '充电器', '1', '1', '0', 'https://resource.smartisan.com/resource/dc53bd870ee64d2053ecc51750ece43a.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('234', '2', '线控耳机', '1', '0', '0', 'https://resource.smartisan.com/resource/72e4c31e4e623f5c37c6489f6d91efc6.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('235', '3', '移动电源', '1', '2', '0', 'https://resource.smartisan.com/resource/afcec520933673b8e03a867e6502f6e0.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('236', '4', '自拍杆', '1', '0', '0', 'https://resource.smartisan.com/resource/b9e61c6d93464454fa2e382632e34cee.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('238', '6', '车载充电器', '1', '0', '0', 'https://resource.smartisan.com/resource/d4480234a2f24b0ff5acd98288fd902d.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('252', '5', '音箱', '1', '0', '0', 'https://resource.smartisan.com/resource/c44f0ab4da5591fc3d0f82b7ac0f4f65.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('292', '10', '包', '1', '1', '0', 'https://resource.smartisan.com/resource/cef4a5433eb95cbdf242d3c1bf5617f8.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('297', '12', '空气净化器', '1', '0', '0', 'https://resource.smartisan.com/resource/71432ad30288fb860a4389881069b874.png', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');
INSERT INTO `tb_item_cat` VALUES ('298', '12', '生活电器配件', '1', '1', '0', 'https://resource.smartisan.com/resource/4d83d72c5ecc288e8d5ddd9d06b80f99.jpg', '', '2019-07-29 14:01:59', '2019-07-29 14:01:59');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `payment` decimal(10,2) DEFAULT NULL COMMENT '实付金额',
  `payment_type` int(1) DEFAULT NULL COMMENT '支付类型 1在线支付 2货到付款',
  `status` int(1) DEFAULT NULL COMMENT '状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败 7-已退款',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `unique_key` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '唯一键',
  PRIMARY KEY (`order_id`),
  KEY `create_time` (`create_time`),
  KEY `status` (`status`),
  KEY `payment_type` (`payment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('31053b08575f44fab2e7816080798768', '158.00', null, '0', '2020-07-26 14:50:00', '2020-07-26 14:50:00', '2020-07-26 14:50:00', null, null, null, '1000000', null);
INSERT INTO `tb_order` VALUES ('3ed82e4f16e24b10b98870d58b27bae7', '158.00', null, '0', '2020-07-26 14:43:06', '2020-07-26 14:43:06', '2020-07-26 14:43:06', null, null, null, '1000000', null);
INSERT INTO `tb_order` VALUES ('67a6c18aa6454519996f19ecab8d858f', '158.00', null, '0', '2020-07-26 14:53:36', '2020-07-26 14:53:36', '2020-07-26 14:53:36', null, null, null, '1000000', null);
INSERT INTO `tb_order` VALUES ('dbbbe1c2caa84c0c9875b3abfc6a40e0', '158.00', null, '0', '2020-07-26 15:13:06', '2020-07-26 15:13:06', '2020-07-26 15:13:06', null, null, null, '1000000', null);
INSERT INTO `tb_order` VALUES ('e1857d9db2e64e8bb48976c78eac78c0', '158.00', null, '0', '2020-07-26 15:10:56', '2020-07-26 15:10:56', '2020-07-26 15:10:56', null, null, null, '1000000', null);
INSERT INTO `tb_order` VALUES ('ee1c590e67f44936bcd71ff68f70b7bd', '158.00', null, '0', '2020-07-26 14:54:50', '2020-07-26 14:54:50', '2020-07-26 14:54:50', null, null, null, '1000000', null);

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `item_id` bigint(20) NOT NULL COMMENT '商品id',
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int(10) DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `total_fee` decimal(10,2) DEFAULT NULL COMMENT '商品总金额',
  `pic_path` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址',
  `status` int(4) DEFAULT NULL COMMENT '1库存已锁定 2库存已释放 3-库存减扣成功',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `oder_item_id` (`order_id`,`item_id`) USING BTREE COMMENT '订单商品唯一索引',
  KEY `item_id` (`item_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------
INSERT INTO `tb_order_item` VALUES ('0e48b7a133b14fc2a725a48ed2104e22', '100023501', 'e1857d9db2e64e8bb48976c78eac78c0', '75', 'Smartisan 双口 & 快充车载充电器', '79.00', '158.00', 'https://resource.smartisan.com/resource/d4480234a2f24b0ff5acd98288fd902d.jpg,https://resource.smartisan.com/resource/69ebf4ca620e6d5a1bb7cb54741e24d3.jpg,https://resource.smartisan.com/resource/214a422b7d250333bec4398d47eac601.jpg,https://resource.smartisan.com/resource/f512a3c4b97d204555f864d4aa17e7e9.jpg,https://resource.smartisan.com/resource/fc8a5d50ed260d9798cfba39ff5234d0.jpg', '1', '2020-07-26 15:10:56', '2020-07-26 23:10:56');
INSERT INTO `tb_order_item` VALUES ('a7c52e1037cc43aba7567ddc166caa3d', '100023501', 'dbbbe1c2caa84c0c9875b3abfc6a40e0', '73', 'Smartisan 双口 & 快充车载充电器', '79.00', '158.00', 'https://resource.smartisan.com/resource/d4480234a2f24b0ff5acd98288fd902d.jpg,https://resource.smartisan.com/resource/69ebf4ca620e6d5a1bb7cb54741e24d3.jpg,https://resource.smartisan.com/resource/214a422b7d250333bec4398d47eac601.jpg,https://resource.smartisan.com/resource/f512a3c4b97d204555f864d4aa17e7e9.jpg,https://resource.smartisan.com/resource/fc8a5d50ed260d9798cfba39ff5234d0.jpg', '1', '2020-07-26 15:13:06', '2020-07-26 23:13:05');
INSERT INTO `tb_order_item` VALUES ('abcf8db260754998adbd945c9f67abcf', '100023501', '67a6c18aa6454519996f19ecab8d858f', '79', 'Smartisan 双口 & 快充车载充电器', '79.00', '158.00', 'https://resource.smartisan.com/resource/d4480234a2f24b0ff5acd98288fd902d.jpg,https://resource.smartisan.com/resource/69ebf4ca620e6d5a1bb7cb54741e24d3.jpg,https://resource.smartisan.com/resource/214a422b7d250333bec4398d47eac601.jpg,https://resource.smartisan.com/resource/f512a3c4b97d204555f864d4aa17e7e9.jpg,https://resource.smartisan.com/resource/fc8a5d50ed260d9798cfba39ff5234d0.jpg', '1', '2020-07-26 14:53:36', '2020-07-26 22:53:36');
INSERT INTO `tb_order_item` VALUES ('bec8a11ff66f4add8770715c5417523d', '100023501', '31053b08575f44fab2e7816080798768', '81', 'Smartisan 双口 & 快充车载充电器', '79.00', '158.00', 'https://resource.smartisan.com/resource/d4480234a2f24b0ff5acd98288fd902d.jpg,https://resource.smartisan.com/resource/69ebf4ca620e6d5a1bb7cb54741e24d3.jpg,https://resource.smartisan.com/resource/214a422b7d250333bec4398d47eac601.jpg,https://resource.smartisan.com/resource/f512a3c4b97d204555f864d4aa17e7e9.jpg,https://resource.smartisan.com/resource/fc8a5d50ed260d9798cfba39ff5234d0.jpg', '1', '2020-07-26 14:50:00', '2020-07-26 22:50:00');
INSERT INTO `tb_order_item` VALUES ('c5a17ac7c4e14ea8be343d006989855b', '100023501', '3ed82e4f16e24b10b98870d58b27bae7', '83', 'Smartisan 双口 & 快充车载充电器', '79.00', '158.00', 'https://resource.smartisan.com/resource/d4480234a2f24b0ff5acd98288fd902d.jpg,https://resource.smartisan.com/resource/69ebf4ca620e6d5a1bb7cb54741e24d3.jpg,https://resource.smartisan.com/resource/214a422b7d250333bec4398d47eac601.jpg,https://resource.smartisan.com/resource/f512a3c4b97d204555f864d4aa17e7e9.jpg,https://resource.smartisan.com/resource/fc8a5d50ed260d9798cfba39ff5234d0.jpg', '1', '2020-07-26 14:43:04', '2020-07-26 22:43:04');
INSERT INTO `tb_order_item` VALUES ('d570e47a972842b787794e0c6f9f7b39', '100023501', 'ee1c590e67f44936bcd71ff68f70b7bd', '77', 'Smartisan 双口 & 快充车载充电器', '79.00', '158.00', 'https://resource.smartisan.com/resource/d4480234a2f24b0ff5acd98288fd902d.jpg,https://resource.smartisan.com/resource/69ebf4ca620e6d5a1bb7cb54741e24d3.jpg,https://resource.smartisan.com/resource/214a422b7d250333bec4398d47eac601.jpg,https://resource.smartisan.com/resource/f512a3c4b97d204555f864d4aa17e7e9.jpg,https://resource.smartisan.com/resource/fc8a5d50ed260d9798cfba39ff5234d0.jpg', '1', '2020-07-26 14:54:50', '2020-07-26 22:54:49');
